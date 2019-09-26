package com.nutsplay.nopagesdk.kernel;

import android.app.Application;

import org.xutils.x;

/**
 * Created by frankma on 2019-09-24 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }

}
