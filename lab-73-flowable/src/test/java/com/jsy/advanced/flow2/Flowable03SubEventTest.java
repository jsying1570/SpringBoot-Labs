package com.jsy.advanced.flow2;

import com.jsy.flow.FlowableDemoApplication;
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
 * 子流程
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable03SubEventTest {

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
                .addClasspathResource("process/02-高级篇/03-sub-event/sub-event-example08.bpmn20.xml")
                .name("子流程-泳池泳道")
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
        String processId = "process:1:9e8e7526-8819-11ee-94c4-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        // 1.根据流程定义ID启动流程实例
        map.put("num", 6);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId,map);
        //Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("totalAmount", 120);
        taskService.complete("e766d4e2-8819-11ee-9484-c03c59ad2248");
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void test2(){
        /*Map<String, Object> variables = taskService.getVariables("1e0c1e18-8817-11ee-bbce-c03c59ad2248");
        System.out.println(variables);*/
        taskService.setVariable("84e80f54-8817-11ee-9aaa-c03c59ad2248","total",666);
    }


}
