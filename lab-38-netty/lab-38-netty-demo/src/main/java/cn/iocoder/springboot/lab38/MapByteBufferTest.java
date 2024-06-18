package cn.iocoder.springboot.lab38;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接操作系统操作，而不会出现内存拷贝，所以速度比较快
 * 这里得例子就是更改文件中得数据，而不会内存拷贝，直接是再操作系统级别
 * 内存映射文件
 */
public class MapByteBufferTest {
    private static final String path = "C:\\工作\\workspace\\study1.0\\netty\\src\\main\\java\\com\\jsy\\base\\data2.txt";

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            final FileChannel channel = raf.getChannel();
            //这里表示，buffer为读写模式，并操作0位置开始，5个字节得数据
            //表示说将文件直接映射到内存，对于内存中的数据的修改，将会由操作系统将修改的内容给写入到磁盘，操作的内存为直接内存
            final MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            buffer.put(1, (byte) 'h');
            buffer.put(2, (byte) 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
