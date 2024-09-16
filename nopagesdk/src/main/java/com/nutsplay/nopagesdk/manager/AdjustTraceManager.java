package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustPlayStoreSubscription;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

import org.json.JSONObject;

/**
 * Created by frankma on 2021/6/9 3:13 PM
 * Email: frankma9103@gmail.com
 * Desc: Adjust追踪管理类,事件Token要随不同的游戏而重新进行配置
 *
 */
public class AdjustTraceManager {

    private static AdjustTraceManager INSTANCE;

    public static AdjustTraceManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AdjustTraceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdjustTraceManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Google内购追踪
     * 84d7kd
     * @param revenue
     */
    public void googleIap(Activity context, double revenue, String currency, String orderId) {
        try {
            String eventToken = context.getResources().getString(SDKResUtils.getResId(context, "adjust_google_iap", "string"));
            AdjustEvent adjustEvent = new AdjustEvent(eventToken);
            adjustEvent.setRevenue(revenue, currency);
            adjustEvent.setOrderId(orderId);
            Adjust.trackEvent(adjustEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * ockst9
     */
    public void adjustLogin(Activity context) {
        try {
            String eventToken = context.getResources().getString(SDKResUtils.getResId(context, "adjust_login", "string"));
            AdjustEvent adjustEvent = new AdjustEvent(eventToken);
            Adjust.trackEvent(adjustEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 注册新用户
     * f9kveh
     */
    public void adjustRegister(Activity context) {
        try {
            String eventToken = context.getResources().getString(SDKResUtils.getResId(context, "adjust_register", "string"));
            AdjustEvent adjustEvent = new AdjustEvent(eventToken);
            Adjust.trackEvent(adjustEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Adjust自定义事件
     * @param eventID
     */
    public void adjustCustomEvent(String eventID){
        try {
            if (StringUtils.isEmpty(eventID)) return;
            AdjustEvent adjustEvent = new AdjustEvent(eventID);
            Adjust.trackEvent(adjustEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 广告收入跟踪
     * @param source - 表明广告收入来源信息的 String 对象。
     * @param payload - 包含广告收入 JSON 的 JSONObject 对象。
     */
    public void adjustTrackAdRevenue(String source, JSONObject payload){
        try{
            if (StringUtils.isEmpty(source) || payload == null) return;
            Adjust.trackAdRevenue(source,payload);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 追踪订阅
     * @param price
     * @param currency
     * @param sku
     * @param orderId
     * @param signature
     * @param purchaseToken
     */
    public void adjustTrackSubs(double price,String currency,String sku,String orderId,String signature,String purchaseToken){
        try {
            AdjustPlayStoreSubscription subscription = new AdjustPlayStoreSubscription(
                    (long) price,
                    currency,
                    sku,
                    orderId,
                    signature,
                    purchaseToken);
            subscription.setPurchaseTime(System.currentTimeMillis());
            Adjust.trackPlayStoreSubscription(subscription);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
