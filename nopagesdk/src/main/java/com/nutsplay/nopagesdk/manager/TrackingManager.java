package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

import java.util.Map;

/**
 * Created by frank-ma on 2019/6/29 12:51 PM
 * Email: frankma9103@gmail.com
 * Desc: 追踪管理类，appsflyer和DE都写在这里面
 * 替换appsflyer,以后都改用adjust做广告效果统计
 */
public class TrackingManager {

    /**
     * 事件追踪初始化
     *
     * @param application
     */
    public static void trackingInit(Application application,String afKey){

        //AF初始化
//        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
//            @Override
//            public void onConversionDataSuccess(Map<String, Object> map) {
//                for (String attrName : map.keySet()) {
//                    LogUtils.d("AppsFlyer", "attribute: " + attrName + " = " + map.get(attrName));
//                }
//            }
//
//            @Override
//            public void onConversionDataFail(String s) {
//                LogUtils.d("AppsFlyer", "error getting conversion data: " + s);
//            }
//
//            @Override
//            public void onAppOpenAttribution(Map<String, String> map) {
//                for (String attrName : map.keySet()) {
//                    LogUtils.d("AppsFlyer", "attribute: " + attrName + " = " + map.get(attrName));
//                }
//            }
//
//            @Override
//            public void onAttributionFailure(String s) {
//                LogUtils.d("AppsFlyer", "error onAttributionFailure : " + s);
//            }
//        };
//
//        AppsFlyerLib.getInstance().init(afKey,conversionListener,application.getApplicationContext());
//        AppsFlyerLib.getInstance().startTracking(application);

    }

    /**
     * 注册账号追踪
     * @param accountId
     */
    public static void registerTracking(String accountId){
        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("registerok"),1);

//        Map<String, Object> eventValue = new HashMap<>();
//        eventValue.put("accountId", accountId);
//        AppsFlyerLib.getInstance().trackEvent(SDKManager.getInstance().getActivity(), "Register", eventValue);
        GooglePayHelp.getInstance().queryPurchase(false, SDKConstant.INAPP);

        //adjust注册账号追踪
        AdjustTraceManager.getInstance().adjustRegister(SDKManager.getInstance().getActivity());
    }

    /**
     * 登录追踪
     * @param accountId
     */
    public static void loginTracking(String accountId){

        try {
            SDKManager.getInstance().setAuto(true);
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("loginok"),1);


//            Map<String, Object> eventValue = new HashMap<>();
//            eventValue.put("accountId", accountId);
//            AppsFlyerLib.getInstance().trackEvent(SDKManager.getInstance().getActivity(), "Login", eventValue);

            //adjust追踪
            AdjustTraceManager.getInstance().adjustLogin(SDKManager.getInstance().getActivity());

            //登录成功之后检查掉单
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SDKManager.getInstance().checkLostOrder(SDKManager.getInstance().getActivity());
                }
            }, 5000);



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 创建角色追踪
     * @param context
     * @param serverId
     * @param roleId
     * @param roleName
     */
    public static void createRoleTracking(Context context,String serverId,String roleId,String roleName){
        try {
//            Map<String, Object> eventValue = new HashMap<>();
//            eventValue.put("Game_ServiceId", serverId);
//            eventValue.put("Game_RoleId", roleId);
//            eventValue.put("Game_RoleName", roleName);

//            AppsFlyerLib.getInstance().trackEvent(context, "create_role", eventValue);
            //DE注意：自定义效果点必须先在平台上创建,每个APP只支持15个自定义效果点。创建方法：登录广告效果监测平台,进入对应的APP,在菜单 投放管理->效果点管理 中创建。
//            DCTrackingPoint.setEffectPoint("create_role",eventValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付追踪
     * @param accountId
     * @param orderId
     * @param currencyAmount
     * @param currencyType
     * @param payType
     */
    public static void purchaseTracking(Context context,String itemType,String accountId, String orderId, double currencyAmount, String currencyType, String payType){

        //AF追踪
//        Map<String, Object> eventValues = new HashMap<>();
//        eventValues.put(AFInAppEventParameterName.REVENUE, currencyAmount);
//        eventValues.put(AFInAppEventParameterName.CURRENCY, currencyType);
//        eventValues.put(AFInAppEventParameterName.CONTENT_ID, orderId);
//        eventValues.put(AFInAppEventParameterName.CONTENT_TYPE, payType);
//        AppsFlyerLib.getInstance().trackEvent(context, AFInAppEventType.PURCHASE, eventValues);
//        AppsFlyerLib.getInstance().trackEvent(context, "NutsGooglePay", eventValues);
        //dataEye追踪
//        DCTrackingPoint.paymentSuccess(accountId, orderId, currencyAmount, currencyType, payType);
        //Adjust
        if (itemType.equals(SDKConstant.INAPP)){
            AdjustTraceManager.getInstance().googleIap((Activity) context,currencyAmount,currencyType,orderId);
        }
    }

    /**
     * 公共的事件追踪方法
     * @param context
     * @param eventType
     * @param eventValue
     */
    public static void EventTracking(Context context, String eventType, Map<String, Object> eventValue) {
        try {
            if (context!=null && eventType!=null && eventValue!=null){
//                AppsFlyerLib.getInstance().trackEvent(context.getApplicationContext(), eventType, eventValue);
                //DE注意：自定义效果点必须先在平台上创建,每个APP只支持15个自定义效果点。创建方法：登录广告效果监测平台,进入对应的APP,在菜单 投放管理->效果点管理 中创建。
//                DCTrackingPoint.setEffectPoint(eventType,eventValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
