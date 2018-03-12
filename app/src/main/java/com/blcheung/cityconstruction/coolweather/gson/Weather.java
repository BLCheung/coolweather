package com.blcheung.cityconstruction.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BLCheung.
 * Date:2018/3/11 0:25
 */

public class Weather {
    private String status;
    private Basic basic;
    private AQI aqi;
    private Now now;
    private Suggestion suggestion;
    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecastList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public AQI getAqi() {
        return aqi;
    }

    public void setAqi(AQI aqi) {
        this.aqi = aqi;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecast> getDailyForecastList() {
        return dailyForecastList;
    }

    public void setDailyForecastList(List<DailyForecast> dailyForecastList) {
        this.dailyForecastList = dailyForecastList;
    }
}
