package com.nutsplay.nopagesdk.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.nutspower.commonlibrary.utils.LogUtils;

import java.util.Arrays;

/**
 * Created by frankma on 2019-11-19 17:10
 * Email: frankma9103@gmail.com
 * Desc: facebook登录页
 */
public class FBLoginActivity extends BaseActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HandlerFacebookLogin();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn){
            String facebookId = accessToken.getUserId();
            LogUtils.e("FacebookID",facebookId);
            com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onSuccess(facebookId);
            finish();
        }else {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }
    }

    /**
     * 处理FB登录回调
     */
    private void HandlerFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //登录成功
                        AccessToken accessToken = loginResult.getAccessToken();
                        String userId = accessToken.getUserId();
                        LogUtils.e("FacebookID",userId);
                        com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onSuccess(userId);
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        LogUtils.e("FacebookID",exception.getMessage());
                        com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onFailure(exception.getMessage());
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (callbackManager != null) callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
