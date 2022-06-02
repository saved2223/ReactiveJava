package com.csf.reactiveDemo.wrapper;

import lombok.Getter;
import lombok.Setter;

public class Times {
    @Getter
    @Setter
    private long fastService;

    @Getter
    @Setter
    private long normalService;

    @Getter
    @Setter
    private long verySlowService;

    @Getter
    @Setter
    private long totalTime;

    public void setTotalTimeAuto(){
        totalTime = fastService + normalService + verySlowService;
    }

}
