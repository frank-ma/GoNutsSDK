package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.os.RemoteException;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.nutsplay.nopagesdk.callback.InstallCallBack;
import com.nutsplay.nopagesdk.kernel.SDKManager;

/**
 * Created by frankma on 2020/7/8 10:55 AM
 * Email: frankma9103@gmail.com
 * Desc: 安装管理器，识别用户安装归因来源
 */
public class InstallManager {

    private static InstallManager INSTANCE;
    private static String TAG="InstallManager";
    private InstallReferrerClient referrerClient;

    public static InstallManager getInstance(){
        if (INSTANCE==null){
            synchronized (InstallManager.class){
                if (INSTANCE==null){
                    INSTANCE=new InstallManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 与Google商店建立连接
     *
     * @param activity
     */
    public void getInstallReferrer(final Activity activity, final InstallCallBack installCallBack){
        if (installCallBack == null) return;
        referrerClient = InstallReferrerClient.newBuilder(activity).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode){
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        // Connection established.
                        getInstallInfo(activity,referrerClient,installCallBack);
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        installCallBack.onFailure("Current Play Store app is not supported");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        installCallBack.onFailure("Connection couldn't be established.");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                        // DEVELOPER_ERROR
                        installCallBack.onFailure("DEVELOPER_ERROR");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                        // SERVICE_DISCONNECTED
                        installCallBack.onFailure("SERVICE_DISCONNECTED");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });

    }

    private void getInstallInfo(Activity activity, InstallReferrerClient referrerClient,InstallCallBack callBack) {
        if (callBack == null) return;

        ReferrerDetails response = null;
        try {
            response = referrerClient.getInstallReferrer();
            String referrerUrl = response.getInstallReferrer();
            long referrerClickTime = response.getReferrerClickTimestampSeconds();
            long appInstallTime = response.getInstallBeginTimestampSeconds();
            Log.e("GoogleInstallRefer",response.toString());
            Log.e("GoogleInstallRefer","referrerUrl---"+referrerUrl);
            Log.e("GoogleInstallRefer","referrerClickTime---"+referrerClickTime);
            Log.e("GoogleInstallRefer","appInstallTime---"+appInstallTime);
            if (referrerUrl != null && (referrerUrl.contains("fb") || referrerUrl.contains("facebook"))){
                Log.e(TAG,"user comes from facebook ads.");
            }
            //上传数据
            String content = referrerUrl;
            if (SDKManager.getInstance().getUser()!=null && SDKManager.getInstance().getUser().getUserId()!=null){
                String userID = SDKManager.getInstance().getUser().getUserId();
                content = referrerUrl;
            }
            SDKManager.getInstance().sdkUploadLog("installReferrer",content);
            callBack.onSuccess(referrerUrl);
            //断开连接，以免占用资源和泄露
            referrerClient.endConnection();
        } catch (RemoteException e) {
            e.printStackTrace();
            callBack.onFailure(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            callBack.onFailure(e.getMessage());
        }
    }

}
