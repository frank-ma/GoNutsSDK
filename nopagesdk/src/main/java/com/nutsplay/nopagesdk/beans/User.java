package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-22 23:31
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class User implements Serializable {

    private String userId;
    private String ticket;
    private String sdkmemberType="";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSdkmemberType() {
        return sdkmemberType;
    }

    public void setSdkmemberType(String sdkmemberType) {
        this.sdkmemberType = sdkmemberType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", ticket='" + ticket + '\'' +
                ", sdkmemberType='" + sdkmemberType + '\'' +
                '}';
    }
}
