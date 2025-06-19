package com.nutsplay.nopagesdk.kernel;

import android.app.Activity;
import android.net.Uri;

import com.nutsplay.nopagesdk.api.FbLoginListener;
import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.callback.AgreementCallBack;
import com.nutsplay.nopagesdk.callback.BindFBCallback;
import com.nutsplay.nopagesdk.callback.BindGoogleCallback;
import com.nutsplay.nopagesdk.callback.BindResultCallBack;
import com.nutsplay.nopagesdk.callback.BindStatusCallBack;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.InstallCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.callback.ShareResultCallBack;
import com.nutsplay.nopagesdk.callback.SocialBindCallBack;
import com.nutsplay.nopagesdk.manager.AIHelpManager;
import com.nutsplay.nopagesdk.manager.AdjustTraceManager;
import com.nutsplay.nopagesdk.manager.GoogleAPI;
import com.nutspower.commonlibrary.utils.StringUtils;

import java.util.List;

/**
 * Created by frank-ma on 2019-09-19 10:05
 * Email: frankma9103@gmail.com
 * Desc: 这是给游戏方调用的接口
 */
public class SDK {

    private volatile static SDK INSTANCE;

    public static SDK getInstance() {
        if (INSTANCE == null) {
            synchronized (SDK.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SDK();
                }
            }
        }
        return INSTANCE;
    }


    //********************************SDK无UI接口******************************************
    //********************************SDK无UI接口******************************************
    /**
     * 初始化接口
     *
     * @param activity
     * @param initParameter
     * @param initCallBack
     */
    public void initSDK(Activity activity, InitParameter initParameter, InitCallBack initCallBack) {
        SDKManager.getInstance().initSDK(activity, initParameter, initCallBack);
    }

    /**
     * 登录 UI
     * @param activity
     * @param loginCallBack
     */
    public void sdkLogin(Activity activity,LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkLogin(activity,loginCallBack,true);
    }

    /**
     * 社交平台账号登录
     * @param loginType 登录类型
     * @param loginCallBack 回调接口
     */
    public void LoginBySocial(Activity activity,String loginType,LoginCallBack loginCallBack){
        SDKManager.getInstance().LoginBySocial(activity,loginType,loginCallBack);
    }

    /**
     * 坚果账号登录
     * 同时，隐藏坚果账号注册入口
     * @param activity
     * @param userName
     * @param pwd
     * @param loginCallBack
     */
    public void LoginByNuts(Activity activity,String userName,String pwd,LoginCallBack loginCallBack){
        SDKManager.getInstance().LoginByNuts(activity,userName,pwd,loginCallBack);
    }

    /**
     * 社交平台绑定接口
     * @param activity
     * @param type
     * @param callBack
     */
    public void BindBySocial(Activity activity, String type, SocialBindCallBack callBack){
        BindBySocial(activity,type,false,callBack);
    }

    /**
     * 绑定社交账号
     * @param activity 上下文，当前Activity
     * @param type 社交账号类型，SDKConstant.TYPE_FACEBOOK 或 SDKConstant.TYPE_GOOGLE
     * @param conflict 是否是绑定冲突，如果true，则需要切换社交账号重新绑定，默认false
     * @param callBack 回调接口
     */
    public void BindBySocial(Activity activity, String type, boolean conflict, SocialBindCallBack callBack){
        SDKManager.getInstance().BindBySocial(activity,type,conflict,callBack);
    }

    /**
     * 查询绑定状态
     * @param activity
     * @param bindStatusCallBack
     */
    public void QueryBindStatus(Activity activity, BindStatusCallBack bindStatusCallBack){
        SDKManager.getInstance().QueryBindStatus(activity,bindStatusCallBack);
    }

    /**
     * 切换账号 UI
     *
     * @param activity
     * @param loginCallBack
     */
    public void sdkSwitchAccount(Activity activity,LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkSwitchAccount(activity,loginCallBack);
    }


    //********************************SDK接口******************************************


    /**
     * 无UI
     *
     * @param activity
     * @param userName
     * @param pwd
     * @param resultCallBack
     */
//    public void sdkRegister(final Activity activity, final String userName, final String pwd, final RegisterCallBack resultCallBack) {
//        SDKManager.getInstance().sdkRegister(activity,userName,pwd,resultCallBack);
//    }



    /**
     * 默认登录（自动初始化，初始化成功后默认游客登录）
     * @param activity
     * @param loginCallBack
     */
//    public void sdkDefaultLogin(Activity activity,InitParameter initParameter,LoginCallBack loginCallBack){
//        SDKManager.getInstance().sdkDefaultLogin(activity,initParameter,loginCallBack);
//    }

    /**
     * SDK登陆接口
     *
     * @param activity
     * @param loginCallBack
     *
     */
//    public void sdkLoginNoUI(final Activity activity, final String userName, final String pwd, final LoginCallBack loginCallBack) {
//        SDKManager.getInstance().sdkLoginNoUI(activity,userName,pwd,loginCallBack);
//    }



    /**
     * 切换账号 无UI
     *
     * @param activity
     * @param loginCallBack
     */
