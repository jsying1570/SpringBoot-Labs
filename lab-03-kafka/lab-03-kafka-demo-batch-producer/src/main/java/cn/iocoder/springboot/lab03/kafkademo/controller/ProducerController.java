package cn.iocoder.springboot.lab03.kafkademo.controller;

import cn.iocoder.springboot.lab03.kafkademo.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;


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
    private Demo02Producer producer;
    /**
     * 异步发送消息
     *
     * @return ok
     */
    @GetMapping("/asyncSend")
    public String asyncSendMessage() {
        IntStream.range(0,10).forEach(item ->{
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
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        return "ok";
    }
}
