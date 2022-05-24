package com.nutsplay.nopagesdk.manager;

import android.content.Context;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.nutsplay.nopagesdk.beans.InitParameter;
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

    private String mClientID = "", identifier="";

    private String[] domains;

    private String goDomainName1 = "https://go.0egg.com/foo";

    private int index = 0;

    private ApiManager() {
        InitParameter initParameter = SDKManager.getInstance().getInitParameter();
        if (initParameter == null){
            System.out.println("ApiManager construction failed:initParameter is null.");
            return;
        }
        mClientID = initParameter.getClientId();
        identifier = Installations.id(SDKManager.getInstance().getActivity());

        String goDomainName1 = "https://go.0egg.com/foo";
        String goDomainName2 = "https://go.0egg.com/foo";

        domains = new String[]{goDomainName1,goDomainName2};
        index = new Random().nextInt(domains.length);
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
    public void SDKInitGo(String aesKey16, String ivParameter,String aesKey16byRSA, NetCallBack callBack) {

        try {
            String url = addDomainName() + "/epsilon";

            Init initBean = new Init();
            initBean.setClientID(mClientID);
            if (SDKManager.getInstance()!=null && SDKManager.getInstance().getUser()!=null && StringUtils.isNotBlank(SDKManager.getInstance().getUser().getTicket())){
                initBean.setTicket(SDKManager.getInstance().getUser().getTicket()); //当前用户的ticket
            }
            String jsonData = GsonUtils.tojsonString(initBean);
            LogUtils.d(TAG,"InitGobody："+jsonData);


            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKRegisterAccount(String aesKey16, String ivParameter,String aesKey16byRSA, String userName, String pwd,NetCallBack callBack){

        try {
            String url = addDomainName() + "/iota";

            AccountLogin registerAccount = new AccountLogin();
            registerAccount.setClientID(mClientID);
            registerAccount.setAccount(userName);
            registerAccount.setSecond(SHA1Utils.sha1UpperCase(pwd));
            String jsonData = GsonUtils.tojsonString(registerAccount);
//            LogUtils.d("注册",jsonData);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录接口
     * 为了避免这个接口被用来暴力探测账号是否存在, 于是服务器这边不会返回账号是否存在, 而是直接就返回密码错误
     * 账号不存在时返回的是-3密码错误，提示时提示：账号或密码错误
     *
     * @param userName
     * @param pwd
     * @param callBack
     */
    public void SDKLoginGo(String aesKey16, String ivParameter,String aesKey16byRSA,String userName, String pwd, NetCallBack callBack){

        try {
            String url = addDomainName() + "/sigma";

            AccountLogin accountLogin = new AccountLogin();
            accountLogin.setClientID(mClientID);
            accountLogin.setAccount(userName);
            accountLogin.setSecond(SHA1Utils.sha1UpperCase(pwd));
            String jsonData = GsonUtils.tojsonString(accountLogin);
//            LogUtils.d("登录",jsonData);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKLoginThird(String aesKey16, String ivParameter,String aesKey16byRSA,String oauthId,String oauthsource, NetCallBack callBack){
        try {
            String url = addDomainName() + "/omega";

            ThirdLogin thirdLogin = new ThirdLogin();
            thirdLogin.setClientID(mClientID);
            thirdLogin.setOauthId(oauthId);
            thirdLogin.setOauthSource(oauthsource);
            String jsonData = GsonUtils.tojsonString(thirdLogin);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKMakeOrder(String aesKey16,String ivParameter, String aesKey16byRSA,String serverId,String referenceId,String gameExt, NetCallBack callBack){
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

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKPurchaseNotify(String type,String aesKey16, String ivParameter,String aesKey16byRSA, Purchase purchase, NetCallBack callBack){
        try {
            String url = addDomainName() + "/phi";
            if (BillingClient.SkuType.SUBS.equals(type)){
                url = addDomainName() + "/psk";
            }

            if (purchase == null) return;
            String transactionId = purchase.getAccountIdentifiers() == null ? "" : purchase.getAccountIdentifiers().getObfuscatedAccountId();
            Notify notify = new Notify();
            notify.setClientID(mClientID);
            notify.setTransactionId(transactionId);
            notify.setChannelCode("GOOGLE");
            String purchaseData = purchase.getOriginalJson();

            notify.setPurchase(purchaseData);
            String jsonData = GsonUtils.tojsonString(notify);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKQueryOrderStatus(String aesKey16, String ivParameter,String aesKey16byRSA,String transactionId, NetCallBack callBack){
        try {
            String url = addDomainName() + "/psi";

            QueryOrder queryOrder = new QueryOrder();
            queryOrder.setClientID(mClientID);
            queryOrder.setTransactionId(transactionId);
            String jsonData = GsonUtils.tojsonString(queryOrder);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKResetPwd(String aesKey16, String ivParameter,String aesKey16byRSA,String account,String oldPwd,String newPwd, NetCallBack callBack){

        try {
            String url = addDomainName() + "/zeta";

            ResetPwd resetPwd = new ResetPwd();
            resetPwd.setClientID(mClientID);
            resetPwd.setAccount(account);
            resetPwd.setSecond(SHA1Utils.sha1UpperCase(oldPwd));
            resetPwd.setNewsecond(SHA1Utils.sha1UpperCase(newPwd));
            String jsonData = GsonUtils.tojsonString(resetPwd);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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
    public void SDKBindAccount(String aesKey16,String ivParameter, String aesKey16byRSA,String oauthid,String oauthSource,String account,String second, NetCallBack callBack){

        try {
            String url = addDomainName() + "/rho";

            BindAccount bindAccount = new BindAccount();
            bindAccount.setClientID(mClientID);
            bindAccount.setOauthid(oauthid);
            bindAccount.setOauthsource(oauthSource);
            bindAccount.setAccount(account);
            bindAccount.setSecond(SHA1Utils.sha1UpperCase(second));
            String jsonData = GsonUtils.tojsonString(bindAccount);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 游客绑定FB等第三方账号
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param oauthid 用户唯一标识
     * @param thirdId 第三方账号id
     * @param thirdSource 第三方账号来源
     * @param callBack
     */
    public void SDKGuestBindThirdAccount(String aesKey16, String ivParameter,String aesKey16byRSA,String oauthid,String thirdId,String thirdSource, NetCallBack callBack){

        try {
            String url = addDomainName() + "/tau";

            GuestBindThird guestBind = new GuestBindThird();
            guestBind.setClientID(mClientID);
            guestBind.setOauthid(oauthid);
            guestBind.setOauthsource("android");
            guestBind.setNewoauthId(thirdId);
            guestBind.setNewoauthSource(thirdSource);
            String jsonData = GsonUtils.tojsonString(guestBind);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 申请绑定邮箱验证码接口
     * 注意：
     * 1.想要绑定邮箱，必须是一个自定义注册账号的用户或者是其他登录方式但绑定了自定义账号的用户
     * 2.一个邮箱可以给多个账号绑定，但找回的时候，必须用户手动提供自定义账号是多少
     * 2.验证码有效期20分钟
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param ticket 用户本地ticket
     * @param email 要绑定的邮箱
     * @param callBack
     */
    public void SDKRequestBindEmail(String aesKey16, String ivParameter,String aesKey16byRSA,String ticket,String email, NetCallBack callBack){

        try {
            String url = addDomainName() + "/adam";

            BindEmail bindEmail = new BindEmail();
            bindEmail.setClientID(mClientID);
            bindEmail.setTicket(ticket);
            bindEmail.setEmail(email);
            String jsonData = GsonUtils.tojsonString(bindEmail);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 确认绑定邮箱验证码接口
     *
     * 注意：绑定的邮箱必须和之前申请验证码的邮箱相同
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param ticket
     * @param email
     * @param verifyCode 邮箱收到的验证码
     * @param callBack
     */
    public void SDKBindEmailConfirm(String aesKey16, String ivParameter,String aesKey16byRSA,String ticket,String email,String verifyCode, NetCallBack callBack){

        try {
            String url = addDomainName() + "/mojo";

            BindEmailConfirm bindEmail = new BindEmailConfirm();
            bindEmail.setClientID(mClientID);
            bindEmail.setTicket(ticket);
            bindEmail.setEmail(email);
            bindEmail.setVerifyCode(verifyCode);
            String jsonData = GsonUtils.tojsonString(bindEmail);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     *
     * 申请通过邮箱验证码重置密码
     *
     * 注意：
     * 1.用户提供的是自定义账号，但系统会往用户账号的绑定邮箱内发送验证码邮件
     * 2.返回结果中会包含用户邮箱信息，应该进行部分信息屏蔽，提示ma******12@gmail.com此类信息
     * 3.验证码有效期20分钟
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param account
     * @param callBack
     */
    public void SDKRequestResetPwd(String aesKey16, String ivParameter,String aesKey16byRSA,String account, NetCallBack callBack){

        try {
            String url = addDomainName() + "/rush";

            RequestResetPwd resetPwd = new RequestResetPwd();
            resetPwd.setClientID(mClientID);
            resetPwd.setAccount(account);
            String jsonData = GsonUtils.tojsonString(resetPwd);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过邮箱验证码重置密码确认接口
     *
     * 注意：
     * 用户提供的密码必须进行一致性验证，保证两次相同，且second是加密的结果，加密格式同注册接口
     *
     * @param aesKey16
     * @param aesKey16byRSA
     * @param account 用户的自定义账号
     * @param newSecond 新密码
     * @param verifyCode 邮箱收到的验证码
     * @param callBack
     */
    public void SDKResetPwdByVerifycode(String aesKey16, String ivParameter,String aesKey16byRSA,String account,String verifyCode,String newSecond, NetCallBack callBack){

        try {
            String url = addDomainName() + "/swift";

            ResetPwdByEmail resetPwdByEmail = new ResetPwdByEmail();
            resetPwdByEmail.setClientID(mClientID);
            resetPwdByEmail.setAccount(account);
            resetPwdByEmail.setVerifycode(verifyCode);
            resetPwdByEmail.setNewsecond(SHA1Utils.sha1UpperCase(newSecond));
            String jsonData = GsonUtils.tojsonString(resetPwdByEmail);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
            NetClient.getInstance().clientPost(url, data, headerMap,callBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 客户端上传报错日志接口
     *
     * @param aesKey16
     * @param aesKey16byRSA
     *  ticket 有则传，这样可以跟踪到单个用户，没有传空
     * @param title 报错日志标题
     * @param content 报错日志内容
     * @param callBack
     */
    public void SDKUploadLog(Context context,String aesKey16,String ivParameter, String aesKey16byRSA,String title,String content, NetCallBack callBack){

        try {
            String url = addDomainName() + "/crash";

            UploadLog uploadLog = new UploadLog();
            uploadLog.setClientID(mClientID);
            if (SDKManager.getInstance() != null && SDKManager.getInstance().getUser() != null && StringUtils.isNotBlank(SDKManager.getInstance().getUser().getTicket())){
                uploadLog.setTicket(SDKManager.getInstance().getUser().getTicket()); //当前用户的ticket
            }
            uploadLog.setPackageName(context.getPackageName());
            uploadLog.setCrashTitle(title);
            uploadLog.setCrashContent(content);
            uploadLog.setDeviceID(identifier);
            String jsonData = GsonUtils.tojsonString(uploadLog);

            String encryptJsonData = AESUtils.encrypt(jsonData, aesKey16,ivParameter);
            Map<String, String> data = new TreeMap<>();
            data.put("asong", encryptJsonData);

            Map<String, String> headerMap = new TreeMap<>();
            headerMap.put("uniqueid",identifier);
            headerMap.put("rak",aesKey16byRSA);
            headerMap.put("siv",ivParameter);
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

    private class GuestBindThird extends Bean implements Serializable{

        private String oauthid;
        private String oauthsource;
        private String newoauthId;
        private String newoauthSource;

        public void setOauthid(String oauthid) {
            this.oauthid = oauthid;
        }

        public void setOauthsource(String oauthsource) {
            this.oauthsource = oauthsource;
        }

        public void setNewoauthId(String newoauthId) {
            this.newoauthId = newoauthId;
        }

        public void setNewoauthSource(String newoauthSource) {
            this.newoauthSource = newoauthSource;
        }
    }

    private class BindEmail extends Bean implements Serializable{

        private String ticket;
        private String email;

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    private class BindEmailConfirm extends Bean implements Serializable{

        private String ticket;
        private String email;
        private String verifyCode;

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }
    }

    private class RequestResetPwd extends Bean implements Serializable{

        private String account;

        public void setAccount(String account) {
            this.account = account;
        }
    }

    private class ResetPwdByEmail extends Bean implements Serializable{

        private String account;
        private String verifycode;
        private String newsecond;

        public void setAccount(String account) {
            this.account = account;
        }

        public void setVerifycode(String verifycode) {
            this.verifycode = verifycode;
        }

        public void setNewsecond(String newsecond) {
            this.newsecond = newsecond;
        }
    }

    private class UploadLog extends Bean implements Serializable{

        private String ticket;
        private String crashTitle;
        private String crashContent;
        private String packageName;
        private String deviceID;

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public void setCrashTitle(String crashTitle) {
            this.crashTitle = crashTitle;
        }

        public void setCrashContent(String crashContent) {
            this.crashContent = crashContent;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }
    }



}
