package com.nutsplay.nopagesdk.callback;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface InitCallBack{

    void onSuccess();

    void onFailure(int code,String msg);
}
