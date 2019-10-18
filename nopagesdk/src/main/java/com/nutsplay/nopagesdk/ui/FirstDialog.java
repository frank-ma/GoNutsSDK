package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutspower.commonlibrary.utils.LogUtils;

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

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack = loginCallBack;
        }

        public FirstDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final FirstDialog dialog = new FirstDialog(context,SDKResUtils.getResId(context,"NutsDialogStyle","style"));
            final FirstDialog dialog = new FirstDialog(context);
            if (inflater == null) return dialog;
            View layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_choose", "layout"), null);
            TextView visitorLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_visitor_sign_in", "id"));
            TextView accountLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_create_account", "id"));
            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_bind", "id"));
            TextView loginTips = layout.findViewById(SDKResUtils.getResId(context, "tv_tips", "id"));

            loginTips.setText(SDKLangConfig.getInstance().findMessage("str_login_tips"));
            visitorLogin.setText(SDKLangConfig.getInstance().findMessage("guest_login"));
            accountLogin.setText(SDKLangConfig.getInstance().findMessage("sign_in"));

            visitorLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().visitorLogin((Activity) context, loginCallBack);
                    dialog.dismiss();
                }
            });
            accountLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LoginDialog.Builder builder = new LoginDialog.Builder(context,loginCallBack);
                    builder.create().show();
                    dialog.dismiss();
                }
            });
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BindDialog.Builder builder = new BindDialog.Builder(context, loginCallBack,new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            LogUtils.e("FirstDialog","Bind successful");
                        }

                        @Override
                        public void onFailure(String msg) {
                            LogUtils.e("FirstDialog","Bind failed:" + msg);
                        }
                    });
                    builder.create().show();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }
    }

}
