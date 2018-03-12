package com.blcheung.cityconstruction.coolweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/10 23:45
 */

/**
 * astro : {"mr":"01:17","ms":"12:28","sr":"06:40","ss":"18:33"}
 * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
 * date : 2018-03-10
 * hum : 29
 * pcpn : 0.0
 * pop : 0
 * pres : 1022
 * tmp : {"max":"22","min":"10"}
 * uv : 10
 * vis : 16
 * wind : {"deg":"0","dir":"无持续风向","sc":"1-2","spd":"8"}
 */
public class DailyForecast {
    /**
     * 日月升落时间
     * mr : 01:17
     * ms : 12:28
     * sr : 06:40
     * ss : 18:33
     */
    private AstroBean astro;
    /**
     * 昼夜天气状况与描述
     * code_d : 100
     * code_n : 100
     * txt_d : 晴
     * txt_n : 晴
     */
    private CondBean cond;
    /**
     * 预报日期
     */
    private String date;
    /**
     * 相对湿度
     */
    private String hum;
    /**
     * 降水量
     */
    private String pcpn;
    /**
     * 降水概率
     */
    private String pop;
    /**
     * 大气压强
     */
    private String pres;
    /**
     * max : 22
     * min : 10
     */
    private TmpBean tmp;
    /**
     * 紫外线强度指数
     */
    private String uv;
    /**
     * 能见度，单位：公里
     */
    private String vis;
    /**
     * 风相关
     * deg : 0
     * dir : 无持续风向
     * sc : 1-2
     * spd : 8
     */
    private WindBean wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public TmpBean getTmp() {
        return tmp;
    }

    public void setTmp(TmpBean tmp) {
        this.tmp = tmp;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
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

    public static class AstroBean {
        /**
         * 月升时间
         */
        private String mr;
        /**
         * 月落时间
         */
        private String ms;
        /**
         * 日升时间
         */
        private String sr;
        /**
         * 日落时间
         */
        private String ss;

        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }

    public static class CondBean {
        /**
         * 白天天气状况代码
         */
        private String code_d;
        /**
         * 夜间天气状况代码
         */
        private String code_n;
        /**
         * 白天天气状况描述
         */
        private String txt_d;
        /**
         * 夜间天气状况描述
         */
        private String txt_n;

        public String getCode_d() {
            return code_d;
        }

        public void setCode_d(String code_d) {
            this.code_d = code_d;
        }

        public String getCode_n() {
            return code_n;
        }

        public void setCode_n(String code_n) {
            this.code_n = code_n;
        }

        public String getTxt_d() {
            return txt_d;
        }

        public void setTxt_d(String txt_d) {
            this.txt_d = txt_d;
        }

        public String getTxt_n() {
            return txt_n;
        }

        public void setTxt_n(String txt_n) {
            this.txt_n = txt_n;
        }
    }

    public static class TmpBean {
        /**
         * 最高温度
         */
        private String max;
        /**
         * 最低温度
         */
        private String min;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }

    public static class WindBean {
        /**
         * 风向360角度
         */
        private String deg;
        /**
         * 风向
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
