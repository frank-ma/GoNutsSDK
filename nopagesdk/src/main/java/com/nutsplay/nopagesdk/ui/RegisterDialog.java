package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));//请输入账号
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            repeatPwd.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            signUp.setText(SDKLangConfig.getInstance().findMessage("sign_up"));

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
                            registerCallBack.onSuccess(account,psw);
                            //注册成功
//                            SaveUserInfoDialog.Builder builder = new SaveUserInfoDialog.Builder(context,account,psw);
//                            builder.create().show();
                            dialog.dismiss();
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
