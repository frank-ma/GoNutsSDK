package com.nutsplay.nonutssdk;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import com.nutsplay.nopagesdk.callback.ShareResultCallBack;
import com.nutsplay.nopagesdk.facebook.FacebookUser;
import com.nutsplay.nopagesdk.kernel.SDK;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.AdjustTraceManager;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutsplay.nopagesdk.ui.SDKBaseActivity;
import com.nutspower.nutsgamesdk.R;

import net.aihelp.ui.listener.OnMessageCountArrivedCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends SDKBaseActivity {

    private String clientId = "5f84109764b5b50775d4abf2";
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
//    private String buglyId = "1ee9849782";
    private String buglyId = "36386748bb";
//    String referenceId = "com.nutspower.nutsgamesdk.sub1";
    String referenceId = "com.nutspower.nutsgamesdk.sub2";

    private TextView logTv,webTv,login;
    private Button initB,defaultLogin;

    //poly
//    private String AIHelpAppID = "NutsPowerOnlineEntertainmentLimited_platform_18d51c55-b1e5-43f4-bcbe-daad1b7381a8";
//    private String AIHelpAppKey = "NUTSPOWERONLINEENTERTAINMENTLIMITED_app_b372655fc824460d8add46957ae8739c";
//    private String AIHelpDomain = "NutsPowerOnlineEntertainmentLimited@aihelp.net";

    //Dragon Home
    private String AIHelpAppID = "NutsPowerOnlineEntertainmentLimited_platform_a84456e0-2d9b-4c65-8e83-0f49630aa2d2";
    private String AIHelpAppKey = "NUTSPOWERONLINEENTERTAINMENTLIMITED_app_a070e2a9a3bf4259bcb19301bdc33a4e";
    private String AIHelpDomain = "nutspoweronlineentertainmentlimited.aihelp.net";

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


//        initB.callOnClick();


        //startActivity(new Intent(this,AdsActivity.class));
    }



    /**
     * ****************************************接口方法*********************************************
     */
    public void initSDK(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("zh_CN");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        initParameter.setShowUserAgreement(true);
        initParameter.setUIVersion(0);//默认是通用UI版本     0:通用UI（Poly那套UI）    1：侵权游戏UI
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpDomain(AIHelpDomain);

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
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("初始化失败：" + code + "------"+errorMsg);
            }
        });

    }

    public void goToNoUIActivity(View view) {

        Intent intent = new Intent(this, NoUIActivity.class);
        startActivity(intent);
    }

    public void initAihelp(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setLanguage("zh_hk");
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpDomain(AIHelpDomain);
        SDKManager.getInstance().initAiHelp(this, initParameter, new ResultCallBack() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
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
            public void onFailure(int code,String msg) {
                showLog("默认登录失败：" + msg);

            }

            @Override
            public void onCancel() {
                showLog("默认登录取消");
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
                if (SDKConstant.TYPE_FACEBOOK.equals(user.getSdkmemberType())){
                    String fbName = user.getFacebookName();
                    String fbPortrait=user.getFacebookPortrait();
                    String fbEmail=user.getFacebookEmail();
                    String fbID=user.getFacebookId();
                }
                showLog("登录成功：" + user.toString());
//                showLog("UserName：" + user.getUserName());
                //判断用户的登录类型
                if (SDKConstant.TYPE_GUEST.equals(user.getSdkmemberType())){
                    //游客
                } else if (SDKConstant.TYPE_ACCOUNT.equals(user.getSdkmemberType())){
                    //账号登录
                } else if (SDKConstant.TYPE_FACEBOOK.equals(user.getSdkmemberType())){
                    //fb登录
                } else if (SDKConstant.TYPE_GOOGLE.equals(user.getSdkmemberType())){
                    //Google登录
                }


            }

            @Override
            public void onCancel() {
                showLog("登录取消");
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("登录失败：" + code+"       "+errorMsg);
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
            public void onCancel() {
                showLog("切换账号取消");
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("切换账号失败：" +code+"              "+ errorMsg);
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
            public void onFailure(int code,String msg) {
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
//        String referenceId = "com.nutspower.nutsgamesdk.test2";
//        String referenceId = "nuts_product_1";
        String referenceId = "com.nutspower.golfduel.cumulative099";
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
            public void onFailure(int code, String msg) {
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
            public void onFailure(int code,String msg) {
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
//        skuList.add("com.nutspower.nutsgamesdk.test1");
//        skuList.add("com.nutspower.dragon.paidland3");
//        skuList.add("com.nutspower.dragon.safediamonds9999");
//        skuList.add("com.nutspower.golfduel.month499");
//        skuList.add("com.nutspower.golfduel.diamond099");
//        skuList.add("com.nutspower.golfduel.tour4999");
//        skuList.add("com.nutspower.golfduel.tour9999");

        //wjgame01
        skuList.add("com.nutspower.wjgame01.hugepack4999");
        skuList.add("com.nutspower.wjgame01.megapack9999");
        skuList.add("com.nutspower.wjgame01.limitedpackage199");
        skuList.add("com.nutspower.wjgame01.hugepack4999");
        skuList.add("com.nutspower.wjgame01.largepack1999");
        skuList.add("com.nutspower.wjgame01.mediumpack999");
        //atw
//        skuList.add("com.nuts.atw.android.googleplay.1");
//        skuList.add("com.nuts.atw.android.googleplay.2");
//        skuList.add("com.nuts.atw.android.googleplay.3");
//        skuList.add("com.nuts.atw.android.googleplay.4");
//        skuList.add("com.nuts.atw.android.googleplay.5");
//        skuList.add("com.nuts.atw.android.googleplay.6");

        //poly
//        skuList.add("com.nuts.pa.android.googleplay.19");



        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList, SDKConstant.INAPP,new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<SkuDetails> skuDetails) {
                showLog("查询本地价格成功：" + skuDetails.size()+"条");
                if (skuDetails.size() == 0) return;
                for (SkuDetails sku : skuDetails) {
                    String skuId = sku.getSku();
                    String localPrice = sku.getPrice();
                    showLog(skuId + "    " + localPrice);
                }
            }

            @Override
            public void onFailure(int code,String msg) {
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
            public void onFailure(int code,String msg) {
                showLog("查询订阅本地价格失败：" + msg);
            }
        });
    }

    /**
     * zh_CN, 中文
     * zh_HK, 粤语中文
     * zh_TW, 繁体中文
     * en, 英文
     * th, 泰语
     * vi, 越语
     * ar，阿拉伯语
     * kr，韩语  ko
     * fr，法语
     * pt，葡萄牙语
     * de，德
     * sp，西班牙 es
     * it，意大利语
     * ja，日语
     * id，印度尼西亚语
     * ru:俄语
     *
     *
     * 荷兰af
     * 孟加拉bn
     *
     * @param view
     */

    public void en(View view) {
        SDK.getInstance().sdkUpdateLanguage("en");
    }
    public void th(View view){
        SDK.getInstance().sdkUpdateLanguage("th");
    }
    public void de(View view){
        SDK.getInstance().sdkUpdateLanguage("de");
    }
    public void ko(View view){
        SDK.getInstance().sdkUpdateLanguage("ko");
    }
    public void ru(View view){
        SDK.getInstance().sdkUpdateLanguage("ru");
    }
    public void it(View view){
        SDK.getInstance().sdkUpdateLanguage("it");
    }
    public void jp(View view){
        SDK.getInstance().sdkUpdateLanguage("ja");
    }
    public void zh_CN(View view){
        SDK.getInstance().sdkUpdateLanguage("zh_CN");
    }
    public void zh_TW(View view){
        SDK.getInstance().sdkUpdateLanguage("zh_HK");
    }
    public void es(View view){
        SDK.getInstance().sdkUpdateLanguage("es");
    }
    public void pt(View view){
        SDK.getInstance().sdkUpdateLanguage("pt");
    }
    public void ar(View view){
        SDK.getInstance().sdkUpdateLanguage("ar");
    }
    public void fr(View view){
        SDK.getInstance().sdkUpdateLanguage("fr");
    }
    public void vi(View view){
        SDK.getInstance().sdkUpdateLanguage("vi");
    }
    public void idn(View view){
        SDK.getInstance().sdkUpdateLanguage("idn");
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

            @Override
            public void onCancel() {

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
     * 坚果账号绑定邮箱
     *
     * @param view
     */
    public void userCenter(View view){
        SDK.getInstance().openUserCenter(this);
    }

    /**
     * 在线客服系统
     * Key-Value可以自己根据需要自定义，会显示在客服后台中
     * @param view
     */
    public void customerService(View view) {

        //打开AIHelp客服聊天界面
        HashMap<String,Object> customData = new HashMap<>();
        customData.put("playerID","10001");
        customData.put("level","2");
        customData.put("coins","999");
        customData.put("diamond","100");
//        customData.put("private_welcome_str","What can I do?");//key是固定的，value可以自定义人工客服的欢迎语


        HashMap<String,Object> map = new HashMap();
        ArrayList<String> tags = new ArrayList();
        // the tag names are variables
        tags.add("ticket111100000000");
        // "elva-tags" 是key值 不可以变
        map.put("elva-tags",tags);
        // "elva-custom-metadata" 是key值 不可以变
        customData.put("elva-custom-metadata",map);

        InitParameter initParameter = new InitParameter();
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpDomain(AIHelpDomain);
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setLanguage("en");
        SDK.getInstance().customerSupport( this,initParameter,"Jack","recharge,vip3,paid3","0", customData);
    }

    /**
     * 常见问题
     * Key-Value可以自己根据需要自定义，会显示在客服后台中
     * @param view
     */
    public void FAQ(View view){

        String userTagKey = "elva-tags";
        String sdkConfigKey = "elva-custom-metadata";
        HashMap<String,Object> sdkParamConfig = new HashMap<>();
        HashMap<String,Object> sdkUserConfig = new HashMap<>();

        ArrayList<String> userTagList = new ArrayList<>();
        userTagList.add("HWSJ11");
        userTagList.add("account");
        sdkUserConfig.put(userTagKey,userTagList);
        sdkUserConfig.put("userLevel","100");
        sdkUserConfig.put("UID","4179");
        sdkUserConfig.put("userName","1008090");
        sdkParamConfig.put(sdkConfigKey,sdkUserConfig);

        InitParameter initParameter = new InitParameter();
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpDomain(AIHelpDomain);
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setLanguage("en");
        SDK.getInstance().showFAQs(this,initParameter,"Liuxiaobei1","recharge,vip3,paid3","10",sdkParamConfig);
    }

    /**
     * 获取aihelp未读消息
     * 需在初始化成功之后调用
     * @param view
     */
    public void fetchUnread(View view){
        SDK.getInstance().fetchUnreadMessages(new OnMessageCountArrivedCallback() {
            @Override
            public void onMessageCountArrived(int msgCount) {
                Log.e("AiHelp", "客服未读消息数量：" + msgCount);
            }
        });
    }


    /**
     * 打开用户协议页面，FB政策要求登录界面要留一个常驻按钮，展示用户协议
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
     * https://developers.google.com/analytics/devguides/collection/android/v4/campaigns?hl=zh-cn
     * utm_source	广告系列来源，用于确定具体的搜索引擎、简报或其他来源	utm_source=google
     * utm_medium	广告系列媒介，用于确定电子邮件或采用每次点击费用 (CPC) 的广告等媒介。	utm_medium=cpc
     * utm_term	广告系列字词，用于付费搜索，为广告提供关键字	utm_term=running+shoes
     * utm_content	广告系列内容，用于 A/B 测试和内容定位广告，以区分指向相同网址的不同广告或链接	utm_content=logolink
     * utm_content=textlink
     * utm_campaign	广告系列名称，用于关键字分析，以标识具体的产品推广活动或战略广告系列	utm_campaign=spring_sale
     * gclid	Google Ads 自动标记参数，用于衡量广告。此值会动态生成，请勿修改。
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
                showLog("安装归因："+msg);
                if (msg.contains("fb")||msg.contains("facebook")){
                    showLog("用户是Facebook广告引导来的流量");
                }
            }
        });

    }

    /**
     * 分享链接
     *
     * @param view
     */
    public void shareLink(View view){
//        String link = "https://play.google.com/store/apps/details?id=com.nutspower.mergegame";
//        String link = "https://play.google.com/store/apps/details?id=com.dyhd.slg01.pgangtai";
        String link = "https://play.google.com/store/apps/details?id=org.nanobit.hollywood";
        SDK.getInstance().facebookShareLink(this, link, new ShareResultCallBack() {
            @Override
            public void onSuccess() {
                showLog("分享成功");
            }

            @Override
            public void onCancel() {
                showLog("分享取消");
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("分享失败："+msg);
            }
        });
    }

    /**
     * FB邀请好友列表，可以传邀请信息，通知对应的好友
     * @param view
     */
    public void appInvite(View view){

        SDK.getInstance().facebookAppRequest(this,"My Invite Code:2131234,Come Play with me!",new ResultCallBack(){

            @Override
            public void onFailure(String msg) {
                showLog("邀请失败："+msg);
            }

            @Override
            public void onSuccess() {
                showLog("邀请好友成功");
            }
        });
    }

    /**
     * 系统原生的分享图片功能，系统分享没有回调
     *
     * @param view
     */
    public void systemShare(View view){
        //方法一：文件路径
//        String filePath = "/storage/emulated/0/DCIM/Camera/IMG_20191125_210352.jpg";
//        String filePath = "/storage/emulated/0/DCIM/Camera/IMG_20201209_221248.jpg";
//        SDK.getInstance().systemSharePhoto(this,filePath);

        //方法二：选择相册图片，传Uri
        selectPhoto();
    }

    public void openApp(View view){
//        PackageManager packageManager = this.getPackageManager();
//        Intent intent= packageManager.getLaunchIntentForPackage("com.nutspower.mergegame");
//        startActivity(intent);

        try {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.nutspower.mergegame", "com.idgame.nutlibrary.SDKUtils");
            intent.setComponent(comp);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * *************************其他方法****************************
     */


    public static final int IMAGE_REQUEST_CODE = 0x102;
    private void selectPhoto() {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && data != null){
            Uri uri = data.getData();
            if (uri == null) return;
            //调用方法二
            SDK.getInstance().systemSharePhoto(this,uri);
        }else if (requestCode == SDKConstant.SHARE_PHOTO_REQUEST_CODE && data !=null){
            Uri uri = data.getData();
            if (uri == null) return;
            showLog(uri.getPath());
        }
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

    /**
     * 商店评价:
     * 设备要求：
     * 搭载 Android 5.0（API 级别 21）或更高版本的 Android 设备（手机和平板电脑），且安装了 Google Play 商店。
     * 安装了 Google Play 商店的 Chrome 操作系统设备。
     *
     * 何时请求应用内评价
     * 请遵循以下准则，这些准则有助您确定何时向用户请求应用内评价：
     *
     * 在用户已充分您的体验应用或游戏内容并给出实用反馈后，触发应用内评价流程。
     * 请不要过度提示用户进行评价。此方法有助于最大限度地减少用户不满并限制 API 使用情况（请参阅配额）。
     * 在系统显示评分按钮或卡片之前或之后，您的应用不应询问用户任何问题，包括有关用户意见的问题（例如，“您是否喜欢这款应用？”）或预测性问题（例如，“您会给这款应用打 5 星吗”）。
     *
     * 测试条件：
     * 将您的应用上传到内部测试轨道并将其安装到设备上，该设备必须具有一个有权访问内部测试轨道的用户帐号。使用内部测试轨道时，必须满足以下条件：
     *
     * 用户帐号是内部测试轨道的一部分。
     * 用户帐号是主帐号，且已在 Play 商店中选定。
     * 用户帐号已从 Play 商店下载应用（该应用已在用户的 Google Play 内容库中列出）。
     * 用户帐号目前未对该应用进行评价。
     * 在设备上的帐号至少从内部测试轨道下载该应用一次并且该帐号列于测试人员列表中之后，您可以将该应用的新版本部署到设备本地（例如，使用 Android Studio 进行部署）。
     * @param view
     */
    public void evaluate(View view) {
        SDK.getInstance().googlePlayEvaluate(this,new ResultCallBack(){

            @Override
            public void onFailure(String msg) {
                showLog("评价失败："+msg);
            }

            @Override
            public void onSuccess() {
                showLog("评价成功");
            }
        });


    }

    /**
     * Adjust自定义追踪事件
     * 参数为：定义好的事件id
     */
    public void adjustEvent(){
        AdjustTraceManager.getInstance().adjustCustomEvent("eventID");
    }


}
