package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.SignInButton;
import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class FirstDialog extends Dialog {

    public FirstDialog(@NonNull Context context) {
        super(context);
    }

    public FirstDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FirstDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;
        private boolean isLogin = true;//是登录还是切换账号
        private long lastTime = 0;

        public Builder(Context context, LoginCallBack loginCallBack,boolean isLogin) {
            this.context = context;
            this.loginCallBack = loginCallBack;
            this.isLogin = isLogin;
        }

        public FirstDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final FirstDialog dialog = new FirstDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_choose_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_choose", "layout"), null);
            }
            TextView visitorLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_visitor_sign_in", "id"));
            TextView accountLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_create_account", "id"));
            TextView loginTips = layout.findViewById(SDKResUtils.getResId(context, "tv_tips", "id"));
//            LoginButton loginButton = layout.findViewById(SDKResUtils.getResId(context,"login_button","id"));
            TextView loginButton = layout.findViewById(SDKResUtils.getResId(context,"login_button","id"));
            final SignInButton googleButton = layout.findViewById(SDKResUtils.getResId(context,"sign_in_button","id"));
            final ImageView closeImg = layout.findViewById(SDKResUtils.getResId(context,"ic_close","id"));

            //切换账号时显示关闭按钮
            if (isLogin){
                closeImg.setVisibility(View.INVISIBLE);
            }else {
                closeImg.setVisibility(View.VISIBLE);
            }

            loginTips.setText(SDKLangConfig.getInstance().findMessage("str_login_tips"));
            visitorLogin.setText(SDKLangConfig.getInstance().findMessage("guest_login"));
            accountLogin.setText(SDKLangConfig.getInstance().findMessage("sign_in"));

            visitorLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().visitorLogin((Activity) context, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });

                }
            });
            accountLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginDialog.Builder builder = new LoginDialog.Builder(context,loginCallBack,isLogin);
                    builder.create().show();
                    dialog.dismiss();
                }
            });

            //fb登录
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    long count = System.currentTimeMillis() - lastTime;
                    if (count <= 2000) {
                        return;
                    }else {
                        lastTime = System.currentTimeMillis();
                    }
                    LoginManager.getInstance().facebookLogin((Activity) context, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                }
            });

            //Google登录
            googleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().googleLogin((Activity) context, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });

                }
            });

            //关闭按钮
            closeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loginCallBack != null) loginCallBack.onCancel();
                    dialog.dismiss();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }
    }



}
