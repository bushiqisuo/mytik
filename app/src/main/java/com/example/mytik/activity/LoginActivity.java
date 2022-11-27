package com.example.mytik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytik.R;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.entity.LoginResponse;
import com.example.mytik.util.SpUtil;
import com.example.mytik.util.TextUtil;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    private Button bigLogin;

    private EditText accountInLogin;

    private EditText pwdInLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    private void initEvent() {
        bigLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountInLogin.getText().toString();
                String pwd = pwdInLogin.getText().toString();

                if (TextUtil.isEmpty(account) || TextUtil.isEmpty(pwd)) {
                    showToast("请输入账号密码");
                } else {
                    //showToast("账号:" + accountInLogin.getText().toString() +
                    //        "密码:" + pwdInLogin.getText().toString());
                    Log.e(TAG, "开始请求登录接口 账号"+account);
                    login(account, pwd);
                    //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        });
    }

    protected void initView() {
        accountInLogin = findViewById(R.id.accountInLogin);
        pwdInLogin = findViewById(R.id.pwdInLogin);
        bigLogin = findViewById(R.id.bigLogin);
    }

    /**
     * http请求 后面补充
     * @param account 用户名
     * @param pwd 密码
     */
    private void login(String account, String pwd) {
        //2.request
        //请求参数
        HashMap<String, Object> map = new HashMap();
        map.put("mobile", account);
        map.put("password", pwd);
        Api.config(ApiConfig.LOGIN_URL, map).postRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "response: " + response );

                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                if (loginResponse.getCode() == 0) {
                    String token = loginResponse.getToken();
                    SpUtil.storeString(LoginActivity.this, "token", token);
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    showToastAsync(response);
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: ", e);
            }
        });
    }
}