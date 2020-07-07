package com.nutsplay.nopagesdk.kernel;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.RegisterCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.manager.LoginManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by frank-ma on 2019-09-19 10:05
 * Email: frankma9103@gmail.com
 * Desc:
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


    //********************************SDK接口******************************************

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
     * 无UI
     *
     * @param activity
     * @param userName
     * @param pwd
     * @param resultCallBack
     */
    public void sdkRegister(final Activity activity, final String userName, final String pwd, final RegisterCallBack resultCallBack) {

        SDKManager.getInstance().sdkRegister(activity,userName,pwd,resultCallBack);
    }

    /**
     * 登录 UI
     * @param activity
     * @param loginCallBack
     */
    public void sdkLogin(Activity activity,LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkLogin(activity,loginCallBack);
    }

    /**
     * 默认登录（自动初始化，初始化成功后默认游客登录）
     * @param activity
     * @param loginCallBack
     */
    public void sdkDefaultLogin(Activity activity,InitParameter initParameter,LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkDefaultLogin(activity,initParameter,loginCallBack);
    }

    /**
     * SDK登陆接口
     *
     * @param activity
     * @param loginCallBack
     */
    public void sdkLoginNoUI(final Activity activity, final String userName, final String pwd, final LoginCallBack loginCallBack) {

        SDKManager.getInstance().sdkLoginNoUI(activity,userName,pwd,loginCallBack);
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

    /**
     * 切换账号 无UI
     *
     * @param activity
     * @param loginCallBack
     */
    public void sdkSwitchAccountNoUI(Activity activity,String userName,String pwd,LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkSwitchAccountNoUI(activity,userName,pwd,loginCallBack);
    }

    /**
     * 游客登录
     *
     * @param activity
     * @param loginCallBack
     */
    public void sdkLoginWithVisitor(Activity activity, LoginCallBack loginCallBack){
        SDKManager.getInstance().sdkLoginWithVisitor(activity,loginCallBack);
    }

    /**
     * SDK三方账户登录接口
     *
     * @param activity
     * @param loginCallBack
     */
    public void sdkLoginThirdAccount(Activity activity, String oauthId, String oauthSource, final LoginCallBack loginCallBack) {

        SDKManager.getInstance().sdkLoginThirdAccount(activity,oauthId,oauthSource,loginCallBack);
    }

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
     * SDK下单接口
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
     * 查询内购商品的本地价格
     *
     * @param activity
     * @param skuList
     * @param callback
     */
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
    public void sdkResetPwd(Activity activity, String account, String oldPwd, String newPwd, final ResultCallBack callback){
        SDKManager.getInstance().sdkResetPwd(activity,account,oldPwd,newPwd,callback);
    }

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
    public void sdkBindAccount(Activity activity, String oauthid, String oauthsource, String account, String pwd, final ResultCallBack callback) {

        SDKManager.getInstance().sdkBindAccount(activity,oauthid,oauthsource,account,pwd,callback);

    }

    /**
     * 游客绑定FB等第三方账户
     *
     * @param activity
     * @param callback
     */
    public void sdkGuestBindThird(Activity activity, final ResultCallBack callback) {

        SDKManager.getInstance().sdkGuestBindFB(activity,callback);
    }

    /**
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

    public void sdkGetFbUserInfo(Activity activity, ResultCallBack resultCallBack) {

        if (activity == null)return;
        SDKManager.getInstance().sdkGetFbUserInfo(activity,resultCallBack);
    }

    public void facebookGameLogin(LoginManager.FbLoginListener fbLoginListener) {
        SDKManager.getInstance().facebookGameLogin(fbLoginListener);
    }

    public void facebookFriendFinder() {
        SDKManager.getInstance().facebookFriendFinder();
    }

    public void facebookSharing() {
        SDKManager.getInstance().facebookSharing();
    }

    /**
     * 打开用户中心界面
     *
     * @param activity
     */
    public void openUserCenter(Activity activity) {
        SDKManager.getInstance().openUserCenter(activity);
    }

    public void customerSupport(String playerName,String serverId, @Nullable HashMap<String ,Object> customData) {
        SDKManager.getInstance().customerSupport(playerName,serverId,customData);
    }

    public void showFAQs(String userName, String serverId, HashMap<String,Object> customData){
        SDKManager.getInstance().showFAQs(userName,serverId,customData);
    }

    public void fireBaseTrackingLevelUp(Activity activity, String character, long level) {
        SDKManager.getInstance().fireBaseTrackingLevelUp(activity,character,level);
    }

    public void fireBaseTrackingTutorialBegin(Activity activity){
        SDKManager.getInstance().fireBaseTrackingTutorialBegin(activity);
    }

    public void fireBaseTrackingTutorialComplete(Activity activity){
        SDKManager.getInstance().fireBaseTrackingTutorialComplete(activity);
    }

    /**
     * 展示用户协议界面
     * @param activity
     */
    public void showUserAgreement(Activity activity){
        SDKManager.getInstance().showUserAgreement(activity);
    }
}
