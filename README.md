# NoNutsSDK
新版海外版本，有两套UI，可以根据初始化参数切换：一套是有坚果标志的，一套是没有坚果标志的

# 前言
本指导手册仅适用于坚果游戏SDK-海外无logoSDK(带UI) Android版本，如要对接其他版本请联系我们获取
QQ：1393981975


# 一、准备工作
## 1.导入sdk libs包支持（重要）
Android Studio用户：
> 在你项目的主工程下libs文件夹中放入sdk_*.aar
注意：
 1.如果app目录下没有libs文件夹，需要自己创建
 2.工程目录需要切换到 Project
如图所示：
![](https://www.showdoc.cc/server/api/common/visitfile/sign/b23237fefc7526bbf0acf0db2a75d662?showdoc=.jpg)

在app的build.gradle文件下添加这几处内容，如下图所示：

![](https://www.showdoc.cc/server/api/common/visitfile/sign/a5e56e6a8057b983e00b03fd83ca3bd8?showdoc=.jpg)


    repositories {
        flatDir {
            dirs 'libs'
        }
    }

    dependencies {

    //sdk用到的库
    implementation(name: 'sdk_v1.5', ext: 'aar')
    implementation 'com.android.billingclient:billing:2.2.1'
    implementation 'org.greenrobot:greendao:3.2.2'
    implementation 'net.zetetic:android-database-sqlcipher:3.5.7@aar'
	//Facebook
    implementation 'com.facebook.android:facebook-android-sdk:6.2.0'
    //google登录
    implementation 'com.google.android.gms:play-services-auth:18.0.0'
    }


## 2.AndroidManifest.xml文件配置（重要）
1.2.1申请相应的权限信息：
```html
    <uses-permission android:name="android.permission.INTERNET"/>
    <!-- google支付权限 -->
    <uses-permission android:name="com.android.vending.BILLING" />
    <!-- bugly -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <!--appsflyer-->
    <uses-permission android:name="com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE"/>
    <!--appsflyer Optional -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

```
1.2.2注册几个Activity
```html
 <!--添加这几个Activity-->
<activity android:name="com.nutsplay.nopagesdk.ui.PayWebActivity"
			android:theme="@android:style/Theme.Translucent.NoTitleBar"	android:configChanges="orientation|screenLayout|screenSize|layoutDirection|keyboardHidden|keyboard"/>
<activity android:name="com.nutsplay.nopagesdk.ui.FBLoginActivity"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"
 android:configChanges="orientation|screenLayout|screenSize|layoutDirection|keyboardHidden|keyboard"/>
<activity android:name="com.nutsplay.nopagesdk.ui.GoogleLoginActivity"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"        android:configChanges="orientation|screenLayout|screenSize|layoutDirection|keyboardHidden|keyboard"/>
<!--添加这几个Activity-->

   <!-- facebook登录相关 -->
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id" />
        <activity android:name="com.facebook.FacebookActivity"
            android:configChanges= "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
            android:label="@string/app_name" />
        <activity android:name="com.facebook.CustomTabActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="@string/fb_login_protocol_scheme" />
            </intent-filter>
        </activity>
        <!-- facebook相关 -->

        <!--appsflyer-->
        <receiver android:name="com.appsflyer.MultipleInstallBroadcastReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="com.android.vending.INSTALL_REFERRER" />
            </intent-filter>
        </receiver>

```
#####FacebookID
@string/facebook_app_id和@string/fb_login_protocol_scheme的值需要在values文件夹下的string.xml文件中新增两条

```
    <string name="facebook_app_id">这个值需要去Facebook后台创建，我方创建好后发给你</string>
    <string name="fb_login_protocol_scheme">这个值需要去Facebook后台创建，我方创建好后发给你</string>

```
1.2.3使用SDK的SDKApplication或者用你自己的application继承自SDK的SDKApplication

    com.nutsplay.nopagesdk.kernel.SDKApplication

如图所示
![](https://www.showdoc.cc/server/api/common/visitfile/sign/9aeb3a3c014ff77f696f68323cd1f5aa?showdoc=.jpg)

# 二、SDK 的调用


> **  初始化sdk（重要：进行其他操作前，先要保证初始化成功）**


```java
/**
     * 初始化SDK
     */
    private void init() {

        //传入配置参数
		InitParameter initParameter = new InitParameter();
        initParameter.setClientId("clientId");
        initParameter.setAppsflyerId("appsflyerId");
        initParameter.setBuglyId("buglyId");
        initParameter.setLanguage("en_us");
        initParameter.setDebug(true);
		initParameter.setUIVersion(0);//有两套UI，默认是通用UI版本     0:通用UI（Poly那套UI）    1：侵权游戏UI
		initParameter.setShowUserAgreement(true);//是否首次安装的时候显示用户协议，默认为true，如果CP想使用自己的用户协议窗口，可以置为false

		 //参数分别为：上下文(当前Activity)，初始化参数对象，回调监听
        SDK.getInstance().initSDK(this, initParameter, new InitCallBack() {
            @Override
            public void onSuccess(@Nullable User user) {
                //showLog("初始化成功");
            }

            @Override
            public void onFailure(int code,String errorMsg) {
               // showLog("初始化失败：" + errorMsg);
            }
        });
```


------------
> 切换SDK语言

```java
/**
     * zh_cn, 中文
     * zh_hk, 中文
     * en_us, 英文
     * th_th, 泰语
     * vi_vn, 越语
     * ar_ar，阿拉伯语
     * kr，韩语
     * fr，法语
     * br，葡萄牙语
     * deu，德
     * sp，西班牙
     * it，意大利语
     * jp，日语
     * idn，印度尼西亚语
     * by:俄语
     */

 //参数分别为：语言（String）
SDK.getInstance().sdkUpdateLanguage("kr");
```

------------

> 登录接口

```java
 //参数分别为：上下文(当前Activity)，回调监听
  SDK.getInstance().sdkLogin(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
               // showLog("登录成功：" + user.toString());
            }

            @Override
            public void onFailure(int code,String errorMsg) {
               // showLog("登录失败：" + errorMsg);
            }
			@Override
            public void onCancel() {
               // showLog("登录取消");
            }
        });
```

------------


> 游客自动登录接口（如果你的需求是玩家进游戏默认使用游客一键自动登录，那么你可以使用这个接口，该接口不用调用初始化接口，SDK会直接初始化完成后会默认使用游客账号登录）

```java
//传入配置参数
 	InitParameter initParameter = new InitParameter();
        initParameter.setClientId("clientId");
        initParameter.setAppsflyerId("appsflyerId");
        initParameter.setBuglyId("buglyId");
        initParameter.setLanguage("en_us");//设置SDK语言
        initParameter.setDebug(true);//设置调试模式
        initParameter.setHasUI(true);
		initParameter.setUIVersion(0);
		initParameter.setShowUserAgreement(true);//是否显示用户协议，默认为true，如果CP想使用自己的用户协议窗口，可以置为false

 //参数分别为：上下文(当前Activity)，初始化参数对象，回调监听
        SDK.getInstance().sdkDefaultLogin(this,initParameter,new LoginCallBack(){

            @Override
            public void onSuccess(User user) {
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
               // showLog("默认登录成功："+user.toString());
            }

            @Override
            public void onFailure(int code,String msg) {
              //  showLog("默认登录失败：" + msg);

            }
			@Override
            public void onCancel() {
             //   showLog("默认登录取消");
            }
        });
```

------------

> 注销接口

```java
 //参数分别为：上下文(当前Activity)，回调监听
    SDK.getInstance().sdkLogout(this,new LogOutCallBack() {
            @Override
            public void onSuccess() {
              //  showLog("注销成功");
            }

            @Override
            public void onFailure(int code,String msg) {
              //  showLog("注销失败：" + msg);
            }
        });
```
------------
> 切换账号接口

```java
 //参数分别为：上下文(当前Activity)，回调监听
    SDK.getInstance().sdkSwitchAccount(this, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if (user == null) return;
                //ticket传给游戏服务器做登录校验
                String ticket = user.getTicket();
               // showLog("切换账号成功：" + user.toString());
            }

            @Override
            public void onFailure(int code,String errorMsg) {
              //  showLog("切换账号失败：" + errorMsg);
            }
			@Override
            public void onCancel() {
             //   showLog("切换账号取消");
            }
        });
```

------------
> 创建角色追踪

```java
    //参数分别为：上下文(当前Activity)，服务器ID，角色ID，角色名
	SDK.getInstance().sdkCreateRoleTracking(this,"serverid","roleid","roleName");
```

------------

> 查询商品的本地化价格（Google支付）

`（根据用户登录的Google Play账号绑定的国家或地区返回相应的当地货币价格，如果用户Google Play账号未绑定国家或地区，则返回的是VPN代理的国家或地区，测试的时候使用一个未绑定国家和地区的Google账号登录Google Play，然后在切换VPN节点后，再清除下Google Play的缓存然后重新登录下Google Play，或者直接清除Google Play的数据，因为Google Play会有缓存，不清缓存的话，拿到的还是原来的价格信息） `
拿到的价格格式如下：
JP¥230
JP¥1,140
HK$15.00
HK$39.90
PHP 100.00
PHP 270.00
......

```java
			List<String> skuList = new ArrayList<>();
            skuList.add("com.nutsplay.iap.item1001");//换成你自己项目的商品id
			skuList.add("com.nutsplay.iap.item1002");
			......
       //第三个参数为商品类型：SDKConstant.INAPP,消耗型商品  SDKConstant.SUBS为订阅商品
        SDK.getInstance().sdkQuerySkuLocalPrice(this, skuList,SDKConstant.INAPP, new SDKGetSkuDetailsCallback() {
            @Override
            public void onSuccess(List<SkuDetails> skuDetails) {
               // showLog("查询本地价格成功："+skuDetails.size());
                if (skuDetails.size()==0)return;
                for (SkuDetails sku:skuDetails){
                    String skuId=sku.getSku();
                    String localPrice=sku.getPrice();
                  //  showLog(skuId+"    "+localPrice);
                }
            }

            @Override
            public void onFailure(int code,String msg) {
              //  showLog("查询本地价格失败："+msg);
            }
        });
```

![](https://www.showdoc.cc/server/api/common/visitfile/sign/5b410b2c17d625d09da7f048e435f619?showdoc=.jpg)

------------

> 支付(包含Google支付和第三方支付，由坚果后台控制切换)

```java

        SDK.getInstance().sdkPurchase(this, "服务器Id", "商品Id", "附加参数", new PurchaseCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
               // showLog("支付成功" );
            }

            @Override
            public void onCancel() {
             //   showLog("支付取消");
            }

            @Override
            public void onFailure(int code,String msg) {
             //   showLog("支付失败：" + msg);
            }
        });
```


------------

>用户中心方法：在用户中心玩家可以绑定邮箱，绑定账号，绑定Facebook

```java
/**
* @params 上下文
*/
	SDK.getInstance().openUserCenter(this);

```


>显示用户协议：CP可以在登录界面放一个常驻按钮，用以显示用户协议

```java
/**
* @params 上下文
*/
	SDK.getInstance().showUserAgreement(this);

```

------------
> 生命周期方法：在Activity对应的生命周期方法中调用SDK的下列方法

```java

       SDK.getInstance().sdkOnRestart(Activity);

		SDK.getInstance().sdkOnDestroy(Activity);


		代码示例：
		@Override
    protected void onRestart() {
        super.onRestart();
        //游戏退到后台，再回到前台时，检查是否有未完成的订单
        SDK.getInstance().sdkOnRestart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SDK.getInstance().sdkOnDestroy(this);
    }

```

# 三、混淆配置
如果要对apk进行混淆，请在混淆文件proguard-rules.pro中添加以下代码：
```java
#DataEye
-libraryjars libs/DataEye*.jar
-keep public class com.dataeye.** {*;}
-keep public interface com.dataeye.** {*;}
-keepattributes SourceFile,LineNumberTable
#appsflyer
-keep class com.appsflyer.** { *; }
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
```
# 四、错误码
|  错误码 | 描述  |
| ------------ | ------------ |
|-2|账户不存在|
|-3|密码错误|
|-4|email已存在|
|-41|email不存在|
|-5|参数错误|
|-6|ticket无效|
|-7|验证失败|
|-8|账户已绑定|
|-9|游戏无效|
|-10|用户冻结|
|-11|账户错误|
|-12|邮箱不匹配|
|-14|邮箱格式异常|
|-15|账户格式异常|
|-16|密码格式异常|
|-17|服务器无效|
|-21|账号已存在|
|-26|商品不存在|
|-29|第三方账号已被占用|
|-32|该账号已经实名制认证|
|111|接口返回数据为空|
|112|接口返回数据异常|
|113|网络异常|
|114|其他错误|
|115|谷歌商店未包含该商品id|
|116|第三方支付失败|
|117|参数错误|
|118|未初始化|
|119|未登录|
|120|初始化失败：玩家拒绝协议|
|121|获取公钥失败：网络错误|
|122|获取公钥失败：接口返回信息为空|
|123|获取公钥失败：接口返回信息格式化错误|
|124|获取公钥失败：其他错误码|
|125|初始化接口失败：网络错误|
|126|初始化接口失败：接口返回信息格式化错误|
|127|初始化接口失败：接口返回信息为空|
|128|初始化接口失败：其他错误码|
|129|初始化接口失败：其他错误码-6|
|130|开发者错误：查不到商品，检查apk是否上传到商店或者商品id是否配置|
|131|fb分享失败|
|132|fb登录失败|
|133|Google登录失败|

# 五、常见问题
#### 1.support库与Androidx库冲突的问题：
![](https://www.showdoc.cc/server/api/common/visitfile/sign/4d6af98babb746da3560c7673e6de0d0?showdoc=.jpg)
对于图中这种support库与Androidx库冲突的问题，编译版本29之后，Google把support库都用Androidx库替代了。SDK里面也使用的是Androidx库，所以你应该：
    * 删除support库的引用，改成引用Androidx的库
        implementation 'androidx.appcompat:appcompat:1.1.0'
    * 编译版本提高到29，目标版本可以设置成28，29都没关系，
    * gradle.properties里面加上两句代码，表示强制使用Androidx:
    android.useAndroidX=true
    android.enableJetifier=true
位置如图所示：
![](https://www.showdoc.cc/server/api/common/visitfile/sign/68350013d1f6e10c6feac800e045f0bc?showdoc=.jpg)

#### 2. W/System.err: java.io.IOException: Cleartext HTTP traffic to **** not permitted
原因：为保证用户数据和设备的安全，Google针对Android P系统的应用，要求默认使用加密连接，
因此在Android P 使用HttpUrlConnection进行http请求会出现以下异常

     W/System.err: java.io.IOException: Cleartext HTTP traffic to **** not permitted

使用OKHttp请求则出现

    java.net.UnknownServiceException: CLEARTEXT communication ** not permitted by network security policy

解决方法：
方法1.在AndroidManifest.xml的application标签中直接添加一个属性即可

    android:usesCleartextTraffic="true"

方法2.http请求都换成https


































