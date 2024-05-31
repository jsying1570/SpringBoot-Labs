package com.jsy.advanced.flow2;

import com.jsy.flow.FlowableDemoApplication;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 会签-多实例
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable05MutiInstanceTest {

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
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/02-高级篇/05-会签/multiInstance-demo2.bpmn20.xml")
                .name("会签-案例6")
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
        String processId = "multiInstance-demo2:1:cc5366a8-a0b0-11ee-a40f-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        map.put("users", Arrays.asList("张三1","李四1","王五"));
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId,"1001231",map);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void getBusinessKey(){
        // 更新业务主键
        // runtimeService.updateBusinessKey();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("ffe242db-9720-11ee-80a7-c03c59ad2248")
                .singleResult();
        String businessKey = processInstance.getBusinessKey();
        System.out.println("businessKey = " + businessKey);
    }

    @Test
    void startFormFlow() throws InterruptedException{
        // 在流程定义表中动态维护
        String processId = "multiInstance-demo6:1:0c426992-970a-11ee-821c-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        map.put("users", Arrays.asList("张三1","李四1","王五"));
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId,map);
        //Thread.sleep(Integer.MAX_VALUE);
    }



    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        taskService.complete("7adc508b-970a-11ee-bf93-c03c59ad2248");
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void test2(){
        /*Map<String, Object> variables = taskService.getVariables("1e0c1e18-8817-11ee-bbce-c03c59ad2248");
        System.out.println(variables);*/
        taskService.setVariable("84e80f54-8817-11ee-9aaa-c03c59ad2248","total",666);
    }


}
