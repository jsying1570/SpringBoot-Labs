package com.jsy;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author jsyin
 */
public class SendRejectionMail implements JavaDelegate {
    /**
     * 这个一个Flowable中的触发器
     * @param execution  事件触发执行任务
     */
    @Override
    public void execute(DelegateExecution execution) {
        // 触发执行的逻辑  按照我们在流程中的定义应该给被拒绝的员工发送通知的邮件
        System.out.println("不好意思，你的请假申请被拒绝了....安心工作");
    }
}
