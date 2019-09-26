package com.nutsplay.nopagesdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by frank-ma on 2019/7/18 9:58 AM
 * Email: frankma9103@gmail.com
 * Desc:国内Android不同厂家的设置界面不一样，所以需要做适配
 */
public class SettingPageUtil {

    /**
     * 打开华为设置界面
     *
     * @param context
     */
    public static void goHuaWeiSettingPage(Context context){
        try {
            Intent intent = new Intent(context.getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager","com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            goDefaultSetting(context);
        }
    }

    /**
     * 打开摩托罗拉设置界面
     *
     * @param context
     */
    public static void goMotorolaSettingPage(Context context){
        try {
            Intent intent = new Intent(context.getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.android.settings","com.android.settings.SubSettings");
            intent.setComponent(comp);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            goDefaultSetting(context);
        }
    }

    /**
     * 打开摩托罗拉设置界面
     *
     * @param context
     */
    public static void goOnePlusSettingPage(Context context){
        try {
            Intent intent = new Intent(context.getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.android.settings","com.android.settings.applications.InstalledAppDetails");
            intent.setComponent(comp);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            goDefaultSetting(context);
        }
    }

    /**
     * 进去系统默认的设置界面
     *
     */
    public static void goSystemPermissionPage(Context context) {

        try {
            Intent intent = new Intent(context.getPackageName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.google.android.packageinstaller","com.android.packageinstaller.permission.ui.ManagePermissionsActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            goDefaultSetting(context);
        }
    }

    /**
     * 进去系统默认的设置界面
     *
     */
    public static void goDefaultSetting(Context context) {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",context.getPackageName(),null);
        intent.setData(uri);
        try {
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 跳转到自启动页面
     *
     * 华为 com.huawei.systemmanager/com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity
     * 小米 com.miui.securitycenter/com.miui.permcenter.autostart.AutoStartManagementActivity
     * vivo com.iqoo.secure/.ui.phoneoptimize.AddWhiteListActivity
     * oppo com.coloros.oppoguardelf/com.coloros.powermanager.fuelgaue.PowerUsageModelActivity
     *
     */
    /**
     * 红米note3，打开有权查看使用情况界面
     *
     * @param context
     */
    public static void selfStartManagerSettingIntent(Context context){

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ComponentName componentName = new ComponentName("com.huawei.systemmanager","com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        ComponentName componentName = new ComponentName("com.android.settings","com.android.settings.Settings$UsageAccessSettingsActivity");
//        ComponentName componentName = new ComponentName("com.miui.securitycenter","com.miui.permcenter.autostart.AutoStartManagementActivity");
//        ComponentName componentName = new ComponentName("com.android.systemui","com.android.systemui.recents.RecentsActivity");
        intent.setComponent(componentName);
        try{
            context.startActivity(intent);
        }catch (Exception e){//抛出异常就直接打开设置页面
            intent=new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }

}
