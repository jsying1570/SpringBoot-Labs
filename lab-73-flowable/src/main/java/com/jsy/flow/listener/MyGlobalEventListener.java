package com.jsy.flow.listener;

import org.flowable.common.engine.api.delegate.event.AbstractFlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;

/**
 * 全局事件监听器
 */
public class MyGlobalEventListener extends AbstractFlowableEventListener {
    /**
     * 当对应的全局事件发生的时候对应的回调方法
     * @param flowableEvent
     */
    @Override
    public void onEvent(FlowableEvent flowableEvent) {
        System.out.println("------------>"+flowableEvent.getType().name());
        if(FlowableEngineEventType.ENTITY_CREATED.equals(flowableEvent.getType())){
            System.out.println("-------实体创建事件------");
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
