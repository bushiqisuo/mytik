package com.example.mytik.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }

    protected void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    protected void showToastAsync(String str) {
        Looper.prepare();
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();
}
