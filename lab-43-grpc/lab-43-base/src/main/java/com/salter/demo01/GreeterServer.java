package com.salter.demo01;


import com.salter.service.GreeterGrpc;
import com.salter.service.GreeterProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerMethodDefinition;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author salter
 */
public class GreeterServer {
    private static final Logger logger = Logger.getLogger(GreeterServer.class.getName());

    private Server server;

    /**
     * 启动服务器
     *
     * @throws IOException io异常
     */
    private void start() throws IOException {
        // 服务器端口
        int port = 50051;
        // 添加我们的服务实现
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("服务器已启动，监听端口: " + port);

        server.getServices().forEach(item -> {
            Collection<ServerMethodDefinition<?, ?>> methods = item.getMethods();
            methods.forEach(a -> System.out.println(a.getMethodDescriptor().getFullMethodName()));
        })
        ;
        logger.info("当前注册的服务列表：" + server.getServices().toString());
        // 添加 JVM 关闭钩子，确保在程序退出时优雅地关闭 gRPC 服务器
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                GreeterServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    /**
     * 关闭服务器
     *
     * @throws InterruptedException 中断异常
     */
    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * 阻塞主线程，直到服务器被终止
     *
     * @throws InterruptedException 中断异常
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final GreeterServer server = new GreeterServer();
        server.start();
        server.blockUntilShutdown();
    }

    /**
     * Greeter 服务实现类
     */
    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        // 实现 SayHello 方法
        @Override
        public void sayHello(GreeterProto.HelloRequest req, StreamObserver<GreeterProto.HelloReply> responseObserver) {
            logger.info("收到客户端请求, name: " + req.getName());

            // 构建响应消息
            String message = "Hello, " + req.getName();
            GreeterProto.HelloReply reply = GreeterProto.HelloReply.newBuilder().setMessage(message).build();

            // 发送响应给客户端
            responseObserver.onNext(reply);

            // 标记 RPC 已完成
            responseObserver.onCompleted();
        }
    }
}