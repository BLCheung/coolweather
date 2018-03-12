package com.blcheung.cityconstruction.coolweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/10 22:24
 */

import com.google.gson.annotations.SerializedName;

/**
 * air : {"brf":"较差","txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"} 空气污染扩散条件指数
 * comf : {"brf":"舒适","txt":"今天夜间不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"} 舒适度指数
 * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"} 洗车指数
 * drsg : {"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"} 穿衣指数
 * flu : {"brf":"易发","txt":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"} 感冒指数
 * sport : {"brf":"较适宜","txt":"天气较好，户外运动请注意防晒。推荐您进行室内运动。"} 运动指数
 * trav : {"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"} 旅游指数
 * uv : {"brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"} 紫外线指数
 */
public class Suggestion {
    /**
     * 空气污染扩散条件指数
     */
    @SerializedName("air")
    private AirBean air;
    /**
     * 舒适度指数
     */
    @SerializedName("comf")
    private ComfBean comf;
    /**
     * 洗车指数
     */
    private CwBean cw;
    /**
     * 穿衣指数
     */
    @SerializedName("drsg")
    private DrsgBean drsg;
    /**
     * 感冒指数
     */
    private FluBean flu;
    /**
     * 运动指数
     */
    @SerializedName("sport")
    private SportBean sport;
    /**
     * 旅游指数
     */
    private TravBean trav;
    /**
     * 紫外线指数
     */
    private UvBean uv;

    public AirBean getAir() {
        return air;
    }

    public void setAir(AirBean air) {
        this.air = air;
    }

    public ComfBean getComf() {
        return comf;
    }

    public void setComf(ComfBean comf) {
        this.comf = comf;
    }

    public CwBean getCw() {
        return cw;
    }

    public void setCw(CwBean cw) {
        this.cw = cw;
    }

    public DrsgBean getDrsg() {
        return drsg;
    }

    public void setDrsg(DrsgBean drsg) {
        this.drsg = drsg;
    }

    public FluBean getFlu() {
        return flu;
    }

    public void setFlu(FluBean flu) {
        this.flu = flu;
    }

    public SportBean getSport() {
        return sport;
    }

    public void setSport(SportBean sport) {
        this.sport = sport;
    }

    public TravBean getTrav() {
        return trav;
    }

    public void setTrav(TravBean trav) {
        this.trav = trav;
    }

    public UvBean getUv() {
        return uv;
    }

    public void setUv(UvBean uv) {
        this.uv = uv;
    }

    public static class AirBean {
        /**
         * brf : 较差
         * txt : 气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。
         */

        private String brf;
        @SerializedName("txt")
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class ComfBean {
        /**
         * brf : 舒适
         * txt : 今天夜间不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
         */

        /**
         * 生活指数简介
         */
        private String brf;
        /**
         * 生活指数详细描述
         */
        @SerializedName("txt")
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class CwBean {
        /**
         * brf : 较适宜
         * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
         */

        private String brf;
        @SerializedName("txt")
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class DrsgBean {
        /**
         * brf : 舒适
         * txt : 建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
         */

        private String brf;
        @SerializedName("txt")
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class FluBean {
        /**
         * brf : 易发
         * txt : 昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class SportBean {
        /**
         * brf : 较适宜
         * txt : 天气较好，户外运动请注意防晒。推荐您进行室内运动。
         */

        private String brf;
        @SerializedName("txt")
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class TravBean {
        /**
         * brf : 适宜
         * txt : 天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }

    public static class UvBean {
        /**
         * brf : 强
         * txt : 紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。
         */

        private String brf;
        private String txt;

        public String getBrf() {
            return brf;
        }

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
