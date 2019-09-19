package com.android.myview.copy;

import android.app.Application;
import android.content.Context;

/**
 * author : zf
 * date   : 2019/9/12
 * You are the best.
 */
public class MyApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
