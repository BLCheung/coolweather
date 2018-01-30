package com.blcheung.cityconstruction.coolweather;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by BLCheung.
 * Date:2018年1月31日,0031 1:19
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
