package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.Installations;

/**
 * Created by frankma on 2019-09-23 22:05
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class LoginManager {

    private volatile static LoginManager INSTANCE;

    public static LoginManager getInstance() {

        if (INSTANCE == null) {
            synchronized (LoginManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginManager();
                }
            }
        }
        return INSTANCE;
    }

    public void facebookLogin(Activity activity,LoginCallBack loginCallBack) {

        String facebookId = doFacebookLogin();
        SDKManager.getInstance().sdkLoginThirdAccount(activity, facebookId, SDKConstant.TYPE_FACEBOOK, loginCallBack);
    }

    public void googleLogin(Activity activity,LoginCallBack loginCallBack) {

        String googleId = doGoogleLogin();
        SDKManager.getInstance().sdkLoginThirdAccount(activity, googleId, SDKConstant.TYPE_GOOGLE, loginCallBack);

    }

    public void visitorLogin(Activity activity, LoginCallBack loginCallBack) {

        String visitor = Installations.id(activity);
        SDKManager.getInstance().sdkLoginThirdAccount(activity, visitor, SDKConstant.TYPE_GUEST, loginCallBack);

    }



    /**
     * Facebook登录
     *
     * @return
     */
    private String doFacebookLogin() {

        return "131192007929843";//马小风FacebookID
    }

    /**
     * Google登录
     *
     * @return
     */
    private String doGoogleLogin() {

        return "";
    }

}
