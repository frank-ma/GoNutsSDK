package com.nutsplay.nopagesdk.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.SDKOrderModel;
import com.nutsplay.nopagesdk.callback.NetCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.ApiManager;
import com.nutsplay.nopagesdk.manager.TrackingManager;
import com.nutsplay.nopagesdk.network.GsonUtils;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.encryption.AESUtils;
import com.nutsplay.nopagesdk.utils.encryption.RSAUtils;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.LogUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

import org.xutils.common.util.LogUtil;

/**
 * Created by frankma on 2019-09-24 16:19
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class PayWebActivity extends BaseActivity {

    private String TAG = "PayWebActivity";
    private WebView webView;
    private ProgressBar progressBar;
    private String transactionId="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(SDKResUtils.getResId(PayWebActivity.this, "activity_payweb", "layout"));
        initView();
    }

    private void initView() {
        webView = findViewById(SDKResUtils.getResId(this, "web", "id"));
        progressBar = findViewById(SDKResUtils.getResId(this, "progress", "id"));
        initWebView();
    }

    private void initWebView() {
        if (webView == null) return;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e(TAG, "payweburl---------" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar == null) return;
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                }
            }
        });
        WebSettings ws = webView.getSettings();
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JSInterface(), "android");


        //加载内容
        String payUrl = getIntent().getStringExtra("OpenData0");
        if (StringUtils.isNotBlank(payUrl)) {
            webView.loadUrl(payUrl);
        }
        transactionId = getIntent().getStringExtra("OpenData1");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            queryOrderStatus(transactionId);
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        queryOrderStatus();
//    }

    /**
     * 页面关闭的时候，查询订单支付状态，以免玩家非常规操作直接关闭支付成功的页面，统计不到数据
     */
    private void queryOrderStatus(String transactionId) {
        try {
            final String aesKey = AESUtils.generate16SecretKey();
            String publicKey = SPManager.getInstance(this).getString(SPKey.PUBLIC_KEY);
            String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
            ApiManager.getInstance().SDKQueryOrderStatus(aesKey, aesKey16byRSA, transactionId, new NetCallBack() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.d(TAG, "查询订单成功：" + result);
                    if (result == null || result.isEmpty()) {
                        SDKManager.getInstance().getPurchaseCallBack().onFailure("SDKQueryOrderStatus:result is null.");
                        return;
                    }
                    try {
                        String decodeData = AESUtils.decrypt(result, aesKey);
                        LogUtils.d(TAG, "查询订单支付状态:" + decodeData);
                        SDKOrderModel orderModel = (SDKOrderModel) GsonUtils.json2Bean(decodeData, SDKOrderModel.class);
                        if (orderModel == null) {
                            SDKManager.getInstance().getPurchaseCallBack().onFailure("orderModel is null.");
                            return;
                        }
                        if (orderModel.getCode() == 1) {

                            String orderId = orderModel.getData().getTransactionId();
                            double price = orderModel.getData().getPrice();
                            String currency = orderModel.getData().getCurrency();
                            String type = "XSOLLA";

                            handleAfterPurchaseSuccess(orderId, String.valueOf(price), currency, type);

                        } else {
                            LogUtils.e(TAG, "SDKQueryOrderStatus---onSuccess:" + orderModel.getMessage());
                            SDKGameUtils.showServiceInfo(orderModel.getCode(),orderModel.getMessage());
                            SDKManager.getInstance().getPurchaseCallBack().onFailure(orderModel.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(String msg) {
                    LogUtils.d(TAG, "查询订单失败：" + msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 支付成功之后的处理
     * @param orderId
     * @param price
     * @param currency
     * @param type
     */
    private void handleAfterPurchaseSuccess(String orderId, String price, String currency, String type) {
        LogUtils.e("第三方支付成功success_story: orderid-" + orderId + "  type-" + currency + " price-" + price + " type2-" + type);
        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("19"), 1);
        //支付追踪
        TrackingManager.purchaseTracking(PayWebActivity.this, SDKManager.getInstance().getUser().getUserId(), orderId, Double.valueOf(price), currency, type);

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

    class JSInterface {
        @JavascriptInterface
        public void fail_story() {
            LogUtil.e("第三方支付失败-----fail_story()");
            try {
                if (SDKManager.getInstance().getPurchaseCallBack() != null) {
                    SDKManager.getInstance().getPurchaseCallBack().onFailure("第三方支付失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }

        @JavascriptInterface
        public void success_story(String orderid, String type, String price, String type2) {
            //type:CNY  price:0.990000 type2:XSOLLA
            try {
                handleAfterPurchaseSuccess(orderid,price,type,type2);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }
    }
}
