package com.example.mytik.util;

import android.content.Context;

public class ContextHolder {
    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
