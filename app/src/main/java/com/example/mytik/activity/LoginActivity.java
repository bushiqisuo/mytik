package com.example.mytik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytik.R;
import com.example.mytik.util.TextUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    private static final String URL_LOGIN = "http://www.baidu.com";

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
        String account = accountInLogin.getText().toString();
        String pwd = pwdInLogin.getText().toString();
        bigLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtil.isEmpty(pwdInLogin.getText().toString()) ||
                        TextUtil.isEmpty(accountInLogin.getText().toString())) {
                    showToast("请输入账号密码");
                } else {
                    showToast("账号:" + accountInLogin.getText().toString() +
                            "密码:" + pwdInLogin.getText().toString());
                    Log.d(TAG, "开始请求登录接口");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
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
     */
    private void httpReq() {
        //1.client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        //2.request
        //请求参数
        Map map = new HashMap();
        //map.put("mobile", account);
        //map.put("password", pwd);
        JSONObject jsonObject = new JSONObject(map);
        String toString = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), toString);
        Request request = new Request.Builder()
                .url(URL_LOGIN)
                .addHeader("contentType", "application/json;charset=utf-8")
                .post(requestBody)
                .build();
        //3.call
        Call call = okHttpClient.newCall(request);
        //4.post
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + e );
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().toString());
            }
        });
    }
}