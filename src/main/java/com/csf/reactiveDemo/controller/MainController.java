package com.csf.reactiveDemo.controller;

import com.csf.reactiveDemo.service.AgregatorService;
import com.csf.reactiveDemo.wrapper.Times;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final AgregatorService agregatorService;

    public MainController(AgregatorService agregatorService) {
        this.agregatorService = agregatorService;
    }

    @GetMapping(value = "/getDataBasicWay")
    public ResponseEntity<Times> getDataBasicWay(){
        return ResponseEntity.ok(agregatorService.getDataBasicWay());
    }

    @GetMapping(value = "/getDataComplFutureWay")
    public ResponseEntity<Times> getDataComplFutureWay(){
        return ResponseEntity.ok(agregatorService.getDataComplFutureWay());
    }

    @GetMapping(value = "/getDataReactWay")
    public ResponseEntity<Times> getDataReactWay(){
        return ResponseEntity.ok(agregatorService.getDataReactWay());
    }
}
