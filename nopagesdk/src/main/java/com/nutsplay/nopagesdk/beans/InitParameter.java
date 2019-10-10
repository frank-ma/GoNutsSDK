package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frank-ma on 2019-09-19 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class InitParameter implements Serializable {

    private String clientId;
    private String clientKey;
    private String buglyId;
    private String buglyChannel;
    private String appsflyerId;
    private String dataeyeId;
    private String language;
    private String customerServiceAddress="";
    private boolean isDebug = false;
    private boolean hasUI = true;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getBuglyId() {
        return buglyId;
    }

    public void setBuglyId(String buglyId) {
        this.buglyId = buglyId;
    }

    public String getBuglyChannel() {
        return buglyChannel;
    }

    public void setBuglyChannel(String buglyChannel) {
        this.buglyChannel = buglyChannel;
    }

    public String getAppsflyerId() {
        return appsflyerId;
    }

    public void setAppsflyerId(String appsflyerId) {
        this.appsflyerId = appsflyerId;
    }

    public String getDataeyeId() {
        return dataeyeId;
    }

    public void setDataeyeId(String dataeyeId) {
        this.dataeyeId = dataeyeId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public String getCustomerServiceAddress() {
        return customerServiceAddress;
    }

    public void setCustomerServiceAddress(String customerServiceAddress) {
        this.customerServiceAddress = customerServiceAddress;
    }

    public boolean isHasUI() {
        return hasUI;
    }

    public void setHasUI(boolean hasUI) {
        this.hasUI = hasUI;
    }
}
