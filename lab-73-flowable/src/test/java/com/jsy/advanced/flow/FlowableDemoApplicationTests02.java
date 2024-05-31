package com.jsy.advanced.flow;

import com.jsy.flow.FlowableDemoApplication;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程变量
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
class FlowableDemoApplicationTests02 {

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
    void deployFlow() {
        //RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01-基础篇/FirstFlow.bpmn20.xml")
                .name("流程变量案例")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    /**
     * 启动流程实例
     */
    @Test
    void startFlow(){
        // 在流程定义表中动态维护
        String processId = "FirstFlow:1:93232d0d-7e55-11ee-beb0-c03c59ad2248";
        // 在启动流程实例的时候我们就可以绑定对应的表达式的值
        Map<String,Object> variables = new HashMap<>();
        variables.put("var1","test1");
        variables.put("var2","test2");
        variables.put("var3","test3");
        // 1.根据流程定义ID启动流程实例 startProcessInstanceById 设置的变量是全局变量
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processId,variables);

    }

    /**
     * 在流程启动后我们还可以绑定对应的流程变量
     */
    @Test
    void setVariables(){
        String executionId = "cd023d6c-7e55-11ee-ba88-c03c59ad2248";
        runtimeService.setVariable(executionId,"var4","test4");
        // RuntimeService设置局部变量
        runtimeService.setVariableLocal(executionId,"varLocal1","localTest1");
        String taskId = "cd063514-7e55-11ee-ba88-c03c59ad2248";
        taskService.setVariable(taskId,"taskVar1","taskVar1");
        // taskService.setVariableLocal 是和TaskId绑定是一个局部变量
        taskService.setVariableLocal(taskId,"taskLocalVar1","taskLocalVar1");
    }

    /**
     * 获取定义的变量
     */
    @Test
    void getVariables(){
        String executionId = "cd023d6c-7e55-11ee-ba88-c03c59ad2248";
        Map<String, Object> variables = runtimeService.getVariables(executionId);
        System.out.println(variables);
        System.out.println("--------------");
        String taskId = "caecdfe0-7e57-11ee-aa66-c03c59ad2248";
        variables = taskService.getVariables(taskId);
        System.out.println(variables);
    }

    /**
     * 根据用户查询待办信息
     */
    @Test
    void findFlow(){
        // 任务实例操作我们都是通过 TaskService 来实现的
       // TaskService taskService = processEngine.getTaskService();
        // 获取到 act_ru_task 中 assignee 字段是 zhangsan 的记录
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("lisi") // 指定查询的条件
                .list();
        for (Task task : list) {
            System.out.println(task.getId());
        }
    }

    /**
     * 任务的审批
     */
    @Test
    void completeTask(){
        Map<String,Object> variables = new HashMap<>();
        variables.put("taskVar1","taskVar1");
        // 完成任务的审批。根据任务的ID , 绑定对应的表达式的值
        //taskService.complete("cd59117c-7c48-11ee-9e81-c03c59ad2248",variables);
        taskService.complete("caecdfe0-7e57-11ee-aa66-c03c59ad2248",variables);
    }

    /**
     * 流程的挂起和激活
     */
    @Test
    void suspendedActivity(){
        String processDefinitionId = "FirstFlow:1:e10cc8fd-7b9c-11ee-8fae-c03c59ad2248";
        // 做流程的挂起和激活操作 --> 针对的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
        // 获取当前的流程定义的状态
        boolean suspended = processDefinition.isSuspended();// 获取是否挂起
        if(suspended){
            // 表示挂起 --》 需要激活流程
            System.out.println("激活流程");
            repositoryService.activateProcessDefinitionById(processDefinitionId);
        }else{
            // 表示激活 --》 挂起流程
            System.out.println("挂起流程");
            repositoryService.suspendProcessDefinitionById(processDefinitionId);
        }
    }

    /**
     * 挂起流程实例
     */
    @Test
    void suspendInstance(){
        // 挂起流程实例
        runtimeService.suspendProcessInstanceById("a7ae5680-7ba3-11ee-809a-c03c59ad2248");
        // 激活流程实例
        //runtimeService.activateProcessInstanceById("a7ae5680-7ba3-11ee-809a-c03c59ad2248");
    }

}
