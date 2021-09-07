//package com.nutsplay.nonutssdk;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import com.applovin.mediation.MaxAd;
//import com.applovin.mediation.MaxAdListener;
//import com.applovin.mediation.MaxAdViewAdListener;
//import com.applovin.mediation.MaxReward;
//import com.applovin.mediation.MaxRewardedAdListener;
//import com.applovin.mediation.ads.MaxAdView;
//import com.applovin.mediation.ads.MaxInterstitialAd;
//import com.applovin.mediation.ads.MaxRewardedAd;
//import com.applovin.sdk.AppLovinSdk;
//import com.applovin.sdk.AppLovinSdkConfiguration;
//import com.nutspower.nutsgamesdk.R;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * APPLovin
// * 广告Demo
// */
//public class AdsActivity extends BaseActivity implements MaxAdListener, MaxRewardedAdListener, MaxAdViewAdListener {
//
//    private MaxInterstitialAd interstitialAd;
//    private MaxRewardedAd rewardedAd;
//    private MaxAdView adView;
//    private int retryAttempt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ads);
//        initAppLovinSDK();
//    }
//
//    /**
//     * 激励广告
//     * @param view
//     */
//    public void rewardedAd(View view) {
//        interstitialAd = new MaxInterstitialAd( "9b1a3630000dd50f", this );
//        interstitialAd.setListener( this );
//
//        // Load the first ad
//        interstitialAd.loadAd();
//    }
//
//    /**
//     * 插页广告
//     * @param view
//     */
//    public void intersAds(View view) {
//        interstitialAd = new MaxInterstitialAd( "9b1a3630000dd50f", this );
//        interstitialAd.setListener( this );
//
//        // Load the first ad
//        interstitialAd.loadAd();
//    }
//
//    /**
//     * Banner广告
//     * @param view
//     */
//    public void bannerAd(View view) {
//        adView = new MaxAdView("9b1a3630000dd50f", this);
//        adView.setListener( this );
//
//        // Stretch to the width of the screen for banners to be fully functional
//        int width = ViewGroup.LayoutParams.MATCH_PARENT;
//
//        // Banner height on phones and tablets is 50 and 90, respectively
//        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );
//
//        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );
//
//        // Set background or background color for banners to be fully functional
//        adView.setBackgroundColor(getResources().getColor(android.R.color.white));
//
//        ViewGroup rootView = findViewById( android.R.id.content );
//        rootView.addView( adView );
//
//        // Load the ad
//        adView.loadAd();
//    }
//
//    /**
//     * 初始化计划AppLovingSDK
//     */
//    private void initAppLovinSDK() {
//        AppLovinSdk.getInstance(this).setMediationProvider("max");
//        AppLovinSdk.getInstance(this).getSettings().setVerboseLogging(true);
//        AppLovinSdk.getInstance(this).getSettings().setMuted(true);//设置静音与否
//        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
//            @Override
//            public void onSdkInitialized(final AppLovinSdkConfiguration configuration){
//                // AppLovin SDK is initialized, start loading ads
//                Log.e("AppLovin",configuration.toString());
//            }
//        });
//    }
//
//    @Override
//    public void onAdLoaded(MaxAd maxAd) {
//        //插页广告加载完成
//        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'
//        // Reset retry attempt
//        retryAttempt = 0;
//
//        //展示广告
//        if (interstitialAd != null) {
//            if (interstitialAd.isReady()){
//                interstitialAd.showAd();
//            }
//        }
//
//        if (rewardedAd != null) {
//            if (rewardedAd.isReady()){
//                rewardedAd.showAd();
//            }
//        }
//    }
//
//    @Override
//    public void onAdLoadFailed(String s, int i) {
//        retryAttempt++;
//        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );
//
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                interstitialAd.loadAd();
//            }
//        }, delayMillis );
//    }
//
//    @Override
//    public void onAdDisplayed(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdHidden(MaxAd maxAd) {
//        // Interstitial ad is hidden. Pre-load the next ad
//        interstitialAd.loadAd();
//    }
//
//    @Override
//    public void onAdClicked(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdDisplayFailed(MaxAd maxAd, int i) {
//        // Interstitial ad failed to display. We recommend loading the next ad
//        interstitialAd.loadAd();
//    }
//
//
//    /**
//     * 激励广告多了三个方法
//     * @param maxAd
//     */
//    @Override
//    public void onRewardedVideoStarted(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onRewardedVideoCompleted(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
//        // Rewarded ad was displayed and user should receive the reward
//    }
//
//    /**
//     * Banner广告多了两个方法
//     * @param maxAd
//     */
//    @Override
//    public void onAdExpanded(MaxAd maxAd) {
//
//    }
//
//    @Override
//    public void onAdCollapsed(MaxAd maxAd) {
//
//    }
//}