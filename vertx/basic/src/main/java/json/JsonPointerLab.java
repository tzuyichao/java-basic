package json;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointer;
import io.vertx.core.json.pointer.JsonPointerIterator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonPointerLab {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        JsonObject helloObject = new JsonObject();
        helloObject.put("world", 10);
        helloObject.put("vertx", "happy");
        jsonObject.put("hello", helloObject);

        JsonPointer pointer1 = JsonPointer.from("/hello/world");
        Object result = pointer1.queryJson(jsonObject);
        log.info("result: {}", result);
        Object result2 = pointer1.query(jsonObject, JsonPointerIterator.JSON_ITERATOR);
        log.info("result2: {}", result2);

        JsonPointer pointer2 = JsonPointer.create()
                .append("hello")
                .append("world");
        result = pointer2.queryJson(jsonObject);
        log.info("result: {}", result);
        result2 = pointer2.query(jsonObject, JsonPointerIterator.JSON_ITERATOR);
        log.info("result2: {}", result2);

        Object result3 = pointer2.writeJson(jsonObject, "Good");
        log.info("result3: {}", result3);
        log.info("jsonObject: {}", jsonObject);
    }
}
