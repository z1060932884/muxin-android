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

    private float barMax;
    private float barMin;
    private float barLeft;
    private float barRight;


    public float getBarMax() {
        return barMax;
    }

    public void setBarMax(float barMax) {
        this.barMax = barMax;
    }

    public float getBarMin() {
        return barMin;
    }

    public void setBarMin(float barMin) {
        this.barMin = barMin;
    }

    public float getBarLeft() {
        return barLeft;
    }

    public void setBarLeft(float barLeft) {
        this.barLeft = barLeft;
    }

    public float getBarRight() {
        return barRight;
    }

    public void setBarRight(float barRight) {
        this.barRight = barRight;
    }

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
