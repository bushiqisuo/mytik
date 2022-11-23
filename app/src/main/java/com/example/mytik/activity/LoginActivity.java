package com.example.mytik.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytik.R;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.util.TextUtil;

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

    private void initView() {
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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", account);
        map.put("password", pwd);
        Log.e(TAG, "map: " +map);
        Api.config(ApiConfig.LOGIN_URL, map).postRequest(new ApiCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "onResponse: " +response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(response);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: ", e);
            }
        });
    }
}