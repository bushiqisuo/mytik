package com.example.mytik.api;

public interface ApiCallback {
    void onSuccess(String response);

    void onFailure(Exception e);
}
