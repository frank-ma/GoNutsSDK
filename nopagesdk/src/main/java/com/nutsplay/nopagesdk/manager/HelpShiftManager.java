package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.nutsplay.nopagesdk.beans.User;

import java.util.Map;

/**
 * Created by frank-ma on 2018/12/28 下午9:23
 * Email: frankma9103@gmail.com
 * Desc: HELPSHIFT 客服系统
 */
public class HelpShiftManager {


    /**
     * 初始化HelpShift客服系统
     */
    public static void showConversation(Activity activity,Map<String, Object> config) {

        try {
            // set tags for tracking
//            config.put("tags", new String[]{"foo", "bar"});

            // set custom issue fields
//            Map<String, Object> cifMap = new HashMap<>();
//            Map<String, String> isPro = new HashMap<>();
//            isPro.put("type", "boolean");
//            isPro.put("value", "true");
//            cifMap.put("is_pro", isPro);
//            config.put("cifs", cifMap);

            // pass the config map in the api
//            Helpshift.showConversation(activity, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * FAQ
     * @param  configMap
     */
    public static void showFAQs(Activity activity,Map<String, Object> configMap) {
        try {
//            configMap.put("enableContactUs", "AFTER_VIEWING_FAQS");
//            Helpshift.showFAQs(activity, configMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置语言
     * @param lan
     */
    public static void setHelpShiftLan(String lan){
//        try{
//            if (StringUtils.isEmpty(lan)) return;
//            Helpshift.setLanguage(SDKGameUtils.getHelpShiftLanguageAlia(lan));
//        }catch (Exception e){
//            SDKManager.getInstance().sdkUploadLog("9",e.getMessage());
//            e.printStackTrace();
//        }finally {
//            SDKManager.getInstance().sdkUploadLog("10","setHelpShiftLan()");
//        }
    }

    /**
     * 设置用户信息
     */
    public static void setUser(User user){
        try {
//            if (user == null || user.getUserId().isEmpty()) return;
//            Map<String, String> userData = new HashMap<>();
//            userData.put("userId", user.getUserId());
//            userData.put("userEmail", user.getBindEmail());
//            userData.put("userName", user.getUserName());
//            userData.put("userAuthToken", user.getTicket());
//            Helpshift.login(userData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void logout(){
//        Helpshift.logout();
    }
}
