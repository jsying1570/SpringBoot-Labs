package cn.iocoder.springboot.lab38.niosgroupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final Integer PORT = 9000;
    private static final String HOSTNAME = "127.0.0.1";

    NIOServer() {
        try {
            //创建一个serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(HOSTNAME, PORT));
            //创建一个selector
            selector = Selector.open();
            //注册channel
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            listen();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //监听
    public void listen() {
        try {
            while (true) {
                //没有事件来得时候就阻塞
                final int select = selector.select();
                //select > 0 表示有事件发生
                if (select > 0) {
                    final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey key = iterator.next();
                        //处理key
                        handle(key);
                        //处理一个Key后要清除
                        iterator.remove();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handle(SelectionKey key) {
        try {
            //连接事件
            if (key.isAcceptable()) {
                final SocketChannel channel = serverSocketChannel.accept();
                channel.configureBlocking(false);
                //注册channel
                channel.register(selector, SelectionKey.OP_READ);
                //设置非阻塞
                channel.configureBlocking(false);
                //打印谁上线了
                final String hostName = channel.getRemoteAddress().toString().substring(1);
                System.out.println("主机: " + hostName + "上线了...");
            }
            if (key.isReadable()) {
                //处理读
                recvData(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //接收数据并处理
    private void recvData(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(5);
            int read = channel.read(buffer);
            if (read == -1) {
                System.out.println("read: " + read);
                channel.close();
            }
            //向除了自己的其他channel发送数据
            String msg = new String(buffer.array());
            sendOtherClient(msg, channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送数据到客户端
    private void sendOtherClient(String msg, SocketChannel self) throws IOException {
        //获取所有的通道
        final Set<SelectionKey> selectionKeys = selector.keys();
        final Iterator<SelectionKey> iterator = selectionKeys.iterator();

        String backMsg = self.getRemoteAddress().toString().substring(1) + "说 :" +msg;
        while (iterator.hasNext()) {
            final SelectableChannel channel = iterator.next().channel();
            if (channel instanceof SocketChannel && self != channel){
                System.out.println("NIOServer.sendOtherClient");
                ((SocketChannel) channel).write(ByteBuffer.wrap(backMsg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {
        NIOServer nioServer = new NIOServer();
    }
}
