package com.nutsplay.nopagesdk.callback;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc: 绑定结果回调接口
 */
public interface BindResultCallBack {

    void onSuccess();

    void onFail(int code,String msg);

    void onCancel();
}
