package com.jsy.flow.delegate;

import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * Java代理类
 */
public class JavaDelegateSecond implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("--JavaDelegateSecond---服务任务--"  + LocalDateTime.now());
        // 抛出对应的错误 触发 事件子流程 中 错误开始事件
        throw new BpmnError("error01");
    }
}
