package com.nutsplay.nopagesdk.network;

import com.nutsplay.nopagesdk.network.beans.InitParameter;
import com.nutsplay.nopagesdk.network.callback.NetCallBack;
import com.nutsplay.nopagesdk.network.kernel.NetClient;
import com.nutsplay.nopagesdk.network.kernel.SDKManager;
import com.nutspower.commonlibrary.utils.DeviceUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by frank-ma on 2019-09-18 19:33
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class ApiManager {

    private final String TAG = getClass().getSimpleName();

    private final String DEVICE_TYPE = "Android";

    private volatile static ApiManager INSTANCE;

    private String mClientID = "", mKey = "", language = "", identifier="";

    private String[] domains;

    private String goDomainName1 = "http://go.0egg.com/foo";

    private ApiManager() {
        InitParameter initParameter = SDKManager.getInstance().getInitParameter();
        if (initParameter == null){
            System.out.println("ApiManager construction failed:initParameter is null.");
            return;
        }
        mClientID = initParameter.getClientId();
        mKey = initParameter.getClientKey();
        language = initParameter.getLanguage();
//        identifier = NutsKernel.getInstance().getIdentifier();

        String goDomainName1 = "http://go.0egg.com/foo";
        String goDomainName2 = "http://go.0egg.com/foo1";
        String goDomainName3 = "http://go.0egg.com/foo2";
        domains = new String[]{goDomainName1,goDomainName2,goDomainName3};
    }


    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ApiManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ApiManager();
                }
            }
        }
        return INSTANCE;
    }

    private String addDomainName(){
        if (domains == null || domains.length == 0) return goDomainName1;
        int index = new Random().nextInt(domains.length);
        return domains[index];
    }


    /**
     * 获取RSA公钥的接口
     *
     * @param jsonCallback
     */
    public void getRASPublicKey(NetCallBack jsonCallback){
        String url= addDomainName() + "/alpha";
        Map<String, String> headerMap = new TreeMap<>();
        headerMap.put("uniqueid",identifier);
        NetClient.getInstance().clientGet(url, null, headerMap,jsonCallback);
    }

    /**
     * 初始化
     *
     * @param callBack
     */
    public void SDKInitGo(String aesKey16, String aesKey, NetCallBack callBack) {


        try {
            String url = addDomainName() + "/epsilon";

            String imei = DeviceUtils.getDeviceID(NutsKernel.getInstance().getActivity());
            InitBean initBean = new InitBean();
            initBean.setImei(imei);
            initBean.setDevice(DEVICE_TYPE);
            initBean.setClientID(mClientID);
            initBean.setTime(currentTime());
            initBean.setMac(StringUtils.MD5(mClientID + currentTime() + mKey));
            initBean.setLanguage(language);
            String jsonData = GsonUtils.tojsonString(initBean);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey);
            NutsClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
