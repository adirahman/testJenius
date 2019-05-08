package com.arc.jenius_api;

import java.util.HashMap;
import java.util.Map;

public class BaseRepo {
    public ResponseCourier courier;
    public Map<String,String> headerRequest = new HashMap<>();

    public BaseRepo(ResponseCourier courier){
        this.courier = courier;

        headerRequest.put("Content-type","application/json");
    }
}
