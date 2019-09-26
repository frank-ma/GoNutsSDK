package com.nutsplay.nopagesdk.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;
import com.nutspower.commonlibrary.utils.LogUtils;
import com.nutspower.commonlibrary.utils.StringUtils;


/**
 * Created by frank-ma on 2018/12/26 下午5:28
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class DeviceUtils {

    public static final int REQUEST_CODE_READ_PHONE_STATE = 0x8;

    private static String sDeviceID;

    @SuppressLint("HardwareIds")
    public String getDeviceId(Activity context) {

        if (sDeviceID != null && StringUtils.isNotBlank(sDeviceID) && !"unknown".equals(sDeviceID)) {
            return sDeviceID;

        } else if (checkPermission(context)) {

            String deviceId = null;
            if (checkPhoneState(context)) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null) {
                    try {
                        deviceId = tm.getDeviceId();
                    } catch (SecurityException e) {
                        deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                    }
                }
            }

            if (deviceId != null) {

                LogUtils.i("commonUtil", "deviceId:" + deviceId);
                sDeviceID = deviceId;
                return deviceId;
            } else {

                LogUtils.w("commonUtil", "deviceId is null");
                return "unknown";
            }
        } else {

            sDeviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            LogUtils.e("lost permission---->android.permission.READ_PHONE_STATE+ deviceId--" + sDeviceID);
            return "unknown";
        }
    }

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceID(Activity context) {

        String deviceId = null;

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                deviceId = tm.getDeviceId();
                if (deviceId != null) {
                    sDeviceID = deviceId;
                    LogUtils.e("deviceID-deviceid:" + sDeviceID);
                    return sDeviceID;
                }
            }
        }


        if (deviceId == null) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (deviceId != null) {
                sDeviceID = deviceId;
                LogUtils.e("deviceID-androidid:" + sDeviceID);
                return sDeviceID;
            }
        }

        return "unkown";
    }


//    /**
//     * 获取设备ID
//     * @param context
//     * @return
//     */
//    @SuppressLint("HardwareIds")
//    public static String getDeviceID(Activity context) {
//
//        String deviceId = null;
//        if (checkPermission(context)) {
//
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (tm != null) {
//                deviceId = tm.getDeviceId();
//                if (deviceId != null) {
//                    sDeviceID = deviceId;
//                    LogUtils.e("deviceID-deviceid:"+sDeviceID);
//                    return sDeviceID;
//                }
//            }
//        }
//
//        if (deviceId == null) {
//            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//            if (deviceId != null) {
//                sDeviceID = deviceId;
//                LogUtils.e("deviceID-androidid:"+sDeviceID);
//                return sDeviceID;
//            }
//        }
//
//        return "unkown";
//    }


    public static boolean checkPermissions(Context context, String permission) {

        if (context == null) {
            return false;
        } else {
            PackageManager localPackageManager = context.getPackageManager();
            if (localPackageManager == null) {
                return false;
            } else {
                return localPackageManager.checkPermission(permission,
                        context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
            }
        }
    }

    private static boolean checkPhoneState(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.checkPermission("android.permission.READ_PHONE_STATE",
                context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查有无读取设备状态的权限
     *
     * @param context
     */
    public static boolean checkPermission(Activity context) {
        if (context == null) return false;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_LOGS) != PackageManager.PERMISSION_GRANTED ||
                //以下为新增
                ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(context, new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.READ_LOGS,


                    Manifest.permission.CAMERA,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,

            }, REQUEST_CODE_READ_PHONE_STATE);
            return false;
        } else {
            LogUtils.e("the permission of '" + Manifest.permission.READ_PHONE_STATE + "' is enable.");
            return true;
        }
    }

    /**
     * 显示
     *
     * @param context
     */
    public static void showDialog(final Context context) {
        //TODO 首先检查是否有权限， 有权限直接读写， 没有权限 拉起权限
        //有权限  跳过拉起设置权限步骤
        //没有权限 走以下步骤

        try {
            int time = SPManager.getInstance(context).getInt(SPKey.key_usage_stats_dialog_show_times,0);
            if (time > 0) return;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), context.getPackageName());
                boolean granted = mode == AppOpsManager.MODE_ALLOWED;
                if (granted) return;
            } else {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context,android.R.style.Theme_DeviceDefault_Light_Dialog);
        builder.setTitle("友情提示");
        builder.setMessage("您还没有授予游戏\"允许查看使用情况\"的权限， 为了帮助我们改进游戏使您获得更好的游戏体验，建议您开启该权限。");
        builder.setPositiveButton("去开启", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SettingPageUtil.selfStartManagerSettingIntent(context);
                dialog.cancel();

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();

                    }
                });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e("oncancel","usage stats dialog canceled.");
                SPManager.getInstance(context).putInt(SPKey.key_usage_stats_dialog_show_times,1);
            }
        });

        AlertDialog dialog1 = builder.show();
        dialog1.setCanceledOnTouchOutside(false);
    }

}
