package com.nutsplay.nopagesdk.callback;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface BindFBCallback{

    void onSuccess(boolean isBindFB);

    void onFail(int code,String msg);
}
