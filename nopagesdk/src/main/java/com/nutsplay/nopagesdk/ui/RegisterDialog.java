package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class RegisterDialog extends Dialog {

    public RegisterDialog(@NonNull Context context) {
        super(context);
    }

    public RegisterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RegisterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack = loginCallBack;
        }

        public RegisterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RegisterDialog dialog = new RegisterDialog(context);
            if (inflater == null) return dialog;
            View layout = inflater.inflate(SDKResUtils.getResId(context, "signup_dialog", "layout"), null);

            TextView signUp = layout.findViewById(SDKResUtils.getResId(context, "tv_sign_up", "id"));
            final EditText userName = layout.findViewById(SDKResUtils.getResId(context, "et_name", "id"));
            final EditText pwd = layout.findViewById(SDKResUtils.getResId(context, "et_pwd", "id"));
            final EditText repeatPwd = layout.findViewById(SDKResUtils.getResId(context, "et_repeat_pwd", "id"));
            final ImageView backIv = layout.findViewById(SDKResUtils.getResId(context, "iv_back", "id"));
            //注册账号
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = userName.getText().toString();
                    String psw = pwd.getText().toString();
                    String rePsw = repeatPwd.getText().toString();

                    if (account.isEmpty()||psw.isEmpty()|| rePsw.isEmpty()){
                        SDKToast.getInstance().ToastShow("The account and password cannot be empty.",2);
                        return;
                    }
                    if (!psw.equals(rePsw)) {
                        SDKToast.getInstance().ToastShow("You should enter the same password.", 3);
                        return;
                    }
                    SDKManager.getInstance().sdkRegisterNoUI((Activity) context, account, psw, loginCallBack);

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
