package com.jsy.flow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * 动态处理会签 完成条件
 */
@Component("multiInstanceDelegate")
public class MultiInstanceDelegate {

    public boolean completeInstanceTask(DelegateExecution execution){
        // 获取会签执行的相关参数信息
        Integer nrOfInstances = (Integer) execution.getVariable("nrOfInstances");
        Integer nrOfActiveInstances = (Integer) execution.getVariable("nrOfActiveInstances");
        Integer nrOfCompletedInstances = (Integer) execution.getVariable("nrOfCompletedInstances");
        Object loopCounter = execution.getVariable("loopCounter");
        System.out.println("nrOfInstances = " + nrOfInstances);
        System.out.println("nrOfActiveInstances = " + nrOfActiveInstances);
        System.out.println("nrOfCompletedInstances = " + nrOfCompletedInstances);
        System.out.println("loopCounter = " + loopCounter);
        return nrOfCompletedInstances > nrOfActiveInstances;
    }
}
