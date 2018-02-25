package com.blcheung.cityconstruction.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BLCheung.
 * Date:2018年1月31日,0031 1:37
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String adress, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(adress)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
