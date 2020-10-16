package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

import com.nutsplay.nopagesdk.callback.RegisterResultCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
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
        private RegisterResultCallBack registerCallBack;
        private long lastTime = 0;
        public Builder(Context context,RegisterResultCallBack registerCallBack) {
            this.context = context;
            this.registerCallBack = registerCallBack;
        }

        public RegisterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RegisterDialog dialog = new RegisterDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_signup_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_signup", "layout"), null);
            }

            TextView signUp = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_up", "id"));
            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final EditText repeatPwd = layout.findViewById(SDKResUtils.getResId(context, "et_repeat_pwd", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            final TextView autoGenerationTv = layout.findViewById(SDKResUtils.getResId(context, "tv_auto_generation", "id"));
            final TextView titleTv = layout.findViewById(SDKResUtils.getResId(context, "title", "id"));
            final ToggleButton pwdToggle1 = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle1", "id"));
            final ToggleButton pwdToggle2 = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle2", "id"));

            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));//请输入账号
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            repeatPwd.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            signUp.setText(SDKLangConfig.getInstance().findMessage("sign_up"));
            titleTv.setText(SDKLangConfig.getInstance().findMessage("nuts_Createaccount"));

            //显隐密码
            pwdToggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle1.setBackgroundResource(SDKResUtils.getResId(context,"eyes_close","drawable"));
                    }else {
                        pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle1.setBackgroundResource(SDKResUtils.getResId(context,"eyes_open","drawable"));
                    }
                    pwd.setSelection(pwd.getText().toString().length());
                }
            });

            //显隐密码
            pwdToggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        repeatPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle2.setBackgroundResource(SDKResUtils.getResId(context,"eyes_close","drawable"));
                    }else {
                        repeatPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle2.setBackgroundResource(SDKResUtils.getResId(context,"eyes_open","drawable"));
                    }
                    repeatPwd.setSelection(repeatPwd.getText().toString().length());
                }
            });

            //自动生成账号密码
            autoGenerationTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autoGenerationAccountPwd(userName,pwd,repeatPwd);
                }
            });

            //注册账号
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

                    final String account = userName.getText().toString();
                    final String psw = pwd.getText().toString();
                    String rePsw = repeatPwd.getText().toString();

                    if (!SDKGameUtils.matchAccountReg(account)||!SDKGameUtils.matchPw(psw)||!SDKGameUtils.matchPw(rePsw)){
                        return;
                    }
                    if (!psw.equals(rePsw)) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("pwd_different"), 2);
                        return;
                    }
                    SDKManager.getInstance().sdkRegister2Dialog((Activity) context, account, psw, registerCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            if (registerCallBack != null) registerCallBack.onSuccess(account,psw);
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
