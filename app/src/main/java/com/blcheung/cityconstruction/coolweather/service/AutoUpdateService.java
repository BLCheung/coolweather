package com.blcheung.cityconstruction.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.blcheung.cityconstruction.coolweather.WeatherActivity;
import com.blcheung.cityconstruction.coolweather.gson.Weather;
import com.blcheung.cityconstruction.coolweather.util.HttpUtil;
import com.blcheung.cityconstruction.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {

    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;    // 8小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;    // 触发时间
        Intent triggerIntent = new Intent(this, AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, triggerIntent, 0);
        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气信息
     */
    private void updateWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prefsWeather = prefs.getString(WeatherActivity.PREFS_WEATHER, null);
        if (prefsWeather != null) {
            // 有缓存时直接解析数据
            final Weather weather = Utility.handlerWeatherResponse(prefsWeather);
            String weatherId = weather.getBasic().getId();
            String url = "http://guolin.tech/api/weather?cityid=" + weatherId +
                    "&key=e4c931cb79eb422f9670161b5a1000e8";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Weather weatherResponse = Utility.handlerWeatherResponse(responseData);
                    if (weatherResponse != null && "ok".equals(weatherResponse.getStatus())) {
                        SharedPreferences.Editor editor = PreferenceManager
                                .getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString(WeatherActivity.PREFS_WEATHER, responseData);
                        editor.apply();
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString(WeatherActivity.PREFS_BINGPIC, bingPic);
                editor.apply();
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}
