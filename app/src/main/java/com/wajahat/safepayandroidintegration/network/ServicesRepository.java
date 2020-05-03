package com.wajahat.safepayandroidintegration.network;

import com.wajahat.safepayandroidintegration.model.SafepayResponseModel;

public class ServicesRepository {

    /* Handle network request here with Url as BASE_URL + "/order/v1/init" */
    public static SafepayResponseModel orderInitService() {
        return new SafepayResponseModel();
    }
}