package com.jsy.base;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程变量
 */
public class VariableTest {

    /**
     * 部署流程
     */
    @Test
    public void deploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("出差申请单.bpmn20.xml")
                .name("出差申请单")
                .deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
        System.out.println(deploy.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void runProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 在启动流程实例的时候创建了流程变量
        Map<String,Object> variables = new HashMap<>();
        variables.put("assignee0","张三");
        variables.put("assignee1","李四");
        variables.put("assignee2","王五");
        variables.put("assignee3","赵会计");
        runtimeService.startProcessInstanceById("evection:1:4",variables);
    }

    /**
     * 完成任务，同时指定流程变量
     */
    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("2501")
                .taskAssignee("张三")
                .singleResult();
        // 获取当前流程实例的所有的变量
        Map<String, Object> processVariables = task.getProcessVariables();
        processVariables.put("num",2);
        taskService.complete(task.getId(),processVariables);
    }

    @Test
    public void completeTask1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("2501")
                .taskAssignee("李四")
                .singleResult();
        taskService.complete(task.getId());
    }

    /**
     * 在根据Task编号来更新流程变量
     */
    @Test
    public void updateVariableLocal(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("2501")
                .taskAssignee("李四")
                .singleResult();
        Map<String, Object> processVariables = task.getProcessVariables();
        processVariables.put("num",6);
        // 设置了本地变量 局部变量
        taskService.setVariablesLocal(task.getId(),processVariables);
    }

    /**
     * 在根据Task编号来更新流程变量
     */
    @Test
    public void updateVariable(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("2501")
                .taskAssignee("李四")
                .singleResult();
        // 在局部变量和全局变量都有的情况下，这儿取出来的是局部变量
        Map<String, Object> processVariables = task.getProcessVariables();
        processVariables.put("num",1);
        // 设置了全局变量
        taskService.setVariables(task.getId(),processVariables);
    }

}
