package com.jsy.flow.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;

/**
 * 任务监听器
 */
public class MyTaskListener1 implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("------start-----");
        String eventName = delegateTask.getEventName();
        System.out.println("eventName = " + eventName);
        System.out.println("delegateTask.getName() = " + delegateTask.getName());
        System.out.println(delegateTask.getId());
        System.out.println("------end-----");
    }
}
