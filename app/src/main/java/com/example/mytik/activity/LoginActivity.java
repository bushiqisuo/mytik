package com.example.mytik.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytik.R;
import com.example.mytik.util.TextUtil;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    private Button bigLogin;

    private EditText accountInLogin;

    private EditText pwdInLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountInLogin = findViewById(R.id.accountInLogin);
        pwdInLogin = findViewById(R.id.pwdInLogin);
        bigLogin = findViewById(R.id.bigLogin);
        bigLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtil.isEmpty(pwdInLogin.getText().toString()) &&
                !TextUtil.isEmpty(accountInLogin.getText().toString())) {
                    showToast("账号:" + accountInLogin.getText().toString() +
                            "密码:" + pwdInLogin.getText().toString());
                } else {
                    showToast("请输入账号密码");
                }
            }
        });
    }
}