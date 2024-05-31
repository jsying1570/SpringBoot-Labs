package com.jsy.flow.delegate;

import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * Java代理类
 */
public class JavaDelegate04 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("--服务任务--JavaDelegate04 扣减库存 "  + LocalDateTime.now());
        /*Object totalScore = execution.getVariable("totalScore");
        System.out.println("totalScore = " + totalScore);*/
        throw new BpmnError("error03");

    }
}
