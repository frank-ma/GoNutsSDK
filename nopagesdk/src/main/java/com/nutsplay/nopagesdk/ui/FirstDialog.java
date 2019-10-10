package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class FirstDialog extends Dialog {

    public FirstDialog(@NonNull Context context) {
        super(context);
    }

    public FirstDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FirstDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack = loginCallBack;
        }

        public FirstDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final FirstDialog dialog = new FirstDialog(context,SDKResUtils.getResId(context,"NutsDialogStyle","style"));
            final FirstDialog dialog = new FirstDialog(context);
            if (inflater == null) return dialog;
            View layout = inflater.inflate(SDKResUtils.getResId(context, "first_dialog", "layout"), null);
            TextView visitorLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_visitor_sign_in", "id"));
            TextView createAccount = layout.findViewById(SDKResUtils.getResId(context, "tv_create_account", "id"));
            visitorLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().visitorLogin((Activity) context, loginCallBack);
                    dialog.dismiss();
                }
            });
            createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LoginDialog.Builder builder = new LoginDialog.Builder(context,loginCallBack);
                    builder.create().show();
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
