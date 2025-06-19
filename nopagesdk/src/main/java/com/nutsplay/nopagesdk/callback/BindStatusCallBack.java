package com.nutsplay.nopagesdk.callback;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc: 绑定状态回调接口
 */
public interface BindStatusCallBack {

    void onSuccess(boolean isBind,String BindType);

    void onFailure(int code,String msg);

}
