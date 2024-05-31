package com.jsy.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

public class MyThreeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("--服务任务--MyThreeDelegate  "  + LocalDateTime.now());
        // throw new BpmnError("error2");
    }
}
