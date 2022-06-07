package com.nutsplay.nopagesdk.callback;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface AgreementCallBack{

    void onSuccess();

    void onCancel();

    void onFail(int code,String msg);

}
