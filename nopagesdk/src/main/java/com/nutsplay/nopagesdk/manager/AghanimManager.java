package com.nutsplay.nopagesdk.manager;

import android.content.Context;

/**
 * Created by frankma on 2023/10/24 9:36 PM
 * Email: frankma9103@gmail.com
 * Desc:  Aghanim三方支付系统
 * 文档：
 * https://docs.aghanim.com/zh/checkout/integrate/android/?mode=browser
 */
public class AghanimManager {

    private static final String TAG = "AghanimManager";
    private volatile static AghanimManager INSTANCE;

//    public Aghanim aghanim;
    public static AghanimManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AghanimManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new AghanimManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context){

//        aghanim = new Aghanim(context,"sdk_sandbox_t3MsipPC2XNR8DpZvMb1ZBGBqL5ThmFN");

//        AghanimKt aghanimKt = new AghanimKt();
//        String s = aghanimKt.testPrint();
//        Log.e(TAG,s);
//        aghanimKt.init(context,"sdk_sandbox_t3MsipPC2XNR8DpZvMb1ZBGBqL5ThmFN");


    }

}
