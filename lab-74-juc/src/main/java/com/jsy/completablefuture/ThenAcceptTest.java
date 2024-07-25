package com.jsy.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，
 * 会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的
 */
public class ThenAcceptTest {
    @Test
    public void test(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1;
        }).thenAccept(item -> {
            System.out.println("thenAccept");
            System.out.println(item);
        });
    }
}
