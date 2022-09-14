package com.example.mytik.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytik.R;
import com.example.mytik.util.TextUtil;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";

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
                if (!TextUtil.isEmpty(accountInRegister.getText().toString()) &&
                        !TextUtil.isEmpty(pwdInRegister.getText().toString())) {
                    showToast("账号:" + accountInRegister.getText().toString() +
                            "密码:" + pwdInRegister.getText().toString());
                } else {
                    showToast("请输入账号密码");
                }
            }
        });
    }
}