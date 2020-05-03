package com.wajahat.safepayandroidintegration.model;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * <p>
 * Construct this class's object to pass it to the Safepay order init request
 */
public class SafepayRequestModel {

    private String client;
    private double amount;
    private String currency;
    private String environment;

    public SafepayRequestModel(String client, double amount, String currency, String environment) {
        this.client = client;
        this.amount = amount;
        this.currency = currency;
        this.environment = environment;
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