package com.example.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.net.NetSocket;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createNetServer()
      .connectHandler(this::handleNewConnection)
      .listen(3000, netServerAsyncResult -> {
        if(netServerAsyncResult.succeeded()) {
          System.out.println("Net Server started on port 3000");
        } else {
          System.err.println(netServerAsyncResult.cause());
        }
      });

    vertx.createHttpServer()
      .connectionHandler(this::handleNewHttpConnection)
      .requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void handleNewHttpConnection(HttpConnection httpConnection) {
    System.out.println(httpConnection.toString());
  }

  private void handleNewConnection(NetSocket netSocket) {
    netSocket.handler(buffer -> {
      netSocket.write(buffer);
      if(buffer.toString().endsWith("/quit\n")) {
        netSocket.close();
      }
    });
  }
}
