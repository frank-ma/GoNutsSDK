package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.gamingservices.FriendFinderDialog;
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

    private static final String TAG = "FBLoginActivity";
    private CallbackManager callbackManager;
    private String name="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HandlerFacebookLogin();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //检查当前用户是否迁移到游戏服务
        if (accessToken != null && accessToken.getGraphDomain()!=null){
            Log.e(TAG,"facebook graphDomain:" + accessToken.getGraphDomain());
            if (accessToken.getGraphDomain().equals("gaming")){
                Log.e("facebook","当前用户已迁移到Facebook Login for games");
            }
        }
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            name = profile.getName();
            Uri uri = profile.getProfilePictureUri(50,50);//头像
            Log.e("facebook","游戏名:" + name + "  "+uri);
        }else{
            name = "";
        }




        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn){
            String facebookId = accessToken.getUserId();
            LogUtils.e("FacebookID",facebookId);
            if (com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener() != null) {
                com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onSuccess(facebookId,name);
            }
            finish();
        }else {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends"));
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


                        String domain = accessToken.getGraphDomain();
//                        openFriendFinderDeepLink(FBLoginActivity.this);

                        LogUtils.e("FacebookID",userId);

                        Profile profile = Profile.getCurrentProfile();
                        if (profile!=null){
                            name = profile.getName();
                        }



                        com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onSuccess(userId , name);
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

    /**
     * 打开好友列表深度链接
     * @param activity
     */
    public void openFriendFinderDeepLink(Activity activity){
        FriendFinderDialog dialog = new FriendFinderDialog(activity);
        dialog.registerCallback(callbackManager, new FacebookCallback<FriendFinderDialog.Result>() {
            @Override
            public void onSuccess(FriendFinderDialog.Result result) {
                Log.e(TAG,"friend Finder Dialog closed");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG,error.toString());
            }
        });
        dialog.show();
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
