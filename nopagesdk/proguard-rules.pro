# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



-dontwarn com.squareup.picasso.**
#### -- OkHttp --
-dontwarn com.squareup.okhttp.internal.**
#### -- Apache Commons --
-dontwarn org.apache.commons.logging.**
-ignorewarnings
#-keep class * {
#public private *;
#}
-keep class * extends java.lang.annotation {
    <fields>;
    <methods>;
}

-keepattributes Signature,*Annotation*
-keep public class org.xutils.** {
    public protected *;
}
-keep public interface org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.** {
    public protected *;
}
-keepclassmembers @org.xutils.db.annotation.* class * {*;}
-keepclassmembers @org.xutils.http.annotation.* class * {*;}
#-keepclassmembers class * {
#    @org.xutils.view.annotation.Event <methods>;
#}

-keep public class com.google.gson.**
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}
-keep public class * extends android.app.Activity
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.preference.Preference

-keep public class * extends android.support.v4.app.Fragment

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.DialogFragment
# To maintain custom components names that are used on layouts XML:
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}
-keep public class com.nutspower.nutssdk.bean.** {
 <fields>;
    <methods>;
}
-keep public class com.nutspower.nutssdk.listener.** {
 <fields>;
    <methods>;
}

-keep public class com.nutspower.nutssdk.config.NutsSDK {
 <fields>;
    <methods>;
}
-keep public class com.nutspower.nutssdk.config.NutsSDKEventStatus {
 <fields>;
    <methods>;
}
-keep public class com.nutspower.nutssdk.manager.LoginManger {
 <fields>;
    <methods>;
}
# Maintain enums
-keepclassmembers enum  * {
    <fields>;
    <methods>;
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# Keep the R
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclassmembers class * implements java.io.Serializable {
    <fields>;
    <methods>;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
    public boolean *(android.webkit.WebView,java.lang.String);
}

-keepclassmembers class * extends android.webkit.WebChromeClient {
    public void *(android.webkit.WebView,java.lang.String);
}
-keep @android.support.annotation.Keep class * {
    <fields>;
    <methods>;
}




#google内购
-keep class com.android.vending.billing.**

#greendao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties {*;}

# If you do not use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do not use RxJava:
-dontwarn rx.**

# Bugly相关
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#Appsflyer相关
-keep class com.appsflyer.**{*;}

-keep class com.android.installreferrer.**{*;}
-keep class com.google.android.**{*;}

#gson相关
-keep class com.google.gson.**{*;}
#xutils相关
-keep class org.xutils.**{*;}

#sdk不混淆的类
-keep class com.nutsplay.nopagesdk.beans.**{*;}
-keep class com.nutsplay.nopagesdk.kernel.**{*;}
-keep class com.nutsplay.nopagesdk.callback.**{*;}
-keep class com.nutsplay.nopagesdk.manager.AdjustTraceManager
-keep class com.nutsplay.nopagesdk.manager.AppManager
-keep class com.nutsplay.nopagesdk.manager.GoogleAPI
-keep class com.nutsplay.nopagesdk.manager.InstallManager
-keep class com.nutsplay.nopagesdk.manager.LoginManager
-keep class com.nutsplay.nopagesdk.manager.TrackingManager
-keep class com.nutsplay.nopagesdk.billing.**{*;}
-keep class com.nutsplay.nopagesdk.db.**{*;}
-keep class com.nutsplay.nopagesdk.facebook.**{*;}
-keep class com.nutsplay.nopagesdk.network.**{*;}
-keep class com.nutsplay.nopagesdk.receiver.**{*;}
-keep class com.nutsplay.nopagesdk.ui.**{*;}
-keep class com.nutsplay.nopagesdk.utils.**{*;}
-keep class com.nutsplay.nopagesdk.view.**{*;}

