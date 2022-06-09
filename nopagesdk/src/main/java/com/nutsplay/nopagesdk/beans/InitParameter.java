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
//    private String buglyId;
    private String buglyChannel;
//    private String appsflyerId;
//    private String dataeyeId;
    private String language="en";
    private String customerServiceAddress="";
    private boolean isDebug = false;
    private boolean hasUI = true;
    private boolean showUserAgreement = true;
    private int UIVersion = 0; //默认是通用UI版本     0:通用UI（Poly那套UI）    1：侵权游戏UI
    private String aihelpAppkey="";
    private String aihelpDomain="";
    private String aihelpAppID="";

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

    public String getBuglyChannel() {
        return buglyChannel;
    }

    public void setBuglyChannel(String buglyChannel) {
        this.buglyChannel = buglyChannel;
    }

//    public String getAppsflyerId() {
//        return appsflyerId;
//    }
//
//    public void setAppsflyerId(String appsflyerId) {
//        this.appsflyerId = appsflyerId;
//    }

    public String getLanguage() {
//        String lan = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.key_sdk_language,"");
//        if (StringUtils.isBlank(lan)){
//            return language;
//        }else {
//            return lan;
//        }
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

    public int getUIVersion() {
        return UIVersion;
    }

    public void setUIVersion(int UIVersion) {
        this.UIVersion = UIVersion;
    }

    public String getAihelpAppkey() {
        return aihelpAppkey;
    }

    public void setAihelpAppkey(String aihelpAppkey) {
        this.aihelpAppkey = aihelpAppkey;
    }

    public String getAihelpDomain() {
        return aihelpDomain;
    }

    public void setAihelpDomain(String aihelpDomain) {
        this.aihelpDomain = aihelpDomain;
    }

    public String getAihelpAppID() {
        return aihelpAppID;
    }

    public void setAihelpAppID(String aihelpAppID) {
        this.aihelpAppID = aihelpAppID;
    }

    public boolean isShowUserAgreement() {
        return showUserAgreement;
    }

    public void setShowUserAgreement(boolean showUserAgreement) {
        this.showUserAgreement = showUserAgreement;
    }
}
