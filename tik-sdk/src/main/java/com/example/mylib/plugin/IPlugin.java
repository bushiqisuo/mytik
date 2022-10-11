package com.example.mylib.plugin;

import android.os.IBinder;

public interface IPlugin {
    void init();

    IBinder getBinder();
}
