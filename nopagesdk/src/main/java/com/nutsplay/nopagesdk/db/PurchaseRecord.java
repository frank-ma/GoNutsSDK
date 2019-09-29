package com.nutsplay.nopagesdk.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-29 10:18
 * Email: frankma9103@gmail.com
 * Desc:
 */
@Entity
public class PurchaseRecord implements Serializable {

    @Unique
    private String transactionId;

    private static final long  serialVersionUID=0x100;

    private String googleId;

    private String purchaseJson;

    private String skuId;

    private int status;//1成功  0创建订单  2支付成功，未兑换


    @Generated(hash = 1790474878)
    public PurchaseRecord(String transactionId, String googleId,
            String purchaseJson, String skuId, int status) {
        this.transactionId = transactionId;
        this.googleId = googleId;
        this.purchaseJson = purchaseJson;
        this.skuId = skuId;
        this.status = status;
    }



    @Generated(hash = 1397732425)
    public PurchaseRecord() {
    }



    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "transactionId='" + transactionId + '\'' +
                ", googleId='" + googleId + '\'' +
                ", purchaseJson='" + purchaseJson + '\'' +
                ", skuId='" + skuId + '\'' +
                ", status=" + status +
                '}';
    }



    public String getTransactionId() {
        return this.transactionId;
    }



    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }



    public String getGoogleId() {
        return this.googleId;
    }



    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }



    public String getPurchaseJson() {
        return this.purchaseJson;
    }



    public void setPurchaseJson(String purchaseJson) {
        this.purchaseJson = purchaseJson;
    }



    public String getSkuId() {
        return this.skuId;
    }



    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }



    public int getStatus() {
        return this.status;
    }



    public void setStatus(int status) {
        this.status = status;
    }
}
