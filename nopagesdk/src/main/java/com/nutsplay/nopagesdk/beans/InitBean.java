package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frank-ma on 2019-08-07 19:53
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class InitBean implements Serializable {

    private String clientID;
    private String time;
    private String token;//当前用户的ticket,如果有则传, 如果没有则不传, 传是为了服务器验证此ticket是否有过期, 如果过期则会返回信息, 通知客户端清空此ticket,重新调用登录方法. 避免用户拿一个过期的ticket去游戏服务器端进行验证
    private String mac;
    private String language;
    private String device;
    private String imei;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "clientID='" + clientID + '\'' +
                ", time='" + time + '\'' +
                ", mac='" + mac + '\'' +
                ", language='" + language + '\'' +
                ", device='" + device + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
