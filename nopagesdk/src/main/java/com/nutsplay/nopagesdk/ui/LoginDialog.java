package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.RegisterResultCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.LogUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class LoginDialog extends Dialog {

    public LoginDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public LoginDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoginDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;
        private boolean isLogin = true;//是登录还是切换账号
//        private Handler handler;
        private long lastTime = 0;

        public Builder(Context context, LoginCallBack loginCallBack,boolean isLogin) {
            this.context = context;
            this.loginCallBack = loginCallBack;
            this.isLogin = isLogin;
        }

        @SuppressLint("HandlerLeak")
        public LoginDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LoginDialog dialog = new LoginDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login", "layout"), null);
            }
            final TextView signIn = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_in", "id"));
            final TextView createAccount = layout.findViewById(SDKResUtils.getResId(context, "tv_create_account", "id"));
            final TextView resetPwd = layout.findViewById(SDKResUtils.getResId(context, "tv_reset_pwd", "id"));
            signIn.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
            createAccount.setText(SDKLangConfig.getInstance().findMessage("str_create_account"));
            resetPwd.setText(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_ResetPassword"));


            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final EditText newPwd = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            final ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "iv_close", "id"));
            final ToggleButton pwdToggle = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle", "id"));
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            newPwd.setHint(SDKLangConfig.getInstance().findMessage("new_password"));
            fillAccount(context,userName, pwd);

//
//            handler = new Handler(){
//                @Override
//                public void handleMessage(@NonNull Message msg) {
//                    super.handleMessage(msg);
//                    switch (msg.what){
//                        case 0:
//                            backIv.callOnClick();
//                            if (pwd !=null) pwd.setText("");
//                            break;
//                        case 1:
//                            TempUser tempUser = (TempUser) msg.obj;
//                            if (tempUser == null) return;
//                            if (userName!=null) userName.setText(tempUser.getAccount());
//                            if (pwd!=null) pwd.setText(tempUser.getPwd());
//                            break;
//                        default:
//                            break;
//
//                    }
//                }
//            };

            //忘记密码
            resetPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ResetPwdDialog.Builder builder = new ResetPwdDialog.Builder(context);
                    builder.create().show();
