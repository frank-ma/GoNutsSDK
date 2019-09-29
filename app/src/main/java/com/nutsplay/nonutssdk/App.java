package com.nutsplay.nonutssdk;



import com.facebook.stetho.Stetho;
import com.nutsplay.nopagesdk.kernel.SDKApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by frankma on 2019-09-29 01:35
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class App extends SDKApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
    }

}
