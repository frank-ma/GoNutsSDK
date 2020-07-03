package com.nutsplay.nopagesdk.callback;

import com.nutsplay.nopagesdk.beans.User;

/**
 * Created by frankma on 2019-09-23 20:12
 * Email: frankma9103@gmail.com
 * Desc:
 */
public interface RegisterCallBack extends CallBack {

  void onSuccess(User user);
}
