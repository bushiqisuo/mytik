package com.example.mytik.entity;

public class LoginResponse {
    /**
     * msg : success
     * code : 0
     * expire : 604800
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNjY5MjA4MzI0LCJleHAiOjE2Njk4MTMxMjR9.E_a7pScUg6Y2Nr_Lm39PdXF2cxk3d-e3zqduHHUNA-Fw8JdH_GB9PjXhdSKnmS-E8PyYsRVhNv2kg6KLT8Ru_A
     */

    private String msg;
    private int code;
    private int expire;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
