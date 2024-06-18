package cn.iocoder.springboot.lab38;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadTest {
    private static final String path = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\data.txt";
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(path)){
            final FileChannel channel = fis.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //从通道里面读
            channel.read(buffer);

            final byte[] array = buffer.array();
            System.out.println(new String(array));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
