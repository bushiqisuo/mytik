package com.example.mytik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.example.mytik.R;
import com.example.mytik.jsbridge.BridgeHandler;
import com.example.mytik.jsbridge.BridgeWebView;
import com.example.mytik.jsbridge.CallBackFunction;

public class WebActivity extends BaseActivity {
    private BridgeWebView bridgeWebView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        bridgeWebView = findViewById(R.id.bridgeWebView);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
        }
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = bridgeWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        bridgeWebView.loadUrl(url);
        registerJavaHandler();
    }

    private void registerJavaHandler() {
        bridgeWebView.registerHandler("goback", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                finish();
            }
        });
    }


}