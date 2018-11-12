package com.zzj.muxin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/9 14:49
 * @desc :
 * @version: 1.0
 */
public class DealRealInfoBean implements Serializable {


    /**
     * original : {"high":"6560","vol":21091,"last":"6500.8641","low":"6466.2844","buy":"6499.4189","sell":"6500.8636","change":"-23.2307000000","currency":"USDT","open":6531.4903,"change_rate":-0.0036,"coin":"BTC"}
     * last : {"usd":6429.35459,"cny":44657.024}
     * coin_type : BTC/USDT
     * coin_title : Bitcoin
     * buy : {"usd":6427.92529,"cny":44647.09635}
     * sell : {"usd":6429.3541,"cny":44657.02057}
     * support_dc : 1
     * attention_rate : 0
     * fee : 0.2
     * support_cs : true
     * currency_class : 1
     * ad_in_content : []
     * high : {"usd":6487.84,"cny":45063.25205}
     * vol : 21091
     * support_cny : false
     * low : {"usd":6395.15527,"cny":44419.48228}
     * ad_in_header : [{"secondary_data":"BITKAN","unit":"cny","primary_data":0,"link":"http://btckan.com"},{"secondary_data":"BITKAN","unit":"btc","primary_data":0,"link":"http://btckan.com"},{"secondary_data":"BITKAN","unit":"p/s","primary_data":0,"link":"http://btckan.com"}]
     * rate : {"usd":1,"cny":6.943}
     * name : OKEX
     * id : 920
     * trade_link : []
     * open : {"high":{"usd":6535.8432,"cny":45318.24265},"vol":19377,"original":{"high":6595.2,"vol":19377,"last":6524.0948,"low":6505.4002,"buy":6524.0426,"sell":6524.0948,"open":6545.2086},"last":{"usd":6465.37795,"cny":44829.65054},"low":{"usd":6446.8516,"cny":44701.19251},"buy":{"usd":6465.32622,"cny":44829.29185},"sell":{"usd":6465.37795,"cny":44829.65054}}
     * status : normal
     */

    private OriginalBean original;
    private LastBean last;
    private String coin_type;
    private String coin_title;
    private BuyBean buy;
    private SellBean sell;
    private String support_dc;
    private String attention_rate;
    private String fee;
    private boolean support_cs;
    private int currency_class;
    private HighBean high;
    private int vol;
    private boolean support_cny;
    private LowBean low;
    private RateBean rate;
    private String name;
    private String id;
    private OpenBean open;
    private String status;

    private List<AdInHeaderBean> ad_in_header;


    public OriginalBean getOriginal() {
        return original;
    }

    public void setOriginal(OriginalBean original) {
        this.original = original;
    }

    public LastBean getLast() {
        return last;
    }

    public void setLast(LastBean last) {
        this.last = last;
    }

