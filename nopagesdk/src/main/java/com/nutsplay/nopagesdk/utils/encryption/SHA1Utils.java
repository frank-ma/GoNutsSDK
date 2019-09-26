package com.nutsplay.nopagesdk.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by frankma on 2019-09-23 19:40
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SHA1Utils {

    /**
     * sha1加密
     * @param decript
     * @return
     */
    public static String sha1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * sha1加密
     * @param decript
     * @return
     */
    public static String sha1UpperCase(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
