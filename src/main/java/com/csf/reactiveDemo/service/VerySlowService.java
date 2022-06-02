package com.csf.reactiveDemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerySlowService implements MicroService {
    @Override
    public String getData() {
        String s = "responseFromVerySlowService";
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }
}
