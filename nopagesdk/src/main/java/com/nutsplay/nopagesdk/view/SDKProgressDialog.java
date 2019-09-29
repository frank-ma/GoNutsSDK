package com.nutsplay.nopagesdk.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

import com.nutsplay.nopagesdk.utils.SDKResUtils;


/**
 * @author zhangpanpan
 * @since 2017/10/18
 */

public class SDKProgressDialog extends Dialog {

    private static SDKProgressDialog progressDialog;
    private static String mMessage;
    private static TextView tv;

    private SDKProgressDialog(Context context) {
        super(context);
    }

    public static SDKProgressDialog createProgrssDialog(Context context, String message) {
        mMessage = message;
        return createProgrssDialog(context);
    }

    public static SDKProgressDialog createProgrssDialog(Context context) {
        progressDialog = new SDKProgressDialog(context);
//        progressDialog = new SDKProgressDialog(context, SDKResUtils.getResId(context,"DialogStyle","style"));
        progressDialog.setContentView(SDKResUtils.getResId(context,"loading_dialog","layout"));
        if (progressDialog.getWindow()!=null) progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        tv =  progressDialog.findViewById(SDKResUtils.getResId(context,"tv_dialog_info","id"));
        if (mMessage != null) {
            tv.setText(mMessage);
        }
        progressDialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });

        return progressDialog;
    }


}