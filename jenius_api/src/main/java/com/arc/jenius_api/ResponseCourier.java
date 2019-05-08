package com.arc.jenius_api;

import com.arc.jenius_api.response.BaseResponse;

public interface ResponseCourier {
    void getDataResponse(BaseResponse response, String message);
}
