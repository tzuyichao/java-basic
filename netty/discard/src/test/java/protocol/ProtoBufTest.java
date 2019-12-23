package protocol;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ProtoBufTest {
    @Test
    public void test_main() {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(10);
        builder.setContent("test");
        MsgProtos.Msg message = builder.build();

        assertNotNull(message);
        log.info("message: {}", message);
    }

    private MsgProtos.Msg buildMsg(int id, String message) {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(id);
        builder.setContent(message);
        MsgProtos.Msg msg = builder.build();
        return msg;
    }

    @Test
    void test_marshal_unmarshal1() throws IOException {
        MsgProtos.Msg msg = buildMsg(1, "test　測試");
        byte[]  byteArrayContent = msg.toByteArray();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(byteArrayContent);
        byte[] data = outputStream.toByteArray();

        MsgProtos.Msg readMsg = MsgProtos.Msg.parseFrom(data);
        assertEquals(msg.getId(), readMsg.getId());
        assertEquals(msg.getContent(), readMsg.getContent());
        log.info("msg content: {}", readMsg.getContent());
    }

    @Test
    void test_marshal_unmarshal2() throws IOException {
        MsgProtos.Msg msg = buildMsg(2, "測試");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        msg.writeTo(outputStream);
        byte[] data = outputStream.toByteArray();

        MsgProtos.Msg readMsg = MsgProtos.Msg.parseFrom(data);
        assertEquals(msg.getId(), readMsg.getId());
        assertEquals(msg.getContent(), readMsg.getContent());
        log.info("msg content: {}", readMsg.getContent());
    }

    @Test
    void test_marshal_unmarshal3() throws IOException {
        MsgProtos.Msg msg = buildMsg(3, "測試 gogogo");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        msg.writeDelimitedTo(outputStream);
        byte[] data = outputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        MsgProtos.Msg readMsg = MsgProtos.Msg.parseDelimitedFrom(inputStream);
        assertEquals(msg.getId(), readMsg.getId());
        assertEquals(msg.getContent(), readMsg.getContent());
        log.info("msg content: {}", readMsg.getContent());
    }
}
