//package com.nutsplay.nopagesdk.manager;
//
//import android.content.Context;
//import android.os.Bundle;
//
//
//import com.nutsplay.nopagesdk.beans.User;
//import com.nutspower.commonlibrary.utils.StringUtils;
//
///**
// * Created by frankma on 2025/7/15 15:12
// * Email: frankma9103@gmail.com
// * Desc: Firebase Google Analytics分析
// * 文档地址：
// * https://firebase.google.com/docs/analytics/get-started?hl=zh-CN&authuser=0&_gl=1*75zqca*_ga*MTExMTkyMDcyMC4xNjQ1NTI1NDI2*_ga_CW55HF8NVT*czE3NTI1NjMzMjEkbzMzJGcxJHQxNzUyNTYzNDYzJGoyMCRsMCRoMA..&platform=ios
// */
//public class FirebaseManager {
//
//    private static FirebaseManager INSTANCE;
//    private FirebaseAnalytics mFirebaseAnalytics;
//
//    public static FirebaseManager getInstance() {
//        if (INSTANCE == null) {
//            synchronized (FirebaseManager.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new FirebaseManager();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public void init(Context context) {
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
//    }
//
//    /**
//     * 记录事件
//     *
//     * @param name  FirebaseAnalytics.Event.
//     * @param bundle
//     */
//    public void logEvent(String name, Bundle bundle) {
////        Bundle bundle = new Bundle();
////        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
////        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
////        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        if (mFirebaseAnalytics != null) {
//            mFirebaseAnalytics.logEvent(name, bundle);
//        }
//    }
//
//    public void loginEvent(User user){
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, user.getUserId());
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, user.getSdkmemberType());
//        if (mFirebaseAnalytics != null) {
//            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
//        }
//    }
//    public void registerEvent(User user){
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, user.getUserId());
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, user.getSdkmemberType());
//        if (mFirebaseAnalytics != null) {
//            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
//        }
//    }
//
//    public void setUserID(String userID) {
//        if (StringUtils.isEmpty(userID)) return;
//        if (mFirebaseAnalytics != null) {
//            mFirebaseAnalytics.setUserId(userID);
//        }
//    }
//
//
//}
