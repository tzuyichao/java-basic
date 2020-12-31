package com.example.complexstreams;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadComplexStreams {
  public static void main(String[] args) throws InterruptedException {
    Vertx vertx = Vertx.vertx();
    OpenOptions options = new OpenOptions()
      .setRead(true);
    AsyncFile file = vertx.fileSystem().openBlocking("sample.db", options);
    RecordParser parser = RecordParser.newFixed(4, file);
    // for fetch mode
    parser.pause();
    parser.fetch(2);
    parser.handler(header -> readMagicNumber(header, parser));

    vertx.setTimer(10000, id -> {
      file.end(ar -> vertx.close());
    });
  }

  private static void readMagicNumber(Buffer header, RecordParser parser) {
    log.info("{}", header);
    log.info("Magic Number: {}:{}:{}:{}", header.getByte(0), header.getByte(1), header.getByte(2), header.getByte(3));
    parser.handler(version -> {
      readVersion(version, parser);
    });
  }

  private static void readVersion(Buffer header, RecordParser parser) {
    log.info("Version: {}", header.getInt(0));
    parser.delimitedMode("\n");
    parser.handler(name -> readName(name, parser));
  }

  private static void readName(Buffer header, RecordParser parser) {
    log.info("Name: {}", header.toString());
    parser.fixedSizeMode(4);
    parser.handler(keyLength -> readKey(keyLength, parser));
  }

  private static void readKey(Buffer keyLength, RecordParser parser) {
    parser.fixedSizeMode(keyLength.getInt(0));
    parser.handler(key -> readValue(key.toString(), parser));
  }

  private static void readValue(String key, RecordParser parser) {
    parser.fixedSizeMode(4);
    parser.handler(valueLength -> finishEntry(key, valueLength, parser));
  }

  private static void finishEntry(String key, Buffer valueLength, RecordParser parser) {
    parser.fixedSizeMode(valueLength.getInt(0));
    parser.handler(value -> {
      log.info("key: {}, value: {}", key, value);
      parser.fixedSizeMode(4);
      parser.handler(keyLength -> readKey(keyLength, parser));
    });
  }
}
