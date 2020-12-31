package com.example.complexstreams;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteComplexStreams {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    OpenOptions options = new OpenOptions()
      .setWrite(true)
      .setCreate(true);
    AsyncFile file = vertx.fileSystem().openBlocking("sample.db", options);
    Buffer buffer = Buffer.buffer();

    buffer.appendBytes(new byte[] {1, 2, 3, 4});
    buffer.appendInt(2);
    buffer.appendString("Sample database\n");
    String key = "abc";
    String value = "123456-abcde";

    buffer.appendInt(key.length());
    buffer.appendString(key);
    buffer.appendInt(value.length());
    buffer.appendString(value);

    key = "foof@bar";
    value = "Foo Bar Baz";

    buffer.appendInt(key.length());
    buffer.appendString(key);
    buffer.appendInt(value.length());
    buffer.appendString(value);

    file.end(buffer, ar -> {
      log.info("Write file completed");
      vertx.close();
    });
  }
}
