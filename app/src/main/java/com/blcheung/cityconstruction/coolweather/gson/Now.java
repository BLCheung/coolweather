package com.blcheung.cityconstruction.coolweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/10 22:14
 */

import com.google.gson.annotations.SerializedName;

/**
 * cond : {"code":"100","txt":"晴"}
 * fl : 14 体感温度，默认单位：摄氏度
 * hum : 60 相对湿度
 * pcpn : 0.0 降水量
 * pres : 1020
 * tmp : 16 温度，默认单位：摄氏度
 * vis : 10 能见度，默认单位：公里大气压强
 * wind : {"deg":"263","dir":"西风","sc":"1-2","spd":"3"}
 */
public class Now {

    /**
     * 体感温度，默认单位：摄氏度
     */
    private String fl;
    /**
     * 相对湿度
     */
    private String hum;
    /**
     * 降水量
     */
    private String pcpn;
    /**
     * 大气压强
     */
    private String pres;
    /**
     * 温度，默认单位：摄氏度
     */
    @SerializedName("tmp")
    private String tmp;
    /**
     * 能见度，默认单位：公里大气压强
     */
    private String vis;
    /**
     * deg : 263 风向360角度
     * dir : 西风 风向
     * sc : 1-2 风力
     * spd : 3 风速，公里/小时
     */
    @SerializedName("cond")
    private CondBean cond;
    /**
     * code : 100 实况天气状况代码
     * txt : 晴 实况天气状况代码
     */
    private WindBean wind;

    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public static class CondBean {

        /**
         * 实况天气状况代码
         */
        private String code;
        /**
         * 实况天气状况代码
         */
        @SerializedName("txt")
        private String txt;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class WindBean {

        /**
         * 风向360角度
         */
        private String deg;
        /**
         * 西风 风向
         */
        private String dir;
        /**
         * 风力
         */
        private String sc;
        /**
         * 风速，公里/小时
         */
        private String spd;

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSpd() {
            return spd;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }
    }
}
