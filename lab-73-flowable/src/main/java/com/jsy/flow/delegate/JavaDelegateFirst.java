package com.jsy.flow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * Java代理类
 */
public class JavaDelegateFirst implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("--服务任务--"  + LocalDateTime.now());
    }
}
