package com.jsy.advanced.flow;

import com.jsy.flow.FlowableDemoApplication;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 网关讲解
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
class FlowableDemoApplicationTests05 {

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    IdentityService identityService;


    /**
     * 流程部署操作
     */
    @Test
    void deployFlow() {
        //RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01-基础篇/HolidayDemo6.bpmn20.xml")
                .name("包容网关的案例")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    /**
     * 删除部署的流程
     */
    @Test
    void deleteDeployFlow(){
        repositoryService.deleteDeployment("6e0fb479-7ff2-11ee-8110-c03c59ad2248",true);
    }

    /**
     * 启动流程实例
     */
    @Test
    void startFlow(){
        // 在流程定义表中动态维护
        String processId = "HolidayDemo6:1:b423a806-7ff3-11ee-a252-c03c59ad2248";
        // 1.根据流程定义ID启动流程实例 startProcessInstanceById 设置的变量是全局变量
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processId);

    }


    /**
     * 任务审批
     */
    @Test
    void completeTask(){
        String taskId = "e195b87b-7ff3-11ee-80a3-c03c59ad2248";
        // 得绑定 请假的天数
        Map<String,Object> map = new HashMap<>();
        map.put("num",2);
        taskService.complete(taskId);
    }

}
