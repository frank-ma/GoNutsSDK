package com.nutsplay.nopagesdk.utils.toast;

import android.widget.Toast;

import com.nutsplay.nopagesdk.kernel.SDKManager;

public class SDKToast {

    private static SDKToast toastCommom;

    private SDKToast() {
    }

    public static SDKToast getInstance() {
        if (toastCommom == null) {
            synchronized (SDKToast.class){
                if (toastCommom==null){
                    toastCommom = new SDKToast();
                }
            }
        }
        return toastCommom;
    }

    public void ToastShow(String tvString, int type) {
        ToastShow(tvString, Toast.LENGTH_LONG, type);
    }

    public void ToastShow(String tvString, int Toast_Length, int type) {

        if (type == 1) {
            Toasty.success(SDKManager.getInstance().getActivity(), tvString, Toast_Length, false).show();
        } else if (type == 2) {
            Toasty.info(SDKManager.getInstance().getActivity(), tvString, Toast_Length, false).show();
        } else if (type == 3) {
            Toasty.warning(SDKManager.getInstance().getActivity(), tvString, Toast_Length, false).show();
        } else if (type == 4) {
            Toasty.error(SDKManager.getInstance().getActivity(), tvString, Toast_Length, false).show();
        } else {
            Toasty.info(SDKManager.getInstance().getActivity(), tvString, Toast_Length, false).show();
        }
    }

    /**
     * 在onDestroy方法里面取消Toast,否则会关掉应用还弹Toast
     */
    public void toastCancel(){
        Toasty.cancel();
    }


}
