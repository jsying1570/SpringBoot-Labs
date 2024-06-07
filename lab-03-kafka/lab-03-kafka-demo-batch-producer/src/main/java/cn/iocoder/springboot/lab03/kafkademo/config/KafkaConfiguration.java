package cn.iocoder.springboot.lab03.kafkademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer.ControlFlag.REQUIRED;

@Configuration
public class KafkaConfiguration {

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
}
