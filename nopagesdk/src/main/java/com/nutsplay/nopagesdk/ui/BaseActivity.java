package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * Created by frankma on 2019-09-24 16:19
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BaseActivity extends Activity {

//    private boolean isRegistered = false;
//    private NetworkChangeReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//            WindowInsetsController insetsController = getWindow().getInsetsController();
//            if (insetsController != null) {
//                insetsController.hide(WindowInsets.Type.statusBars());
//                insetsController.hide(WindowInsets.Type.displayCutout());
//            }
//        }else {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //注册网络状态监听广播
//        netWorkChangReceiver = new NetworkChangeReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
//        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(netWorkChangReceiver, filter);
//        isRegistered = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (isRegistered && netWorkChangReceiver != null) {
//            unregisterReceiver(netWorkChangReceiver);
//        }
    }
}
