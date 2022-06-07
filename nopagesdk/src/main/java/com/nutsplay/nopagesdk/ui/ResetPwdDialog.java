package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 通过绑定的邮箱发送验证码进行重置密码
 */
public class ResetPwdDialog extends Dialog {

    private int recLen = 60;

    public ResetPwdDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public ResetPwdDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ResetPwdDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private Context context;
        private TextView btnSend;
        private int recLen = 60;
        public Builder(Context context) {
            this.context = context;
        }

        public ResetPwdDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ResetPwdDialog dialog = new ResetPwdDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_reset_pwd", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_reset_pwd", "layout"), null);
            }

            TextView resetTitle = layout.findViewById(SDKResUtils.getResId(context, "reset_title", "id"));
            TextView reset = layout.findViewById(SDKResUtils.getResId(context, "tv_reset", "id"));
            final EditText accountEt = layout.findViewById(SDKResUtils.getResId(context, "et_account", "id"));
            btnSend = layout.findViewById(SDKResUtils.getResId(context, "btn_send_verification_code", "id"));
            final EditText verificationCode = layout.findViewById(SDKResUtils.getResId(context, "et_verification_code", "id"));
            EditText newPwd = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd", "id"));
            EditText newPwdRepeat = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd_repeat", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            final ToggleButton pwdToggle = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle", "id"));


            //设置自定义字体
            SDKGameUtils.setTypeFace(context,resetTitle);
            SDKGameUtils.setTypeFaceBold(context,btnSend);
            SDKGameUtils.setTypeFaceBold(context,reset);


            //多语言适配
            resetTitle.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));
            accountEt.setHint(SDKLangConfig.getInstance().findMessage("account_which_bind_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            newPwd.setHint(SDKLangConfig.getInstance().findMessage("new_password"));
            newPwdRepeat.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            reset.setText(SDKLangConfig.getInstance().findMessage("reset"));

            //显隐密码
            pwdToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        newPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        newPwdRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"icon_grey_visibility_off","drawable"));
                    }else {
                        newPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        newPwdRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle.setBackgroundResource(SDKResUtils.getResId(context,"icon_grey_visibility","drawable"));
                    }
                    newPwd.setSelection(newPwd.getText().toString().length());
                    newPwdRepeat.setSelection(newPwdRepeat.getText().toString().length());
                }
            });

            //发送邮箱验证码
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    String account = accountEt.getText().toString();
                    if (!SDKGameUtils.matchAccount(context,accountEt,account)) return;
                    SDKManager.getInstance().sdkResetPwdSendCode((Activity) context, account, new ResultCallBack() {
                        @Override
                        public void onSuccess() {

                            //开始倒计时
                            btnSend.setBackgroundResource(SDKResUtils.getResId(context,"shape_bg_time","drawable"));
                            handler.postDelayed(runnable, 1000);
                            btnSend.setClickable(false);
                        }

                        @Override
                        public void onFailure(String msg) {
                            btnSend.setClickable(true);
                        }
                    });

                }
            });


            //重置密码
            reset.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    String account = accountEt.getText().toString();
                    String code = verificationCode.getText().toString();
                    String newSecond = newPwd.getText().toString();

                    if (!SDKGameUtils.matchAccount(context,accountEt,account) ||
                            !SDKGameUtils.matchCode(context,verificationCode,code)) return;

                    //判断密码
                    if (!SDKGameUtils.match2Pw(context,newPwd,newPwdRepeat)){
                        pwdToggle.setChecked(true);
                        return;
                    }else {
                        pwdToggle.setChecked(false);
                    }

                    SDKManager.getInstance().sdkResetPwdByCode((Activity) context, account, code, newSecond,new ResultCallBack(){

                        @Override
                        public void onFailure(String msg) {

                        }

                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }
                    });

                }
            });

            //返回按钮
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

        /**
         * 错误提示弹窗
         */
        private PopupWindow popupWindow;
        private  void initPopWindow(Context context, View anchor, String str){
            View view = LayoutInflater.from(context).inflate(SDKResUtils.getResId(context,"nuts2_item_pop","layout"),null,false);
            TextView content = view.findViewById(SDKResUtils.getResId(context,"tv_pop","id"));
            content.setText(str);

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//为popwindow设置一个背景才有效
            popupWindow.showAsDropDown(anchor,0,0);
        }

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                recLen--;
                if (recLen == 0) {
                    btnSend.setClickable(true);
                    btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
                    btnSend.setBackgroundResource(SDKResUtils.getResId(context,"shape_bg_blue","drawable"));
                    recLen = 60;
                    return;
                }
                btnSend.setText(recLen + "s");
                handler.postDelayed(this, 1000);

            }
        };
    }
}
