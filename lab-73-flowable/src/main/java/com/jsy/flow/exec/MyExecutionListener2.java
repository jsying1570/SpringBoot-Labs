package com.jsy.flow.exec;

import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.impl.bpmn.listener.ExpressionExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 执行监听器
 * 表达式
 */
@Component("myExecutionListener2")
public class MyExecutionListener2  {

    /**
     * 执行监听器触发的回调方法
     */
    public void trigger(DelegateExecution execution) {
        System.out.println("MyExecutionListener2---- 开始");
        FlowElement currentFlowElement = execution.getCurrentFlowElement();
        System.out.println("currentFlowElement.getName() = " + currentFlowElement.getName());
        String currentActivityId = execution.getCurrentActivityId();
        System.out.println("currentActivityId = " + currentActivityId);
        System.out.println("execution.getEventName() = " + execution.getEventName());
        System.out.println("MyExecutionListener2---- 结束");
    }
}
