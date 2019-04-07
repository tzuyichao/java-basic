package nio.buffer;

import java.nio.ByteBuffer;

public class WrapLab {
    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArray);
        // 不具擷取的效果，offset, length參數是設定buffer的offset和limit的值
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteArray, 2, 5);

        BufferUtil.inspectBuffer(byteBuffer1);
        BufferUtil.inspectBuffer(byteBuffer2);
    }
}
