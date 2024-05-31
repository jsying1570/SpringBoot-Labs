package com.jsy.advanced.flow;

import com.jsy.flow.FlowableDemoApplication;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 候选人组案例讲解
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
class FlowableDemoApplicationTests04 {

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
     * 维护用户
     */
    @Test
    void createUser(){
        User user = identityService.newUser("lisi");
        user.setEmail("lisi@qq.com");
        user.setFirstName("li");
        user.setLastName("si");
        user.setPassword("123");
        identityService.saveUser(user);
    }

    /**
     * 用户组的维护
     */
    @Test
    void createGroup(){
        Group group = identityService.newGroup("xzb");
        group.setName("行政部");
        group.setType("type1");
        identityService.saveGroup(group);
    }

    /**
     * 维护用户和用户组的关联关系
     */
    @Test
    void createMemeberShip(){
        // 查询到对应的用户组
        Group group = identityService.createGroupQuery().groupId("xsb").singleResult();
        List<User> users = identityService.createUserQuery().list();
        for (User user : users) {
            // 用户和组的一个绑定
            identityService.createMembership(user.getId(),group.getId());
        }

    }

    /**
     * 流程部署操作
     */
    @Test
    void deployFlow() {
        //RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01-基础篇/HolidayDemo3.bpmn20.xml")
                .name("候选人组案例")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    /**
     * 删除部署的流程
     */
    @Test
    void deleteDeployFlow(){
        repositoryService.deleteDeployment("69146afd-7fe8-11ee-bca4-c03c59ad2248",true);
    }

    /**
     * 启动流程实例
     */
    @Test
    void startFlow(){
        // 在流程定义表中动态维护
        String processId = "HolidayDemo3:1:adb38e97-7fe8-11ee-8aa8-c03c59ad2248";
        // 1.根据流程定义ID启动流程实例 startProcessInstanceById 设置的变量是全局变量
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processId);
        

    }

    /**
     * 候选任务的查询
     */
    @Test
    void findCandidateTask(){
        // 根据当前登录的用户来查询
        Group group = identityService.createGroupQuery()
                .groupMember("zhangsan") // 根据当前登录的用户查询对应的group
                .singleResult();
        System.out.println("group.getId() = " + group.getId());
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateGroup(group.getId()) // 根据 候选人组来查询 待办的信息
                .list();
        for (Task task : list) {
            System.out.println("task.getId() = " + task.getId());
        }
    }

    /**
     * 候选任务的查询
     */
    @Test
    void cliamTask(){
        // 根据当前登录的用户来查询
        Group group = identityService.createGroupQuery()
                .groupMember("zhangsan") // 根据当前登录的用户查询对应的group
                .singleResult();
        System.out.println("group.getId() = " + group.getId());
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateGroup(group.getId()) // 根据 候选人组来查询 待办的信息
                .list();
        for (Task task : list) {
            taskService.claim(task.getId(),"zhangsan");
        }
    }

    /**
     * 候选任务的查询
     */
    @Test
    void completeTask(){
        taskService.complete("084bbc8b-7fea-11ee-9745-c03c59ad2248");
    }

}
