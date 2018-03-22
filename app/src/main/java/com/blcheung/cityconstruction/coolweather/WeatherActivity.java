package com.blcheung.cityconstruction.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blcheung.cityconstruction.coolweather.gson.DailyForecast;
import com.blcheung.cityconstruction.coolweather.gson.Weather;
import com.blcheung.cityconstruction.coolweather.service.AutoUpdateService;
import com.blcheung.cityconstruction.coolweather.ui.ChooseAreaFragment;
import com.blcheung.cityconstruction.coolweather.util.HttpUtil;
import com.blcheung.cityconstruction.coolweather.util.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    public static final String PREFS_WEATHER = "prefs_weather";
    public static final String PREFS_BINGPIC = "prefs_bingpic";
    public DrawerLayout dlDrawerLayout;
    private Button btnTitleNav;
    private ScrollView svWeather;
    private LinearLayout llDailyForecast;
    public SwipeRefreshLayout slRefreshWeather;
    private ImageView ivWeatherBingpic;
    private TextView tvTitleCity, tvTitleUpdateTime;
    private TextView tvNowDegree, tvNowWeatherInfo;
    private TextView tvAqiaqi, tvAqiPM25;
    private TextView tvSuggestionComfort, tvSuggsetionSport, tvSuggestionCw;
    private SharedPreferences prefs;
    public String weatherId;
    private long firstClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏 -> Android 5.0及以上
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            // 活动的布局会显示在状态栏上面
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        // 初始化控件
        initViews();
        btnTitleNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        slRefreshWeather.setColorSchemeColors(ContextCompat
                .getColor(this, R.color.colorPrimary));
        // 从本地缓存中获取天气信息
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prefsString = prefs.getString(PREFS_WEATHER, null);
        if (prefsString != null) {
            // 有缓存时直接解析天气数据
            Weather handlerWeatherResponse = Utility.handlerWeatherResponse(prefsString);
            weatherId = handlerWeatherResponse.getBasic().getId();
            showWeatherInfo(handlerWeatherResponse);
        } else {
            // 无缓存时从服务器中获取
            weatherId = getIntent().getStringExtra(ChooseAreaFragment.EXTRA_WEATHERID);
            svWeather.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        // 刷新
        slRefreshWeather.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBingPic();
                requestWeather(weatherId);
            }
        });
        // 本地缓存获取必应每日一图
        String prefsBingPic = prefs.getString(PREFS_BINGPIC, null);
        if (prefsBingPic != null) {
            Glide.with(WeatherActivity.this).load(prefsBingPic).into(ivWeatherBingpic);
        } else {
            // 从服务器中获取
            loadBingPic();
        }
    }

    /**
     * 控件的初始化
     */
    private void initViews() {
        svWeather = findViewById(R.id.sv_weather_layout);
        llDailyForecast = findViewById(R.id.ll_daily_forecast_layout);
        slRefreshWeather = findViewById(R.id.sl_refresh_weather);
        ivWeatherBingpic = findViewById(R.id.iv_weather_bing_pic);
        dlDrawerLayout = findViewById(R.id.dl_drawer_layout);
        // Title.xml
        tvTitleCity = findViewById(R.id.tv_title_city);
        btnTitleNav = findViewById(R.id.btn_title_nav_button);
        tvTitleUpdateTime = findViewById(R.id.tv_title_update_time);
        // Now.xml
        tvNowDegree = findViewById(R.id.tv_now_degree);
        tvNowWeatherInfo = findViewById(R.id.tv_now_weather_info);
        // Aqi.xml
        tvAqiaqi = findViewById(R.id.tv_aqi_aqi);
        tvAqiPM25 = findViewById(R.id.tv_aqi_pm25);
        // Suggestion.xml
        tvSuggestionComfort = findViewById(R.id.tv_suggestion_comfort);
//        tvSuggestionDress = findViewById(R.id.tv_suggestion_drsg);
        tvSuggsetionSport = findViewById(R.id.tv_suggestion_sport);
        tvSuggestionCw = findViewById(R.id.tv_suggestion_cw);
//        tvSuggestionAir = findViewById(R.id.tv_suggestion_air);
//        tvSuggestionFlu = findViewById(R.id.tv_suggestion_flu);
//        tvSuggestionTravel = findViewById(R.id.tv_suggestion_travel);
//        tvSuggestionUv = findViewById(R.id.tv_suggestion_uv);
    }

    //    private TextView tvSuggestionFlu, tvSuggestionTravel, tvSuggestionUv, tvSuggestionAir, tvSuggestionDress;

    /**
     * 根据weatherId从服务器请求城市天气信息
     *
     * @param weatherId
     */
    public void requestWeather(final String weatherId) {
        String url = "http://guolin.tech/api/weather?cityid=" + weatherId +
                "&key=e4c931cb79eb422f9670161b5a1000e8";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                final Weather weatherResponse = Utility.handlerWeatherResponse(responseData);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherResponse != null && "ok".equals(weatherResponse.getStatus())) {
                            // 缓存到本地
                            SharedPreferences.Editor editor = PreferenceManager.
                                    getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString(PREFS_WEATHER, responseData);
                            editor.apply();
                            showWeatherInfo(weatherResponse);
                        } else {
                            Toast.makeText(WeatherActivity.this,
                                    "服务器返回信息有误或数据解析错误!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        slRefreshWeather.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,
                                "服务器无响应,无法获取到信息!",
                                Toast.LENGTH_SHORT).show();
                        slRefreshWeather.setRefreshing(false);
                    }
                });
            }
        });
    }

    /**
     * 处理并显示Weather实体类中的数据信息在控件上
     *
     * @param weather
     */
    private void showWeatherInfo(Weather weather) {
        if (weather != null && "ok".equals(weather.getStatus())) {
            String cityName = weather.getBasic().getCity();
            String updateTime = weather.getBasic().getUpdate().getLoc().split(" ")[1];
            String degree = weather.getNow().getTmp() + "°C";
            String weatherInfo = weather.getNow().getCond().getTxt();
            tvTitleCity.setText(cityName);
            tvTitleUpdateTime.setText(updateTime);
            tvNowDegree.setText(degree);
            tvNowWeatherInfo.setText(weatherInfo);
            llDailyForecast.removeAllViews();
            // 遍历最近几天的天气信息
            for (DailyForecast dailyForecast : weather.getDailyForecastList()) {
                View view = LayoutInflater.from(this).inflate(R.layout.daily_forecast_item,
                        llDailyForecast, false);
                TextView tvItemDate = view.findViewById(R.id.tv_item_date);
                TextView tvItemInfo = view.findViewById(R.id.tv_item_info);
                TextView tvItemMax = view.findViewById(R.id.tv_item_max);
                TextView tvItemMin = view.findViewById(R.id.tv_item_min);
                tvItemDate.setText(dailyForecast.getDate());
                tvItemInfo.setText(dailyForecast.getCond().getTxt_d());
                tvItemMax.setText(dailyForecast.getTmp().getMax());
                tvItemMin.setText(dailyForecast.getTmp().getMin());
                llDailyForecast.addView(view);
            }
            if (weather.getAqi() != null) {
                tvAqiaqi.setText(weather.getAqi().getCity().getAqi());
                tvAqiPM25.setText(weather.getAqi().getCity().getPm25());
            }
            String comfort = "舒适度: " + weather.getSuggestion().getComf().getTxt();
//        String drsg = "穿衣指数: " + weather.getSuggestion().getDrsg().getTxt();
            String sport = "运动指数: " + weather.getSuggestion().getSport().getTxt();
            String carWash = "洗车指数: " + weather.getSuggestion().getCw().getTxt();
//        String air = "空气指数: " + weather.getSuggestion().getAir().getTxt();
//        String flu = "感冒指数: " + weather.getSuggestion().getFlu().getTxt();
//        String travel = "旅游指数: " + weather.getSuggestion().getTrav().getTxt();
//        String uv = "紫外线指数: " + weather.getSuggestion().getUv().getTxt();
//        tvSuggestionAir.setText(air);
            tvSuggestionComfort.setText(comfort);
//        tvSuggestionDress.setText(drsg);
            tvSuggsetionSport.setText(sport);
//        tvSuggestionFlu.setText(flu);
//        tvSuggestionTravel.setText(travel);
//        tvSuggestionUv.setText(uv);
            tvSuggestionCw.setText(carWash);
            svWeather.setVisibility(View.VISIBLE);
            // 启动更新服务
            Intent autoUpdateIntent = new Intent(this, AutoUpdateService.class);
            startService(autoUpdateIntent);
        } else {
            Toast.makeText(WeatherActivity.this, "获取天气信息失败!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseBingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString(PREFS_BINGPIC, responseBingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        String prefsString = prefs.getString(PREFS_BINGPIC, null);
//                        if (prefsString != null && prefsString.equals(responseBingPic)) {
//                            Glide.with(WeatherActivity.this)
//                                    .load(prefsString)
//                                    .into(ivWeatherBingpic);
//                        } else {
                        Glide.with(WeatherActivity.this)
                                .load(responseBingPic)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivWeatherBingpic);
//                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "图片加载失败!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 如果点击两次返回键的间隔小于两秒,则退出.
            if (System.currentTimeMillis() - firstClickTime > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                firstClickTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
