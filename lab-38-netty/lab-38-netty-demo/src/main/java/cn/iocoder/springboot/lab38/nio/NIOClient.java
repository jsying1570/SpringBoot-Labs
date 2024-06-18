package cn.iocoder.springboot.lab38.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args) {
        try {
            //创建一个客户端channel
            final SocketChannel open = SocketChannel.open();
            //设置channel为非阻塞
            open.configureBlocking(true);

            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9001);
            //connect方法不会阻塞再这儿
            if (!open.connect(inetSocketAddress)) {
                //客户端没连接上可以做其他得工作
                //判断客户端是否已经完成连接
                while (!open.finishConnect()){
                    System.out.println("因为连接需要时间,客户端不会阻塞，可以做其他工作。。。");
                }
            }
            //发送数据
            while (true){
                final String str = data();
                ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                //将数据写入buffer中
                open.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String data(){
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
