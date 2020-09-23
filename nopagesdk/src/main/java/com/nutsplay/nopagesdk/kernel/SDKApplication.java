package com.nutsplay.nopagesdk.kernel;

import androidx.multidex.MultiDexApplication;

import com.nutsplay.nopagesdk.db.DaoMaster;
import com.nutsplay.nopagesdk.db.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.xutils.x;

/**
 * Created by frankma on 2019-09-24 11:27
 * Email: frankma9103@gmail.com
 * Desc:
 */

public class SDKApplication extends MultiDexApplication {

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

}
