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
 * Desc:
 */
public class EmailBindDialog extends BaseDialog {

    public EmailBindDialog(@NonNull Context context) {
        super(context);
    }

    public EmailBindDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected EmailBindDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public EmailBindDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final EmailBindDialog dialog = new EmailBindDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_email_normal", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_email", "layout"), null);
            }

            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_up", "id"));
            TextView title = layout.findViewById(SDKResUtils.getResId(context, "title", "id"));
            final EditText email = layout.findViewById(SDKResUtils.getResId(context, "et_email", "id"));
            final EditText verificationCode = layout.findViewById(SDKResUtils.getResId(context, "et_verification_code", "id"));
            final TextView btnSend = layout.findViewById(SDKResUtils.getResId(context, "btn_send_verification_code", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));

            //多语言适配
            title.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            email.setHint(SDKLangConfig.getInstance().findMessage("please_input_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            bind.setText(SDKLangConfig.getInstance().findMessage("bind"));


            //发送邮箱验证码
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress = email.getText().toString();
                    if (emailAddress.isEmpty()){
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("email_null"), 3);
                        return;
                    }
                    if (!SDKGameUtils.matchEmail(emailAddress)) return;
                    SDKManager.getInstance().sdkUserBindEmailSendCode((Activity) context, emailAddress, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //发送验证码成功，开始计时60s
                            countDown(context,btnSend);
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });

                }
            });

            //绑定邮箱
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress = email.getText().toString();
                    if (emailAddress.isEmpty()){
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("email_null"), 3);
                        return;
                    }
                    if (!SDKGameUtils.matchEmail(emailAddress)) return;

                    String code = verificationCode.getText().toString();
                    if (code.isEmpty()) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("39"),3);
                        return;
                    }

                    SDKManager.getInstance().sdkUserBindEmail((Activity) context, emailAddress, code, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(String msg) {

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
