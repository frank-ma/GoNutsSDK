package com.nutsplay.nopagesdk.kernel;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.adjust.sdk.Adjust;
import com.nutsplay.nopagesdk.manager.TrackingManager;

import org.xutils.x;

/**
 * Created by frankma on 2019-09-24 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */

public abstract class SDKApplication extends MultiDexApplication {

    //    private DaoSession daoSession;
    private static SDKApplication context;
    private static final String AF_DEV_KEY = "VBmCBKvNg5uvd4iiLZSx7J";

    public abstract void initAdjust();

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
//        initGreenDao();
        context = this;
        initAdjust();
        TrackingManager.trackingInit(this, AF_DEV_KEY);
    }


    public static SDKApplication getInstance() {
        return context;
    }

//    private void initGreenDao() {

    // regular SQLite database
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "purchase_record");
//        Database db = helper.getWritableDb();
//        daoSession = new DaoMaster(db).newSession();
//    }

//    public DaoSession getDaoSession() {
//        return daoSession;
//    }

    public static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

    }
}
