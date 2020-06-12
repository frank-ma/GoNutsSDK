package com.nutsplay.nopagesdk.billing;

import java.io.Serializable;

/**
 * Created by frankma on 2019-10-08 22:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class PurchaseJson implements Serializable {


    /**
     * orderId : GPA.3362-7306-2522-22787
     * packageName : com.nutsplay.nonutssdk
     * productId : com.nutsplay.iap.item1002
     * purchaseTime : 1570529045458
     * purchaseState : 0
     * purchaseToken : eodejijffafnknhfoneobmlj.AO-J1OwdlWmGScJ1he8PJGLtzaIHULmIJpShZVcHn9Tm-4_-jpROmlr0Z8BgXOo0jqoyIBfavRhVZaLqp_GPKSPDbZaWOSrVdOg7RXQFac5FHfxPrn9I0qO0lbSXASIjxWCNvi6zQU73mL5VdjQL0anOzQbwjr8TIg
     * acknowledged : false
     * autoRenewing : true(订阅特有的，消耗型商品没有这个参数)
     */

    private String orderId;
    private String packageName;
    private String productId;
    private long purchaseTime;
    private int purchaseState;
    private String purchaseToken;
    private boolean acknowledged;
    private String developerPayload;
    private boolean autoRenewing;

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

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public String getDeveloperPayload() {
        return developerPayload;
    }

    public void setDeveloperPayload(String developerPayload) {
        this.developerPayload = developerPayload;
    }

    public boolean isAutoRenewing() {
        return autoRenewing;
    }

    public void setAutoRenewing(boolean autoRenewing) {
        this.autoRenewing = autoRenewing;
    }

    @Override
    public String toString() {
        return "PurchaseJson{" +
                "orderId='" + orderId + '\'' +
                ", packageName='" + packageName + '\'' +
                ", productId='" + productId + '\'' +
                ", purchaseTime=" + purchaseTime +
                ", purchaseState=" + purchaseState +
                ", purchaseToken='" + purchaseToken + '\'' +
                ", acknowledged=" + acknowledged +
                ", developerPayload='" + developerPayload + '\'' +
                ", autoRenewing=" + autoRenewing +
                '}';
    }
}
