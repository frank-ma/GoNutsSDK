package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
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
public class EmailBindDialog extends Dialog {

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
        private static Handler handler;
        private long lastTime = 0;

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
            email.setHint(SDKLangConfig.getInstance().findMessage("nuts_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            bind.setText(SDKLangConfig.getInstance().findMessage("bind"));


            handler = new Handler(){
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
                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 600000) {
                        SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("nuts_Emailhasbeenbound"), 3);
                        return;
                    }

                    SDKManager.getInstance().sdkUserBindEmailSendCode((Activity) context, emailAddress, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            lastTime = System.currentTimeMillis();
                            //发送验证码成功，开始计时20s
//                            countDown(context,btnSend);
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


        /**
         * 倒计时显示
         */
        private void countDown(final Context context, final TextView button) {

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
