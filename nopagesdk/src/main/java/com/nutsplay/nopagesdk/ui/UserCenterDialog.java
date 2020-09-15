package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class UserCenterDialog extends Dialog {

    public UserCenterDialog(@NonNull Context context) {
        super(context);
    }

    public UserCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UserCenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private Handler handler;
        public Builder(Context context) {
            this.context = context;
        }

        public UserCenterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UserCenterDialog dialog = new UserCenterDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_center_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_center", "layout"), null);
            }
            TextView bindEmail = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_email", "id"));
            final TextView bindFb = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_fb", "id"));
            TextView resetPwd = layout.findViewById(SDKResUtils.getResId(context, "tv_reset_pwd", "id"));
            TextView emailTip = layout.findViewById(SDKResUtils.getResId(context, "tv_email", "id"));
            ImageView imgClose = layout.findViewById(SDKResUtils.getResId(context, "img_close", "id"));
            //如果是Facebook用户就不显示绑定FB按钮了,账号登录也不能绑定
            if (SDKManager.getInstance().getUser()!=null &&
                    SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_FACEBOOK)||
                    SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_ACCOUNT)){
                bindFb.setVisibility(View.GONE);
            }
            if (SDKManager.getInstance().getUser()!=null){
                if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()){
                    bindEmail.setVisibility(View.VISIBLE);
                }else {
                    bindEmail.setVisibility(View.GONE);
                    String textContent = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail")+SDKGameUtils.hideEmail(SDKManager.getInstance().getUser().getBindEmail());
                    emailTip.setText(textContent);
                }
            }

            //显示语言多样化
            bindEmail.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            bindFb.setText(SDKLangConfig.getInstance().findMessage("str_bind_facebook"));
            resetPwd.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));


            handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case 0:
                            bindFb.setVisibility(View.GONE);
                            break;
                        default:
                            break;

                    }
                }
            };

            //绑定邮箱
            bindEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmailBindDialog.Builder builder = new EmailBindDialog.Builder(context);
                    builder.create().show();
                }
            });
            //绑定FB
            bindFb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SDKManager.getInstance().sdkGuestBindFB((Activity) context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //游客绑定FB成功
                            handler.sendEmptyMessage(0);
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                }
            });

            //重置密码
            resetPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ResetPwdDialog.Builder builder = new ResetPwdDialog.Builder(context);
                    builder.create().show();
                }
            });
            //关闭
            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
