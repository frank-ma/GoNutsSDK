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

//    private void initHelpShift() {
//        /**
//         * 初始化客服系统HelpShift
//         *
//         */
//        Core.init(Support.getInstance());
//        InstallConfig installConfig = new InstallConfig.Builder()
//                .setEnableInAppNotification(true)
//                .build();
//        try {
//            Core.install(this,
//                    "ede1e4fac3a77f37b7742b273b1b8ceb",
//                    "freetrialshenqiwangluo.helpshift.com",
//                    "freetrialshenqiwangluo_platform_20200528025919539-3daee148250700c",
//                    installConfig);
//        } catch (InstallException e) {
//            Log.e("Helpshift", "install call : " + e);
//        }
//    }


}
