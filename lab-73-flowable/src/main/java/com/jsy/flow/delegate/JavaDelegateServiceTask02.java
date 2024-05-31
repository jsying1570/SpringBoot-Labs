package com.jsy.flow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * 需要把该对象注入到容器中
 */
@Component
public class JavaDelegateServiceTask02  {

    public void fun1(DelegateExecution execution,Integer age){
        System.out.println("JavaDelegateServiceTask02----- 执行了" + age);
    }


    public Integer getScoreTotal(DelegateExecution execution){
        System.out.println("---------getScoreTotal-------");
        return 123456;
    }
}
