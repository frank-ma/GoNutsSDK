package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.receiver.NetworkChangeReceiver;

/**
 * Created by frankma on 2019-09-24 16:19
 * Email: frankma9103@gmail.com
 * Desc:  让游戏的UnityPlayerActivity继承该SDKBaseActivity,以便注册该网络状态监听广播
 *
 */
public class SDKBaseActivity extends Activity {

    private boolean isRegistered = false;
    private NetworkChangeReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册网络状态监听广播
        netWorkChangReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
        isRegistered = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegistered && netWorkChangReceiver != null) {
            unregisterReceiver(netWorkChangReceiver);
        }
    }
}
