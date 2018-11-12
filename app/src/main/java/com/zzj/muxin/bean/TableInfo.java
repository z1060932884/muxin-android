package com.zzj.muxin.bean;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/12 11:22
 * @desc :
 * @version: 1.0
 */
public class TableInfo  {
    private String time;
    private String buy;
    private String sale;
    private String flowInto;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getFlowInto() {
        return flowInto;
    }

    public void setFlowInto(String flowInto) {
        this.flowInto = flowInto;
    }
}
