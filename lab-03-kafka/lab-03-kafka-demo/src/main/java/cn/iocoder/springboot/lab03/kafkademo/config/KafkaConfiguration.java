package cn.iocoder.springboot.lab03.kafkademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer.ControlFlag.REQUIRED;

@Configuration
public class KafkaConfiguration {

    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {
        // 创建 DeadLetterPublishingRecoverer 对象
        ConsumerRecordRecoverer recover = new DeadLetterPublishingRecoverer(template);
        // 创建 FixedBackOff 对象
        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
        // 创建 SeekToCurrentErrorHandler 对象
        return new SeekToCurrentErrorHandler(recover, backOff);
    }

    /**
     * 配置Kafka jaas，可以通过以下方式指定，也可以通过启动类Application中指定环境变量的方式
     * @return KafkaJaasLoginModuleInitializer
     * @throws IOException 异常
     */
    @Bean
    public KafkaJaasLoginModuleInitializer jaasConfig() throws IOException {
        KafkaJaasLoginModuleInitializer jaasConfig = new KafkaJaasLoginModuleInitializer();
        jaasConfig.setControlFlag(REQUIRED);
        Map<String, String> options = new HashMap<>();
        options.put("useKeyTab", "true");
        options.put("storeKey", "true");
        options.put("keyTab", "D:\\keytab\\starlink.keytab");
        options.put("principal", "starlink@HADOOP.CHINATELECOM.CN");
        jaasConfig.setOptions(options);
        return jaasConfig;
    }
//
//    @Bean
//    public SeekToCurrentErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
//        return new SeekToCurrentErrorHandler(
//                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
//    }

//    @Bean
//    @Primary
//    public BatchErrorHandler kafkaBatchErrorHandler() {
//        // 创建 SeekToCurrentBatchErrorHandler 对象
//        SeekToCurrentBatchErrorHandler batchErrorHandler = new SeekToCurrentBatchErrorHandler();
//        // 创建 FixedBackOff 对象
//        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);
//        batchErrorHandler.setBackOff(backOff);
//        // 返回
//        return batchErrorHandler;
//    }

}
