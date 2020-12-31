package com.example.jukebox;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Jukebox extends AbstractVerticle {
  private static final int HTTP_PORT = 8080;
  private static final int CONTROL_HTTP_PORT = 8081;
  private enum State {PLAYING, PAUSED}
  private State currentMode = State.PAUSED;
  private final Queue<String> playlist = new ArrayDeque<>();
  private final Set<HttpServerResponse> streamers = new HashSet<>();
  private AsyncFile currentFile;
  private long positionInFile;

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();
    eventBus.consumer("jukebox.list", this::list);
    eventBus.consumer("jukebox.schedule", this::schedule);
    eventBus.consumer("jukebox.play", this::play);
    eventBus.consumer("jukebox.pause", this::pause);

    vertx.createHttpServer()
      .requestHandler(this::httpHandler)
      .listen(HTTP_PORT, ar -> {
        if(ar.succeeded()) {
          log.info("Http Server started at {}", HTTP_PORT);
        } else {
          log.error("Create Http Server failed", ar.cause());
        }
      });
    vertx.setPeriodic(100, this::streamAudioChunk);

    vertx.createHttpServer()
      .requestHandler(this::controlHttpHandler)
      .listen(CONTROL_HTTP_PORT, ar -> {
        if(ar.succeeded()) {
          log.info("Control Http Server started at {}", CONTROL_HTTP_PORT);
        } else {
          log.error("Create Control Http Server failed", ar.cause());
        }
      });
  }

  private void streamAudioChunk(long id) {
    if(currentMode == State.PAUSED) {
      return;
    }
    if(currentFile == null && playlist.isEmpty()) {
      log.info("playlist is empty");
      currentMode = State.PAUSED;
      return;
    }
    if(currentFile == null) {
      openNextFile();
    }
    currentFile.read(Buffer.buffer(4096), 0, positionInFile, 4096, ar -> {
      if(ar.succeeded()) {
        processReadBuffer(ar.result());
      } else {
        log.error("Read Failed", ar.cause());
        closeCurrentFile();
      }
    });
  }

  private void processReadBuffer(Buffer buffer) {
//    log.info("process read buffer");
    positionInFile +=  buffer.length();
    if(buffer.length() == 0) {
      closeCurrentFile();
      return;
    }
    for(HttpServerResponse streamer : streamers) {
      if(!streamer.writeQueueFull()) {
        streamer.write(buffer);
      }
    }
  }

  private void closeCurrentFile() {
    positionInFile = 0;
    currentFile.close();
    currentFile = null;
  }

  private void openNextFile() {
    log.info("open next file");
    OpenOptions options = new OpenOptions().setRead(true);
    currentFile = vertx.fileSystem().openBlocking("tracks/" + playlist.poll(), options);
  }

  private void controlHttpHandler(HttpServerRequest request) {
    if("/play".equals(request.path())) {
      vertx.eventBus().send("jukebox.play", "");
      request.response().setStatusCode(200).end();
      return;
    }
    if("/pause".equals(request.path())) {
      vertx.eventBus().send("jukebox.pause", "");
      request.response().setStatusCode(200).end();
      return;
    }
    if("/list".equals(request.path())) {
      vertx.eventBus().request("jukebox.list", "", reply -> {
        if(reply.succeeded()) {
          JsonObject data = (JsonObject) reply.result().body();
          data.getJsonArray("files")
            .stream()
            .forEach(name -> {
              playlist.offer(name.toString());
            });
        } else {
          log.error("/list error", reply.cause());
        }
      });
      request.response().setStatusCode(200).end();
      return;
    }
    request.response().setStatusCode(404).end();
  }

  private void httpHandler(HttpServerRequest request) {
    if("/".equals(request.path())) {
      log.info("to openAudioStream() {}", request);
      openAudioStream(request);
      return;
    }
    request.response().setStatusCode(404).end();
  }

  private void openAudioStream(HttpServerRequest request) {
    HttpServerResponse response = request.response();
    response
      .putHeader("Content-Type", "audio/mpeg3")
      .setChunked(true);
    streamers.add(response);
    response.endHandler(v -> {
      streamers.remove(response);
      log.info("A streamer left");
    });
  }

  private void play(Message<?> request) {
    currentMode = State.PLAYING;
  }

  private void pause(Message<?> request) {
    currentMode = State.PAUSED;
  }

  private void schedule(Message<JsonObject> request) {
    String file = request.body().getString("file");
    if(playlist.isEmpty() && currentMode == State.PAUSED) {
      currentMode = State.PLAYING;
    }
    playlist.offer(file);
  }

  private void list(Message<?> request) {
    vertx.fileSystem().readDir("tracks", ".*mp3$", ar -> {
      if(ar.succeeded()) {
        List<String> files = ar.result()
          .stream()
          .map(File::new)
          .map(File::getName)
          .collect(Collectors.toList());
        JsonObject json = new JsonObject().put("files", new JsonArray(files));
        log.info("list: {}", json.toString());
        request.reply(json);
      } else {
        log.error("readDir failed", ar.cause());
        request.fail(500, ar.cause().getMessage());
      }
    });
  }
}
