package com.jsy.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * supplyAsync是创建带有返回值的异步任务。
 * 它有两个方法:
 * 一个是使用默认线程池（ForkJoinPool.commonPool()）的方法
 * 一个是带有自定义线程池的重载方法
 */
public class SupplyAsyncTest {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something...."+Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "result";
        });

        //等待任务执行完成
        System.out.println("结果->:"+Thread.currentThread().getName()+" " + cf.get());
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        //传递线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something....");
            return "result";
        }, executorService);

        //等待子任务执行完成
        System.out.println("结果->" + cf.get());
    }
}
