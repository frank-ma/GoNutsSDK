//package com.nutsplay.nopagesdk.manager;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.nutsplay.nopagesdk.beans.PayResult;
//import com.nutsplay.nopagesdk.beans.SDKOrderModel;
//import com.nutsplay.nopagesdk.callback.NetCallBack;
//import com.nutsplay.nopagesdk.callback.SDKGetMiPaySkuDetailsCallback;
//import com.nutsplay.nopagesdk.kernel.SDKConstant;
//import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
//import com.nutsplay.nopagesdk.kernel.SDKManager;
//import com.nutsplay.nopagesdk.network.GsonUtils;
//import com.nutsplay.nopagesdk.utils.SDKGameUtils;
//import com.nutsplay.nopagesdk.utils.encryption.AESUtils;
//import com.nutsplay.nopagesdk.utils.encryption.RSAUtils;
//import com.nutsplay.nopagesdk.utils.sputil.SPKey;
//import com.nutsplay.nopagesdk.utils.sputil.SPManager;
//import com.nutsplay.nopagesdk.utils.toast.SDKToast;
//import com.nutspower.commonlibrary.utils.LogUtils;
//import com.xiaomi.billingclient.api.BillingClient;
//import com.xiaomi.billingclient.api.BillingClientStateListener;
//import com.xiaomi.billingclient.api.BillingFlowParams;
//import com.xiaomi.billingclient.api.BillingResult;
//import com.xiaomi.billingclient.api.ConsumeResponseListener;
//import com.xiaomi.billingclient.api.Purchase;
//import com.xiaomi.billingclient.api.PurchasesResponseListener;
//import com.xiaomi.billingclient.api.PurchasesUpdatedListener;
//import com.xiaomi.billingclient.api.SkuDetails;
//import com.xiaomi.billingclient.api.SkuDetailsParams;
//import com.xiaomi.billingclient.api.SkuDetailsResponseListener;
//
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by frankma on 2022/10/25 10:18 上午
// * Email: frankma9103@gmail.com
// * Desc: （背景：由于国际社会制裁俄罗斯，谷歌停用了俄罗斯用户的内购功能）
// * 该版本用于小米国际版支付,
// * 主要用于俄罗斯用户的内购功能
// *
// */
//public class MiPayManager implements PurchasesUpdatedListener {
//
//    private static final String TAG = "MiPayManager";
//    private volatile static MiPayManager INSTANCE;
//    private BillingClient billingClient;
//    private boolean isConnected = false;
//
//    public static MiPayManager getInstance() {
//        if (INSTANCE == null) {
//            synchronized (MiPayManager.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new MiPayManager();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public boolean isConnected() {
//        return isConnected;
//    }
//
//    public void setConnected(boolean connected) {
//        isConnected = connected;
//    }
//
//    /**
//     * 初始化小米支付库
//     * @param activity
//     */
//    public void initMiPay(Activity activity,String skuId,String transactionId){
//        if (billingClient == null){
//            billingClient = BillingClient.newBuilder(activity).setListener(this).build();
//        }
//        //切换横竖屏
//        billingClient.setScreenOrientation(BillingClient.OrientationType.LANDSCAPE);
//        //建立服务连接
//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingServiceDisconnected() {
//                setConnected(false);
//                LogUtils.d(TAG,"onBillingServiceDisconnected");
//            }
//
//            @Override
//            public void onBillingSetupFinished(BillingResult billingResult) {
//                LogUtils.d(TAG, "MiPay 初始化，Service.code : " + billingResult.getResponseCode() + "\tmsg : " + billingResult.getDebugMessage());
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
//                    //建立连接成功
//                    setConnected(true);
//                    String userId = SDKManager.getInstance().getUser().getUserId();
//                    launchBillingMiPay(activity,skuId,userId,transactionId);
//
//                }else {
//                    SDKToast.getInstance().ToastShow(billingResult.getDebugMessage(), 2);
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 初始化小米支付
//     * @param context
//     * @param billingClientStateListener
//     */
//    public void initMiPay(Context context,BillingClientStateListener billingClientStateListener){
//        if (billingClient == null){
//            billingClient = BillingClient.newBuilder(context).setListener(this).build();
//        }
//        //切换横竖屏
//        billingClient.setScreenOrientation(BillingClient.OrientationType.LANDSCAPE);
//        //建立服务连接
//        billingClient.startConnection(billingClientStateListener);
//    }
//
//    /**
//     * 查询商品的当地价格信息
//     * @param skuList
//     * @param callback
//     */
//    public void querySkuDetails(Activity activity,List<String> skuList, String skuType,SDKGetMiPaySkuDetailsCallback callback){
//        if (skuList == null || skuList.size() == 0 || callback == null) return;
//        if (!isConnected() || billingClient == null) {
//
//            initMiPay(activity, new BillingClientStateListener() {
//                @Override
//                public void onBillingServiceDisconnected() {
//                    LogUtils.d(TAG,"onBillingServiceDisconnected");
//                }
//
//                @Override
//                public void onBillingSetupFinished(BillingResult billingResult) {
//                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
//                        setConnected(true);
//                        querySkuDetails(activity,skuList,skuType,callback);
//                    } else {
//                        callback.onFailure(billingResult.getResponseCode(),billingResult.getDebugMessage());
//                    }
//                }
//            });
//            return;
//        }
//
//
//        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//        params.setSkusList(skuList).setType(skuType);
//        billingClient.querySkuDetailsAsync(params.build(),
//                new SkuDetailsResponseListener() {
//                    @Override
//                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult,
//                                                     @Nullable List<SkuDetails> list) {
//                        Log.d("TAG", "onSkuDetailsResponse");
//                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                            //查询商品详情
//                            callback.onSuccess(list);
//                        }else {
//                            callback.onFailure(billingResult.getResponseCode(),billingResult.getDebugMessage());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 发起购买流程
//     *
//     * @param activity
//     * @param skuId 商品id
//     * @param obfuscatedAccountId 会回传
//     * @param obfuscatedProfileId
//     */
//    public void launchBillingMiPay(Activity activity,String skuId,String obfuscatedAccountId,String obfuscatedProfileId){
//        if (!isConnected() || billingClient == null) {
//            return;
//        }
//        if (skuId == null || skuId.isEmpty()) return;
//        List<String> skuList = new ArrayList<>();
//        skuList.add(skuId);
//
//        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
//
//        billingClient.querySkuDetailsAsync(params.build(),
//                new SkuDetailsResponseListener() {
//                    @Override
//                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult,
//                                                     @Nullable List<SkuDetails> list) {
//                        LogUtils.d("TAG", "onSkuDetailsResponse");
//                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                            //查询商品详情
//                            if (list != null && list.size() > 0){
//                                for (SkuDetails skuDetails:list){
//                                    //发起购买流程
//                                    launchMiPay(activity,skuDetails,obfuscatedAccountId,obfuscatedProfileId);
//                                }
//                            }else {
//                                //查询不到该商品说明：小米商店后台未配置该商品
//                                SDKManager.getInstance().hideProgress();
//                                if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                                    SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.no_upload_apk, "XiaoMi Play does not have the item id.");
//                                }
//                            }
//                        }else {
//                            //其他错误码
//                            SDKManager.getInstance().hideProgress();
//                            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                                SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                            }
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 开启小米支付购买流程
//     * @param activity
//     * @param skuDetails
//     * @param ObfuscatedAccountId
//     * @param ObfuscatedProfileId
//     */
//    private void launchMiPay(Activity activity,SkuDetails skuDetails,String ObfuscatedAccountId,String ObfuscatedProfileId){
//        BillingFlowParams params = BillingFlowParams.newBuilder()
//                .setSkuDetails(skuDetails)
//                .setObfuscatedAccountId(ObfuscatedAccountId)
//                .setObfuscatedProfileId(ObfuscatedProfileId)
//                //webHook为开发者指定的webhook url
//                .setWebHookUrl("https://go.0egg.com/pay/notify/xiaomi")
//                .build();
//        BillingResult result = billingClient.launchBillingFlow(activity, params);
//        LogUtils.d(TAG, "launchMiPay result = " + result.getResponseCode());
//    }
//
//    /**
//     * 支付结果回调
//     * @param billingResult
//     * @param list
//     */
//    @Override
//    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
//        if (list != null && billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
//            //支付成功
//            if (list.size() > 0){
//                for (Purchase purchase:list){
//                    LogUtils.d(TAG,"客户端支付完成");
//                    notifySDKServerDelay(purchase);
//                }
//            }
//
//        }else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
//            //支付取消
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onCancel();
//            }
//        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.PAYMENT_SHOW_DIALOG){
//            //弹出支付窗口
//
//        } else {
//            //other message
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//            }
//        }
//    }
//
//    /**
//     * 延时5秒去查询小米支付结果
//     * 不然服务器端还没查询完成就查询的话会返回错误
//     * @param purchase
//     */
//    private void notifySDKServerDelay(Purchase purchase){
//        TimerTask task = new TimerTask(){
//            public void run(){
//                LogUtils.d(TAG,"开始Notify服务器");
//                notifySDKServer(purchase);
//            }
//        };
//        Timer timer = new Timer();
//        //5秒后执行
//        timer.schedule(task, 5 * 1000);
//    }
//
//    /**
//     * 通知SDK服务器进行验证订单
//     * @param purchase
//     */
//    private void notifySDKServer(Purchase purchase) {
//        if (purchase == null) return;
//        String token = purchase.getPurchaseToken();
//        LogUtils.d(TAG,"token:"+token);
//        try {
//            final String aesKey = AESUtils.generate16SecretKey();
//            final String ivParameter = AESUtils.generate16SecretKey();
//            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
//            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
//            ApiManager.getInstance().SDKPurchaseNotifyMiPay(aesKey, ivParameter, aesKey16byRSA, purchase, new NetCallBack() {
//                @Override
//                public void onSuccess(String result) {
//                    if (result == null || result.isEmpty()) {
//                        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                            SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.result_is_null, "SDKPurchaseNotify response result is null.");
//                        }
//                        SDKManager.getInstance().hideProgress();
//                        return;
//                    }
//                    String decodeData = AESUtils.decrypt(result, aesKey, ivParameter);
//                    SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
//                    if (orderModel == null) {
//                        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                            SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.model_is_null, "orderModel is null.");
//                        }
//                        SDKManager.getInstance().hideProgress();
//                        return;
//                    }
//                    if (orderModel.getCode() == 1) {
//                        LogUtils.d(TAG, "通知服务器成功");
//                        String orderId = orderModel.getData().getTransactionId();
//                        double price = orderModel.getData().getPrice();
//                        String currency = orderModel.getData().getCurrency();
//                        String type = "XiaoMiIAP";
//
//                        handleAfterPurchaseSuccess(BillingClient.SkuType.INAPP, true, purchase, orderId, String.valueOf(price), currency, type);
//                        //消费商品
//                        consumeSku(purchase);
//                    }else {
//                        SDKManager.getInstance().hideAllProgress();
//                        LogUtils.e(TAG, "SDKPurchaseNotifyMiPay---failed:code:" + orderModel.getCode()+",msg:"+orderModel.getMessage());
//                        SDKGameUtils.showServiceInfo(orderModel.getCode(), orderModel.getMessage());
//                        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                            SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getCode(), orderModel.getMessage());
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(String msg) {
//                    LogUtils.e(TAG, "SDKPurchaseNotify:onFailure---" + msg);
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.network_error, msg);
//                    }
//                    //有可能网络错误，建立重发机制
//                    resendOrder5();
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.other_error, e.getMessage());
//            }
//        }
//    }
//
//
//    /**
//     * 建立重发机制
//     * 5分钟
//     */
//    public void resendOrder5() {
//        //todo 建立重发机制
//    }
//
//    /**
//     * 通过购买令牌来消费产品
//     * @param purchase
//     */
//    private void consumeSku(Purchase purchase){
//        if (purchase == null || billingClient == null) return;
//
//        billingClient.consumeAsync(purchase.getPurchaseToken(), new ConsumeResponseListener() {
//            @Override
//            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    //确认成功，您可再次发起购买
//                    LogUtils.d(TAG,"消费成功，您可再次发起购买");
//                }else {
//                    LogUtils.d(TAG, "消费失败-code:" + billingResult.getResponseCode() + ": " + billingResult.getDebugMessage());
//                }
//            }
//        });
//    }
//
//
//    /**
//     * 查询补单
//     */
//    public void queryLostOrder(){
//        if (billingClient == null) return;
//        billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, new PurchasesResponseListener() {
//            @Override
//            public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> list) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    if (list.size() > 0){
//                        LogUtils.d(TAG,"有掉单"+list.size());
//                        for (Purchase purchase:list){
//                            //补单操作
//                            notifySDKServer(purchase);
//                        }
//                    }else {
//                        //没有掉单
//                        LogUtils.d(TAG,"没有掉单");
//                    }
//                }
//            }
//        });
//    }
//
//
//    /**
//     * 支付成功之后的处理:回调，追踪，成功的提示
//     *
//     * @param orderId
//     * @param price
//     * @param currency
//     * @param type
//     */
//    private void handleAfterPurchaseSuccess(String itemType, boolean isCallback, Purchase purchase, String orderId, String price, String currency, String type) {
//        LogUtils.e("小米支付成功 success_story: orderid-" + orderId + "  type-" + currency + " price-" + price + " type2-" + type);
//        if (isCallback)
//            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("19"), 1);
//        //支付追踪
//        TrackingManager.purchaseTracking(SDKManager.getInstance().getActivity(), itemType, SDKManager.getInstance().getUser().getUserId(), orderId, Double.parseDouble(price), currency, type);
//
//        if (!isCallback) return;
//        //回调接口
//        PayResult payParams = new PayResult();
//        payParams.setCurrency(currency);
//        payParams.setOrderid(orderId);
//        payParams.setReferenceId(orderId);
//        payParams.setSuc(true);
//        payParams.setPrice(price);
//        payParams.setPayType(type);
//        payParams.setMessage("success_story");
//        SDKManager.getInstance().hideProgress();
//        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//            SDKManager.getInstance().getPurchaseCallBack().onSuccess(payParams);
//        }
//    }
//
//}
