package com.nutsplay.nonutssdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.SkuDetails;
import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.InstallCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.facebook.FacebookUser;
import com.nutsplay.nopagesdk.kernel.SDK;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutspower.nutsgamesdk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity {

//    private String clientId = "5dcbeab164b5b50deb76be93";
    private String clientId = "5ef872ec64b5b50defb667dc";
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
//    private String buglyId = "1ee9849782";
    private String buglyId = "36386748bb";
//    String referenceId = "com.nutspower.nutsgamesdk.sub1";
    String referenceId = "com.nutspower.nutsgamesdk.sub2";

    private TextView logTv,webTv,login;
    private Button initB,defaultLogin;

    //poly
    private String AIHelpAppID = "NutsPowerOnlineEntertainmentLimited_platform_18d51c55-b1e5-43f4-bcbe-daad1b7381a8";
    private String AIHelpAppKey = "NUTSPOWERONLINEENTERTAINMENTLIMITED_app_b372655fc824460d8add46957ae8739c";
    private String AIHelpDomain = "NutsPowerOnlineEntertainmentLimited@aihelp.net";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logTv = findViewById(R.id.log);
        initB = findViewById(R.id.init);
        defaultLogin = findViewById(R.id.default_login);
        webTv = findViewById(R.id.webUrl);
        login = findViewById(R.id.login);
        //通过html的形式实现超链接
//        String csdnLink1 = "<a href=\"https://fb.gg/me/friendfinder/295570801431576\">好友列表</a>";
//        webTv.setText(Html.fromHtml(csdnLink1));


        defaultLogin.callOnClick();


    }

    public void initSDK(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("en");
        initParameter.setDebug(false);
        initParameter.setHasUI(true);
        initParameter.setShowUserAgreement(true);
        initParameter.setUIVersion(1);//默认是通用UI版本     0:通用UI（Poly那套UI）    1：侵权游戏UI
//        initParameter.setAihelpAppID(AIHelpAppID);
//        initParameter.setAihelpAppkey(AIHelpAppKey);
//        initParameter.setAihelpDomain(AIHelpDomain);

        SDK.getInstance().initSDK(this, initParameter, new InitCallBack() {
            @Override
            public void onSuccess(@Nullable User user) {
                //user为上次登录的用户，可能为空，所以客户端要做判断,客户端拿到这个信息之后，可以显示在登录界面左上角，告诉用户自动登录的是哪个账号，玩家就可以决定要不要切换账号
//                右上角要放一个切换账号的按钮
                if (user != null){
                    showLog(user.toString());
                    showLog("当前自动登录的用户类型是："+user.getSdkmemberType()+"-"+user.getUserId());
                }else{
                    showLog("当前没有自动登录的用户");
                }
                showLog("初始化成功");
                login.callOnClick();
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("初始化失败：" + errorMsg);
            }
        });

        login();

    }

    public void goToNoUIActivity(View view) {

        Intent intent = new Intent(this, NoUIActivity.class);
        startActivity(intent);
    }

    /**
     * 默认登录：自动执行初始化和游客登录
     *
     * @param view
     */
    public void defaultLogin(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("zh_hk");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        initParameter.setUIVersion(0);
        initParameter.setShowUserAgreement(true);
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpDomain(AIHelpDomain);

        SDK.getInstance().sdkDefaultLogin(this,initParameter,new LoginCallBack(){

            @Override
            public void onSuccess(User user) {
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                showLog("默认登录成功："+user.toString());
            }

            @Override
            public void onFailure(String msg) {
                showLog("默认登录失败：" + msg);

            }

        });
    }

    public void loginUI(View view) {

        login();

    }

    private void login() {
        SDK.getInstance().sdkLogin(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                //如果用户是facebook登录的话，获取fb信息
                if (user.getSdkmemberType().equals(SDKConstant.TYPE_FACEBOOK)){
                    String fbName = user.getFacebookName();
                    String fbPortrait=user.getFacebookPortrait();
                    String fbEmail=user.getFacebookEmail();
                    String fbID=user.getFacebookId();
                }
                showLog("登录成功：" + user.toString());
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("登录失败：" + errorMsg);
            }
        });
    }

    public void switchAccount(View view) {

        SDK.getInstance().sdkSwitchAccount(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                showLog("切换账号成功：" + user.toString());
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("切换账号失败：" + errorMsg);
            }
        });

    }

    public void logout(View view) {

        SDK.getInstance().sdkLogout(this, new LogOutCallBack() {
            @Override
            public void onSuccess() {
                showLog("注销成功");
            }

            @Override
            public void onFailure(String msg) {
                showLog("注销失败：" + msg);
            }
        });

    }

    /**
     * 受管理的商品
     *
     * @param view
     */

    public void purchase(View view) {
        String referenceId = "com.nutspower.nutsgamesdk.test1";
        SDK.getInstance().sdkPurchase(this, "0", referenceId, "", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
                if (payResult == null) return;
                showLog("支付成功" + payResult.toString());
            }

            @Override
            public void onCancel() {
                showLog("支付取消");
            }

            @Override
            public void onFailure(String msg) {
                showLog("支付失败：" + msg);
            }
        });

    }


    /**
     * 发起订阅购买
     * @param view
     */
    public void subscription(View view) {

        SDK.getInstance().sdkSubscription(this, "0", referenceId,"testSub", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
                if (payResult == null) return;
                showLog("订阅成功" + payResult.toString());
            }

            @Override
            public void onCancel() {
                showLog("订阅取消");
            }

            @Override
            public void onFailure(String msg) {
                showLog("订阅失败：" + msg);
            }
        });

    }

    /**
     * 创建角色追踪
     * @param view
     */
    public void createRoleTracking(View view) {
        SDK.getInstance().sdkCreateRoleTracking(this, "0", "001", "xiaohao");
    }

    /**
     * 查询消耗型商品的本地货币价格
     *
     * @param view
     */
    public void localPrice(View view) {

        List<String> skuList = new ArrayList<>();
        skuList.add("com.nutspower.nutsgamesdk.test1");

        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList, SDKConstant.INAPP,new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<SkuDetails> skuDetails) {
                showLog("查询本地价格成功：" + skuDetails.size());
                if (skuDetails.size() == 0) return;
                for (SkuDetails sku : skuDetails) {
                    String skuId = sku.getSku();
                    String localPrice = sku.getPrice();
                    showLog(skuId + "    " + localPrice);
                }
            }

            @Override
            public void onFailure(String msg) {
                showLog("查询本地价格失败：" + msg);
            }
        });
    }

    /**
     * 查询订阅商品的本地货币价格
     * 跟前面消耗型商品只是参数不同
     *
     * @param view
     */
    public void subsLocalPrice(View view) {

        List<String> skuList = new ArrayList<>();
        skuList.add("com.nutspower.nutsgamesdk.sub1");

        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList, SDKConstant.SUBS,new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<SkuDetails> skuDetails) {
                showLog("查询订阅本地价格成功：" + skuDetails.size());
                if (skuDetails.size() == 0) return;
                for (SkuDetails sku : skuDetails) {
                    String skuId = sku.getSku();
                    String localPrice = sku.getPrice();
                    showLog(skuId + "    " + localPrice);
                }
            }

            @Override
            public void onFailure(String msg) {
                showLog("查询订阅本地价格失败：" + msg);
            }
        });
    }

    /**
     * zh_CN, 中文
     * zh_HK, 中文
     * en, 英文
     * th, 泰语
     * vi, 越语
     * ar，阿拉伯语
     * kr，韩语
     * fr，法语
     * pt，葡萄牙语
     * de，德
     * sp，西班牙
     * it，意大利语
     * ja，日语
     * id，印度尼西亚语
     * ru:俄语
     *
     * @param view
     */

    public void updateLanguagezhCN(View view) {
        SDK.getInstance().sdkUpdateLanguage("zh_CN");
    }

    public void updateLanguageEN(View view){
        SDK.getInstance().sdkUpdateLanguage("en");
    }

    public void saveShot(View view) {
        //截图保存
        SDKManager.getInstance().saveShot(this);
    }

    /**
     * 游客绑定FB账号
     *
     * @param view
     */
    public void guestBindFB(View view) {
        SDK.getInstance().sdkGuestBindThird(this, new ResultCallBack() {

            @Override
            public void onSuccess() {
                showLog("绑定FB成功");

            }

            @Override
            public void onFailure(String msg) {
                showLog("绑定FB失败：" + msg);
            }

        });
    }


    /**
     * 获取用户FB信息
     *
     * @param view
     */
    public void getFbUserInfo(View view){
        SDK.getInstance().sdkGetFbUserInfo(this, new ResultCallBack(){

            @Override
            public void onFailure(String msg) {
                showLog("获取用户信息失败：" + msg);
            }

            @Override
            public void onSuccess() {

            }
        });
    }

    /**
     * FB游戏登录
     * @param view
     */
    public void fbGameLogin(View view){
        SDK.getInstance().facebookGameLogin(new LoginManager.FbLoginListener() {
            @Override
            public void onSuccess(FacebookUser user) {
                showLog("fb游戏登录成功：fbid-" + user.getId());
            }

            @Override
            public void onFailure(String msg) {

                showLog("fb游戏登录失败："+msg);
            }
        });
    }

    /**
     * FB好友查找
     * @param view
     */
    public void fbFriendFinder(View view){
        SDK.getInstance().facebookFriendFinder();
        String url = "https://fb.gg/me/friendfinder/295570801431576";
        WebView web = new WebView(this);
        web.loadUrl(url);
    }

    /**
     * FB游戏分享
     * @param view
     */
    public void Fbsharing(View view){
        SDK.getInstance().facebookSharing();
    }

    /**
     * 坚果账号绑定邮箱
     *
     * @param view
     */
    public void userCenter(View view){
        SDK.getInstance().openUserCenter(this);
    }

    /**
     * 在线客服系统
     *
     * @param view
     */
    public void customerService(View view) {

        //打开AIHelp客服聊天界面
        HashMap<String,Object> customData = new HashMap<>();
        customData.put("playerID","10001");
        customData.put("level","2");
        customData.put("coins","999");
        customData.put("diamond","100");
        SDK.getInstance().customerSupport( "Jack","0", customData);
    }

    /**
     * 常见问题
     *
     * @param view
     */
    public void FAQ(View view){

        HashMap<String,Object> customData = new HashMap<>();
        customData.put("playerID","100011");
        customData.put("level","12");
        customData.put("coins","1999");
        customData.put("diamond","0");
        SDK.getInstance().showFAQs("Liuxiaobei","0",customData);
    }

    /**
     * 打开用户协议页面，登录界面可以留一个常驻按钮，展示用户协议
     *
     * @param view
     */
    public void showUserAgreement(View view){
        SDK.getInstance().showUserAgreement(this);
    }

    /**
     * Firebase功能测试
     *
     * @param view
     */
    public void FirebaseFunction(View view){
        SDK.getInstance().fireBaseTrackingLevelUp(this,"beat_boss",10);
        SDK.getInstance().fireBaseTrackingTutorialBegin(this);
        SDK.getInstance().fireBaseTrackingTutorialComplete(this);
    }

    /**
     * 安装来源归因
     *
     * @param view
     */
    public void installReferrer(View view) {
        SDK.getInstance().installReferrer(this,new InstallCallBack(){
            @Override
            public void onFailure(String msg) {
                showLog("获取用户归因失败："+msg);
            }

            @Override
            public void onSuccess(String msg) {
                if (msg == null) return;
                if (msg.contains("fb")||msg.contains("facebook")){
                    showLog("用户是Facebook广告引导来的流量");
                }
            }
        });

    }

    /**
     * *************************生命周期方法****************************
     */

    /**
     * 生命周期方法
     *
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        //游戏退到后台，再回到前台时，检查是否有未完成的订单
        SDK.getInstance().sdkOnRestart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG","onDestroy");
        SDK.getInstance().sdkOnDestroy(this);
    }

    private void showLog(final String msg) {
        Log.d("LOG",msg);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logTv.append("\n");
                logTv.append(msg);
            }
        });
    }


}
