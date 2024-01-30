package com.jsy.base;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

public class WorkflowDeploy02Test {

    /**
     * 部署流程涉及到三张表：
     *    流程部署表：ACT_RE_DEPLOYMENT 一次流程部署操作就会生成一张表结构
     *    流程定义表：ACT_RE_PROCDEF 一次部署操作中包含几个流程定义文件就会产生几条记录
     *    流程定义资源文件表：ACT_GET_BYTEARRAY  有多少资源就会生成几条记录
     */
    @Test
    public void testDeploy(){
        // 1.获取 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("MyHoliday.bar");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        // 3.完成流程的部署操作 ZIP 或者 Bar文件
        Deployment deploy = repositoryService.createDeployment()
                // .addClasspathResource("MyHoliday.bar")// 关联要部署的流程文件
                .addZipInputStream(zipInputStream)
                .name("XXX公司请求流程")
                .deploy() ;// 部署流程
        System.out.println("deploy.getId() = " + deploy.getId());
        System.out.println("deploy.getName() = " + deploy.getName());
    }

    /**
     * 流程的挂起和激活
     */
    @Test
    public void testSuspended(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        // 获取对应的流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId("myProcess:1:7")
                .singleResult();
        // 获取当前的流程定义的 状态信息
        boolean suspended = processDefinition.isSuspended();
        if(suspended){
            // 当前的流程被 挂起了
            // 激活流程
            System.out.println("激活流程：" + processDefinition.getId()+":"+processDefinition.getName());
            repositoryService.activateProcessDefinitionById("myProcess:1:7");
        }else{
            // 当前的流程是激活状态
            // 我们可以挂起当前的流程
            System.out.println("挂起流程：" + processDefinition.getId()+":"+processDefinition.getName());
            repositoryService.suspendProcessDefinitionById("myProcess:1:7");
        }
    }

    @Test
    public void testRunProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 我们需要通过RuntimeService来启动流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        Map<String,Object> variables = new HashMap<>();
        variables.put("employee","张三") ;// 谁申请请假
        variables.put("nrOfHolidays",3); // 请几天假
        variables.put("description","工作累了，想出去玩玩"); // 请假的原因
        // 启动流程实例
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceById("myProcess:1:7"
                ,"order100002"
                ,variables);
        System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
        System.out.println("holidayRequest.getActivityId() = " + holidayRequest.getActivityId());
        System.out.println("holidayRequest.getId() = " + holidayRequest.getId());
    }


    @Test
    public void testCompleteTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId("7501") // 根据流程实例编号 来查找对应的Task
                .taskAssignee("lisi")
                .singleResult();
        // 获取当前的流程实例绑定的流程变量
        Map<String, Object> processVariables = task.getProcessVariables();
        Set<String> keys = processVariables.keySet();
        for (String key : keys) {
            System.out.println(key + ":" + processVariables.get(key));
        }
        /*// 创建流程变量
       // Map<String,Object> map = new HashMap<>();
        processVariables.put("approved",true);
        processVariables.put("description","我要出去玩....");*/
        // 完成任务
        taskService.complete(task.getId());

    }
}
