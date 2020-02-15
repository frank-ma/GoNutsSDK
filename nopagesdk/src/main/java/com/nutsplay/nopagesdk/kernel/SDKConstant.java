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
    public static final String INAPP = "inapp";
    public static final String SUBS = "subs";
}
