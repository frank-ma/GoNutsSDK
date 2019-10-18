package com.nutsplay.nopagesdk.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.utils.QrcodeUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.ScreenShotUtils;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SaveUserInfoDialog extends Dialog {

    public SaveUserInfoDialog(@NonNull Context context) {
        super(context);
    }

    public SaveUserInfoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SaveUserInfoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String account,pwd;

        public Builder(Context context, String account,String password) {
            this.context = context;
            this.account = account;
            this.pwd = password;
        }

        public SaveUserInfoDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final FirstDialog dialog = new FirstDialog(context,SDKResUtils.getResId(context,"NutsDialogStyle","style"));
            final SaveUserInfoDialog dialog = new SaveUserInfoDialog(context);
            if (inflater == null) return dialog;
            View layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_save_account", "layout"), null);
            TextView tvAccount = layout.findViewById(SDKResUtils.getResId(context, "tv_account", "id"));
            TextView tvPwd = layout.findViewById(SDKResUtils.getResId(context, "tv_pwd", "id"));
            TextView tvSave = layout.findViewById(SDKResUtils.getResId(context, "tv_save", "id"));
            TextView tvNo = layout.findViewById(SDKResUtils.getResId(context, "tv_close", "id"));
            ImageView ivQrcode = layout.findViewById(SDKResUtils.getResId(context, "iv_qrcode", "id"));

            tvAccount.setText("UserName:"+account);
            tvPwd.setText("Password:"+pwd);
            generationQrcode(ivQrcode);

//            loginTips.setText(SDKLangConfig.getInstance().findMessage("str_login_tips"));

            tvNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            tvSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    saveImg();
                    dialog.dismiss();
                }
            });

            dialog.setContentView(layout);
            if (dialog.getWindow()!=null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

        /**
         * 截图保存图片到本地相册，需要获取权限
         *
         */
        private void saveImg() {
            ScreenShotUtils.saveBitmap((Activity) context);
        }

        /**
         * 生成二维码
         *
         * @param ivQrcode
         */
        private void generationQrcode(ImageView ivQrcode) {

            String content= account+"|"+pwd;
            Bitmap qrImage = QrcodeUtils.createQRImage(content, 300, 300);
            ivQrcode.setImageBitmap(qrImage);
        }
    }

}
