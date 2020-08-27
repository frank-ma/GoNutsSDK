package com.nutsplay.nopagesdk.callback;

/**
 * Created by frankma on 2019-09-26 17:10
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface LogOutCallBack{

    void onSuccess();

    void onFailure(int code,String msg);
}
