package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.callback.AgreementCallBack;
import com.nutsplay.nopagesdk.kernel.SDKKernel;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 用户协议窗口
 */
public class UserAgreementDialog extends Dialog {

    public UserAgreementDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public UserAgreementDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UserAgreementDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private AgreementCallBack callBack;

        public Builder(Context context, AgreementCallBack callBack) {
            this.context = context;
            this.callBack = callBack;
        }

        public UserAgreementDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UserAgreementDialog dialog = new UserAgreementDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()) {
                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_useragreement", "layout"), null);
            } else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_agreement", "layout"), null);
            }

            ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "ic_close", "id"));
            TextView protocolContent = layout.findViewById(SDKResUtils.getResId(context, "user_agreement", "id"));
            TextView accept = layout.findViewById(SDKResUtils.getResId(context, "tv_accept", "id"));
            TextView title = layout.findViewById(SDKResUtils.getResId(context, "title", "id"));

            //设置自定义字体
            SDKGameUtils.setTypeFaceBold(context,title);
            SDKGameUtils.setTypeFace(context,protocolContent);
            SDKGameUtils.setTypeFaceBold(context,accept);
            accept.setText(SDKLangConfig.getInstance().findMessage("accept"));
            //设置TextView的内容可以滚动
            protocolContent.setMovementMethod(ScrollingMovementMethod.getInstance());

            //协议内容
            try {
                SDKKernel.getInstance().setActivity((Activity) context);
                String userProtocol = SDKKernel.getInstance().getInitData().getData().getPolicy_txt();
                if (userProtocol == null || userProtocol.isEmpty()) userProtocol = "No Policy";
                protocolContent.setText(userProtocol);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //接受按钮
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (context == null) return;
                    callBack.onSuccess();
                    SPManager.getInstance(context).putBoolean(SPKey.key_first_open,false);
                    dialog.dismiss();
                }
            });

            //关闭按钮
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onCancel();
                    dialog.dismiss();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

    }

}
