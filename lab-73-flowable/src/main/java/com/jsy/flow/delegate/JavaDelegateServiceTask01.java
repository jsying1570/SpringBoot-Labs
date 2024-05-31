package com.jsy.flow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * 需要把该对象注入到容器中
 */
@Component
public class JavaDelegateServiceTask01 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("JavaDelegateServiceTask01 --- 执行了");
    }
}
