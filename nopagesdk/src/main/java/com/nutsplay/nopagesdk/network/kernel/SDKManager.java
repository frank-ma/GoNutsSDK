package com.nutsplay.nopagesdk.network.kernel;

import android.app.Activity;

import com.nutsplay.nopagesdk.network.beans.InitParameter;
import com.nutsplay.nopagesdk.network.callback.InitCallBack;

/**
 * Created by frank-ma on 2019-09-19 10:05
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKManager {

    private volatile static SDKManager INSTANCE;

    private InitParameter initParameter;

    private Activity activity;

    public static SDKManager getInstance() {
        if (INSTANCE == null) {
            synchronized (SDKManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SDKManager();
                }
            }
        }
        return INSTANCE;
    }

    public InitParameter getInitParameter() {
        return initParameter;
    }

    public void setInitParameter(InitParameter initParameter) {
        this.initParameter = initParameter;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void initSDK(Activity activity, InitParameter initParameter, InitCallBack initCallBack){

        if (activity == null){
            System.out.println("initSDK failed:Activity is null.");
            return;
        }

        if (initParameter == null){
            System.out.println("initSDK failed:InitParameter is null.");
            return;
        }

        if (initCallBack == null) {
            System.out.println("initSDK failed:InitCallBack is null.");
            return;
        }

        setInitParameter(initParameter);
        setActivity(activity);


    }
}
