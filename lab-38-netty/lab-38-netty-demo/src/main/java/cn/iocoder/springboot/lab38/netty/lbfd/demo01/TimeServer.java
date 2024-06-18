package cn.iocoder.springboot.lab38.netty.lbfd.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {
    private static final int PORT = 8080;
    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加handler
                            ChannelPipeline pipeline = ch.pipeline();
                            //添加\r\n 换行符  作为分隔符
                            pipeline.addLast(new LineBasedFrameDecoder(1024));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new MySeverHandler());
                        }
                    });
            ChannelFuture cf = serverBootstrap.bind(port).sync();

            System.out.println("=================");
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        TimeServer timeServer = new TimeServer();
        int port = TimeServer.PORT;

        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        timeServer.bind(port);
    }
}
