package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by frankma on 2020-04-22 17:15
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 倒计时显示
     */
    static void countDown(final Context context, final TextView button) {

        button.setEnabled(false);
//        button.setTextColor(SDKResUtils.getResId(context, "black", "color"));
//        button.setBackgroundResource(SDKResUtils.getResId(context, "gray", "color"));

        CountDownTimer timer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(final long millisUntilFinished) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setText(millisUntilFinished / 1000 + "s");
                    }
                });

            }

            @Override
            public void onFinish() {
                new Runnable(){
                    @Override
                    public void run() {
                        button.setEnabled(true);
                        button.setText("Send Code");
//                        button.setTextColor(context.getResources().getColor(android.R.color.white));
//                        button.setBackgroundResource(SDKResUtils.getResId(context, "colorAccent", "color"));
                    }
                }.run();

            }
        }.start();
    }
}
