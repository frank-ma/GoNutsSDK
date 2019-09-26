package com.nutsplay.nonutssdk;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.PayResult;
import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.callback.InitCallBack;
import com.nutsplay.nopagesdk.callback.LogOutCallBack;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.PurchaseCallBack;
import com.nutsplay.nopagesdk.callback.RegisterCallBack;
import com.nutsplay.nopagesdk.kernel.SDKManager;

public class MainActivity extends AppCompatActivity {

    private String clientId = "5d7f63a6e73f2146c4b1e731";
    private String appsflyerId = "VBmCBKvNg5uvd4iiLZSx7J";
    private String buglyId = "1ee9849782";
    private String dataEyeId = "C0D10A7AA5016F9B3FCCBB6821EC72F91";

    private EditText userNameEt, pwdEt;
    private TextView logTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEt = findViewById(R.id.accountEt);
        pwdEt = findViewById(R.id.pwdEt);
        logTv = findViewById(R.id.log);
    }

    public void initSDK(View view) {

        InitParameter initParameter = new InitParameter();
        initParameter.setClientId(clientId);
        initParameter.setAppsflyerId(appsflyerId);
        initParameter.setBuglyId(buglyId);
        initParameter.setDataeyeId(dataEyeId);
        initParameter.setLanguage("en_us");
        initParameter.setDebug(true);
        SDKManager.getInstance().initSDK(this, initParameter, new InitCallBack() {
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

    public void register(View view) {

        SDKManager.getInstance().sdkRegister(this, userNameEt.getText().toString(), pwdEt.getText().toString(), new RegisterCallBack() {
            @Override
            public void onSuccess() {

                showLog("注册成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                showLog("注册失败：" + errorMsg);
            }
        });

    }

    public void login(View view) {

        SDKManager.getInstance().sdkLogin(this, userNameEt.getText().toString(), pwdEt.getText().toString(), new LoginCallBack() {
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

    public void visitorLogin(View view) {

        SDKManager.getInstance().sdkLoginWithVisitor(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                showLog("visitor login success:" + user.getUserId());
            }

            @Override
            public void onFailure(String msg) {
                showLog("visitor login fail:" + msg);
            }
        });
    }

    public void fbLogin(View view) {

        SDKManager.getInstance().sdkLoginWithFacebook(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                showLog("fb login success:" + user.getUserId());
            }

            @Override
            public void onFailure(String msg) {
                showLog("fb login fail:" + msg);
            }
        });
    }

    public void googleLogin(View view) {

        SDKManager.getInstance().sdkLoginWithGoogle(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                showLog("google login success:" + user.getUserId());
            }

            @Override
            public void onFailure(String msg) {
                showLog("google login fail:" + msg);
            }
        });
    }

    public void switchAccount(View view) {

    }

    public void logout(View view) {

        SDKManager.getInstance().sdkLogout(this,new LogOutCallBack() {
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

    public void purchase(View view) {
        String referenceId = "com.nutsplay.iap.item1001";
        SDKManager.getInstance().sdkPurchase(this, "0", referenceId, "", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
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

    private void showLog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logTv.append("\n");
                logTv.append(msg);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //游戏退到后台，再回到前台时，检查是否有未完成的订单
        SDKManager.getInstance().sdkOnResume();
    }
}
