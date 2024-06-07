package com.example.utils;

public class Urls {
    //创建订单
    public static final String CEATE_ORDER = "http://seata-xa-order/order/addOrder";
    //获取商品信息
    public static final String GOOD_INFO = "http://seata-xa-good/good/%d";
    //减库存
    public static final String REDUCE_STOCK = "http://seata-xa-good/good/reduceStock?goodId=%d&stockNum=%d";

    //扣钱
    public static final String REDUCE_MONEY = "http://seata-xa-account/account/reduceMoney?accountId=%d&money=%f";

}