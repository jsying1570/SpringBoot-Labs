package com.jsy.base;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;

public class CompleteTaskTest {
    @Test
    public void testDeploy(){
        // 1.获取 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3.完成流程的部署操作 ZIP 或者 Bar文件
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("MyHoliday22.bpmn20.xml")// 关联要部署的流程文件
                .name("XXX公司请求流程")
                .deploy() ;// 部署流程
        System.out.println("deploy.getId() = " + deploy.getId());
        System.out.println("deploy.getName() = " + deploy.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testRunProcess(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceById("holiday-new:2:35004");
        System.out.println("processInstance = " + processInstance);

    }

    @Test
    public void testCompleteTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("37501") // 根据流程实例编号 来查找对应的Task
                .taskAssignee("小明")
                .singleResult();

        // 完成任务
        taskService.complete(task.getId());

    }
}
