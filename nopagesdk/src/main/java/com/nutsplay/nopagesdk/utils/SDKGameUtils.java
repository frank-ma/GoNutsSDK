package com.nutsplay.nopagesdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

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


/**
 * Created by jackzpp on 2016/12/5
 * <p>
 * 努力做的更好
 */

public class SDKGameUtils {


    static boolean googleserviceFlag = true;
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

        boolean isMatch = account.matches("\\w{6,24}");
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

        boolean isMatch = pw.matches("\\w{6,24}");
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

        boolean isMatch = account.matches("\\w{6,24}");
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

        boolean isMatch = pw.matches("\\w{6,24}");
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

    public static void showServiceInfo(int message) {
        if (message == SDKConstant.status_account_exist) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("1"), 3);
        } else if (message == SDKConstant.status_account_do_not_exist) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("2"), 3);
        } else if (message == SDKConstant.status_password_error) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("3"), 3);
        } else if (message == SDKConstant.status_email_exist) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("5"), 3);
        } else if (message == SDKConstant.status_email_not_exist) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("4"), 3);
        } else if (message == SDKConstant.status_parameter_error) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("6"), 3);
        } else if (message == SDKConstant.status_ticket_invalid) {
            SDKToast.getInstance().ToastShow("ticket无效，请先登录", 3);
        } else if (message == SDKConstant.status_verify_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("6"), 3);
        } else if (message == SDKConstant.status_account_bound) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("7"), 3);
        } else if (message == SDKConstant.status_game_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("8"), 3);
        } else if (message == SDKConstant.status_user_frozen) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("9"), 3);
        } else if (message == SDKConstant.status_account_error) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("10"), 3);
        } else if (message == SDKConstant.status_email_not_fixed) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("11"), 3);
        } else if (message == SDKConstant.status_token_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("10"), 3);
        } else if (message == SDKConstant.status_email_format_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("12"), 3);
        } else if (message == SDKConstant.status_account_format_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("13"), 3);
        } else if (message == SDKConstant.status_passwd_format_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("15"), 3);
        } else if (message == SDKConstant.status_server_invalid) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_service_err"), 3);
        } else if (message == SDKConstant.status_pay_fail) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("14"), 3);
        } else if (message == SDKConstant.status_pay_balance_not_enough) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("18"), 3);
        } else if (message == SDKConstant.status_pay_save_to_balance) {
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("13"), 3);
        } else if (message == SDKConstant.status_pay_balance_plus_deposit_not_enough) {
            SDKToast.getInstance().ToastShow("nuts_service_err", 3);
        } else if (message == SDKConstant.status_deal_balance_fail) {
            SDKToast.getInstance().ToastShow("status_deal_balance_fail", 3);
        } else if (message == SDKConstant.status_production_do_not_exist) {
            SDKToast.getInstance().ToastShow("status_production_do_not_exist", 3);
        } else if (message == SDKConstant.status_card_used) {
            SDKToast.getInstance().ToastShow("status_card_used", 3);
        } else if (message == SDKConstant.status_card_or_pass_invalid) {
            SDKToast.getInstance().ToastShow("status_card_or_pass_invalid", 3);
        } else if (message == SDKConstant.status_account_has_real_name_authentication){
            SDKToast.getInstance().ToastShow("该账户已实名认证",3);
        }

    }

    public synchronized static int getLanguage(String langugae) {

        if (langugae.contains("cn")) {
            lan = 1;
        }
        if (langugae.contains("en")) {
            lan = 2;

        }

        if (langugae.contains("th")) {
            lan = 3;
        }
        if (langugae.contains("vn")) {
            lan = 4;
        }

        if (langugae.contains("ar")) {
            lan = 5;
        }
        if (langugae.contains("kr")) {
            lan = 6;
        }
        if (langugae.contains("hk")) {
            lan = 7;
        }

        if (langugae.contains("fr")) {
            lan = 8;
        }
        if (langugae.contains("br")) {
            lan = 9;
        }
        if (langugae.contains("de")) {
            lan = 10;
        }
        if (langugae.contains("sp")) {
            lan = 11;
        }
        if (langugae.contains("it")) {
            lan = 12;
        }
        if (langugae.contains("jp")) {
            lan = 13;
        }
        if (langugae.contains("idn")) {
            lan = 14;
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

    /**
     * 判断错误码信息
     *
     * @param errorCode
     */
    public static void judgeErrorCode(int errorCode) {

        if (errorCode <= -20) {

            if (errorCode == SDKConstant.status_pay_fail) {
                SDKToast.getInstance().ToastShow("pay fail ", 3);
            } else if (errorCode == SDKConstant.status_pay_balance_not_enough) {
                SDKToast.getInstance().ToastShow("pay balance not enough ", 3);
            } else if (errorCode == SDKConstant.status_pay_save_to_balance) {
                SDKToast.getInstance().ToastShow("pay save to balance ", 3);
            } else if (errorCode == SDKConstant.status_pay_balance_plus_deposit_not_enough) {
                SDKToast.getInstance().ToastShow("pay balance plus deposit not enough ", 3);
            } else if (errorCode == SDKConstant.status_deal_balance_fail) {
                SDKToast.getInstance().ToastShow("deal balance fail ", 3);
            } else if (errorCode == SDKConstant.status_production_do_not_exist) {
                SDKToast.getInstance().ToastShow("production do not exist ", 3);
            } else if (errorCode == SDKConstant.status_card_used) {
                SDKToast.getInstance().ToastShow("card used ", 3);
            } else if (errorCode == SDKConstant.status_card_or_pass_invalid) {
                SDKToast.getInstance().ToastShow("card or pass invalid ", 3);
            } else if (errorCode == SDKConstant.status_email_not_exist) {
                SDKToast.getInstance().ToastShow("email not exist ", 3);
            }

        } else {

            if (errorCode <= -10) {

                if (errorCode == SDKConstant.status_user_frozen) {
                    SDKToast.getInstance().ToastShow(" user frozen", 3);
                } else if (errorCode == SDKConstant.status_account_error) {
                    SDKToast.getInstance().ToastShow("account error ", 3);
                } else if (errorCode == SDKConstant.status_email_not_fixed) {
                    SDKToast.getInstance().ToastShow("email not fixed ", 3);
                } else if (errorCode == SDKConstant.status_token_invalid) {
                    SDKToast.getInstance().ToastShow("token invalid ", 3);
                } else if (errorCode == SDKConstant.status_email_format_invalid) {
                    SDKToast.getInstance().ToastShow("email format invalid ", 3);
                } else if (errorCode == SDKConstant.status_account_format_invalid) {
                    SDKToast.getInstance().ToastShow("account format invalid ", 3);
                } else if (errorCode == SDKConstant.status_passwd_format_invalid) {
                    SDKToast.getInstance().ToastShow("password format invalid ", 3);
                } else if (errorCode == SDKConstant.status_server_invalid) {
                    SDKToast.getInstance().ToastShow("server invalid ", 3);
                }

            } else {

                if (errorCode == SDKConstant.status_account_exist) {
                    SDKToast.getInstance().ToastShow("account exit", 3);
                } else if (errorCode == SDKConstant.status_account_do_not_exist) {
                    SDKToast.getInstance().ToastShow("account do not exit", 3);
                } else if (errorCode == SDKConstant.status_password_error) {
                    SDKToast.getInstance().ToastShow("password error", 3);
                } else if (errorCode == SDKConstant.status_email_exist) {
                    SDKToast.getInstance().ToastShow("email exit", 3);
                } else if (errorCode == SDKConstant.status_parameter_error) {
                    SDKToast.getInstance().ToastShow("parameter error", 3);
                } else if (errorCode == SDKConstant.status_ticket_invalid) {
                    SDKToast.getInstance().ToastShow("ticket invalid", 3);
                } else if (errorCode == SDKConstant.status_verify_invalid) {
                    SDKToast.getInstance().ToastShow("验证码无效", 3);
                } else if (errorCode == SDKConstant.status_account_bound) {
                    SDKToast.getInstance().ToastShow("account bound ", 3);
                } else if (errorCode == SDKConstant.status_game_invalid) {
                    SDKToast.getInstance().ToastShow("game invalid ", 3);
                }

            }
        }

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
}
