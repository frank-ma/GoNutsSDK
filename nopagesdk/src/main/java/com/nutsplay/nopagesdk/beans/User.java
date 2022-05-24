package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-22 23:31
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class User implements Serializable {

    private String userId="";
    private String ticket="";
    private String sdkmemberType="";
    private String userName="";//账号登录的时候的用户名
    private String facebookName="";//玩家用Facebook登录时的Facebook昵称
    private String facebookId="";//FacebookID
    private String facebookPortrait="";//Facebook头像
    private String facebookEmail="";//Facebook邮箱
    private String bindEmail="";//绑定的邮箱


    public String getUserId() {
        return userId;
    }

    /**
     * 不要返回userid给游戏方，只返回ticket，让他们拿着ticket去坚果服务器验证
     *
     * @param userId
     */
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSdkmemberType(String sdkmemberType) {
        this.sdkmemberType = sdkmemberType;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookPortrait() {
        return facebookPortrait;
    }

    public void setFacebookPortrait(String facebookPortrait) {
        this.facebookPortrait = facebookPortrait;
    }

    public String getFacebookEmail() {
        return facebookEmail;
    }

    public void setFacebookEmail(String facebookEmail) {
        this.facebookEmail = facebookEmail;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", ticket='" + ticket + '\'' +
                ", sdkmemberType='" + sdkmemberType + '\'' +
                ", userName='" + userName + '\'' +
                ", facebookName='" + facebookName + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", facebookPortrait='" + facebookPortrait + '\'' +
                ", facebookEmail='" + facebookEmail + '\'' +
                ", bindEmail='" + bindEmail + '\'' +
                '}';
    }
}
