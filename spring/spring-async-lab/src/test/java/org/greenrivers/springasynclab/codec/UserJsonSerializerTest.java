package org.greenrivers.springasynclab.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.greenrivers.springasynclab.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;

@Slf4j
@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonSerializerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialization() throws JsonProcessingException {
        User user = new User();
        user.setFavoriteColor(Color.lightGray);
        String json = objectMapper.writeValueAsString(user);
        log.info(json);

    }
}
