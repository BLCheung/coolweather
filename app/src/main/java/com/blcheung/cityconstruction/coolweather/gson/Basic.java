package com.blcheung.cityconstruction.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BLCheung.
 * Date:2018/3/10 21:50
 */

/**
 * city : 广州
 * cnty : 中国
 * id : CN101280101
 * lat : 23.12517738
 * lon : 113.28063965
 * update : {"loc":"2018-03-10 20:47","utc":"2018-03-10 12:47"}
 */
public class Basic {
    /**
     * 城市
     */
    @SerializedName("city")
    private String city;
    /**
     * 国家
     */
    private String cnty;
    /**
     * 城市ID
     */
    @SerializedName("id")
    private String id;
    /**
     * 经度
     */
    private String lat;
    /**
     * 纬度
     */
    private String lon;
    /**
     * loc : 2018-03-10 20:47 当地时间，24小时制，格式yyyy-MM-dd HH:mm
     * utc : 2018-03-10 12:47 UTC时间，24小时制，格式yyyy-MM-dd HH:mm
     */
    private UpdateBean update;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

    public static class UpdateBean {

        /**
         * 当地时间，24小时制，格式yyyy-MM-dd HH:mm
         */
        @SerializedName("loc")
        private String loc;
        /**
         * UTC时间，24小时制，格式yyyy-MM-dd HH:mm
         */
        private String utc;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }
    }
}
