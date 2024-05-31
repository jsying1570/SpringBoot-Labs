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
 * 任务
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable02TaskTest {

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
                .addClasspathResource("process/02-高级篇/02-task/zhedie.bpmn20.xml")
                .name("折叠子流程")
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
        String processId = "myProcess:1:f420203e-9f50-11ee-8519-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        // 1.根据流程定义ID启动流程实例
        //map.put("inputArray", Arrays.asList(10,11,12,13,14));
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 委派转办
     */
    @Test
    void setDelegateTaskUser(){
        taskService.delegateTask("d0734a6f-85dc-11ee-9484-c03c59ad2248","lisi");
    }

    /**
     * 撤销委派
     */
    @Test
    void resovleTask(){
        taskService.resolveTask("d0734a6f-85dc-11ee-9484-c03c59ad2248");
    }

    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
       // map.put("skipFlag",true);
        taskService.complete("bd6424f8-9f54-11ee-ad29-c03c59ad2248",map);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 触发接收任务
     */
    @Test
    void triggerReceiveTask(){
        // 触发接收任务
        runtimeService.trigger("d272d56d-85e5-11ee-a39d-c03c59ad2248");
    }

    @Test
    void deleteProcessInstance(){
        runtimeService.deleteProcessInstance("bd6165d3-9f54-11ee-ad29-c03c59ad2248"
                ,"测试数据");
    }
}
