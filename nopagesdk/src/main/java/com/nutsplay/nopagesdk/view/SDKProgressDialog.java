package com.nutsplay.nopagesdk.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKResUtils;


/**
 * @author
 * @since 2017/10/18
 */

public class SDKProgressDialog extends Dialog {

    private static SDKProgressDialog progressDialog;
    private static String mMessage;
    private static TextView tv;

    private SDKProgressDialog(Context context) {
        super(context);
    }

    private SDKProgressDialog(Context context,int themeResId){
        super(context,themeResId);
    }

    public static SDKProgressDialog createProgrssDialog(Context context, String message) {
        mMessage = message;
        return createProgrssDialog(context);
    }

    public static SDKProgressDialog createProgrssDialog(Context context) {
//        progressDialog = new SDKProgressDialog(context);
        progressDialog = new SDKProgressDialog(context, SDKResUtils.getResId(context,"DialogStyle","style"));

        if (SDKManager.getInstance().isCommonVersion()){
            progressDialog.setContentView(SDKResUtils.getResId(context,"sdk_layout_loading_normal","layout"));
        }else {
            progressDialog.setContentView(SDKResUtils.getResId(context,"sdk_layout_loading","layout"));
        }

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