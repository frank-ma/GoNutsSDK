package com.nutsplay.nopagesdk.kernel;

/**
 * Created by frank-ma on 2018/12/28 下午8:33
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKConstant {

    public static final String OpenType = "OpenType";
    public static final String Login = "login";
    public static final String Bind = "bind";
    public static final String android = "android";

    public static final String TYPE_GUEST = "android";
    public static final String TYPE_ACCOUNT = "account";
    public static final String TYPE_FACEBOOK = "facebook";
    public static final String TYPE_GOOGLE = "google";


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
    public static final int result_is_null = 111; //接口返回数据为空
    public static final int orderModel_is_null = 112;//接口返回数据异常
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
    public static final int get_public_key_other_code = 123; //获取公钥接口失败，其他错误码

    public static final int init_net_error = 124; //初始化接口失败，网络错误
    public static final int init_initgoBean_null = 125; //初始化接口失败，接口返回信息格式化失败
    public static final int init_response_null = 126; //初始化接口失败：接口返回信息为空
    public static final int init_other_code = 127; //初始化接口失败，其他错误码
    public static final int init_other_code_6 = 128; //初始化接口失败，-6：ticket无效





}