//    public void sdkSwitchAccountNoUI(Activity activity,String userName,String pwd,LoginCallBack loginCallBack){
//        SDKManager.getInstance().sdkSwitchAccountNoUI(activity,userName,pwd,loginCallBack);
//    }

    /**
     * 游客登录
     *
     * @param activity
     * @param loginCallBack
     */
//    public void sdkLoginWithVisitor(Activity activity, LoginCallBack loginCallBack){
//        SDKManager.getInstance().sdkLoginWithVisitor(activity,loginCallBack);
//    }

    /**
     * SDK三方账户登录接口
     *
     * @param activity
     * @param loginCallBack
     */
//    public void sdkLoginThirdAccount(Activity activity, String oauthId, String thirdName, String oauthSource, final LoginCallBack loginCallBack) {
//
//        SDKManager.getInstance().sdkLoginThirdAccount(activity, oauthId, thirdName,oauthSource, loginCallBack, new ResultCallBack() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//        });
//    }

    /**
     * 注销账号
     *
     * @param activity
     * @param logOutCallBack
     */
    public void sdkLogout(Activity activity, LogOutCallBack logOutCallBack) {

        SDKManager.getInstance().sdkLogout(activity,logOutCallBack);
    }


    /**
     * SDK支付接口
     *
     * @param activity
     * @param purchaseCallBack
     */
    public void sdkPurchase(final Activity activity, String serverId, final String referenceId, String gameExt, final PurchaseCallBack purchaseCallBack) {
        SDKManager.getInstance().sdkPurchase(activity, serverId, referenceId, gameExt, purchaseCallBack);
    }

    /**
     * SDK订阅商品接口
     *
     * @param activity
     * @param purchaseCallBack
     */
    public void sdkSubscription(final Activity activity, String serverId, final String referenceId,String gameExt, final PurchaseCallBack purchaseCallBack) {

        SDKManager.getInstance().sdkSubscription(activity, serverId, referenceId,gameExt, purchaseCallBack);
    }

    /**
     * 查询内购商品的本地价格(小米支付)
     *
     * @param activity
     * @param skuList
     * @param callback
     */
//    public void sdkQuerySkuLocalPrice(Activity activity, final List<String> skuList, String skuType,final SDKGetMiPaySkuDetailsCallback callback) {
//        SDKManager.getInstance().sdkQuerySkuLocalPrice(activity, skuList, skuType,callback);
//    }

    public void sdkQuerySkuLocalPrice(Activity activity, final List<String> skuList, String skuType,final SDKGetSkuDetailsCallback callback) {
        SDKManager.getInstance().sdkQuerySkuLocalPrice(activity, skuList, skuType,callback);
    }

    /**
     * 创角色追踪
     *
     * @param activity
     * @param serverId
     * @param roleId
     * @param roleName
     */
    public void sdkCreateRoleTracking(Activity activity, String serverId, String roleId, String roleName) {

        SDKManager.getInstance().sdkCreateRoleTracking(activity, serverId, roleId, roleName);
    }

    /**
     * 切换SDK语言
     *
     * @param language
     */
    public void sdkUpdateLanguage(String language) {

        SDKManager.getInstance().sdkUpdateLanguage(language);
    }

    /**
     * 修改密码
     *
     * @param activity
     * @param account
     * @param oldPwd
     * @param newPwd
     * @param callback
     */
//    public void sdkResetPwd(Activity activity, String account, String oldPwd, String newPwd, final ResultCallBack callback){
//        SDKManager.getInstance().sdkResetPwd(activity,account,oldPwd,newPwd,callback);
//    }

    /**
     * 第三方账号绑定账号密码
     *
     * @param activity
     * @param oauthid     第三方账号ID
     * @param oauthsource 第三方账号来源
     * @param account     账号
     * @param pwd      密码
     * @param callback
     */
//    public void sdkBindAccount(Activity activity, String oauthid, String oauthsource, String account, String pwd, final ResultCallBack callback) {
//        SDKManager.getInstance().sdkBindAccount(activity,oauthid,oauthsource,account,pwd,callback);
//    }

    /**
     * 游客绑定FB等第三方账户
     *
     * @param activity
     * @param callback
     */
    public void sdkGuestBindThird(Activity activity, final SocialBindCallBack callback) {
        SDKManager.getInstance().sdkGuestBindFB(activity,callback);
    }

    /**
     * 游客绑定FB等第三方账户
     *
     * @param activity
     * @param type 三方账户类型：google或facebook
     * @param callback
     */
    public void sdkGuestBindThird(Activity activity, String type,final SocialBindCallBack callback) {
        if (type == null) return;
        if (type.equalsIgnoreCase(SDKConstant.TYPE_GOOGLE)){
            SDKManager.getInstance().sdkGuestBindGoogle(activity,callback);
        }else if (type.equalsIgnoreCase(SDKConstant.TYPE_FACEBOOK)){
            SDKManager.getInstance().sdkGuestBindFB(activity,callback);
        }
    }

    /**
     * 生命周期方法
     * 在activity的onRestart()方法中调用
     */
    public void sdkOnRestart(Activity activity) {
        SDKManager.getInstance().sdkOnRestart(activity);
    }

    /**
     * 在activity的onDestroy()方法中调用
     */
    public void sdkOnDestroy(Activity activity) {
        SDKManager.getInstance().sdkOnDestroy();
    }

