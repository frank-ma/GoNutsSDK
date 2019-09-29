package com.nutsplay.nopagesdk.callback;

import com.nutsplay.nopagesdk.beans.User;

/**
 * Created by frankma on 2019-09-26 17:42
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface SwitchAccountCallBack extends CallBack{

    void onSuccess(User user);

}
