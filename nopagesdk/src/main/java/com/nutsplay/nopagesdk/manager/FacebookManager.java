package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.net.Uri;

import com.android.installreferrer.api.InstallReferrerClient;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nutsplay.nopagesdk.callback.ShareResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;

/**
 * Created by frankma on 2020/7/8 10:55 AM
 * Email: frankma9103@gmail.com
 * Desc: facebook管理器：分享
 */
public class FacebookManager {

    private static FacebookManager INSTANCE;
    private static String TAG="FacebookManager";
    private InstallReferrerClient referrerClient;

    public static FacebookManager getInstance(){
        if (INSTANCE == null){
            synchronized (FacebookManager.class){
                if (INSTANCE==null){
                    INSTANCE=new FacebookManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Facebook分享应用链接
     * @param context
     * @param httpUrl
     */
    public void facebookShareUrl(Activity context, String httpUrl, final ShareResultCallBack resultCallBack){

        CallbackManager callbackManager = CallbackManager.Factory.create();
        ShareDialog shareDialog = new ShareDialog(context);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                resultCallBack.onSuccess();
            }

            @Override
            public void onCancel() {
                resultCallBack.onCancel();
            }

            @Override
            public void onError(FacebookException error) {
                resultCallBack.onFailure(SDKConstant.fb_share_error,error.getMessage());
            }
        });

        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(httpUrl))
                .build();
        shareDialog.show(shareLinkContent, ShareDialog.Mode.AUTOMATIC);
    }
}
