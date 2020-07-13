package com.nutsplay.nopagesdk.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by frankma on 2020-04-22 17:15
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BaseDialog extends Dialog {

    private static Handler handler;
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
