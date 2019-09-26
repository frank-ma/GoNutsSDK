package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-23 17:09
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class Purchase implements Serializable {


    /**
     * orderId : 12999763169054705758.1373895267524947
     * packageName : com.nutsplay.game.demo
     * productId : com.nutsplay.xianmo.1001
     * purchaseTime : 1418702012574
     * purchaseState : 0
     * developerPayload : 2014121214255606432
     * purchaseToken : bkcdlmcdebkalcdhnlicdajp.AO-J1Oy9Lio-jjvax11Ln1zy9Poe_2_sUXaExjR5bqmt-zyF87zjSJZ9pFweCN90k9YC-aw5-2SBGI4n-LSr9RjOiUP3hCd08J3VuX7Og54q_eYNhsinKCPDa8C_WGZcPQaImC2iwDR4PrVI6SApHdfkeLbqsXAX4A
     */

    private String orderId;
    private String packageName;
    private String productId;
    private long purchaseTime;
    private int purchaseState;
    private String developerPayload;
    private String purchaseToken;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public int getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(int purchaseState) {
        this.purchaseState = purchaseState;
    }

    public String getDeveloperPayload() {
        return developerPayload;
    }

    public void setDeveloperPayload(String developerPayload) {
        this.developerPayload = developerPayload;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }
}
