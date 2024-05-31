package com.jsy.flow.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FlowableConfig {

    @Autowired
    SpringProcessEngineConfiguration configuration;

    /**
     * @PostConstruct 在系统启动之后触发
     */
    @PostConstruct
    public void setFlowableEventListenerConfiguration(){
        // 绑定全局事件监听器
        // configuration.setEventListeners(Collections.singletonList(new MyGlobalEventListener()));
        /*Map<String, List<FlowableEventListener>> map = new HashMap<>();
        map.put(FlowableEngineEventType.ENTITY_CREATED.name()
                ,Arrays.asList(new MyGlobalEventListener()));
        // 绑定特定的事件类型
        configuration.setTypedEventListeners(map);*/
        // 放开 事件监听器的日志
        //configuration.setEnableDatabaseEventLogging(true);
        // 我们如果不想要使用事件监听器。可以禁用
        configuration.setEnableEventDispatcher(false);
    }
}
