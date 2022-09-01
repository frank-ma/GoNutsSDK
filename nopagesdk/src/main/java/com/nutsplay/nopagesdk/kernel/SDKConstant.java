package com.nutsplay.nopagesdk.kernel;

/**
 * Created by frank-ma on 2018/12/28 下午8:33
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKConstant {


    public static final String Login = "login";
    public static final String Bind = "bind";
    public static final String android = "android";

    public static final String TYPE_GUEST = "android";
    public static final String TYPE_ACCOUNT = "account";
    public static final String TYPE_FACEBOOK = "facebook";
    public static final String TYPE_GOOGLE = "google";


    public static final int SUCCESS = 1;                //成功
    public static final int STATUS_ACCOUNT_EXIST = -21;                //账号已存在
    public static final int STATUS_ACCOUNT_DO_NOT_EXIST = -2;         //账号不存在
    public static final int STATUS_PASSWORD_ERROR = -3;               //密码错误
    public static final int STATUS_EMAIL_EXIST = -4;                  //email已存在
    public static final int STATUS_EMAIL_NOT_EXIST = -41;             //email不存在
    public static final int STATUS_PARAMETER_ERROR = -5;              //参数错误
    public static final int STATUS_TICKET_INVALID = -6;               //ticket无效
    public static final int STATUS_VERIFY_INVALID = -7;               //验证失败
    public static final int STATUS_ACCOUNT_BOUND = -8;                //账号已绑定过
    public static final int STATUS_GAME_INVALID = -9;                 //游戏代码错误
    public static final int STATUS_USER_FROZEN = -10;                 //用户被冻结
    public static final int STATUS_ACCOUNT_ERROR = -11;               //账号错误
    public static final int STATUS_EMAIL_NOT_FIXED = -12;             //email未设置
    public static final int STATUS_EMAIL_FORMAT_INVALID = -14;        //email格式错误
    public static final int STATUS_ACCOUNT_FORMAT_INVALID = -15;      //账号格式错误
    public static final int STATUS_PASSWD_FORMAT_INVALID = -16;       //密码格式错误
    public static final int STATUS_SERVER_INVALID = -17;              //服务器代码不正确
    public static final int STATUS_PRODUCTION_DO_NOT_EXIST = -26;     //购买商品未配置
    public static final int STATUS_THIRD_ACCOUNT_USED = -29;          //第三方账号已被占用
    public static final int STATUS_ANTIADDICTIVE_ALREADY_EXIST = -32; //防沉迷资料已存在

    public static final int STATUS_TEMPEMAILBIND_NOT_EXIST = -33;      //临时绑定记录不存在
    public static final int STATUS_TEMPEMAILBIND_CODE_INVALID = -34;    //临时绑定验证码不正确
    public static final int STATUS_TEMPEMAILBIND_EMAIL_NOT_MATCH = -35; //邮箱不一致
    public static final int STATUS_TEMPEMAILBIND_SEND_EMAIL_FAIL = -36; //往邮箱发送邮件失败
    public static final int STATUS_ACCOUNT_NOT_BOUND = -37;             //此ID尚未绑定过自定义账号(用于申请email绑定时)
    public static final int STATUS_TEMPEMAILBIND_ALREADY_EXIST = -38;   //临时绑定记录已存在
    public static final String INAPP = "inapp";
    public static final String SUBS = "subs";
    public static final String share_url = "share_url";
    public static final String openType="open_type";//Facebook页面的打开类型
    public static final int SHARE_PHOTO_REQUEST_CODE = 0x101;


    //Google官方支付错误码
    static int SERVICE_TIMEOUT = -3;
    static int FEATURE_NOT_SUPPORTED = -2;
    static int SERVICE_DISCONNECTED = -1;
    static int OK = 0;
    static int USER_CANCELED = 1;
    static int SERVICE_UNAVAILABLE = 2;
    static int BILLING_UNAVAILABLE = 3;
    static int ITEM_UNAVAILABLE = 4;
    static int DEVELOPER_ERROR = 5;
    static int ERROR = 6;
    static int ITEM_ALREADY_OWNED = 7;
    static int ITEM_NOT_OWNED = 8;
//    12500:"A non-recoverable sign in failure occurred";
//    12501:"Sign in action cancelled";
//    12502:"Sign-in in progress";
//    switch(var0) {
//        case -1:
//            return "SUCCESS_CACHE";
//        case 0:
//            return "SUCCESS";
//        case 1:
//        case 9:
//        case 11:
//        case 12:
//        default:
//            return (new StringBuilder(32)).append("unknown status code: ").append(var0).toString();
//        case 2:
//            return "SERVICE_VERSION_UPDATE_REQUIRED";
//        case 3:
//            return "SERVICE_DISABLED";
//        case 4:
//            return "SIGN_IN_REQUIRED";
//        case 5:
//            return "INVALID_ACCOUNT";
//        case 6:
//            return "RESOLUTION_REQUIRED";
//        case 7:
//            return "NETWORK_ERROR";
//        case 8:
//            return "INTERNAL_ERROR";
//        case 10:
//            return "DEVELOPER_ERROR";
//        case 13:
//            return "ERROR";
//        case 14:
//            return "INTERRUPTED";
//        case 15:
//            return "TIMEOUT";
//        case 16:
//            return "CANCELED";
//        case 17:
//            return "API_NOT_CONNECTED";
//        case 18:
//            return "DEAD_CLIENT";
//    }

    public static final int result_is_null = 111; //接口返回数据为空
    public static final int model_is_null = 112;//接口返回的json数据格式化异常
    public static final int network_error = 113;//网络异常
    public static final int other_error = 114;//其他错误，try catch的异常
    public static final int no_upload_apk = 115; //Google Play does not have the item id
    public static final int third_pay_failed = 116; //第三方支付失败
    public static final int parameter_is_null = 117; //参数错误
    public static final int not_init = 118; //未初始化
    public static final int not_login = 119; //未登录

    //init初始化失败错误码
    public static final int refuse_protocol = 120; //玩家拒绝协议

    public static final int get_public_key_net_error = 121; //获取公钥接口失败，网络错误
    public static final int get_public_key_null = 122;//获取公钥接口失败，接口返回信息格式化失败
    public static final int get_public_key_format_error = 123;//获取公钥接口失败，接口返回信息格式化失败
    public static final int get_public_key_other_code = 124; //获取公钥接口失败，其他错误码

    public static final int init_net_error = 125; //初始化接口失败，网络错误
    public static final int init_initgoBean_null = 126; //初始化接口失败，接口返回信息格式化失败
    public static final int init_response_null = 127; //初始化接口失败：接口返回信息为空
    public static final int init_other_code = 128; //初始化接口失败，其他错误码
    public static final int init_other_code_6 = 129; //初始化接口失败，-6：ticket无效

    //登录失败错误码
    public static int developer_error = 130;//开发者错误：查不到商品，检查是否上传到商店，或者商品id是否配置成功
    public static int fb_share_error = 131;//facebook分享失败
    public static int fb_login_error = 132;//facebook登录失败
    public static int google_login_error = 133;//google登录失败
}
