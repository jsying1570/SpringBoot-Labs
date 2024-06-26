package cn.iocoder.springboot.lab38.netty.sequence;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


public class EchoInboundHandler3 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("进入 EchoInboundHandler3.channelRead");

        String data = ((ByteBuf)msg).toString(CharsetUtil.UTF_8);
        System.out.println("EchoInboundHandler3.channelRead 接收到数据：" + data);
        //ctx.writeAndFlush(UnPooled.copiedBuffer("[第二次write] [EchoInboundHandler3] " + data, CharsetUtil.UTF_8));
        ctx.fireChannelRead(msg);

        System.out.println("退出 EchoInboundHandler3 channelRead");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[EchoInboundHandler3.channelReadComplete]读取数据完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[EchoInboundHandler3.exceptionCaught]");
    }


}