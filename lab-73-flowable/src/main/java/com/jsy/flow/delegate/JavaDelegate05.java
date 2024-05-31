package com.jsy.flow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * Java代理类
 */
public class JavaDelegate05 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("--服务任务--JavaDelegate05 锁定库存 "  + LocalDateTime.now());
       // System.out.println("sum = " + execution.getVariable("sum"));
    }
}
