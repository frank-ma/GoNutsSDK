package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 注册账号Dialog
 */
public class RegisterDialog extends Dialog {

    public RegisterDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public RegisterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegisterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;
        private boolean isLogin = true;//是登录还是切换账号

        public Builder(Context context, LoginCallBack loginCallBack,boolean isLogin) {
            this.context = context;
            this.loginCallBack = loginCallBack;
            this.isLogin = isLogin;
        }

        public RegisterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RegisterDialog dialog = new RegisterDialog(context);
            if (inflater == null) return dialog;
            View layout;
            switch (SDKManager.getInstance().getUIVersion()){
                case 0://横版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_register", "layout"), null);
                    break;
                case 1://竖版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_register_portrait", "layout"), null);
                    break;
                default://旧版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_signup_normal", "layout"), null);
                    break;
            }
//            if (SDKManager.getInstance().getUIVersion()){
//                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_register", "layout"), null);
//            }else {
//                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_signup", "layout"), null);
//            }

            TextView signUp = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_up", "id"));
            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final EditText repeatPwd = layout.findViewById(SDKResUtils.getResId(context, "et_repeat_pwd", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            final TextView loginTv = layout.findViewById(SDKResUtils.getResId(context, "tv_login", "id"));
            final ToggleButton pwdToggle = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle", "id"));
            ImageView ivDone = layout.findViewById(SDKResUtils.getResId(context, "iv_done", "id"));
            TextView tipRegister = layout.findViewById(SDKResUtils.getResId(context, "tip_register", "id"));

            //设置自定义字体
            SDKGameUtils.setTypeFaceBold(context,signUp);
            SDKGameUtils.setTypeFace(context,loginTv);


            String signInTip = SDKLangConfig.getInstance().findMessage("sign_in_tip")+" ";
            String signIn = SDKLangConfig.getInstance().findMessage("sign_in");
            loginTv.setText(Html.fromHtml("<font color=\"#BBBBBB\">" + signInTip + "</font><font color=\"#4f84e2\"> " + signIn + "</font>"));

            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));//请输入账号
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            repeatPwd.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            signUp.setText(SDKLangConfig.getInstance().findMessage("sign_up"));
            if (tipRegister != null){
                tipRegister.setText(SDKLangConfig.getInstance().findMessage("sign_up"));
            }

            //显隐密码
            pwdToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        repeatPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"icon_grey_visibility_off","drawable"));
                    }else {
                        pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        repeatPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"icon_grey_visibility","drawable"));
                    }
                    pwd.setSelection(pwd.getText().toString().length());
                    repeatPwd.setSelection(repeatPwd.getText().toString().length());
                }
            });

            //注册账号
            signUp.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    final String account = userName.getText().toString();
                    final String psw = pwd.getText().toString();
                    String rePsw = repeatPwd.getText().toString();

                    if (!SDKGameUtils.matchAccount(context,userName,ivDone,account)){
                        return;
                    }
                    //判断密码
                    if (!SDKGameUtils.match2Pw(context,pwd,repeatPwd)){
                        pwdToggle.setChecked(true);
                        return;
                    }else {
                        pwdToggle.setChecked(false);
                    }

                    if (!psw.equals(rePsw)) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("pwd_different"), 2);
                        return;
                    }
                    SDKManager.getInstance().sdkRegister2Dialog((Activity) context, account, psw, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                            //注册成功
//                            SaveUserInfoDialog.Builder builder = new SaveUserInfoDialog.Builder(context,account,psw);
//                            builder.create().show();
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });

                }
            });

            //返回按钮
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    LoginOptionsDialog optionsDialog = new LoginOptionsDialog.Builder(context, SDKManager.getInstance().getLoginCallBack(), isLogin).create();
                    optionsDialog.show();
                }
            });

            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    LoginDialog loginDialog = new LoginDialog.Builder(context,SDKManager.getInstance().getLoginCallBack(), isLogin).create();
                    loginDialog.show();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

        private void autoGenerationAccountPwd(EditText userName,EditText password,EditText repeatPwd) {

            String account = SDKGameUtils.generate10Account();
            String pw = SDKGameUtils.generate6Password();
            userName.setText(account);
            password.setText(pw);
            repeatPwd.setText(pw);
        }
    }

}
