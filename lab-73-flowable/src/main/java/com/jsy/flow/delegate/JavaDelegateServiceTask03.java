package com.jsy.flow.delegate;

import lombok.Setter;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * 需要把该对象注入到容器中
 */
@Component
public class JavaDelegateServiceTask03 implements JavaDelegate {

    @Setter
    private Expression name;
    @Setter
    private Expression age;
    @Setter
    private Expression desc;

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("JavaDelegateServiceTask02----- 执行了" + age);
        System.out.println("name.getExpressionText() = " + name.getExpressionText());
        System.out.println("age.getExpressionText() = " + age.getExpressionText());
        System.out.println("desc.getExpressionText() = " + desc.getExpressionText());
        System.out.println("name.getValue(execution) = " + name.getValue(execution));
        System.out.println("age.getValue(execution) = " + age.getValue(execution));
        System.out.println("desc.getValue(execution) = " + desc.getValue(execution));
    }
}
