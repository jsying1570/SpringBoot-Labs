package com.wangwenjun.servant;

import com.wangwenjun.service.Service;

import java.util.ServiceLoader;


public class ServiceInvoker {
    public static void main(String[] args) {
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
        for (Service service : serviceLoader) {
            service.show();
        }
    }
}
