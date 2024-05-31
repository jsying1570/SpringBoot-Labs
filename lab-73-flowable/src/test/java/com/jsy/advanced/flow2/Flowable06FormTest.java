package com.jsy.advanced.flow2;

import com.jsy.flow.FlowableDemoApplication;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormDeployment;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.model.FormField;
import org.flowable.form.model.SimpleFormModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态表单
 */
@SpringBootTest(classes = FlowableDemoApplication.class)
public class Flowable06FormTest {

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormRepositoryService formRepositoryService;


    @Autowired
    FormService formService;

    /**
     * 部署表单操作
     */
    @Test
    void deployFormFlow() throws InterruptedException {

        FormDeployment formDeployment = formRepositoryService.createDeployment()
                .addClasspathResource("process/02-高级篇/06-form/first.form")
                .name("报销表单")
                .deploy();
        System.out.println("formDeployment.getId() = " + formDeployment.getId());
    }

    @Test
    void deployFormFlow2() throws InterruptedException {
        String json = "{\"name\":\"报销的表单3\",\"key\":\"formbx3\",\"version\":0,\"fields\":[{\"fieldType\":\"FormField\",\"id\":\"amount\",\"name\":\"报销金额\",\"type\":\"text\",\"value\":null,\"required\":false,\"readOnly\":false,\"overrideId\":true,\"placeholder\":\"0\",\"layout\":null},{\"fieldType\":\"FormField\",\"id\":\"reason\",\"name\":\"原因\",\"type\":\"multi-line-text\",\"value\":null,\"required\":false,\"readOnly\":false,\"overrideId\":true,\"placeholder\":null,\"layout\":null},{\"fieldType\":\"FormField\",\"id\":\"bxDate\",\"name\":\"报销日期\",\"type\":\"date\",\"value\":null,\"required\":false,\"readOnly\":false,\"overrideId\":true,\"placeholder\":null,\"layout\":null}],\"outcomes\":[{\"id\":null,\"name\":\"Accept\"},{\"id\":null,\"name\":\"Reject\"}]}";
        FormDeployment formDeployment = formRepositoryService.createDeployment()
                .addString("bx-form2.form",json) // 注意部署的文件名称的后缀必须是 .form
                .name("报销表单2")
                .deploy();
        System.out.println("formDeployment.getId() = " + formDeployment.getId());
    }


    /**
     * 流程部署操作
     */
    @Test
    void deployFlow() throws InterruptedException {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/02-高级篇/06-form/form-demo3.bpmn20.xml")
                .name("动态表单3")
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
        String processId = "form-demo2:1:4dec2945-9729-11ee-9545-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        map.put("amount",999);
        map.put("reason","维护客户关系123");
        map.put("bxDate","2022-05-06");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId,map);
        //Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void startFlowWithForm(){
        String processId = "form-demo3:3:8b1012ba-972e-11ee-89f3-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        map.put("amount",6666);
        map.put("reason","维护客户关系1");
        map.put("bxDate","2022-06-06");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceWithForm(processId, "Accept", map, "报销流程2");
        System.out.println("processInstance.getId() = " + processInstance.getId());
    }

    /**
     * 获取流程绑定的表单数据
     */
    @Test
    public void getTaskFormInfo(){
        String proDefId = "form-demo2:1:4dec2945-9729-11ee-9545-c03c59ad2248";
        String proInsId = "7d33bd92-9729-11ee-825d-c03c59ad2248";
        FormInfo formInfo = runtimeService.getStartFormModel(proDefId, proInsId);
        System.out.println("formInfo.getKey() = " + formInfo.getKey());
        System.out.println("formInfo.getName() = " + formInfo.getName());
        System.out.println("formInfo.getDescription() = " + formInfo.getDescription());
        // 获取表单对应的数据
        SimpleFormModel formModel = (SimpleFormModel) formInfo.getFormModel();
        List<FormField> fields = formModel.getFields();
        for (FormField field : fields) {
            System.out.println("field.getId() = " + field.getId());
            System.out.println("field.getName() = " + field.getName());
            System.out.println("field.getValue() = " + field.getValue());
        }
    }

    /**
     * 获取绑定在单独节点上的表单数据
     */
    @Test
    public void getTaskFormNodeInfo(){
        FormInfo formInfo = taskService.getTaskFormModel("1d4d4d8f-972a-11ee-ad49-c03c59ad2248");
        System.out.println("formInfo.getKey() = " + formInfo.getKey());
        System.out.println("formInfo.getName() = " + formInfo.getName());
        System.out.println("formInfo.getDescription() = " + formInfo.getDescription());
        // 获取表单对应的数据
        SimpleFormModel formModel = (SimpleFormModel) formInfo.getFormModel();
        List<FormField> fields = formModel.getFields();
        for (FormField field : fields) {
            System.out.println("field.getId() = " + field.getId());
            System.out.println("field.getName() = " + field.getName());
            System.out.println("field.getValue() = " + field.getValue());
        }
    }

    @Test
    void getBusinessKey(){
        // 更新业务主键
        // runtimeService.updateBusinessKey();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("ffe242db-9720-11ee-80a7-c03c59ad2248")
                .singleResult();
        String businessKey = processInstance.getBusinessKey();
        System.out.println("businessKey = " + businessKey);
    }

    @Test
    void startFormFlow() throws InterruptedException{
        // 在流程定义表中动态维护
        String processId = "multiInstance-demo6:1:0c426992-970a-11ee-821c-c03c59ad2248";
        Map<String,Object> map = new HashMap<>();
        map.put("users", Arrays.asList("张三1","李四1","王五"));
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId,map);
        //Thread.sleep(Integer.MAX_VALUE);
    }



    /**
     * 任务的审批
     */
    @Test
    void completeTask() throws InterruptedException {
        //taskService.complete("7d37b53a-9729-11ee-825d-c03c59ad2248");
        Map<String,Object> map = new HashMap<>();
        //Thread.sleep(Integer.MAX_VALUE); form_form-bx2_outcome
        taskService.completeTaskWithForm("a8e34381-972e-11ee-a237-c03c59ad2248"
        ,"65617756-972e-11ee-b5ed-c03c59ad2248","Reject",map);
    }

    @Test
    void test2(){
        /*Map<String, Object> variables = taskService.getVariables("1e0c1e18-8817-11ee-bbce-c03c59ad2248");
        System.out.println(variables);*/
        taskService.setVariable("84e80f54-8817-11ee-9aaa-c03c59ad2248","total",666);
    }


}
