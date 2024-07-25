package com.jsy.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
 */
public class ExceptionallyTest {
    @Test
    public void test(){
        CompletableFuture.supplyAsync(()->{
            int i = 10 / 0;
            return 1024;
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return 404;
        }).whenComplete((t,u)->{
            System.out.println("t:"+t);
            System.out.println("u:"+u);
        }).join();
    }
}