//                    pwd.setText("");
                }
            });

            //显隐密码
            pwdToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"eyes_close","drawable"));
                    }else {
                        pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"eyes_open","drawable"));
                    }
                    pwd.setSelection(pwd.getText().toString().length());//将光标设置在文本框的末尾
                }
            });

            //登录或重置密码
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }


                    if (!SDKGameUtils.matchAccount(userName.getText().toString())||!SDKGameUtils.matchPw(pwd.getText().toString())){
                        return;
                    }

                    if (signIn.getText().equals(SDKLangConfig.getInstance().findMessage("sign_in"))) {
                        //登录账号
                        SDKManager.getInstance().showEmptyProgress((Activity) context);
                        SDKManager.getInstance().sdkLogin2Dialog((Activity) context, userName.getText().toString(), pwd.getText().toString(), new ResultCallBack() {
                            @Override
                            public void onSuccess() {

                                if (SDKManager.getInstance().getUser() != null){
                                    if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()){
                                        String content = SDKLangConfig.getInstance().findMessage("bind_email_tips");
                                        SDKToast.getInstance().ToastShow(content,1);
                                    }
                                    loginCallBack.onSuccess(SDKManager.getInstance().getUser());
                                }
                                dialog.dismiss();
                                SDKManager.getInstance().hideEmptyProgress();
                                //新用户第一次登录不弹绑定提示，第二次再弹
//                                if (SDKGameUtils.getInstance().isFirstAccountLogin(context)){
//                                    loginCallBack.onSuccess(SDKManager.getInstance().getUser());
//                                    SDKGameUtils.getInstance().setFirstAccountLogin(context,false);
//                                    dialog.dismiss();
//                                }else {
//
////                                    if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()) {
////                                        String content = SDKLangConfig.getInstance().findMessage("bind_email_tips");
////                                        TipDialog.Builder tipDialog = new TipDialog.Builder(context,"Tips",content);
////                                        tipDialog.setOnConfirmBtnClickListener(new TipDialog.onConfirmBtnClickListener() {
////                                            @Override
////                                            public void onConfirmBtnClick() {
////                                                loginCallBack.onSuccess(SDKManager.getInstance().getUser());
////                                                dialog.dismiss();
////                                            }
////                                        });
////                                        tipDialog.create().show();
////                                    } else {
////                                        loginCallBack.onSuccess(SDKManager.getInstance().getUser());
////                                        dialog.dismiss();
////                                    }
//                                    String content = SDKLangConfig.getInstance().findMessage("bind_email_tips");
//                                    SDKToast.getInstance().ToastShow(content,1);
//                                    loginCallBack.onSuccess(SDKManager.getInstance().getUser());
//                                    dialog.dismiss();
//                                }
                            }

                            @Override
                            public void onFailure(String msg) {
                                SDKManager.getInstance().hideEmptyProgress();
                                if (loginCallBack != null) loginCallBack.onFailure(SDKConstant.network_error,msg);
                                LogUtils.d("sdkLogin2Dialog", msg);

                            }
                        });

                    } else {
                        //重置密码
                        if (!SDKGameUtils.matchPw(newPwd.getText().toString())){
                            return;
                        }
                        if (pwd.getText().toString().equals(newPwd.getText().toString())) {
                            SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("pwd_different"), 2);
                            return;
                        }
                        SDKManager.getInstance().sdkResetPwd((Activity) context, userName.getText().toString(), pwd.getText().toString(), newPwd.getText().toString(), new ResultCallBack() {
                            @Override
                            public void onSuccess() {
//                                backIv.callOnClick();
//                                new Handler().post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        pwd.setText("");
//                                    }
//                                });
//                                handler.sendEmptyMessage(0);
                                backIv.callOnClick();
                                if (pwd !=null) pwd.setText("");
                            }

                            @Override
                            public void onFailure(String msg) {

                            }
                        });
                    }

                }
            });
            //注册账号
            createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }
                    RegisterDialog.Builder builder = new RegisterDialog.Builder(context, new RegisterResultCallBack() {
                        @Override
                        public void onSuccess(final String account, final String pas) {
                            try{
                                if (StringUtils.isBlank(account) || StringUtils.isBlank(pas)) return;
//                                ((Activity)context).runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        userName.setText(account);
//                                        pwd.setText(pas);
//                                    }
//                                });
//                                Looper.prepare();
//                                new Handler(Looper.getMainLooper()).post(new Runnable(){
//                                    @Override
//                                    public void run() {
//                                        userName.setText(account);
//                                        pwd.setText(pas);
//                                    }
//                                });
//                                Looper.loop();
//                                Message message = new Message();
//                                message.what = 1;
//                                TempUser user = new TempUser();
//                                user.setAccount(account);
//                                user.setPwd(pas);
//                                message.obj = user;
//                                handler.sendMessage(message);


                                Log.d("LoginDialog","MainThreadID_bindfb:"+ Looper.getMainLooper().getThread().getId());
                                Log.d("LoginDialog","ThreadID_bindfb:"+Thread.currentThread().getId());
                                dialog.getCurrentFocus();
                                if (userName!=null) userName.setText(account);
                                if (pwd!=null) pwd.setText(pas);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                    builder.create().show();

                }
            });

            //返回按钮
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fillAccount(context, userName, pwd);
                    signIn.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
                    resetPwd.setVisibility(View.VISIBLE);
                    newPwd.setVisibility(View.GONE);
                    backIv.setVisibility(View.INVISIBLE);
                }
            });

            //关闭按钮
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    FirstDialog.Builder builder = new FirstDialog.Builder(context, loginCallBack,isLogin);
                    builder.create().show();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

        /**
         * 填充账号和密码
         * @param context
         * @param userName
         * @param pwd
         */
        private void fillAccount(Context context,EditText userName, EditText pwd) {
            if (userName == null || pwd == null) return;
            //记住账号密码
            String lastAccount = SPManager.getInstance(context).getString(SPKey.key_user_name_last_login, "");
            String lastPwd = SPManager.getInstance(context).getString(SPKey.key_pwd_last_login, "");
            if (StringUtils.isNotBlank(lastAccount)) userName.setText(lastAccount);
            if (StringUtils.isNotBlank(lastPwd)) pwd.setText(lastPwd);
        }
    }
}
