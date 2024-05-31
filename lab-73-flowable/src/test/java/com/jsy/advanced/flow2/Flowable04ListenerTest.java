package com.jsy.advanced.flow2;

import com.jsy.flow.FlowableDemoApplication;
import com.jsy.flow.listener.MyGlobalEventListener;
import liquibase.pro.packaged.M;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 监听器
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable04ListenerTest {

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    /**
     * 流程部署操作
     */
    @Test
    void deployFlow() throws InterruptedException {
        //RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/02-高级篇/04-监听器/test1111123.bpmn20.xml")
                .name("监听器-事件监听器4")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());

    } //

    /**
     * 启动流程实例
     *  vkaffbzroxqgbghd
     */
    @Test
    void startFlow() throws InterruptedException{

        // 在流程定义表中动态维护
        String processId = "test1111123:1:3b27b404-967d-11ee-9483-c03c59ad2248";
        MyGlobalEventListener myGlobalEventListener = new MyGlobalEventListener();
        // 动态的绑定事件监听器
        runtimeService.addEventListener(myGlobalEventListener, FlowableEngineEventType.TASK_CREATED);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
        runtimeService.removeEventListener(myGlobalEventListener);
        //Thread.sleep(Integer.MAX_VALUE);

    }


    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("totalAmount", 120);
        taskService.complete("73edc747-95eb-11ee-ae46-c03c59ad2248");
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void test2(){
        /*Map<String, Object> variables = taskService.getVariables("1e0c1e18-8817-11ee-bbce-c03c59ad2248");
        System.out.println(variables);*/
        taskService.setVariable("84e80f54-8817-11ee-9aaa-c03c59ad2248","total",666);
    }


}
