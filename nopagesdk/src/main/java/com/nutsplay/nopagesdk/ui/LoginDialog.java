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

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.RegisterResultCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
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

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack = loginCallBack;
        }

        public LoginDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final FirstDialog dialog = new FirstDialog(context,SDKResUtils.getResId(context,"NutsDialogStyle","style"));
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
            resetPwd.setText(SDKLangConfig.getInstance().findMessage("str_reset_password"));


            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final EditText newPwd = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            newPwd.setHint(SDKLangConfig.getInstance().findMessage("new_password"));
            fillAccount(context,userName, pwd);


            //重置密码
            resetPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn.setText(SDKLangConfig.getInstance().findMessage("reset"));
                    resetPwd.setVisibility(View.INVISIBLE);
                    newPwd.setVisibility(View.VISIBLE);
                    backIv.setVisibility(View.VISIBLE);
                }
            });
            //登录或重置密码
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (signIn.getText().equals(SDKLangConfig.getInstance().findMessage("sign_in"))) {
                        //登录账号

                        if (userName.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()) {

                            SDKToast.getInstance().ToastShow("The account and password cannot be empty.", 2);
                            return;
                        }
                        SDKManager.getInstance().sdkLogin2Dialog((Activity) context, userName.getText().toString(), pwd.getText().toString(), loginCallBack, new ResultCallBack() {
                            @Override
                            public void onSuccess() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(String msg) {
                                LogUtils.d("sdkLogin2Dialog", msg);
                            }
                        });

                    } else {
                        //重置密码

                        if (userName.getText().toString().isEmpty() || pwd.getText().toString().isEmpty() || newPwd.getText().toString().isEmpty()) {

                            SDKToast.getInstance().ToastShow("The account and password cannot be empty.", 2);
                            return;
                        }
                        if (pwd.getText().toString().equals(newPwd.getText().toString())) {

                            SDKToast.getInstance().ToastShow("The new password is same as old password.", 2);
                            return;
                        }
                        SDKManager.getInstance().sdkResetPwd((Activity) context, userName.getText().toString(), pwd.getText().toString(), newPwd.getText().toString(), loginCallBack);
                    }

                }
            });
            //注册账号
            createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RegisterDialog.Builder builder = new RegisterDialog.Builder(context, loginCallBack, new RegisterResultCallBack() {
                        @Override
                        public void onSuccess(String account, String pas) {
                            if (account == null || pas == null) return;
                            userName.setText(account);
                            pwd.setText(pas);
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
                    fillAccount(context,userName,pwd);
                    signIn.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
                    resetPwd.setVisibility(View.VISIBLE);
                    newPwd.setVisibility(View.GONE);
                    backIv.setVisibility(View.INVISIBLE);
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
