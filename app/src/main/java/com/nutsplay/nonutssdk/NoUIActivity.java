package com.nutsplay.nonutssdk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.SkuDetails;
import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.RegisterCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.callback.SDKGetSkuDetailsCallback;
import com.nutsplay.nopagesdk.kernel.SDK;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutspower.nutsgamesdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 无UI接口
 *
 */
public class NoUIActivity extends BaseActivity {

    private String clientId = "5d7f63a6e73f2146c4b1e731";
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
    private String buglyId = "1ee9849782";

    private EditText userNameEt, pwdEt,newPwdEdt;
    private TextView logTv;
    private Button initB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_ui);
        userNameEt = findViewById(R.id.accountEt);
        pwdEt = findViewById(R.id.pwdEt);
        newPwdEdt=findViewById(R.id.newPwd);
        logTv = findViewById(R.id.log);
        initB=findViewById(R.id.init);


//        initB.callOnClick();
    }


    public void initSDK(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("en");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        SDK.getInstance().initSDK(this, initParameter, new InitCallBack() {
            @Override
            public void onSuccess(@Nullable User user) {
                showLog("初始化成功");
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("初始化失败：" + code+" "+errorMsg);
            }
        });

    }

    public void visitorLogin(View view) {

        SDK.getInstance().sdkLoginWithVisitor(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                showLog("login success:userId-" + user.getUserId());
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("visitor login fail:" + msg);
            }

            @Override
            public void onCancel() {
                showLog("游客登录取消");
            }
        });
    }

    /**
     * 默认登录，直接调用自动初始化
     * @param view
     */
    public void defaultLogin(View view){

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setLanguage("en");
        initParameter.setDebug(true);
        initParameter.setHasUI(true);
        SDK.getInstance().sdkDefaultLogin(this, initParameter, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                showLog("默认登录成功："+user.toString());
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("默认登录失败："+msg);

            }

            @Override
            public void onCancel() {
                showLog("默认登录取消");
            }
        });
    }

    public void register(View view) {

        SDK.getInstance().sdkRegister(this, userNameEt.getText().toString(), pwdEt.getText().toString(),new RegisterCallBack() {
            @Override
            public void onSuccess(User user) {

                showLog("注册成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("注册失败：" + errorMsg);
            }
        });

    }

    public void loginNoUI(View view) {

        SDK.getInstance().sdkLoginNoUI(this, userNameEt.getText().toString(), pwdEt.getText().toString(), new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                showLog("登录成功：" + user.toString());
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("登录失败：" + errorMsg);
            }

            @Override
            public void onCancel() {
                showLog("登录取消");
            }
        });
    }


    public void switchAccount(View view) {

        SDK.getInstance().sdkSwitchAccountNoUI(this,userNameEt.getText().toString(),pwdEt.getText().toString(), new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
                showLog("切换账号成功：" + user.toString());
            }

            @Override
            public void onFailure(int code,String errorMsg) {
                showLog("切换账号失败：" + errorMsg);
            }

            @Override
            public void onCancel() {
                showLog("切换账号取消");
            }
        });

    }

    /**
     * 重置密码
     *
     * @param view
     */
    public void resetPwd(View view){

        SDK.getInstance().sdkResetPwd(this, userNameEt.getText().toString(), pwdEt.getText().toString(), newPwdEdt.getText().toString(), new ResultCallBack() {
            @Override
            public void onSuccess() {
                showLog("修改密码成功");
            }

            @Override
            public void onFailure(String msg) {
                showLog("修改密码失败:"+msg);
            }
        });
    }

    public void logout(View view) {

        SDK.getInstance().sdkLogout(this,new LogOutCallBack() {
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

    public void purchase(View view) {
        String referenceId = "com.nutsplay.iap.item1002";
        SDK.getInstance().sdkPurchase(this, "0", referenceId, "", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
                if (payResult == null) return;
                showLog("支付成功" +payResult.toString());
            }

            @Override
            public void onCancel() {
                showLog("支付取消");
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("支付失败：" + msg);
            }
        });

    }

    public void createRoleTracking(View view){
        SDK.getInstance().sdkCreateRoleTracking(this,"0","001","xiaohao");
    }

    /**
     * 新接口，没有最多20条的限制
     * @param view
     */
    public void localPrice(View view){

        List<String> skuList = new ArrayList<>();
        for (int i = 0; i < 125; i++) {
            skuList.add("com.nutsplay.iap.item1002");
        }

        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList,SDKConstant.INAPP, new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<SkuDetails> skuDetails) {
                showLog("查询本地价格成功："+skuDetails.size());
                if (skuDetails.size() == 0)return;
                for (SkuDetails sku:skuDetails){
                    String skuId=sku.getSku();
                    String localPrice=sku.getPrice();
                    showLog(skuId+"    "+localPrice);
                }
            }

            @Override
            public void onFailure(int code,String msg) {
                showLog("查询本地价格失败："+msg);
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
     * fo，法语
     * pt，葡萄牙语
     * de，德
     * sp，西班牙
     * it，意大利语
     * ja，日语
     * id，印度尼西亚语
     * ru:俄语
     * @param view
     */
    public void updateLanguage(View view){
        SDK.getInstance().sdkUpdateLanguage("en_us");
    }

    /**
     * 游客绑定新账号
     * @param view
     */
    public void bind(View view){

        SDK.getInstance().sdkBindAccount(this, "testtesttesttesttesttest", SDKConstant.TYPE_GUEST, userNameEt.getText().toString(), pwdEt.getText().toString(), new ResultCallBack() {

            @Override
            public void onSuccess() {
                showLog("绑定成功");
            }

            @Override
            public void onFailure(String msg) {
                showLog("绑定失败:" + msg);
            }
        });
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


    @Override
    protected void onRestart() {
        super.onRestart();
        //游戏退到后台，再回到前台时，检查是否有未完成的订单
        SDK.getInstance().sdkOnRestart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SDK.getInstance().sdkOnDestroy(this);
    }

    private void showLog(final String msg) {
        Log.e("MainActivity",msg);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logTv.append("\n");
                logTv.append(msg);
            }
        });
    }
}
