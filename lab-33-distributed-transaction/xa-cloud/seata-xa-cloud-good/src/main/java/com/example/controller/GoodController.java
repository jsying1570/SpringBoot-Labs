package com.example.controller;

import com.example.domain.Good;
import com.example.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/{id}")
    public Good findById(@PathVariable("id") int id) {
        Good goodById = goodService.findGoodById(id);
        return goodById;
    }


    @GetMapping("/reduceStock")
    public void reduceStock(int goodId, int stockNum) {
        goodService.reduceStock(goodId, stockNum);
    }
}
