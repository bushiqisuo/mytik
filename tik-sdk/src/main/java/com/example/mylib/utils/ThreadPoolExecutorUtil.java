package com.example.mylib.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorUtil {
    private static volatile ThreadPoolExecutorUtil threadPoolExecutor;

    private ExecutorService mCachedThreadPool;

    public ThreadPoolExecutorUtil() {
    }

    public static ThreadPoolExecutorUtil getInstance() {
        if (threadPoolExecutor == null) {
            synchronized (ThreadPoolExecutorUtil.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutorUtil();
                }
            }
        }
        return threadPoolExecutor;
    }

    public synchronized ExecutorService getThreadPool() {
        if (mCachedThreadPool == null) {
            mCachedThreadPool = Executors.newCachedThreadPool();
        }
        return mCachedThreadPool;
    }
}
