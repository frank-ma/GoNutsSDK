package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
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

import com.nutsplay.nopagesdk.R;
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
 * Desc: 坚果账号登录接口
 */
public class LoginDialog extends Dialog {

    public LoginDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context, "dialog_anim_style", "style"));
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

        public Builder(Context context, LoginCallBack loginCallBack, boolean isLogin) {
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
            if (SDKManager.getInstance().isCommonVersion()) {
                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_login", "layout"), null);
            } else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login", "layout"), null);
            }
            TextView signIn = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_in", "id"));
            TextView createAccount = layout.findViewById(SDKResUtils.getResId(context, "tv_create_account", "id"));
            TextView resetPwd = layout.findViewById(SDKResUtils.getResId(context, "tv_reset_pwd", "id"));
            ImageView ivDone = layout.findViewById(SDKResUtils.getResId(context, "iv_done", "id"));
            EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "iv_close", "id"));
            ToggleButton pwdToggle = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle", "id"));

            //设置自定义字体
            SDKGameUtils.setTypeFaceBold(context, signIn);
            SDKGameUtils.setTypeFace(context, createAccount);

            String signUpTip = SDKLangConfig.getInstance().findMessage("sign_up_tip")+" ";
            String signUp = SDKLangConfig.getInstance().findMessage("sign_up")+" ";
            createAccount.setText(Html.fromHtml("<font color=\"#BBBBBB\">"+ signUpTip +"</font><font color=\"#977cdc\"> " + signUp +"</font>"));

            signIn.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
            //增加下划线
            resetPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            resetPwd.getPaint().setAntiAlias(true);
            resetPwd.setText(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_ResetPassword"));
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));
            fillAccount(context, userName, pwd);


            //忘记密码
            resetPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ResetPwdDialog.Builder builder = new ResetPwdDialog.Builder(context);
                    builder.create().show();
                }
            });

            //显隐密码
            pwdToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context, "icon_grey_visibility_off", "drawable"));
                    } else {
                        pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context, "icon_grey_visibility", "drawable"));
                    }
                    pwd.setSelection(pwd.getText().toString().length());//将光标设置在文本框的末尾
                }
            });

            //登录
            signIn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //防止快速点击
//                    if (SDKGameUtils.isMultiClicks()) {
//                        return;
//                    }

                    if (!SDKGameUtils.matchAccount(context, userName,ivDone, userName.getText().toString())) {
                        return;
                    }
                    if (!SDKGameUtils.matchPw(context,pwd,pwd.getText().toString())){
                        pwdToggle.setChecked(true);
                        pwd.setTextColor(R.color.color_da6a6a);
                        return;
                    }else {
                        pwdToggle.setChecked(false);
                        pwd.setTextColor(R.color.color_4c506b);
                    }

                    //登录账号
                    SDKManager.getInstance().showEmptyProgress((Activity) context);
                    SDKManager.getInstance().sdkLogin2Dialog((Activity) context, userName.getText().toString(), pwd.getText().toString(), new ResultCallBack() {
                        @Override
                        public void onSuccess() {

                            if (SDKManager.getInstance().getUser() != null) {
                                if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()) {
                                    String content = SDKLangConfig.getInstance().findMessage("bind_email_tips");
                                    SDKToast.getInstance().ToastShow(content, 1);
                                }
                                if (loginCallBack != null) loginCallBack.onSuccess(SDKManager.getInstance().getUser());
                            }
                            dialog.dismiss();
                            SDKManager.getInstance().hideEmptyProgress();
                        }

                        @Override
                        public void onFailure(String msg) {
                            SDKManager.getInstance().hideEmptyProgress();
                            if (loginCallBack != null)
                                loginCallBack.onFailure(SDKConstant.network_error, msg);
                            LogUtils.d("sdkLogin2Dialog", msg);

                        }
                    });
                }
            });

            //注册账号
            createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }
                    RegisterDialog.Builder builder = new RegisterDialog.Builder(context, new RegisterResultCallBack() {
                        @Override
                        public void onSuccess(final String account, final String pas) {

                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                    builder.create().show();
                    dialog.dismiss();
                }
            });

            //返回按钮
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    LoginOptionsDialog.Builder builder = new LoginOptionsDialog.Builder(context, loginCallBack, isLogin);
                    builder.create().show();
                }
            });

            //关闭按钮
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    LoginOptionsDialog.Builder builder = new LoginOptionsDialog.Builder(context, loginCallBack, isLogin);
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
         *
         * @param context
         * @param userName
         * @param pwd
         */
        private void fillAccount(Context context, EditText userName, EditText pwd) {
            if (userName == null || pwd == null) return;
            //记住账号密码
            String lastAccount = SPManager.getInstance(context).getString(SPKey.key_user_name_last_login, "");
            String lastPwd = SPManager.getInstance(context).getString(SPKey.key_pwd_last_login, "");
            if (StringUtils.isNotBlank(lastAccount)) userName.setText(lastAccount);
            if (StringUtils.isNotBlank(lastPwd)) pwd.setText(lastPwd);
        }
    }
}
