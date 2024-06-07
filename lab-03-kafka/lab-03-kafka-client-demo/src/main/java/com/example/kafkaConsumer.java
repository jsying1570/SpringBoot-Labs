package com.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class kafkaConsumer {
    public static void main(String[] args) throws InterruptedException {
        String topic = "demo01";
        System.setProperty("java.security.auth.login.config", "D:\\keytab\\kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:\\keytab\\krb5.conf");
        Properties props = new Properties();
        props.setProperty("group.id", "tests");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("bootstrap.servers", "node1:9092,node2:9092,node3:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.kerberos.service.name", "kafka");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);

        kafkaConsumer.subscribe(Arrays.asList(topic));

        ConsumerRecords<String, String> msgList = kafkaConsumer.poll(1000);

        for (; ; ) {
            msgList = kafkaConsumer.poll(1000);
            if (null != msgList && msgList.count() > 0) {
                for (ConsumerRecord<String, String> record : msgList) {
                    //消费100条就打印 ,但打印的数据不一定是这个规律的
                    System.out.println("=======receive: key = " + record.key() + ", value = " + record.value() + " offset===" + record.offset());
                    //当消费了1000条就退出
                }
            } else {
                Thread.sleep(1000);
            }
        }
    }
}
