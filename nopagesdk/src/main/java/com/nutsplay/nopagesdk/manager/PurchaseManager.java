package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.android.billingclient.api.BillingClient;
import com.nutsplay.nopagesdk.billing.BillingManager;
import com.nutspower.commonlibrary.utils.LogUtils;


/**
 * Created by frankma on 2019-09-23 22:16
 * Email: frankma9103@gmail.com
 * Desc: Google支付，新版的Google支付功能，以前的是
 *
 */
public class PurchaseManager {

    private static String TAG = "PurchaseManager";

    private volatile static PurchaseManager INSTANCE;

    private BillingClient billingClient;

    private BillingManager billingManager;

    public static PurchaseManager getInstance() {
        if (INSTANCE == null) {
            synchronized (PurchaseManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PurchaseManager();
                }
            }
        }
        return INSTANCE;
    }


    public void requestGoogleIAP(Activity activity,String referenceId) {

        LogUtils.d(TAG,"发起Google内购");


        GooglePayHelp.getInstance().initGoogleIAP(activity,referenceId);

    }

}
