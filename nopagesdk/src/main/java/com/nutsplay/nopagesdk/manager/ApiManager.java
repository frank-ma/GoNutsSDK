package com.nutsplay.nopagesdk.manager;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.beans.Purchase;
import com.nutsplay.nopagesdk.callback.NetCallBack;
import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.network.GsonUtils;
import com.nutsplay.nopagesdk.network.NetClient;
import com.nutsplay.nopagesdk.utils.Installations;
import com.nutsplay.nopagesdk.utils.encryption.AESUtils;
import com.nutsplay.nopagesdk.utils.encryption.SHA1Utils;
import com.nutspower.commonlibrary.utils.LogUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

import java.io.Serializable;
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

    private String imei="",mClientID = "", mKey = "", language = "", identifier="";

    private String[] domains;

    private String goDomainName1 = "https://go.0egg.com/foo";

    private ApiManager() {
        InitParameter initParameter = SDKManager.getInstance().getInitParameter();
        if (initParameter == null){
            System.out.println("ApiManager construction failed:initParameter is null.");
            return;
        }
//        imei = DeviceUtils.getDeviceID(SDKManager.getInstance().getActivity());
        mClientID = initParameter.getClientId();
        mKey = initParameter.getClientKey();
        language = initParameter.getLanguage();
        identifier = Installations.id(SDKManager.getInstance().getActivity());

        String goDomainName1 = "https://go.0egg.com/foo";
        String goDomainName2 = "https://go.0egg.com/foo";
        String goDomainName3 = "https://go.0egg.com/foo";
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
     * **************************************************************************************************
     */


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
    public void SDKInitGo(String aesKey16, String aesKey16byRSA, NetCallBack callBack) {

        try {
            String url = addDomainName() + "/epsilon";

            Init initBean = new Init();
            initBean.setClientID(mClientID);
            if (SDKManager.getInstance().getUser()!=null && StringUtils.isNotBlank(SDKManager.getInstance().getUser().getTicket())){
                initBean.setTicket(SDKManager.getInstance().getUser().getTicket()); //当前用户的ticket
            }
            String jsonData = GsonUtils.tojsonString(initBean);
            LogUtils.d(TAG,"InitGo初始化请求body："+jsonData);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册账号
     * @param aesKey16
     * @param aesKey16byRSA
     * @param callBack
     */
    public void SDKRegisterAccount(String aesKey16, String aesKey16byRSA, String userName, String pwd,NetCallBack callBack){

        try {
            String url = addDomainName() + "/iota";

            AccountLogin registerAccount = new AccountLogin();
            registerAccount.setClientID(mClientID);
            registerAccount.setAccount(userName);
            registerAccount.setSecond(SHA1Utils.sha1UpperCase(pwd));
            String jsonData = GsonUtils.tojsonString(registerAccount);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录接口
     *
     * @param userName
     * @param pwd
     * @param callBack
     */
    public void SDKLoginGo(String aesKey16, String aesKey16byRSA,String userName, String pwd, NetCallBack callBack){

        try {
            String url = addDomainName() + "/sigma";

            AccountLogin accountLogin = new AccountLogin();
            accountLogin.setClientID(mClientID);
            accountLogin.setAccount(userName);
            accountLogin.setSecond(SHA1Utils.sha1UpperCase(pwd));
            String jsonData = GsonUtils.tojsonString(accountLogin);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第三方类型的登录（游客登录，FB登录，谷歌登录）
     * @param aesKey16
     * @param aesKey16byRSA
     * @param oauthsource
     * @param callBack
     */
    public void SDKLoginThird(String aesKey16, String aesKey16byRSA,String oauthId,String oauthsource, NetCallBack callBack){
        try {
            String url = addDomainName() + "/omega";

            ThirdLogin thirdLogin = new ThirdLogin();
            thirdLogin.setClientID(mClientID);
            thirdLogin.setOauthId(oauthId);
            thirdLogin.setOauthSource(oauthsource);
            String jsonData = GsonUtils.tojsonString(thirdLogin);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 充值-下单
     * @param aesKey16
     * @param aesKey16byRSA
     * @param callBack
     */
    public void SDKMakeOrder(String aesKey16, String aesKey16byRSA,String serverId,String referenceId,String gameExt, NetCallBack callBack){
        try {
            String url = addDomainName() + "/delta";

            MakeOrder makeOrder = new MakeOrder();
            makeOrder.setClientID(mClientID);
            makeOrder.setServerID(serverId);
            makeOrder.setUserID(SDKManager.getInstance().getUser().getUserId());
            makeOrder.setDevice(DEVICE_TYPE);
            makeOrder.setReferenceId(referenceId);
            makeOrder.setGameExt(gameExt);
            String jsonData = GsonUtils.tojsonString(makeOrder);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 充值-回调接口
     * @param aesKey16
     * @param aesKey16byRSA
     * @param callBack
     */
    public void SDKPurchaseNotify(String aesKey16, String aesKey16byRSA, String transactionId, Purchase purchase, NetCallBack callBack){
        try {
            String url = addDomainName() + "/phi";

            Notify notify = new Notify();
            notify.setClientID(mClientID);
            notify.setTransactionId(transactionId);
            notify.setChannelCode("GOOGLE");
            String purchaseData=GsonUtils.tojsonString(purchase);
            notify.setPurchase(purchaseData);
            String jsonData = GsonUtils.tojsonString(notify);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 第三方充值完成后查询订单状态接口
     * @param aesKey16
     * @param aesKey16byRSA
     * @param callBack
     */
    public void SDKQueryOrderStatus(String aesKey16, String aesKey16byRSA,String transactionId, NetCallBack callBack){
        try {
            String url = addDomainName() + "/psi";

            QueryOrder queryOrder = new QueryOrder();
            queryOrder.setClientID(mClientID);
            queryOrder.setTransactionId(transactionId);
            String jsonData = GsonUtils.tojsonString(queryOrder);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param account
     * @param oldPwd
     * @param newPwd
     * @param callBack
     */
    public void SDKResetPwd(String aesKey16, String aesKey16byRSA,String account,String oldPwd,String newPwd, NetCallBack callBack){

        try {
            String url = addDomainName() + "/zeta";

            ResetPwd resetPwd = new ResetPwd();
            resetPwd.setClientID(mClientID);
            resetPwd.setAccount(account);
            resetPwd.setSecond(SHA1Utils.sha1UpperCase(oldPwd));
            resetPwd.setNewsecond(SHA1Utils.sha1UpperCase(newPwd));
            String jsonData = GsonUtils.tojsonString(resetPwd);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 第三方类型账号绑定注册账号
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param oauthid 用户唯一标识
     * @param oauthSource 第三方来源
     * @param account 账号
     * @param second SHA1加密后大写的密码
     * @param callBack
     */
    public void SDKBindAccount(String aesKey16, String aesKey16byRSA,String oauthid,String oauthSource,String account,String second, NetCallBack callBack){

        try {
            String url = addDomainName() + "/rho";

            BindAccount bindAccount = new BindAccount();
            bindAccount.setClientID(mClientID);
            bindAccount.setOauthid(oauthid);
            bindAccount.setOauthsource(oauthSource);
            bindAccount.setAccount(account);
            bindAccount.setSecond(SHA1Utils.sha1UpperCase(second));
            String jsonData = GsonUtils.tojsonString(bindAccount);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

/**
 * **************************************************************************************************
 */

    /**
     * 时间戳
     *
     * @return
     */
    private String currentTime() {
        return System.currentTimeMillis() / 1000 + "";
    }

    private class Bean implements Serializable{

        private String clientID;

        public void setClientID(String clientID) {
            this.clientID = clientID;
        }
    }

    private class Init extends Bean implements Serializable{

        private String ticket;

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }
    }

    private class ThirdLogin extends Bean implements Serializable {

        private String oauthId;
        private String oauthSource;

        public void setOauthId(String oauthId) {
            this.oauthId = oauthId;
        }

        public void setOauthSource(String oauthSource) {
            this.oauthSource = oauthSource;
        }
    }

    private class AccountLogin extends Bean implements Serializable{

        private String account;
        private String second;

        public void setAccount(String account) {
            this.account = account;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }

    private class MakeOrder extends Bean implements Serializable{

        private String serverID;
        private String userID;
        private String device;
        private String referenceId;
        private String gameExt;

        public void setServerID(String serverID) {
            this.serverID = serverID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public void setReferenceId(String referenceId) {
            this.referenceId = referenceId;
        }

        public void setGameExt(String gameExt) {
            this.gameExt = gameExt;
        }
    }

    private class Notify extends Bean implements Serializable{

        private String transactionId;
        private String purchase;
        private String channelCode;

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public void setPurchase(String purchaseJson) {
            this.purchase = purchaseJson;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }
    }

    private class QueryOrder extends Bean implements Serializable{

        private String transactionId;

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
    }

    private class ResetPwd extends Bean implements Serializable{

        private String account;

        private String second;

        private String newsecond;

        public void setAccount(String account) {
            this.account = account;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public void setNewsecond(String newsecond) {
            this.newsecond = newsecond;
        }
    }

    private class BindAccount extends Bean implements Serializable{

        private String oauthid;
        private String oauthsource;
        private String account;
        private String second;

        public void setOauthid(String oauthid) {
            this.oauthid = oauthid;
        }

        public void setOauthsource(String oauthsource) {
            this.oauthsource = oauthsource;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }

}