//    public void sdkGetFbUserInfo(Activity activity, ResultCallBack resultCallBack) {
//        if (activity == null) return;
//        SDKManager.getInstance().sdkGetFbUserInfo(activity,resultCallBack);
//    }

    public void facebookGameLogin(FbLoginListener fbLoginListener) {
        SDKManager.getInstance().facebookGameLogin(fbLoginListener);
    }

    public void facebookFriendFinder() {
        SDKManager.getInstance().facebookFriendFinder();
    }

    public void facebookShareLink(Activity activity, String url, ShareResultCallBack callBack) {
        SDKManager.getInstance().facebookShareLink(activity,url,callBack);
    }

    public void systemSharePhoto(Activity activity, Uri uri){
        SDKManager.getInstance().systemSharePhoto(activity,uri);
    }

    public void systemSharePhoto(Activity activity, String path){
        SDKManager.getInstance().systemSharePhoto(activity,path);
    }

//    public void facebookAppRequest(Activity activity,String message,ResultCallBack resultCallBack){
//        SDKManager.getInstance().facebookAppRequest(activity,message,resultCallBack);
//    }

    /**
     * 打开用户中心界面
     *
     * @param activity
     */
    public void openUserCenter(Activity activity) {
        SDKManager.getInstance().openUserCenter(activity);
    }

    /**
     * 直接进入AiHelp客服聊天界面
     *
     * @param playerName 玩家昵称
     * @param serverId 服务器ID
     * @param userTags 玩家标签
     * @param customData 自定义数据
     * @param showRobot 是否显示机器人按钮 VIP用户传false直接开启人工客服
     */
    public void customerSupport() {
        AIHelpManager.getInstance().show();
    }
    public void customerSupport(String entranceId) {
        AIHelpManager.getInstance().show(entranceId);
    }

    /**
     * FAQ
     * @param userName 玩家昵称
     * @param serverId 服务器ID
     * @param userTags 玩家标签
     * @param customData 自定义数据
     * @param showRobot 是否显示机器人按钮 VIP用户传false直接开启人工客服
     */
//    public void showFAQs(String userName, String serverId, String userTags, JSONObject customData, boolean showRobot){
//        SDKManager.getInstance().showFAQs(userName,serverId,userTags,customData,showRobot);
//    }

    //检查是否绑定FB
    public void isBindFacebook(Activity activity, BindFBCallback callback){
        SDKManager.getInstance().isBindFacebook(activity,callback);
    }

    //检查账号是否绑定Google
    public void isBindGoogle(Activity activity, BindGoogleCallback callback){
        SDKManager.getInstance().isBindGoogle(activity,callback);
    }

    /**
     * 绑定邮箱：目的是找回密码
     * 游客绑定邮箱需要先绑定账号，
     * 账号用户可以绑定邮箱
     * @param activity
     * @param callback
     */
    public void bindEmail(Activity activity, BindResultCallBack callback){
        SDKManager.getInstance().bindEmail(activity,callback);
    }


//    public void fireBaseTrackingLevelUp(Activity activity, String character, long level) {
//        SDKManager.getInstance().fireBaseTrackingLevelUp(activity,character,level);
//    }
//
//    public void fireBaseTrackingTutorialBegin(Activity activity){
//        SDKManager.getInstance().fireBaseTrackingTutorialBegin(activity);
//    }
//
//    public void fireBaseTrackingTutorialComplete(Activity activity){
//        SDKManager.getInstance().fireBaseTrackingTutorialComplete(activity);
//    }

    /**
     * 展示用户协议界面
     * @param activity
     */
    public void showUserAgreement(Activity activity, AgreementCallBack callBack){
        SDKManager.getInstance().showUserAgreement(activity,callBack);
    }

    /**
     * 用户安装来源归因
     * @param activity
     * @param installCallBack
     */
    public void installReferrer(Activity activity, InstallCallBack installCallBack) {
        SDKManager.getInstance().installReferrer(activity,installCallBack);
    }

    /**
     * 谷歌商店评分
     */
    public void googlePlayEvaluate(Activity activity,ResultCallBack callBack) {
        if (activity == null || callBack == null) {
            System.out.println("Parameter cannot be empty.");
            return;
        }
        GoogleAPI.evaluateInApp(activity,callBack);
    }

    //获取未读消息
//    public  void fetchUnreadMessage(OnMessageCountArrivedCallback callback){
//        if (callback == null) return;
//        AIHelpManager.fetchUnreadMessage(callback);
//    }

    /**
     * Adjust自定义追踪事件
     * @param eventID
     */
    public void adjustCustomEvent(String eventID){
        if (StringUtils.isNotBlank(eventID)){
            AdjustTraceManager.getInstance().adjustCustomEvent(eventID);
        }
    }

    /**
     *  获取Firebase设备Token信息
     */
//    public void firebaseGetToken(OnCompleteListener<String> completeListener) {
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(completeListener);
//    }
}
