package com.example.mytik.api;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    private static final String TAG = "Api";

    private static String requestUrl;

    private static HashMap<String, Object> requestParams;

    private static OkHttpClient okHttpClient;

    private static Api api = new Api();

    private Api() {
    }

    public static Api config(String url, HashMap<String, Object> params) {
        okHttpClient = new OkHttpClient.Builder()
                .build();
        requestUrl = ApiConfig.BASE_URL + url;
        requestParams = params;
        return api;
    }

    public static void postRequest(ApiCallback callback) {
        JSONObject jsonObject = new JSONObject(requestParams);
        String toString = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), toString);
        Log.e(TAG, "map: " +requestParams);
        Log.e(TAG, "url: " + requestUrl + "body:" + toString);
        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("contentType", "application/json;charset=utf-8")
                .post(requestBody)
                .build();
        //3.call
        Call call = okHttpClient.newCall(request);
        //4.post
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();
                callback.onSuccess(result);
            }
        });
    }
}
