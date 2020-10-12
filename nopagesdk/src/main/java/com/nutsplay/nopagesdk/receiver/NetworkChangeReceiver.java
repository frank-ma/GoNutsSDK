package com.nutsplay.nopagesdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.nutspower.commonlibrary.utils.LogUtils;

/**
 * Created by frankma on 2020/9/24 10:29 AM
 * Email: frankma9103@gmail.com
 * Desc: 网络状态监听
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private String TAG = getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())){
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);
            LogUtils.d(TAG,"wifiState:" + wifiState);
            switch (wifiState){
                case WifiManager.WIFI_STATE_DISABLED:

                    break;
                case WifiManager.WIFI_STATE_ENABLED:

                    break;
                default:
                    break;
            }
        }

        //监听网络连接，包括WiFi和移动数据的打开和关闭，以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()){
                    if (info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() == ConnectivityManager.TYPE_MOBILE){
                        LogUtils.d(TAG,getConnectionType(info.getType()) + "连上");
                    }
                }else {
                    LogUtils.d(TAG,getConnectionType(info.getType()) + "断开");
                }
            }
        }
    }

    private String getConnectionType(int type){
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE){
            connType = "3G";
        } else if (type == ConnectivityManager.TYPE_WIFI){
            connType = "wifi";
        }
        return connType;
    }

}
