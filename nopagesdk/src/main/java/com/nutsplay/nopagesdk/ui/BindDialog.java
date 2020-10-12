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

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.TrackingManager;
import com.nutsplay.nopagesdk.utils.Installations;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutspower.commonlibrary.utils.LogUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BindDialog extends Dialog {

    public BindDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public BindDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BindDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack=loginCallBack;
        }

        public BindDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BindDialog dialog = new BindDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_account_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_account", "layout"), null);
            }

            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_bind", "id"));
            TextView bindTips = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_tips", "id"));
            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "iv_close", "id"));
            final ToggleButton pwdToggle = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle", "id"));

            bindTips.setText(SDKLangConfig.getInstance().findMessage("str_bind_tips"));
            bind.setText(SDKLangConfig.getInstance().findMessage("bind"));
            userName.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_account_tips"));
            pwd.setHint(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_password_tips"));

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
                }
            });

            //绑定一个新账号
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = userName.getText().toString();
                    String psw = pwd.getText().toString();

                    if (!SDKGameUtils.matchAccount(account)|| !SDKGameUtils.matchPw(psw)){
                        return;
                    }
                    String oauthid= Installations.id(context);
                    SDKManager.getInstance().sdkBindAccount2Dialog((Activity) context, oauthid, SDKConstant.TYPE_GUEST, account, psw, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //绑定成功后，用新账号登录
                            if (loginCallBack != null) loginCallBack.onSuccess(SDKManager.getInstance().getUser());
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
