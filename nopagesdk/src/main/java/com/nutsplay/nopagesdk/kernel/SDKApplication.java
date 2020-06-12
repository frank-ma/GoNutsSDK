package com.nutsplay.nopagesdk.kernel;

import android.app.Application;

import com.nutsplay.nopagesdk.db.DaoMaster;
import com.nutsplay.nopagesdk.db.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.xutils.x;

/**
 * Created by frankma on 2019-09-24 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKApplication extends Application {

    private DaoSession daoSession;
    private static SDKApplication context;

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        initGreenDao();
        context = this;
//        initHelpShift();
    }



    public static SDKApplication getInstance() {
        return context;
    }

    private void initGreenDao() {

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "purchase_record");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

//    /**
//     * 初始化客服系统HelpShift
//     *
//     */
//    private void initHelpShift() {
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
//            LogUtils.e("Helpshift install call : " + e);
//        }
//    }
}
