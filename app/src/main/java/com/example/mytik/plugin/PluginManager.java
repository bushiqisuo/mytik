package com.example.mytik.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.mytik.util.ContextHolder;

import java.util.Arrays;
import java.util.List;

/**
 * 插件管理类
 */
public class PluginManager {
    private static final String TAG = "PluginManager";

    private static final String PACKAGE_NAME = "com.example.mytik";

    private static volatile PluginManager pluginManager;

    private static List installedSplits;

    private PluginManager() {
        //获取插件名集合
        loadInstalledSplits();
    }

    public static PluginManager getInstance() {
        if (pluginManager == null) {
            synchronized (PluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new PluginManager();
                }
            }
        }
        return pluginManager;
    }

    private void loadInstalledSplits() {
        Context context = ContextHolder.getContext();
        if (context == null) {
            Log.e(TAG, "context is null and return");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            Log.e(TAG, "packageManager is null and return");
            return;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(PACKAGE_NAME, 0);
            installedSplits = Arrays.asList(packageInfo.splitNames);
            Log.d(TAG, "installedSplits: " + installedSplits);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "packageName is not found", e);
        }
    }
}
