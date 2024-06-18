package cn.iocoder.springboot.lab38.niosgroupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {
    private SocketChannel socketChannel;
    private Selector selector;
    private static final String HOSTNAME = "127.0.0.1";
    private static final Integer PORT = 9000;

    NIOClient() {
        try {
            socketChannel = SocketChannel.open();
            System.out.println(socketChannel.hashCode());
            selector = Selector.open();
            socketChannel.connect(new InetSocketAddress(HOSTNAME, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

//            new Thread(new SendData()).start();
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            socketChannel.read(buffer);
//            System.out.println(new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recvData() {
        try {
            String client = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("==============="+client);
            //发送数据
            new Thread(new SendData()).start();
            //获取其他客户端发送的数据
            while (true) {
                if(selector.select() > 0) {
                    final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey key = iterator.next();
                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();
                            System.out.println(channel.hashCode());
                            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                            channel.read(byteBuffer);
                            String msg = new String(byteBuffer.array());
                            System.out.println(msg);
                        }
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NIOClient().recvData();
//        new NIOClient();
    }

    class SendData implements Runnable {
        private Scanner scanner = new Scanner(System.in);

        @Override
        public void run() {
            sendMsg();
        }

        public void sendMsg() {
            try {
                while (true) {
                    String msg = scanner.nextLine();
                    System.out.println("我：" + msg);
                    socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
