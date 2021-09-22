package com.nutsplay.nopagesdk.kernel;

import androidx.multidex.MultiDexApplication;

import com.nutsplay.nopagesdk.manager.TrackingManager;

import org.xutils.x;

/**
 * Created by frankma on 2019-09-24 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */

public abstract class SDKApplication extends MultiDexApplication {

    private static SDKApplication context;
    private static final String AF_DEV_KEY = "VBmCBKvNg5uvd4iiLZSx7J";

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        context = this;
        TrackingManager.trackingInit(this, AF_DEV_KEY);
    }


    public static SDKApplication getInstance() {
        return context;
    }
}
