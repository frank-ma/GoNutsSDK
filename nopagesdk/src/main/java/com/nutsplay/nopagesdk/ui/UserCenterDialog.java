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
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public UserCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UserCenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private long lastTime = 0;
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
            final TextView bindEmail = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_email", "id"));
            final TextView bindFb = layout.findViewById(SDKResUtils.getResId(context, "tv_bind_fb", "id"));
            final TextView resetPwd = layout.findViewById(SDKResUtils.getResId(context, "tv_reset_pwd", "id"));
            final TextView emailTip = layout.findViewById(SDKResUtils.getResId(context, "tv_email", "id"));
            ImageView imgClose = layout.findViewById(SDKResUtils.getResId(context, "img_close", "id"));
            final TextView emptyTxt = layout.findViewById(SDKResUtils.getResId(context, "nothing", "id"));
            //如果是Facebook用户就不显示绑定FB按钮了,账号登录也不能绑定

            if (SDKManager.getInstance().getUser() != null){
                //游客登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_GUEST)){
                    //游客绑定FB之后，游客和FB指向的是同一个账号
                    bindEmail.setVisibility(View.GONE);
                    resetPwd.setVisibility(View.GONE);
                    bindFb.setVisibility(View.VISIBLE);
                }
                //账号登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_ACCOUNT)){
                    bindFb.setVisibility(View.GONE);
                    if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()){
                        bindEmail.setVisibility(View.VISIBLE);
                        resetPwd.setVisibility(View.GONE);
                    } else{
                        bindEmail.setVisibility(View.GONE);
                        emailTip.setVisibility(View.VISIBLE);
                        resetPwd.setVisibility(View.VISIBLE);
                        String textContent = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail")+SDKGameUtils.hideEmail(SDKManager.getInstance().getUser().getBindEmail());
                        emailTip.setText(textContent);
                    }
                }
                //FB登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_FACEBOOK)){
                    bindFb.setVisibility(View.GONE);
                    bindEmail.setVisibility(View.GONE);
                    resetPwd.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }
                //Google用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_GOOGLE)){
                    bindFb.setVisibility(View.GONE);
                    bindEmail.setVisibility(View.GONE);
                    resetPwd.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }
            }

            //显示语言多样化
            bindEmail.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            bindFb.setText(SDKLangConfig.getInstance().findMessage("str_bind_facebook"));
            resetPwd.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));


//            handler = new Handler(Looper.getMainLooper()){
//                @Override
//                public void handleMessage(@NonNull Message msg) {
//                    super.handleMessage(msg);
//                    if (msg.what == 0) {
//                        bindFb.setVisibility(View.GONE);
//                        emptyTxt.setVisibility(View.VISIBLE);
//                    }
//                }
//            };

            //绑定邮箱
            bindEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

                    EmailBindDialog.Builder builder = new EmailBindDialog.Builder(context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            ((Activity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    bindEmail.setVisibility(View.GONE);
                                    emailTip.setVisibility(View.VISIBLE);
                                    resetPwd.setVisibility(View.VISIBLE);
                                    String textContent = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail")+SDKGameUtils.hideEmail(SDKManager.getInstance().getUser().getBindEmail());
                                    emailTip.setText(textContent);
                                }
                            });
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                    builder.create().show();
                }
            });
            //绑定FB
            bindFb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

                    SDKManager.getInstance().sdkGuestBindFB((Activity) context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //游客绑定FB成功
                            ((Activity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    bindFb.setVisibility(View.GONE);
                                    emptyTxt.setVisibility(View.VISIBLE);
                                }
                            });
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

                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

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
