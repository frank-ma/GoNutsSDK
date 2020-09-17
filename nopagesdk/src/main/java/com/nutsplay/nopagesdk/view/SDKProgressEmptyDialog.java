package com.nutsplay.nopagesdk.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

import com.nutsplay.nopagesdk.utils.SDKResUtils;


/**
 * @author
 * @since 2017/10/18
 */

public class SDKProgressEmptyDialog extends Dialog {

    private static SDKProgressEmptyDialog progressDialog;
    private static String mMessage;
    private static TextView tv;

    private SDKProgressEmptyDialog(Context context) {
        super(context);
    }

    private SDKProgressEmptyDialog(Context context, int themeResId){
        super(context,themeResId);
    }

    public static SDKProgressEmptyDialog createProgrssDialog(Context context, String message) {
        mMessage = message;
        return createProgrssDialog(context);
    }

    public static SDKProgressEmptyDialog createProgrssDialog(Context context) {
        progressDialog = new SDKProgressEmptyDialog(context, SDKResUtils.getResId(context,"DialogStyle","style"));
        progressDialog.setContentView(SDKResUtils.getResId(context,"sdk_empty_loading","layout"));

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

        if (progressDialog.getWindow()!=null) {
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        return progressDialog;
    }


}