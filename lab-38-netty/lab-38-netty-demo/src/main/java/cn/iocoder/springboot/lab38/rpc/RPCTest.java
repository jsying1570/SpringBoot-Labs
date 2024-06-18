package cn.iocoder.springboot.lab38.rpc;

import java.net.InetSocketAddress;

public class RPCTest {
    public static void main(String[] args)  {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Server serviceServer = new ServiceCenter(8088);
//                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
//                    serviceServer.start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}
