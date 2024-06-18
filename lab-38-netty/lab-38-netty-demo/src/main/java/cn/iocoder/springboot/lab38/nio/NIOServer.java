package cn.iocoder.springboot.lab38.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private ServerSocketChannel server;
    private Selector selector;

    NIOServer() {
        initServer();
    }

    private void initServer() {
        try {
            server = ServerSocketChannel.open();
            //绑定端口
            server.socket().bind(new InetSocketAddress(9001));

            //设置ServerSocketChannel为非阻塞
            server.configureBlocking(false);
            //获取selector
            selector = Selector.open();
            //将serverSocketChannel注册到selector,并指定自己所关注得事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("selector监听得channel通道数 " + selector.keys().size());

            while (true) {
                //这里表示可以阻塞得时间为1s钟,1秒种没有时间，则同样得返回
                final int select = selector.select(1000);
                if (select == 0) {
                    System.out.println("未收到连接。。。");
                    continue;
                }
                //遍历selector中所注册得通道
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("发生事件得selectionKeys得数量 " + selectionKeys.size());

                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey key = iterator.next();
                    //处理每个通道
                    handle(key);
                    //移除当前处理得key
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(SelectionKey key) {
        //1.如果是请求连接得事件，则创建一个socketChannel
        if (key.isAcceptable()) {
            try {
                //创建一个channel
                final SocketChannel channel = server.accept();
                System.out.println("接收到一个客户端连接... " + channel.hashCode());
                //设置channel为一个非阻塞方式
                channel.configureBlocking(false);
                //将新创建得channel也加入到selector监管中
                channel.register(selector, SelectionKey.OP_READ);
                System.out.println("selector监听得channel通道数 " + selector.keys().size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (key.isReadable()) {
                System.out.println("=====================");
                //如果为读事件，则读取通道中得数据
                SocketChannel channel = (SocketChannel) key.channel();
                //创建buffer
                ByteBuffer buffer = ByteBuffer.allocate(10);
                try {
                    final int read = channel.read(buffer);
                    if (read == -1) {
                        System.out.println("read:" + read);
                        channel.close();
                    }
                    System.out.println("data:" + new String(buffer.array()));
                    //复原
                    buffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) {
        final NIOServer nioServer = new NIOServer();
    }
}

