package httpclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@lombok.Data
@ToString
public class Data {
    private Integer id;
    private String email;
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;
    private String avatar;
}
