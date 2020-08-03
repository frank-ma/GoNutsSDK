package com.nutsplay.nopagesdk.callback;

import com.nutsplay.nopagesdk.beans.User;

/**
 * Created by frank-ma on 2019-09-18 20:54
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface LoginCallBack extends CallBack{

    void onSuccess(User user);

    void onCancel();
}
