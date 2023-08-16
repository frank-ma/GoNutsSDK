//package com.nutsplay.nopagesdk.manager;
//
//import android.app.Activity;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.android.billingclient.api.AcknowledgePurchaseParams;
//import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
//import com.android.billingclient.api.BillingClient;
//import com.android.billingclient.api.BillingClientStateListener;
//import com.android.billingclient.api.BillingFlowParams;
//import com.android.billingclient.api.BillingResult;
//import com.android.billingclient.api.ConsumeParams;
//import com.android.billingclient.api.ConsumeResponseListener;
//import com.android.billingclient.api.Purchase;
//import com.android.billingclient.api.PurchaseHistoryRecord;
//import com.android.billingclient.api.PurchaseHistoryResponseListener;
//import com.android.billingclient.api.PurchasesResponseListener;
//import com.android.billingclient.api.PurchasesUpdatedListener;
//import com.android.billingclient.api.SkuDetails;
//import com.android.billingclient.api.SkuDetailsParams;
//import com.android.billingclient.api.SkuDetailsResponseListener;
//import com.nutsplay.nopagesdk.beans.PayResult;
//import com.nutsplay.nopagesdk.beans.SDKOrderModel;
//import com.nutsplay.nopagesdk.callback.NetCallBack;
//import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
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
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by frankma on 2019-09-25 15:40
// * Email: frankma9103@gmail.com
// * Desc: 4.0.0版本的Google支付库
// */
//public class GooglePayHelpV4 implements PurchasesUpdatedListener {
//
//    private String TAG = "GooglePayHelp";
//
//    private String skuId = "";
//
//    private String itemType = BillingClient.SkuType.INAPP;//inapp普通商品   subs订阅商品
//
//    private BillingClient billingClient;
//
//    private volatile static GooglePayHelpV4 INSTANCE;
//
//    private boolean isConnected = false;
//
//
//    public static GooglePayHelpV4 getInstance() {
//        if (INSTANCE == null) {
//            synchronized (GooglePayHelpV4.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new GooglePayHelpV4();
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
//    public void initGoogleIAP(final Activity activity, final String skuId, String transactionId, final String type) {
//
//        this.skuId = skuId;
//        this.itemType = type;
//        if (billingClient == null) {
//            billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
//        }
//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    //初始化成功
//                    Log.d(TAG, "Google 初始化成功");
//                    setConnected(true);
//                    queryPurchase(true, type,transactionId);
//                } else {
//                    SDKToast.getInstance().ToastShow(billingResult.getDebugMessage(), 2);
//                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onBillingServiceDisconnected() {
////                reConnectGooglePlay(type);
//            }
//        });
//
//    }
//
//    /**
//     * 订阅商品，适用于订阅升级降级，带有oldSkuId参数
//     *
//     * @param activity
//     * @param skuId
//     * @param oldSkuId
//     * @param transactionId
//     * @param type
//     */
//    public void initGoogleIAP(final Activity activity, final String skuId, final String oldSkuId, String transactionId, final String type) {
//
//        this.skuId = skuId;
//        this.itemType = type;
//        if (billingClient == null) {
//            billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
//        }
//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    //初始化成功
//                    LogUtils.d(TAG, "Google 初始化成功");
//                    setConnected(true);
//
//
//                    queryPurchase(true, type,transactionId);
//                } else {
//                    SDKToast.getInstance().ToastShow(billingResult.getDebugMessage(), 2);
//                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onBillingServiceDisconnected() {
//
////                reConnectGooglePlay(type,transactionId);
//            }
//        });
//    }
//
//    public void initGoogleIAP(final Activity activity, BillingClientStateListener billingClientStateListener) {
//        if (billingClient == null) {
//            billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
//        }
//        billingClient.startConnection(billingClientStateListener);
//    }
//
//    /**
//     * 查询当前GP账号缓存的购买交易
//     * 该方法使用GP商店的缓存
//     * 至少调用两次：
//     * 1 每次启动应用时都调用，以便恢复掉单数据；
//     * 2 onResume()方法中调用，因为当您的应用在后台运行时，用户可能会发起购买交易，例如在GP商店中兑换促销代码；
//     */
//    public void queryPurchase(final boolean isBuy, final String type,String transactionId) {
//
//        if (!isConnected() || billingClient == null) {
//            return;
//        }
//        billingClient.queryPurchasesAsync(type, new PurchasesResponseListener() {
//            @Override
//            public void onQueryPurchasesResponse(@NonNull @NotNull BillingResult billingResult, @NonNull @NotNull List<Purchase> purchasesList) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    LogUtils.d(TAG, "queryPurchase:purchaseList.size()----" + purchasesList.size());
//                    if (!isBuy && purchasesList.size() == 0) {
//                        LogUtils.d(TAG, "no lost order");
//                        destroy();
//                        return;
//                    }
//                    //如果是订阅，直接进行购买，如果有订阅是不能购买的
//                    if (BillingClient.SkuType.SUBS.equals(type)) {
//                        if (isBuy) launchBillingFlow(skuId, type,transactionId);
//                    } else {
//                        //消耗型商品
//                        if (purchasesList.size() == 0) {
//                            //没有掉单，直接购买
//                            LogUtils.d(TAG, "no lost order");
//                            if (isBuy) launchBillingFlow(skuId, itemType,transactionId);
//                        } else {
//                            //有掉单，先补单
//                            LogUtils.d("lost order", "lost order size:" + purchasesList.size());
//                            boolean isItemLost = false;
//                            for (Purchase purchase : purchasesList) {
//                                if (purchase == null) continue;
//                                if (purchase.getSkus().get(0).equals(skuId)) {
//                                    isItemLost = true;
//                                }
//                                notifyServerForLostOrder(itemType, purchase);
//                            }
//                            //要买的item没有掉单可以直接购买
//                            if (!isItemLost && isBuy) {
//                                launchBillingFlow(skuId, itemType,transactionId);
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 未确认的订阅发给服务器进行确认，
//     * 如果三天未确认，就会给用户退款
//     *
//     * @param purchase
//     */
//    private void resentSubscription(Purchase purchase) {
//        if (purchase == null) return;
//        try {
////            ApiManager.getInstance().SDKSubscriptionNotify(purchase);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 通过数据库记录进行补单
//     */
////    public void resentOrderByDbRecord() {
////        try {
////            List<Purchase> records = DBManager.getInstance().queryLostOrders();
////            if (records == null) return;
////            LogUtils.d(TAG, "掉单数量：" + records.size());
////            if (records.size() > 0) {
////                for (Purchase purchase : records) {
////                    notifyServerForLostOrder(BillingClient.SkuType.INAPP, purchase);
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    /**
//     * 通过网络请求发起最近的购买交易
//     * 返回用户每个商品ID发起的最近一笔购买交易
//     * 注意：该接口会进行网络调用，这可能会导致向应用用户收费，要尽量少用
//     */
//    private void queryHistoryPurchase() {
//
//        if (billingClient == null) return;
//        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
//            @Override
//            public void onPurchaseHistoryResponse(@NotNull BillingResult billingResult, List<PurchaseHistoryRecord> purchaseHistoryRecordList) {
//                if (purchaseHistoryRecordList != null) {
//                    LogUtils.d(TAG, "queryHistoryPurchase:purchaseHistoryRecordList.size()-----" + purchaseHistoryRecordList.size());
//                }
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchaseHistoryRecordList != null && purchaseHistoryRecordList.size() > 0) {
//                    for (PurchaseHistoryRecord historyRecord : purchaseHistoryRecordList) {
//                        String sku = historyRecord.getSkus().get(0);
//                        String historyRecordStr = historyRecord.toString();
//                        String originalJson = historyRecord.getOriginalJson();
//                        LogUtils.d(TAG, "queryHistoryPurchase:" + historyRecordStr);
//
//
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 价格变动确认流程
//     */
////    private void priceChangeConfirm(Activity activity) {
////        if (billingClient == null) return;
////        SkuDetails changeSku = null;
////        PriceChangeFlowParams priceChangeFlowParams = PriceChangeFlowParams.newBuilder()
////                .setSkuDetails(changeSku).build();
////        billingClient.launchPriceChangeConfirmationFlow(activity, priceChangeFlowParams,
////                new PriceChangeConfirmationListener() {
////                    @Override
////                    public void onPriceChangeConfirmationResult(@NotNull BillingResult billingResult) {
////                        int responseCode = billingResult.getResponseCode();
////
////                        if (responseCode == BillingClient.BillingResponseCode.OK) {
////                            //用户确认了价格变动，谢谢支持
////
////
////                        } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
////                            //用户没有确认价格变动
////
////                        }
////                    }
////                });
////    }
//
//    /**
//     * 查询商品的本地价格
//     *
//     * @param skuList
//     */
//    public void querySkuDetails(final List<String> skuList, final String type, final SDKGetSkuDetailsCallback callback) {
//        if (skuList == null || skuList.size() == 0 || callback == null) return;
//
//        if (!isConnected() || billingClient == null) {
//            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
//                @Override
//                public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
//
//                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                        setConnected(true);
//                        querySkuDetails(skuList, type, callback);
//                    } else {
//                        callback.onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                    }
//                }
//
//                @Override
//                public void onBillingServiceDisconnected() {
//                    Log.i(TAG, "line135-onBillingServiceDisconnected()");
//                }
//            });
//            return;
//        }
//
//
//        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//        params.setSkusList(skuList).setType(type);
//        billingClient.querySkuDetailsAsync(params.build(),
//                new SkuDetailsResponseListener() {
//                    @Override
//                    public void onSkuDetailsResponse(@NotNull BillingResult billingResult, List<SkuDetails> skuDetailsList) {
//
//                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                            if (skuDetailsList != null && skuDetailsList.size() > 0) {
//
//                                List<com.nutsplay.nopagesdk.beans.SkuDetails> skus = new ArrayList<>();
//                                LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
//                                for (SkuDetails skuDetails : skuDetailsList) {
//                                    com.nutsplay.nopagesdk.beans.SkuDetails sku = new com.nutsplay.nopagesdk.beans.SkuDetails();
//                                    sku.setSku(skuDetails.getSku());
//                                    sku.setType(skuDetails.getType());
//                                    sku.setPrice(skuDetails.getPrice());
//                                    sku.setPriceCurrencyCode(skuDetails.getPriceCurrencyCode());
//                                    sku.setPriceAmountMicros(skuDetails.getPriceAmountMicros());
//                                    sku.setTitle(skuDetails.getTitle());
//                                    sku.setDescription(skuDetails.getDescription());
//                                    sku.setOriginalJson(skuDetails.getOriginalJson());
//                                    sku.setIconUrl(skuDetails.getIconUrl());
//                                    sku.setFreeTrialPeriod(skuDetails.getFreeTrialPeriod());
//                                    sku.setIntroductoryPrice(skuDetails.getIntroductoryPrice());
//                                    sku.setIntroductoryPriceAmountMicros(skuDetails.getIntroductoryPriceAmountMicros());
//                                    sku.setIntroductoryPriceCycles(skuDetails.getIntroductoryPriceCycles() + "");
//                                    sku.setOriginalPrice(skuDetails.getOriginalPrice());
////                                    sku.setRewarded(skuDetails.isRewarded());
//                                    sku.setSubscriptionPeriod(skuDetails.getSubscriptionPeriod());
//                                    sku.setIntroductoryPricePeriod(skuDetails.getIntroductoryPricePeriod());
//
//                                    skus.add(sku);
//                                }
//                                if (skus.size() > 0) {
//                                    callback.onSuccess(skus);
//                                }
//                            } else {
//                                callback.onFailure(SDKConstant.developer_error, "skuDetailsList is null or skuDetailsList.size() == 0,Please make sure the app has been uploaded to Google Play.");
//                            }
//                        } else {
//                            callback.onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 发起订阅
//     */
//    public void doSubscription(String subsId,String transactionId) {
//
//        if (billingClient == null) return;
//        BillingResult result = billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);
//        if (result.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//            //用户的GooglePlay版本支持订阅功能
//            //查询是否有该订阅
//            //如果没有支付该订阅
//            launchBillingFlow(skuId, BillingClient.SkuType.SUBS,transactionId);
//
//        } else {
//            SDKToast.getInstance().ToastShow("Your Google Play version does not support subscriptions, please upgrade it", 3);
//        }
//
//    }
//
//    /**
//     * 发起支付,内购和订阅
//     *
//     * @param skuId
//     */
//    public void launchBillingFlow(String skuId,String type,String transactionId) {
//
//        if (!isConnected() || billingClient == null) {
//            return;
//        }
//
//        if (skuId == null || skuId.isEmpty()) return;
//        List<String> skuList = new ArrayList<>();
//        skuList.add(skuId);
//        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//        params.setSkusList(skuList).setType(type);
//        billingClient.querySkuDetailsAsync(params.build(),
//                new SkuDetailsResponseListener() {
//                    @Override
//                    public void onSkuDetailsResponse(@NotNull BillingResult billingResult, List<SkuDetails> skuDetailsList) {
//
//                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                            if (skuDetailsList != null && skuDetailsList.size() > 0) {
//
//                                LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
//                                for (SkuDetails skuDetails : skuDetailsList) {
//                                    String sku = skuDetails.getSku();
//                                    if (sku.equals(skuId)) {
//                                        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
//                                                .setObfuscatedAccountId(transactionId)//订单号
//                                                .setSkuDetails(skuDetails).build();
//                                        if (billingClient == null) continue;
//                                        BillingResult bResult = billingClient.launchBillingFlow(SDKManager.getInstance().getActivity(), flowParams);
//                                        LogUtils.d(TAG, "launchBillingFlow---responseCode:" + bResult.getResponseCode() + "  msg:" + bResult.getDebugMessage());
//                                    }
//                                }
//
//                            } else {
//                                //Google商店未配置该商品
//                                SDKManager.getInstance().hideProgress();
//                                if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                                    SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.no_upload_apk, "Google Play does not have the item id.");
//                                    destroy();
//                                }
//                            }
//                        } else {
//                            SDKManager.getInstance().hideProgress();
//                            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                                SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                                destroy();
//                            }
//                        }
//                    }
//                });
//
//
//    }
//
//    /**
//     * Google Play服务重连策略
//     */
//    private void reConnectGooglePlay(final String type,String transactionId) {
//
//        if (billingClient == null) return;
//        billingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
//
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    //初始化成功
//                    LogUtils.d(TAG, "Google 初始化成功");
//                    setConnected(true);
//
//
//                    queryPurchase(true, type,transactionId);
//                } else {
//                    Log.d(TAG, "Google 初始化失败---responseCode:" + billingResult.getResponseCode() + "---" + billingResult.getDebugMessage());
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onBillingServiceDisconnected() {
//                Log.i(TAG, "line330-onBillingServiceDisconnected()");
//            }
//        });
//    }
//
//    /**
//     * 支付结果回调监听
//     *
//     * @param billingResult
//     * @param purchases
//     */
//    @Override
//    public void onPurchasesUpdated(@NotNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
//
//        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
//            //支付成功
//            for (Purchase purchase : purchases) {
//                //更新DB
////                PurchaseRecord purchaseRecord = new PurchaseRecord();
////                purchaseRecord.setTransactionId(transactionId);
////                purchaseRecord.setSkuId(skuId);
////                purchaseRecord.setGoogleId(purchase.getOrderId());
////                purchaseRecord.setStatus(2);
////                purchaseRecord.setPurchaseJson(purchase.getOriginalJson());
////                DBManager.getInstance().insertOrReplace(purchaseRecord);
//
//
//                //支付结果的回调这里，只有普通商品，订阅是没有回调的
//                notifyServer(itemType, false, purchase);
//            }
//
//        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
//            Log.e(TAG, "user_cancel");
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onCancel();
//            }
//            //消费完断开支付服务连接
//            destroy();
//        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
//            //Item 已经拥有
//            SDKToast.getInstance().ToastShow("Item already owned", 3);
//            Log.e(TAG, "item already owned");
//            queryHistoryPurchase();
//        } else {
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getResponseCode(), billingResult.getDebugMessage());
//                destroy();
//            }
//        }
//
//    }
//
//    /**
//     * 确认订阅购买交易
//     */
//    private void acknowledgeSubs(double price, String currency, final Purchase purchase) {
//        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
//            //统计订阅
//            AdjustTraceManager.getInstance().adjustTrackSubs(price, currency, purchase.getSkus().get(0), purchase.getOrderId(), purchase.getSignature(), purchase.getPurchaseToken());
//            //向用户授予权限
//            if (!purchase.isAcknowledged()) {
//                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
//                        .setPurchaseToken(purchase.getPurchaseToken()).build();
//                if (billingClient == null) return;
//                billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
//                    @Override
//                    public void onAcknowledgePurchaseResponse(@NotNull BillingResult billingResult) {
//                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                            //确认订阅成功
//                            LogUtils.e("订阅确认成功");
//                        } else {
//                            //订阅确认失败
//                            LogUtils.e("订阅确认失败：" + billingResult.getDebugMessage());
//                        }
//                        //消费完断开支付服务连接
//                        destroy();
//                    }
//                });
//            }
//        }
//
//    }
//
//    public void handlePurchase(Purchase purchase) {
//        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
//
//        } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
//
//        }
//    }
//
//    /**
//     * 支付成功，回调服务器，然后消费掉商品以便继续购买
//     *
//     * @param purchase
//     */
//    private void notifyServer(final String itemType, final boolean isBudan, final Purchase purchase) {
//        if (purchase == null) return;
//
//        try {
//            final String aesKey = AESUtils.generate16SecretKey();
//            final String ivParameter = AESUtils.generate16SecretKey();
//            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
//            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
//
//
//            ApiManager.getInstance().SDKPurchaseNotify(itemType, aesKey, ivParameter, aesKey16byRSA, purchase, new NetCallBack() {
//                @Override
//                public void onSuccess(String result) {
//
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
//
//                        LogUtils.d(TAG, "通知服务器成功");
//                        String orderId = orderModel.getData().getTransactionId();
//                        double price = orderModel.getData().getPrice();
//                        String currency = orderModel.getData().getCurrency();
//                        String type = "GoogleIAP";
//
//                        handleAfterPurchaseSuccess(itemType, true, purchase, orderId, String.valueOf(price), currency, type);
//
//
//                        if (SDKConstant.SUBS.equals(itemType)) {
//                            //确认订阅
//                            acknowledgeSubs(price, currency, purchase);
//                        } else {
//                            //消费商品
//                            consumeSku(purchase);
//                        }
//                    } else {
//                        SDKManager.getInstance().hideAllProgress();
//                        LogUtils.e(TAG, "SDKPurchaseNotify---failed:" + orderModel.getMessage());
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
//                    //有可能网络错误，建立重发
//                    resendOrder5();
//                }
//            });
//        } catch (Exception e) {
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
//
//    }
//
//    /**
//     * 支付成功，回调服务器，然后消费掉商品以便继续购买
//     * 补单回调服务器
//     *
//     * @param purchase
//     */
//    private void notifyServerForLostOrder(String itemType, final Purchase purchase) {
//        if (purchase == null) return;
//
//        try {
//            final String aesKey = AESUtils.generate16SecretKey();
//            final String ivParameter = AESUtils.generate16SecretKey();
//            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
//            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
//
//            ApiManager.getInstance().SDKPurchaseNotify(itemType, aesKey, ivParameter, aesKey16byRSA, purchase, new NetCallBack() {
//                @Override
//                public void onSuccess(String result) {
//
//                    if (result == null || result.isEmpty()) {
//                        LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---result is null.");
//                        return;
//                    }
//                    String decodeData = AESUtils.decrypt(result, aesKey, ivParameter);
//
//                    SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
//                    if (orderModel == null) {
//                        LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---orderModel is null.");
//                        return;
//                    }
//                    if (orderModel.getCode() == 1) {
//
//                        LogUtils.d(TAG, "通知服务器成功");
//                        String orderId = orderModel.getData().getTransactionId();
//                        double price = orderModel.getData().getPrice();
//                        String currency = orderModel.getData().getCurrency();
//                        String type = "GoogleIAP";
//
//                        SDKManager.getInstance().hideProgress();
//                        handleAfterPurchaseSuccess(itemType, true, purchase, orderId, String.valueOf(price), currency, type);
//                        //消费商品
//                        consumeSku(purchase);
//
//                    } else {
//                        LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---" + orderModel.getMessage());
//                        SDKGameUtils.showServiceInfo(orderModel.getCode(), orderModel.getMessage());
//                        SDKManager.getInstance().hideProgress();
//                        if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                            SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getCode(), orderModel.getMessage());
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(String msg) {
//                    LogUtils.e(TAG, "notifyServerForLostOrder:onFailure---" + msg);
//                    SDKManager.getInstance().hideProgress();
//                    if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                        SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.network_error, msg);
//                    }
//                    destroy();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            SDKManager.getInstance().hideProgress();
//            if (SDKManager.getInstance().getPurchaseCallBack() != null) {
//                SDKManager.getInstance().getPurchaseCallBack().onFailure(SDKConstant.other_error, e.getMessage());
//            }
//            destroy();
//        }
//    }
//
//    /**
//     * 消费商品
//     */
//    private void consumeSku(final Purchase purchase) {
//        if (purchase == null) return;
//
//        ConsumeParams consumeParams = ConsumeParams.newBuilder()
//                .setPurchaseToken(purchase.getPurchaseToken())
//                .build();
//        if (billingClient == null) return;
//        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
//            @Override
//            public void onConsumeResponse(@NotNull BillingResult billingResult, @NotNull String purchaseToken) {
//                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                    //消费成功
//                    LogUtils.d(TAG, "消费成功");
//                } else {
//                    LogUtils.d(TAG, "消费失败-code:" + billingResult.getResponseCode() + ": " + billingResult.getDebugMessage());
//                }
//                //消费完断开支付服务连接
//                destroy();
//            }
//        });
//
//    }
//
//    /**
//     * Clear the resources
//     * 否则会有个问题：支付完一笔后，切换网络或者杀掉谷歌商店，
//     * 会重新弹出支付框
//     */
//    public void destroy() {
//        try {
//            Log.d(TAG, "Destroy GooglePayHelp");
//            if (billingClient != null && billingClient.isReady()) {
//                billingClient.endConnection();
//                billingClient = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
//        LogUtils.e("Google支付成功 success_story: orderid-" + orderId + "  type-" + currency + " price-" + price + " type2-" + type);
//        if (isCallback)
//            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("19"), 1);
//        //支付追踪
//        TrackingManager.purchaseTracking(SDKManager.getInstance().getActivity(), itemType, SDKManager.getInstance().getUser().getUserId(), orderId, Double.parseDouble(price), currency, type);
//
//        //更新DB
////        PurchaseRecord purchaseRecord = new PurchaseRecord();
////        purchaseRecord.setTransactionId(transactionId);
////        purchaseRecord.setStatus(1);
////        purchaseRecord.setSkuId(skuId);
////        purchaseRecord.setGoogleId(purchase.getOrderId());
////        purchaseRecord.setPurchaseJson(purchase.toString());
////        DBManager.getInstance().insertOrReplace(purchaseRecord);
//
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
