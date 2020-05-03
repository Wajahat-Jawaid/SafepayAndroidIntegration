package com.wajahat.safepayandroidintegration.model;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * <p>
 * This is the object returned by Safepay's v1/init API
 */
public class SafepayResponseModel {

    public static final String SUCCESS = "success";

    private Status status;
    private Data data;

    public Status getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        private String token;
        private String client;
        private int amount;
        private String currency;
        private String environment;

        public String getToken() {
            return token;
        }

        public String getClient() {
            return client;
        }

        public double getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public String getEnvironment() {
            return environment;
        }
    }

    public class Status {
        public String message;
    }
}