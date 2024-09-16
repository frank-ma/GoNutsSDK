package com.nutsplay.nopagesdk.network;

import android.util.Base64;
import android.util.Log;

/**
 * Created by frank-ma on 2019/7/24 4:18 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class NetUtils {

    /**
     * 从服务器拿到的结果，先要进行URLDecode解码，再进行Base64解码
     *
     * @param source
     * @return
     */
    public static String decode(String source){
        if (source==null || source.isEmpty())return "";

//        try {
//            String urlDecode = URLDecoder.decode(source,"utf-8");
//            System.out.println("公钥URLDecoder转码之后===========\n"+urlDecode);
//            byte[] encrypted1 = Base64.decode(urlDecode, Base64.DEFAULT);// 先用base64解密
//            String base64Result=new String(encrypted1,"utf-8");
//            System.out.println("公钥base64转码之后===========\n"+base64Result);
//            return base64Result;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String result="";
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            byte[] base64Result = java.util.Base64.getUrlDecoder().decode(source);
//            try {
//                result =new String(base64Result,"utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }


        String result="";
        byte[] base64Result = Base64.decode(source, Base64.DEFAULT);// 先用base64解密
        try {
            result = new String(base64Result,"utf-8");
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decode1(String source){
        if (source==null || source.isEmpty())return new byte[]{};

//        try {
//            String urlDecode = URLDecoder.decode(source,"utf-8");
//            System.out.println("公钥URLDecoder转码之后===========\n"+urlDecode);
//            byte[] encrypted1 = Base64.decode(urlDecode, Base64.DEFAULT);// 先用base64解密
//            String base64Result=new String(encrypted1,"utf-8");
//            System.out.println("公钥base64转码之后===========\n"+base64Result);
//            return base64Result;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            byte[] base64Result = java.util.Base64.getUrlDecoder().decode(source);
            return base64Result;
        }
        return new byte[]{};
    }

    /**
     * 上传服务器的数据，先进行base64加密，再进行URLEncode加密
     *
     * @param source
     * @return
     */
//    public static String encode(String source){
//        if (source==null || source.isEmpty())return "";
//
//        try {
//            String base64Result = Base64.encodeToString(source.getBytes(),Base64.DEFAULT);
//            System.out.println("base64加密之后===========\n"+base64Result);
//
//            String urlEncode = URLEncoder.encode(base64Result,"utf-8");
//            System.out.println("URLDecoder加密之后===========\n"+urlEncode);
//            return urlEncode;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    public static String encode(byte[] source){
        if (source==null)return "";

        try {

            String base64Result = "";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                base64Result = java.util.Base64.getEncoder().encodeToString(source);
                Log.e("encode","getEncoder==========="+base64Result);
            }

            return base64Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
