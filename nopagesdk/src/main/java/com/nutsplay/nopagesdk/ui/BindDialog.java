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
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.TrackingManager;
import com.nutsplay.nopagesdk.utils.Installations;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.LogUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BindDialog extends Dialog {

    public BindDialog(@NonNull Context context) {
        super(context);
    }

    public BindDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BindDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private ResultCallBack callBack;
        private LoginCallBack loginCallBack;

        public Builder(Context context, LoginCallBack loginCallBack,ResultCallBack cCallBack) {
            this.context = context;
            this.loginCallBack=loginCallBack;
            this.callBack = cCallBack;
        }

        public BindDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BindDialog dialog = new BindDialog(context);
            if (inflater == null) return dialog;
            View layout = inflater.inflate(SDKResUtils.getResId(context, "bind_account_dialog", "layout"), null);

            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_bind", "id"));
            TextView bindTips = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_tips", "id"));
            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "iv_close", "id"));

            bindTips.setText(SDKLangConfig.getInstance().findMessage("str_bind_tips"));
            bind.setText(SDKLangConfig.getInstance().findMessage("bind"));
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));

            //注册账号
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = userName.getText().toString();
                    String psw = pwd.getText().toString();

                    if (account.isEmpty()||psw.isEmpty()){
                        SDKToast.getInstance().ToastShow("The account and password cannot be empty.",2);
                        return;
                    }
                    if (!SDKGameUtils.matchAccount(account)|| !SDKGameUtils.matchPw(psw)){
                        return;
                    }
                    String oauthid= Installations.id(context);
                    SDKManager.getInstance().sdkBindAccount2Dialog((Activity) context, oauthid, SDKConstant.TYPE_GUEST, account, psw, callBack, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(String msg) {
                            LogUtils.e("BindDialog","sdkBindAccount---"+msg);
                        }
                    });

                }
            });

            //关闭按钮
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loginCallBack != null) {
                        loginCallBack.onSuccess(SDKManager.getInstance().getUser());
                        //登录追踪
                        TrackingManager.loginTracking(SDKManager.getInstance().getUser().getUserId());
                    }
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
