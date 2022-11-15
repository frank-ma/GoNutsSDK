package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.NutsLoginManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 登录选项窗口
 */
public class LoginOptionsDialog extends Dialog {

    public LoginOptionsDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public LoginOptionsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoginOptionsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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

        public LoginOptionsDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LoginOptionsDialog dialog = new LoginOptionsDialog(context);
            if (inflater == null) return dialog;
            View layout;
            switch (SDKManager.getInstance().getUIVersion()){
                case 0://横版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_login_choose", "layout"), null);
                    break;
                case 1://竖版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_login_choose_portrait", "layout"), null);
                    break;
                default://旧版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_choose_normal", "layout"), null);
                    break;
            }

            //findView
            TextView visitorLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_visitor_sign_in", "id"));
            TextView accountLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_login", "id"));
            TextView accountRegister = layout.findViewById(SDKResUtils.getResId(context, "tv_register", "id"));
            TextView fbLogin = layout.findViewById(SDKResUtils.getResId(context,"fb_login","id"));
//            SignInButton googleButton = layout.findViewById(SDKResUtils.getResId(context,"sign_in_button","id"));
            ImageView closeImg = layout.findViewById(SDKResUtils.getResId(context,"ic_close","id"));
            View llLogin = layout.findViewById(SDKResUtils.getResId(context,"ll_login","id"));
            View llRegister = layout.findViewById(SDKResUtils.getResId(context,"ll_register","id"));


            //切换账号时显示Close按钮
            closeImg.setVisibility(isLogin ? View.INVISIBLE:View.VISIBLE);

            //设置自定义字体
            SDKGameUtils.setTypeFaceBold(context,visitorLogin);
            SDKGameUtils.setTypeFaceBold(context,accountLogin);
            SDKGameUtils.setTypeFaceBold(context,accountRegister);
            SDKGameUtils.setTypeFaceBold(context,fbLogin);

            visitorLogin.setText(SDKLangConfig.getInstance().findMessage("guest_login"));
            accountLogin.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
            accountRegister.setText(SDKLangConfig.getInstance().findMessage("sign_up"));

            //游客登录
            visitorLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }
                    SDKManager.getInstance().handleLogout((Activity) context);

//                    SDKManager.getInstance().showEmptyProgress((Activity) context);
                    SDKManager.getInstance().showProgress((Activity) context);
                    NutsLoginManager.getInstance().visitorLogin((Activity) context, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                            SDKManager.getInstance().hideProgress();
                        }

                        @Override
                        public void onFailure(String msg) {
                            SDKManager.getInstance().hideProgress();
                        }
                    });

                }
            });

            //账号登录
            llLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }
                    SDKManager.getInstance().handleLogout((Activity) context);

                    LoginDialog.Builder builder = new LoginDialog.Builder(context,loginCallBack,isLogin);
                    builder.create().show();
                    dialog.dismiss();
                }
            });

            //注册账号
            llRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }
                    SDKManager.getInstance().handleLogout((Activity) context);

                    RegisterDialog.Builder builder = new RegisterDialog.Builder(context, loginCallBack,isLogin);
                    builder.create().show();
                    dialog.dismiss();
                }
            });

            //fb登录
            fbLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }
                    SDKManager.getInstance().handleLogout((Activity) context);

                    SDKManager.getInstance().showEmptyProgress((Activity) context);
                    NutsLoginManager.getInstance().facebookLogin((Activity) context, loginCallBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                            SDKManager.getInstance().hideEmptyProgress();
                        }

                        @Override
                        public void onFailure(String msg) {
                            SDKManager.getInstance().hideEmptyProgress();
                        }
                    });
                }
            });

            //Google登录
//            googleButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //防止快速点击
//                    long count = System.currentTimeMillis() - lastTime;
//                    if (count <= 2000) {
//                        return;
//                    }else {
//                        lastTime = System.currentTimeMillis();
//                    }
//                    SDKManager.getInstance().handleLogout((Activity) context);
//
//                    SDKManager.getInstance().showEmptyProgress((Activity) context);
//                    LoginManager.getInstance().googleLogin((Activity) context, loginCallBack, new ResultCallBack() {
//                        @Override
//                        public void onSuccess() {
//                            dialog.dismiss();
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//                    });
//
//                }
//            });

            //关闭按钮
            closeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //恢复自动登录
                    SDKManager.getInstance().setAuto(true);

                    if (loginCallBack != null) loginCallBack.onCancel();
                    dialog.dismiss();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }
    }



}
