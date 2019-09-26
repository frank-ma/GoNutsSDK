package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
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

    public void initGoogleIAP(final Activity activity, final String skuId) {

        this.skuId = skuId;
        billingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //初始化成功
                    LogUtils.d(TAG, "Google 初始化成功");
                    isConnected = true;


                    queryPurchase(true);
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

                reConnectGooglePlay();
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
    public void queryPurchase(final boolean isBuy) {

        if (!isConnected() || billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    setConnected(true);
                    queryPurchase(isBuy);
                }

                @Override
                public void onBillingServiceDisconnected() {

                }
            });
            return;
        }
        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
        List<Purchase> purchasesList = purchasesResult.getPurchasesList();
        if (purchasesList == null) return;
        LogUtils.d(TAG, "queryPurchase:purchaseList.size()----" + purchasesList.size());

        if (purchasesList.size() == 0) {
            if (isBuy) launchBillingFlow(skuId);
        } else {
            LogUtils.d(TAG, "queryPurchase:purchaseList.size()----" + purchasesList.size());
            for (Purchase purchase : purchasesList) {
                LogUtils.d(TAG, purchase.toString());
                int statues = purchase.getPurchaseState();
                String sku = purchase.getSku();
                String orderId = purchase.getOrderId();
                LogUtils.d(TAG, orderId);

                //补单机制
                resentOrder(purchase);
            }
        }

    }

    /**
     * 补单机制
     */
    private void resentOrder(Purchase purchase) {
        if (purchase == null) return;

        notifyServer(purchase);
    }

    /**
     * 通过网络请求发起最近的购买交易
     * 返回用户每个商品ID发起的最近一笔购买交易
     * 注意：该接口会进行网络调用，这可能会导致向应用用户收费，要尽量少用
     */
    private void queryHistoryPurchase() {

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
     * 查询商品的本地价格
     *
     * @param skuList
     */
    private void querySkuDetails(List<String> skuList) {
        if (!isConnected) {
            LogUtils.d(TAG, "querySkuDetails fail:google play server has not init.");
        }

        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {

                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null && skuDetailsList.size() > 0) {

                            LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
                            for (SkuDetails skuDetails : skuDetailsList) {
                                String sku = skuDetails.getSku();
                                String price = skuDetails.getPrice();
                                LogUtils.d(TAG, "sku:" + sku + "--------price:" + price);
                            }

                        }

                    }
                });
    }

    /**
     * 发起支付
     *
     * @param skuId
     */
    public void launchBillingFlow(final String skuId) {

        if (!isConnected() || billingClient == null) {
            initGoogleIAP(SDKManager.getInstance().getActivity(), new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    setConnected(true);
                    queryPurchase(true);
                }

                @Override
                public void onBillingServiceDisconnected() {

                }
            });
            return;
        }

        List<String> skuList = new ArrayList<>();
        skuList.add(skuId);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                        // Process the result.
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null && skuDetailsList.size() > 0) {

                            LogUtils.d(TAG, "skuDetailsList.size()---" + skuDetailsList.size());
                            for (SkuDetails skuDetails : skuDetailsList) {
                                String sku = skuDetails.getSku();
                                if (sku.equals(skuId)) {
                                    BillingFlowParams flowParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build();
                                    BillingResult bResult = billingClient.launchBillingFlow(SDKManager.getInstance().getActivity(), flowParams);
                                }
                            }

                        }

                    }
                });


    }

    /**
     * 重连策略
     */
    private void reConnectGooglePlay() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {

            }

            @Override
            public void onBillingServiceDisconnected() {

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

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            //支付成功
            for (Purchase purchase : purchases) {

                notifyServer(purchase);
            }

        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            SDKManager.getInstance().getPurchaseCallBack().onCancel();
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            //Item 已经拥有
            queryHistoryPurchase();
        } else {
            SDKManager.getInstance().getPurchaseCallBack().onFailure(billingResult.getDebugMessage());
        }

    }

    /**
     * 支付成功，回调服务器，然后消费掉商品以便继续购买
     *
     * @param purchase
     */
    private void notifyServer(final Purchase purchase) {

        try {
            final String aesKey = AESUtils.generate16SecretKey();
            String publicKey = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.PUBLIC_KEY);
            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
            String transactionId = SPManager.getInstance(SDKManager.getInstance().getActivity()).getString(SPKey.key_transactionId, "");
            ApiManager.getInstance().SDKPurchaseNotify(aesKey, aesKey16byRSA, transactionId, purchase, new NetCallBack() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.d(TAG, "SDKPurchaseNotify---onSuccess:" + result);
                    if (result == null || result.isEmpty()) {
                        SDKManager.getInstance().getPurchaseCallBack().onFailure("result is null.");
                        return;
                    }
                    try {
                        String decodeData = AESUtils.decrypt(result, aesKey);
                        LogUtils.d(TAG, "Google IAP Notify:" + decodeData);
                        SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
                        if (orderModel == null) {
                            SDKManager.getInstance().getPurchaseCallBack().onFailure("orderModel is null.");
                            return;
                        }
                        if (orderModel.getCode() == 1) {

                            String orderId = orderModel.getData().getTransactionId();
                            double price = orderModel.getData().getPrice();
                            String currency = orderModel.getData().getCurrency();
                            String type = "GoogleIAP";

                            handleAfterPurchaseSuccess(orderId, String.valueOf(price), currency, type);


                            //消费商品
                            consumeSku(purchase);

                        } else {
                            LogUtils.e(TAG, "SDKPurchaseNotify---onSuccess:" + orderModel.getMessage());
                            SDKGameUtils.showServiceInfo(orderModel.getCode());
                            SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(String msg) {

                    SDKManager.getInstance().getPurchaseCallBack().onFailure(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            SDKManager.getInstance().getPurchaseCallBack().onFailure(e.getMessage());
        }
    }

    /**
     * 消费商品
     */
    private void consumeSku(Purchase purchase) {

        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .setDeveloperPayload(purchase.getDeveloperPayload())
                .build();
        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //消费成功
                    LogUtils.d(TAG, "消费成功");
                } else {
                    LogUtils.d(TAG, "消费失败");
                }

            }
        });

    }

    /**
     * 支付成功之后的处理:回调，追踪，成功的提示
     *
     * @param orderId
     * @param price
     * @param currency
     * @param type
     */
    private void handleAfterPurchaseSuccess(String orderId, String price, String currency, String type) {
        LogUtils.e("第三方支付成功success_story: orderid-" + orderId + "  type-" + currency + " price-" + price + " type2-" + type);
        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("19"), 1);
        //支付追踪
        TrackingManager.purchaseTracking(SDKManager.getInstance().getActivity(), SDKManager.getInstance().getUser().getUserId(), orderId, Double.valueOf(price), currency, type);

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
