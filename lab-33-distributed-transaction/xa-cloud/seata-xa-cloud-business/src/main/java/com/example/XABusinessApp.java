package com.example;


import com.example.config.XIDInterceptor;
import io.seata.spring.boot.autoconfigure.SeataTCCFenceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication(exclude = SeataTCCFenceAutoConfiguration.class)
@EnableFeignClients
public class XABusinessApp {
    public static void main(String[] args) {
        SpringApplication.run(XABusinessApp.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new XIDInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
