package com.example.mytik.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.mytik.R;
import com.example.mytik.api.Api;
import com.example.mytik.api.ApiCallback;
import com.example.mytik.api.ApiConfig;
import com.example.mytik.util.TextUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";

    private static final String URL_REGISTER = "http://www.baidu.com";

    private Button bigRegister;

    private EditText accountInRegister;

    private EditText pwdInRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bigRegister = findViewById(R.id.bigRegister);
        accountInRegister = findViewById(R.id.accountInRegister);
        pwdInRegister = findViewById(R.id.pwdInRegister);

        bigRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountInRegister.getText().toString();
                String password = pwdInRegister.getText().toString();
                if (TextUtil.isEmpty(account) || TextUtil.isEmpty(password)) {
                    showToast("请输入账号密码");
                } else {
                    //网络请求
                    Log.d(TAG, "onClick: 开始请求注册接口");
                    register(account, password);
                }
            }
        });
    }

    private void register(String account, String pwd) {
        //请求参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", account);
        map.put("password", pwd);
        Api.config(ApiConfig.REGISTER_URL, map).postRequest(new ApiCallback() {
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