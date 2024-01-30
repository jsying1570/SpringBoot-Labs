package com.jsy.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * 自定义的监听器
 * @author jsyin
 */
public class MyTaskListener implements TaskListener {
    /**
     * 监听器触发的方法
     * @param delegateTask 委派任务
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("MyTaskListener触发了..." + delegateTask.getName());
        if("创建请假流程".equals(delegateTask.getName())
                &&"create".equals(delegateTask.getEventName())){
            // 满足触发条件的事件，那么我们就来设置 assignee
            delegateTask.setAssignee("小明");
        }else{
            delegateTask.setAssignee("小李");
        }
    }
}
