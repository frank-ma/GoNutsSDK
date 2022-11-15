package com.nutsplay.nopagesdk.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.LoginCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.TrackingManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class BindTipDialog extends Dialog {

    public BindTipDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public BindTipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BindTipDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private LoginCallBack loginCallBack;
        private long lastTime=0;

        public Builder(Context context, LoginCallBack loginCallBack) {
            this.context = context;
            this.loginCallBack = loginCallBack;
        }

        public BindTipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BindTipDialog dialog = new BindTipDialog(context);
            if (inflater == null) return dialog;

            View layout;
            switch (SDKManager.getInstance().getUIVersion()){
                case 0://横版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_tips", "layout"), null);
                    break;
                case 1://竖版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_tips_portrait", "layout"), null);
                    break;
                default://旧版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_tips", "layout"), null);
                    break;
            }
//            if (SDKManager.getInstance().getUIVersion()){
//                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_tips", "layout"), null);
//            }else {
//                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_bind_tips", "layout"), null);
//            }

            TextView tvTips = layout.findViewById(SDKResUtils.getResId(context, "tv_tips", "id"));
            TextView tvContent = layout.findViewById(SDKResUtils.getResId(context, "tv_content", "id"));
            TextView bind = layout.findViewById(SDKResUtils.getResId(context, "tv_bind", "id"));
            TextView enterGame = layout.findViewById(SDKResUtils.getResId(context, "tv_enter_game", "id"));

            //设置自定义字体
            SDKGameUtils.setTypeFace(context,tvTips);
            SDKGameUtils.setTypeFace(context,tvContent);
            SDKGameUtils.setTypeFaceBold(context,bind);
            SDKGameUtils.setTypeFaceBold(context,enterGame);


            tvTips.setText(SDKLangConfig.getInstance().findMessage("tourist_signin_tips"));
            tvContent.setText(SDKLangConfig.getInstance().findMessage("tourist_signin_alert"));
            bind.setText(SDKLangConfig.getInstance().findMessage("viewstring_Bind_Account"));
            enterGame.setText(SDKLangConfig.getInstance().findMessage("nutsplay_viewstring_confirm"));

            //绑定账号
            bind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime < 2000) {
                        return;
                    }else {
                        lastTime = currentTime;
                    }

                    BindAccountDialog.Builder builder = new BindAccountDialog.Builder(context, loginCallBack);
                    builder.create().show();
                    dialog.dismiss();
                }
            });

            //进入游戏按钮
            enterGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loginCallBack != null) {
                        loginCallBack.onSuccess(SDKManager.getInstance().getUser());
                        //登录追踪
                        TrackingManager.loginTracking(SDKManager.getInstance().getUser().getUserId());
                        dialog.dismiss();
                    }
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setOnCancelListener(new OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    loginCallBack.onSuccess(SDKManager.getInstance().getUser());
//                }
//            });
            return dialog;
        }
    }

}
