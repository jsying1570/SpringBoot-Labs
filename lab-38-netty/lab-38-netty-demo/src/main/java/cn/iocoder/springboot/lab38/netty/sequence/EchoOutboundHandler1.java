package cn.iocoder.springboot.lab38.netty.sequence;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;


public class EchoOutboundHandler1 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler1.write");

        //ctx.writeAndFlush(UnPooled.copiedBuffer("[第一次write中的write]", CharsetUtil.UTF_8));
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("在OutboundHandler里测试一下channel().writeAndFlush", CharsetUtil.UTF_8));
        ctx.write(msg);

        System.out.println("退出 EchoOutboundHandler1.write");
    }
}