package com.jsy.flow.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;

public class MyListener01 implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("MyListener01-----notify ---- 执行了"+delegateTask.getEventName());
        if(EVENTNAME_CREATE.equals(delegateTask.getEventName())){
            // 用户节点的创建  然后指派审批人
            delegateTask.setAssignee("boge");
        }
    }
}
