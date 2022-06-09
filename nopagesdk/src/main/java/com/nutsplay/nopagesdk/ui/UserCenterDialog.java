package com.nutsplay.nopagesdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.beans.UserBindInfo;
import com.nutsplay.nopagesdk.callback.NetCallBack;
import com.nutsplay.nopagesdk.callback.ResultCallBack;
import com.nutsplay.nopagesdk.kernel.SDKConstant;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.manager.ApiManager;
import com.nutsplay.nopagesdk.manager.CustomerServiceManager;
import com.nutsplay.nopagesdk.network.GsonUtils;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;
import com.nutsplay.nopagesdk.utils.encryption.AESUtils;
import com.nutsplay.nopagesdk.utils.encryption.RSAUtils;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;
import com.nutspower.commonlibrary.utils.StringUtils;

import org.json.JSONObject;

/**
 * Created by frankma on 2019-10-09 18:22
 * Email: frankma9103@gmail.com
 * Desc: 用户中心Dialog
 * 规则：
 * 只有坚果账号登录的才需要找回密码，才能绑定邮箱；
 * FB或Google登录的屏蔽掉绑定邮箱
 *
 * 账号登录：
 * 绑定邮箱的话，能重置密码
 * 没绑定邮箱的话，不能重置密码
 *
 * 游客：
 *
 */
public class UserCenterDialog extends Dialog {

