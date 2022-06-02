package com.csf.reactiveDemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class FastService implements MicroService{
    @Override
    public String getData() {
        String s = "responseFromFastService";
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }
}
