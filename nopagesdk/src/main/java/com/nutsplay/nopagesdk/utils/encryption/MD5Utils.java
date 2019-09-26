package com.nutsplay.nopagesdk.utils.encryption;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by frank-ma on 2019/8/1 6:57 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class MD5Utils {

    public static String md5(String string) {
        if (string == null || TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
