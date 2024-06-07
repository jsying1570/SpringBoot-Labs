package com.example.feign;

import com.example.domain.Good;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-xa-good")
public interface GoodFeignClient {

    @GetMapping("/good/{id}")
    Good findById(@PathVariable("id") int id);


    @GetMapping("/good/reduceStock")
    void reduceStock(@RequestParam("goodId") int goodId, @RequestParam("stockNum") int stockNum);

}
