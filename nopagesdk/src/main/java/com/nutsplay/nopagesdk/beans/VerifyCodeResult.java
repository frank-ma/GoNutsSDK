package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2020-04-17 16:27
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class VerifyCodeResult implements Serializable {


    /**
     * code : 1
     * message : success
     * data : {"ID":"5e99673b64b5b52d6e153cc6","passport_id":"5e995aa964b5b52d6e153cc3","email_address":"1393981975@qq.com","bind_time":"2020-04-17T01:22:19.986554313-07:00","random_code":"878816","status":0}
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
         * ID : 5e99673b64b5b52d6e153cc6
         * passport_id : 5e995aa964b5b52d6e153cc3
         * email_address : 1393981975@qq.com
         * bind_time : 2020-04-17T01:22:19.986554313-07:00
         * random_code : 878816
         * status : 0
         */

        private String ID;
        private String passport_id;
        private String email_address;
        private String bind_time;
        private String random_code;
        private int status;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPassport_id() {
            return passport_id;
        }

        public void setPassport_id(String passport_id) {
            this.passport_id = passport_id;
        }

        public String getEmail_address() {
            return email_address;
        }

        public void setEmail_address(String email_address) {
            this.email_address = email_address;
        }

        public String getBind_time() {
            return bind_time;
        }

        public void setBind_time(String bind_time) {
            this.bind_time = bind_time;
        }

        public String getRandom_code() {
            return random_code;
        }

        public void setRandom_code(String random_code) {
            this.random_code = random_code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
