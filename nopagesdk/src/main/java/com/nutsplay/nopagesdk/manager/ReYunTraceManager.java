package com.nutsplay.nopagesdk.manager;

import android.content.Context;
import android.util.Log;

import com.reyun.solar.engine.OnAttributionListener;
import com.reyun.solar.engine.OnInitializationCallback;
import com.reyun.solar.engine.SolarEngineConfig;
import com.reyun.solar.engine.SolarEngineManager;
import com.reyun.solar.engine.infos.SECustomEventModel;
import com.reyun.solar.engine.infos.SELoginEventModel;
import com.reyun.solar.engine.infos.SEOrderEventModel;
import com.reyun.solar.engine.infos.SEPurchaseEventModel;
import com.reyun.solar.engine.infos.SERegisterEventModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by frankma on 2025/1/6/ 7:33 PM
 * Email: frankma9103@gmail.com
 * Desc: ReYun追踪管理类
 *
 */
public class ReYunTraceManager {

    private static ReYunTraceManager INSTANCE;

    public static ReYunTraceManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ReYunTraceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReYunTraceManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 热云初始化
     * @param context
     * @param appKey
     */
    public void init(Context context, String appKey){
        SolarEngineConfig config = new SolarEngineConfig.Builder().build();
        config.setOnAttributionListener(new OnAttributionListener() {
            @Override
            public void onAttributionSuccess(JSONObject jsonObject) {
                //获取归因结果成功时执行的动作

            }

            @Override
            public void onAttributionFail(int i) {
                //获取归因结果失败时执行的动作

            }
        });
        SolarEngineManager.getInstance().initialize(context, appKey,config, new OnInitializationCallback() {
            @Override
            public void onInitializationCompleted(int code) {
                if(code == 0) {
                    //初始化成功
                    Log.d("reyun","初始化成功");
                } else {
                    //初始化失败，具体失败原因参考下方code码释义
                    Log.d("reyun","初始化失败"+code);
                }
            }
        });

        Log.d("reyun","DistinctId:"+SolarEngineManager.getInstance().getDistinctId());
    }

    public void init(Context context, String appKey, boolean isDebugModel){
        SolarEngineConfig config = new SolarEngineConfig.Builder()
                .isDebugModel(isDebugModel)
                .build();
        config.setOnAttributionListener(new OnAttributionListener() {
            @Override
            public void onAttributionSuccess(JSONObject jsonObject) {
                //获取归因结果成功时执行的动作
                Log.d("reyun",jsonObject.toString());
            }

            @Override
            public void onAttributionFail(int i) {
                //获取归因结果失败时执行的动作
                Log.d("reyun",i+"");
            }
        });
        SolarEngineManager.getInstance().initialize(context, appKey,config, new OnInitializationCallback() {
            @Override
            public void onInitializationCompleted(int code) {
                if(code == 0) {
                    //初始化成功
                    Log.d("reyun","初始化成功");
                } else {
                    //初始化失败，具体失败原因参考下方code码释义
                    Log.d("reyun","初始化失败"+code);
                }
            }
        });
        Log.d("reyun","DistinctId:"+SolarEngineManager.getInstance().getDistinctId());

    }

    /**
     * 用户产生订单时上报
     * @param revenue
     * @param currency
     * @param orderId
     */
    public void makeOrderTrack(double revenue, String currency, String orderId,String orderState){
        try {
            JSONObject customProperties = new JSONObject();

            //开发者自定义Json格式上报事件属性
//            customProperties.put("key1", "value1");
//            customProperties.put("key2", "value2");
            SEOrderEventModel seOrderEventModel = new SEOrderEventModel(orderId, revenue, currency, "GooglePlay", orderState, customProperties);
            SolarEngineManager.getInstance().trackOrder(seOrderEventModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Google内购追踪
     *
     * @param revenue
     */
    public void googleIapTrack(double revenue, String currency, String orderId,String skuId,String skuName) {
        try {
            JSONObject customProperties = new JSONObject();
            //开发者自定义Json格式上报事件属性
//            customProperties.put("key1", "value1");
//            customProperties.put("key2", "value2");

            SEPurchaseEventModel sePurchaseEventModel = new SEPurchaseEventModel(orderId, revenue, currency, "GooglePlay", skuId, skuName, 1, 1, "", customProperties);
            SolarEngineManager.getInstance().trackPurchase(sePurchaseEventModel);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * @param loginType  登录类型
     * @param loginState 登录状态
     */
    public void loginTrack(String userId,String loginType,String loginState) {
        try {
            JSONObject customProperties = new JSONObject();
            //开发者自定义Json格式上报事件属性
//                customProperties.put("key1","value1");
//                customProperties.put("key2","value2");
            SELoginEventModel seLoginEventModel = new SELoginEventModel(loginType,loginState,customProperties);
            SolarEngineManager.getInstance().trackAppLogin(seLoginEventModel);

            //设置 userID
            SolarEngineManager.getInstance().login(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通知 SDK 清除账号 ID
     */
    public void logout(){
        SolarEngineManager.getInstance().logout();
    }

    /**
     * 注册新用户
     * @param regType  注册类型
     * @param regState 注册状态
     */
    public void registerTrack(String regType, String regState) {
        try {
            JSONObject customProperties = new JSONObject();
            //开发者自定义Json格式上报事件属性
//            customProperties.put("key1", "value1");
//            customProperties.put("key2", "value2");

            SERegisterEventModel seRegisterEventModel = new SERegisterEventModel(regType, regState, customProperties);
            SolarEngineManager.getInstance().trackAppRegister(seRegisterEventModel);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    {
        JSONObject customEventData = new JSONObject();
        try {
            //开发者自定义Json格式上报事件属性
            customEventData.put("key1","value1");
            customEventData.put("key2","value2");
        } catch (JSONException e) {
///
        }
        ReYunTraceManager.getInstance().customEvent("eventName",customEventData);
    }



    /**
     * 热云自定义事件
     * @param eventName 事件名
     * @param customEventData  事件属性
     */
    public void customEvent(String eventName,JSONObject customEventData){
        try {
            JSONObject preEventData = new JSONObject();
            //开发者自定义Json格式上报事件预置属性
//            preEventData.put("_currency_type", "USD");
//            preEventData.put("_pay_amount", 11.2);
            SECustomEventModel seCustomEventModel = new SECustomEventModel(eventName, preEventData, customEventData);
            SolarEngineManager.getInstance().track(seCustomEventModel);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