    public UserCenterDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        if (window == null) return;
        window.setWindowAnimations(SDKResUtils.getResId(context,"dialog_anim_style","style"));
    }

    public UserCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UserCenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        @SuppressLint("HandlerLeak")
        public UserCenterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UserCenterDialog dialog = new UserCenterDialog(context);
            if (inflater == null) return dialog;
            View layout;
            if (SDKManager.getInstance().isCommonVersion()){
                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_usercenter", "layout"), null);
            }else {
                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_user_center", "layout"), null);
            }

            ImageView imgClose = layout.findViewById(SDKResUtils.getResId(context, "img_close", "id"));
            ImageView userIcon = layout.findViewById(SDKResUtils.getResId(context, "user_icon", "id"));
            TextView userNameTv = layout.findViewById(SDKResUtils.getResId(context, "tv_name", "id"));

            TextView customServiceTv = layout.findViewById(SDKResUtils.getResId(context, "tv_customer_service", "id"));
            TextView bindFbTv = layout.findViewById(SDKResUtils.getResId(context,"tv_bind_fb","id"));
            //绑定邮箱、重置密码
            TextView bindEmailTv = layout.findViewById(SDKResUtils.getResId(context,"tv_bind_email","id"));
            TextView resetPwdTv = layout.findViewById(SDKResUtils.getResId(context,"tv_reset_pwd","id"));
            View bindEmailLayout = layout.findViewById(SDKResUtils.getResId(context,"layout_bind_email","id"));
            View resetPwdLayout = layout.findViewById(SDKResUtils.getResId(context,"layout_reset_pwd","id"));

            requestData(bindFbTv,bindEmailLayout,resetPwdLayout,userNameTv,userIcon);

            //设置自定义字体
            SDKGameUtils.setTypeFace(context,userNameTv);
            SDKGameUtils.setTypeFaceBold(context,customServiceTv);
            SDKGameUtils.setTypeFaceBold(context,bindFbTv);
            SDKGameUtils.setTypeFaceBold(context,bindEmailTv);
            SDKGameUtils.setTypeFaceBold(context,resetPwdTv);
            //显示语言多样化
            customServiceTv.setText(SDKLangConfig.getInstance().findMessage("str_customer_service"));
            bindFbTv.setText(SDKLangConfig.getInstance().findMessage("str_bind_facebook"));
            bindEmailTv.setText(SDKLangConfig.getInstance().findMessage("str_bind_email"));
            resetPwdTv.setText(SDKLangConfig.getInstance().findMessage("str_reset_pwd"));

            //绑定邮箱
            bindEmailTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    BindEmailDialog.Builder builder = new BindEmailDialog.Builder(context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            Log.d("UserCenterDialog","MainThreadID_EmailBindDialog:"+Looper.getMainLooper().getThread().getId());
                            Log.d("UserCenterDialog","ThreadID_EmailBindDialog:"+Thread.currentThread().getId());
                            bindEmailLayout.setVisibility(View.GONE);
                            resetPwdLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                    builder.create().show();
                }
            });
            //绑定FB
            bindFbTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    SDKManager.getInstance().sdkGuestBindFB((Activity) context, new ResultCallBack() {
                        @Override
                        public void onSuccess() {
                            //游客绑定FB成功
                            Log.d("UserCenterDialog","MainThreadID_bindfb:"+Looper.getMainLooper().getThread().getId());
                            Log.d("UserCenterDialog","ThreadID_bindfb:"+Thread.currentThread().getId());
                            bindFbTv.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                }
            });

            //重置密码
            resetPwdTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //防止快速点击
                    if (SDKGameUtils.isMultiClicks()) {
                        return;
                    }

                    ResetPwdDialog.Builder builder = new ResetPwdDialog.Builder(context);
                    builder.create().show();
                }
            });

            //客服系统
            customServiceTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = SDKManager.getInstance().getUser();
                    if (user == null) return;
                    String username = user.getUserName();
                    CustomerServiceManager.customerSupport(username,"","",new JSONObject(),true);
                }
            });

            //关闭
            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            dialog.setContentView(layout);
            if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);
            return dialog;
        }

        /**
         * 初始化用户状态
         */
        private void requestData(View bindFB ,View bindEmail,View resetPwd,TextView userName,ImageView userIcon) {
            try {
                String ticket = SDKManager.getInstance().getUser().getTicket();
                if (ticket.isEmpty()) return;
                String aesKey = AESUtils.generate16SecretKey();
                String ivParameter = AESUtils.generate16SecretKey();
                String publicKey = SPManager.getInstance(context).getString(SPKey.PUBLIC_KEY);
                String aesKey16byRSA = RSAUtils.encryptData(aesKey.getBytes(), RSAUtils.loadPublicKey(publicKey));
                ApiManager.getInstance().queryUserInfo(aesKey, ivParameter, aesKey16byRSA, ticket, new NetCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        if (StringUtils.isEmpty(result)) return;
                        String decodeData = AESUtils.decrypt(result, aesKey,ivParameter);
                        if (StringUtils.isEmpty(decodeData)) return;
                        UserBindInfo userBindInfo = (UserBindInfo) GsonUtils.json2Bean(decodeData, UserBindInfo.class);
                        System.out.println(userBindInfo.toString());

                        if (userBindInfo.getCode() == SDKConstant.SUCCESS) {
                            UserBindInfo.DataBean data = userBindInfo.getData();
                            if (data == null) return;
                            initView(data,bindFB,bindEmail,resetPwd,userName,userIcon);
                        }else {
                            SDKGameUtils.showServiceInfo(userBindInfo.getCode(),userBindInfo.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        System.out.println(msg);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 初始化视图
         * @param data
         * @param bindFB
         * @param bindEmail
         * @param resetPwd
         * @param userName
         * @param userIcon
         */
        private void initView(UserBindInfo.DataBean data,View bindFB ,View bindEmail,View resetPwd,TextView userName,ImageView userIcon) {
            User user = SDKManager.getInstance().getUser();
            if (user == null) return;
            String msg = "";
            int resId = 0;
            switch (SDKManager.getInstance().getUser().getSdkmemberType()){
                case SDKConstant.TYPE_GUEST:
                    bindFB.setVisibility(data.isBindFacebook() ? View.GONE : View.VISIBLE);
                    bindEmail.setVisibility(View.GONE);
                    resetPwd.setVisibility(View.GONE);

                    msg += SDKLangConfig.getInstance().findMessage("20");
                    resId = SDKResUtils.getResId(context,"usercenter_guest","drawable");
                    break;
                case SDKConstant.TYPE_ACCOUNT:
                    bindFB.setVisibility(data.isBindFacebook() ? View.GONE : View.VISIBLE);
                    bindEmail.setVisibility(StringUtils.isEmpty(data.getBindEmail()) ? View.VISIBLE : View.GONE);
                    resetPwd.setVisibility(StringUtils.isEmpty(data.getBindEmail())? View.GONE:View.VISIBLE);

                    resId = SDKResUtils.getResId(context,"usercenter_nuts_account","drawable");
                    msg += "UserName："+ data.getBindAccount();
                    if (StringUtils.isEmpty(data.getBindEmail())) break;
                    String bindEmailStr = SDKLangConfig.getInstance().findMessage("nuts_BoundEmail") + SDKGameUtils.hideEmail(data.getBindEmail());
                    msg += "\n"+ bindEmailStr;
                    break;
                case SDKConstant.TYPE_FACEBOOK:
                    bindFB.setVisibility(View.GONE);
                    bindEmail.setVisibility(View.GONE);
                    resetPwd.setVisibility(View.GONE);

                    msg += "Facebook：" + user.getFacebookName();
                    resId = SDKResUtils.getResId(context,"usercenter_fbuser","drawable");
                    break;
                default:
                    break;
            }

            userName.setText(msg);
            userIcon.setImageResource(resId);
        }
    }


}
