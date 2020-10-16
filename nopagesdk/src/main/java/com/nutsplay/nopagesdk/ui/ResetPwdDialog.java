package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 通过绑定的邮箱发送验证码进行重置密码
 */
public class ResetPwdDialog extends Dialog {

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
        private Handler handler;
        private long lastTime = 0;
        public Builder(Context context) {
            this.context = context;
        }

        public ResetPwdDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ResetPwdDialog dialog = new ResetPwdDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_reset_pwd_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_reset_pwd", "layout"), null);
            }

            TextView resetTitle = layout.findViewById(SDKResUtils.getResId(context, "reset_title", "id"));
            TextView reset = layout.findViewById(SDKResUtils.getResId(context, "tv_reset", "id"));
            final EditText accountEt = layout.findViewById(SDKResUtils.getResId(context, "et_account", "id"));
            final TextView btnSend = layout.findViewById(SDKResUtils.getResId(context, "btn_send_verification_code", "id"));
            final EditText verificationCode = layout.findViewById(SDKResUtils.getResId(context, "et_verification_code", "id"));
            final EditText newPwd = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd", "id"));
            final EditText newPwdRepeat = layout.findViewById(SDKResUtils.getResId(context, "et_new_pwd_repeat", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            final ToggleButton pwdToggle1 = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle1", "id"));
            final ToggleButton pwdToggle2 = layout.findViewById(SDKResUtils.getResId(context, "pwd_toggle2", "id"));

            //多语言适配
            resetTitle.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));
            accountEt.setHint(SDKLangConfig.getInstance().findMessage("account_which_bind_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            newPwd.setHint(SDKLangConfig.getInstance().findMessage("new_password"));
            newPwdRepeat.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            reset.setText(SDKLangConfig.getInstance().findMessage("reset"));

            handler = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case 0:
                            String time = (String) msg.obj;
                            if (time == null) return;
                            btnSend.setText(time);
                            break;
                        case 1:
                            btnSend.setEnabled(true);
                            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
                            break;
                        case 2:
                            btnSend.setEnabled(false);
                            break;
                        default:
                            break;
                    }
                }
            };
            //显隐密码
            pwdToggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        newPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle1.setBackgroundResource(SDKResUtils.getResId(context,"eyes_close","drawable"));
                    }else {
                        newPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle1.setBackgroundResource(SDKResUtils.getResId(context,"eyes_open","drawable"));
                    }
                    newPwd.setSelection(newPwd.getText().toString().length());
                }
            });

            //显隐密码
            pwdToggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){
                        newPwdRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pwdToggle2.setBackgroundResource(SDKResUtils.getResId(context,"eyes_close","drawable"));
                    }else {
                        newPwdRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pwdToggle2.setBackgroundResource(SDKResUtils.getResId(context,"eyes_open","drawable"));
                    }
                    newPwdRepeat.setSelection(newPwdRepeat.getText().toString().length());
                }
            });

            //发送邮箱验证码
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = accountEt.getText().toString();
                    if (account.isEmpty()){
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_username_null"), 3);
                        return;
                    }
                    if (!SDKGameUtils.matchAccount(account)) return;
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 600000) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_Emailhasbeenbound"), 3);
                        return;
                    }

                    SDKManager.getInstance().sdkResetPwdSendCode((Activity) context, account, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            lastTime = System.currentTimeMillis();
                            //开始倒计时
//                            countDown(btnSend);

                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });

                }
            });

            //重置密码
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

                    String account = accountEt.getText().toString();
                    String code = verificationCode.getText().toString();
                    String newSecond = newPwd.getText().toString();
                    String newSecondRepeat = newPwdRepeat.getText().toString();

                    if (account.isEmpty()){
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("email_null"), 3);
                        return;
                    }

                    if (code.isEmpty()) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("39"),3);
                        return;
                    }

                    if (newSecond.isEmpty() || newSecondRepeat.isEmpty()) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("33"),3);
                        return;
                    }

                    if (!newSecond.equals(newSecondRepeat)){
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("pwd_different"),3);
                        return;
                    }

                    if (!SDKGameUtils.matchAccount(account) || !SDKGameUtils.matchPw(newSecond)) return;

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
         * 倒计时显示
         */
        private void countDown(final TextView button) {
            handler.sendEmptyMessage(2);
            CountDownTimer timer = new CountDownTimer(20000,1000) {
                @Override
                public void onTick(final long millisUntilFinished) {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = millisUntilFinished / 1000 + "s";
                    handler.sendMessage(message);

                }

                @Override
                public void onFinish() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }.start();
        }

    }
}
