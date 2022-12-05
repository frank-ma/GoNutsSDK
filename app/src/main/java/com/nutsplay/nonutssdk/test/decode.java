package com.nutsplay.nonutssdk.test;

public class decode {

    /**
     * 服务器端登录验证
     * 加密验证
     */
    public static void main(String[] args) {
        String clientID = "635f680c95b526b99391e7e7";
        String ticket = "80unt6j22s8f2ipp2x0u3yneclrnp5j1";
        String time = "1669219225610";
//        time = System.currentTimeMillis() + "";
        String encryptKey = "gubyiy8j76mvvbbqpfdbkq933qal2ogzpxyyza0j";

        String res = MD5Utils.md5(clientID+ticket+time+encryptKey);
        System.out.println(res);
    }
}
