package com.android.myview;

import android.app.Application;
import android.content.Context;

/**
 * author : zf
 * date   : 2019/9/12
 * You are the best.
 */
public class ScreenUtils {
    private static Context sContext = MyApplication.context;

    public static int getScreenWidth() {
        return sContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return sContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dpTopx(int dp) {
        final float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxTodp(int px) {
        final float scale = sContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
