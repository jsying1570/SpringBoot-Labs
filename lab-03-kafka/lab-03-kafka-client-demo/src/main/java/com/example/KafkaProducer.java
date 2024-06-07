package com.example;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducer {

     private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static void main(String[] args) {
        String bootstrapServers = "node1:9092,node2:9092,node3:9092";
        String securityProtocol = "SASL_PLAINTEXT";
        String topicName = "demo01";

        System.setProperty("java.security.auth.login.config", "D:\\keytab\\kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:\\keytab\\krb5.conf");
        Properties props = new Properties();
        props.put("sasl.kerberos.service.name", "kafka");
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //指定了生产者的唯一标识符
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        // 确认级别，"all"表示所有副本都确认了才算发送成功
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        // 发送重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 1);

        // 创建生产者实例
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

        try {
            // 循环并向Kafka主题发送消息
            for (int i = 0; i < 10; i++) {
                String message = "Message " + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
                producer.send(record);
            }
        } catch (Exception e) {
            logger.error("连接失败",e);
        } finally {
            // 关闭生产者
            producer.close();
        }
    }
}
