package com.jsy.flow.delegate;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.context.Context;

import java.time.LocalDateTime;

/**
 * Java代理类
 */
public class JavaDelegate01 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        Object loopCounter = execution.getVariable("loopCounter");
        System.out.println("------>第"+loopCounter+"个");
    }
}
