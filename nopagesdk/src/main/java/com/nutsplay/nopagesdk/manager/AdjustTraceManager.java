package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAdRevenue;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustPlayStoreSubscription;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

/**
 * Created by frankma on 2021/6/9 3:13 PM
 * Email: frankma9103@gmail.com
 * Desc: Adjust追踪管理类,事件Token要随不同的游戏而重新进行配置
 *
 * 文档：
 * https://dev.adjust.com/zh/sdk/android/v4/configuration
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
            //收入事件去重
            adjustEvent.setDeduplicationId(orderId);
            Adjust.trackEvent(adjustEvent);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 第三方支付追踪
     * 这里指 Xsolla 网页版支付
     * @param context
     * @param revenue 金额
     * @param currency 货币
     * @param orderId
     */
    public void webStoreCheckOut(Activity context, double revenue, String currency, String orderId) {
        try {
            String eventToken = context.getResources().getString(SDKResUtils.getResId(context, "adjust_webStore_checkout", "string"));
            AdjustEvent adjustEvent = new AdjustEvent(eventToken);
            adjustEvent.setRevenue(revenue, currency);
            adjustEvent.setOrderId(orderId);

            //adjustEvent.addCallbackParameter();
//            收入事件去重
            adjustEvent.setDeduplicationId(orderId);
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

            // 设置用户标签
            // Adjust.setPushToken("token",context);
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
     *
     * 文档地址：
     * https://dev.adjust.com/zh/sdk/android/features/ad-revenue#send-ad-revenue
     *
     * @param source - 表明广告收入来源信息的 String 对象。
     * @param revenue - 收入额
     * @param currency- 币种的 ISO 4217 代码，由 3 个字符组成
     *
     */
    // TODO: 2025/12/19 接口修改
    public void adjustTrackAdRevenue(String source,double revenue,String currency){
        try{
            if (StringUtils.isEmpty(source)) return;
            AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue(source);
            adjustAdRevenue.setRevenue(revenue,currency);
            Adjust.trackAdRevenue(adjustAdRevenue);
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
