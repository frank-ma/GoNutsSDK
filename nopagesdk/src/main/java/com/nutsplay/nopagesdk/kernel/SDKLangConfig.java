package com.nutsplay.nopagesdk.kernel;


import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutspower.commonlibrary.utils.StringUtils;

import java.util.Properties;


/**
 * Created by jackzpp on 2016/12/2
 * <p>
 * 努力做的更好
 */

public class SDKLangConfig {
    private static SDKLangConfig instance;
    private Properties[] array = new Properties[4];

    public final static SDKLangConfig getInstance() {
        if (instance == null) {
            instance = new SDKLangConfig();
        }
        return instance;
    }

    public final String findMessage(String key) {
//        int value = SDKGameUtils.getLanguage(SDKKernel.getInstance().getLanguage()) - 1;
        int value = SDKGameUtils.getLanguage(SDKManager.getInstance().getInitParameter().getLanguage()) - 1;
        if (value < 0) {
            value = 0;
        }
        String result = array[value].getProperty(key, "");
        if (StringUtils.isBlank(result)){
            return array[1].getProperty(key, key);
        }
        return result;
    }

    /**
     * zh_cn:中文
     * th：泰语
     * vi：越语
     * ar:阿拉伯语
     * 15种语言
     */
    public SDKLangConfig() {

        Properties zh_CN = new Properties();
        Properties th = new Properties();//泰
        Properties vi = new Properties();//越南语
        Properties ar = new Properties();//阿拉伯
        Properties en = new Properties();//英
        Properties zh_HK = new Properties();//繁体中文
        Properties ko = new Properties();//朝鲜语

        Properties fr = new Properties();//法语
        Properties pt = new Properties();//葡萄牙语
        Properties de = new Properties();//德
        Properties es = new Properties();//西班牙
        Properties it = new Properties();//意大利语
        Properties ja = new Properties();//日语
        Properties id = new Properties();//印度尼西亚语
        Properties ru = new Properties();//俄语


        array = new Properties[]{zh_CN, en, th, vi, ar, ko, zh_HK, fr, pt, de, es, it, ja, id,ru};




        zh_CN.setProperty("switchAccount", "切换账户");
        vi.setProperty("switchAccount", "Đổi tài khoản");
        th.setProperty("switchAccount", "สลับไอดี");
        ar.setProperty("switchAccount", "تبديل  الحساب");
        en.setProperty("switchAccount", "Switch account");
        ko.setProperty("switchAccount", "계정 전환");
        zh_HK.setProperty("switchAccount", "切換帳號");
        fr.setProperty("switchAccount", "Changer de compte");
        pt.setProperty("switchAccount", "Mudar de conta");
        de.setProperty("switchAccount", "Konto wechseln");
        es.setProperty("switchAccount", "Cambiar cuenta");
        it.setProperty("switchAccount", "Cambia account");
        ja.setProperty("switchAccount", "アカウント変更");
        id.setProperty("switchAccount", "Ganti Akun");
        ru.setProperty("switchAccount", "Сменить аккаунт");



        zh_CN.setProperty("bindSucess", "绑定成功");
        th.setProperty("bindSucess", "ผูกมัดสำเร็จ");
        vi.setProperty("bindSucess", "Cố định thành công");
        ar.setProperty("bindSucess", " ربط  النجاح ");
        en.setProperty("bindSucess", "Binding success");
        ko.setProperty("bindSucess", "연동하기 성공");
        zh_HK.setProperty("bindSucess", "绑定成功");
        fr.setProperty("bindSucess", "Liaison réussie");
        pt.setProperty("bindSucess", "Vínculo com sucesso");
        de.setProperty("bindSucess", "Verbunden");
        es.setProperty("bindSucess", "Vinculado con éxito");
        it.setProperty("bindSucess", "Collegamento riuscito");
        ja.setProperty("bindSucess", "連携成功");
        id.setProperty("bindSucess", "Binding sukses");
        ru.setProperty("bindSucess", "Связывание успешно");


        zh_CN.setProperty("exit", "退出");
        th.setProperty("exit", "ออก");
        vi.setProperty("exit", "Thoát");
        ar.setProperty("exit", "تسجيل الخروج");
        en.setProperty("exit", "Exit");
        ko.setProperty("exit", "나가기");
        zh_HK.setProperty("exit", "退出");
        fr.setProperty("exit", "Sortir");
        pt.setProperty("exit", "Sair");
        de.setProperty("exit", "Verlassen");
        es.setProperty("exit", "Verlassen");
        it.setProperty("exit", "Esci");
        ja.setProperty("exit", "中止");
        id.setProperty("exit", "Keluar");
        ru.setProperty("exit", "Выход");


        zh_CN.setProperty("exitmessage", "您想要退出游吗?");
        th.setProperty("exitmessage", "ต้องการออกเกมหรือไม่?");
        vi.setProperty("exitmessage", "Muốn thoát game??");
        ar.setProperty("exitmessage", "هل تود الخروج من اللعبة؟");
        en.setProperty("exitmessage", "Do you want to quit the game??");
        ko.setProperty("exitmessage", "게임을 종료하시겠습니까?");
        zh_HK.setProperty("exitmessage", "您想要退出遊戲嗎？");
        fr.setProperty("exitmessage", "Diamante");
        pt.setProperty("exitmessage", "Monatskarte");
        de.setProperty("exitmessage", "Diamant");
        es.setProperty("exitmessage", "Diamante");
        it.setProperty("exitmessage", "Diamante");
        ja.setProperty("exitmessage", "ダイヤモンド");
        id.setProperty("exitmessage", "Berlian");
        ru.setProperty("exitmessage", "Вы хотите выйти из тура?");


        zh_CN.setProperty("cancel", "取消");
        th.setProperty("cancel", "ยกเลิก");
        vi.setProperty("cancel", "Hủy");
        ar.setProperty("cancel", "تراجع");
        en.setProperty("cancel", "Cancel");
        ko.setProperty("cancel", "취소");
        zh_HK.setProperty("cancel", "取消");
        fr.setProperty("cancel", "Annuler");
        pt.setProperty("cancel", "Cancelar");
        de.setProperty("cancel", "Abbrechen");
        es.setProperty("cancel", "Cancelar");
        it.setProperty("cancel", "Cancella");
        ja.setProperty("cancel", "キャンセル");
        id.setProperty("cancel", "Batal");
        ru.setProperty("cancel", "Отмена");


        zh_CN.setProperty("50", "正在绑定...");
        th.setProperty("50", "กำลังผูกมัด");
        vi.setProperty("50", "Đang cố định…");
        ar.setProperty("50", "بصدد التوثيق");
        en.setProperty("50", "Binding...");
        ko.setProperty("50", "연동하기 중...");
        zh_HK.setProperty("50", "正在綁定…");
        fr.setProperty("50", "Liaison...");
        pt.setProperty("50", "A vincular...");
        de.setProperty("50", "Verbinden …");
        es.setProperty("50", "Vinculando…");
        it.setProperty("50", "Collegamento in corso...");
        ja.setProperty("50", "連携中…");
        id.setProperty("50", "Binding...");
        ru.setProperty("50", "Связывание ...");

        zh_CN.setProperty("51", "账户已经被绑定");
        th.setProperty("51", "บัญชีผูกติดแล้ว");
        vi.setProperty("51", "Tài khoản đã được cố định");
        ar.setProperty("51", "الحساب موثق");
        en.setProperty("51", "The account has been bound");
        ko.setProperty("51", "계정이 연동되었습니다");
        zh_HK.setProperty("51", "帳號已被使用過");
        fr.setProperty("51", "Le compte a été lié");
        pt.setProperty("51", "A conta foi vinculada.");
        de.setProperty("51", "Konto ist verbunden");
        es.setProperty("51", "La cuenta ha sido vinculada");
        it.setProperty("51", "L'account è stato collegato");
        ja.setProperty("51", "このアカウントは既に連携されています");
        id.setProperty("51", "Akun sudah dibinding");
        ru.setProperty("51", "Аккаунт привязан");



        zh_CN.setProperty("nutsplay_viewstring_ResetPassword", "找回密码");
        th.setProperty("nutsplay_viewstring_ResetPassword", "ลืมรหัสผ่าน");
        vi.setProperty("nutsplay_viewstring_ResetPassword", "Tìm lại mật khẩu>");
        ar.setProperty("nutsplay_viewstring_ResetPassword", "نسيت كلمة المرور");
        en.setProperty("nutsplay_viewstring_ResetPassword", "Forgot password");
        ko.setProperty("nutsplay_viewstring_ResetPassword", "비밀 번호 찾기");
        zh_HK.setProperty("nutsplay_viewstring_ResetPassword", "忘記密碼");
        fr.setProperty("nutsplay_viewstring_ResetPassword", "Mot de passe oublié");
        pt.setProperty("nutsplay_viewstring_ResetPassword", "Esqueci a senha");
        de.setProperty("nutsplay_viewstring_ResetPassword", "Passwort vergessen");
        es.setProperty("nutsplay_viewstring_ResetPassword", "Olvidé mi contraseña");
        it.setProperty("nutsplay_viewstring_ResetPassword", "Password dimenticata");
        ja.setProperty("nutsplay_viewstring_ResetPassword", "パスワードを忘れた");
        id.setProperty("nutsplay_viewstring_ResetPassword", "Lupa Password");
        ru.setProperty("nutsplay_viewstring_ResetPassword", "Забыли пароль");

        zh_CN.setProperty("nutsplay_viewstring_confirm", "确定");
        th.setProperty("nutsplay_viewstring_confirm", "ยืนยัน");
        vi.setProperty("nutsplay_viewstring_confirm", "Xác nhận");
        ar.setProperty("nutsplay_viewstring_confirm", "تحديد");
        en.setProperty("nutsplay_viewstring_confirm", "Determine");
        ko.setProperty("nutsplay_viewstring_confirm", "확정");
        zh_HK.setProperty("nutsplay_viewstring_confirm", "確定");
        fr.setProperty("nutsplay_viewstring_confirm", "Confirmer");
        pt.setProperty("nutsplay_viewstring_confirm", "Confirmar");
        de.setProperty("nutsplay_viewstring_confirm", "Bestätigen");
        es.setProperty("nutsplay_viewstring_confirm", "Confirmar");
        it.setProperty("nutsplay_viewstring_confirm", "Conferma");
        ja.setProperty("nutsplay_viewstring_confirm", "確認");
        id.setProperty("nutsplay_viewstring_confirm", "Yakin");
        ru.setProperty("nutsplay_viewstring_confirm", "Определить");


        zh_CN.setProperty("nutsplay_viewstring_usercenter", "用户中心");
        th.setProperty("nutsplay_viewstring_usercenter", "ศูนย์ผู้เล่น");
        vi.setProperty("nutsplay_viewstring_usercenter", "Use");
        ar.setProperty("nutsplay_viewstring_usercenter", "مركز المستخدم");
        en.setProperty("nutsplay_viewstring_usercenter", "User Center");
        ko.setProperty("nutsplay_viewstring_usercenter", "사용자 센터");
        zh_HK.setProperty("nutsplay_viewstring_usercenter", "會員中心");
        fr.setProperty("nutsplay_viewstring_usercenter", "Centre d’Utilisateur");
        pt.setProperty("nutsplay_viewstring_usercenter", "Centro de Usuário");
        de.setProperty("nutsplay_viewstring_usercenter", "Nutzerzentrum");
        es.setProperty("nutsplay_viewstring_usercenter", "Centro de usuario");
        it.setProperty("nutsplay_viewstring_usercenter", "Centro utente");
        ja.setProperty("nutsplay_viewstring_usercenter", "ユーザーセンター");
        id.setProperty("nutsplay_viewstring_usercenter", "Layanan");
        ru.setProperty("nutsplay_viewstring_usercenter", "Центр пользователя");


        zh_CN.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登录中...");
        vi.setProperty("nutsplay_viewstring_signining_fb", "Facebook Đang đăng nhập...:");
        th.setProperty("nutsplay_viewstring_signining_fb", "Facebook กำลังเข้าสู่ระบบ...");
        ar.setProperty("nutsplay_viewstring_signining_fb", "تسجيل الدخول بالفايسبوك ...");
        en.setProperty("nutsplay_viewstring_signining_fb", "nutsplay_viewstring_usercenter");
        ko.setProperty("nutsplay_viewstring_signining_fb", "Facebook 로그인 중...");
        zh_HK.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登入中...");
        fr.setProperty("nutsplay_viewstring_signining_fb", "Connexion Facebook...");
        pt.setProperty("nutsplay_viewstring_signining_fb", "Acessar com Facebook...");
        de.setProperty("nutsplay_viewstring_signining_fb", "Facebook-Anm.");
        es.setProperty("nutsplay_viewstring_signining_fb", "Accediendo con Facebook...");
        it.setProperty("nutsplay_viewstring_signining_fb", "Accedendo con Facebook...");
        ja.setProperty("nutsplay_viewstring_signining_fb", "Facebookログイン中…");
        id.setProperty("nutsplay_viewstring_signining_fb", "Facebook Login...");
        ru.setProperty("nutsplay_viewstring_signining_fb", "Логин в фейсбук...");

        zh_CN.setProperty("tourist_signin_tips", "提示");
        vi.setProperty("tourist_signin_tips", "Mẹo");
        th.setProperty("tourist_signin_tips", "เคล็ดลับ");
        ar.setProperty("tourist_signin_tips", "نصائح");
        en.setProperty("tourist_signin_tips", "Tips");
        ko.setProperty("tourist_signin_tips", "팁");
        zh_HK.setProperty("tourist_signin_tips", "提示");
        fr.setProperty("tourist_signin_tips", "Pourboires");
        pt.setProperty("tourist_signin_tips", "Dicas");
        de.setProperty("tourist_signin_tips", "Tipps");
        es.setProperty("tourist_signin_tips", "Consejos");
        it.setProperty("tourist_signin_tips", "Consigli");
        ja.setProperty("tourist_signin_tips", "ヒント");
        id.setProperty("tourist_signin_tips", "Kiat");
        ru.setProperty("tourist_signin_tips", "подсказки");

        zh_CN.setProperty("tourist_signin_alert", "游客账号仅供体验，为了您的账户安全，请及时绑定一个新账号");
        vi.setProperty("tourist_signin_alert", "Vì an toàn, hãy cố định tài khoản");
        th.setProperty("tourist_signin_alert", "บัญชีนี้เป็นบัญชีที่ใช้ร่วมกัน เพื่อความปลอดภัยของบัญชีท่าน กรุณาทำการผูกมัดในทันที");
        ar.setProperty("tourist_signin_alert", "حسابك هنا معد فقط للاستخدام في اللعبة، ينصح بتوثيق حسابك لتأمينه وحمايته");
        en.setProperty("tourist_signin_alert", "The guest account is only for experience, for your account security, please bind a new account.");
        ko.setProperty("tourist_signin_alert", "이 계정을 안전하게 사용하기를 위하여 등록한 계정번호를 연동해주시기 바랍니다");
        zh_HK.setProperty("tourist_signin_alert", "遊客帳號僅供体验，為了您的帳號安全，請及時綁定註冊帳號");
        fr.setProperty("tourist_signin_alert", "Le compte de jeu est destiné uniquement à l’utilisation, pour la sécurité de votre compte, veuillez lier ce compte");
        pt.setProperty("tourist_signin_alert", "A conta do jogo se destina apenas para utilização. Por motivo de segurança deve vincular a conta");
        de.setProperty("tourist_signin_alert", "Das Spielkonto dient nur deiner Sicherheit, bitte registriertes Konto verbinden.");
        es.setProperty("tourist_signin_alert", "La cuenta del juego es solo para uso. Por tu seguridad, vincula tu cuenta.");
        it.setProperty("tourist_signin_alert", "L'account del gioco è solo per uso, per maggiore sicurezza collega l'account registrato");
        ja.setProperty("tourist_signin_alert", "ゲームアカウントは使用することしかできません。アカウントの安全のため、早めに登録アカウントに連携してください");
        id.setProperty("tourist_signin_alert", "Untuk keamanan karakter, segera binding akun registrasi.");
        ru.setProperty("tourist_signin_alert", "Гостевая учетная запись предназначена только для опыта, для безопасности вашей учетной записи, пожалуйста, привяжите новую учетную запись.");


        zh_CN.setProperty("viewstring_enter_game", "进入游戏");
        vi.setProperty("viewstring_enter_game", "Đăng nhập");
        th.setProperty("viewstring_enter_game", "เข้าเกม");
        ar.setProperty("viewstring_enter_game", "دخول اللعبة");
        en.setProperty("viewstring_enter_game", "Enter the game");
        ko.setProperty("viewstring_enter_game", "게임을 시작하기");
        zh_HK.setProperty("viewstring_enter_game", "進入遊戲");
        fr.setProperty("viewstring_enter_game", "Entrer dans le jeu");
        pt.setProperty("viewstring_enter_game", "Entrar no jogo");
        de.setProperty("viewstring_enter_game", "Spiel betreten");
        es.setProperty("viewstring_enter_game", "Entrar al juego");
        it.setProperty("viewstring_enter_game", "Entra");
        ja.setProperty("viewstring_enter_game", "ゲームに入る");
        id.setProperty("viewstring_enter_game", "Masuk game");
        ru.setProperty("viewstring_enter_game", "Войти в игру");

        zh_CN.setProperty("viewstring_Bind_Account", "绑定账号");
        vi.setProperty("viewstring_Bind_Account", "Cố định TK");
        th.setProperty("viewstring_Bind_Account", "ผูกมัดบัญชี");
        ar.setProperty("viewstring_Bind_Account", "حسابات موثقة");
        en.setProperty("viewstring_Bind_Account", "Bind accounts");
        ko.setProperty("viewstring_Bind_Account", "계정번호를 연동하기");
        zh_HK.setProperty("viewstring_Bind_Account", "綁定帳號");
        fr.setProperty("viewstring_Bind_Account", "Lier le compte");
        pt.setProperty("viewstring_Bind_Account", "Vincular conta");
        de.setProperty("viewstring_Bind_Account", "Konto verbinden");
        es.setProperty("viewstring_Bind_Account", "Vincular cuenta");
        it.setProperty("viewstring_Bind_Account", "Collega account");
        ja.setProperty("viewstring_Bind_Account", "アカウントに連携する");
        id.setProperty("viewstring_Bind_Account", "Binding Akun");
        ru.setProperty("viewstring_Bind_Account", "Привязать аккаунты");

        zh_CN.setProperty("nutsplay_viewstring_account_id", "账号ID:");
        vi.setProperty("nutsplay_viewstring_account_id", "TK ID:");
        th.setProperty("nutsplay_viewstring_account_id", "บัญชีID:");
        ar.setProperty("nutsplay_viewstring_account_id", "اسم المستخدم");
        en.setProperty("nutsplay_viewstring_account_id", "Account ID:");
        ko.setProperty("nutsplay_viewstring_account_id", "아이디:");
        zh_HK.setProperty("nutsplay_viewstring_account_id", "帳號ID：");
        fr.setProperty("nutsplay_viewstring_account_id", "ID de Compte :");
        pt.setProperty("nutsplay_viewstring_account_id", "ID de Conta:");
        de.setProperty("nutsplay_viewstring_account_id", "Konto-ID:");
        es.setProperty("nutsplay_viewstring_account_id", "ID Cuenta:");
        it.setProperty("nutsplay_viewstring_account_id", "ID Account:");
        ja.setProperty("nutsplay_viewstring_account_id", "アカウントID：");
        id.setProperty("nutsplay_viewstring_account_id", "Akun ID:");
        ru.setProperty("nutsplay_viewstring_account_id", "ID аккаунта:");


        zh_CN.setProperty("nutsplay_viewstring_bindemail", "绑定邮箱");
        vi.setProperty("nutsplay_viewstring_bindemail", "Cố định Email");
        th.setProperty("nutsplay_viewstring_bindemail", "ผูกมัดอีเมล");
        ar.setProperty("nutsplay_viewstring_bindemail", "توثيق بالبريد");
        en.setProperty("nutsplay_viewstring_bindemail", "Bind Email");
        ko.setProperty("nutsplay_viewstring_bindemail", "이메일 주소를 연동하기");
        zh_HK.setProperty("nutsplay_viewstring_bindemail", "綁定電子信箱");
        fr.setProperty("nutsplay_viewstring_bindemail", "Lier par Email");
        pt.setProperty("nutsplay_viewstring_bindemail", "Vincular Email");
        de.setProperty("nutsplay_viewstring_bindemail", "E-Mail verb.");
        es.setProperty("nutsplay_viewstring_bindemail", "Vincular correo");
        it.setProperty("nutsplay_viewstring_bindemail", "Collega email");
        ja.setProperty("nutsplay_viewstring_bindemail", "メールアドレスに連携する");
        id.setProperty("nutsplay_viewstring_bindemail", "Binding Email");
        ru.setProperty("nutsplay_viewstring_bindemail", "Привязать электронную почту");


        zh_CN.setProperty("nutsplay_viewstring_cscenter", "客服中心");
        vi.setProperty("nutsplay_viewstring_cscenter", "CSKH");
        th.setProperty("nutsplay_viewstring_cscenter", "ศูนย์บริการ");
        ar.setProperty("nutsplay_viewstring_cscenter", "خدمة العملاءا");
        en.setProperty("nutsplay_viewstring_cscenter", "Customer Service");
        ko.setProperty("nutsplay_viewstring_cscenter", "고객 센터");
        zh_HK.setProperty("nutsplay_viewstring_cscenter", "客服中心");
        fr.setProperty("nutsplay_viewstring_cscenter", "Service Clientèle");
        pt.setProperty("nutsplay_viewstring_cscenter", "Apoio ao Cliente");
        de.setProperty("nutsplay_viewstring_cscenter", "Kundendienst");
        es.setProperty("nutsplay_viewstring_cscenter", "Atención al cliente");
        it.setProperty("nutsplay_viewstring_cscenter", "Servizio Clienti");
        ja.setProperty("nutsplay_viewstring_cscenter", "問い合わせ");
        id.setProperty("nutsplay_viewstring_cscenter", "Customer Service");
        ru.setProperty("nutsplay_viewstring_cscenter", "Обслуживание клиентов");


        ar.setProperty("nutsplay_viewstring_account_tips", "اسم المستخدم (6-14)");
        de.setProperty("nutsplay_viewstring_account_tips", "Benutzername (6-14)");
        en.setProperty("nutsplay_viewstring_account_tips", "Username(6-14)");
        es.setProperty("nutsplay_viewstring_account_tips", "Nombre de usuario (6-14)");
        fr.setProperty("nutsplay_viewstring_account_tips", "Nom d'utilisateur (6-14)");
        id.setProperty("nutsplay_viewstring_account_tips", "Nama pengguna (6-14)");
        it.setProperty("nutsplay_viewstring_account_tips", "Nome utente (6-14)");
        ja.setProperty("nutsplay_viewstring_account_tips", "ユーザー名（6-14）");
        ko.setProperty("nutsplay_viewstring_account_tips", "사용자 이름 (6-14)");
        pt.setProperty("nutsplay_viewstring_account_tips", "Nome de usuário (6-14)");
        th.setProperty("nutsplay_viewstring_account_tips", "ชื่อผู้ใช้ (6-14)");
        vi.setProperty("nutsplay_viewstring_account_tips", "Tên người dùng (6-14)");
        zh_CN.setProperty("nutsplay_viewstring_account_tips", "用户名（6-14）");
        zh_HK.setProperty("nutsplay_viewstring_account_tips", "用戶名（6-14）");
        ru.setProperty("nutsplay_viewstring_account_tips", "Имя пользователя (6-14)");


        ar.setProperty("nutsplay_viewstring_password_tips", "كلمه السر");
        de.setProperty("nutsplay_viewstring_password_tips", "Passwort");
        en.setProperty("nutsplay_viewstring_password_tips", "Password");
        es.setProperty("nutsplay_viewstring_password_tips", "Contraseña");
        fr.setProperty("nutsplay_viewstring_password_tips", "Mot de passe");
        id.setProperty("nutsplay_viewstring_password_tips", "Kata sandi");
        it.setProperty("nutsplay_viewstring_password_tips", "Parola d'ordine");
        ja.setProperty("nutsplay_viewstring_password_tips", "パスワード");
        ko.setProperty("nutsplay_viewstring_password_tips", "암호");
        pt.setProperty("nutsplay_viewstring_password_tips", "Senha");
        th.setProperty("nutsplay_viewstring_password_tips", "รหัสผ่าน");
        vi.setProperty("nutsplay_viewstring_password_tips", "Mật khẩu");
        zh_CN.setProperty("nutsplay_viewstring_password_tips", "密码");
        zh_HK.setProperty("nutsplay_viewstring_password_tips", "密碼");
        ru.setProperty("nutsplay_viewstring_password_tips", "пароль");


        ar.setProperty("nuts_email", "البريد الإلكتروني");
        de.setProperty("nuts_email", "Email");
        en.setProperty("nuts_email", "email");
        es.setProperty("nuts_email", "correo electrónico");
        fr.setProperty("nuts_email", "email");
        id.setProperty("nuts_email", "surel");
        it.setProperty("nuts_email", "e-mail");
        ja.setProperty("nuts_email", "Eメール");
        ko.setProperty("nuts_email", "이메일");
        pt.setProperty("nuts_email", "o email");
        th.setProperty("nuts_email", "อีเมล์");
        vi.setProperty("nuts_email", "email");
        zh_CN.setProperty("nuts_email", "电子邮件");
        zh_HK.setProperty("nuts_email", "電子郵件");
        ru.setProperty("nuts_email", "Эл. адрес");

        zh_CN.setProperty("nuts_username_null", "用户名不能为空");
        vi.setProperty("nuts_username_null", "Tên người dùng không thể trống");
        th.setProperty("nuts_username_null", "Username ไม่สามารถปล่อยว่างได้");
        ar.setProperty("nuts_username_null", "اسم المستخدم لا يمكن أن يكون فارغا");
        en.setProperty("nuts_username_null", "Enter the correct account and password");
        ko.setProperty("nuts_username_null", "사용자 아이다를 입력해주세요");
        zh_HK.setProperty("nuts_username_null", "會員名稱不能為空白");
        fr.setProperty("nuts_username_null", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("nuts_username_null", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("nuts_username_null", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        es.setProperty("nuts_username_null", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("nuts_username_null", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("nuts_username_null", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("nuts_username_null", "Akun harus 6-14 karakter(huruf / angka).");
        ru.setProperty("nuts_username_null", "Имя пользователя не может быть пустым");

        zh_CN.setProperty("nuts_service_err", "服务器数据解析错误");
        vi.setProperty("nuts_service_err", "Dữ liệu lỗi, hãy thử lại");
        th.setProperty("nuts_service_err", "ข้อมูลเซิร์ฟเวอร์ผิดพลาด กรุณาลองใหม่้");
        ar.setProperty("nuts_service_err", "خطأ في الاتصال ببيانات السيرفر");
        en.setProperty("nuts_service_err", "Server data error");
        ko.setProperty("nuts_service_err", "서버 데이터 에러");
        zh_HK.setProperty("nuts_service_err", "伺服器數據解析錯誤");
        fr.setProperty("nuts_service_err", "Erreur de données serveur");
        pt.setProperty("nuts_service_err", "Erro no servidor");
        de.setProperty("nuts_service_err", "Serverdatenfehler");
        es.setProperty("nuts_service_err", "Error en el servidor");
        it.setProperty("nuts_service_err", "Errore server");
        ja.setProperty("nuts_service_err", "サーバーデーターエラー");
        id.setProperty("nuts_service_err", "Data server error");
        ru.setProperty("nuts_service_err", "Ошибка данных сервера");

        zh_CN.setProperty("welcom_guest", "欢迎使用游客账户");
        vi.setProperty("welcom_guest", "Dùng tài khoản chơi thử");
        th.setProperty("welcom_guest", "ขอต้อนรับบัญชีผู้เยี่ยมชม");
        ar.setProperty("welcom_guest", "مرحبا بالحساب الزائر");
        en.setProperty("welcom_guest", "Welcome guest account!");
        ko.setProperty("welcom_guest", "비회원 계정을 사용하시는 분들을 환영합니다");
        zh_HK.setProperty("welcom_guest", "歡迎使用遊客帳號");
        fr.setProperty("welcom_guest", "Vous pouvez utiliser le compte invité !");
        pt.setProperty("welcom_guest", "Benvindo a usar a conta de convidado");
        de.setProperty("welcom_guest", "Willkommen beim Gastkonto!");
        es.setProperty("welcom_guest", "Bienvenido a usar la cuenta de invitado");
        it.setProperty("welcom_guest", "Sei libero di utilizzare il nostro account guest");
        ja.setProperty("welcom_guest", "ゲストアカウントへようこそ！");
        id.setProperty("welcom_guest", "Selamat menggunakan akun guest!");
        ru.setProperty("welcom_guest", "Добро пожаловать в гостевой аккаунт");


        zh_CN.setProperty("loginok", "登录成功，我们将会给您带来很棒的游戏体验");
        vi.setProperty("loginok", "Đăng nhập thành công, Game thế giới trò chơi vui nhộn nhất");
        th.setProperty("loginok", "ล็อกอินสำเร็จ Game จะให้ท่านได้พบกับการเล่นเกมที่น่าตื่นตาตื่นใจ");
        ar.setProperty("loginok", "تم تسجيل الدخول بنجاح، سوف تتيح لك Game خوض تجربة فريدة");
        en.setProperty("loginok", "Successfully logged in, Game will bring you a great gaming experience");
        ko.setProperty("loginok", "성공적인 로그인, 우리는 당신에게 훌륭한 게임 경험을 제공합니다");
        zh_HK.setProperty("loginok", "登入成功，我们將會給您帶來很棒的遊戲體驗");
        fr.setProperty("loginok", "Connecté avec succès. Game vous apportera une expérience de jeu grandiose !");
        pt.setProperty("loginok", "Acessou com sucesso. Game irá proporcionar uma excelente experiência de jogo!");
        de.setProperty("loginok", "Angemeldet. Game bringt dir ein großartiges Spielerlebnis!");
        es.setProperty("loginok", "Accedido con éxito. Game te hará pasar un tiempo extraordinario.");
        it.setProperty("loginok", "Accesso avvenuto con successo. Con Game avrai una fantastica esperienza di gioco");
        ja.setProperty("loginok", "ログインしました。Gameは素晴らしいゲーム体験を提供します！");
        id.setProperty("loginok", "Sukses login.Nikmati pengalaman bermain di Game!");
        ru.setProperty("loginok", "После успешного входа в игру Game подарит вам отличный игровой опыт");


        ar.setProperty("registerok", "تم تسجيل الحساب بنجاح");
        de.setProperty("registerok", "Kontoregistrierung erfolgreich");
        en.setProperty("registerok", "Account registration successful");
        es.setProperty("registerok", "Registro de cuenta exitoso");
        fr.setProperty("registerok", "Enregistrement du compte réussi");
        id.setProperty("registerok", "Pendaftaran akun berhasil");
        it.setProperty("registerok", "Registrazione dell'account riuscita");
        ja.setProperty("registerok", "アカウントの登録に成功しました");
        ko.setProperty("registerok", "계정 등록이 완료되었습니다");
        pt.setProperty("registerok", "Registro da conta com sucesso");
        th.setProperty("registerok", "การลงทะเบียนบัญชีสำเร็จ");
        vi.setProperty("registerok", "Đăng ký tài khoản thành công");
        zh_CN.setProperty("registerok", "注册成功");
        zh_HK.setProperty("registerok", "註冊成功");
        ru.setProperty("registerok", "Регистрация аккаунта прошла успешно");


        zh_CN.setProperty("email_null", "邮箱不能为空");
        vi.setProperty("email_null", "Email không thể trống");
        th.setProperty("email_null", "E-Mail ไม่สามารถปล่อยว่างได้จ");
        ar.setProperty("email_null", "عنوان البريد الإلكتروني لا يمكن أن يكون فارغا");
        en.setProperty("email_null", "The Email can not be blank");
        ko.setProperty("email_null", "이메일 주소를 입력해주십시오");
        zh_HK.setProperty("email_null", "電子信箱不能為空白");
        fr.setProperty("email_null", "L’adresse email ne peut pas être vide");
        pt.setProperty("email_null", "O endereço de email não pode ficar em branco");
        de.setProperty("email_null", "E-Mail-Adresse darf nicht leer sein");
        es.setProperty("email_null", "El correo no puede quedar en blanco");
        it.setProperty("email_null", "L'email non può essere vuota");
        ja.setProperty("email_null", "メールアドレスを入力しないといけません");
        id.setProperty("email_null", "Alamat email harus diisi");
        ru.setProperty("email_null", "Адрес электронной почты не может быть пустым");


        zh_CN.setProperty("please_input_email", "请输入邮箱");
        vi.setProperty("please_input_email", "Mời nhập Email");
        th.setProperty("please_input_email", "กรุณาใส่ E-Mail");
        ar.setProperty("please_input_email", "يرجى إدخال عنوان البريد الإلكتروني");
        en.setProperty("please_input_email", "please enter your email");
        ko.setProperty("please_input_email", "이메일 주소를 입력해 주세요");
        zh_HK.setProperty("please_input_email", "請輸入電子信箱");
        fr.setProperty("please_input_email", "Veuillez entrer votre adresse email");
        pt.setProperty("please_input_email", "Insira o seu endereço de email");
        de.setProperty("please_input_email", "Bitte E-Mail-Adresse eingeben");
        es.setProperty("please_input_email", "Introduce tu correo");
        it.setProperty("please_input_email", "Si prega di inserire l'indirizzo email");
        ja.setProperty("please_input_email", "メールアドレスを入力してください");
        id.setProperty("please_input_email", "Masukkan alamat email anda");
        ru.setProperty("please_input_email", "введите ваш адрес электронной почты");

        zh_CN.setProperty("please_register", "请先注册账户");
        vi.setProperty("please_register", "Hãy đăng ký tài khoản");
        th.setProperty("please_register", "กรุณาลงทะเบียนไอดีก่อน");
        ar.setProperty("please_register", "يرجى تسجيل حسابك أولا");
        en.setProperty("please_register", "Please register your account first");
        ko.setProperty("please_register", "회원가입하십시오");
        zh_HK.setProperty("please_register", "請先註冊帳號");
        fr.setProperty("please_register", "Veuillez créer un compte");
        pt.setProperty("please_register", "Registre uma conta");
        de.setProperty("please_register", "Bitte Konto registrieren");
        es.setProperty("please_register", "Registra una cuenta");
        it.setProperty("please_register", "Si prega di registrare un account");
        ja.setProperty("please_register", "アカウントを登録してください");
        id.setProperty("please_register", "Silahkan register akun");
        ru.setProperty("please_register", "Пожалуйста, сначала зарегистрируйте свой аккаунт");


        zh_CN.setProperty("please_bind_email", "请先绑定您的邮箱");
        vi.setProperty("please_bind_email", "Chọn kiểu thanh toán");
        th.setProperty("please_bind_email", "กรุณาเลือกช่องทางการเติมเงิน");
        ar.setProperty("please_bind_email", "الرجاء ربط البريد الإلكتروني الخاص بك");
        en.setProperty("please_bind_email", "Please bind your email first");
        ko.setProperty("please_bind_email", "이메일 주소를 연동하기를 하세요");
        zh_HK.setProperty("please_bind_email", "請先綁定您的電子信箱");
        fr.setProperty("please_bind_email", "Veuillez d’abord lier votre email");
        pt.setProperty("please_bind_email", "Vincule o seu email primeiro");
        de.setProperty("please_bind_email", "Bitte zuerst E-Mail verbinden");
        es.setProperty("please_bind_email", "Vincula tu correo primero");
        it.setProperty("please_bind_email", "Prima si prega di associare la tua mail");
        ja.setProperty("please_bind_email", "メールアドレスに連携してください");
        id.setProperty("please_bind_email", "Binding email dulu");
        ru.setProperty("please_bind_email", "Пожалуйста, сначала привяжите свой адрес электронной почты");

        zh_CN.setProperty("nutsplay_viewstring_repeatpassword", "重复密码");
        vi.setProperty("nutsplay_viewstring_repeatpassword", "Xác nhận mật khẩu");
        th.setProperty("nutsplay_viewstring_repeatpassword", "ระบุรหัสผ่านอีกครั้ง");
        ar.setProperty("nutsplay_viewstring_repeatpassword", "الرجاء إعادة كتابة  كلمة المرور");
        en.setProperty("nutsplay_viewstring_repeatpassword", "Repeat password");
        ko.setProperty("nutsplay_viewstring_repeatpassword", "비밀번호가 중복되었습니다");
        zh_HK.setProperty("nutsplay_viewstring_repeatpassword", "密碼重複");
        fr.setProperty("nutsplay_viewstring_repeatpassword", "Répétez le mdp");
        pt.setProperty("nutsplay_viewstring_repeatpassword", "Repita a senha");
        de.setProperty("nutsplay_viewstring_repeatpassword", "Passwort wiederh.");
        es.setProperty("nutsplay_viewstring_repeatpassword", "Repite tu contraseña");
        it.setProperty("nutsplay_viewstring_repeatpassword", "Ridigitare la password");
        ja.setProperty("nutsplay_viewstring_repeatpassword", "パスワードを繰り返してください");
        id.setProperty("nutsplay_viewstring_repeatpassword", "Ulangi password");
        ru.setProperty("nutsplay_viewstring_repeatpassword", "Повторите пароль");


        zh_CN.setProperty("nutsplay_viewstring_signout", "注销");
        vi.setProperty("nutsplay_viewstring_signout", "Thoát");
        th.setProperty("nutsplay_viewstring_signout", "ยกเลิก");
        ar.setProperty("nutsplay_viewstring_signout", "تسجيل الخروج");
        en.setProperty("nutsplay_viewstring_signout", "Log out");
        ko.setProperty("nutsplay_viewstring_signout", "로그아웃");
        zh_HK.setProperty("nutsplay_viewstring_signout", "取消");
        fr.setProperty("nutsplay_viewstring_signout", "Déconnecter");
        pt.setProperty("nutsplay_viewstring_signout", "Sair");
        de.setProperty("nutsplay_viewstring_signout", "Abmeld.");
        es.setProperty("nutsplay_viewstring_signout", "Salir");
        it.setProperty("nutsplay_viewstring_signout", "Esci");
        ja.setProperty("nutsplay_viewstring_signout", "ログアウト");
        id.setProperty("nutsplay_viewstring_signout", "Keluar");
        ru.setProperty("nutsplay_viewstring_signout", "Выйти");

        zh_CN.setProperty("gameview.pay.user.cannel", "用户取消交易");
        th.setProperty("gameview.pay.user.cannel", " ผู้เล่นยกเลิกการดำเนินการ ");
        vi.setProperty("gameview.pay.user.cannel", "Người dùng hủy giao dịch");
        ar.setProperty("gameview.pay.user.cannel", "ألغى المستخدم عملية الشحن");
        en.setProperty("gameview.pay.user.cannel", "The user cancels the transaction");
        ko.setProperty("gameview.pay.user.cannel", "사용자가 거래를 취소하셨습니다");
        zh_HK.setProperty("gameview.pay.user.cannel", "會員取消交易");
        fr.setProperty("gameview.pay.user.cannel", "L’utilisateur a annulé l’opération");
        pt.setProperty("gameview.pay.user.cannel", "O usuário cancelou a transação");
        de.setProperty("gameview.pay.user.cannel", "Nutzer bricht Transaktion ab");
        es.setProperty("gameview.pay.user.cannel", "El usuario ha cancelado la transacción");
        it.setProperty("gameview.pay.user.cannel", "L'utente ha annullato l'operazione");
        ja.setProperty("gameview.pay.user.cannel", "このユーザーは取引をキャンセルしました");
        id.setProperty("gameview.pay.user.cannel", "User membatalkan transaksi");
        ru.setProperty("gameview.pay.user.cannel", "Пользователь отменяет транзакцию");


        zh_CN.setProperty("1", "账户已存在");
        vi.setProperty("1", "Tài khoản Đã có người dùng");
        th.setProperty("1", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("1", "اسم المستخدم موجود من قبل");
        en.setProperty("1", "Account already exists");
        ko.setProperty("1", "동일한 아이디가 존재하였습니다");
        zh_HK.setProperty("1", "帳號已存在");
        fr.setProperty("1", "Le compte a déjà été enregistré");
        pt.setProperty("1", "A conta já foi registada");
        de.setProperty("1", "Konto bereits registriert");
        es.setProperty("1", "La cuenta ya ha sido registrada");
        it.setProperty("1", "L'account è già stato registrato");
        ja.setProperty("1", "このアカウントは既に登録されています");
        id.setProperty("1", "Akun telah terdaftar");
        ru.setProperty("1", "аккаунт уже существует");


        zh_CN.setProperty("2", "账户不存在");
        vi.setProperty("2", "Tài khoản không tồn tại");
        th.setProperty("2", "ไม่มีไอดีนี้อยู่");
        ar.setProperty("2", "اسم المستخدم غير موجود");
        en.setProperty("2", "Account does not exist");
        ko.setProperty("2", "존재하지 않은 아이다입니다");
        zh_HK.setProperty("2", "帳號不存在");
        fr.setProperty("2", "Le compte n’existe pas !");
        pt.setProperty("2", "A conta não existe");
        de.setProperty("2", "Konto existiert nicht");
        es.setProperty("2", "La cuenta no existe");
        it.setProperty("2", "L'account non esiste");
        ja.setProperty("2", "このアカウントは存在しません");
        id.setProperty("2", "Akun tidak tersedia");
        ru.setProperty("2", "Счет не существует");

        zh_CN.setProperty("3", "账户或密码错误");
        vi.setProperty("3", "Sai mật khẩu");
        th.setProperty("3", "รหัสผิดพลาด");
        ar.setProperty("3", "كلمة مرور خاطئة");
        en.setProperty("3", "Account or password error");
        ko.setProperty("3", "비밀전호 오류");
        zh_HK.setProperty("3", "賬戶或密碼錯誤");
        fr.setProperty("3", "Entrez le compte et mdp corrects");
        pt.setProperty("3", "Insira a conta ou a senha correta");
        de.setProperty("3", "Bitte korrektes Konto und Passwort eing.");
        es.setProperty("3", "Introduce tu cuenta o contraseña");
        it.setProperty("3", "Si prega di inserire correttamente l'account o la password");
        ja.setProperty("3", "正しいアカウントとパスワードを入力してください");
        id.setProperty("3", "Masukkan akun atau password dengan benar");
        ru.setProperty("3", "Ошибка аккаунта или пароля");

        zh_CN.setProperty("4", "邮箱不存在");
        vi.setProperty("4", "Email không tồn tại");
        th.setProperty("4", "ไม่มี E-mail นี้อยู่");
        ar.setProperty("4", "البريد الإلكتروني غير موجود");
        en.setProperty("4", "The email does not exist");
        ko.setProperty("4", "이 이메일과 바인딩된 계정이 존재하지 않습니다.");
        zh_HK.setProperty("4", "電子信箱不存在");
        fr.setProperty("4", "Aucun compte trouvé avec cette adresse email");
        pt.setProperty("4", "Não foi encontrada nenhuma conta com esse endereço de correio");
        de.setProperty("4", "Kein Konto mit dieser E-Mail gefunden");
        es.setProperty("4", "No se han encontrado cuentas con este correo");
        it.setProperty("4", "Nessun account trovato associato a questa email");
        ja.setProperty("4", "メールアドレスは存在しません");
        id.setProperty("4", "Email tidak tersedia");
        ru.setProperty("4", "Электронная почта не существует");

        zh_CN.setProperty("5", "邮箱已存在");
        vi.setProperty("5", "Email đã tồn tại");
        th.setProperty("5", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("5", "البريد الإلكتروني موجود من قبل");
        en.setProperty("5", "Email already exists");
        ko.setProperty("5", "이 이메일과 바인딩된 유저가 이미 존재합니다.");
        zh_HK.setProperty("5", "電子信箱已存在");
        fr.setProperty("5", "Adresse email déjà utilisé par un autre utilisateur");
        pt.setProperty("5", "Já existe um usuário com esse email");
        de.setProperty("5", "Ein Nutzer mit dieser E-Mail von dir existiert bereits");
        es.setProperty("5", "El usuario del correo especificado ya existe");
        it.setProperty("5", "Esiste già un utente associato a questa email");
        ja.setProperty("5", "このメールアドレスは既に登録されています");
        id.setProperty("5", "Email telah terdaftar");
        ru.setProperty("5", "адрес электронной почты уже существует");

        zh_CN.setProperty("6", "身份验证无效");
        vi.setProperty("6", "Xác nhận vô hiệu");
        th.setProperty("6", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("6", "البيانات غير صالحة");
        en.setProperty("6", "Invalid authentication");
        ko.setProperty("6", "인증하기 실페");
        zh_HK.setProperty("6", "身份驗證無效");
        fr.setProperty("6", "Authentification non valide");
        pt.setProperty("6", "Autenticação inválida");
        de.setProperty("6", "Ungültige Authentifizierung");
        es.setProperty("6", "Autentificación inválida");
        it.setProperty("6", "Autenticazione invalida");
        ja.setProperty("6", "認証失敗");
        id.setProperty("6", "Verifikasi Gagal");
        ru.setProperty("6", "Неверная аутентификация");

        zh_CN.setProperty("7", "账户已绑定");
        vi.setProperty("7", "Tài khoản đã cố định");
        th.setProperty("7", "ไอดีผูกมัดแล้ว");
        ar.setProperty("7", "الحساب موثق");
        en.setProperty("7", "Account has bound");
        ko.setProperty("7", "이미 연동되어있는 계정입니다");
        zh_HK.setProperty("7", "帳號已綁定");
        fr.setProperty("7", "Le compte a été lié");
        pt.setProperty("7", "A conta foi criada");
        de.setProperty("7", "Konto ist verbunden");
        es.setProperty("7", "Cuenta vinculada");
        it.setProperty("7", "L'account è stato associato");
        ja.setProperty("7", "アカウントは連携されました");
        id.setProperty("7", "Akun telah dibinding");
        ru.setProperty("7", "Аккаунт привязан");

        zh_CN.setProperty("8", "游戏状态无效");
        vi.setProperty("8", "Trạng thái vô hiệu");
        th.setProperty("8", "สถานะเกมล้มเหลว");
        ar.setProperty("8", "وضع لعبة غير صالح");
        en.setProperty("8", "Game status is invalid");
        ko.setProperty("8", "게임의 상태가 올바르지 않음");
        zh_HK.setProperty("8", "遊戲狀態無效");
        fr.setProperty("8", "État du jeu non valide");
        pt.setProperty("8", "Estado de jogo inválido");
        de.setProperty("8", "Ungültiger Spielstatus");
        es.setProperty("8", "Estado de juego no válido");
        it.setProperty("8", "Stato di gioco non valido");
        ja.setProperty("8", "ゲームの状態が無効です");
        id.setProperty("8", "Status game tidak valid");
        ru.setProperty("8", "Статус игры недействителен");

        zh_CN.setProperty("9", "账号被冻结");
        vi.setProperty("9", "Tài khoản bị đóng băng");
        th.setProperty("9", "ไอดีถูกล็อก");
        ar.setProperty("9", "الحساب مجمد");
        en.setProperty("9", "The account is frozen");
        ko.setProperty("9", "동결되어있는 아이다입니다");
        zh_HK.setProperty("9", "帳號被鎖定");
        fr.setProperty("9", "Le compte est gelé");
        pt.setProperty("9", "A conta está congelada");
        de.setProperty("9", "Konto eingefroren");
        es.setProperty("9", "La cuenta está congelada");
        it.setProperty("9", "L'account è congelato");
        ja.setProperty("9", "アカウントは凍結されました");
        id.setProperty("9", "Akun telah dibekukan");
        ru.setProperty("9", "Аккаунт заморожен");

        zh_CN.setProperty("10", "账户错误");
        vi.setProperty("10", "Tài khoản lỗi");
        th.setProperty("10", "ไอดีผิดพลาด");
        ar.setProperty("10", "خطأ في الحساب");
        en.setProperty("10", "Account error");
        ko.setProperty("10", "계정번호 오류");
        zh_HK.setProperty("10", "帳號錯誤");
        fr.setProperty("10", "Erreur de compte");
        pt.setProperty("10", "Erro na conta");
        de.setProperty("10", "Kontofehler");
        es.setProperty("10", "Error de cuenta");
        it.setProperty("10", "Errore nell'account");
        ja.setProperty("10", "アカウントエラー");
        id.setProperty("10", "Akun error");
        ru.setProperty("10", "Ошибка аккаунта");

        zh_CN.setProperty("11", "不是固定邮箱");
        vi.setProperty("11", "Email cố định không đúng");
        th.setProperty("11", "ไม่ใช่ E-mail ที่แน่นอน");
        ar.setProperty("11", "يجب تحديد البريد الكتروني الخاص بك");
        en.setProperty("11", "Not a fixed mailbox");
        ko.setProperty("11", "고정된 메일 주소가 아닙니다");
        zh_HK.setProperty("11", "非原本電子信箱");
        fr.setProperty("11", "Format d’adresse e-mail incorrecte");
        pt.setProperty("11", "Formato de endereço de email inválido");
        de.setProperty("11", "Ungültiges E-Mail-Format");
        es.setProperty("11", "Error en el formato del correo");
        it.setProperty("11", "Formato dell'indirizzo email invalido");
        ja.setProperty("11", "メールアドレスの形式が無効です");
        id.setProperty("11", "Alamat email tidak valid");
        ru.setProperty("11", "Не фиксированный почтовый ящик");

        zh_CN.setProperty("12", "邮箱格式无效");
        vi.setProperty("12", "Định dạng Email không hợp lệ");
        th.setProperty("12", "รูปแบบ E-mail ใช้ไม่ได้");
        ar.setProperty("12", "عنوان البريد الإلكتروني غير صالح");
        en.setProperty("12", "Invalid Email format");
        ko.setProperty("12", "올바르지 않은 이메일 주소 형식");
        zh_HK.setProperty("12", "電子信箱格式錯誤");
        fr.setProperty("12", "Format d’Email non Valide");
        pt.setProperty("12", "Formato de email inválido");
        de.setProperty("12", "Ungültiges E-Mail-Format");
        es.setProperty("12", "Error en el formato del corroe");
        it.setProperty("12", "Formato Email invalido");
        ja.setProperty("12", "メールの形式が無効です");
        id.setProperty("12", "Format email tidak valid");
        ru.setProperty("12", "Неверный формат электронной почты");

        zh_CN.setProperty("13", "账户格式无效");
        vi.setProperty("13", "Định dạng tài khoản không hợp lệ");
        th.setProperty("13", "รูปแบบไอดีใช้ไม่ได้");
        ar.setProperty("13", "شكل حساب غير صالح");
        en.setProperty("13", "Account must be 6-24 characters Letters or Numbers");
        ko.setProperty("13", "올바르지 않은 계정 형식");
        zh_HK.setProperty("13", "帳號格式錯誤");
        fr.setProperty("13", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("13", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("13", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        es.setProperty("13", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("13", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("13", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("13", "Akun harus 6-14 karakter(huruf / angka).");
        ru.setProperty("13", "Учетная запись должна состоять из 6-24 букв и цифр.");

        zh_CN.setProperty("14", "支付失败");
        vi.setProperty("14", "Thanh toán thất bại");
        th.setProperty("14", "ชำระเงินล้มเหลว");
        ar.setProperty("14", "تعذر الدفع");
        en.setProperty("14", "Payment failed");
        ko.setProperty("14", "지불 실패");
        zh_HK.setProperty("14", "付款失敗");
        fr.setProperty("14", "Paiement échoué");
        pt.setProperty("14", "Falha no pagamento");
        de.setProperty("14", "Zahlung fehlgeschl.");
        es.setProperty("14", "Pago fallido");
        it.setProperty("14", "Pagamento non riuscito");
        ja.setProperty("14", "支払失敗");
        id.setProperty("14", "TopUp gagal");
        ru.setProperty("14", "Платеж не прошел");

        zh_CN.setProperty("15", "密码格式无效");
        vi.setProperty("15", "Mã thẻ không hợp lệ");
        th.setProperty("15", "รูปแบบรหัสใช้ไม่ได้");
        ar.setProperty("15", "شكل كلمة المرور غير صالح");
        en.setProperty("15", "Password must be 6-24 characters Letters or Numbers");
        ko.setProperty("15", "올바르지 않은 비밀번호 형식");
        zh_HK.setProperty("15", "密碼格式錯誤");
        fr.setProperty("15", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("15", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("15", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        es.setProperty("15", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("15", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("15", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("15", "Password harus 6-14 karakter(huruf / angka).");
        ru.setProperty("15", "Неверный формат пароля");


        zh_CN.setProperty("19", "支付成功,如果长时间未到账,请重启游戏或者联系客服");
        vi.setProperty("19", "Thanh toán thành công, nếu chưa nhận được, hãy khởi động lại game hoặc liên hệ CSKH");
        th.setProperty("19", "เติมเงินสำเร็จ หากเงินยังไม่เข้าเกมเป็นเวลานาน กรุณาออกเข้าใหม่หรือติดต่อฝ่ายบริการ");
        ar.setProperty("19", "دفع ناجح، إن لم يتم التفعيل لوقت طويل، يرجى إعادة تشغيل اللعبة أو الاتصال بخدمة العملاء");
        en.setProperty("19", "Payment success, if not credited for a long time, please restart the game or contact customer service");
        ko.setProperty("19", "지불이 완료되었습니다. 완료되지 않는 경우에는 게임을 다시 시작하거나 고객 센터에 연락해주시기 바랍니다");
        zh_HK.setProperty("19", "交易成功，如果仍未收到點數，請重啟遊戲或聯絡客服");
        fr.setProperty("19", "Paiement réussi. Si non reçu pendant une longue période, veuillez redémarrer le jeu ou contacter le service clientèle");
        pt.setProperty("19", "Pagamento bem sucedido. Se não for creditado na conta, reinicie o jogo ou contate o apoio ao cliente");
        de.setProperty("19", "Zahlung erfolgt. Wenn der Erhalt zu lange dauert, Spiel neu starten oder Kundendienst kontaktieren.");
        es.setProperty("19", "Pago completado. Si no recibes el paquete reinicia el juego o contacta a atención al cliente.");
        it.setProperty("19", "Pagamento avvenuto con successo. Se non accreditato dopo molto tempo, si prega di riavviare il gioco o contattare il servizio clienti");
        ja.setProperty("19", "支払成功、長期間届かない場合は、ゲームを再開するか、或いはサポートにご連絡ください");
        id.setProperty("19", "BePembayaran sukses. Jika masih belum menerima, silahkan coba restart game atau hubungi customer servicerlian");
        ru.setProperty("19", "Успешный платеж, если деньги не зачислялись долгое время, перезапустите игру или обратитесь в службу поддержки.");

        zh_CN.setProperty("20", "游客账户");
        vi.setProperty("20", "Tài khoản chơi thử");
        th.setProperty("20", "ไอดีเยี่ยมชม");
        ar.setProperty("20", "حساب زائر");
        en.setProperty("20", "Tourist account");
        ko.setProperty("20", "비회원 계정");
        zh_HK.setProperty("20", "遊客帳號");
        fr.setProperty("20", "Compte Invité");
        pt.setProperty("20", "Conta de convidado");
        de.setProperty("20", "Gastkonto");
        es.setProperty("20", "Cuenta de invitado");
        it.setProperty("20", "Account guest");
        ja.setProperty("20", "ゲストアカウント");
        id.setProperty("20", "Akun guest");
        ru.setProperty("20", "Туристический счет");

        zh_CN.setProperty("21", "正在发送验证码...");
        vi.setProperty("21", "Đang gửi mã xác nhận…");
        th.setProperty("21", "กำลังส่งรหัสยืนยัน");
        ar.setProperty("21", "إرسال رمز التحقق ...");
        en.setProperty("21", "Sending verification code ...");
        ko.setProperty("21", "인증번호를 보내는 중...");
        zh_HK.setProperty("21", "正在發送驗證碼…");
        fr.setProperty("21", "Envoi du code de vérification...");
        pt.setProperty("21", "Enviando código de verificação");
        de.setProperty("21", "Sendet Bestätigungscode ...");
        es.setProperty("21", "Enviando código de verificación…");
        it.setProperty("21", "Invio del codice di conferma...");
        ja.setProperty("21", "認証コードを発信中…");
        id.setProperty("21", "Sedang kirim kode verifikasi...");
        ru.setProperty("21", "Отправка кода подтверждения ...");

        zh_CN.setProperty("22", "验证码发送成功!");
        vi.setProperty("22", "Gửi mã xác nhận thành công!");
        th.setProperty("22", "ส่งรหัสยืนยันเสร็จสิ้น");
        ar.setProperty("22", "أُرسلَ رمز التحقق بنجاح!!");
        en.setProperty("22", "Verification code sent successfully!");
        ko.setProperty("22", "인증번호를 보내는 중...");
        zh_HK.setProperty("22", "驗證碼發送成功！");
        fr.setProperty("22", "Code de vérification envoyé !");
        pt.setProperty("22", "Código de verificação enviado com sucesso!");
        de.setProperty("22", "Bestätigungscode gesendet!");
        es.setProperty("22", "Código de verificación enviado");
        it.setProperty("22", "Codice di conferma inviato correttamente!");
        ja.setProperty("22", "認証コードを発送しました！");
        id.setProperty("22", "Sukses kirim kode verifikasi!");
        ru.setProperty("22", "Код подтверждения отправлен успешно");

        zh_CN.setProperty("23", "关闭");
        vi.setProperty("23", "Đóng");
        th.setProperty("23", "ปิด");
        ar.setProperty("23", "إغلاق");
        en.setProperty("23", "nuts_icon_close");
        ko.setProperty("23", "닫기");
        zh_HK.setProperty("23", "關閉");
        fr.setProperty("23", "Fermer");
        pt.setProperty("23", "Fechar");
        de.setProperty("23", "Schließen");
        es.setProperty("23", "Cerrar");
        it.setProperty("23", "Chiudi");
        ja.setProperty("23", "閉じる");
        id.setProperty("23", "Tutup");
        ru.setProperty("23", "неисправность");


        zh_CN.setProperty("24", "验证码发送失败!");
        vi.setProperty("24", "Gửi mã xác nhận thất bại!");
        th.setProperty("24", "ส่งรหัสยืนยันล้มเหลว");
        ar.setProperty("24", "فشل في إرسال رمز التحقق!");
        en.setProperty("24", "Verification code failed to send!");
        ko.setProperty("24", "인증번호 보내기는 실패!");
        zh_HK.setProperty("24", "驗證碼發送失敗！");
        fr.setProperty("24", "Impossible d’envoyer le code de vérification");
        pt.setProperty("24", "Falha no envio do código de verificação");
        de.setProperty("24", "Bestätigungscode senden fehlgeschl.");
        es.setProperty("24", "Error al enviar código de verificación");
        it.setProperty("24", "Invio del codice di conferma non riuscito");
        ja.setProperty("24", "確認コードの送信に失敗しました");
        id.setProperty("24", "Gagal kirim kode verifikasi");
        ru.setProperty("24", "Не удалось отправить проверочный код");

        zh_CN.setProperty("25", "验证中...");
        vi.setProperty("25", "Đang xác nhận…");
        th.setProperty("25", "กำลังยืนยัน");
        ar.setProperty("25", "التأكيد ...");
        en.setProperty("25", "Verification ...");
        ko.setProperty("25", "로딩 중...");
        zh_HK.setProperty("25", "驗證中…");
        fr.setProperty("25", "Vérification...");
        pt.setProperty("25", "Verificando...");
        de.setProperty("25", "Bestätigen …");
        es.setProperty("25", "Verificando…");
        it.setProperty("25", "Verifica...");
        ja.setProperty("25", "認証中…");
        id.setProperty("25", "Verifikasi... ");
        ru.setProperty("25", "Проверка ...");

        zh_CN.setProperty("26", "发送验证码");
        vi.setProperty("26", "Gửi mã xác nhận");
        th.setProperty("26", "ส่งรหัสยืนยัน");
        ar.setProperty("26", "إرسال رمز التحقق");
        en.setProperty("26", "Send the Code");
        ko.setProperty("26", "인증번호 보내기");
        zh_HK.setProperty("26", "發送驗證碼");
        fr.setProperty("26", "Envoyer le code");
        pt.setProperty("26", "Enviar o código");
        de.setProperty("26", "Code senden");
        es.setProperty("26", "Enviar código");
        it.setProperty("26", "Inviare il codice");
        ja.setProperty("26", "検証コード");
        id.setProperty("26", "Kirim Kode");
        ru.setProperty("26", "Отправить код");

        zh_CN.setProperty("27", "正在领取...");
        vi.setProperty("27", "Đang nhận…");
        th.setProperty("27", "กำลังรับ...");
        ar.setProperty("27", "قيد الاستلام ...");
        en.setProperty("27", "Receiving ...");
        ko.setProperty("27", "가져 오는 중...");
        zh_HK.setProperty("27", "正在領取…");
        fr.setProperty("27", "Réception...");
        pt.setProperty("27", "Recebendo...");
        de.setProperty("27", "Erhalten ...");
        es.setProperty("27", "Recibiendo…");
        it.setProperty("27", "ricezione...");
        ja.setProperty("27", "受取中…");
        id.setProperty("27", "Sedang menerima...");
        ru.setProperty("27", "Получение...");

        zh_CN.setProperty("28", "领取成功，请到游戏内查看");
        vi.setProperty("28", "Nhận thành công, hãy vào game kiểm tra");
        th.setProperty("28", "รับสำเร็จ");
        ar.setProperty("28", "تم الاستلام بنجاح، يرجى التحقق في اللعبة");
        en.setProperty("28", "To receive success, please go to the game view");
        ko.setProperty("28", "가져오기 성공! 게임 안에서 확인해보십시오");
        zh_HK.setProperty("28", "領取成功，請至遊戲內確認");
        fr.setProperty("28", "Reçu avec succès. Veuillez vérifier en jeu.");
        pt.setProperty("28", "Recebido com sucesso. Verifique no jogo.");
        de.setProperty("28", "Erhalten. Bitte im Spiel anm.");
        es.setProperty("28", "Recibido con éxito. Compruébalo en el juego.");
        it.setProperty("28", "Ricezione avvenuta con successo, Si prega di verificare nel gioco");
        ja.setProperty("28", "受取成功！ゲーム内でチェックしてください。");
        id.setProperty("28", "Sukses terima. Silahkan cek di game.");
        ru.setProperty("28", "Чтобы добиться успеха, перейдите в режим просмотра игры");

        zh_CN.setProperty("29", "领取失败，请重试");
        vi.setProperty("29", "Nhận thất bại, hãy thử lại");
        th.setProperty("29", "ล้มเหลว กรุณาลองใหม่");
        en.setProperty("29", "Failed to receive. Please try again");
        ar.setProperty("29", "فشل الاستلام، يرجى المحاولة مرة أخرى");
        ko.setProperty("29", "가져오기 실페! 다시 시도해 주십시오");
        zh_HK.setProperty("29", "領取失敗，請重試");
        fr.setProperty("29", "Échec de réception. Veuillez réessayer ultérieurement");
        pt.setProperty("29", "Falha na receção. Tente novamente.");
        de.setProperty("29", "Erhalt fehlgeschl., bitte erneut vers.");
        es.setProperty("29", "Error al recibir. Vuelve a intentarlo.");
        it.setProperty("29", "Ricezione non riuscita. Riprovare di nuovo");
        ja.setProperty("29", "受取失敗、もう一度試してください");
        id.setProperty("29", "Gagal ambil. Coba kembali");
        ru.setProperty("29", "Не удалось получить. Пожалуйста, попробуйте еще раз");

        zh_CN.setProperty("30", "游客用户需要绑定平台账户才能使用此功能");
        vi.setProperty("30", "Người dùng chơi thử cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("30", "ไอดีเยี่ยมชมต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("30", "تحتاج الحسابات الزائرة إلى تقييد حساباتها  للتمكن من استخدام هذه الميزة");
        en.setProperty("30", "Guest users will need to bind their platform account to use this feature");
        ko.setProperty("30", "이 기능은 연동된 플랫품 계정하는 사용자만 사용하실 수 있습니다.");
        zh_HK.setProperty("30", "遊客帳號需要綁定平台帳號才能使用此功能");
        fr.setProperty("30", "Les comptes invité doivent d’abord faire une liaison");
        pt.setProperty("30", "Contas de convidado necessitam de vincular primeiro");
        de.setProperty("30", "Gastkonto zuerst mit Plattformkonto verb.");
        es.setProperty("30", "Las cuentas de invitado deben ser vinculadas primero.");
        it.setProperty("30", "Il guest account deve prima essere associato al platform account");
        ja.setProperty("30", "ゲストアカウントは、まずプラットフォームアカウントに連携する必要があります");
        id.setProperty("30", "Akun Guest harus segera bind akun");
        ru.setProperty("30", "Гостевым пользователям нужно будет привязать свою платформу account, чтобы использовать эту функцию.");

        zh_CN.setProperty("31", "Facebook用户需要绑定平台账户才能使用此功能");
        vi.setProperty("31", "Người dùng Facebook cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("31", "ไอดี Facebook ต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("31", "يحتاج مستخدمو الفايسبوك إلى تقييد حساباتهم للتمكن من استخدام هذه الميزة");
        en.setProperty("31", "Facebook users need to bind the platform account to use this feature");
        ko.setProperty("31", "Facebook 사용자가 플랫폼 계정을 연동하여 이 기능을 사용하실 수 있습니다.");
        zh_HK.setProperty("31", "Facebook會員需要綁定平台帳號才能使用此功能");
        fr.setProperty("31", "Les utilisateurs Facebook doivent d’abord faire une liaison");
        pt.setProperty("31", "Usuários de Facebook necessitam de vincular a conta");
        de.setProperty("31", "Facebook-Nutzer müssen zuerst das Plattformkonto verb.");
        es.setProperty("31", "Los usuarios de Facebook deben vincular sus cuentas.");
        it.setProperty("31", "l'utente Facebook deve prima essere associato al platform account");
        ja.setProperty("31", "Facebookアカウントは、まずプラットフォームアカウントに連携する必要があります");
        id.setProperty("31", "User Facebook harus segera bind akun");
        ru.setProperty("31", "Пользователям Facebook необходимо привязать платформу account для использования этой функции.");

        zh_CN.setProperty("32", "当前没有绑定邮箱!需要先绑定邮箱才能使用找回密码");
        vi.setProperty("32", "Chưa cố định Email! Cần cố định Email mới có thể tìm lại mật khẩu");
        th.setProperty("32", "ยังไม่ได้ผูกมัดไอดี ต้องผูกมัดไอดีก่อนจึงจะรีเซ็ตรหัสได้");
        ar.setProperty("32", "لا يوجد حساب مقيد! تحتاج إلى تقييد الحساب بعنوان بريد إلكتروني أولا لاسترداد كلمة المرور");
        en.setProperty("32", "There is no binding mailbox at present! Bind mailbox to use retrieve password");
        ko.setProperty("32", "현재 이 계정을 연동하는 이메일 주소가 없습니다! 비밀번호 찾기는 연동된 이메일 주소로만 할 수 있습니다");
        zh_HK.setProperty("32", "尚未綁定電子信箱！需要先綁定電子信箱才能使用找回密碼");
        fr.setProperty("32", "Aucun email lié ! La liaison par email permet de pouvoir récupérer son mot de passe.");
        pt.setProperty("32", "Não existe vinculação ao email. Vincular no email permite recuperar a senha.");
        de.setProperty("32", "Keine Mailbox verb.! Diese verb. ermöglicht Passwortwiederherst.");
        es.setProperty("32", "No hay correos vinculados. Vincula tu correo para poder recuperar tu contraseña.");
        it.setProperty("32", "Nessuna email associata! L'associazione di un'email permette la ricezione della password.");
        ja.setProperty("32", "まだメールアドレスに連携していません！メールアドレスに連携してからパスワードを取得できます");
        id.setProperty("32", "Bind email kosong! Bind email untuk mendapatkan password kembali.");
        ru.setProperty("32", "В настоящее время нет обязательного почтового ящика! Привязать почтовый ящик к использованию для восстановления пароля");

        zh_CN.setProperty("33", "密码不能为空");
        vi.setProperty("33", "Mật khẩu không thể trống");
        th.setProperty("33", "รหัสปล่อยว่างไม่ได้");
        ar.setProperty("33", "خانة كلمة المرور لا يمكن أن تكون فارغة");
        en.setProperty("33", "Enter the correct account and password");
        ko.setProperty("33", "비밀 번호를 입력해주십시오");
        zh_HK.setProperty("33", "密碼不能為空白");
        fr.setProperty("33", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("33", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("33", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        es.setProperty("33", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("33", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("33", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("33", "Password harus 6-14 karakter(huruf / angka).");
        ru.setProperty("33", "пароль не может быть пустым");

        zh_CN.setProperty("34", "正在注册...");
        vi.setProperty("34", "Đang đăng ký...");
        th.setProperty("34", "กำลังลงทะเบียน...");
        ar.setProperty("34", "تسجيل ...");
        en.setProperty("34", "Signing up ...");
        ko.setProperty("34", "등록 중...");
        zh_HK.setProperty("34", "正在註冊…");
        fr.setProperty("34", "Inscription...");
        pt.setProperty("34", "Registrando...");
        de.setProperty("34", "Registriert …");
        es.setProperty("34", "Registrando");
        it.setProperty("34", "Registrazione...");
        ja.setProperty("34", "登録中…");
        id.setProperty("34", "Sedang register...");
        ru.setProperty("34", "Регистрация ...");

        zh_CN.setProperty("35", "注册失败");
        vi.setProperty("35", "Định dạng Email không đúng");
        th.setProperty("35", "ลงทะเบียนล้มเหลว");
        ar.setProperty("35", "فشل التسجيل");
        en.setProperty("35", "registration failed");
        ko.setProperty("35", "등록 실패");
        zh_HK.setProperty("35", "註冊失敗");
        fr.setProperty("35", "Inscription échouée");
        pt.setProperty("35", "Falha no registro");
        de.setProperty("35", "Registrieren fehlgeschl.");
        es.setProperty("35", "Registro fallido");
        it.setProperty("35", "Registrazione non riuscita");
        ja.setProperty("35", "登録失敗");
        id.setProperty("35", "Registrasi gagal");
        ru.setProperty("35", "Регистрация не удалась");

        zh_CN.setProperty("36", "邮箱格式不正确");
        vi.setProperty("36", "Định dạng Email không đúng");
        th.setProperty("36", "รูปแบบ E-mail ไม่ถูกต้อง");
        ar.setProperty("36", "عنوان البريد الإلكتروني غير صالح");
        en.setProperty("36", "E-mail format is incorrect");
        ko.setProperty("36", "올바르지 않은 이메일 형식입니다");
        zh_HK.setProperty("36", "電子信箱格式不正確");
        fr.setProperty("36", "Format d’adresse e-mail incorrecte");
        pt.setProperty("36", "Formato de endereço de email inválido");
        de.setProperty("36", "Ungültiges E-Mail-Format");
        es.setProperty("36", "Formato de correo no válido");
        it.setProperty("36", "Formato dell'indirizzo email invalido");
        ja.setProperty("36", "メールアドレスの形式が無効です");
        id.setProperty("36", "Format alamat email salah");
        ru.setProperty("36", "Неправильный формат электронной почты");

        zh_CN.setProperty("37", "邮箱不能为空");
        vi.setProperty("37", "Email không thể trống");
        th.setProperty("37", "E-mail ปล่อยว่างไม่ได้");
        ar.setProperty("37", "عنوان البريد الإلكتروني لا يمكن أن يكون فارغا");
        en.setProperty("37", "E-mail can not be empty");
        ko.setProperty("37", "이메일 주소를 입력해주십시오");
        zh_HK.setProperty("37", "電子信箱不能為空白");
        fr.setProperty("37", "L’adresse email ne peut pas être vide");
        pt.setProperty("37", "O endereço de email não pode ficar em branco");
        de.setProperty("37", "E-Mail-Addresse darf nicht leer sein");
        es.setProperty("37", "El correo no puede quedar en blanco");
        it.setProperty("37", "L'email non può essere vuota");
        ja.setProperty("37", "メールアドレスを入力しなければなりません");
        id.setProperty("37", "Alamat email harus diisi");
        ru.setProperty("37", "E-mail не может быть пустым");

        zh_CN.setProperty("39", "验证码不能为空");
        vi.setProperty("39", "Mã xác nhận không thể trống");
        th.setProperty("39", "รหัสยืนยันปล่อยว่างไม่ได้");
        ar.setProperty("39", "خانة رمز التحقق لا يمكن أن تكون فارغة");
        en.setProperty("39", "verification code must be filled");
        ko.setProperty("39", "인증 번호를 입력해주십시");
        zh_HK.setProperty("39", "驗證碼不能為空白");
        fr.setProperty("39", "Le code de vérification ne peut pas être vide");
        pt.setProperty("39", "Código de verificação não pode ficar em branco");
        de.setProperty("39", "Bestätigungscode darf nicht leer sein");
        es.setProperty("39", "El código de verificación no puede quedar en blanco");
        it.setProperty("39", "Il codice di conferma non può essere vuoto");
        ja.setProperty("39", "認証コードを入力しなければなりません");
        id.setProperty("39", "Koder verifikasi harus diisi");
        ru.setProperty("39", "код подтверждения должен быть заполнен");

        zh_CN.setProperty("38Reg", "用户名必须是6-14位字母或数字");
        vi.setProperty("38Reg", "Định dạng người dùng không hợp lệ");
        th.setProperty("38Reg", "รูปแบบชื่อไอดีไม่ถูกต้อง");
        ar.setProperty("38Reg", "شكل اسم المستخدم غير صالح");
        en.setProperty("38Reg", "Account must be 6-14 characters Letters or Numbers");
        ko.setProperty("38Reg", "올바르지 않은 사용자 아이디 형식입니다");
        zh_HK.setProperty("38Reg", "帳號格式不正確");
        fr.setProperty("38Reg", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("38Reg", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("38Reg", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        es.setProperty("38Reg", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("38Reg", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("38Reg", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("38Reg", "Akun harus 6-14 karakter(huruf / angka).");
        ru.setProperty("38Reg", "Имя пользователя должно состоять из 6-14 букв или цифр.");

        zh_CN.setProperty("38", "用户名必须是6-14位字母或数字");
        vi.setProperty("38", "Định dạng người dùng không hợp lệ");
        th.setProperty("38", "รูปแบบชื่อไอดีไม่ถูกต้อง");
        ar.setProperty("38", "شكل اسم المستخدم غير صالح");
        en.setProperty("38", "Username format is incorrect");
        ko.setProperty("38", "올바르지 않은 사용자 아이디 형식입니다");
        zh_HK.setProperty("38", "帳號必須是6-14位字母或數字");
        fr.setProperty("38", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("38", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("38", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        es.setProperty("38", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("38", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("38", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("38", "Akun harus 6-14 karakter(huruf / angka).");
        ru.setProperty("38", "Имя пользователя должно состоять из 6-14 букв или цифр.");


        zh_CN.setProperty("40", "验证码格式不正确");
        vi.setProperty("40", "Mã xác nhận không đúng");
        th.setProperty("40", "รูปแบบรหัสยืนยันไม่ถูกต้อง");
        ar.setProperty("40", "شكل رمز التحقق غير صالح");
        en.setProperty("40", "The verification code is not in the correct format");
        ko.setProperty("40", "올바르지 않은 인증번호 형식입니다");
        zh_HK.setProperty("40", "驗證碼格式不正確");
        fr.setProperty("40", "Format de code de vérification invalide");
        pt.setProperty("40", "Formato de código de verificação inválido");
        de.setProperty("40", "Ungült. Bestätigungscode-Format");
        es.setProperty("40", "Código de verificación no válido");
        it.setProperty("40", "Formato del codice di conferma invalido");
        ja.setProperty("40", "認証コードの形式が無効です");
        id.setProperty("40", "Format kode verifikasi salah");
        ru.setProperty("40", "Неверный формат кода подтверждения");


        zh_CN.setProperty("regspw", "密码必须是6-14位字母或数字");
        vi.setProperty("regspw", "Lỗi định dạng mật khẩu");
        th.setProperty("regspw", "รูปแบบรหัสไม่ถูกต้อง");
        ar.setProperty("regspw", "شكل كلمة المرور غير صالح");
        en.setProperty("regspw", "Password must be 6-14 characters Letters or Numbers");
        ko.setProperty("regspw", "올바르지 않은 비밀번호 형식입니다");
        zh_HK.setProperty("regspw", "密碼必須是6-14位字母或數字");
        fr.setProperty("regspw", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("regspw", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("regspw", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        es.setProperty("regspw", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("regspw", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("regspw", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("regspw", "Password harus 6-14 karakter(huruf / angka).");
        ru.setProperty("regspw", "Пароль должен состоять из 6-14 букв или цифр.");


        zh_CN.setProperty("41", "密码必须是6-14位字母或数字");
        vi.setProperty("41", "Lỗi định dạng mật khẩu");
        th.setProperty("41", "รูปแบบรหัสไม่ถูกต้อง");
        ar.setProperty("41", "شكل كلمة المرور غير صالح");
        en.setProperty("41", "The password is not formatted correctly");
        ko.setProperty("41", "올바르지 않은 비밀번호 형식입니다");
        zh_HK.setProperty("41", "密碼必須是6-14位字母或數字");
        fr.setProperty("41", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("41", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("41", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        es.setProperty("41", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("41", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("41", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("41", "Password harus 6-14 karakter(huruf / angka).");
        ru.setProperty("41", "Пароль должен состоять из 6-14 букв или цифр.");


        zh_CN.setProperty("42", "正在开启支付");
        vi.setProperty("42", "Đang mở thanh toán");
        th.setProperty("42", "กำลังเปิดการชำระเงิน");
        ar.setProperty("42", "الأجور افتتاح");
        en.setProperty("42", "Opening payment");
        ko.setProperty("42", "지불하기를 불러오는중..");
        zh_HK.setProperty("42", "正在開啟付款");
        fr.setProperty("42", "Ouverture du paiement");
        pt.setProperty("42", "Abrindo pagamento");
        de.setProperty("42", "Öffnet Zahlung");
        es.setProperty("42", "Abriendo pago");
        it.setProperty("42", "Apertura pagamento");
        ja.setProperty("42", "支払が起動中");
        id.setProperty("42", "Membuka pembayaran");
        ru.setProperty("42", "Открытие платежа");

        zh_CN.setProperty("43", "关闭");
        vi.setProperty("43", "Đóng");
        th.setProperty("43", "ปิด");
        ar.setProperty("43", "إغلاق");
        en.setProperty("43", "shut down");
        ko.setProperty("43", "닫기");
        zh_HK.setProperty("43", "關閉");
        fr.setProperty("43", "Fermer");
        pt.setProperty("43", "Fechar");
        de.setProperty("43", "Schließen");
        es.setProperty("43", "Cerrar");
        it.setProperty("43", "Chiudi");
        ja.setProperty("43", "閉じる");
        id.setProperty("43", "Tutup");
        ru.setProperty("43", "неисправность");


        ar.setProperty("44", "رمز التحقق");
        de.setProperty("44", "Bestätigungscode");
        en.setProperty("44", "Verification code");
        es.setProperty("44", "Código de verificación");
        fr.setProperty("44", "Code de vérification");
        id.setProperty("44", "Kode verifikasi");
        it.setProperty("44", "Codice di verifica");
        ja.setProperty("44", "検証コード");
        ko.setProperty("44", "확인 코드");
        pt.setProperty("44", "Código de verificação");
        th.setProperty("44", "รหัสยืนยัน");
        vi.setProperty("44", "Mã xác nhận");
        zh_CN.setProperty("44", "验证码");
        zh_HK.setProperty("44", "驗證碼");
        ru.setProperty("44", "код подтверждения");

        zh_CN.setProperty("45", "Facebook");
        vi.setProperty("45", "Facebook");
        th.setProperty("45", "Facebook");
        ar.setProperty("45", "فايسبوك");
        en.setProperty("45", "Facebook");
        ko.setProperty("45", "Facebook");
        zh_HK.setProperty("45", "Facebook");
        fr.setProperty("45", "Facebook");
        pt.setProperty("45", "Facebook");
        de.setProperty("45", "Facebook");
        es.setProperty("45", "Facebook");
        it.setProperty("45", "Facebook");
        ja.setProperty("45", "Facebook");
        id.setProperty("45", "Facebook");
        ru.setProperty("45", "Facebook");

        zh_CN.setProperty("46", "绑定 Facebook");
        vi.setProperty("46", "Cố định Facebook");
        th.setProperty("46", "ผูกมัด Facebook");
        ar.setProperty("46", "فايسبوك");
        en.setProperty("46", "Bind Facebook");
        ko.setProperty("46", "Facebook계정을 연동하기");
        zh_HK.setProperty("46", "綁定Facebook");
        fr.setProperty("46", "Lier Facebook");
        pt.setProperty("46", "Vincular Facebook");
        de.setProperty("46", "Facebook verb.");
        es.setProperty("46", "Vincular Facebook");
        it.setProperty("46", "Associa Facebook");
        ja.setProperty("46", "Facebookに連携する");
        id.setProperty("46", "Bind Facebook");
        ru.setProperty("46", "Привязать Facebook");

        zh_CN.setProperty("autoLogin", "默认此账户为下次登录账户");
        th.setProperty("autoLogin", "Mặc định sử dụng tài khoản này cho lần đăng nhập kế tiếp");
        vi.setProperty("autoLogin", "ตั้งไอดีนี้เป็นค่าเริ่มต้น");
        ar.setProperty("autoLogin", "اعتمد هذا الحساب ليكون الحساب الافتراضي");
        en.setProperty("autoLogin", "Set this account as a default user account");
        ko.setProperty("autoLogin", "오토 로그인");
        zh_HK.setProperty("autoLogin", "設定此帳號為下次登入帳號");
        fr.setProperty("autoLogin", "Connexion-auto");
        pt.setProperty("autoLogin", "Acesso Automático");
        de.setProperty("autoLogin", "Auto-Anm.");
        es.setProperty("autoLogin", "Acceder automáticamente");
        it.setProperty("autoLogin", "Auto-login");
        ja.setProperty("autoLogin", "自動ログイン");
        id.setProperty("autoLogin", "Auto-login");
        ru.setProperty("autoLogin", "Установить эту учетную запись как учетную запись пользователя по умолчанию");


        ar.setProperty("nuts_pleaseloginfirst", "الرجاء تسجيل الدخول أولا");
        de.setProperty("nuts_pleaseloginfirst", "Bitte loggen Sie sich zuerst ein");
        en.setProperty("nuts_pleaseloginfirst", "please login first");
        es.setProperty("nuts_pleaseloginfirst", "por favor ingresa primero");
        fr.setProperty("nuts_pleaseloginfirst", "s'il vous plait Connectez-vous d'abord");
        id.setProperty("nuts_pleaseloginfirst", "silahkan masuk terlebih dahulu");
        it.setProperty("nuts_pleaseloginfirst", "per favore fai prima il log in");
        ja.setProperty("nuts_pleaseloginfirst", "最初にログインしてください");
        ko.setProperty("nuts_pleaseloginfirst", "먼저 로그인을 해주세요");
        pt.setProperty("nuts_pleaseloginfirst", "por favor faça o login primeiro");
        th.setProperty("nuts_pleaseloginfirst", "กรุณาเข้าสู่ระบบก่อน");
        vi.setProperty("nuts_pleaseloginfirst", "vui lòng đăng nhập trước");
        zh_CN.setProperty("nuts_pleaseloginfirst", "请先登录");
        zh_HK.setProperty("nuts_pleaseloginfirst", "請先登錄");
        ru.setProperty("nuts_pleaseloginfirst", "Пожалуйста, войдите сначала");


//     ===================================新增====================================

        zh_CN.setProperty("resetPwdOk", "重置密码成功");
        en.setProperty("resetPwdOk", "Reset password successfully");
        th.setProperty("resetPwdOk", "รีเซ็ตรหัสผ่านสำเร็จ");
        vi.setProperty("resetPwdOk", "Đặt lại mật khẩu thành công");
        ar.setProperty("resetPwdOk", "إعادة تعيين كلمة المرور بنجاح");
        ko.setProperty("resetPwdOk", "비밀번호 재설정");
        zh_HK.setProperty("resetPwdOk", "重置密碼成功");
        fr.setProperty("resetPwdOk", "Réinitialiser le mot de passe avec succès");
        pt.setProperty("resetPwdOk", "Redefinir senha com sucesso");
        de.setProperty("resetPwdOk", "Passwort erfolgreich zurücksetzen");
        es.setProperty("resetPwdOk", "Restablecer contraseña exitosamente");
        it.setProperty("resetPwdOk", "Reimposta password correttamente");
        ja.setProperty("resetPwdOk", "パスワードを正常にリセット");
        id.setProperty("resetPwdOk", "Berhasil mereset kata sandi");
        ru.setProperty("resetPwdOk", "Сбросить пароль успешно");


        zh_CN.setProperty("str_login_tips","请选择一种方式登录:");
        en.setProperty("str_login_tips","Please choose a way to log in:");
        th.setProperty("str_login_tips", "กรุณาเลือกวิธีการเข้าสู่ระบบ:");
        vi.setProperty("str_login_tips", "Vui lòng chọn một cách để đăng nhập:");
        ar.setProperty("str_login_tips", "الرجاء اختيار طريقة لتسجيل الدخول:");
        ko.setProperty("str_login_tips", "로그인 방법을 선택하십시오 :");
        zh_HK.setProperty("str_login_tips", "請選擇一種方式登錄：");
        fr.setProperty("str_login_tips", "Veuillez choisir un moyen de vous connecter:");
        pt.setProperty("str_login_tips", "Escolha uma maneira de fazer login:");
        de.setProperty("str_login_tips", "Bitte wählen Sie einen Weg, um sich einzuloggen:");
        es.setProperty("str_login_tips", "Por favor, elija una forma de iniciar sesión:");
        it.setProperty("str_login_tips", "Scegli un modo per accedere:");
        ja.setProperty("str_login_tips", "ログイン方法を選択してください：");
        id.setProperty("str_login_tips", "Silakan pilih cara untuk masuk:");
        ru.setProperty("str_login_tips", "Пожалуйста, выберите способ авторизации:");

        zh_CN.setProperty("guest_login","游客登录");
        en.setProperty("guest_login","Guest login");
        th.setProperty("guest_login", "เข้าสู่ระบบบุคคลทั่วไป");
        vi.setProperty("guest_login", "Đăng nhập của khách");
        ar.setProperty("guest_login", "تسجيل دخول ضيف");
        ko.setProperty("guest_login", "손님 로그인");
        zh_HK.setProperty("guest_login", "訪客登錄");
        fr.setProperty("guest_login", "Login invité");
        pt.setProperty("guest_login", "Login de convidado");
        de.setProperty("guest_login", "Gastzugang");
        es.setProperty("guest_login", "Inicio de sesión de invitado");
        it.setProperty("guest_login", "Accesso ospite");
        ja.setProperty("guest_login", "ゲストログイン");
        id.setProperty("guest_login", "Login tamu");
        ru.setProperty("guest_login", "Гостевой вход");

        zh_CN.setProperty("str_bind_tips","创建一个你要绑定的新账号:");
        en.setProperty("str_bind_tips","Create a new account that you want to bind:");
        th.setProperty("str_bind_tips", "สร้างบัญชีใหม่ที่คุณต้องการผูก:");
        vi.setProperty("str_bind_tips", "Tạo một tài khoản mới mà bạn muốn liên kết:");
        ar.setProperty("str_bind_tips", "قم بإنشاء حساب جديد تريد ربطه:");
        ko.setProperty("str_bind_tips", "바인딩하려는 새 계정을 만듭니다.");
        zh_HK.setProperty("str_bind_tips", "創建一個你要綁定的新賬號:");
        fr.setProperty("str_bind_tips", "Créez un nouveau compte que vous souhaitez lier:");
        pt.setProperty("str_bind_tips", "Crie uma nova conta que você deseja vincular:");
        de.setProperty("str_bind_tips", "Erstellen Sie ein neues Konto, das Sie binden möchten:");
        es.setProperty("str_bind_tips", "Crea una nueva cuenta que quieras vincular:");
        it.setProperty("str_bind_tips", "Crea un nuovo account che desideri associare:");
        ja.setProperty("str_bind_tips", "バインドする新しいアカウントを作成します:");
        id.setProperty("str_bind_tips", "Buat akun baru yang ingin Anda ikat:");
        ru.setProperty("str_bind_tips", "Создайте новую учетную запись, которую вы хотите связать:");

        zh_CN.setProperty("bind","绑定");
        en.setProperty("bind","Bind");
        th.setProperty("bind", "ผูกพัน");
        vi.setProperty("bind", "Ràng buộc");
        ar.setProperty("bind", "ملزم");
        ko.setProperty("bind", "바인딩");
        zh_HK.setProperty("bind", "綁定");
        fr.setProperty("bind", "Reliure");
        pt.setProperty("bind", "Encadernação");
        de.setProperty("bind", "Einband");
        es.setProperty("bind", "Vinculante");
        it.setProperty("bind", "legame");
        ja.setProperty("bind", "バインディング");
        id.setProperty("bind", "Mengikat");
        ru.setProperty("bind", "переплет");

        zh_CN.setProperty("sign_in","登录");
        en.setProperty("sign_in","Log in");
        th.setProperty("sign_in", "เข้าสู่ระบบ");
        vi.setProperty("sign_in", "Đăng nhập");
        ar.setProperty("sign_in", "تسجيل الدخول");
        ko.setProperty("sign_in", "로그인");
        zh_HK.setProperty("sign_in", "登錄");
        fr.setProperty("sign_in", "Connexion");
        pt.setProperty("sign_in", "Entrar");
        de.setProperty("sign_in", "Einloggen");
        es.setProperty("sign_in", "Iniciar sesión");
        it.setProperty("sign_in", "Entra");
        ja.setProperty("sign_in", "ログイン");
        id.setProperty("sign_in", "Login");
        ru.setProperty("sign_in", "войти");

        zh_CN.setProperty("reset","重置");
        en.setProperty("reset","Reset");
        th.setProperty("reset", "รีเซ็ต");
        vi.setProperty("reset", "Đặt lại");
        ar.setProperty("reset", "إعادة تعيين");
        ko.setProperty("reset", "리셋");
        zh_HK.setProperty("reset", "重置");
        fr.setProperty("reset", "Réinitialiser");
        pt.setProperty("reset", "Reset");
        de.setProperty("reset", "Zurücksetzen");
        es.setProperty("reset", "Restablecer");
        it.setProperty("reset", "reset");
        ja.setProperty("reset", "リセットする");
        id.setProperty("reset", "Setel ulang");
        ru.setProperty("reset", "сброс");

        zh_CN.setProperty("sign_up","注册");
        en.setProperty("sign_up","Register");
        th.setProperty("sign_up", "การลงทะเบียน");
        vi.setProperty("sign_up", "Đăng ký");
        ar.setProperty("sign_up", "تسجيل");
        ko.setProperty("sign_up", "등록");
        zh_HK.setProperty("sign_up", "註冊");
        fr.setProperty("sign_up", "Inscription");
        pt.setProperty("sign_up", "Inscrição");
        de.setProperty("sign_up", "Registrierung");
        es.setProperty("sign_up", "Registro");
        it.setProperty("sign_up", "registrazione");
        ja.setProperty("sign_up", "登録");
        id.setProperty("sign_up", "Pendaftaran");
        ru.setProperty("sign_up", "регистрация");

        zh_CN.setProperty("new_password","新密码");
        en.setProperty("new_password","New password");
        th.setProperty("new_password", "รหัสผ่านใหม่");
        vi.setProperty("new_password", "Mật khẩu mới");
        ar.setProperty("new_password", "كلمة مرور جديدة");
        ko.setProperty("new_password", "새로운 비밀번호");
        zh_HK.setProperty("new_password", "新密碼");
        fr.setProperty("new_password", "Nouveau mot de passe");
        pt.setProperty("new_password", "Nova senha");
        de.setProperty("new_password", "Neues Passwort");
        es.setProperty("new_password", "Nueva contraseña");
        it.setProperty("new_password", "Nuova password");
        ja.setProperty("new_password", "新しいパスワード");
        id.setProperty("new_password", "Kata sandi baru");
        ru.setProperty("new_password", "Новый пароль");

        zh_CN.setProperty("repeat_password","重复密码");
        en.setProperty("repeat_password","Repeat the password");
        th.setProperty("repeat_password", "ทำซ้ำรหัสผ่าน");
        vi.setProperty("repeat_password", "Lặp lại mật khẩu");
        ar.setProperty("repeat_password", "كرر كلمة المرور");
        ko.setProperty("repeat_password", "비밀번호 반복");
        zh_HK.setProperty("repeat_password", "重複密碼");
        fr.setProperty("repeat_password", "Répéter le mot de passe");
        pt.setProperty("repeat_password", "Repita a senha");
        de.setProperty("repeat_password", "Passwort wiederholen");
        es.setProperty("repeat_password", "Repetir contraseña");
        it.setProperty("repeat_password", "Ripeti la password");
        ja.setProperty("repeat_password", "繰り返しパスワード");
        id.setProperty("repeat_password", "Ulangi kata sandi");
        ru.setProperty("repeat_password", "Повторить пароль");

        zh_CN.setProperty("str_create_account","创建账户");
        en.setProperty("str_create_account","Create an account");
        th.setProperty("str_create_account", "สร้างบัญชี");
        vi.setProperty("str_create_account", "Tạo một tài khoản");
        ar.setProperty("str_create_account", "انشاء حساب");
        ko.setProperty("str_create_account", "계정 만들기");
        zh_HK.setProperty("str_create_account", "創建賬戶");
        fr.setProperty("str_create_account", "Créer un compte");
        pt.setProperty("str_create_account", "Crie uma conta");
        de.setProperty("str_create_account", "Erstellen Sie ein Konto");
        es.setProperty("str_create_account", "Crear una cuenta");
        it.setProperty("str_create_account", "Crea un account");
        ja.setProperty("str_create_account", "アカウントを作成する");
        id.setProperty("str_create_account", "Buat akun");
        ru.setProperty("str_create_account", "Создать аккаунт");

        zh_CN.setProperty("str_reset_password","重置密码");
        en.setProperty("str_reset_password","Reset password");
        th.setProperty("str_reset_password", "รีเซ็ตรหัสผ่าน");
        vi.setProperty("str_reset_password", "Đặt lại mật khẩu");
        ar.setProperty("str_reset_password", "إعادة تعيين كلمة المرور");
        ko.setProperty("str_reset_password", "비밀번호 재설정");
        zh_HK.setProperty("str_reset_password", "重置密碼");
        fr.setProperty("str_reset_password", "Réinitialiser le mot de passe");
        pt.setProperty("str_reset_password", "Redefinir senha");
        de.setProperty("str_reset_password", "Passwort zurücksetzen");
        es.setProperty("str_reset_password", "Restablecer contraseña");
        it.setProperty("str_reset_password", "Reimposta password");
        ja.setProperty("str_reset_password", "パスワードをリセット");
        id.setProperty("str_reset_password", "Setel ulang kata sandi");
        ru.setProperty("str_reset_password", "Сбросить пароль");


        zh_CN.setProperty("str_save_account","保存你的账号到本地相册，方便日后找回:");
        en.setProperty("str_save_account","Save your account to a local album so you can retrieve it later:");
        th.setProperty("str_save_account", "บันทึกบัญชีของคุณในอัลบั้มท้องถิ่นเพื่อให้คุณสามารถเรียกดูได้ในภายหลัง:");
        vi.setProperty("str_save_account", "Lưu tài khoản của bạn vào album cục bộ để bạn có thể truy xuất nó sau:");
        ar.setProperty("str_save_account", "احفظ حسابك في ألبوم محلي حتى تتمكن من استعادته لاحقًا:");
        ko.setProperty("str_save_account", "나중에 검색 할 수 있도록 계정을 로컬 앨범에 저장하십시오.");
        zh_HK.setProperty("str_save_account", "保存你的賬號到本地相冊，方便日後找回:");
        fr.setProperty("str_save_account", "Enregistrez votre compte dans un album local pour pouvoir le récupérer ultérieurement:");
        pt.setProperty("str_save_account", "Salve sua conta em um álbum local para recuperá-la mais tarde:");
        de.setProperty("str_save_account", "Speichern Sie Ihr Konto in einem lokalen Album, damit Sie es später abrufen können:");
        es.setProperty("str_save_account", "Guarde su cuenta en un álbum local para poder recuperarla más tarde:");
        it.setProperty("str_save_account", "Salva il tuo account in un album locale in modo da poterlo recuperare in seguito:");
        ja.setProperty("str_save_account", "アカウントをローカルアルバムに保存して、後で取得できるようにします。");
        id.setProperty("str_save_account", "Simpan akun Anda ke album lokal sehingga Anda dapat mengambilnya nanti:");
        ru.setProperty("str_save_account", "Сохраните свою учетную запись в локальном альбоме, чтобы вы могли получить ее позже:");

        //用户中心
        zh_CN.setProperty("str_bind_email","绑定邮箱");
        zh_HK.setProperty("str_bind_email","綁定郵箱");
        en.setProperty("str_bind_email","Bind Email");
        ar.setProperty("str_bind_email", "ربط البريد الإلكتروني");
        de.setProperty("str_bind_email", "E-Mail binden");
        es.setProperty("str_bind_email", "Enlazar correo electrónico");
        fr.setProperty("str_bind_email", "Lier l'e-mail");
        id.setProperty("str_bind_email", "Ikat Email");
        it.setProperty("str_bind_email", "Bind Email");
        ja.setProperty("str_bind_email", "メールをバインド");
        ko.setProperty("str_bind_email", "이메일 바인딩");
        pt.setProperty("str_bind_email", "Bind Email");
        th.setProperty("str_bind_email", "ผูกอีเมล");
        vi.setProperty("str_bind_email", "Ràng buộc Email");
        ru.setProperty("str_bind_email", "Привязать электронную почту");


        zh_CN.setProperty("str_bind_facebook","绑定Facebook");
        zh_HK.setProperty("str_bind_facebook","綁定Facebook");
        en.setProperty("str_bind_facebook","Bind Facebook");
        ar.setProperty("str_bind_facebook", "ربط الفيسبوك");
        de.setProperty("str_bind_facebook", "Facebook binden");
        es.setProperty("str_bind_facebook", "Vincular Facebook");
        fr.setProperty("str_bind_facebook", "Lier Facebook");
        id.setProperty("str_bind_facebook", "Ikat Facebook");
        it.setProperty("str_bind_facebook", "Bind Facebook");
        ja.setProperty("str_bind_facebook", "Facebookをバインド");
        ko.setProperty("str_bind_facebook", "Facebook 바인딩");
        pt.setProperty("str_bind_facebook", "Ligar Facebook");
        th.setProperty("str_bind_facebook", "ผูก Facebook");
        vi.setProperty("str_bind_facebook", "Ràng buộc Facebook");
        ru.setProperty("str_bind_facebook", "Привязать Facebook");


        ar.setProperty("str_reset_pwd", "إعادة تعيين كلمة المرور");
        de.setProperty("str_reset_pwd", "Passwort zurücksetzen");
        en.setProperty("str_reset_pwd", "Reset Password");
        es.setProperty("str_reset_pwd", "Restablecer la contraseña");
        fr.setProperty("str_reset_pwd", "réinitialiser le mot de passe");
        id.setProperty("str_reset_pwd", "Atur Ulang Kata Sandi");
        it.setProperty("str_reset_pwd", "Resetta la password");
        ja.setProperty("str_reset_pwd", "パスワードを再設定する");
        ko.setProperty("str_reset_pwd", "암호를 재설정");
        pt.setProperty("str_reset_pwd", "Redefinir senha");
        th.setProperty("str_reset_pwd", "รีเซ็ตรหัสผ่าน");
        vi.setProperty("str_reset_pwd", "Đặt lại mật khẩu");
        zh_CN.setProperty("str_reset_pwd", "重设密码");
        zh_HK.setProperty("str_reset_pwd", "重設密碼");
        ru.setProperty("str_reset_pwd", "Сброс пароля");


        //发送邮箱验证码成功

        ar.setProperty("sendVerifySuccess", "أرسل بنجاح ، يرجى الذهاب إلى صندوق البريد الخاص بك للتحقق من رمز التحقق.");
        de.setProperty("sendVerifySuccess", "Senden Sie erfolgreich, gehen Sie zu Ihrer Mailbox, um den Bestätigungscode zu überprüfen.");
        en.setProperty("sendVerifySuccess", "Send successfully, please go to your mailbox to check the verification code.");
        es.setProperty("sendVerifySuccess", "Envíe correctamente, vaya a su buzón para comprobar el código de verificación.");
        fr.setProperty("sendVerifySuccess", "Envoyer avec succès, veuillez accéder à votre boîte aux lettres pour vérifier le code de vérification.");
        id.setProperty("sendVerifySuccess", "Kirim berhasil, silakan masuk ke kotak surat Anda untuk memeriksa kode verifikasi.");
        it.setProperty("sendVerifySuccess", "Invia con successo, vai alla tua casella di posta per controllare il codice di verifica.");
        ja.setProperty("sendVerifySuccess", "送信に成功しました。メールボックスに移動して確認コードを確認してください。");
        ko.setProperty("sendVerifySuccess", "성공적으로 전송되었습니다. 사서함으로 이동하여 인증 코드를 확인하십시오.");
        pt.setProperty("sendVerifySuccess", "Enviar com sucesso, vá para sua caixa de correio para verificar o código de verificação.");
        th.setProperty("sendVerifySuccess", "ส่งสำเร็จโปรดไปที่กล่องจดหมายของคุณเพื่อตรวจสอบรหัสยืนยัน");
        vi.setProperty("sendVerifySuccess", "Gửi thành công, vui lòng vào hộp thư của bạn để kiểm tra mã xác minh.");
        zh_CN.setProperty("sendVerifySuccess", "发送成功，请转到您的邮箱以检查验证码。");
        zh_HK.setProperty("sendVerifySuccess", "發送成功，請轉到您的郵箱以檢查驗證碼。");
        ru.setProperty("sendVerifySuccess", "Отправлено успешно, перейдите в свой почтовый ящик, чтобы проверить код подтверждения.");

        //绑定邮箱成功
        ar.setProperty("bindEmailSuccess", "ربط صندوق البريد بنجاح");
        de.setProperty("bindEmailSuccess", "Mailbox erfolgreich binden");
        en.setProperty("bindEmailSuccess", "Bind mailbox successfully");
        es.setProperty("bindEmailSuccess", "Enlazar el buzón con éxito");
        fr.setProperty("bindEmailSuccess", "Lier la boîte aux lettres avec succès");
        id.setProperty("bindEmailSuccess", "Berhasil mengikat kotak surat");
        it.setProperty("bindEmailSuccess", "Bind mailbox correttamente");
        ja.setProperty("bindEmailSuccess", "メールボックスを正常にバインドします");
        ko.setProperty("bindEmailSuccess", "사서함을 성공적으로 바인딩");
        pt.setProperty("bindEmailSuccess", "Vincular caixa de correio com sucesso");
        th.setProperty("bindEmailSuccess", "ผูกกล่องจดหมายสำเร็จ");
        vi.setProperty("bindEmailSuccess", "Liên kết hộp thư thành công");
        zh_CN.setProperty("bindEmailSuccess", "成功绑定邮箱");
        zh_HK.setProperty("bindEmailSuccess", "成功綁定郵箱");
        ru.setProperty("bindEmailSuccess", "Привязать почтовый ящик успешно");

        //重置密码成功
        ar.setProperty("resetPwdSuccess", "إعادة تعيين كلمة المرور بنجاح");
        de.setProperty("resetPwdSuccess", "Passwort erfolgreich zurücksetzen");
        en.setProperty("resetPwdSuccess", "Reset password successfully");
        es.setProperty("resetPwdSuccess", "Restablecer contraseña con éxito");
        fr.setProperty("resetPwdSuccess", "Réinitialiser le mot de passe avec succès");
        id.setProperty("resetPwdSuccess", "Berhasil mengatur ulang kata sandi");
        it.setProperty("resetPwdSuccess", "Reimposta la password con successo");
        ja.setProperty("resetPwdSuccess", "パスワードをリセットしました");
        ko.setProperty("resetPwdSuccess", "성공적으로 비밀번호 재설정");
        pt.setProperty("resetPwdSuccess", "Redefinir senha com sucesso");
        th.setProperty("resetPwdSuccess", "รีเซ็ตรหัสผ่านสำเร็จ");
        vi.setProperty("resetPwdSuccess", "Đặt lại mật khẩu thành công");
        zh_CN.setProperty("resetPwdSuccess", "成功重置密码");
        zh_HK.setProperty("resetPwdSuccess", "成功重置密碼");
        ru.setProperty("resetPwdSuccess", "Сбросить пароль успешно");


        //提醒用户绑定邮箱
        ar.setProperty("bind_email_tips", "قم بربط حسابك وصندوق البريد الخاص بك في أقرب وقت ممكن في مركز المستخدم حتى تتمكن من استرداد كلمة المرور الخاصة بك في المستقبل.");
        de.setProperty("bind_email_tips", "Binden Sie Ihr Konto und Ihre Mailbox so schnell wie möglich im User Center, damit Sie Ihr Passwort in Zukunft abrufen können.");
        en.setProperty("bind_email_tips", "Bind your account and mailbox as soon as possible in the user center so that you can retrieve your password in the future.");
        es.setProperty("bind_email_tips", "Vincula tu cuenta y buzón lo antes posible en el centro de usuarios para que puedas recuperar tu contraseña en el futuro.");
        fr.setProperty("bind_email_tips", "Liez votre compte et votre boîte aux lettres dès que possible dans le centre utilisateur afin de pouvoir récupérer votre mot de passe à l'avenir.");
        id.setProperty("bind_email_tips", "Ikat akun dan kotak surat Anda sesegera mungkin di pusat pengguna sehingga Anda bisa mendapatkan kembali kata sandi Anda di masa mendatang.");
        it.setProperty("bind_email_tips", "Associa il tuo account e la tua casella di posta il prima possibile nel centro utenti in modo da poter recuperare la tua password in futuro.");
        ja.setProperty("bind_email_tips", "ユーザーセンターでアカウントとメールボックスをできるだけ早くバインドして、将来パスワードを取得できるようにします。");
        ko.setProperty("bind_email_tips", "나중에 암호를 검색 할 수 있도록 사용자 센터에서 가능한 한 빨리 계정과 사서함을 바인딩하십시오.");
        pt.setProperty("bind_email_tips", "Vincule sua conta e caixa de correio o mais rápido possível na central do usuário para que possa recuperar sua senha no futuro.");
        th.setProperty("bind_email_tips", "ผูกบัญชีและกล่องจดหมายของคุณโดยเร็วที่สุดในศูนย์ผู้ใช้เพื่อให้คุณสามารถดึงรหัสผ่านของคุณได้ในอนาคต");
        vi.setProperty("bind_email_tips", "Liên kết tài khoản và hộp thư của bạn càng sớm càng tốt trong trung tâm người dùng để bạn có thể lấy lại mật khẩu của mình trong tương lai.");
        zh_CN.setProperty("bind_email_tips", "尽快在用户中心将您的帐户和邮箱绑定，以便将来可以取回密码。");
        zh_HK.setProperty("bind_email_tips", "盡快在用戶中心將您的帳戶和郵箱綁定，以便將來可以取回密碼。");
        ru.setProperty("bind_email_tips", "Свяжите свою учетную запись и почтовый ящик как можно скорее в центре пользователей, чтобы вы могли восстановить свой пароль в будущем.");


        //密码输入的密码不一致
        ar.setProperty("pwd_different", "كلمات المرور غير متسقة.");
        de.setProperty("pwd_different", "Passwörter sind inkonsistent.");
        en.setProperty("pwd_different", "Passwords are inconsistent.");
        es.setProperty("pwd_different", "Las contraseñas son inconsistentes.");
        fr.setProperty("pwd_different", "Les mots de passe sont incohérents.");
        id.setProperty("pwd_different", "Kata sandi tidak konsisten.");
        it.setProperty("pwd_different", "Le password non sono coerenti.");
        ja.setProperty("pwd_different", "パスワードに矛盾があります。");
        ko.setProperty("pwd_different", "암호가 일치하지 않습니다.");
        pt.setProperty("pwd_different", "As senhas são inconsistentes.");
        th.setProperty("pwd_different", "รหัสผ่านไม่สอดคล้องกัน");
        vi.setProperty("pwd_different", "Mật khẩu không nhất quán.");
        zh_CN.setProperty("pwd_different", "密码不一致。");
        zh_HK.setProperty("pwd_different", "密碼不一致。");
        ru.setProperty("pwd_different", "Пароли несовместимы.");

        //接受（协议）
        ar.setProperty("accept", "يوافق على");
        de.setProperty("accept", "Zustimmen");
        en.setProperty("accept", "Agree");
        es.setProperty("accept", "De acuerdo");
        fr.setProperty("accept", "Se mettre d'accord");
        id.setProperty("accept", "Setuju");
        it.setProperty("accept", "Essere d'accordo");
        ja.setProperty("accept", "同意する");
        ko.setProperty("accept", "동의하다");
        pt.setProperty("accept", "Aceita");
        th.setProperty("accept", "ตกลง");
        vi.setProperty("accept", "Đồng ý");
        zh_CN.setProperty("accept", "同意");
        zh_HK.setProperty("accept", "同意");
        ru.setProperty("accept", "Согласен");


        ar.setProperty("account_which_bind_email", "اسم المستخدم");
        de.setProperty("account_which_bind_email", "Nutzername");
        en.setProperty("account_which_bind_email", "Username");
        es.setProperty("account_which_bind_email", "Nombre de usuario");
        fr.setProperty("account_which_bind_email", "Nom d'utilisateur");
        id.setProperty("account_which_bind_email", "Nama pengguna");
        it.setProperty("account_which_bind_email", "Nome utente");
        ja.setProperty("account_which_bind_email", "ユーザー名");
        ko.setProperty("account_which_bind_email", "사용자 이름");
        pt.setProperty("account_which_bind_email", "Nome do usuário");
        th.setProperty("account_which_bind_email", "ชื่อผู้ใช้");
        vi.setProperty("account_which_bind_email", "tên tài khoản");
        zh_CN.setProperty("account_which_bind_email", "用户名");
        zh_HK.setProperty("account_which_bind_email", "用戶名");
        ru.setProperty("account_which_bind_email", "Имя пользователя");

        //绑定的邮箱：
        ar.setProperty("nuts_BoundEmail", "بريد إلكتروني منضم:");
        de.setProperty("nuts_BoundEmail", "Gebundene E-Mail:");
        en.setProperty("nuts_BoundEmail", "Bound Email:");
        es.setProperty("nuts_BoundEmail", "Correo electrónico vinculado:");
        fr.setProperty("nuts_BoundEmail", "Email lié:");
        id.setProperty("nuts_BoundEmail", "Email Terikat:");
        it.setProperty("nuts_BoundEmail", "Email vincolata:");
        ja.setProperty("nuts_BoundEmail", "バインドされたメール：");
        ko.setProperty("nuts_BoundEmail", "바운드 이메일:");
        pt.setProperty("nuts_BoundEmail", "Email vinculado:");
        th.setProperty("nuts_BoundEmail", "อีเมลผูกมัด:");
        vi.setProperty("nuts_BoundEmail", "Email ràng buộc:");
        zh_CN.setProperty("nuts_BoundEmail", "绑定的邮箱:");
        zh_HK.setProperty("nuts_BoundEmail", "綁定的電郵:");
        ru.setProperty("nuts_BoundEmail", "Связанный адрес электронной почты:");

        //注册账号
        ar.setProperty("nuts_Createaccount", "إصنع حساب");
        de.setProperty("nuts_Createaccount", "Konto erstellen");
        en.setProperty("nuts_Createaccount", "Create account");
        es.setProperty("nuts_Createaccount", "Crear una cuenta");
        fr.setProperty("nuts_Createaccount", "Créer un compte");
        id.setProperty("nuts_Createaccount", "Buat Akun");
        it.setProperty("nuts_Createaccount", "Creare un profilo");
        ja.setProperty("nuts_Createaccount", "アカウントを作成する");
        ko.setProperty("nuts_Createaccount", "계정 생성");
        pt.setProperty("nuts_Createaccount", "Criar Conta");
        th.setProperty("nuts_Createaccount", "สร้างบัญชี");
        vi.setProperty("nuts_Createaccount", "Tạo tài khoản");
        zh_CN.setProperty("nuts_Createaccount", "创建帐号");
        zh_HK.setProperty("nuts_Createaccount", "創建帳號");
        ru.setProperty("nuts_Createaccount", "Регистрация");

        //验证码已发送，请勿频繁发送
        ar.setProperty("nuts_Emailhasbeenbound", "لا ترسل بشكل متكرر ، حاول مرة أخرى خلال 10 دقائق");
        de.setProperty("nuts_Emailhasbeenbound", "Nicht häufig senden, versuchen Sie es in 10 Minuten erneut");
        en.setProperty("nuts_Emailhasbeenbound", "Do not send frequently, try again in 10 minutes");
        es.setProperty("nuts_Emailhasbeenbound", "No envíe con frecuencia, vuelva a intentarlo en 10 minutos");
        fr.setProperty("nuts_Emailhasbeenbound", "N'envoyez pas fréquemment, réessayez dans 10 minutes");
        id.setProperty("nuts_Emailhasbeenbound", "Jangan sering mengirim, coba lagi dalam 10 menit");
        it.setProperty("nuts_Emailhasbeenbound", "Non inviare frequentemente, riprova tra 10 minuti");
        ja.setProperty("nuts_Emailhasbeenbound", "頻繁に送信せず、10分後に再試行してください");
        ko.setProperty("nuts_Emailhasbeenbound", "자주 보내지 말고 10 분 후에 다시 시도하십시오.");
        pt.setProperty("nuts_Emailhasbeenbound", "Não envie com frequência, tente novamente em 10 minutos");
        th.setProperty("nuts_Emailhasbeenbound", "อย่าส่งบ่อยลองอีกครั้งใน 10 นาที");
        vi.setProperty("nuts_Emailhasbeenbound", "Không gửi thường xuyên, hãy thử lại sau 10 phút");
        zh_CN.setProperty("nuts_Emailhasbeenbound", "请勿频繁发送，10分钟后重试");
        zh_HK.setProperty("nuts_Emailhasbeenbound", "請勿頻繁發送，10分鐘後重試");
        ru.setProperty("nuts_Emailhasbeenbound", "Не отправляйте часто, попробуйте еще раз через 10 минут");

        //网络错误
        ar.setProperty("network_error", "خطأ في الشبكة");
        de.setProperty("network_error", "Netzwerkfehler");
        en.setProperty("network_error", "Network Error");
        es.setProperty("network_error", "Error de red");
        fr.setProperty("network_error", "Erreur réseau");
        id.setProperty("network_error", "Kesalahan Jaringan");
        it.setProperty("network_error", "Errore di rete");
        ja.setProperty("network_error", "ネットワークエラー");
        ko.setProperty("network_error", "네트워크 오류");
        pt.setProperty("network_error", "Erro de rede");
        th.setProperty("network_error", "ข้อผิดพลาดของเครือข่าย");
        vi.setProperty("network_error", "Lỗi mạng");
        zh_CN.setProperty("network_error", "网络错误");
        zh_HK.setProperty("network_error", "網絡錯誤");
        ru.setProperty("network_error", "Сетевая ошибка");

        ar.setProperty("email_no_bind", "حسابك ليس مرتبطًا بالبريد الإلكتروني ، يرجى ربط البريد الإلكتروني في مركز المستخدم أولاً");
        de.setProperty("email_no_bind", "Ihr Konto ist nicht an eine E-Mail gebunden. Bitte binden Sie die E-Mail zuerst im User Center");
        en.setProperty("email_no_bind", "Your account is not bound to an email, please bind the email in the user center first");
        es.setProperty("email_no_bind", "Su cuenta no está vinculada a un correo electrónico, primero enlace el correo electrónico en el centro de usuarios");
        fr.setProperty("email_no_bind", "Votre compte n'est pas lié à un e-mail, veuillez d'abord lier l'e-mail dans le centre utilisateur");
        id.setProperty("email_no_bind", "Akun Anda tidak terikat dengan email, harap ikat email di pusat pengguna terlebih dahulu");
        it.setProperty("email_no_bind", "Il tuo account non è vincolato a un'e-mail, prima associa l'e-mail nel centro utenti");
        ja.setProperty("email_no_bind", "アカウントはメールにバインドされていません。まずユーザーセンターでメールをバインドしてください");
        ko.setProperty("email_no_bind", "귀하의 계정은 이메일에 연결되어 있지 않습니다. 먼저 사용자 센터에서 이메일을 연결하세요.");
        pt.setProperty("email_no_bind", "Sua conta não está vinculada a um e-mail, vincule o e-mail na central do usuário primeiro");
        th.setProperty("email_no_bind", "บัญชีของคุณไม่ผูกมัดกับอีเมลโปรดผูกอีเมลในศูนย์ผู้ใช้ก่อน");
        vi.setProperty("email_no_bind", "Tài khoản của bạn không bị ràng buộc với một email, trước tiên hãy liên kết email trong trung tâm người dùng");
        zh_CN.setProperty("email_no_bind", "您的账号未绑定邮箱,请先在用户中心绑定邮箱");
        zh_HK.setProperty("email_no_bind", "您的賬號未綁定郵箱,請先在用戶中心綁定郵箱");
        ru.setProperty("email_no_bind", "Ваша учетная запись не привязана к электронной почте, пожалуйста, сначала привяжите электронную почту в центре пользователей");
    }
}

