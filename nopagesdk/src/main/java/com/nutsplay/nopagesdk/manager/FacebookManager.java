package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.nutsplay.nopagesdk.callback.ShareResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;

/**
 * Created by frankma on 2020/7/8 10:55 AM
 * Email: frankma9103@gmail.com
 * Desc: facebook管理器：分享
 * 文档地址：https://developers.facebook.com/documentation/app-events/getting-started-app-events-android
 *
 */
public class FacebookManager {

    private static FacebookManager INSTANCE;
    private static String TAG="FacebookManager";
//    private InstallReferrerClient referrerClient;

    private AppEventsLogger eventsLogger;

    public static FacebookManager getInstance(){
        if (INSTANCE == null){
            synchronized (FacebookManager.class){
                if (INSTANCE==null){
                    INSTANCE = new FacebookManager();
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

    /**
     * 打点事件
     * @param context
     * @param eventName
     */
    public void logEvent(Context context, String eventName) {
        try {
            if (context == null) {
                return;
            }
            if (eventsLogger == null) {
                eventsLogger = AppEventsLogger.newLogger(context);
            }
            eventsLogger.logEvent(eventName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void logEvent(Context context, String eventName,double valueToSum) {
        try {
            if (context == null) {
                return;
            }
            if (eventsLogger == null) {
                eventsLogger = AppEventsLogger.newLogger(context);
            }
            eventsLogger.logEvent(eventName,valueToSum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void logEvent(Context context, String eventName, Bundle parameters) {
        try {
            if (context == null) {
                return;
            }
            if (eventsLogger == null) {
                eventsLogger = AppEventsLogger.newLogger(context);
            }
            eventsLogger.logEvent(eventName,parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void logEvent(Context context, String eventName, double valueToSum, Bundle parameters) {
        try {
            if (context == null) {
                return;
            }
            if (eventsLogger == null) {
                eventsLogger = AppEventsLogger.newLogger(context);
            }
            eventsLogger.logEvent(eventName,valueToSum,parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
