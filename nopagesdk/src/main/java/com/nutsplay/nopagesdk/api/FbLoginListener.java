package com.nutsplay.nopagesdk.api;

import com.nutsplay.nopagesdk.facebook.FacebookUser;

/**
 * Created by frankma on 2021/9/9 11:20 上午
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface FbLoginListener {
    void onSuccess(FacebookUser facebookUser);

    void onFailure(String msg);

    void onCancel();
}
