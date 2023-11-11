package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
        window.setWindowAnimations(SDKResUtils.getResId(context, "dialog_anim_style", "style"));
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
        private boolean canClose;

        public Builder(Context context, AgreementCallBack callBack,boolean canClose) {
            this.context = context;
            this.callBack = callBack;
            this.canClose = canClose;
        }

        public UserAgreementDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UserAgreementDialog dialog = new UserAgreementDialog(context);
            if (inflater == null) return dialog;
            View layout;
            switch (SDKManager.getInstance().getUIVersion()){
                case 0://横版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_useragreement", "layout"), null);
                    break;
                case 1://竖版UI
                    layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_useragreement_portrait", "layout"), null);
                    break;
                default://旧版
                    layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_agreement_normal", "layout"), null);
                    break;
            }

            ImageView closeIv = layout.findViewById(SDKResUtils.getResId(context, "ic_close", "id"));
            TextView protocolContent = layout.findViewById(SDKResUtils.getResId(context, "user_agreement", "id"));
            TextView accept = layout.findViewById(SDKResUtils.getResId(context, "tv_accept", "id"));
            TextView title = layout.findViewById(SDKResUtils.getResId(context, "title", "id"));
            TextView userProtocolTv = layout.findViewById(SDKResUtils.getResId(context, "user_protocol", "id"));
            //显隐关闭按钮
            closeIv.setVisibility(canClose? View.VISIBLE:View.GONE);

            //设置自定义字体
            SDKGameUtils.setTypeFaceBold(context,title);
            SDKGameUtils.setTypeFace(context,protocolContent);
            SDKGameUtils.setTypeFaceBold(context,accept);

            title.setText(SDKLangConfig.getInstance().findMessage("userAgreement"));
            accept.setText(SDKLangConfig.getInstance().findMessage("accept"));
            //设置TextView的内容可以滚动
            protocolContent.setMovementMethod(ScrollingMovementMethod.getInstance());

            //设置超链接
            String webLinkText = "<a href='http://nutspower.com/PrivacyNotice.html'>Privacy Policy</a>";
            userProtocolTv.setText(Html.fromHtml(webLinkText));
            userProtocolTv.setMovementMethod(LinkMovementMethod.getInstance());
            userProtocolTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SDKManager.getInstance().sdkUploadLog("30","click protocol link");
                }
            });
            //协议内容
            try {
                SDKKernel.getInstance().setActivity((Activity) context);
                String userProtocol = SDKKernel.getInstance().getInitData().getData().getPolicy_txt();
                if (userProtocol == null || userProtocol.isEmpty()) userProtocol = "No Policy";
//                protocolContent.setText(userProtocol);
            } catch (Exception e) {
                callBack.onSuccess();
                e.printStackTrace();
            }
            //接受按钮
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callBack.onSuccess();
                    if (context == null) return;
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
