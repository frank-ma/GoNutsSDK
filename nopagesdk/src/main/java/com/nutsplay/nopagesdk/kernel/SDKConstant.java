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
//    public static int realNameState = 0;//实名制状态


//    public static final int status_account_exist = -1;
//    public static final int status_account_do_not_exist = -2;
//    public static final int status_password_error = -3;
//    public static final int status_email_exist = -4;
//    public static final int status_email_not_exist = -41;
//    public static final int status_parameter_error = -5;
//    public static final int status_ticket_invalid = -6;
//    public static final int status_verify_invalid = -7;
//    public static final int status_account_bound = -8;
//    public static final int status_game_invalid = -9;
//    public static final int status_user_frozen = -10;
//    public static final int status_account_error = -11;
//    public static final int status_email_not_fixed = -12;
//    public static final int status_token_invalid = -13;
//    public static final int status_email_format_invalid = -14;
//    public static final int status_account_format_invalid = -15;
//    public static final int status_passwd_format_invalid = -16;
//    public static final int status_server_invalid = -17;
//
//    public static final int status_pay_fail = -21;
//    public static final int status_pay_balance_not_enough = -22;
//    public static final int status_pay_save_to_balance = -23;
//    public static final int status_pay_balance_plus_deposit_not_enough = -24;
//    public static final int status_deal_balance_fail = -25;
//    public static final int status_production_do_not_exist = -26;
//    public static final int status_card_used = -27;
//    public static final int status_card_or_pass_invalid = -28;
//    public static final int status_account_has_real_name_authentication = -32;
//    public static final int status_pay_transaction_not_exist = -210;//充值订单不存在


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
}
