package cn.iocoder.springboot.lab38.netty.buf;

import java.nio.ByteBuffer;

public class JDKBuffer {
    public void test01() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        byte[] bytes = "hello wor".getBytes();
        for (int i = 0; i < 6; i++) {
            buffer.put(bytes[i]);
        }
        showPositionAndLimit(buffer);
//        System.out.println(buffer.arrayOffset());
//        buffer.clear();
////        buffer.flip();
////        showPositionAndLimit(buffer);
////        show(buffer);
        buffer.flip();
        showPositionAndLimit(buffer);
        show(buffer);
        buffer.reset(); //其实就是将Position设置成Mark标记的值
        showPositionAndLimit(buffer);
    }


    //显示数据
    private void show(ByteBuffer buffer) {
        int count = 0;
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            if (count == 3) {
                buffer.mark();
            }
            count++;
            System.out.println((char) b);
        }
    }

    private void showPositionAndLimit(ByteBuffer buffer) {
        System.out.println("limit:" + buffer.limit() + " " + "position:" + buffer.position());
    }
}
