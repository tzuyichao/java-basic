package protocol.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper = objectMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }

    public static String pojoToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        return "";
    }

    public static <T> T jsonToPojo(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        return  null;
    }
}
