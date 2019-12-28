package org.greenrivers.hello.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.jackson.DatabindCodec;
import lombok.extern.slf4j.Slf4j;
import org.greenrivers.hello.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentVerticle extends AbstractVerticle {
    public static final String GET_ALL_DOCUMENTS = "get.all.documents";

    private DocumentService documentService;

    private final ObjectMapper mapper = DatabindCodec.mapper();

    @Autowired
    public void DocumentVerticle(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);
        vertx.eventBus()
                .<String>consumer(GET_ALL_DOCUMENTS)
                .handler(getAllDocuments());
    }

    private Handler<Message<String>> getAllDocuments() {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(documentService.getAllDocuments()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                future.fail(e.getMessage());
            }
        }, result -> {
            if(result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause().toString());
            }
        });
    }


}
