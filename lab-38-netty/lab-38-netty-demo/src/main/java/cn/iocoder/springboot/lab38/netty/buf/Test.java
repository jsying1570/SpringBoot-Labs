package cn.iocoder.springboot.lab38.netty.buf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Test {
    private volatile int count = 1;
    private static final AtomicIntegerFieldUpdater<Test> atf = AtomicIntegerFieldUpdater.newUpdater(Test.class,"count");
    public static void main(String[] args) {
        Test test = new Test();
        boolean b = atf.compareAndSet(test, 1, 4);

        int i = atf.addAndGet(test, 4);
        System.out.println(i);
        System.out.println(test.count);
        System.out.println(b);
    }
}
