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
 * 事件篇
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable01EventTest {

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
                .addClasspathResource("process/02-高级篇/01-event/event-other3.bpmn20.xml")
                .name("其他事件-补偿事件")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());

    } //

    /**
     * 启动流程实例
     */
    @Test
    void startFlow() throws InterruptedException{
        // 在流程定义表中动态维护
        String processId = "myProcess:1:0a59003a-8432-11ee-91ff-c03c59ad2248";
        // 1.根据流程定义ID启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("cancelFlag",true);
        taskService.complete("13d88f3e-8430-11ee-aa9e-c03c59ad2248",map);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 通过发送msg01 这个消息来触发 流程实例
     */
    @Test
    void sendMessage() throws InterruptedException{
        // 根据消息来启动流程实例
        runtimeService.startProcessInstanceByMessage("msg01");
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 消息中间事件
     * @throws InterruptedException
     */
    @Test
    void sendIntermediateMessage() throws InterruptedException{
        String executionId = "51a3b909-8315-11ee-b680-c03c59ad2248";
        // 根据流程实例id找到对应的执行实例id
        /*Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId("45ffdd17-8312-11ee-8452-c03c59ad2248")
                .onlyChildExecutions()
                .singleResult();
        System.out.println("execution.getId() = " + execution.getId());*/
        // 根据消息来启动流程实例
        runtimeService.messageEventReceived("msg04",executionId);
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 通过信号启动流程实例
     */
    @Test
    void sendSignal() throws InterruptedException {
        runtimeService.signalEventReceived("signal3");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
