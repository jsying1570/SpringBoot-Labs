package cn.iocoder.springboot.lab38.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.nio.ByteBuffer;

/**
 * netty ByteBuf  采用Facade(门面模式)，对JDK中的ByteBuf进行的封装
 */
public class NettyByteBuf03 {
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }

    //测试JDK的bytebuff
    private static void test01(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String content = "贾少映 CTO之路";
        buffer.put(content.getBytes());

        //获取数据
        buffer.flip();

        System.out.println(buffer.remaining());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        byte[] dst = new byte[buffer.remaining()];
        buffer.get(dst);
        String decodeValue = new String(dst);
        System.out.println(decodeValue);
    }
    //netty 中的bytebuffer
    private static void test02(){
        ByteBuf buf = Unpooled.copiedBuffer("贾少映 CTO之路".getBytes());

        byte[] bytes = new byte[6];
        buf.readBytes(bytes);
        System.out.println(new String(bytes));

        System.out.println(buf.readerIndex());

        //转换为jdk的bytebuff:转换后，索引相互不影响
        ByteBuffer buffer = buf.nioBuffer();
        System.out.println(buffer.position());

    }

    private static void test03(){
        UnpooledByteBufAllocator bufAllocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = bufAllocator.buffer();
        System.out.println(buffer.capacity());

    }

}
