package com.nutsplay.nonutssdk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.billingclient.api.ProductDetails;
import com.nuts.test.R;
import com.nutsplay.nopagesdk.api.FbLoginListener;
import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.callback.AgreementCallBack;
import com.nutsplay.nopagesdk.callback.BindFBCallback;
import com.nutsplay.nopagesdk.callback.BindGoogleCallback;
import com.nutsplay.nopagesdk.callback.BindResultCallBack;
import com.nutsplay.nopagesdk.callback.BindStatusCallBack;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.InstallCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.callback.SocialBindCallBack;
import com.nutsplay.nopagesdk.facebook.FacebookUser;
import com.nutsplay.nopagesdk.kernel.SDK;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.manager.HelpShiftManager;
import com.nutsplay.nopagesdk.ui.SDKBaseActivity;
import com.nutsplay.nopagesdk.utils.DeviceUtils;
import com.nutsplay.nopagesdk.utils.FileUtils;
import com.nutspower.commonlibrary.utils.LogUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity2 extends SDKBaseActivity {
    private static final String TAG = "MainActivity2";


//    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
//    private String buglyId = "36386748bb";
//    String referenceId = "com.nutspower.nutsgamesdk.sub2";
    String referenceId = "com.nuts.sm.android.googleplay.1";


//    private String clientId = "67fd2b2395b52694179f8ffb";//FG
    private String clientId = "5dad5c14e73f210d548bf491";//海战（sdk测试）     635f680c95b526b99391e7e7
//    private String clientId = "64e3342d95b526d070bf82c9";//测试应用      635f680c95b526b99391e7e7
//    private String clientId = "64e2e7ae95b526d070bf817f";//wvb
//    private String clientId = "6449d80495b526d070beff5a";//MiPay
//    private String clientId = "64aed91995b526d070bf580c";//viking
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
    private String buglyId = "36386748bb";
//    String referenceId = "gem_0001";

    private TextView logTv,webTv,login;
    private Button initB,defaultLogin;
    private String reyunAppId="c48e4cf4e8f80a2b";

    //poly
//    private String AIHelpAppID = "NutsPowerOnlineEntertainmentLimited_platform_18d51c55-b1e5-43f4-bcbe-daad1b7381a8";
//    private String AIHelpAppKey = "NUTSPOWERONLINEENTERTAINMENTLIMITED_app_b372655fc824460d8add46957ae8739c";
//    private String AIHelpDomain = "NutsPowerOnlineEntertainmentLimited@aihelp.net";

    //Dragon Home
    private String AIHelpAppID = "NutsPowerOnlineEntertainmentLimited_platform_a84456e0-2d9b-4c65-8e83-0f49630aa2d2";
    private String AIHelpAppKey = "NUTSPOWERONLINEENTERTAINMENTLIMITED_app_a070e2a9a3bf4259bcb19301bdc33a4e";
    private String AIHelpDomain = "nutspoweronlineentertainmentlimited.aihelp.net";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logTv = findViewById(R.id.log);
        initB = findViewById(R.id.init);
//        defaultLogin = findViewById(R.id.default_login);
//        webTv = findViewById(R.id.webUrl);
        login = findViewById(R.id.login);
        //通过html的形式实现超链接
//        String csdnLink1 = "<a href=\"https://fb.gg/me/friendfinder/295570801431576\">好友列表</a>";
//        webTv.setText(Html.fromHtml(csdnLink1));


        initB.callOnClick();


        //startActivity(new Intent(this,AdsActivity.class));

    }



    /**
     * ****************************************接口方法*********************************************
     */
    public void initSDK(View view) {
        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setLanguage("zh_cn");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        initParameter.setPushLogUrl("");//为空则不走游戏的日志上报地址
        initParameter.setShowUserAgreement(true);
        initParameter.setAihelpAppkey(AIHelpAppKey);
        initParameter.setAihelpAppID(AIHelpAppID);
        initParameter.setAihelpDomain(AIHelpDomain);
        initParameter.setReyunAppID(reyunAppId);
        initParameter.setUIVersion(SDKConstant.LANDSCAPE);//0：新UI横版  1：新UI竖版   其他：老UI

        SDK.getInstance().initSDK(this, initParameter, new InitCallBack() {
            @Override
            public void onSuccess() {
                showLog("初始化成功");
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("初始化失败：" + code + "------"+errorMsg);
            }
        });

    }


    public void loginUI(View view) {
        login();
    }

    private void login() {
        SDK.getInstance().sdkLogin(this, new LoginCallBack() {
            @Override
            public void onSuccess(String ticket,String sdkMemberType) {
                //ticket传给游戏服务器做登录校验
                showLog("登录成功：" + ticket +" sdkMemberType:"+ sdkMemberType);
                //判断用户的登录类型
                if (SDKConstant.TYPE_GUEST.equals(sdkMemberType)){
                    //游客
                } else if (SDKConstant.TYPE_ACCOUNT.equals(sdkMemberType)){
                    //账号登录
                } else if (SDKConstant.TYPE_FACEBOOK.equals(sdkMemberType)){
                    //fb登录
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
            public void onSuccess(String ticket,String sdkMemberType) {
                //ticket传给游戏服务器做登录校验
                showLog("切换账号成功：" + ticket);
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

//        SDK.getInstance().sdkLogout(this, new LogOutCallBack() {
//            @Override
//            public void onSuccess() {
//                showLog("注销成功");
//            }
//
//            @Override
//            public void onFailure(int code,String msg) {
//                showLog("注销失败：" + msg);
//            }
//        });



    }

    /**
     * 受管理的商品
     *
     * @param view
     */

    public void purchase(View view) {
//        String referenceId = "com.dyhd.game.seawar3d.pay00991";
        String skuId = "nuts_product_1";
        SDK.getInstance().sdkPurchase(this, "0", skuId, "", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
                if (payResult == null) return;
                String orderid= payResult.getOrderid();
                showLog("支付成功" + payResult.toString());
            }

            @Override
            public void onCancel() {
                showLog("支付取消");
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("支付失败：code-" +code+ ",  msg-"+msg);
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
//        skuList.add("com.nutspower.wjgame01.hugepack4999");
//        skuList.add("com.nutspower.wjgame01.megapack9999");
//        skuList.add("com.nutspower.wjgame01.limitedpackage199");
//        skuList.add("com.nutspower.wjgame01.hugepack4999");
//        skuList.add("com.nutspower.wjgame01.largepack1999");
//        skuList.add("com.nutspower.wjgame01.mediumpack999");
        //atw
//        skuList.add("com.nuts.atw.android.googleplay.1");
//        skuList.add("com.nuts.atw.android.googleplay.2");
//        skuList.add("com.nuts.atw.android.googleplay.3");
//        skuList.add("com.nuts.atw.android.googleplay.4");
//        skuList.add("com.nuts.atw.android.googleplay.5");
//        skuList.add("com.nuts.atw.android.googleplay.6");

        //poly
//        skuList.add("com.nuts.pa.android.googleplay.19");

        //test xiaomiPay
//        skuList.add("viking.package4999");


        skuList.add("nuts_product_1");
        skuList.add("nuts_product_2");
        skuList.add("nuts_product_3");


        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList, SDKConstant.INAPP,new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<ProductDetails> skuDetails) {
                showLog("查询本地价格成功：" + skuDetails.size()+"条");
                if (skuDetails.size() == 0) return;
                for (ProductDetails product : skuDetails) {
                    String skuId = product.getProductId();
                    String localPrice = product.getOneTimePurchaseOfferDetails().getFormattedPrice();
                    showLog(skuId + "    " + localPrice);
                }
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("查询本地价格失败：" + code + msg);
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

    }

    /**
     * 游客绑定FB账号
     *
     * @param view
     */
    public void guestBindFB(View view) {
        SDK.getInstance().sdkGuestBindThird(this, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定FB成功:" + type + ":ticket"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == SDKConstant.CONFLICT){
                    showLog("绑定FB冲突:" + msg);
                }else {
                    showLog("绑定FB失败：" + msg);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }
    /**
     * 游客绑定Google账号
     *
     * @param view
     */
    public void guestBindGoogle(View view) {
        SDK.getInstance().sdkGuestBindThird(this, SDKConstant.TYPE_GOOGLE, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定Google成功:" + type + ":ticket"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == SDKConstant.CONFLICT){
                    showLog("绑定Google冲突:" + msg);
                }else {
                    showLog("绑定Google失败:" + msg);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }



    /**
     * FB游戏登录
     * @param view
     */
    public void fbGameLogin(View view){
        SDK.getInstance().facebookGameLogin(new FbLoginListener() {
            @Override
            public void onSuccess(FacebookUser user) {
                showLog("fb游戏登录成功：fbid-" + user.getId());
            }

            @Override
            public void onFailure(int code,String msg) {

                showLog("fb游戏登录失败："+msg);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    /**
     * Google 登录
     * @param view
     */
    public void googleLogin(View view){
//        String webClientID = "892138677814-u0nb82tcoll567i97nmtmvbbveo4m6it.apps.googleusercontent.com";
//        String uuid = UUID.randomUUID().toString();
//        GoogleLoginManager.getInstance().login(this,webClientID,uuid);
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
     * 检查是否绑定FB
     * true绑定
     * false未绑定
     */
    public void isBindFacebook(View view) {

        SDK.getInstance().isBindFacebook(this, new BindFBCallback() {
            @Override
            public void onSuccess(boolean isBindFB) {
                showLog("isBindFacebook:"+isBindFB);
            }

            @Override
            public void onFail(int code,String msg) {
                showLog("isBindFacebook:"+code+"----"+msg);
            }
        });
    }
    /**
     * 检查是否绑定Google
     * true绑定
     * false未绑定
     */
    public void isBindGoogle(View view) {

        SDK.getInstance().isBindGoogle(this, new BindGoogleCallback() {
            @Override
            public void onSuccess(boolean isBindGoogle) {
                showLog("isBindGoogle:" + isBindGoogle);
            }

            @Override
            public void onFail(int code,String msg) {
                showLog("isBindGoogle:"+code+"----"+msg);
            }
        });
    }

    /**
     * 绑定邮箱
     * @param view
     */
    public void bindEmail(View view){
        SDK.getInstance().bindEmail(this, new BindResultCallBack() {
            @Override
            public void onSuccess() {
                showLog("bindEmail onSuccess");
            }

            @Override
            public void onCancel() {
                showLog("bindEmail onCancel");
            }

            @Override
            public void onFail(int code, String msg) {
                showLog("bindEmail onFail"+code+"----"+msg);
            }
        });
    }

    /**
     * 打开用户协议页面，FB政策要求登录界面要留一个常驻按钮，展示用户协议
     *
     * @param view
     */
    public void showUserAgreement(View view){
        SDK.getInstance().showUserAgreement(this, new AgreementCallBack() {
            @Override
            public void onSuccess() {
                showLog("用户同意协议");
            }

            @Override
            public void onCancel() {
                showLog("用户拒绝协议");
            }

            @Override
            public void onFail(int code, String msg) {
                showLog("打开协议失败");
            }
        });
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
    public void onDestroy() {
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
    public void other(View view){
        SDK.getInstance().adjustCustomEvent("eventID");
    }

    public void clear(View view) {
        logTv.setText("");
    }

    public void faq(View view) {
        Map<String,Object> config = new HashMap<>();
        HelpShiftManager.showFAQs(this,config);
    }

    public void conversation(View view) {
        Map<String, Object> config = new HashMap<>();
        //配置根据需要添加
//        config.put("tags", new String[]{"foo", "bar"});
        HelpShiftManager.showConversation(this,config);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == DeviceUtils.REQUEST_CODE_READ_PHONE_STATE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                FileUtils.createMkdirsAndFiles("test");
            }
        }
    }

    /**
     * 备份
     * @param view
     */
    public void backUp(View view) {
        //加入备份队列，闲时备份
//        BackupManager backupManager = new BackupManager(this);
//        backupManager.dataChanged();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress inetAddress = InetAddress.getByName("go.0egg.com");
                    boolean reachable = inetAddress.isReachable(5000);
                    LogUtils.e(TAG,"Ping结果："+reachable);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *     ********************************SDK拆分无UI接口******************************************
     */
    public void LoginBySocialGoogle(View view){
        SDK.getInstance().LoginBySocial(this, SDKConstant.TYPE_GOOGLE, new LoginCallBack() {
            @Override
            public void onSuccess(String ticket, String sdkMemberType) {
                showLog("Google登录成功："+sdkMemberType+"： "+ticket);
            }

            @Override
            public void onCancel() {
                showLog("Google登录取消");
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("Google登录失败:"+code+":"+msg);
            }
        });
    }
    public void LoginBySocialFB(View view){
        SDK.getInstance().LoginBySocial(this, SDKConstant.TYPE_FACEBOOK, new LoginCallBack() {
            @Override
            public void onSuccess(String ticket, String sdkMemberType) {
                showLog("FB 登录成功："+sdkMemberType+"： "+ticket);
            }

            @Override
            public void onCancel() {
                showLog("FB 登录取消");
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("FB 登录失败:"+code+":"+msg);
            }
        });
    }

    public void LoginByNuts(View view){
        SDK.getInstance().LoginByNuts(this, "frankma", "qqqqqq", new LoginCallBack() {
            @Override
            public void onSuccess(String ticket, String sdkMemberType) {
                showLog("坚果账号登录成功：sdkMemberType："+sdkMemberType+"   ticket:"+ticket);
            }

            @Override
            public void onCancel() {
                showLog("坚果账号登录取消");
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("坚果账号登录失败："+code+":  "+msg);
            }
        });
    }

    public void BindBySocialGoogle(View view){
        SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_GOOGLE, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定Google成功" +  ":type:"+type+ ":ticket:"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == SDKConstant.CONFLICT){
                    //该社交平台账号已经绑定过其他账号了
                    showLog("绑定Google冲突:"+code+":"+msg);
                    //弹出选项框供玩家选择
                    //选择1.登录其他社交账号：调用 SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_GOOGLE,true, new SocialBindCallBack()
                    //选择2.登录该已绑定的社交账号：调用 LoginBySocial(SDKConstant.TYPE_GOOGLE)
                    //选择3.关闭对话框
                }else {
                    showLog("绑定Google失败: "+code+":"+msg);
                }
            }

            @Override
            public void onCancel() {
                showLog("绑定Google取消");
            }
        });
    }
    public void BindBySocialGoogleRetry(View view){
        SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_GOOGLE,true, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定Google成功" + ":type:"+type+ ":ticket:"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("绑定Google失败: "+code+":"+msg);
            }

            @Override
            public void onCancel() {
                showLog("绑定Google取消");
            }
        });
    }
    public void BindBySocialFacebook(View view){
        SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_FACEBOOK, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定Facebook成功"+":type:"+type+ ":ticket:"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == SDKConstant.CONFLICT){
                    //该社交平台账号已经绑定过其他账号了
                    showLog("绑定Facebook冲突:"+msg);
                    //弹出选项框供玩家选择
                    //选择1.登录其他社交账号：调用 SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_GOOGLE,true, new SocialBindCallBack()
                    //选择2.登录该已绑定的社交账号：调用 LoginBySocial(SDKConstant.TYPE_GOOGLE)
                    //选择3.关闭对话框
                }else {
                    showLog("绑定Facebook失败: "+code+":"+msg);
                }
            }

            @Override
            public void onCancel() {
                showLog("绑定Facebook取消");
            }
        });
    }
    public void BindBySocialFacebookRetry(View view){
        SDK.getInstance().BindBySocial(this, SDKConstant.TYPE_FACEBOOK,true, new SocialBindCallBack() {
            @Override
            public void onSuccess(String type,String ticket) {
                showLog("绑定Facebook成功"  + ":type:"+type+ ":ticket:"+ticket);
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("绑定Facebook失败:"+code+":"+msg);
            }

            @Override
            public void onCancel() {
                showLog("绑定Facebook取消");
            }
        });
    }
    public void QueryBindStatus(View view){
        SDK.getInstance().QueryBindStatus(this, new BindStatusCallBack() {
            @Override
            public void onSuccess(boolean isBind, String type) {
                showLog("查询绑定状态成功：isBind:"+isBind+" type:"+type);
            }

            @Override
            public void onFailure(int code, String msg) {
                showLog("查询绑定状态失败："+code+":"+msg);
            }
        });
    }


}
