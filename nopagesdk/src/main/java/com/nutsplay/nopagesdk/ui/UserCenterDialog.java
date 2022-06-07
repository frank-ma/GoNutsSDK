package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 用户中心Dialog
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

        @SuppressLint("HandlerLeak")
        public UserCenterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UserCenterDialog dialog = new UserCenterDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_usercenter", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_center", "layout"), null);
            }
            final View bindEmail = layout.findViewById(SDKResUtils.getResId(context, "ll_bind_email", "id"));
            final View bindFb = layout.findViewById(SDKResUtils.getResId(context, "ll_bind_fb", "id"));
            final View resetPwd = layout.findViewById(SDKResUtils.getResId(context, "ll_reset_pwd", "id"));

            final TextView bindEmailTv = layout.findViewById(SDKResUtils.getResId(context,"tv_bind_email","id"));
            final TextView bindFbTv = layout.findViewById(SDKResUtils.getResId(context,"tv_bind_fb","id"));
            final TextView resetPwdTv = layout.findViewById(SDKResUtils.getResId(context,"tv_reset_pwd","id"));

            final TextView userNameTv = layout.findViewById(SDKResUtils.getResId(context, "tv_name", "id"));
            ImageView imgClose = layout.findViewById(SDKResUtils.getResId(context, "img_close", "id"));
            final TextView emptyTxt = layout.findViewById(SDKResUtils.getResId(context, "nothing", "id"));
            final ImageView userIcon = layout.findViewById(SDKResUtils.getResId(context, "user_icon", "id"));
            showUserName(userNameTv,userIcon);
            //如果是Facebook用户就不显示绑定FB按钮了,账号登录也不能绑定

            if (SDKManager.getInstance().getUser() != null){
                //游客登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_GUEST)){
                    //游客绑定FB之后，游客和FB指向的是同一个账号

                }
                //账号登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_ACCOUNT)){

                    if (SDKManager.getInstance().getUser().getBindEmail().isEmpty()){
                        bindEmail.setVisibility(View.VISIBLE);
                    } else{
                        bindEmail.setVisibility(View.GONE);
                    }
                }
                //FB登录用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_FACEBOOK)){
                    bindFb.setVisibility(View.GONE);
                }
                //Google用户
                if (SDKManager.getInstance().getUser().getSdkmemberType().equals(SDKConstant.TYPE_GOOGLE)){

                }
            }

            //设置自定义字体
            SDKGameUtils.setTypeFace(context,userNameTv);
            SDKGameUtils.setTypeFaceBold(context,bindEmailTv);
            SDKGameUtils.setTypeFaceBold(context,bindFbTv);
            SDKGameUtils.setTypeFaceBold(context,resetPwdTv);
            //显示语言多样化
            bindEmailTv.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            bindFbTv.setText(SDKLangConfig.getInstance().findMessage("str_bind_facebook"));
            resetPwdTv.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));

            //绑定邮箱
            bindEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    BindEmailDialog.Builder builder = new BindEmailDialog.Builder(context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            Log.d("UserCenterDialog","MainThreadID_EmailBindDialog:"+Looper.getMainLooper().getThread().getId());
                            Log.d("UserCenterDialog","ThreadID_EmailBindDialog:"+Thread.currentThread().getId());
                            bindEmail.setVisibility(View.GONE);
                            resetPwd.setVisibility(View.VISIBLE);
                            String textContent = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail")+SDKGameUtils.hideEmail(SDKManager.getInstance().getUser().getBindEmail());
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
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    SDKManager.getInstance().sdkGuestBindFB((Activity) context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //游客绑定FB成功
                            Log.d("UserCenterDialog","MainThreadID_bindfb:"+Looper.getMainLooper().getThread().getId());
                            Log.d("UserCenterDialog","ThreadID_bindfb:"+Thread.currentThread().getId());
                            bindFb.setVisibility(View.GONE);
                            emptyTxt.setVisibility(View.VISIBLE);
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
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
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

        /**
         * 展示用户名
         * @param userNameTv
         */
        public void showUserName(TextView userNameTv,ImageView userIcon) {
            String msg = "";
            User user = SDKManager.getInstance().getUser();
            if (user == null ) return;
            String sdkmemberType = user.getSdkmemberType();
            if (StringUtils.isEmpty(sdkmemberType)) return;
            if (!user.getUserName().isEmpty() && sdkmemberType.equals(SDKConstant.TYPE_GUEST)){
                sdkmemberType = SDKConstant.TYPE_ACCOUNT;
            }
            switch (sdkmemberType){
                case SDKConstant.TYPE_ACCOUNT:
                    msg += "UserName："+user.getUserName();
                    userIcon.setImageResource(SDKResUtils.getResId(context,"usercenter_nuts_account","drawable"));
                    break;
                case SDKConstant.TYPE_FACEBOOK:
                    msg += "Facebook："+user.getFacebookName();
                    userIcon.setImageResource(SDKResUtils.getResId(context,"usercenter_fbuser","drawable"));
                    break;
                default:
                    msg += "Hi,Guest!";
                    userIcon.setImageResource(SDKResUtils.getResId(context,"usercenter_guest","drawable"));
                    break;
            }

            if (!user.getBindEmail().isEmpty()){
                String bindEmailStr = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail")+SDKGameUtils.hideEmail(user.getBindEmail());
                msg += "\n"+ bindEmailStr;
            }

            userNameTv.setText(msg);
        }
    }


}
