package com.blcheung.cityconstruction.coolweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/10 22:00
 */

/**
 * city : {"aqi":"95","qlty":"良","pm25":"71","pm10":"98","no2":"34","so2":"10","co":"0.51","o3":"129"}
 */
public class AQI {

    /**
     * aqi : 95 空气质量指数
     * qlty : 良 空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
     * pm25 : 71 pm2.5
     * pm10 : 98 pm10
     * no2 : 34 二氧化氮
     * so2 : 10 二氧化硫
     * co : 0.51 一氧化碳
     * o3 : 129 臭氧
     */
    private CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * 空气质量指数
         */
        private String aqi;
        /**
         * 空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
         */
        private String qlty;
        /**
         * pm2.5
         */
        private String pm25;
        /**
         * pm25
         */
        private String pm10;
        /**
         * 二氧化氮
         */
        private String no2;
        /**
         * 二氧化硫
         */
        private String so2;
        /**
         * 一氧化碳
         */
        private String co;
        /**
         * 臭氧
         */
        private String o3;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getQlty() {
            return qlty;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
    }
}
