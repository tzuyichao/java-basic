package protocol;

import lombok.Data;
import protocol.util.JsonUtil;

@Data
public class JsonMsg {
    private int id;
    private String content;

    public String convertToJson() {
        return JsonUtil.pojoToJson(this);
    }

    public static JsonMsg parseFromJson(String json) {
        return JsonUtil.jsonToPojo(json, JsonMsg.class);
    }

    @Override
    public String toString() {
        return this.convertToJson();
    }

}
