package com.nutsplay.nopagesdk.beans;

import java.io.Serializable;


/**
 * 初始化成功返回之对象
 *
 */
public class SDKInitModel implements Serializable {


    /**
     * code : 1
     * data : {"ID":"5d7f63a6e73f2146c4b1e731","code":"Pokemon","name":"宠物小精灵","icon":"","debug_mark":0,"story_mark":0,"policy_txt":"NO POLICY"}
     * message : "success"
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        /**
         * "ID":"5d7f63a6e73f2146c4b1e731","code":"Goblin","name":"Goblin's very busy","icon":"","debug_mark":0,"story_mark":0,"policy_txt":"NO POLICY"
         */

        private String ID;
        private String code;
        private String name;
        private String icon;
        private int debug_mark;
        private int story_mark;
        private String policy_txt;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getDebug_mark() {
            return debug_mark;
        }

        public void setDebug_mark(int debug_mark) {
            this.debug_mark = debug_mark;
        }

        public int getStory_mark() {
            return story_mark;
        }

        public void setStory_mark(int story_mark) {
            this.story_mark = story_mark;
        }

        public String getPolicy_txt() {
            return policy_txt;
        }

        public void setPolicy_txt(String policy_txt) {
            this.policy_txt = policy_txt;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "ID='" + ID + '\'' +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    ", debug_mark=" + debug_mark +
                    ", story_mark=" + story_mark +
                    ", policy_txt='" + policy_txt + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SDKInitModel{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
