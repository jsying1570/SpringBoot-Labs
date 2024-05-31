package com.jsy.flow.exec;

import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 执行监听器
 * 委托表达式
 */
@Component("myExecutionListener3")
public class MyExecutionListener3 implements ExecutionListener {

    /**
     * 执行监听器触发的回调方法
     */
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("MyExecutionListener3---- 开始");
        FlowElement currentFlowElement = execution.getCurrentFlowElement();
        System.out.println("currentFlowElement.getName() = " + currentFlowElement.getName());
        String currentActivityId = execution.getCurrentActivityId();
        System.out.println("currentActivityId = " + currentActivityId);
        System.out.println("execution.getEventName() = " + execution.getEventName());
        System.out.println("MyExecutionListener3---- 结束");
    }
}
