package com.csf.reactiveDemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NormalService implements MicroService {
    @Override
    public String getData() {
        String s = "responseFromNormalService";
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }
}
