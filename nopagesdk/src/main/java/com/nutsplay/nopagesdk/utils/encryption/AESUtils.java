package com.nutsplay.nopagesdk.utils.encryption;

import android.os.Build;
import android.util.Base64;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by frank-ma on 2019/7/22 7:03 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class AESUtils {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String sKey;
    private String ivParameter = "1234567890123456";
    private static AESUtils instance = null;

    private AESUtils(String sKey) {
        this.sKey = sKey;
    }

    public static AESUtils getInstance(String sKey) {
        if (instance == null)
            instance = new AESUtils(sKey);
        return instance;
    }

    /**
     * 随机生成16位密码（由字母和数字组成，如果首位为0，则换成1）
     * @return
     */
    public static String generate16SecretKey() {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else  { // 产生数字
                String key= String.valueOf(random.nextInt(10));
                if (i == 0 && key.equals("0")){
                    key="1";
                }
                val += key;
            }
        }
        return val;
    }

    /**
     * 生成随机数当作getItemID
     * n ： 需要的长度
     *
     * @return
     */
    private static String getItemID(int n) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    // 加密
    public static String encrypt(String encData, String secretKey) throws Exception {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码。（处于android.util包）
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            return java.util.Base64.getEncoder().encodeToString(encrypted);// 此处使用BASE64做转码。
//        }
//        return "";
    }

//    public static String encrypt(String encData, String secretKey, String vector) throws Exception {
//
//        if (secretKey == null) {
//            return null;
//        }
//        if (secretKey.length() != 16) {
//            return null;
//        }
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        byte[] raw = secretKey.getBytes();
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
//        return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码。（处于android.util包）
//    }


    // 加密
    public String encode(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
//        return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return java.util.Base64.getEncoder().encodeToString(encrypted);// 此处使用BASE64做转码。
        }
        return "";
    }

    // 解密
    public String decode(String sSrc) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public String decodeNoBase64(String sSrc) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(sSrc.getBytes());
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    // 解密
    public static String decrypt(String sSrc, String key) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

}
