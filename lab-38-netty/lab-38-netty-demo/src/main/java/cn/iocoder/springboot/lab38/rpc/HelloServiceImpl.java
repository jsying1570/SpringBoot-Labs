package cn.iocoder.springboot.lab38.rpc;



public class HelloServiceImpl implements HelloService  {
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}
