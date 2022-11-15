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
        initAdjust();
    }

    /**
     * 初始化Adjust统计平台
     */
    public void initAdjust() {
//        String appToken = "Adjust的appToken";
//        ENVIRONMENT_SANDBOX 沙盒模式； ENVIRONMENT_PRODUCTION 生产模式；自己视情况切换
//        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
//        AdjustConfig config = new AdjustConfig(this, appToken, environment);
//        config.setLogLevel(LogLevel.DEBUG);//可以更改日志的级别
//        config.setLogLevel(LogLevel.VERBOSE); // enable all logs
//        config.setLogLevel(LogLevel.DEBUG); // disable verbose logs
//        config.setLogLevel(LogLevel.INFO); // disable debug logs (default)
//        config.setLogLevel(LogLevel.WARN); // disable info logs
//        config.setLogLevel(LogLevel.ERROR); // disable warning logs
//        config.setLogLevel(LogLevel.ASSERT); // disable error logs
//        config.setLogLevel(LogLevel.SUPRESS); // disable all logs
//        Adjust.onCreate(config);
    }

}
