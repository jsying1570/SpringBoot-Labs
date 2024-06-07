package com.example.controller;


import com.example.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/placeOrder")
    public String placeOrder(int accountId, int goodId, int num) {
        businessService.placeOrder(accountId, goodId, num);
        return "success";
    }
}
