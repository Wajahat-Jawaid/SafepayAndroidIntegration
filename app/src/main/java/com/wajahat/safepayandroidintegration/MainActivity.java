package com.wajahat.safepayandroidintegration;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wajahat.safepayandroidintegration.model.SafepayRequestModel;
import com.wajahat.safepayandroidintegration.model.SafepayResponseModel;
import com.wajahat.safepayandroidintegration.network.ServicesRepository;
import com.wajahat.safepayandroidintegration.utils.SafepayManager;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class MainActivity extends AppCompatActivity {

    private String orderId = null, paymentBeacon = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // It's obtained from your server. Safepay uses it to keep track of the order at the
        // time of request so as the client can extract this id from return url when payment
        // is succeeded
        orderId = "obtained_from_server";

        // Once you get the orderId, initialize Safepay to get paymentBeacon
        initSafepay();
    }

    private void initSafepay() {
        // Generate request model
        SafepayRequestModel request = SafepayManager.getSafepayOrderRequestModel(
                1000.0f, "USD", SafepayManager.Environment.SANDBOX
        );

        // Trigger v1/init API
        SafepayResponseModel response = ServicesRepository.orderInitService();
        if (response.getStatus().message.equals("status")) {
            assert response.getData() != null;
            paymentBeacon = response.getData().getToken(); // track_**********

            // Now using orderId & paymentBeacon, generate the Url to open in WebView
            generateUrlForWebView();
        }
    }

    private void generateUrlForWebView() {
        String url = SafepayManager.generatePaymentUrl(
                SafepayManager.Environment.SANDBOX,
                paymentBeacon,
                orderId
        );

        // launch WebView activity and pass generated Url to it
        launchWebViewActivity(url);
    }

    private void launchWebViewActivity(String url) {
        Intent intent = new Intent(MainActivity.this, PaymentWebViewActivity.class);
        intent.putExtra("paymentUrl", url);
        intent.putExtra("cancelUrl", SafepayManager.getCancelUrl());
        startActivity(intent);
    }
}