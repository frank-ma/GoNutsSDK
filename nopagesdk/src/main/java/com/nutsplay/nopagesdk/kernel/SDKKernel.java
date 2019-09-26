package com.nutsplay.nopagesdk.kernel;

import android.app.Activity;
import android.util.Log;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.SDKInitModel;
import com.nutsplay.nopagesdk.beans.User;
import com.nutsplay.nopagesdk.utils.Installations;
import com.nutsplay.nopagesdk.utils.sputil.SPKey;
import com.nutsplay.nopagesdk.utils.sputil.SPManager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by frank-ma on 2018/12/28 下午3:25
 * Email: frankma9103@gmail.com
 * Desc: 存储登录参数
 */
public class SDKKernel {

    private InitParameter sdkClientParams;

    private Activity activity;

    private String mPayUrl;

    private static volatile SDKKernel INSTANCE;

    private SDKKernel() {
    }

    public static SDKKernel getInstance() {

        if (null == INSTANCE) {
            synchronized (SDKKernel.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SDKKernel();
                }
            }
        }
        return INSTANCE;
    }

    public void setSdkClientParams(InitParameter sdkClientParams) {
        this.sdkClientParams = sdkClientParams;
        SPManager.getInstance(getActivity()).putBean(SPKey.key_bean_init, sdkClientParams);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        if (activity == null) {
            throw new NullPointerException("请传入activity");
        }
        return activity;
    }

    public String getAppid() {
        if (sdkClientParams != null) {
            return sdkClientParams.getClientId();
        }
        return "";
    }

    public String getAppkey() {
        if (sdkClientParams != null) {
            return sdkClientParams.getClientKey();
        }
        return "";
    }

    public String getCustomerServiceAddress(){
        if (sdkClientParams != null){
            if (sdkClientParams.getCustomerServiceAddress().isEmpty()){
                return "https://tb.53kf.com/code/app/10197683/1";
            }
            return sdkClientParams.getCustomerServiceAddress();
        }
        return "https://tb.53kf.com/code/app/10197683/1";
    }

    public String getAFkey(){
        if (sdkClientParams != null){
            if (sdkClientParams.getAppsflyerId().isEmpty()){
                return "VBmCBKvNg5uvd4iiLZSx7J";
            }
            return sdkClientParams.getAppsflyerId();
        }
        return "VBmCBKvNg5uvd4iiLZSx7J";
    }

    public String getLanguage() {
        if (sdkClientParams != null) {
            return sdkClientParams.getLanguage();
        }
        return "zh_cn";
    }

    public boolean isDebug() {
        if (sdkClientParams != null) {
            return sdkClientParams.isDebug();
        }
        return true;
    }

//    public String getToken() {
//        return SPManager.getInstance(getActivity()).getString(SPKey.key_sdk_token,"");
//    }
//
//    public void setToken(SDKInitModel sdkInitModel){
//        if (sdkInitModel==null)return;
//        SPManager.getInstance(getActivity()).putString(SPKey.key_sdk_token, sdkInitModel.getData().getAccessToken());
//    }

    public void setAuto(boolean auto) {
        SPManager.getInstance(getActivity()).putBoolean(SPKey.key_sdk_auto, auto);
    }

    public boolean isAuto() {
        return SPManager.getInstance(getActivity()).getBoolean(SPKey.key_sdk_auto);
    }

    public void setGuestLoginCount(int count) {
        SPManager.getInstance(getActivity()).putInt(SPKey.key_sdk_auto_guest_count, count);
    }

    public int getGuestLoginCount() {
        return SPManager.getInstance(getActivity()).getInt(SPKey.key_sdk_auto_guest_count);
    }

    public User getUser() {
        return (User) SPManager.getInstance(getActivity()).getBean(SPKey.key_bean_data_user);
    }

    public void setUser(User token) {
        SPManager.getInstance(getActivity()).putBean(SPKey.key_bean_data_user, token);
    }

    public void setSwitchAccount(boolean isSwitch) {
        SPManager.getInstance(getActivity()).putBoolean(SPKey.key_sdk_switch, isSwitch);
    }

    public boolean isSwitchAccount() {
        return SPManager.getInstance(getActivity()).getBoolean(SPKey.key_sdk_switch);
    }

    public void setBindGuest(boolean auto) {
        SPManager.getInstance(getActivity()).putBoolean(SPKey.key_sdk_bind_guest, auto);
    }

    public boolean isBindGuest() {
        return SPManager.getInstance(getActivity()).getBoolean(SPKey.key_sdk_bind_guest);
    }

    /**
     * 获取唯一标识符
     *
     * @return
     */
    public String getIdentifier() {
        return Installations.id(getActivity());
    }

    public void setInitData(SDKInitModel sdkInitModel) {
        SPManager.getInstance(getActivity()).putBean(SPKey.key_bean_data_init, sdkInitModel);
    }

    public SDKInitModel getInitData(){
        return (SDKInitModel) SPManager.getInstance(getActivity()).getBean(SPKey.key_bean_data_init);
    }

    public String getPayUrl() {
        return mPayUrl;
    }

    public void setPayUrl(String mPayUrl) {
        this.mPayUrl = mPayUrl;
        //解析出orderid
        analysisUrl(mPayUrl);
    }

    /**
     *
     * //https://web.iapppay.com/h5/gateway?data=%7B%22tid%22%3A%2232911903281137427276%22%2C%22app%22%3A%223015863201%22%2C%22url_r%22%3A%22http%3A%2F%2Fapi.0yx.net%2FPay%2FReturn%2Faibei.do%22%2C+%22url_h%22+%3A%22http%3A%2F%2Fapi.0yx.net%2FPay%2FReturn%2Faibei.do%22%7D&sign=PlG1%2BJHnVIiEgvq81WQ4EpJv5x5HEAcyjEWsfNohw%2FMVrh2TdxDyAJ%2B1UYwv%2BCNvi9aDRhnBa7wxk69FieHW1NcDsEbLHTNkiRKWgy6eyuyPHsB3AYSXQzC4Zsea013Tl8tNjg6XvzoNB9Bo0hu7Zpykhp0wQdMP3yX9h4v5ZbM%3D&sign_type=RSA
     *
     * // data={"tid":"32931903271538028839","app":"3019663731","url_r":"http://api.0yx.net/Pay/Return/aibei2.do",+"url_h"+:"http://api.0yx.net/Pay/Return/aibei2.do"}&sign=DRpHAeYAgkHyibPZyVjGFGcd1OemzJIKGNF0xdvLjkwXaTrkDAzGYJty6Vu0oTuCioHBOsm/4MdXV6RRzEdZ0D6mrA5d6yPcY7w4lueM7fg/CrGzuZ3JR4sIG1vodykvPPEwbgVhvUjRzCGMXZi13lVsEYjExoPzgQ0RPFaPqI8=&sign_type=RSA","message":"1"}
     * @param mPayUrl
     */
    private void analysisUrl(String mPayUrl){
        if (mPayUrl==null || mPayUrl.isEmpty())return;

        try {
            String decodeUrl = URLDecoder.decode(mPayUrl, "UTF-8");
            if (decodeUrl.contains("data=")){
                int startIndex=decodeUrl.indexOf("\"tid\":\"")+7;
                int endIndex=decodeUrl.indexOf("\",\"app\"");
                String tid=decodeUrl.substring(startIndex,endIndex);
                Log.e("tid",tid);
                SPManager.getInstance(getActivity()).putString(SPKey.AiBeiTranslateId,tid);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
