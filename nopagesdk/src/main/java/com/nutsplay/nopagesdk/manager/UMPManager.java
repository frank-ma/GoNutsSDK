package com.nutsplay.nopagesdk.manager;

import android.app.Activity;
import android.util.Log;

import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

/**
 * Created by frankma on 2020/7/8 10:55 AM
 * Email: frankma9103@gmail.com
 * Desc:  https://developers.google.com/admob/android/privacy?hl=zh-cn
 * 目的：就是在加载广告之前，给用户发一个表单，征求用户同意
 * 为了满足欧盟《电子隐私指令》和《一般数据保护条例》(GDPR) 的要求
 */
public class UMPManager {

    private static UMPManager INSTANCE;
    private static String TAG = "UMPManager";

    public static UMPManager getInstance() {
        if (INSTANCE == null) {
            synchronized (UMPManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UMPManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 请求提供用户意见征求信息
     * @param activity
     */
    public void requestConsentInfoUpdate(Activity activity){
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity)
                .addTestDeviceHashedId("F40EEA20C4680FC7EB36C9D00E711D2E")
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .build();

        // Set tag for under age of consent. false means users are not under age of consent.
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setConsentDebugSettings(debugSettings)
                .setTagForUnderAgeOfConsent(false)
                .build();

        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(activity);
        consentInformation.requestConsentInfoUpdate(
                activity, params,
                (ConsentInformation.OnConsentInfoUpdateSuccessListener) () -> {
                    // 加载和显示用户意见表单
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                            activity,
                            (ConsentForm.OnConsentFormDismissedListener) loadAndShowError -> {
                                if (loadAndShowError != null) {
                                    // Consent gathering failed.
                                    Log.w(TAG, String.format("%s: %s",
                                            loadAndShowError.getErrorCode(),
                                            loadAndShowError.getMessage()));
                                }

                                // Consent has been gathered.
                                if (consentInformation.canRequestAds()) {
                                    initializeMobileAdsSdk();
                                }
                            }
                    );
                },
                (ConsentInformation.OnConsentInfoUpdateFailureListener) requestConsentError -> {
                    // Consent gathering failed.
                    Log.w(TAG, String.format("%s: %s", requestConsentError.getErrorCode(), requestConsentError.getMessage()));
                });
    }

    /**
     * 初始化Mobile广告SDK
     */
    private void initializeMobileAdsSdk() {
//

        // TODO: 请求一个广告
        // InterstitialAd.load(...);
    }


}
