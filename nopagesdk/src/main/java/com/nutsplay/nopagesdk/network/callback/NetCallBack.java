package com.nutsplay.nopagesdk.network.callback;

/**
 * Created by frank-ma on 2019-09-18 19:50
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface NetCallBack {

    void onSuccess(String result);

    void onFailure(String errorMsg);

}
