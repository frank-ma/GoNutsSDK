package com.nutsplay.nonutssdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.SkuDetails;
import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.kernel.SDK;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutspower.nutsgamesdk.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String clientId = "5dcbeab164b5b50deb76be93";
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
    private String buglyId = "1ee9849782";

    private TextView logTv;
    private Button initB,defaultLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logTv = findViewById(R.id.log);
        initB = findViewById(R.id.init);
        defaultLogin = findViewById(R.id.default_login);


        defaultLogin.callOnClick();
    }

    public void initSDK(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("en_us");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        SDK.getInstance().initSDK(this, initParameter, new InitCallBack() {
            @Override
            public void onSuccess() {
                showLog("初始化成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("初始化失败：" + errorMsg);
            }
        });

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
        initParameter.setLanguage("en_us");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);

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

        SDK.getInstance().sdkLogin(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
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
        String referenceId = "com.nutspower.nutsgamesdk.sub1";
        SDK.getInstance().sdkSubscription(this, "0", referenceId,"", new PurchaseCallBack() {
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
     * zh_cn, 中文
     * zh_hk, 中文
     * en_us, 英文
     * th_th, 泰语
     * vi_vn, 越语
     * ar_ar，阿拉伯语
     * kr，韩语
     * fr，法语
     * br，葡萄牙语
     * deu，德
     * sp，西班牙
     * it，意大利语
     * jp，日语
     * idn，印度尼西亚语
     * by:俄语
     * tr:土耳其语
     *
     * @param view
     */
    public void updateLanguage(View view) {
        SDK.getInstance().sdkUpdateLanguage("zh_hk");
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
     * 生命周期方法
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        //游戏退到后台，再回到前台时，检查是否有未完成的订单
        SDK.getInstance().sdkOnResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG","onDestroy");
        SDK.getInstance().sdkOnDestroy(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG","onPause");
        SDK.getInstance().sdkOnDestroy(this);
    }

    private void showLog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logTv.append("\n");
                logTv.append(msg);
            }
        });
    }
}
