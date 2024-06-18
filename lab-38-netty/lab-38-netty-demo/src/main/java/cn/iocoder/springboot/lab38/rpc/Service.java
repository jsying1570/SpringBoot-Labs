package cn.iocoder.springboot.lab38.rpc;

import java.io.IOException;

public class Service {
    public static void main(String[] args) throws IOException {
        Server serviceServer = new ServiceCenter(8088);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        serviceServer.start();
    }
}
