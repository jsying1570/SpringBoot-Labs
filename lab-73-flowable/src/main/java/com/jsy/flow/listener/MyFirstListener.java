package com.jsy.flow.listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

import java.time.LocalDateTime;

/**
 * 第一个监听器
 */
public class MyFirstListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("MyFirstListener --- 执行了" + LocalDateTime.now().toString());
    }
}
