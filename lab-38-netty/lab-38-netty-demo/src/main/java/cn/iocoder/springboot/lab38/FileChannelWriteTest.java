package cn.iocoder.springboot.lab38;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {
    private static final String path = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\data.txt";
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            final FileChannel channel = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //数据写入buffer
            byteBuffer.put("hello,你好!!".getBytes());
            //buffer反转
            byteBuffer.flip();
            //读buffer中得数据
            channel.write(byteBuffer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
