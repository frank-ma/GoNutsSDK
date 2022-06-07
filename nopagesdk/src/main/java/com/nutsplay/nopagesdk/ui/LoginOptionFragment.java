//package com.nutsplay.nopagesdk.ui;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.nutsplay.nopagesdk.callback.LoginCallBack;
//import com.nutsplay.nopagesdk.callback.RegisterResultCallBack;
//import com.nutsplay.nopagesdk.callback.ResultCallBack;
//import com.nutsplay.nopagesdk.kernel.SDKConstant;
//import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
//import com.nutsplay.nopagesdk.kernel.SDKManager;
//import com.nutsplay.nopagesdk.manager.LoginManager;
//import com.nutsplay.nopagesdk.utils.SDKGameUtils;
//import com.nutsplay.nopagesdk.utils.SDKResUtils;
//
//import org.jetbrains.annotations.NotNull;
//
///**
// * Created by frankma on 2019-10-09 18:22
// * Email: frankma9103@gmail.com
// * Desc:
// */
//public class LoginOptionFragment extends Fragment {
//
//    private long lastTime = 0;
//
//    public static LoginOptionFragment newInstance(boolean isLogin) {
//        Bundle args = new Bundle();
//        args.putBoolean(SDKConstant.openType,isLogin);
//        LoginOptionFragment fragment = new LoginOptionFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @org.jetbrains.annotations.Nullable
//    @Override
//    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        View v;
//        if (SDKManager.getInstance().isCommonVersion()) {
//            v = inflater.inflate(SDKResUtils.getResId(getContext(), "nuts2_fragment_login_choose", "layout"), null);
//        } else {
//            v = inflater.inflate(SDKResUtils.getResId(getContext(), "sdk_dialog_login_choose", "layout"), null);
//        }
//
//        initView(v);
//        return v;
//    }
//
//    private void initView(View v) {
//        TextView visitorLogin = v.findViewById(SDKResUtils.getResId(getContext(), "tv_visitor_sign_in", "id"));
//        TextView accountLogin = v.findViewById(SDKResUtils.getResId(getContext(), "tv_login", "id"));
//        TextView accountRegister = v.findViewById(SDKResUtils.getResId(getContext(), "tv_register", "id"));
//        TextView fbLogin = v.findViewById(SDKResUtils.getResId(getContext(), "fb_login", "id"));
////            SignInButton googleButton = layout.findViewById(SDKResUtils.getResId(context,"sign_in_button","id"));
//        ImageView closeImg = v.findViewById(SDKResUtils.getResId(getContext(), "ic_close", "id"));
//
//        //切换账号时显示关闭按钮
//        if (getArguments() != null) {
//            closeImg.setVisibility(getArguments().getBoolean(SDKConstant.openType)?View.INVISIBLE:View.VISIBLE);
//        }
//
//        //设置文本
//        visitorLogin.setText(SDKLangConfig.getInstance().findMessage("guest_login"));
//        accountLogin.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
//        accountRegister.setText(SDKLangConfig.getInstance().findMessage("sign_up"));
//
//        //游客登录
//        visitorLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //防止快速点击
//                if (SDKGameUtils.isMultiClicks()) {
//                    return;
//                }
//                SDKManager.getInstance().handleLogout((Activity) getActivity());
//                SDKManager.getInstance().showEmptyProgress((Activity) getActivity());
//                LoginManager.getInstance().visitorLogin((Activity) getActivity(), loginCallBack, new ResultCallBack() {
//                    @Override
//                    public void onSuccess() {
//                        dismiss();
//
//                        SDKManager.getInstance().hideEmptyProgress();
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        SDKManager.getInstance().hideEmptyProgress();
//                    }
//                });
//
//            }
//        });
//
//
//    }
//
//    public static class Builder {
//        private Context context;
//        private LoginCallBack loginCallBack;
//        private boolean isLogin = true;//是登录还是切换账号
//        private long lastTime = 0;
//
//        public Builder(Context context, LoginCallBack loginCallBack, boolean isLogin) {
//            this.context = context;
//            this.loginCallBack = loginCallBack;
//            this.isLogin = isLogin;
//        }
//
//        public LoginOptionFragment create() {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final LoginOptionFragment dialog = new LoginOptionFragment(context);
//            if (inflater == null) return dialog;
//            View layout;
//            if (SDKManager.getInstance().isCommonVersion()) {
//                layout = inflater.inflate(SDKResUtils.getResId(context, "nuts2_fragment_login_choose", "layout"), null);
//            } else {
//                layout = inflater.inflate(SDKResUtils.getResId(context, "sdk_dialog_login_choose", "layout"), null);
//            }
//
//
//            //findView
//            TextView visitorLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_visitor_sign_in", "id"));
//            TextView accountLogin = layout.findViewById(SDKResUtils.getResId(context, "tv_login", "id"));
//            TextView accountRegister = layout.findViewById(SDKResUtils.getResId(context, "tv_register", "id"));
//            TextView fbLogin = layout.findViewById(SDKResUtils.getResId(context, "fb_login", "id"));
////            SignInButton googleButton = layout.findViewById(SDKResUtils.getResId(context,"sign_in_button","id"));
//            ImageView closeImg = layout.findViewById(SDKResUtils.getResId(context, "ic_close", "id"));
//
//
//            //切换账号时显示关闭按钮
//            if (isLogin) {
//                closeImg.setVisibility(View.INVISIBLE);
//            } else {
//                closeImg.setVisibility(View.VISIBLE);
//            }
//
//
//            visitorLogin.setText(SDKLangConfig.getInstance().findMessage("guest_login"));
//            accountLogin.setText(SDKLangConfig.getInstance().findMessage("sign_in"));
//            accountRegister.setText(SDKLangConfig.getInstance().findMessage("sign_up"));
//
//            //游客登录
//            visitorLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //防止快速点击
//                    long count = System.currentTimeMillis() - lastTime;
//                    if (count <= 2000) {
//                        return;
//                    } else {
//                        lastTime = System.currentTimeMillis();
//                    }
//                    SDKManager.getInstance().handleLogout((Activity) context);
//
//                    SDKManager.getInstance().showEmptyProgress((Activity) context);
//                    LoginManager.getInstance().visitorLogin((Activity) context, loginCallBack, new ResultCallBack() {
//                        @Override
//                        public void onSuccess() {
//                            dialog.dismiss();
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//                    });
//
//                }
//            });
//
//            //账号登录
//            accountLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //防止快速点击
//                    long count = System.currentTimeMillis() - lastTime;
//                    if (count <= 2000) {
//                        return;
//                    } else {
//                        lastTime = System.currentTimeMillis();
//                    }
//                    SDKManager.getInstance().handleLogout((Activity) context);
//
//                    LoginDialog.Builder builder = new LoginDialog.Builder(context, loginCallBack, isLogin);
//                    builder.create().show();
//                    dialog.dismiss();
//                }
//            });
//
//            //注册账号
//            accountRegister.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //防止快速点击
//                    long count = System.currentTimeMillis() - lastTime;
//                    if (count <= 2000) {
//                        return;
//                    } else {
//                        lastTime = System.currentTimeMillis();
//                    }
//                    SDKManager.getInstance().handleLogout((Activity) context);
//
//                    RegisterDialog.Builder builder = new RegisterDialog.Builder(context, new RegisterResultCallBack() {
//                        @Override
//                        public void onSuccess(String account, String pwd) {
//                            System.out.println(account + "---" + pwd);
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            System.out.println(msg);
//                        }
//                    });
//                    builder.create().show();
//                    dialog.dismiss();
//                }
//            });
//
//            //fb登录
//            fbLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //防止快速点击
//                    long count = System.currentTimeMillis() - lastTime;
//                    if (count <= 2000) {
//                        return;
//                    } else {
//                        lastTime = System.currentTimeMillis();
//                    }
//                    SDKManager.getInstance().handleLogout((Activity) context);
//
//                    SDKManager.getInstance().showEmptyProgress((Activity) context);
//                    LoginManager.getInstance().facebookLogin((Activity) context, loginCallBack, new ResultCallBack() {
//                        @Override
//                        public void onSuccess() {
//                            dialog.dismiss();
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            SDKManager.getInstance().hideEmptyProgress();
//                        }
//                    });
//                }
//            });
//
//            //Google登录
////            googleButton.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    //防止快速点击
////                    long count = System.currentTimeMillis() - lastTime;
////                    if (count <= 2000) {
////                        return;
////                    }else {
////                        lastTime = System.currentTimeMillis();
////                    }
////                    SDKManager.getInstance().handleLogout((Activity) context);
////
////                    SDKManager.getInstance().showEmptyProgress((Activity) context);
////                    LoginManager.getInstance().googleLogin((Activity) context, loginCallBack, new ResultCallBack() {
////                        @Override
////                        public void onSuccess() {
////                            dialog.dismiss();
////                            SDKManager.getInstance().hideEmptyProgress();
////                        }
////
////                        @Override
////                        public void onFailure(String msg) {
////                            SDKManager.getInstance().hideEmptyProgress();
////                        }
////                    });
////
////                }
////            });
//
//            //关闭按钮
//            closeImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //恢复自动登录
//                    SDKManager.getInstance().setAuto(true);
//
//                    if (loginCallBack != null) loginCallBack.onCancel();
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.setContentView(layout);
//            if (dialog.getWindow() != null)
//                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//            dialog.setCancelable(false);
//            return dialog;
//        }
//    }
//
//
//}
