package com.nutsplay.nopagesdk.callback;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.User;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface InitCallBack extends CallBack{

    void onSuccess(@Nullable User user);

}
