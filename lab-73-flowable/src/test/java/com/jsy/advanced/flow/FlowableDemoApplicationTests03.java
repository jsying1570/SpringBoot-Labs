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
 * 候选人讲解
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
class FlowableDemoApplicationTests03 {

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
                .addClasspathResource("process/01-基础篇/HolidayDemo2.bpmn20.xml")
                .name("候选人案例")
                .deploy();// 部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    /**
     * 启动流程实例
     */
    @Test
    void startFlow(){
        // 在流程定义表中动态维护
        String processId = "HolidayDemo2:1:196236e6-7fe1-11ee-9a84-c03c59ad2248";
        // 在启动流程实例的时候我们就可以绑定对应的表达式的值
        Map<String,Object> variables = new HashMap<>();
        variables.put("candidate1","zhangsan");
        variables.put("candidate2","lisi");
        variables.put("candidate3","wangwu");
        // 1.根据流程定义ID启动流程实例 startProcessInstanceById 设置的变量是全局变量
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processId,variables);

    }

    /**
     *  根据候选人查询待办信息
     *  候选人还不是审批人
     *  候选人需要通过拾取的操作 把候选人变更为审批人
     *  多个候选人。只有一个可以变为审批人  拾取的操作
     *  审批人如果不想审批了。可以归还  从 审批人 --> 候选人
     */
    @Test
    void findFlow(){
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser("zhangsan") // 根据候选人查询
                .list();
        for (Task task : list) {
            System.out.println("task.getId() = " + task.getId());
        }
    }


    /**
     * 拾取操作
     */
    @Test
    void calimTask(){
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser("boge1") // 根据候选人查询
                .list();
        for (Task task : list) {
            // 拾取的动作。把zhangsan 由候选人变更为 审批人
            taskService.claim(task.getId(),"boge1");
        }
    }

    /**
     * 归还的操作
     */
    @Test
    void uncalimTask(){
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("boge1") // 根据候选人查询
                .list();
        for (Task task : list) {
            System.out.println("--归还操作--");
            // 归还操作  审批人--》候选人
            taskService.unclaim(task.getId());
        }
    }

    /**
     * 指派操作：
     *   指派另一个用户来审批操作
     */
    @Test
    void setAssignee(){
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("boge1") // 根据候选人查询
                .list();
        for (Task task : list) {
            System.out.println("--指派--");
            // 归还操作  审批人--》候选人
            taskService.setAssignee(task.getId(),"boge666");
        }
    }

    /**
     * 任务的审批
     */
    @Test
    void completeTask(){
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("zhangsan") // 根据候选人查询
                .list(); // 返回给前端。登录用户看到了这多条待办选择其中一条处理
        Map<String,Object> variables = new HashMap<>();
        variables.put("candidate4","boge1");
        variables.put("candidate5","boge2");
        for (Task task : list) {
            // 拾取的动作。把zhangsan 由候选人变更为 审批人
            taskService.complete("40d49019-7fe3-11ee-b697-c03c59ad2248");
        }

        taskService.complete("40d49019-7fe3-11ee-b697-c03c59ad2248");
    }

    @Test
    void completeTask1(){
        taskService.complete("40d49019-7fe3-11ee-b697-c03c59ad2248");
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
