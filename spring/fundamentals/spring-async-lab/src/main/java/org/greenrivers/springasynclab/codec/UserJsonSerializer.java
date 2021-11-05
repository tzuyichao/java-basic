package org.greenrivers.springasynclab.codec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.greenrivers.springasynclab.model.User;
import org.springframework.boot.jackson.JsonComponent;

import java.awt.*;
import java.io.IOException;

/**
 * source: https://www.baeldung.com/spring-boot-jsoncomponent
 */
@JsonComponent
public final class UserJsonSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("favoriteColor", getColorAsWebColor(user.getFavoriteColor()));
        jsonGenerator.writeEndObject();
    }

    private static String getColorAsWebColor(Color color) {
        int r = (int)Math.round(color.getRed()*255.0);
        int g = (int)Math.round(color.getGreen()*255.0);
        int b = (int)Math.round(color.getBlue()*255.0);
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
