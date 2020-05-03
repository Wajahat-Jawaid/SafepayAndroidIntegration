package com.wajahat.safepayandroidintegration;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentWebViewActivity extends AppCompatActivity {

    private static final String TAG = PaymentWebViewActivity.class.getSimpleName();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_webview);

        // Get urls from the launcher activity
        final String paymentUrl = getIntent().getStringExtra("paymentUrl");
        final String cancelUrl = getIntent().getStringExtra("cancelUrl");

        // Open paymentUrl in WebView
        WebView mWebView = findViewById(R.id.web_view);
        mWebView.loadUrl(paymentUrl);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Avoid calling mWebView.loadUrl() as it delegates the url to native Browser like
        // Google Chrome instead of opening it inside app's native WebView
        // We force links and redirects to open in the WebView
        mWebView.setWebViewClient(new WebViewClient() {

            // Every time redirection occurs, this event is called. Handle your Url
            // change logic here
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // If payment is successful, safepay returns url which contains the keyword
                // 'success'
                if (url.contains("success")) {
                    Toast.makeText(
                            PaymentWebViewActivity.this,
                            "Payment successfully done",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                // If url is exactly equal to the cancelUrl you provided, then safepay
                // cancelled payment request.
                else if (url.equalsIgnoreCase(cancelUrl)) {
                    Toast.makeText(
                            PaymentWebViewActivity.this,
                            "Payment failed",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                // This case should never reach as the success case is handled in first
                // condition whereas if something wrong happens, then safepay returns 'cancelUrl'
                // Provide implementation to handle rarely happened abnormal cases
                else {
                    // Something wrong happened. Please try later
                }

                return false;
            }
        });
    }
}