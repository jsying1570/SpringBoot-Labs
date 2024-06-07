package cn.iocoder.springboot.lab03.kafkademo.consumer;

import cn.iocoder.springboot.lab03.kafkademo.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo01AConsumer {

    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "group01-" + Demo01Message.TOPIC)
    public void onMessage(ConsumerRecord<Integer, String> record) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

//    @KafkaListener(topics = Demo01Message.TOPIC,
//            groupId = "demo01-B-consumer-group-" + Demo01Message.TOPIC)
//    public void onMessage(ConsumerRecord<Integer, String> record) throws InterruptedException {
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record.partition());
//        Thread.sleep(10 * 1000L);
//        Thread.sleep(1L);
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record.partition());
//    }

}
