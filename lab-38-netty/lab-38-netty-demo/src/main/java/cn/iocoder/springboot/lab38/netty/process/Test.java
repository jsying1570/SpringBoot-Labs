package cn.iocoder.springboot.lab38.netty.process;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(new Runnable() {
            public void run() {
                System.out.println(System.nanoTime()+"线程开始执行------------------------");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.nanoTime()+"线程执行结束------------------------");
            };
        });
        th.setDaemon(false);
        th.start();
        Thread.sleep(5000);
        System.out.println("Test.main");
    }
}
