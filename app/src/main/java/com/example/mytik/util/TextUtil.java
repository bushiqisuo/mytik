package com.example.mytik.util;

public class TextUtil {
    private static final String TAG = "TestUtil";

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
