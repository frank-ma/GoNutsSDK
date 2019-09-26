package com.nutsplay.nopagesdk.callback;

import com.nutsplay.nopagesdk.beans.PayResult;

/**
 * Created by frankma on 2019-09-23 21:11
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface PurchaseCallBack extends CallBack {

    void onSuccess(PayResult payResult);

    void onCancel();
}
