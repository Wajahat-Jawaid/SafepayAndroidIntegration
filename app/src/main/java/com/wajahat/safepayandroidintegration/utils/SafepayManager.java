package com.wajahat.safepayandroidintegration.utils;

import com.wajahat.safepayandroidintegration.model.SafepayRequestModel;
import com.wajahat.safepayandroidintegration.network.ServicesRepository;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class SafepayManager {

    private static final String SANDBOX_CHECKOUT_URL = "https://sandbox.api.getsafepay.com/components?";
    private static final String PRODUCTION_CHECKOUT_URL = "https://www.getsafepay.com/components";
    private static final String CLIENT_SECRET = "sec_******";

    /** Payment Environment */
    public enum Environment {
        SANDBOX("sandbox"),
        PRODUCTION("production");

        String envName;

        Environment(String envName) {
            this.envName = envName;
        }

        public static String getEnvName(Environment env) {
            return env.envName;
        }
    }

    /**
     * Generate request model to send it to the Safepay order init API
     *
     * @param amount Gross Price value to charge from the customer
     * @param env    Indicating whether the current environment is sandbox or production
     * @return Request object to be passed to the API
     */
    public static SafepayRequestModel getSafepayOrderRequestModel(
            double amount, String currency, Environment env) {
        return new SafepayRequestModel(
                CLIENT_SECRET,
                amount,
                currency,
                Environment.getEnvName(env)
        );
    }

    /**
     * If Safepay request is not succeeded for any reason. That may be whether request
     * was failed, user cancelled the request, request timeout, then navigate user to this url
     *
     * @return cancel url to redirect the customer to
     */
    public static String getCancelUrl() {
        return "https://example.com/cancelUrl";
    }

    /**
     * Generates the final URL to open in WebView.
     *
     * @param env           Indicating whether the current environment is sandbox or production
     * @param paymentBeacon A unique string obtained as a response of
     *                      {@link ServicesRepository.orderInitService()}
     * @param orderId       Provided by your own server to keep track of the order while redirection
     *                      is happening
     * @return generated Url
     */
    public static String generatePaymentUrl(Environment env, String paymentBeacon, String orderId) {
        return (env == Environment.SANDBOX ? SANDBOX_CHECKOUT_URL : PRODUCTION_CHECKOUT_URL) +
                "env=sandbox&" +
                "beacon=" + paymentBeacon + "&" +
                "source=magento&" +
                "order_id=" + orderId + "&" +
                "redirect_url=http://example.com/success/a/safepay&" +
                "cancel_url=" + getCancelUrl();
    }
}