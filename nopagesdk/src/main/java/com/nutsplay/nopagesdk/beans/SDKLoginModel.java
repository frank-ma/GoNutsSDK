package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;

/**
 * Created by frankma on 2019-09-23 19:51
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKLoginModel implements Serializable {


    /**
     * code : 1
     * message : success
     * data : {"passportId":"5d88be7364b5b5517c7ee173","clientId":"5d7f63a6e73f2146c4b1e731","ticket":"tp8fy8vcp42a7tqyjgehad7i12b64j91","expire":"2019-09-23T05:45:39.102-07:00"}
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

    public static class DataBean implements Serializable{
        /**
         * passportId : 5d88be7364b5b5517c7ee173
         * clientId : 5d7f63a6e73f2146c4b1e731
         * ticket : tp8fy8vcp42a7tqyjgehad7i12b64j91
         * expire : 2019-09-23T05:45:39.102-07:00
         */

        private String passportId;//就是现在的userid
        private String clientId;
        private String ticket;//就是以前的ticket，直接返回给CP
        private String expire;
        private String bindEmail;//绑定的邮箱

        public String getPassportId() {
            return passportId;
        }

        public void setPassportId(String passportId) {
            this.passportId = passportId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getBindEmail() {
            return bindEmail;
        }

        public void setBindEmail(String bindEmail) {
            this.bindEmail = bindEmail;
        }
    }
}
