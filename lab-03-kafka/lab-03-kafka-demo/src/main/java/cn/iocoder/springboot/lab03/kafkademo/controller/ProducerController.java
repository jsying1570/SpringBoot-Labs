package cn.iocoder.springboot.lab03.kafkademo.controller;

import cn.iocoder.springboot.lab03.kafkademo.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ProducerController
 *
 * @author admin
 */
@Slf4j
@RequestMapping("/producer")
@RestController
public class ProducerController {
    @Autowired
    private Demo01Producer producer;

    /**
     * 同步发送消息
     *
     * @return ok
     */
    @GetMapping("/syncSend")
    public String syncSendMessage() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult<Object, Object> result = null;
        try {
            result = producer.syncSend(id);
        } catch (Exception e) {
            log.error("发送消息失败", e);
        }
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        return "ok";
    }

    /**
     * 异步发送消息
     *
     * @return ok
     */
    @GetMapping("/asyncSend")
    public String asyncSendMessage() {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(@NonNull Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }

        });
        return "ok";
    }
}
