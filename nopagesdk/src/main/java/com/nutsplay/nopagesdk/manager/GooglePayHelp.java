package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.PriceChangeConfirmationListener;
import com.android.billingclient.api.PriceChangeFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.SDKOrderModel;
import com.nutsplay.nopagesdk.callback.NetCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.db.DBManager;
import com.nutsplay.nopagesdk.db.PurchaseRecord;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.network.GsonUtils;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.encryption.AESUtils;
import com.nutsplay.nopagesdk.utils.encryption.RSAUtils;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankma on 2019-09-25 15:40
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class GooglePayHelp implements PurchasesUpdatedListener {

    private String TAG = "GooglePayHelp";

    private String skuId = "";

    private String transactionId = "";//坚果订单ID

    private BillingClient billingClient;

    private volatile static GooglePayHelp INSTANCE;

    private boolean isConnected = false;


    public static GooglePayHelp getInstance() {
        if (INSTANCE == null) {
            synchronized (GooglePayHelp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GooglePayHelp();
                }
            }
        }
        return INSTANCE;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void initGoogleIAP(final Activity activity, final String skuId, String transactionId, final String type) {

        this.skuId = skuId;
        this.transactionId = transactionId;
        billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult == null) return;
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //初始化成功
                    LogUtils.d(TAG, "Google 初始化成功");
                    setConnected(true);


                    queryPurchase(true,type);
                } else {
                    SDKToast.getInstance().ToastShow(billingResult.getDebugMessage(), 2);
                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getDebugMessage());
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

                reConnectGooglePlay(type);
            }
        });
    }

    /**
     * 订阅商品，适用于订阅升级降级，带有oldSkuId参数
     *
     * @param activity
     * @param skuId
     * @param oldSkuId
     * @param transactionId
     * @param type
     */
    public void initGoogleIAP(final Activity activity, final String skuId,final String oldSkuId, String transactionId, final String type) {

        this.skuId = skuId;
        this.transactionId = transactionId;
        billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult == null) return;
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //初始化成功
                    LogUtils.d(TAG, "Google 初始化成功");
                    setConnected(true);


                    queryPurchase(true,type);
                } else {
                    SDKToast.getInstance().ToastShow(billingResult.getDebugMessage(), 2);
                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getDebugMessage());
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

                reConnectGooglePlay(type);
            }
        });
    }

    public void initGoogleIAP(final Activity activity, BillingClientStateListener billingClientStateListener) {

        billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
        billingClient.startConnection(billingClientStateListener);
    }

    /**
     * 查询当前GP账号缓存的购买交易
     * 该方法使用GP商店的缓存
     * 至少调用两次：
     * 1 每次启动应用时都调用，以便恢复掉单数据；
     * 2 onResume()方法中调用，因为当您的应用在后台运行时，用户可能会发起购买交易，例如在GP商店中兑换促销代码；
     */
    public void queryPurchase(final boolean isBuy, final String type) {

        if (!isConnected() || billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult == null) return;

                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        setConnected(true);
                        queryPurchase(isBuy,type);
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                    Log.i(TAG, "line135-onBillingServiceDisconnected()");

                }
            });
            return;
        }
        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(type);
        List<Purchase> purchasesList = purchasesResult.getPurchasesList();
        if (purchasesList == null) return;
        LogUtils.d(TAG, "queryPurchase:purchaseList.size()----" + purchasesList.size());

        //如果是订阅，直接进行购买，如果有订阅是不能购买的
        if (type.equals(BillingClient.SkuType.SUBS) && isBuy){
            launchBillingFlow(skuId,type);
            return;
        }



        if (purchasesList.size() > 0){
            //有掉单，先进行补单操作
            LogUtils.d(TAG, "queryPurchase:purchaseList.size()----" + purchasesList.size());
            for (Purchase purchase : purchasesList) {
                if (purchase == null ) return;
                LogUtils.d(TAG, purchase.toString());
                int statues = purchase.getPurchaseState();
                String sku = purchase.getSku();
                String orderId = purchase.getOrderId();
                LogUtils.d(TAG, orderId);

                //补单机制
                if (type.equals(BillingClient.SkuType.INAPP)){
                    notifyServer(true,purchase);
                }
            }
        }
        //执行购买操作
        if (isBuy) launchBillingFlow(skuId,type);

    }

    /**
     * 未确认的订阅发给服务器进行确认，
     * 如果三天未确认，就会给用户退款
     *
     * @param purchase
     */
    private void resentSubscription(Purchase purchase) {
        if (purchase == null) return;
        try{
//            ApiManager.getInstance().SDKSubscriptionNotify(purchase);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 通过数据库记录进行补单
     */
    public void resentOrderByDbRecord() {
        try {
            List<Purchase> records = DBManager.getInstance().queryLostOrders();
            LogUtils.d(TAG, "掉单数量：" + records.size());
            if (records != null && records.size() > 0) {
                for (Purchase purchase : records) {
                    notifyServerForLostOrder(purchase);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过网络请求发起最近的购买交易
     * 返回用户每个商品ID发起的最近一笔购买交易
     * 注意：该接口会进行网络调用，这可能会导致向应用用户收费，要尽量少用
     */
    private void queryHistoryPurchase() {

        if (billingClient == null) return;
        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
            @Override
            public void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> purchaseHistoryRecordList) {
                if (purchaseHistoryRecordList != null) {
                    LogUtils.d(TAG, "queryHistoryPurchase:purchaseHistoryRecordList.size()-----" + purchaseHistoryRecordList.size());
                }
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchaseHistoryRecordList != null && purchaseHistoryRecordList.size() > 0) {
                    for (PurchaseHistoryRecord historyRecord : purchaseHistoryRecordList) {
                        String sku = historyRecord.getSku();
                        String historyRecordStr = historyRecord.toString();
                        String originalJson = historyRecord.getOriginalJson();
                        LogUtils.d(TAG, "queryHistoryPurchase:" + historyRecordStr);


                    }
                }
            }
        });
    }

    /**
     * 价格变动确认流程
     *
     */
    private void priceChangeConfirm(Activity activity){
        if (billingClient==null) return;
        SkuDetails changeSku=null;
        PriceChangeFlowParams priceChangeFlowParams= PriceChangeFlowParams.newBuilder()
                .setSkuDetails(changeSku).build();
        billingClient.launchPriceChangeConfirmationFlow(activity, priceChangeFlowParams,
                new PriceChangeConfirmationListener() {
                    @Override
                    public void onPriceChangeConfirmationResult(BillingResult billingResult) {
                        if (billingResult==null)return;
                        int responseCode =billingResult.getResponseCode();

                        if (responseCode== BillingClient.BillingResponseCode.OK){
                            //用户确认了价格变动，谢谢支持


                        }else if (responseCode== BillingClient.BillingResponseCode.USER_CANCELED){
                            //用户没有确认价格变动

                        }
                    }
                });
    }

    /**
     * 查询商品的本地价格
     *
     * @param skuList
     */
    public void querySkuDetails(final List<String> skuList,final String type,final SDKGetSkuDetailsCallback callback) {
        if (skuList == null || skuList.size() == 0 || callback==null) return;

        if (!isConnected() || billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult == null) return;

                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        setConnected(true);
                        querySkuDetails(skuList,type,callback);
                    }else {
                        callback.onFailure(billingResult.getResponseCode()+":"+billingResult.getDebugMessage());
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                    Log.i(TAG, "line135-onBillingServiceDisconnected()");

                }
            });
            return;
        }


        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(type);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {

                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (skuDetailsList != null && skuDetailsList.size() > 0) {

                                List<com.nutsplay.nopagesdk.beans.SkuDetails> skus = new ArrayList<>();

                                LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
                                for (SkuDetails skuDetails : skuDetailsList) {

                                    com.nutsplay.nopagesdk.beans.SkuDetails sku = new com.nutsplay.nopagesdk.beans.SkuDetails();
                                    sku.setSku(skuDetails.getSku());
                                    sku.setType(skuDetails.getType());
                                    sku.setPrice(skuDetails.getPrice());
                                    sku.setPriceCurrencyCode(skuDetails.getPriceCurrencyCode());
                                    sku.setPriceAmountMicros(skuDetails.getPriceAmountMicros());
                                    sku.setTitle(skuDetails.getTitle());
                                    sku.setDescription(skuDetails.getDescription());
                                    sku.setOriginalJson(skuDetails.getOriginalJson());
                                    sku.setIconUrl(skuDetails.getIconUrl());
                                    sku.setFreeTrialPeriod(skuDetails.getFreeTrialPeriod());
                                    sku.setIntroductoryPrice(skuDetails.getIntroductoryPrice());
                                    sku.setIntroductoryPriceAmountMicros(skuDetails.getIntroductoryPriceAmountMicros());
                                    sku.setIntroductoryPriceCycles(skuDetails.getIntroductoryPriceCycles());
                                    sku.setOriginalPrice(skuDetails.getOriginalPrice());
                                    sku.setRewarded(skuDetails.isRewarded());
                                    sku.setSubscriptionPeriod(skuDetails.getSubscriptionPeriod());
                                    sku.setIntroductoryPricePeriod(skuDetails.getIntroductoryPricePeriod());

                                    skus.add(sku);
                                }
                                if (skus.size() > 0) {
                                    callback.onSuccess(skus);
                                }
                            } else {
                                callback.onFailure("skuDetailsList is null or skuDetailsList.size() == 0,Please make sure the app has been uploaded to Google Play.");

                            }

                        } else {

                            callback.onFailure(billingResult.getResponseCode() + ":" + billingResult.getDebugMessage());
                        }

                    }
                });
    }

    /**
     * 发起订阅
     *
     */
    public void doSubscription(String subsId){

        if (billingClient == null) return;
           BillingResult result = billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);
        if (result == null) return;
        if (result.getResponseCode() == BillingClient.BillingResponseCode.OK){
            //用户的GooglePlay版本支持订阅功能
            //查询是否有该订阅
            //如果没有支付该订阅
            launchBillingFlow(skuId,BillingClient.SkuType.SUBS);

        }else {
            SDKToast.getInstance().ToastShow("Your Google Play version does not support subscriptions, please upgrade it",3);
        }

    }

    /**
     * 发起支付,内购和订阅
     *
     * @param skuId
     */
    public void launchBillingFlow(final String skuId, final String type) {

        if (!isConnected() || billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult == null) return;

                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        setConnected(true);
                        queryPurchase(true,type);
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                    Log.i(TAG, "line269-onBillingServiceDisconnected()");
                }
            });
            return;
        }

        if (skuId == null || skuId.isEmpty()) return;
        List<String> skuList = new ArrayList<>();
        skuList.add(skuId);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(type);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {

                        if (billingResult == null) return;
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (skuDetailsList != null && skuDetailsList.size() > 0) {

                                LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
                                for (SkuDetails skuDetails : skuDetailsList) {
                                    String sku = skuDetails.getSku();
                                    if (sku.equals(skuId)) {
                                        BillingFlowParams flowParams = BillingFlowParams.newBuilder().setDeveloperId(transactionId).setSkuDetails(skuDetails).build();

                                        BillingResult bResult = billingClient.launchBillingFlow(SDKManager.getInstance().getActivity(), flowParams);
                                        if (bResult != null) {
                                            LogUtils.d(TAG, "launchBillingFlow---responseCode:" + bResult.getResponseCode() + "  msg:" + bResult.getDebugMessage());
                                        }
                                    }
                                }

                            } else {
                                //Google商店未配置该商品
                                if (SDKManager.getInstance().getPurchaseCallBack() != null)
                                    SDKManager.getInstance().getPurchaseCallBack().onFailure("Google Play does not have the item id.");
                            }
                        } else {
                            if (SDKManager.getInstance().getPurchaseCallBack() != null)
                                SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode() + ":" + billingResult.getDebugMessage());
                        }


                    }
                });


    }

    /**
     * Google Play服务重连策略
     */
    private void reConnectGooglePlay(final String type) {

        if (billingClient == null) return;
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult == null) return;
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //初始化成功
                    LogUtils.d(TAG, "Google 初始化成功");
                    setConnected(true);


                    queryPurchase(true,type);
                } else {
                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getDebugMessage());
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Log.i(TAG, "line330-onBillingServiceDisconnected()");
            }
        });
    }

    /**
     * 支付结果回调监听
     *
     * @param billingResult
     * @param purchases
     */
    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        if (billingResult == null) return;

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            //支付成功
            for (Purchase purchase : purchases) {

                //更新DB
                PurchaseRecord purchaseRecord = new PurchaseRecord();
                purchaseRecord.setTransactionId(transactionId);
                purchaseRecord.setSkuId(skuId);
                purchaseRecord.setGoogleId(purchase.getOrderId());
                purchaseRecord.setStatus(2);
                purchaseRecord.setPurchaseJson(purchase.getOriginalJson());
                DBManager.getInstance().insertOrReplace(purchaseRecord);

                if (purchase.isAcknowledged()){
                    Log.d(TAG,"支付已确认");
                }
                notifyServer(false,purchase);
            }

        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            if (SDKManager.getInstance().getPurchaseCallBack() != null)
                SDKManager.getInstance().getPurchaseCallBack().onCancel();
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            //Item 已经拥有
            SDKToast.getInstance().ToastShow("Item already owned",3);
            Log.e(TAG,"item already owned");
            queryHistoryPurchase();
        } else {
            if (SDKManager.getInstance().getPurchaseCallBack() != null)
                SDKManager.getInstance().getPurchaseCallBack().onFailure("responseCode-" + billingResult.getResponseCode() + ":" + billingResult.getDebugMessage());
        }

    }

    /**
     * 支付成功，回调服务器，然后消费掉商品以便继续购买
     *
     * @param purchase
     */
    private void notifyServer(final boolean isBudan, final Purchase purchase) {
        if (purchase == null) return;

        try {
            final String aesKey = AESUtils.generate16SecretKey();
            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));


            LogUtils.e(TAG, "purchase.getDeveloperPayload()--------" + purchase.getDeveloperPayload());
            ApiManager.getInstance().SDKPurchaseNotify(aesKey, aesKey16byRSA, purchase.getDeveloperPayload(),purchase, new NetCallBack() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.d(TAG, "SDKPurchaseNotify---onSuccess:" + result);
                    if (result == null || result.isEmpty()) {
                        if (SDKManager.getInstance().getPurchaseCallBack() != null && !isBudan)
                            SDKManager.getInstance().getPurchaseCallBack().onFailure("result is null.");
                        return;
                    }
                    try {
                        String decodeData = AESUtils.decrypt(result, aesKey);
                        LogUtils.d(TAG, "Google IAP Notify:" + decodeData);
                        SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
                        if (orderModel == null) {
                            if (SDKManager.getInstance().getPurchaseCallBack() != null && !isBudan)
                                SDKManager.getInstance().getPurchaseCallBack().onFailure("orderModel is null.");
                            return;
                        }
                        if (orderModel.getCode() == 1) {

                            LogUtils.d(TAG, "通知服务器成功");
                            String orderId = orderModel.getData().getTransactionId();
                            double price = orderModel.getData().getPrice();
                            String currency = orderModel.getData().getCurrency();
                            String type = "GoogleIAP";

                            handleAfterPurchaseSuccess(true, purchase, orderId, String.valueOf(price), currency, type);


                            //消费商品
                            consumeSku(purchase);

                        } else {
                            LogUtils.e(TAG, "SDKPurchaseNotify---onSuccess:" + orderModel.getMessage());
                            SDKGameUtils.showServiceInfo(orderModel.getCode(), orderModel.getMessage());
                            if (SDKManager.getInstance().getPurchaseCallBack() != null && !isBudan)
                                SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(String msg) {

                    LogUtils.e(TAG, "SDKPurchaseNotify:onFailure---" + msg);
                    if (SDKManager.getInstance().getPurchaseCallBack() != null && !isBudan)
                        SDKManager.getInstance().getPurchaseCallBack().onFailure(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (SDKManager.getInstance().getPurchaseCallBack() != null && !isBudan)
                SDKManager.getInstance().getPurchaseCallBack().onFailure(e.getMessage());
        }
    }

//    private void notifyServer(final PurchaseJson purchase) {
//        if (purchase == null) return;
//
//        try {
//            final String aesKey = AESUtils.generate16SecretKey();
//            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
//            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
//
//
//            com.nutsplay.nopagesdk.beans.Purchase pur = new com.nutsplay.nopagesdk.beans.Purchase();
//            pur.setOrderId(purchase.getOrderId());
//            pur.setPackageName(purchase.getPackageName());
//            pur.setProductId(purchase.getProductId());
//            pur.setPurchaseTime(purchase.getPurchaseTime());
//            pur.setPurchaseState(purchase.getPurchaseState());
//            pur.setPurchaseToken(purchase.getPurchaseToken());
//
//
//            List<PurchaseRecord> records = DBManager.getInstance().query(purchase.getOrderId());
//            if (records == null || records.size() == 0) return;
//            PurchaseRecord pr = records.get(0);
//            if (pr == null) return;
//            pur.setDeveloperPayload(pr.getTransactionId());
//
//            LogUtils.e(TAG, "purchase.getDeveloperPayload()--------" + pr.getTransactionId());
//            ApiManager.getInstance().SDKPurchaseNotify(aesKey, aesKey16byRSA, pr.getTransactionId(), pur, new NetCallBack() {
//                @Override
//                public void onSuccess(String result) {
//                    LogUtils.d(TAG, "SDKPurchaseNotify---onSuccess:" + result);
//                    if (result == null || result.isEmpty()) {
//                        if (SDKManager.getInstance().getPurchaseCallBack() != null)
//                            SDKManager.getInstance().getPurchaseCallBack().onFailure("result is null.");
//                        return;
//                    }
//                    try {
//                        String decodeData = AESUtils.decrypt(result, aesKey);
//                        LogUtils.d(TAG, "Google IAP Notify:" + decodeData);
//                        SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
//                        if (orderModel == null) {
//                            if (SDKManager.getInstance().getPurchaseCallBack() != null)
//                                SDKManager.getInstance().getPurchaseCallBack().onFailure("orderModel is null.");
//                            return;
//                        }
//                        if (orderModel.getCode() == 1) {
//
//                            LogUtils.d(TAG, "通知服务器成功");
//                            String orderId = orderModel.getData().getTransactionId();
//                            double price = orderModel.getData().getPrice();
//                            String currency = orderModel.getData().getCurrency();
//                            String type = "GoogleIAP";
//
//                            handleAfterPurchaseSuccess(true, purchase, orderId, String.valueOf(price), currency, type);
//
//
//                            //消费商品
//                            consumeSku(purchase);
//
//                        } else {
//                            LogUtils.e(TAG, "SDKPurchaseNotify---onSuccess:" + orderModel.getMessage());
//                            SDKGameUtils.showServiceInfo(orderModel.getCode(), orderModel.getMessage());
//                            if (SDKManager.getInstance().getPurchaseCallBack() != null)
//                                SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getMessage());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(String msg) {
//
//                    LogUtils.e(TAG, "SDKPurchaseNotify:onFailure---" + msg);
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null)
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(msg);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null)
//                SDKManager.getInstance().getPurchaseCallBack().onFailure(e.getMessage());
//        }
//    }

    /**
     * 支付成功，回调服务器，然后消费掉商品以便继续购买
     * 补单回调服务器
     *
     * @param purchase
     */
    private void notifyServerForLostOrder(final Purchase purchase) {
        if (purchase == null) return;

        try {
            final String aesKey = AESUtils.generate16SecretKey();
            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));


            ApiManager.getInstance().SDKPurchaseNotify(aesKey, aesKey16byRSA, purchase.getDeveloperPayload(), purchase, new NetCallBack() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.d(TAG, "notifyServerForLostOrder---onSuccess:" + result);
                    if (result == null || result.isEmpty()) {
                        LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---result is null.");
                        return;
                    }
                    try {
                        String decodeData = AESUtils.decrypt(result, aesKey);
                        LogUtils.d(TAG, "Google IAP Notify:" + decodeData);
                        SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
                        if (orderModel == null) {
                            LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---orderModel is null.");
                            return;
                        }
                        if (orderModel.getCode() == 1) {

                            LogUtils.d(TAG, "通知服务器成功");
                            String orderId = orderModel.getData().getTransactionId();
                            double price = orderModel.getData().getPrice();
                            String currency = orderModel.getData().getCurrency();
                            String type = "GoogleIAP";

                            handleAfterPurchaseSuccess(false, purchase, orderId, String.valueOf(price), currency, type);
                            //消费商品
                            consumeSku(purchase);

                        } else {
                            SDKGameUtils.showServiceInfo(orderModel.getCode(), orderModel.getMessage());
                            LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---" + orderModel.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(String msg) {
                    LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---" + msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---" + e.getMessage());
        }
    }

    /**
     * 消费商品
     */
    private void consumeSku(final Purchase purchase) {
        if (purchase == null) return;

        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .setDeveloperPayload(purchase.getDeveloperPayload())
                .build();
        if (billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult == null) return;

                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        setConnected(true);
                        consumeSku(purchase);
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                    Log.i(TAG, "line135-onBillingServiceDisconnected()");

                }
            });
        } else {
            billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                @Override
                public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                    if (billingResult == null) return;
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        //消费成功
                        LogUtils.d(TAG, "消费成功");
                    } else {
                        LogUtils.d(TAG, "消费失败-code:" + billingResult.getResponseCode() + ": " + billingResult.getDebugMessage());
                    }

                }
            });
        }
    }

    /**
     * 支付成功之后的处理:回调，追踪，成功的提示
     *
     * @param orderId
     * @param price
     * @param currency
     * @param type
     */
    private void handleAfterPurchaseSuccess(boolean isCallback, Purchase purchase, String orderId, String price, String currency, String type) {
        LogUtils.e("Google支付成功 success_story: orderid-" + orderId + "  type-" + currency + " price-" + price + " type2-" + type);
        if (isCallback)
            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("19"), 1);
        //支付追踪
        TrackingManager.purchaseTracking(SDKManager.getInstance().getActivity(), SDKManager.getInstance().getUser().getUserId(), orderId, Double.valueOf(price), currency, type);

        //更新DB
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setTransactionId(transactionId);
        purchaseRecord.setStatus(1);
        purchaseRecord.setSkuId(skuId);
        purchaseRecord.setGoogleId(purchase.getOrderId());
        purchaseRecord.setPurchaseJson(purchase.toString());
        DBManager.getInstance().insertOrReplace(purchaseRecord);


        if (!isCallback) return;
        //回调接口
        PayResult payParams = new PayResult();
        payParams.setCurrency(currency);
        payParams.setOrderid(orderId);
        payParams.setSuc(true);
        payParams.setPrice(price);
        payParams.setPayType(type);
        payParams.setMessage("success_story");
        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
            SDKManager.getInstance().getPurchaseCallBack().onSuccess(payParams);
        }
    }

}
