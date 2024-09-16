package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 绑定邮箱窗口
 */
public class BindEmailDialog extends Dialog {

    public BindEmailDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public BindEmailDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BindEmailDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private Context context;
        private TextView btnSend;
        private int recLen = 60;
        ResultCallBack resultCallBack;

        public Builder(Context context,ResultCallBack resultCallBack) {
            this.context = context;
            this.resultCallBack = resultCallBack;
        }

        public BindEmailDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BindEmailDialog dialog = new BindEmailDialog(context);
            if (inflater == null) return dialog;
            View layout;
            switch (SDKManager.getInstance().getUIVersion()){
                case 0://横版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_bind_email", "layout"), null);
                    break;
                case 1://竖版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_bind_email_portrait", "layout"), null);
                    break;
                default://旧版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_email_normal", "layout"), null);
                    break;
            }
//            if (SDKManager.getInstance().getUIVersion()){
//                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_bind_email", "layout"), null);
//            }else {
//                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_email", "layout"), null);
//            }

            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_bind", "id"));
            TextView title = layout.findViewById(SDKResUtils.getResId(context, "title", "id"));
            final EditText email = layout.findViewById(SDKResUtils.getResId(context, "et_email", "id"));
            final EditText verificationCode = layout.findViewById(SDKResUtils.getResId(context, "et_verification_code", "id"));
            btnSend = layout.findViewById(SDKResUtils.getResId(context, "btn_send_verification_code", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            ImageView imgClose = layout.findViewById(SDKResUtils.getResId(context, "iv_close", "id"));

            //设置自定义字体
            SDKGameUtils.setTypeFace(context,title);
            SDKGameUtils.setTypeFaceBold(context,btnSend);
            SDKGameUtils.setTypeFaceBold(context,bind);


            //多语言适配
            title.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            email.setHint(SDKLangConfig.getInstance().findMessage("nuts_email"));
            verificationCode.setHint(SDKLangConfig.getInstance().findMessage("44"));
            btnSend.setText(SDKLangConfig.getInstance().findMessage("26"));
            bind.setText(SDKLangConfig.getInstance().findMessage("bind"));

            //发送邮箱验证码
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    String emailAddress = email.getText().toString();
                    if (!SDKGameUtils.matchEmail(context,email,emailAddress)) return;
                    SDKManager.getInstance().sdkUserBindEmailSendCode((Activity) context, emailAddress, new ResultCallBack() {
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

            //绑定邮箱
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress = email.getText().toString();
                    if (!SDKGameUtils.matchEmail(context,email,emailAddress)) return;

                    String code = verificationCode.getText().toString();
                    if (!SDKGameUtils.matchCode(context,verificationCode,code)) return;

                    SDKManager.getInstance().sdkUserBindEmail((Activity) context, emailAddress, code, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            dialog.dismiss();
                            resultCallBack.onSuccess();
                        }

                        @Override
                        public void onFailure(String msg) {
                            resultCallBack.onFailure(msg);
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

            //关闭
            imgClose.setOnClickListener(new View.OnClickListener() {
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
