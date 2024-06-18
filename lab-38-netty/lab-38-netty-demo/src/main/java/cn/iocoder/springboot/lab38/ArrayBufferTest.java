package cn.iocoder.springboot.lab38;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 设置数组buffer，看channel分散和聚集得情况
 */
public class ArrayBufferTest {
    public static void main(String[] args) {
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            //绑定端口
            server.socket().bind(new InetSocketAddress(9000));
            //获取通道
            final SocketChannel channel = server.accept();

            System.out.println("接收一个连接:");
            //创建buffer数组
            ByteBuffer[] buffers = new ByteBuffer[2];
            buffers[0] = ByteBuffer.allocate(5);
            buffers[1] = ByteBuffer.allocate(3);

            //设置只读8个字节
            int messageLength = 8;
            while (true) {
                //将数据从channel中读出
                int readCount = 0;
                while (readCount < messageLength) {
                    final long read = channel.read(buffers);
                    readCount += read;

                    System.out.println("readCount =" + readCount);
                    //打印看看每个通道中得情况
                    Arrays.stream(buffers).map(x -> "Position = " + x.position() + " Limit = " + x.limit()).forEach(System.out::println);
                }

                Arrays.stream(buffers).forEach(Buffer::flip);

                //将数据写入channel中
                int writeCount = 0;
                while (writeCount < messageLength) {
                    //将数据写入通道中
                    final long write = channel.write(buffers);
                    writeCount += write;
                    System.out.println("writeCount ="+writeCount);
                }
                //清空buffer
                Arrays.stream(buffers).forEach(Buffer::clear);
                System.out.println("readCount =" + readCount + "writeCount =" + writeCount);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
