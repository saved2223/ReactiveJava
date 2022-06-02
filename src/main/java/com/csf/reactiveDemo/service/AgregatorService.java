package com.csf.reactiveDemo.service;

import com.csf.reactiveDemo.wrapper.Times;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgregatorService {
    private final FastService fastService;
    private final NormalService normalService;
    private final VerySlowService verySlowService;

    public AgregatorService(FastService fastService, NormalService normalService, VerySlowService verySlowService) {
        this.fastService = fastService;
        this.normalService = normalService;
        this.verySlowService = verySlowService;
    }

    public Times getDataBasicWay() {
        Times times = new Times();
        long start = System.currentTimeMillis();
        fastService.getData();
        times.setFastService(System.currentTimeMillis() - start);
        normalService.getData();
        times.setNormalService(System.currentTimeMillis() - times.getFastService() - start);
        verySlowService.getData();
        times.setVerySlowService(System.currentTimeMillis() - times.getNormalService() - start);
        times.setTotalTimeAuto();
        return times;
    }

    public Times getDataComplFutureWay() {
        Times times = new Times();
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> fast =  CompletableFuture.supplyAsync(
                () -> {
                    long start = System.currentTimeMillis();
                    fastService.getData();
                    return System.currentTimeMillis() - start;
                }
        ).thenAccept(times::setFastService);
        CompletableFuture<Void> normal =  CompletableFuture.supplyAsync(
                () -> {
                    long start = System.currentTimeMillis();
                    normalService.getData();
                    return System.currentTimeMillis() - start;
                }
        ).thenAccept(times::setNormalService);
        CompletableFuture<Void> slow =  CompletableFuture.supplyAsync(
                () -> {
                    long start = System.currentTimeMillis();
                    verySlowService.getData();
                    return System.currentTimeMillis() - start;
                }
        ).thenAccept(times::setVerySlowService);
        try {
            fast.get();
            normal.get(); //вот lock находится прям тут в .get()
            slow.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        times.setTotalTime(System.currentTimeMillis() - startTime);

        return times;
    }

    public Times getDataReactWay() {
        Times times = new Times();
        return times;
    }
}
