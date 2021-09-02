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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.gamingservices.FriendFinderDialog;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nutsplay.nopagesdk.facebook.FacebookUser;
import com.nutsplay.nopagesdk.facebook.UserRequest;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutspower.commonlibrary.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by frankma on 2019-11-19 17:10
 * Email: frankma9103@gmail.com
 * Desc: facebook登录页
 */
public class FBLoginActivity extends BaseActivity {

    private static final String TAG = "FBLoginActivity";
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private static final String USER_POSTS = "user_posts";
    private static final String AUTH_TYPE = "rerequest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        int openType = getIntent().getIntExtra(SDKConstant.openType,0);
        if (openType == 1){
            //分享链接
            facebookShareUrl(this);
        }else {
            //登录
            doLogin();
        }
    }

    private void doLogin(){
        HandlerFacebookLogin();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //检查当前用户是否迁移到游戏服务
        if (accessToken != null && accessToken.getGraphDomain()!=null){
            Log.e(TAG,"facebook graphDomain:" + accessToken.getGraphDomain());
            if ("gaming".equals(accessToken.getGraphDomain())){
                Log.e("facebook","当前用户已迁移到Facebook Login for games");
            }
        }

        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn){
            String facebookId = accessToken.getUserId();
            LogUtils.e("FacebookID",facebookId);
            getFacebookUserInfo();
            finish();
        }else {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(EMAIL));//"public_profile","user_friends"
        }
    }



    /**
     * 处理FB登录回调
     */
    private void HandlerFacebookLogin() {


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //登录成功
                        getFacebookUserInfo();
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        if (com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener()!=null){
                            com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onCancel();
                        }
                        finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        LogUtils.e("FacebookID",exception.getMessage());
                        if (com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener()!=null){
                            com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onFailure(exception.getMessage());
                        }
                        finish();
                    }
                });
    }

    /**
     * 获取用户FB信息
     */
    private void getFacebookUserInfo() {
        UserRequest.makeUserRequest(new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {

                try {
                    JSONObject userObj = response.getJSONObject();
                    if (userObj == null)return;

                    FacebookUser facebookUser = jsonToUser(userObj);
                    Log.e(TAG,"fb_email:" + facebookUser.getEmail());
                    if (com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener() != null) {
                        com.nutsplay.nopagesdk.manager.LoginManager.getInstance().getFBLoginListener().onSuccess(facebookUser);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private FacebookUser jsonToUser(JSONObject userObj) {
        try {
            Uri picture = Uri.parse(userObj.getJSONObject("picture").getJSONObject("data").getString("url"));
            String name = userObj.getString("name");
            String id = userObj.getString("id");
            String email = "";
            if (userObj.has("email")) {
                email = userObj.getString("email");
            }
            // Build permissions display string
            StringBuilder builder = new StringBuilder();
            JSONArray perms = userObj.getJSONObject("permissions").getJSONArray("data");
            builder.append("Permissions:\n");
            for (int i = 0; i < perms.length(); i++) {
                builder
                        .append(perms.getJSONObject(i).get("permission"))
                        .append(": ")
                        .append(perms.getJSONObject(i).get("status"))
                        .append("\n");
            }
            String permissions = builder.toString();
            return new FacebookUser(picture,name,id,email,permissions);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new FacebookUser();
    }

    /**
     * Facebook分享应用链接
     * @param context
     */
    public void facebookShareUrl(Activity context){

        String shareUrl = getIntent().getStringExtra(SDKConstant.share_url);
        if (shareUrl == null || shareUrl.isEmpty()) return;

        ShareDialog shareDialog = new ShareDialog(context);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                SDKManager.getInstance().getShareResultCallBack().onSuccess();
                finish();
            }

            @Override
            public void onCancel() {
                SDKManager.getInstance().getShareResultCallBack().onCancel();
                finish();
            }

            @Override
            public void onError(FacebookException error) {
                SDKManager.getInstance().getShareResultCallBack().onFailure(SDKConstant.fb_share_error,error.getMessage());
                finish();
            }
        });

        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(shareUrl))
                .build();
        shareDialog.show(shareLinkContent, ShareDialog.Mode.AUTOMATIC);
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

//    @Override
//    public void finish() {
//        overridePendingTransition(0,0);
//        super.finish();
//    }
}
