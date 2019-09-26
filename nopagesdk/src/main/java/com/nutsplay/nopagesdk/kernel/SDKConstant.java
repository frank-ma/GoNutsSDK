package com.nutsplay.nopagesdk.kernel;

/**
 * Created by frank-ma on 2018/12/28 下午8:33
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class SDKConstant {

    public static final String PhoneRegisterFragment = "PhoneRegisterFragment";

    public static final String LoginFragment = "LoginFragment";

    public static final String LoginMainFragment = "LoginMainFragment";

    public static final String ForgetPwdFragment = "ForgetPwdFragment";

    public static final String PhoneBindFragment = "PhoneBindFragment";

    public static final String OpenType = "OpenType";
    public static final String Login = "login";
    public static final String Bind = "bind";
    public static final String android="android";
    public static String AntiAddictionFormatTime = "";//防沉迷显示时间
    public static boolean openAntiAddiction = false;//防沉迷开关
//    public static int realNameState = 0;//实名制状态



    public static final int status_account_exist = -1;
    public static final int status_account_do_not_exist = -2;
    public static final int status_password_error = -3;
    public static final int status_email_exist = -4;
    public static final int status_email_not_exist = -41;
    public static final int status_parameter_error = -5;
    public static final int status_ticket_invalid = -6;
    public static final int status_verify_invalid = -7;
    public static final int status_account_bound = -8;
    public static final int status_game_invalid = -9;
    public static final int status_user_frozen = -10;
    public static final int status_account_error = -11;
    public static final int status_email_not_fixed = -12;
    public static final int status_token_invalid = -13;
    public static final int status_email_format_invalid = -14;
    public static final int status_account_format_invalid = -15;
    public static final int status_passwd_format_invalid = -16;
    public static final int status_server_invalid = -17;

    public static final int status_pay_fail = -21;
    public static final int status_pay_balance_not_enough = -22;
    public static final int status_pay_save_to_balance = -23;
    public static final int status_pay_balance_plus_deposit_not_enough = -24;
    public static final int status_deal_balance_fail = -25;
    public static final int status_production_do_not_exist = -26;
    public static final int status_card_used = -27;
    public static final int status_card_or_pass_invalid = -28;
    public static final int status_account_has_real_name_authentication = -32;
    public static final int status_pay_transaction_not_exist = -210;//充值订单不存在
}
