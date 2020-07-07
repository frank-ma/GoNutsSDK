package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ResetPwdDialog extends BaseDialog {

    public ResetPwdDialog(@NonNull Context context) {
        super(context);
    }

    public ResetPwdDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ResetPwdDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private Context context;

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

            //多语言适配
            resetTitle.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));
            accountEt.setHint(SDKLangConfig.getInstance().findMessage("account_which_bind_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            newPwd.setHint(SDKLangConfig.getInstance().findMessage("new_password"));
            newPwdRepeat.setHint(SDKLangConfig.getInstance().findMessage("repeat_password"));
            reset.setText(SDKLangConfig.getInstance().findMessage("reset"));


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
                    SDKManager.getInstance().sdkResetPwdSendCode((Activity) context, account, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //开始倒计时
                            countDown(context,btnSend);

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
    }
}
