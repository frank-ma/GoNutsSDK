package com.nutsplay.nopagesdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;


/**
 * Created by jackzpp on 2016/12/5
 * <p>
 * 努力做的更好
 */

public class SDKGameUtils {


    private static int lan;

    private static SDKGameUtils gameutils;


    public static SDKGameUtils getInstance() {
        if (gameutils == null) {
            gameutils = new SDKGameUtils();
        }

        return gameutils;
    }


    public static String mapToParamString(Map<String, Object> map) throws UnsupportedEncodingException {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            }
        }
        return sb.substring(1);
    }

    public static String mapToParamStringPost(Map<String, String> map) throws UnsupportedEncodingException {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            }
        }
        return sb.substring(1);
    }


    public static void FullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                .FLAG_FULLSCREEN);
    }


    /**
     * 判断邮箱格式
     *
     * @return
     */
    public static boolean matchMobleCode(String phone) {

        if (phone == null || phone.isEmpty()) {
            SDKToast.getInstance().ToastShow("请输入手机号", 3);
            return false;
        }

        boolean isMatch = phone.matches("\\b(^1(1|2|3|4|5|6|7|8|9|0)\\d{9}$$)\\b");

        if (!isMatch) {
            SDKToast.getInstance().ToastShow("手机号格式不正确", 3);

            return false;
        }
        return true;

    }


    /**
     * 判断邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean matchEmail(String email) {
        if (email.isEmpty()) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("email_null"), 3);
            return false;
        }

        boolean isMatch = email.matches("\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b");
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("36"), 3);

            return false;
        }
        return true;


    }

    /**
     * 判断用户名
     *
     * @return
     */
    public static boolean matchAccountReg(String account) {
        if (account.isEmpty() || "".equals(account)) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("38Reg"), 3);
            return false;
        }

        boolean isMatch = account.matches("^[A-Za-z0-9]{6,14}$");
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("38Reg"), 3);

            return false;
        }
        return true;

    }


    public static boolean matchPwReg(String pw) {
        if (pw.isEmpty() || "".equals(pw)) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("regspw"), 3);
            return false;
        }

        boolean isMatch = pw.matches("^[A-Za-z0-9]{6,14}$");
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("regspw"), 3);

            return false;
        }
        return true;
    }

    /**
     * 判断用户名
     *
     * @return
     */
    public static boolean matchAccount(String account) {
        if (account.isEmpty() || "".equals(account)) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_username_null"), 3);
            return false;
        }

        boolean isMatch = account.matches("^[A-Za-z0-9]{6,14}$");//旧的正则\\w{6,24}
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("38"), 3);

            return false;
        }
        return true;

    }

    /**
     * 判断用户名
     *
     * @return
     */
    public static boolean matchCode(String account) {
        if (account.isEmpty() || "".equals(account)) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("39"), 3);
            return false;
        }

        boolean isMatch = account.matches("\\w{4,24}");
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("40"), 3);

            return false;
        }
        return true;

    }

    /**
     * 实名制-验证身份证号码
     * @param IDNumber
     * @return
     */
    public static boolean matchIDCard(String IDNumber){
        if (IDNumber==null|| IDNumber.isEmpty()){
            SDKToast.getInstance().ToastShow("身份证号码不能为空", 3);
            return false;
        }
        boolean isMatch=IDNumber.matches("^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)");
        if (isMatch){
            return true;
        }else {
            SDKToast.getInstance().ToastShow("请输入正确的身份证号码", 3);
            return false;
        }
    }

    /**
     * 匹配实名制-真实姓名
     *
     * @param realName
     * @return
     */
    public static boolean matchRealName(String realName){
        if (realName==null||realName.isEmpty()){
            SDKToast.getInstance().ToastShow("姓名不能为空",3);
            return false;
        }
        boolean isMatch=realName.matches("^[\\u4e00-\\u9fa5]{2,8}");
        if (isMatch){
            return true;
        }else {
            SDKToast.getInstance().ToastShow("请输入2-8位的姓名", 3);
            return false;
        }
    }

    /**
     * 匹配实名制-手机号
     *
     * @param phoneNum
     * @return
     */
    public static boolean matchRealPhoneNumber(String phoneNum){
        if (phoneNum==null||phoneNum.isEmpty()){
            SDKToast.getInstance().ToastShow("手机号不能为空",3);
            return false;
        }
        boolean isMatch=phoneNum.matches("(^1(1|2|3|4|5|6|7|8|9|0)\\d{9}$)");
        if (isMatch){
            return true;
        }else {
            SDKToast.getInstance().ToastShow("请输入正确的手机号码", 3);
            return false;
        }

    }

    /**
     * 实名制认证
     * @return
     */
    public static boolean realNameVerify(String realName, String IDNumber, String phoneNum){
        if (matchRealName(realName) && matchIDCard(IDNumber) && matchRealPhoneNumber(phoneNum)){
            return true;
        }
        return false;
    }


    public static String getData() throws ParseException {
        // 创建日期对象
        Date d = new Date();
        // 给定模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // public final String format(Date date)
        String s = sdf.format(d);
        return s;
    }


    public static boolean matchPw(String pw) {
        if (pw.isEmpty() || "".equals(pw)) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("33"), 3);
            return false;
        }

        boolean isMatch = pw.matches("^[A-Za-z0-9]{6,14}$");//旧的正则\\w{6,24}
        if (!isMatch) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("41"), 3);

            return false;
        }
        return true;

    }


    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf).toUpperCase();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    /**
     * 错误码信息
     *
     * @param code
     * @param msg
     */
    public static void showServiceInfo(int code,String msg) {
        if (code == SDKConstant.STATUS_ACCOUNT_EXIST) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("1"), 3);
        } else if (code == SDKConstant.STATUS_ACCOUNT_DO_NOT_EXIST) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("2"), 3);
        } else if (code == SDKConstant.STATUS_PASSWORD_ERROR) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("3"), 3);
        } else if (code == SDKConstant.STATUS_EMAIL_EXIST) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("5"), 3);
        } else if (code == SDKConstant.STATUS_EMAIL_NOT_EXIST) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("4"), 3);
        } else if (code == SDKConstant.STATUS_PARAMETER_ERROR) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("6"), 3);
        } else if (code == SDKConstant.STATUS_TICKET_INVALID) {
            SDKToast.getInstance().ToastShow("ticket无效，请先登录", 3);
        } else if (code == SDKConstant.STATUS_VERIFY_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("6"), 3);
        } else if (code == SDKConstant.STATUS_ACCOUNT_BOUND) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("7"), 3);
        } else if (code == SDKConstant.STATUS_GAME_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("8"), 3);
        } else if (code == SDKConstant.STATUS_USER_FROZEN) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("9"), 3);
        } else if (code == SDKConstant.STATUS_ACCOUNT_ERROR) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("10"), 3);
        } else if (code == SDKConstant.STATUS_EMAIL_NOT_FIXED) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("11"), 3);
        } else if (code == SDKConstant.STATUS_EMAIL_FORMAT_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("12"), 3);
        } else if (code == SDKConstant.STATUS_ACCOUNT_FORMAT_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("13"), 3);
        } else if (code == SDKConstant.STATUS_PASSWD_FORMAT_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("15"), 3);
        } else if (code == SDKConstant.STATUS_SERVER_INVALID) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_service_err"), 3);
        } else if (code == SDKConstant.STATUS_PRODUCTION_DO_NOT_EXIST) {
            SDKToast.getInstance().ToastShow("status_production_do_not_exist", 3);
        } else if (code == SDKConstant.STATUS_THIRD_ACCOUNT_USED){
            SDKToast.getInstance().ToastShow("third account used",3);
        } else if (code == SDKConstant.STATUS_ANTIADDICTIVE_ALREADY_EXIST){
            SDKToast.getInstance().ToastShow("antiaddictive already exist",3);
        } else if (code == SDKConstant.STATUS_TEMPEMAILBIND_NOT_EXIST) {//临时绑定记录不存在
            SDKToast.getInstance().ToastShow("The binding record does not exist", 3);
        } else if (code == SDKConstant.STATUS_TEMPEMAILBIND_CODE_INVALID) {//临时绑定验证码不正确
            SDKToast.getInstance().ToastShow("Code invalid", 3);
        } else if (code == SDKConstant.STATUS_TEMPEMAILBIND_EMAIL_NOT_MATCH) {//邮箱不一致
            SDKToast.getInstance().ToastShow("Email not match", 3);
        } else if (code == SDKConstant.STATUS_TEMPEMAILBIND_SEND_EMAIL_FAIL) {//往邮箱发送邮件失败
            SDKToast.getInstance().ToastShow("Failed to send mail to mailbox", 3);
        } else if (code == SDKConstant.STATUS_ACCOUNT_NOT_BOUND) {//此ID尚未绑定过自定义账号(用于申请email绑定时)
            SDKToast.getInstance().ToastShow("Please bind an account before you can bind the mailbox, or directly bind your Facebook account.", 3);
        } else if (code == SDKConstant.STATUS_TEMPEMAILBIND_ALREADY_EXIST) {//临时绑定记录已存在
            SDKToast.getInstance().ToastShow("Email is bound", 3);
        } else {
            SDKToast.getInstance().ToastShow(msg, 3);
        }

    }

    public synchronized static int getLanguage(String langugae) {

        if (langugae.contains("cn")||langugae.contains("CN")) {
            lan = 1;
        }
        if (langugae.contains("en")) {
            lan = 2;
        }
        if (langugae.contains("th")) {
            lan = 3;
        }
        if (langugae.contains("vi")) {
            lan = 4;
        }
        if (langugae.contains("ar")) {
            lan = 5;
        }
        if (langugae.contains("kr")||langugae.contains("ko")) {
            lan = 6;
        }
        if (langugae.contains("hk")||langugae.contains("HK")) {
            lan = 7;
        }
        if (langugae.contains("fr")||langugae.contains("fo")) {
            lan = 8;
        }
        if (langugae.contains("br")||langugae.contains("pt")) {
            lan = 9;//葡萄牙
        }
        if (langugae.contains("de")) {
            lan = 10;
        }
        if (langugae.contains("sp")||langugae.contains("es")) {
            lan = 11;
        }
        if (langugae.contains("it")) {
            lan = 12;
        }
        if (langugae.contains("ja")||langugae.contains("jp")) {
            lan = 13;
        }
        if (langugae.contains("id")||langugae.contains("idn")) {
            lan = 14;
        }
        if (langugae.contains("ru")){
            lan = 15;
        }
        return lan;
    }


    public static String getStringLanguage(int langugae) {
        String lan = null;
        if (langugae == 1) {
            lan = "zh_cn";
        } else if (langugae == 2) {
            lan = "en_us";
        } else if (langugae == 3) {
            lan = "th_th";
        } else if (langugae == 4) {
            lan = "vi_vn";
        } else if (langugae == 5) {
            lan = "ar_ar";
        }
        return lan;
    }

    public static void getKeyHash(Context context) {
        try {
            if (context == null) return;
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (info == null ) return;
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "packageName:" + context.getPackageName() + " KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.d("KeyHash:", e.toString());
        } catch (Exception e){
            e.printStackTrace();
            Log.d("KeyHash:", e.toString());
        }
    }

    /**
     * 绑定账号dialog
     */
    public static void showBindAccountDialog(Context context) {

        if (Build.VERSION.SDK_INT >= 14){
            AlertDialog.Builder normalDialog = new AlertDialog.Builder(context,android.R.style.Theme_DeviceDefault_Light_Dialog);
            normalDialog.setTitle(SDKLangConfig.getInstance().findMessage("tourist_signin_tips"));
            normalDialog.setMessage(SDKLangConfig.getInstance().findMessage("tourist_signin_alert"));
            normalDialog.setPositiveButton(SDKLangConfig.getInstance().findMessage("viewstring_Bind_Account"),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            normalDialog.setNegativeButton(SDKLangConfig.getInstance().findMessage("viewstring_enter_game"),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            normalDialog.setCancelable(false);
            normalDialog.show();
        }

    }


    /**
     * 随机生成10位账号（由字母和数字组成，字母开头）
     * @return
     */
    public static String generate10Account() {
        String val = "";
        Random random = new Random();
        //产生首字母
        val += (char) (65 + random.nextInt(26));

        for (int i = 0; i < 9; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else  { // 产生数字
                String key= String.valueOf(random.nextInt(10));
                val += key;
            }
        }
        return val;
    }

    /**
     * 随机生成6位密码（由字母和数字组成，字母开头）
     * @return
     */
    public static String generate6Password() {
        String val = "";
        Random random = new Random();
        //产生首字母
        val += (char) (65 + random.nextInt(26));

        for (int i = 0; i < 5; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) (nextInt + random.nextInt(26));
            } else  { // 产生数字
                String key= String.valueOf(random.nextInt(10));
                val += key;
            }
        }
        return val;
    }
}
