package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frank-ma on 2018/12/26 上午11:13
 * Email: frankma9103@gmail.com
 * Desc:
 */

public class PayResult implements Serializable {

    private boolean isSuc=false;
    private String orderid;//弃用：坚果订单号
    private String price;
    private String payType;
    private String currency;
    private String message;
    private String referenceId;//坚果订单号
    private String gameExt="";//游戏附加参数

    public String getGameExt() {
        return gameExt;
    }

    public void setGameExt(String gameExt) {
        this.gameExt = gameExt;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isSuc() {
        return isSuc;
    }

    public void setSuc(boolean suc) {
        isSuc = suc;
    }

    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PayResult{" +
                "isSuc=" + isSuc +
                ", orderid='" + orderid + '\'' +
                ", price='" + price + '\'' +
                ", payType='" + payType + '\'' +
                ", currency='" + currency + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
