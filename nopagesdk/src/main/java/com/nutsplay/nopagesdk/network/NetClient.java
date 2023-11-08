package com.nutsplay.nopagesdk.network;

import com.nutsplay.nopagesdk.callback.NetCallBack;
import com.nutsplay.nopagesdk.kernel.SDKLangConfig;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLHandshakeException;

/**
 * Created by frank-ma on 2019-09-19 12:05
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class NetClient {

    private String TAG = getClass().getSimpleName();

    private volatile static NetClient INSTANCE;

    public static NetClient getInstance() {
        if (INSTANCE == null) {
            synchronized (NetClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetClient();
                }
            }
        }
        return INSTANCE;
    }

    public NetClient() {
        if (SDKManager.getInstance().getActivity() == null) return;
        x.Ext.init(SDKManager.getInstance().getActivity().getApplication());
        x.Ext.setDebug(SDKManager.getInstance().getInitParameter().isDebug());
    }

    /**
     * http请求get
     * @param url
     * @param map
     * @param headerMap
     * @param netCallBack
     */
    public void clientGet(String url, Map<String, String> map, Map<String, String> headerMap, final NetCallBack netCallBack) {
        RequestParams params = new RequestParams(url);
        params.setMaxRetryCount(1);

        if (headerMap != null && headerMap.size() > 0) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                params.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }

        LogUtils.e(TAG, "url:" + params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                netCallBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex == null) return;
                if (ex instanceof UnknownHostException || ex instanceof SSLHandshakeException || ex instanceof TimeoutException){
                    SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("network_error"),4);
                    SDKManager.getInstance().hideAllProgress();
                }else {
                    SDKToast.getInstance().ToastShow(ex.getMessage(), 4);
                }
                LogUtils.e(TAG, "onError: " + ex.getMessage());
                netCallBack.onFailure(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * http请求post
     * @param url
     * @param map
     * @param headerMap
     * @param netCallBack
     */
    public void clientPost(String url, Map<String, String> map, Map<String, String> headerMap, final NetCallBack netCallBack) {

        RequestParams params = new RequestParams(url);
        //要先设置请求方法，否则addBodyParameter添加的参数都给加到请求链接里面去了，本应该加到body里面的，url太长就会报错414 Uri too long
        params.setMethod(HttpMethod.POST);
        params.setMaxRetryCount(1);

        if (map != null && map.size()>0 ) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
//                params.addBodyParameter("",entry.getValue());
                params.setBodyContent(entry.getValue());
            }
        }

        if (headerMap!=null && headerMap.size()>0){
            for (Map.Entry<String,String> entry:headerMap.entrySet()){
                params.addHeader(entry.getKey(),entry.getValue());
            }
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                netCallBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex == null) return;
                if (ex instanceof UnknownHostException || ex instanceof SSLHandshakeException || ex instanceof TimeoutException) {
                    SDKToast.getInstance().ToastShow(SDKLangConfig.getInstance().findMessage("network_error"),4);
                    SDKManager.getInstance().hideAllProgress();
//                    return;
                }else{
                    SDKToast.getInstance().ToastShow(ex.getMessage(), 4);
                }
                netCallBack.onFailure(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
