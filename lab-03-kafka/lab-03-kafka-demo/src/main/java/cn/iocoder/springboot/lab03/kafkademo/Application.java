package cn.iocoder.springboot.lab03.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        System.setProperty("java.security.auth.login.config", "D:\\keytab\\kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:\\keytab\\krb5.conf");
        SpringApplication.run(Application.class, args);
    }

}
