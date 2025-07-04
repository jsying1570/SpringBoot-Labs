package com.salter.demo01;


import com.salter.service.GreeterGrpc;
import com.salter.service.GreeterProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author salter
 */
public class GreeterClient {
    private static final Logger logger = Logger.getLogger(GreeterClient.class.getName());

    private final ManagedChannel channel;
    /**
     * 调用 SayHello 方法
     */
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /**
     * 构造函数，初始化 Channel 和 Stub
     * @param host 主机
     * @param port 端口
     */
    public GreeterClient(String host, int port) {
        // 创建一个到服务器的 Channel
        // usePlaintext() 用于测试，实际生产中应使用 TLS 加密
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        // 基于 Channel 创建 Stub
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    /**
     * 关闭 Channel
     * @throws InterruptedException 中断异常
     */
    public void     shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 调用 SayHello 方法
     * @param name 名称
     */
    public void greet(String name) {
        logger.info("准备向服务器发送请求, name: " + name);
        // 创建请求消息
        GreeterProto.HelloRequest request = GreeterProto.HelloRequest.newBuilder().setName(name).build();
        GreeterProto.HelloReply response;

        try {
            // 通过 Stub 调用远程方法
            response = blockingStub.sayHello(request);
        } catch (Exception e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e);
            return;
        }
        logger.info("收到服务器的响应: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建客户端，连接本地 50051 端口
        GreeterClient client = new GreeterClient("127.0.0.1", 50051);
        try {
            // 发送请求
            String user = "gRPC Java";
            if (args.length > 0) {
                user = args[0];
            }
            client.greet(user);
        } finally {
            // 关闭客户端
            client.shutdown();
        }
    }
}