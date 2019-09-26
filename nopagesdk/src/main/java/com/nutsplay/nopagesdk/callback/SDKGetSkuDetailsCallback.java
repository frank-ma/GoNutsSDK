package com.nutsplay.nopagesdk.callback;

import com.android.billingclient.api.SkuDetails;

import java.util.List;

/**
 * Created by frankma on 2019-09-25 12:07
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface SDKGetSkuDetailsCallback extends CallBack {

    void onSuccess(List<SkuDetails> skuDetails);
}
