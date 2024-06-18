package cn.iocoder.springboot.lab38;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    private static final String path = "C:\\工作\\workspace\\study1.0\\netty\\src\\main\\java\\com\\jsy\\base\\data2.txt";
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(path,"rw")){
            FileChannel channel = raf.getChannel();
            FileLock lock = channel.lock(0,5,true);

            System.out.println("lock:"+lock.isValid());
            System.out.println("lock type:"+lock.isShared());
            lock.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
