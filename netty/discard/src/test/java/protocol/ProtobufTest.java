package protocol;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ProtobufTest {
    @Test
    public void test_main() {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(10);
        builder.setContent("test");
        MsgProtos.Msg message = builder.build();

        assertNotNull(message);
        log.info("message: {}", message);
    }
}
