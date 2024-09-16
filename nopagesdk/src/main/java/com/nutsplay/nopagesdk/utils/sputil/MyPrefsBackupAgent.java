package com.nutsplay.nopagesdk.utils.sputil;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.ParcelFileDescriptor;

import com.nutspower.commonlibrary.utils.LogUtils;

import java.io.IOException;

/**
 * Created by frankma on 2023/11/4 4:48 PM
 * Email: frankma9103@gmail.com
 * Desc: 备份SharedPreference帮助类
 * 官方文档
 * https://developer.android.com/guide/topics/data/keyvaluebackup?hl=zh-cn#BackupAgent
 *
 */
public class MyPrefsBackupAgent extends BackupAgentHelper {

    // The name of the SharedPreferences file
    static final String PREFS = "sp_nutsplay_client";

    // A key to uniquely identify the set of backup data
    static final String PREFS_BACKUP_KEY = "gosdk";

    private static String TAG = "MyPrefsBackupAgent";
    @Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PREFS);
        addHelper(PREFS_BACKUP_KEY, helper);
    }

    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
        super.onBackup(oldState, data, newState);
        LogUtils.e(TAG,"onBackup");
    }

    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
        super.onRestore(data, appVersionCode, newState);
        LogUtils.e(TAG,"onRestore");
    }
}
