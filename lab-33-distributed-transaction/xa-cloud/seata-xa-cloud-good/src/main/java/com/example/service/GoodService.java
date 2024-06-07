package com.example.service;

import com.example.domain.Good;

public interface GoodService {
    Good findGoodById(int id);

    void reduceStock(int goodId, int stockNum);
}
