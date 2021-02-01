package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.ThirdLoginResultCallBack;
import com.nutsplay.nopagesdk.facebook.FacebookUser;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.ui.FBLoginActivity;
import com.nutsplay.nopagesdk.ui.GoogleLoginActivity;
import com.nutsplay.nopagesdk.utils.Installations;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.StringUtils;

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

    public interface FbLoginListener {

        void onSuccess(FacebookUser facebookUser);

        void onFailure(String msg);

        void onCancel();
    }

    public interface GoogleLoginListener {

        void onSuccess(String googleId,String displayName);

        void onFailure(int code,String msg);
    }

    private FbLoginListener fbloginListener;

    private GoogleLoginListener googleLoginListener;

    public FbLoginListener getFBLoginListener() {
        return fbloginListener;
    }

    public void setFBLoginListener(FbLoginListener loginListener) {
        this.fbloginListener = loginListener;
    }

    public GoogleLoginListener getGoogleLoginListener() {
        return googleLoginListener;
    }

    public void setGoogleLoginListener(GoogleLoginListener googleLoginListener) {
        this.googleLoginListener = googleLoginListener;
    }

    /**
     * FB登录
     *
     * @param activity
     * @param loginCallBack
     */
    public void facebookLogin(final Activity activity, final LoginCallBack loginCallBack,final ResultCallBack resultCallBack) {

        if (activity == null || loginCallBack == null) return;

        AppManager.startActivity(FBLoginActivity.class);
        setFBLoginListener(new FbLoginListener() {
            @Override
            public void onSuccess(FacebookUser user) {

                SDKManager.getInstance().sdkLoginThirdAccount(activity, user, SDKConstant.TYPE_FACEBOOK, loginCallBack,resultCallBack);
            }

            @Override
            public void onFailure(String msg) {
                if (msg!=null && !msg.isEmpty()) SDKToast.getInstance().ToastShow(msg,3);
                resultCallBack.onFailure(msg);
                loginCallBack.onFailure(SDKConstant.fb_login_error,msg);
            }

            @Override
            public void onCancel() {
                resultCallBack.onFailure("cancel");
                loginCallBack.onCancel();
            }
        });

    }

    /**
     * FB登录
     *
     * @param activity
     * @param resultCallBack
     */
    public void facebookLogin(final Activity activity, final ThirdLoginResultCallBack resultCallBack) {

        if (activity == null || resultCallBack == null) return;

        AppManager.startActivity(FBLoginActivity.class);
        setFBLoginListener(new FbLoginListener() {
            @Override
            public void onSuccess(FacebookUser user) {
                if (StringUtils.isNotBlank(user.getId())) resultCallBack.onSuccess(user.getId());
            }

            @Override
            public void onFailure(String msg) {
                resultCallBack.onFailure(msg);
            }

            @Override
            public void onCancel() {

            }
        });

    }

    /**
     * Google登录
     * @param activity
     * @param loginCallBack
     */
    public void googleLogin(final Activity activity, final LoginCallBack loginCallBack, final ResultCallBack resultCallBack) {

        if (activity == null || loginCallBack == null) return;

        AppManager.startActivity(GoogleLoginActivity.class);
        setGoogleLoginListener(new GoogleLoginListener() {
            @Override
            public void onSuccess(String googleId,String displayName) {
                SDKManager.getInstance().sdkLoginThirdAccount(activity, googleId,displayName, SDKConstant.TYPE_GOOGLE, loginCallBack,resultCallBack);
            }

            @Override
            public void onFailure(int code,String msg) {
                resultCallBack.onFailure(msg);
                loginCallBack.onFailure(SDKConstant.google_login_error,msg);
            }
        });

    }

    /**
     * 游客登录
     *
     * @param activity
     * @param loginCallBack
     */
    public void visitorLogin(Activity activity, LoginCallBack loginCallBack,ResultCallBack resultCallBack) {

        String visitor = Installations.id(activity);
        SDKManager.getInstance().sdkLoginThirdAccount(activity, visitor, "",SDKConstant.TYPE_GUEST, loginCallBack,resultCallBack);

    }



}
