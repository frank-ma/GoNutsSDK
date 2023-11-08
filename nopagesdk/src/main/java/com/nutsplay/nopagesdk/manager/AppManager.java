package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;

/**
 * Created by frank-ma on 2018/12/28 下午9:23
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class AppManager {

    public static void startActivity(Class activity){
        Activity currActivity= SDKManager.getInstance().getActivity();
        Intent intent=new Intent(currActivity,activity);
        currActivity.startActivity(intent);
//        currActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        currActivity.overridePendingTransition(0,0);
    }

    public static void startActivityWithData(Class activity, String... data){
        Activity currActivity= SDKManager.getInstance().getActivity();
        Intent intent=new Intent(currActivity,activity);
        intent.putExtra("OpenData0",data[0]);
        intent.putExtra("OpenData1",data[1]);
        currActivity.startActivity(intent);
        currActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public static void startActivityWithData(Activity activity,Intent intent){
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    /**
     * 判断应用是不是首次启动
     * @param activity
     */
    public  static  boolean isFirstRun(Activity activity){
        float nowVersionCode = getVersionCode(activity);
        float spVersionCode = SPManager.getInstance(activity).getFloat(SPKey.key_first_install);

        if (nowVersionCode > spVersionCode){
            //应用首次启动
            SPManager.getInstance(activity).putFloat(SPKey.key_first_install,nowVersionCode);
            return true;
        }else {
            //应用非首次启动
            SPManager.getInstance(activity).putFloat(SPKey.key_first_install,nowVersionCode);
            return false;
        }
    }

    public static float getVersionCode(Context context){
        float versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return versionCode;
    }

}
