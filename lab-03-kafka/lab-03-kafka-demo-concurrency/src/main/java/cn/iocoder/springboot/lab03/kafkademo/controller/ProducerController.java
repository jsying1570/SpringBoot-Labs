package cn.iocoder.springboot.lab03.kafkademo.controller;

import cn.iocoder.springboot.lab03.kafkademo.producer.Demo06Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
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
    private Demo06Producer producer;
    /**
     * 异步发送消息
     *
     * @return ok
     */
    @GetMapping("/syncSend")
    public String asyncSendMessage() {
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
}
