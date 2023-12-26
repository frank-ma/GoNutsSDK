package com.nutsplay.nopagesdk.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.ui.TipDialog;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;

import org.xutils.common.util.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by frankma on 2022/3/17 3:52 下午
 * Email: frankma9103@gmail.com
 * Desc: 文件操作类
 */
public class FileUtils {

    static final String FilePath = Environment.getExternalStorageDirectory() + "/GoSDK";
    static final String FileName = "lastLogin.txt";
    static final String Path = "/GoSDK";

    /**
     * 判断sdcrad是否已经安装
     *
     * @return boolean true安装 false 未安装
     */
    private static boolean isSDCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 得到sdcard的路径
     *
     * @return
     */
    private static String getSDCardRoot() {
        System.out.println(isSDCardMounted() + Environment.getExternalStorageState());
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

//-----------------------------------
//    android 13文件存储适配 android文件存储实例
//    https://blog.51cto.com/u_13317/7226487


 /**
  *  Checks if external storage is available for read and write
  */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 创建文件的路径及文件
     *
     * @param path     路径，方法中以默认包含了sdcard的路径，path格式是"/path...."
     * @param filename 文件的名称
     * @return 返回文件的路径，创建失败的话返回为空
     */
    public static String createMkdirsAndFiles(String path, String filename) {
        if (TextUtils.isEmpty(path)) {
            System.out.println("路径为空");
        }
        path = getSDCardRoot() + path;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),path);
        if (!file.exists()) {
            try {
                boolean mkdirs = file.mkdirs();
                System.out.println("创建文件夹结果：" + mkdirs);
            } catch (Exception e) {
                System.out.println("创建文件夹不成功");
            }
        }
        if (!file.exists()) return "";
        File f = new File(file, filename);
        if (!f.exists()) {
            try {
                boolean newFile = f.createNewFile();
                System.out.println("创建文件结果：" + newFile);
            } catch (Exception e) {
                System.out.println("创建文件不成功");
            }
        }
        return f.getAbsolutePath();
    }

    /**
     * 在根目录创建文件filename
     * @param filename
     * @return
     */
    public static String createMkdirsAndFiles(String filename) {
        File f = new File(getSDCardRoot(), filename);
        if (!f.exists()) {
            try {
                boolean newFile = f.createNewFile();
                if (newFile){
                    System.out.println("创建文件成功"+filename);
                }
            } catch (Exception e) {
//                throw new RuntimeException("创建文件不成功");
                System.out.println("创建文件不成功");
            }
        }
        return f.getAbsolutePath();
    }

    /**
     * 把内容写入文件
     * 0：游客登录
     * 1：FB登录
     *
     * @param text 内容
     */
    public static void write2File(Activity context, String text) {

        if (text == null || text.isEmpty()) return;
        //如果用户没有授予访问sdcard文件的权限的话，就存储在SharedPreference里
        if (!hasPermission(context)) {
            SPManager.getInstance(context).putString(SPKey.Last_login,text);
            return;
        }

        String path = createMkdirsAndFiles("/"+context.getPackageName()+Path, getFileName(context));
//        String path = createMkdirsAndFiles(FileName);
        File file = new File(path);
        if (!file.exists()) {
            SPManager.getInstance(context).putString(SPKey.Last_login,text);
            return;
        }
        FileOutputStream fos = null;
        //先清空再写入
        try {
            //无论是否写入文件正常，都写入SP存储
            SPManager.getInstance(context).putString(SPKey.Last_login,text);
            //写入文件
            boolean canWrite = file.canWrite();
            if (canWrite){
                fos = new FileOutputStream(file);
                byte[] buffer = text.getBytes();
                fos.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件内容
     *
     * @return
     */
    public static String readFile(Activity context) {

        boolean writable = isExternalStorageWritable();
        System.out.println(writable);

        //如果用户没有授予访问sdcard文件的读写权限的话，就存储在SharedPreference里
        if (!hasPermission(context)) {
            return SPManager.getInstance(context).getString(SPKey.Last_login,"");
        }

        String path = createMkdirsAndFiles(Path, getFileName(context));
        File file = new File(path);
        if (!file.exists()) {
            return SPManager.getInstance(context).getString(SPKey.Last_login,"");
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuilder sb = new StringBuilder();
            while ((readline = br.readLine()) != null) {
                sb.append(readline);
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            //如果用户没有授予访问sdcard文件的读写权限的话，就读取存储在SharedPreference里的
            return SPManager.getInstance(context).getString(SPKey.Last_login,"");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileName(Context context){
        return getAppName(context) + FileName;
    }

    private static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("路径为空");
        }
        File file = new File(path);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 检查有无读取设备状态的权限
     *
     * @param context
     */
    public static boolean checkPermission(Activity context) {
        adjustFirstLogin(context);
       // NutsToast.getInstence().ToastShow(context, NutsLangConfig.getInstance().findMessage("ask_permission"), 3);
        boolean hasPermission = false;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有权限，弹出授权提示框
            String content = SDKLangConfig.getInstance().findMessage("permission_tips");
            new TipDialog.Builder(context,"Permission",content).create().show();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x13);
//            }
            hasPermission = false;
        } else {
            LogUtil.e("the permission of '" + Manifest.permission.READ_PHONE_STATE + "' is enable.");
            hasPermission = true;
        }

        return hasPermission;
    }

    /**
     * 判断是否是卸载重装的新用户
     */
    private static void adjustFirstLogin(Context context) {

        try {
            boolean firstInit = SPManager.getInstance(context).getBoolean(SPKey.key_first_init,true);
            //赋值false
            if (firstInit){
                SPManager.getInstance(context).putBoolean(SPKey.key_first_init,false);
            }
        }catch (Exception e) {
        }
    }

    /**
     * 检查有无读取设备状态的权限
     *
     * @param context
     */
    public static boolean hasPermission(Activity context) {
        boolean hasPermission = false;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("lost of permission-" + Manifest.permission.READ_PHONE_STATE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE},101);
//            }
            hasPermission = false;
        } else {
            LogUtil.e("the permission of '" + Manifest.permission.READ_PHONE_STATE + "' is enable.");
            hasPermission = true;
        }
        return hasPermission;
    }
}

