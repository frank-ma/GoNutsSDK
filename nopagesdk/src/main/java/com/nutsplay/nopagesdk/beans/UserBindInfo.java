package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2022/6/9 11:13 上午
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class UserBindInfo implements Serializable {

    /**
     * code : 1
     * message : STATUS_SUCCESS
     * data : {"passportId":"6295e77895b5268bb09da19c","BindAccount":"9d6af9ce-89c2-3d90-aa3b-29e95407e9e8@android","bindEmail":"","bindFacebook":false,"bindApple":false,"bindGoogle":false,"bindiosTourist":false,"BindandroidTourist":true}
     */

    private int code;
    private String message;
    private DataBean data;

    public static class DataBean implements Serializable {
        /**
         * passportId : 6295e77895b5268bb09da19c
         * BindAccount : 9d6af9ce-89c2-3d90-aa3b-29e95407e9e8@android
         * bindEmail :
         * bindFacebook : false
         * bindApple : false
         * bindGoogle : false
         * bindiosTourist : false
         * BindandroidTourist : true
         */

        private String passportId;
        private String BindAccount;
        private String bindEmail;
        private boolean bindFacebook;
        private boolean bindApple;
        private boolean bindGoogle;
        private boolean bindiosTourist;
        private boolean BindandroidTourist;

        public String getPassportId() {
            return passportId;
        }

        public void setPassportId(String passportId) {
            this.passportId = passportId;
        }

        public String getBindAccount() {
            return BindAccount;
        }

        public void setBindAccount(String bindAccount) {
            BindAccount = bindAccount;
        }

        public String getBindEmail() {
            return bindEmail;
        }

        public void setBindEmail(String bindEmail) {
            this.bindEmail = bindEmail;
        }

        public boolean isBindFacebook() {
            return bindFacebook;
        }

        public void setBindFacebook(boolean bindFacebook) {
            this.bindFacebook = bindFacebook;
        }

        public boolean isBindApple() {
            return bindApple;
        }

        public void setBindApple(boolean bindApple) {
            this.bindApple = bindApple;
        }

        public boolean isBindGoogle() {
            return bindGoogle;
        }

        public void setBindGoogle(boolean bindGoogle) {
            this.bindGoogle = bindGoogle;
        }

        public boolean isBindiosTourist() {
            return bindiosTourist;
        }

        public void setBindiosTourist(boolean bindiosTourist) {
            this.bindiosTourist = bindiosTourist;
        }

        public boolean isBindandroidTourist() {
            return BindandroidTourist;
        }

        public void setBindandroidTourist(boolean bindandroidTourist) {
            BindandroidTourist = bindandroidTourist;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "passportId='" + passportId + '\'' +
                    ", BindAccount='" + BindAccount + '\'' +
                    ", bindEmail='" + bindEmail + '\'' +
                    ", bindFacebook=" + bindFacebook +
                    ", bindApple=" + bindApple +
                    ", bindGoogle=" + bindGoogle +
                    ", bindiosTourist=" + bindiosTourist +
                    ", BindandroidTourist=" + BindandroidTourist +
                    '}';
        }
    }


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

    @Override
    public String toString() {
        return "UserBindInfo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
