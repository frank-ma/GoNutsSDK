package com.nutsplay.nonutssdk;


import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.facebook.stetho.Stetho;
import com.helpshift.Helpshift;
import com.helpshift.UnsupportedOSVersionException;
import com.nutsplay.nopagesdk.kernel.SDKApplication;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frankma on 2019-09-29 01:35
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class App extends SDKApplication {



    @Override
    public void onCreate() {
        super.onCreate();

//        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
        initAdjust();
        initHelpShift();

    }

    /**
     * 客服系统
     */
    private void initHelpShift() {
        try {
            Map<String, Object> config = new HashMap<>();
            //打开客服系统
            config.put("enableLogging",  true);
//            config.put("enableInAppNotification", false);
            //屏幕方向
//            config.put("screenOrientation", ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            //自定义通知Icon
//            config.put("notificationIcon", R.drawable.notification_icon);
//            config.put("notificationChannelId",  "your channel name here");
            ///todo 添加配置  初始化helpshift api
            Helpshift.install(this,
                    "algames_platform_20230609004001142-6097eb4ea8c6e4a",
                    "algames.helpshift.com",
                    config);
        } catch (UnsupportedOSVersionException e) {
            // Android OS versions prior to Lollipop (< SDK 21) are not supported.
        }
    }

    /**
     * 初始化Adjust统计平台
     */
    public void initAdjust() {
        String appToken = "7bwy6y88uhhc";
//        ENVIRONMENT_SANDBOX 沙盒模式； ENVIRONMENT_PRODUCTION 生产模式；自己视情况切换
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
//        config.setLogLevel(LogLevel.DEBUG);//可以更改日志的级别
        config.setLogLevel(LogLevel.VERBOSE); // enable all logs
//        config.setLogLevel(LogLevel.DEBUG); // disable verbose logs
//        config.setLogLevel(LogLevel.INFO); // disable debug logs (default)
//        config.setLogLevel(LogLevel.WARN); // disable info logs
//        config.setLogLevel(LogLevel.ERROR); // disable warning logs
//        config.setLogLevel(LogLevel.ASSERT); // disable error logs
//        config.setLogLevel(LogLevel.SUPRESS); // disable all logs
        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Adjust.onResume();
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
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
        });
    }
}
