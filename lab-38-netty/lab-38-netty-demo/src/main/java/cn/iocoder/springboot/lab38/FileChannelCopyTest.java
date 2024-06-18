package cn.iocoder.springboot.lab38;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelCopyTest {
    private static final String path = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\data.txt";
    private static final String path1 = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\dat1.txt";

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fos = new FileOutputStream(path1)) {

            final FileChannel channel1 = fis.getChannel();
            final FileChannel channel2 = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(512);
            while (true) {
                //从通道中
                final int result = channel1.read(buffer);
                if (result == -1) {
                    break;
                }
                //切换通道读写
                buffer.flip();
                //写入通道中
                channel2.write(buffer);

                //复位
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
