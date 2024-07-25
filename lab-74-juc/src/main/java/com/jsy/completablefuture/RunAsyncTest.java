package com.jsy.completablefuture;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * runAsync是创建没有返回值的异步任务。它有如下两个方法，
 * 一个是使用默认线程池（ForkJoinPool.commonPool()）的方法
 * 一个是带有自定义线程池的重载方法
 */
public class RunAsyncTest {
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println("do something....");
            try {
                Thread.sleep(10000);
                int a = 1 / 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //等待任务执行完成
        System.out.println("结果->" + cf.get());
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException, TimeoutException {
        // 自定义线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("do something....");
        }, executorService);

        //1.等待任务执行完成
//        System.out.println("结果->" + cf.get());
        //2.等待任务3秒，3秒没完成，直接返回
        System.out.println("结果->" + cf.get(5, TimeUnit.SECONDS));
    }

    @Test
    public void test03() {
        // 自定义线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000);
                int a = 1 / 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("do something....");
        }, executorService);

        System.out.println("结果->" + cf.join());
    }
}
