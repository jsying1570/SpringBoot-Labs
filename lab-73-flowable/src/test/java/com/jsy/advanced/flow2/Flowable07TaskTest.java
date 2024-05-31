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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable07TaskTest {

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
                .addClasspathResource("process/02-高级篇/07-back/back-demo3.bpmn20.xml")
                .name("子流程回退案例")
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
        String processId = "back-demo3:1:2f284184-9fad-11ee-a1bc-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        // 1.根据流程定义ID启动流程实例
        //map.put("inputArray", Arrays.asList(10,11,12,13,14));
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
        //Thread.sleep(Integer.MAX_VALUE);
    }



    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
       // map.put("skipFlag",true);
        taskService.complete("c56ea55d-9faf-11ee-8a22-c03c59ad2248",map);
        //taskService.complete("6f5f5e56-9f5d-11ee-ad7f-c03c59ad2248",map);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 任务的回退
     */
    @Test
    void backFlow(){
        runtimeService.createChangeActivityStateBuilder()
                // 绑定到具体的流程实例
                .processInstanceId("d2562d0f-9f5a-11ee-b0c7-c03c59ad2248")
                // 从 A 跳转到 B
                .moveActivityIdTo("usertak1","usertask4")
                .changeState(); // 执行跳转的操作
    }

    @Test
    void backFlow2(){
        runtimeService.createChangeActivityStateBuilder()
                // 绑定到具体的流程实例
                .processInstanceId("62ec67c4-9fae-11ee-bfaa-c03c59ad2248")
                // 从 A 跳转到 B
                //.moveActivityIdTo("user2","user4")
                .moveActivityIdsToSingleActivityId(Arrays.asList("usertask4","usertask2"),"usertak1")
                .changeState(); // 执行跳转的操作
    }

    @Test
    void backFlow3(){
        runtimeService.createChangeActivityStateBuilder()
                // 绑定到具体的流程实例
                .processInstanceId("d2562d0f-9f5a-11ee-b0c7-c03c59ad2248")
                // 从 A 跳转到 B
                //.moveActivityIdTo("user2","user4")
                //.moveActivityIdsToSingleActivityId(Arrays.asList("usertask4","usertask2"),"usertak1")
                .moveSingleActivityIdToActivityIds("usertask5"
                        ,Arrays.asList("usertask2","usertask4"))
                .changeState(); // 执行跳转的操作
    }

    @Test
    void backFlow4(){
        runtimeService.createChangeActivityStateBuilder()
                // 绑定到具体的流程实例
                .processInstanceId("45d61295-9fad-11ee-8c24-c03c59ad2248")
                // 从 A 跳转到 B
                //.moveActivityIdTo("user2","user4")
                //.moveActivityIdsToSingleActivityId(Arrays.asList("usertask4","usertask2"),"usertak1")
                //.moveActivityIdTo("user3","user2")
                //.moveExecutionToActivityId("b2700c16-9fad-11ee-b5d2-c03c59ad2248","user1")
                //.moveActivityIdToParentActivityId("user2","user1")

                .changeState(); // 执行跳转的操作
    }

    /**
     * 撤销流程
     */
    @Test
    void deleteProcessInstances(){
        runtimeService.deleteProcessInstance("c56b49f8-9faf-11ee-8a22-c03c59ad2248"
                ,"测试数据");
    }
}
