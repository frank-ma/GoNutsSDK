package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-24 16:35
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKOrderModel implements Serializable {


    /**
     * code : 1
     * message : success
     * data : {"TransactionId":"2019092401323753515","Account":"","Price":0.99,"ReferenceId":"com.nutsplay.iap.item1001","ProductName":"一把金币","Currency":"USD","ServerId":"0","ChannelCode":"WEBPAY","ChannelOuterId":"","GameOuterId":"","PayUrl":""}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * TransactionId : 2019092401323753515
         * Account :
         * Price : 0.99
         * ReferenceId : com.nutsplay.iap.item1001
         * ProductName : 一把金币
         * Currency : USD
         * ServerId : 0
         * ChannelCode : WEBPAY
         * ChannelOuterId :
         * GameOuterId :
         * PayUrl :
         */

        private String TransactionId;
        private String Account;
        private double Price;
        private String ReferenceId;
        private String ProductName;
        private String Currency;
        private String ServerId;
        private String ChannelCode;
        private String ChannelOuterId;
        private String GameOuterId;
        private String PayUrl;

        public String getTransactionId() {
            return TransactionId;
        }

        public void setTransactionId(String TransactionId) {
            this.TransactionId = TransactionId;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getReferenceId() {
            return ReferenceId;
        }

        public void setReferenceId(String ReferenceId) {
            this.ReferenceId = ReferenceId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getCurrency() {
            return Currency;
        }

        public void setCurrency(String Currency) {
            this.Currency = Currency;
        }

        public String getServerId() {
            return ServerId;
        }

        public void setServerId(String ServerId) {
            this.ServerId = ServerId;
        }

        public String getChannelCode() {
            return ChannelCode;
        }

        public void setChannelCode(String ChannelCode) {
            this.ChannelCode = ChannelCode;
        }

        public String getChannelOuterId() {
            return ChannelOuterId;
        }

        public void setChannelOuterId(String ChannelOuterId) {
            this.ChannelOuterId = ChannelOuterId;
        }

        public String getGameOuterId() {
            return GameOuterId;
        }

        public void setGameOuterId(String GameOuterId) {
            this.GameOuterId = GameOuterId;
        }

        public String getPayUrl() {
            return PayUrl;
        }

        public void setPayUrl(String PayUrl) {
            this.PayUrl = PayUrl;
        }
    }
}
