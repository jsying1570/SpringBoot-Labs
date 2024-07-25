package com.jsy.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * ThenRun() 做完第一个任务后，再做第二个任务。某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
 *  调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
 *  调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
 *  thenAccept和thenAcceptAsync，thenApply和thenApplyAsync等，它们之间的区别也是如此
 */
public class ThenRunTest {
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<Void> thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture.get());
    }
}
