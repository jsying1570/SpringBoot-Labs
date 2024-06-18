package cn.iocoder.springboot.lab38;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileChannelTransfrom {
    private static final String path = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\data.txt";
    private static final String path1 = "D:\\java\\workspace\\study10\\netty\\src\\main\\java\\com\\jsy\\base\\data2.txt";

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fos = new FileOutputStream(path1)) {
            final FileChannel channel1 = fis.getChannel();
            final FileChannel channel2 = fos.getChannel();
            //从目标通道拷贝本通道
            channel2.transferFrom(channel1,0,channel1.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
