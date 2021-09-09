package com.nutsplay.nopagesdk.api;

/**
 * Created by frankma on 2021/9/9 11:20 上午
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface GoogleLoginListener {

    void onSuccess(String googleId,String displayName);

    void onFailure(int code,String msg);
}
