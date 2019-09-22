package com.nutsplay.nopagesdk.utils.toast;

import android.widget.Toast;

import com.nutsplay.nopagesdk.network.kernel.SDKManager;

public class NutsToast {

    private static NutsToast toastCommom;

    private NutsToast() {
    }

    public static NutsToast getInstance() {
        if (toastCommom == null) {
            synchronized (NutsToast.class){
                if (toastCommom==null){
                    toastCommom = new NutsToast();
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
            Toasty.success(SDKManager.getInstance().getActivity(), tvString, Toast_Length, true).show();
        } else if (type == 2) {
            Toasty.info(SDKManager.getInstance().getActivity(), tvString, Toast_Length, true).show();
        } else if (type == 3) {
            Toasty.warning(SDKManager.getInstance().getActivity(), tvString, Toast_Length, true).show();
        } else if (type == 4) {
            Toasty.error(SDKManager.getInstance().getActivity(), tvString, Toast_Length, true).show();
        } else {
            Toasty.info(SDKManager.getInstance().getActivity(), tvString, Toast_Length, true).show();
        }
    }


}