    public String getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }

    public String getCoin_title() {
        return coin_title;
    }

    public void setCoin_title(String coin_title) {
        this.coin_title = coin_title;
    }

    public BuyBean getBuy() {
        return buy;
    }

    public void setBuy(BuyBean buy) {
        this.buy = buy;
    }

    public SellBean getSell() {
        return sell;
    }

    public void setSell(SellBean sell) {
        this.sell = sell;
    }

    public String getSupport_dc() {
        return support_dc;
    }

    public void setSupport_dc(String support_dc) {
        this.support_dc = support_dc;
    }

    public String getAttention_rate() {
        return attention_rate;
    }

    public void setAttention_rate(String attention_rate) {
        this.attention_rate = attention_rate;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public boolean isSupport_cs() {
        return support_cs;
    }

    public void setSupport_cs(boolean support_cs) {
        this.support_cs = support_cs;
    }

    public int getCurrency_class() {
        return currency_class;
    }

    public void setCurrency_class(int currency_class) {
        this.currency_class = currency_class;
    }

    public HighBean getHigh() {
        return high;
    }

    public void setHigh(HighBean high) {
        this.high = high;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public boolean isSupport_cny() {
        return support_cny;
    }

    public void setSupport_cny(boolean support_cny) {
        this.support_cny = support_cny;
    }

    public LowBean getLow() {
        return low;
    }

    public void setLow(LowBean low) {
        this.low = low;
    }

    public RateBean getRate() {
        return rate;
    }

    public void setRate(RateBean rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OpenBean getOpen() {
        return open;
    }

    public void setOpen(OpenBean open) {
        this.open = open;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<AdInHeaderBean> getAd_in_header() {
        return ad_in_header;
    }

    public void setAd_in_header(List<AdInHeaderBean> ad_in_header) {
        this.ad_in_header = ad_in_header;
    }


    public static class OriginalBean {
        /**
         * high : 6560
         * vol : 21091
         * last : 6500.8641
         * low : 6466.2844
         * buy : 6499.4189
         * sell : 6500.8636
         * change : -23.2307000000
         * currency : USDT
         * open : 6531.4903
         * change_rate : -0.0036
         * coin : BTC
         */

        private String high;
        private int vol;
        private String last;
        private String low;
        private String buy;
        private String sell;
        private String change;
        private String currency;
        private double open;
        private double change_rate;
        private String coin;

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public int getVol() {
            return vol;
        }

        public void setVol(int vol) {
            this.vol = vol;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getChange() {
            return change;
        }

        public void setChange(String change) {
            this.change = change;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getChange_rate() {
            return change_rate;
        }

        public void setChange_rate(double change_rate) {
            this.change_rate = change_rate;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }
    }

    public static class LastBean {
        /**
         * usd : 6429.35459
         * cny : 44657.024
         */

        private double usd;
        private double cny;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class BuyBean {
        /**
         * usd : 6427.92529
         * cny : 44647.09635
         */

        private double usd;
        private double cny;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class SellBean {
        /**
         * usd : 6429.3541
         * cny : 44657.02057
         */

        private double usd;
        private double cny;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class HighBean {
        /**
         * usd : 6487.84
         * cny : 45063.25205
         */

        private double usd;
        private double cny;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class LowBean {
        /**
         * usd : 6395.15527
         * cny : 44419.48228
         */

        private double usd;
        private double cny;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class RateBean {
        /**
         * usd : 1
         * cny : 6.943
         */

        private int usd;
        private double cny;

        public int getUsd() {
            return usd;
        }

        public void setUsd(int usd) {
            this.usd = usd;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }
    }

    public static class OpenBean {
        /**
         * high : {"usd":6535.8432,"cny":45318.24265}
         * vol : 19377
         * original : {"high":6595.2,"vol":19377,"last":6524.0948,"low":6505.4002,"buy":6524.0426,"sell":6524.0948,"open":6545.2086}
         * last : {"usd":6465.37795,"cny":44829.65054}
         * low : {"usd":6446.8516,"cny":44701.19251}
         * buy : {"usd":6465.32622,"cny":44829.29185}
         * sell : {"usd":6465.37795,"cny":44829.65054}
         */

        private HighBeanX high;
        private int vol;
        private OriginalBeanX original;
        private LastBeanX last;
        private LowBeanX low;
        private BuyBeanX buy;
        private SellBeanX sell;

        public HighBeanX getHigh() {
            return high;
        }

        public void setHigh(HighBeanX high) {
            this.high = high;
        }

        public int getVol() {
            return vol;
        }

        public void setVol(int vol) {
            this.vol = vol;
        }

        public OriginalBeanX getOriginal() {
            return original;
        }

        public void setOriginal(OriginalBeanX original) {
            this.original = original;
        }

        public LastBeanX getLast() {
            return last;
        }

        public void setLast(LastBeanX last) {
            this.last = last;
        }

        public LowBeanX getLow() {
            return low;
        }

        public void setLow(LowBeanX low) {
            this.low = low;
        }

        public BuyBeanX getBuy() {
            return buy;
        }

        public void setBuy(BuyBeanX buy) {
            this.buy = buy;
        }

        public SellBeanX getSell() {
            return sell;
        }

        public void setSell(SellBeanX sell) {
            this.sell = sell;
        }

        public static class HighBeanX {
            /**
             * usd : 6535.8432
             * cny : 45318.24265
             */

            private double usd;
            private double cny;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }
        }

        public static class OriginalBeanX {
            /**
             * high : 6595.2
             * vol : 19377
             * last : 6524.0948
             * low : 6505.4002
             * buy : 6524.0426
             * sell : 6524.0948
             * open : 6545.2086
             */

            private double high;
            private int vol;
            private double last;
            private double low;
            private double buy;
            private double sell;
            private double open;

            public double getHigh() {
                return high;
            }

            public void setHigh(double high) {
                this.high = high;
            }

            public int getVol() {
                return vol;
            }

            public void setVol(int vol) {
                this.vol = vol;
            }

            public double getLast() {
                return last;
            }

            public void setLast(double last) {
                this.last = last;
            }

            public double getLow() {
                return low;
            }

            public void setLow(double low) {
                this.low = low;
            }

            public double getBuy() {
                return buy;
            }

            public void setBuy(double buy) {
                this.buy = buy;
            }

            public double getSell() {
                return sell;
            }

            public void setSell(double sell) {
                this.sell = sell;
            }

            public double getOpen() {
                return open;
            }

            public void setOpen(double open) {
                this.open = open;
            }
        }

        public static class LastBeanX {
            /**
             * usd : 6465.37795
             * cny : 44829.65054
             */

            private double usd;
            private double cny;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }
        }

        public static class LowBeanX {
            /**
             * usd : 6446.8516
             * cny : 44701.19251
             */

            private double usd;
            private double cny;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }
        }

        public static class BuyBeanX {
            /**
             * usd : 6465.32622
             * cny : 44829.29185
             */

            private double usd;
            private double cny;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }
        }

        public static class SellBeanX {
            /**
             * usd : 6465.37795
             * cny : 44829.65054
             */

            private double usd;
            private double cny;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }
        }
    }

    public static class AdInHeaderBean {
        /**
         * secondary_data : BITKAN
         * unit : cny
         * primary_data : 0
         * link : http://btckan.com
         */

        private String secondary_data;
        private String unit;
        private int primary_data;
        private String link;

        public String getSecondary_data() {
            return secondary_data;
        }

        public void setSecondary_data(String secondary_data) {
            this.secondary_data = secondary_data;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getPrimary_data() {
            return primary_data;
        }

        public void setPrimary_data(int primary_data) {
            this.primary_data = primary_data;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
