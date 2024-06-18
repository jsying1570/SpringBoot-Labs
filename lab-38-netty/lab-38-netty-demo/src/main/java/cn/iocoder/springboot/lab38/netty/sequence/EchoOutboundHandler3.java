package cn.iocoder.springboot.lab38.netty.sequence;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;


public class EchoOutboundHandler3 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler3.write");

        ctx.write(msg);

        System.out.println("退出 EchoOutboundHandler3.write");
    }

}