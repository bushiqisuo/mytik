package com.example.mytik.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.mytik.R;
import com.example.mytik.util.TextUtil;

import java.io.IOException;

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
        accountInRegister =findViewById(R.id.accountInRegister);
        pwdInRegister =findViewById(R.id.pwdInRegister);

        bigRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtil.isEmpty(accountInRegister.getText().toString()) ||
                        TextUtil.isEmpty(pwdInRegister.getText().toString())) {
                    showToast("请输入账号密码");
                } else {
                    //网络请求
                    Log.d(TAG, "onClick: 开始请求注册接口");
                    //1.client
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .build();
                    //2.request

                }
            }
        });
    }
}