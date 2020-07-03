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
     */
    public SDKLangConfig() {

        Properties zh_CN = new Properties();
        Properties th = new Properties();
        Properties vi = new Properties();//越南语
        Properties ar = new Properties();
        Properties en = new Properties();
        Properties zh_HK = new Properties();
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


        zh_CN.setProperty("yuekainfo", "Ncoin Recharge");
        vi.setProperty("yuekainfo", "Đã chọn mua");
        th.setProperty("yuekainfo", "แจ้ง： เติมเงินซื้อบัตรเดือนสามารถใช้บัตรที่มีมูลมค่ามากกว่าเติมเงินได้ ส่วนที่เกินจะกลายเป็น Ncoin และสามารถนำไปใช้ซื้อสินค้าอื่นต่อไปได้");
        ar.setProperty("yuekainfo", "Chose already");
        en.setProperty("yuekainfo", "Chose already");
        ko.setProperty("yuekainfo", "Chose already");
        zh_HK.setProperty("yuekainfo", "Chose already");
        fr.setProperty("yuekainfo", "Carte Mensuelle");
        pt.setProperty("yuekainfo", "Cartão Mensal");
        de.setProperty("yuekainfo", "Monatskarte");
        es.setProperty("yuekainfo", "Tarjeta mensual");
        it.setProperty("yuekainfo", "Carta mensile");
        ja.setProperty("yuekainfo", "マンスリーカード");
        id.setProperty("yuekainfo", "Monthly Card");


        zh_CN.setProperty("Chose already", "Ncoin Recharge");
        vi.setProperty("Chose already", "Đã chọn mua");
        th.setProperty("Chose already", "เลือกซื้อแล้ว");
        ar.setProperty("Chose already", "Chose already");
        en.setProperty("Chose already", "Chose already");
        ko.setProperty("Chose already", "Chose already");
        zh_HK.setProperty("Chose already", "Chose already");
        fr.setProperty("Chose already", "Chose already");
        pt.setProperty("Chose already", "Chose already");
        de.setProperty("Chose already", "Chose already");
        es.setProperty("Chose already", "Chose already");
        it.setProperty("Chose already", "Chose already");
        ja.setProperty("Chose already", "Chose already");
        id.setProperty("Chose already", "Chose already");


        zh_CN.setProperty("Ncoin Recharge", "Ncoin Recharge");
        vi.setProperty("Ncoin Recharge", "Nạp qua Ncoin");
        th.setProperty("Ncoin Recharge", "เติมเงินผ่าน Ncoin");
        ar.setProperty("Ncoin Recharge", "Ncoin Recharge");
        en.setProperty("Ncoin Recharge", "Ncoin Recharge");
        ko.setProperty("Ncoin Recharge", "Ncoin Recharge");
        zh_HK.setProperty("Ncoin Recharge", "Ncoin Recharge");
        fr.setProperty("Ncoin Recharge", "CNcoin Recharge");
        pt.setProperty("Ncoin Recharge", "Ncoin Recharge");
        de.setProperty("Ncoin Recharge", "Ncoin Recharge");
        es.setProperty("Ncoin Recharge", "Ncoin Recharge");
        it.setProperty("Ncoin Recharge", "Ncoin Recharge");
        ja.setProperty("Ncoin Recharge", "Ncoin Recharge");
        id.setProperty("Ncoin Recharge", "Ncoin Recharge");


        zh_CN.setProperty("mua", "Mua");
        vi.setProperty("mua", "Mua");
        th.setProperty("mua", "ซื้อ");
        ar.setProperty("mua", "mua");
        en.setProperty("mua", "mua");
        ko.setProperty("mua", "mua");
        zh_HK.setProperty("mua", "mua");
        fr.setProperty("mua", "mua");
        pt.setProperty("mua", "mua");
        de.setProperty("mua", "mua");
        es.setProperty("mua", "mua");
        it.setProperty("mua", "mua");
        ja.setProperty("mua", "mua");
        id.setProperty("mua", "mua");

        zh_CN.setProperty("getDomin", "注: 首次充值可获得双倍钻石, 之后每次充值可额外获得10%钻石");
        vi.setProperty("getDomin", "Chú ý: Lần đầu nạp nhận Kim Cương X2, kế tiếp mỗi lần nạp sẽ nhận thêm 10% kim cương");
        th.setProperty("getDomin", "แจ้ง : เติมเงินครั้งแรกจะได้รับเพชร2เท่า ครั้งที่2เป็นต้นไปจะได้รับเพชรเพิ่ม10%");
        ar.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        en.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        ko.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        zh_HK.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        fr.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        pt.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        de.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        es.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        it.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        ja.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        id.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");


        zh_CN.setProperty("Pack", "Diamond");
        vi.setProperty("Pack", "Quà");
        th.setProperty("Pack", "กล่อง");
        ar.setProperty("Pack", "Pack");
        en.setProperty("Pack", "Pack");
        ko.setProperty("Pack", "Pack");
        zh_HK.setProperty("Pack", "Pack");
        fr.setProperty("Pack", "Diamante");
        pt.setProperty("Pack", "Monatskarte");
        de.setProperty("Pack", "Diamant");
        es.setProperty("Pack", "Diamante");
        it.setProperty("Pack", "Diamante");
        ja.setProperty("Pack", "ダイヤモンド");
        id.setProperty("Pack", "Berlian");


        zh_CN.setProperty(" Monthly Card", "Monthly Card");
        vi.setProperty(" Monthly Card", "Thẻ Tháng");
        th.setProperty(" Monthly Card", "บัตรเดือน");
        ar.setProperty(" Monthly Card", "Monthly Card");
        en.setProperty(" Monthly Card", "Monthly Card");
        ko.setProperty(" Monthly Card", "Monthly Card");
        zh_HK.setProperty(" Monthly Card", "Monthly Card");
        fr.setProperty(" Monthly Card", "Carte Mensuelle");
        pt.setProperty(" Monthly Card", "Cartão Mensal");
        de.setProperty(" Monthly Card", "Monatskarte");
        es.setProperty(" Monthly Card", "Tarjeta mensual");
        it.setProperty(" Monthly Card", "Carta mensile");
        ja.setProperty(" Monthly Card", "マンスリーカード");
        id.setProperty(" Monthly Card", "Monthly Card");

        zh_CN.setProperty("Diamond", "Diamond");
        vi.setProperty("Diamond", "Kim Cương");
        th.setProperty("Diamond", "เพชร");
        ar.setProperty("Diamond", "Diamond");
        en.setProperty("Diamond", "Diamond");
        ko.setProperty("Diamond", "Diamond");
        zh_HK.setProperty("Diamond", "Diamond");
        fr.setProperty("Diamond", "Diamante");
        pt.setProperty("Diamond", "Monatskarte");
        de.setProperty("Diamond", "Diamant");
        es.setProperty("Diamond", "Diamante");
        it.setProperty("Diamond", "Diamante");
        ja.setProperty("Diamond", "ダイヤモンド");
        id.setProperty("Diamond", "Berlian");

        zh_CN.setProperty("switchAccount", "切换账户");
        vi.setProperty("switchAccount", "Đổi tài khoản");
        th.setProperty("switchAccount", "สลับไอดี");
        ar.setProperty("switchAccount", "تبديل  الحساب");
        en.setProperty("switchAccount", "Switch ic_account");
        ko.setProperty("switchAccount", "계정 전환");
        zh_HK.setProperty("switchAccount", "切換帳號");
        fr.setProperty("switchAccount", "Changer de compte");
        pt.setProperty("switchAccount", "Mudar de conta");
        de.setProperty("switchAccount", "Konto wechseln");
        es.setProperty("switchAccount", "Cambiar cuenta");
        it.setProperty("switchAccount", "Cambia ic_account");
        ja.setProperty("switchAccount", "アカウント変更");
        id.setProperty("switchAccount", "Ganti Akun");

        zh_CN.setProperty("googles", "Google+");
        th.setProperty("googles", "Google+");
        vi.setProperty("googles", "Google+");
        ar.setProperty("googles", "Google+");
        en.setProperty("googles", "Google+");
        ko.setProperty("googles", "Google+");
        zh_HK.setProperty("googles", "Google+");
        fr.setProperty("googles", "Google+");
        pt.setProperty("googles", "Google+");
        de.setProperty("googles", "Google+");
        es.setProperty("googles", "Google+");
        it.setProperty("googles", "Google+");
        ja.setProperty("googles", "Google+");
        id.setProperty("googles", "Google+");


        zh_CN.setProperty("userinfos", "用户");
        vi.setProperty("userinfos", "User");
        th.setProperty("userinfos", "บัญชี");
        ar.setProperty("userinfos", "المستخدم");
        en.setProperty("userinfos", "User");
        ko.setProperty("userinfos", "사용자");
        zh_HK.setProperty("userinfos", "會員");
        fr.setProperty("userinfos", "Utilisateur");
        pt.setProperty("userinfos", "Usuário");
        de.setProperty("userinfos", "Nutzer");
        es.setProperty("userinfos", "Usuario");
        it.setProperty("userinfos", "Utente");
        ja.setProperty("userinfos", "ユーザー");
        id.setProperty("userinfos", "User");


        zh_CN.setProperty("nutsHide", "隐藏");
        vi.setProperty("nutsHide", "Ẩn");
        th.setProperty("nutsHide", " อน");
        ar.setProperty("nutsHide", "ซ่อน");
        en.setProperty("nutsHide", "Hide");
        ko.setProperty("nutsHide", "숨김");
        zh_HK.setProperty("nutsHide", "隱藏");
        fr.setProperty("nutsHide", "Masquer");
        pt.setProperty("nutsHide", "Esconder");
        de.setProperty("nutsHide", "Verberg.");
        es.setProperty("nutsHide", "Esconder");
        it.setProperty("nutsHide", "Nascondi");
        ja.setProperty("nutsHide", "隠す");
        id.setProperty("nutsHide", "Hide");

        zh_CN.setProperty("bindGoogle", "绑定Google+");
        vi.setProperty("bindGoogle", "Cố định Google+");
        th.setProperty("bindGoogle", "ผูกมัดGoogle+");
        ar.setProperty("bindGoogle", "ربط جوجل");
        en.setProperty("bindGoogle", "Bind Google+");
        ko.setProperty("bindGoogle", "g+와 연동하기");
        zh_HK.setProperty("bindGoogle", "绑定Google+");
        fr.setProperty("bindGoogle", "Lier Google+");
        pt.setProperty("bindGoogle", "Vincular Google+");
        de.setProperty("bindGoogle", "Google+ verb.");
        es.setProperty("bindGoogle", "Vincular Google+");
        it.setProperty("bindGoogle", "Collega Google+");
        ja.setProperty("bindGoogle", "Googe+連携");
        id.setProperty("bindGoogle", "Bind Google+");


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

        zh_CN.setProperty("pwsome", "请输入相同的密码");
        th.setProperty("pwsome", "กรุณาใส่รหัสอีกครั้ง");
        vi.setProperty("pwsome", "Nhập mật khẩu tương đồng");
        ar.setProperty("pwsome", "يرجى إدخال نفس كلمة المرور");
        en.setProperty("pwsome", "Enter the same password");
        ko.setProperty("pwsome", "같은 비밀번호를 입력했습니다.");
        zh_HK.setProperty("pwsome", "請輸入相同密碼");
        fr.setProperty("pwsome", "Entrez le même mdp");
        pt.setProperty("pwsome", "Insira a mesma senha");
        de.setProperty("pwsome", "Gleiches Passwort eing.");
        es.setProperty("pwsome", "Introduce la misma contraseña");
        it.setProperty("pwsome", "Inserisci la stessa password");
        ja.setProperty("pwsome", "同じパスワードを入力してください");
        id.setProperty("pwsome", "Masukkan password yang sama");


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

        zh_CN.setProperty("account_message", "检测到登录多账户，请选择以下一个账户进行游戏");
        th.setProperty("account_message", "ตรวจพบล็อกอินหลายไอดีด กรุณาเลือกไอดีที่ต้องการใช้เพียง 1 ไอดี");
        vi.setProperty("account_message", "Phát hiện đăng nhập nhiều tài khoản, hãy chọn ra 1 tài khoản để vào game");
        ar.setProperty("account_message", "تم التفطن إلى أكثر من تسجيل دخول متزامن الرجاء اختيار واحد من الحسابات التالية لدخول اللعبة");
        en.setProperty("account_message", "Multiple sign-in is detected, Please select one of the following accounts to play the game");
        ko.setProperty("account_message", "여러 계정은 로그인 되어있으므로 한 계정만 선택해서 등록해주십시오");
        zh_HK.setProperty("account_message", "偵測到登入重複帳號，請選擇以下任一帳號進入遊戲");
        fr.setProperty("account_message", "Enregistrement multiple détecté, veuillez sélectionner l’un des comptes suivants pour jouer au jeu");
        pt.setProperty("account_message", "Múltiplos registos foram detectados, selecione uma das seguintes contas para jogar.");
        de.setProperty("account_message", "Mehrfach angemeldet, bitte wähle eines dieser Konten zum Spielen.");
        es.setProperty("account_message", "Accesos múltiples detectados. Por favor, elige una de las siguientes cuentas para jugar.");
        it.setProperty("account_message", "Rilevati accessi multipli. Seleziona uno dei seguenti ic_account per giocare");
        ja.setProperty("account_message", "複数のアカウントがサインイン状態であることが検出されました。以下のアカウントから一つ選んでプレイしてください。");
        id.setProperty("account_message", "Login multiple akun, silahkan pilih salah satu akun untuk memulai permainan");

        zh_CN.setProperty("pwInfomessage", "修改密码成功，请输入账户和新的密码进行登录验证");
        th.setProperty("pwInfomessage", "แก้รหัสสำเร็จ กรุณาใส่ไอดีและรหัสใหม่ยืนยันการ");
        vi.setProperty("pwInfomessage", "Đổi mật khẩu thành công, hãy nhập tài khoản và mật khẩu mới để xác nhận đăng nhập");
        ar.setProperty("pwInfomessage", "تم تغيير كلمة المرور,الرجاء إدخال اسم مستخدم وكلمة مرور جديدة للمصادقة على تسجيل الدخول");
        en.setProperty("pwInfomessage", "password has been updated, Please enter an ic_account and a new password for login verification");
        ko.setProperty("pwInfomessage", "비밀번호가 변경되었습니다.변경된 계정과 비밀번호로 다시 등록해주십시오이");
        zh_HK.setProperty("pwInfomessage", "修改密碼成功，請輸入帳號和新的密碼進行登入驗證");
        fr.setProperty("pwInfomessage", "Mot de passe changé. Veuillez entrer votre compte et le mot de passe pour une vérification de connexion.");
        pt.setProperty("pwInfomessage", "A senha foi atualizada. Por favor insira a sua conta e a nova senha para verificação de acesso.");
        de.setProperty("pwInfomessage", "Passwort aktualisiert. Bitte Konto und das neue Passwort zum Bestätigen angeben.");
        es.setProperty("pwInfomessage", "Contraseña actualizada. Introduce tu cuenta y tu nueva contraseña.");
        it.setProperty("pwInfomessage", "Password aggiornata. Inserisci l'ic_account e la nuova password per verificare l'accesso");
        ja.setProperty("pwInfomessage", "パスワードを変更しました。アカウントと新しいパスワードを入力してログインしてください");
        id.setProperty("pwInfomessage", "Password telah diubah.Silahkan masukkan akun dan password baru untuk verifikasi login.");


        zh_CN.setProperty("pwInfo", "此功能需要已经绑定邮箱的账户才能使用，如果未绑定邮箱，请及时联系客服");
        th.setProperty("pwInfo", "ฟังก์ชั่นดังกล่าวต้องผูกมัด E-mail ก่อนจึงจะใช้ได้ หากยังไม่ได้ผูกมัด กรุณาติดต่อฝ่ายบริการ");
        vi.setProperty("pwInfo", "Cần cố định Email mới có thể sử dụng chức năng này, nếu chưa cố định, hãy liên hệ CSKH");
        ar.setProperty("pwInfo", "هذه الخاصية تحتاج إلى حساب موثق ببريد إلكتروني إذا كان الحساب غير موثق ببريد إلكتروني الرجاء الاتصال بخدمة العملاء");
        en.setProperty("pwInfo", "This function requires an ic_account that is already bound to the mailbox,If the mailbox is not bound,Please contact customer service");
        ko.setProperty("pwInfo", "이 기능은 이메일이 연동되어있는 계정만 사용할 수 있으므로 연동되지 않은 경우에는 고객 센터에 연락해 주시기 바랍니다");
        zh_HK.setProperty("pwInfo", "此功能需要已經綁定電子信箱的帳號才能使用，如果未綁定電子信箱，請聯繫客服");
        fr.setProperty("pwInfo", "Cette fonction nécessite un compte qui est déjà lié par email. Si l’email n’est pas lié, veuillez contacter le service clientèle.");
        pt.setProperty("pwInfo", "Esta função requer uma conta já vinculada a uma conta de e-mail. Se o e-mail não estivere vinculado,por favor contacte o apoio ao cliente.");
        de.setProperty("pwInfo", "Dieses Funktion erfordert ein bereits mit der Mailbox verbundenes Konto. Ist die Mailbox nicht verbunden, wende dich an den Kundendienst.");
        es.setProperty("pwInfo", "Esta función requiere una cuenta ya vinculada. Si la cuenta no está vinculada, por favor, contacta con atención al cliente.");
        it.setProperty("pwInfo", "Questa funzione necessita di un ic_account collegato alla casella email. Se la casella email non è collegata, contatta il servizio clienti");
        ja.setProperty("pwInfo", "メールアドレスに連携したプレーヤーしかこの機能を体験できません。まだメールアドレスに連携されていなければ、サポートにご連絡ください");
        id.setProperty("pwInfo", "Berlaku untuk akun yang sudah binding email.Bila belum melakukan binding email, silahkan hubungi customer service.");

        zh_CN.setProperty("bindFacebookSucess", "当前账户绑定Facebook成功,下次登录游戏点击游客登录将会生成一个新的游戏账户");
        th.setProperty("bindFacebookSucess", "ขณะนี้ผูกมัด Facebookสำเร็จ ในการล็อกอินครั้งหน้ากดเยี่ยมชม จะเป็นการสร้างไอดีใหม่");
        vi.setProperty("bindFacebookSucess", "Tài khoản cố định Facebook thành công, lần đăng nhập sau nếu nhấn đăng nhập chơi thử sẽ xuất hiện 1 tài khoản mới");
        ar.setProperty("bindFacebookSucess", "تم ربط الحساب بالفايسبوك بنجاح,يؤدي النقر على حساب زائر  إلى إنشاء حساب جديد عند تسجيل الدخول القادم!");
        en.setProperty("bindFacebookSucess", "The current ic_account bind Facebook successfully,Clicking on Guest Login will generate a new game ic_account at next login");
        ko.setProperty("bindFacebookSucess", "현재 Facebook 계정은 연동되어있으며 다음에 게임을 로그인할때는 자동적으로 새로운 계정이 생성될겁니다");
        zh_HK.setProperty("bindFacebookSucess", "該帳號成功綁定Facebook，下次登入遊戲時, 點擊遊客登入將會產生一個新的遊戲帳號");
        fr.setProperty("bindFacebookSucess", "Lias on du compte avec Facebook réussie. Appuyez sur Connexion Invité générera un nouveau compte à la prochaine connexion.");
        pt.setProperty("bindFacebookSucess", "A conta atual foi vinculada ao Facebook com sucesso. Clicar em Acesso de Convidado irá gerar uma nova conta na próxima vez.");
        de.setProperty("bindFacebookSucess", "Aktuelles Konto mit Facebook verbunden. Über Gastanmeldung wird beim nächsten Mal ein neues Konto erzeugt.");
        es.setProperty("bindFacebookSucess", "La cuenta actual ha sido vinculada con Facebook. Toca Acceso de invitado para generar una nueva cuenta al entrar.");
        it.setProperty("bindFacebookSucess", "L'ic_account selezionato è stato collegato a Facebook. Toccare Login Ospite genererà un nuovo ic_account al prossimo accesso");
        ja.setProperty("bindFacebookSucess", "このアカウントをFacebookに連携しました、次回ゲームにログインする時に、ゲストアカウントをクリックすると、新しいアカウントが現れます");
        id.setProperty("bindFacebookSucess", "Binding Facebook sukses.Berikutnya tap Guest Login akan membuat akun login baru.");


        th.setProperty("sdk_initial_error", "เริ่มต้นล้มเหลว");
        vi.setProperty("sdk_initial_error", "Khởi động thất bại");
        ar.setProperty("sdk_initial_error", "فشل التهيئة");
        en.setProperty("sdk_initial_error", "Initialization failed");
        ko.setProperty("sdk_initial_error", "초기화 실패");
        zh_HK.setProperty("sdk_initial_error", "初始化失敗");
        fr.setProperty("sdk_initial_error", "Echec d’initialisation");
        pt.setProperty("sdk_initial_error", "Falha na inicialização");
        de.setProperty("sdk_initial_error", "Initialisierung fehlgeschl.");
        es.setProperty("sdk_initial_error", "Inicialización fallida");
        it.setProperty("sdk_initial_error", "Inizializzazione Fallita");
        ja.setProperty("sdk_initial_error", "初期化失敗");
        id.setProperty("sdk_initial_error", "Inisialisasi gagal");

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

        zh_CN.setProperty("51", "账户已经被绑定");
        th.setProperty("51", "บัญชีผูกติดแล้ว");
        vi.setProperty("51", "Tài khoản đã được cố định");
        ar.setProperty("51", "الحساب موثق");
        en.setProperty("51", "The ic_account has been bound");
        ko.setProperty("51", "계정이 연동되었습니다");
        zh_HK.setProperty("51", "帳號已被使用過");
        fr.setProperty("51", "Le compte a été lié");
        pt.setProperty("51", "A conta foi vinculada.");
        de.setProperty("51", "Konto ist verbunden");
        es.setProperty("51", "La cuenta ha sido vinculada");
        it.setProperty("51", "L'ic_account è stato collegato");
        ja.setProperty("51", "このアカウントは既に連携されています");
        id.setProperty("51", "Akun sudah dibinding");

        zh_CN.setProperty("nutsplay_viewstring_signin", "登录");
        th.setProperty("nutsplay_viewstring_signin", "เข้าสู่ระบบ");
        vi.setProperty("nutsplay_viewstring_signin", "Đăng nhập");
        ar.setProperty("nutsplay_viewstring_signin", "تسجيل الدخول");
        en.setProperty("nutsplay_viewstring_signin", "log in");
        ko.setProperty("nutsplay_viewstring_signin", "로그인");
        zh_HK.setProperty("nutsplay_viewstring_signin", "登入");
        fr.setProperty("nutsplay_viewstring_signin", "Connecter");
        pt.setProperty("nutsplay_viewstring_signin", "Acesso");
        de.setProperty("nutsplay_viewstring_signin", "Anm.");
        es.setProperty("nutsplay_viewstring_signin", "Acceder");
        it.setProperty("nutsplay_viewstring_signin", "Accedi");
        ja.setProperty("nutsplay_viewstring_signin", "ログイン");
        id.setProperty("nutsplay_viewstring_signin", "Login");

        zh_CN.setProperty("nutsplay_viewstring_touristsignin", "游客登录");
        th.setProperty("nutsplay_viewstring_touristsignin", "ผู้เยี่ยมชม");
        vi.setProperty("nutsplay_viewstring_touristsignin", "Chơi ngay");
        ar.setProperty("nutsplay_viewstring_touristsignin", "تسجيل دخول حساب زائر");
        en.setProperty("nutsplay_viewstring_touristsignin", "Quick Play");
        ko.setProperty("nutsplay_viewstring_touristsignin", "쾌속 플레이");
        zh_HK.setProperty("nutsplay_viewstring_touristsignin", "遊客登入");
        fr.setProperty("nutsplay_viewstring_touristsignin", "Partie Rapide");
        pt.setProperty("nutsplay_viewstring_touristsignin", "Jogo rápido");
        de.setProperty("nutsplay_viewstring_touristsignin", "Schnellspiel");
        es.setProperty("nutsplay_viewstring_touristsignin", "Partida rápida");
        it.setProperty("nutsplay_viewstring_touristsignin", "Partita Rapida");
        ja.setProperty("nutsplay_viewstring_touristsignin", "ゲスト");
        id.setProperty("nutsplay_viewstring_touristsignin", "Guest Login");

        zh_CN.setProperty("nutsplay_viewstring_signup", "注册账户");
        th.setProperty("nutsplay_viewstring_signup", "สมัคร");
        vi.setProperty("nutsplay_viewstring_signup", "Đăng ký");
        ar.setProperty("nutsplay_viewstring_signup", "تسجيل حساب");
        en.setProperty("nutsplay_viewstring_signup", "Register");
        ko.setProperty("nutsplay_viewstring_signup", "등록");
        zh_HK.setProperty("nutsplay_viewstring_signup", "註冊帳號");
        fr.setProperty("nutsplay_viewstring_signup", "S’inscrire");
        pt.setProperty("nutsplay_viewstring_signup", "Registo");
        de.setProperty("nutsplay_viewstring_signup", "Registrieren");
        es.setProperty("nutsplay_viewstring_signup", "Registrar");
        it.setProperty("nutsplay_viewstring_signup", "Registrati");
        ja.setProperty("nutsplay_viewstring_signup", "登録");
        id.setProperty("nutsplay_viewstring_signup", "Daftar");

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

        zh_CN.setProperty("nutsplay_viewstring_cardno", "请输入卡号");
        th.setProperty("nutsplay_viewstring_cardno", "กรุณาระบุหมายเลขบัตร");
        vi.setProperty("nutsplay_viewstring_cardno", "Nhập số seri thẻ");
        ar.setProperty("nutsplay_viewstring_cardno", "الرجاء إدخال كلمة مرور البطاقة");
        en.setProperty("nutsplay_viewstring_cardno", "Please enter the card password");
        ko.setProperty("nutsplay_viewstring_cardno", "카드 번호를 입력해 주세요");
        zh_HK.setProperty("nutsplay_viewstring_cardno", "請輸入卡號");
        fr.setProperty("nutsplay_viewstring_cardno", "Entrez le numéro de carte");
        pt.setProperty("nutsplay_viewstring_cardno", "Inserir o número do cartão");
        de.setProperty("nutsplay_viewstring_cardno", "Kartennummer eingeben");
        es.setProperty("nutsplay_viewstring_cardno", "Introduce el número de tarjeta");
        it.setProperty("nutsplay_viewstring_cardno", "Inserisci il numero carta");
        ja.setProperty("nutsplay_viewstring_cardno", "カードナンバーを入力してください");
        id.setProperty("nutsplay_viewstring_cardno", "Masukkan nomor kartu");

        zh_CN.setProperty("nutsplay_viewstring_cardpass", "请输入卡密码");
        th.setProperty("nutsplay_viewstring_cardpass", "กรุณาระบุรหัสบัตร");
        vi.setProperty("nutsplay_viewstring_cardpass", "Nhập mã số thẻ");
        ar.setProperty("nutsplay_viewstring_cardpass", "الرجاء إدخال رقم البطاقة");
        en.setProperty("nutsplay_viewstring_cardpass", "Please enter the card number");
        ko.setProperty("nutsplay_viewstring_cardpass", "비밀번호를 입력해 주세요");
        zh_HK.setProperty("nutsplay_viewstring_cardpass", "請輸入密碼");
        fr.setProperty("nutsplay_viewstring_cardpass", "Entrez le mdp de carte");
        pt.setProperty("nutsplay_viewstring_cardpass", "Inserir a senha do cartão");
        de.setProperty("nutsplay_viewstring_cardpass", "Kartenpasswort eingeben");
        es.setProperty("nutsplay_viewstring_cardpass", "Introduce la contraseña de la tarjeta");
        it.setProperty("nutsplay_viewstring_cardpass", "Inserisci la password carta");
        ja.setProperty("nutsplay_viewstring_cardpass", "カードパスワードを入力してください");
        id.setProperty("nutsplay_viewstring_cardpass", "Masukkan password kartu");


        zh_CN.setProperty("nutsplay_viewstring_recharge", "充值");
        th.setProperty("nutsplay_viewstring_recharge", "เติมเงิน");
        vi.setProperty("nutsplay_viewstring_recharge", "Nạp");
        ar.setProperty("nutsplay_viewstring_recharge", "إعادة شحن");
        en.setProperty("nutsplay_viewstring_recharge", "Recharge");
        ko.setProperty("nutsplay_viewstring_recharge", "충전");
        zh_HK.setProperty("nutsplay_viewstring_recharge", "儲值");
        fr.setProperty("nutsplay_viewstring_recharge", "Recharger");
        pt.setProperty("nutsplay_viewstring_recharge", "Recarregar");
        de.setProperty("nutsplay_viewstring_recharge", "Aufladen");
        es.setProperty("nutsplay_viewstring_recharge", "Recargar");
        it.setProperty("nutsplay_viewstring_recharge", "Ricarica");
        ja.setProperty("nutsplay_viewstring_recharge", "チャージ");
        id.setProperty("nutsplay_viewstring_recharge", "TopUp");


        zh_CN.setProperty("nutsplay_viewstring_cardpay", "充值卡");
        th.setProperty("nutsplay_viewstring_cardpay", "เติมเงิน");
        vi.setProperty("nutsplay_viewstring_cardpay", "Thẻ nạp");
        ar.setProperty("nutsplay_viewstring_cardpay", "إعادة شحن البطاقة");
        en.setProperty("nutsplay_viewstring_cardpay", "Recharge card");
        ko.setProperty("nutsplay_viewstring_cardpay", "충전 카드");
        zh_HK.setProperty("nutsplay_viewstring_cardpay", "儲值卡");
        fr.setProperty("nutsplay_viewstring_cardpay", "Carte de Recharge");
        pt.setProperty("nutsplay_viewstring_cardpay", "Cartão de recarga");
        de.setProperty("nutsplay_viewstring_cardpay", "Karte aufladen");
        es.setProperty("nutsplay_viewstring_cardpay", "Recargar tarjeta");
        it.setProperty("nutsplay_viewstring_cardpay", "Ricarica carta");
        ja.setProperty("nutsplay_viewstring_cardpay", "チャージカード");
        id.setProperty("nutsplay_viewstring_cardpay", "Kartu TopUp");

        zh_CN.setProperty("nutsplay_viewstring_choose_payment_style", "选择充值方式:");
        th.setProperty("nutsplay_viewstring_choose_payment_style", "เลือกวิธีการเติมเงิน");
        vi.setProperty("nutsplay_viewstring_choose_payment_style", "Chọn phương thức nạp:");
        ar.setProperty("nutsplay_viewstring_choose_payment_style", "تحديد وسيلة الشحن:");
        en.setProperty("nutsplay_viewstring_choose_payment_style", "Choose the top-up method:");
        ko.setProperty("nutsplay_viewstring_choose_payment_style", "충전하는 수단을 선택해주십시오");
        zh_HK.setProperty("nutsplay_viewstring_choose_payment_style", "選擇儲值方式：");
        fr.setProperty("nutsplay_viewstring_choose_payment_style", "Sélectionnez le Mode de Paiement :");
        pt.setProperty("nutsplay_viewstring_choose_payment_style", "Selecionar Método de Pagamento:");
        de.setProperty("nutsplay_viewstring_choose_payment_style", "Zahlungsmethode ausw.:");
        es.setProperty("nutsplay_viewstring_choose_payment_style", "Elegir medio de pago:");
        it.setProperty("nutsplay_viewstring_choose_payment_style", "Scegli metodo di pagamento");
        ja.setProperty("nutsplay_viewstring_choose_payment_style", "チャージ方法を選んでください");
        id.setProperty("nutsplay_viewstring_choose_payment_style", "Pilih Metode TopUp:");

        zh_CN.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin余额:");
        th.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin คงเหลือ:");
        vi.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin còn:");
        ar.setProperty("nutsplay_viewstring_pay_ncoin_balance", "رصيد الـ Ncoin");
        en.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin Balance:");
        ko.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin잔액");
        zh_HK.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin餘額：");
        fr.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Solde de Ncoin :");
        pt.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Saldo de Ncoin:");
        de.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin-Guthaben:");
        es.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Balance Ncoin:");
        it.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Saldo Ncoin");
        ja.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin残高：");
        id.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Nominal Ncoin:");


        zh_CN.setProperty("nutsplay_viewstring_pay_consume_coin", "本次消费:");
        th.setProperty("nutsplay_viewstring_pay_consume_coin", "ใช้จ่ายครั้งนี:");
        vi.setProperty("nutsplay_viewstring_pay_consume_coin", "Tiêu phí:");
        ar.setProperty("nutsplay_viewstring_pay_consume_coin", "الاستهلاك :");
        en.setProperty("nutsplay_viewstring_pay_consume_coin", "The consumption:");
        ko.setProperty("nutsplay_viewstring_pay_consume_coin", "이번 비용:");
        zh_HK.setProperty("nutsplay_viewstring_pay_consume_coin", "本次消費：");
        fr.setProperty("nutsplay_viewstring_pay_consume_coin", "La consommation :");
        pt.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        de.setProperty("nutsplay_viewstring_pay_consume_coin", "Verbrauch:");
        es.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        it.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        ja.setProperty("nutsplay_viewstring_pay_consume_coin", "今回の消費：");
        id.setProperty("nutsplay_viewstring_pay_consume_coin", "Pemakaian:");

        zh_CN.setProperty("nutsplay_viewstring_signining", "登录中...");
        vi.setProperty("nutsplay_viewstring_signining", "Đang đăng nhập...:");
        th.setProperty("nutsplay_viewstring_signining", "กำลังเข้าสู่ระบบ...");
        ar.setProperty("nutsplay_viewstring_signining", "تسجيل الدخول ...");
        en.setProperty("nutsplay_viewstring_signining", "Login...");
        ko.setProperty("nutsplay_viewstring_signining", "로그인 중...");
        zh_HK.setProperty("nutsplay_viewstring_signining", "登入中…");
        fr.setProperty("nutsplay_viewstring_signining", "Connexion...");
        pt.setProperty("nutsplay_viewstring_signining", "Acedendo...");
        de.setProperty("nutsplay_viewstring_signining", "Anm. …");
        es.setProperty("nutsplay_viewstring_signining", "Accediendo…");
        it.setProperty("nutsplay_viewstring_signining", "Accesso in corso...");
        ja.setProperty("nutsplay_viewstring_signining", "ログイン中…");
        id.setProperty("nutsplay_viewstring_signining", "Login....");

        zh_CN.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登录中...");
        vi.setProperty("nutsplay_viewstring_signining_fb", "Facebook Đang đăng nhập...:");
        th.setProperty("nutsplay_viewstring_signining_fb", "Facebook กำลังเข้าสู่ระบบ...");
        ar.setProperty("nutsplay_viewstring_signining_fb", "تسجيل الدخول بالفايسبوك ...");
        en.setProperty("nutsplay_viewstring_signining_fb", "Facebook Login ...");
        ko.setProperty("nutsplay_viewstring_signining_fb", "Facebook 로그인 중...");
        zh_HK.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登入中...");
        fr.setProperty("nutsplay_viewstring_signining_fb", "Connexion Facebook...");
        pt.setProperty("nutsplay_viewstring_signining_fb", "Acessar com Facebook...");
        de.setProperty("nutsplay_viewstring_signining_fb", "Facebook-Anm.");
        es.setProperty("nutsplay_viewstring_signining_fb", "Accediendo con Facebook...");
        it.setProperty("nutsplay_viewstring_signining_fb", "Accedendo con Facebook...");
        ja.setProperty("nutsplay_viewstring_signining_fb", "Facebookログイン中…");
        id.setProperty("nutsplay_viewstring_signining_fb", "Facebook Login...");

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

        zh_CN.setProperty("tourist_signin_alert", "游客账号仅供体验，为了您的账户安全，请及时绑定一个新账号");
        vi.setProperty("tourist_signin_alert", "Vì an toàn, hãy cố định tài khoản");
        th.setProperty("tourist_signin_alert", "บัญชีนี้เป็นบัญชีที่ใช้ร่วมกัน เพื่อความปลอดภัยของบัญชีท่าน กรุณาทำการผูกมัดในทันที");
        ar.setProperty("tourist_signin_alert", "حسابك هنا معد فقط للاستخدام في اللعبة، ينصح بتوثيق حسابك لتأمينه وحمايته");
        en.setProperty("tourist_signin_alert", "The guest account is only for experience, for your account security, please bind a new account.");
        ko.setProperty("tourist_signin_alert", "이 계정을 안전하게 사용하기를 위하여 등록한 계정번호를 연동해주시기 바랍니다");
        zh_HK.setProperty("tourist_signin_alert", "遊客帳號僅供使用，為了您的帳號安全，請及時綁定註冊帳號");
        fr.setProperty("tourist_signin_alert", "Le compte de jeu est destiné uniquement à l’utilisation, pour la sécurité de votre compte, veuillez lier ce compte");
        pt.setProperty("tourist_signin_alert", "A conta do jogo se destina apenas para utilização. Por motivo de segurança deve vincular a conta");
        de.setProperty("tourist_signin_alert", "Das Spielkonto dient nur deiner Sicherheit, bitte registriertes Konto verbinden.");
        es.setProperty("tourist_signin_alert", "La cuenta del juego es solo para uso. Por tu seguridad, vincula tu cuenta.");
        it.setProperty("tourist_signin_alert", "L'ic_account del gioco è solo per uso, per maggiore sicurezza collega l'ic_account registrato");
        ja.setProperty("tourist_signin_alert", "ゲームアカウントは使用することしかできません。アカウントの安全のため、早めに登録アカウントに連携してください");
        id.setProperty("tourist_signin_alert", "Untuk keamanan karakter, segera binding akun registrasi.");


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
        it.setProperty("viewstring_Bind_Account", "Collega ic_account");
        ja.setProperty("viewstring_Bind_Account", "アカウントに連携する");
        id.setProperty("viewstring_Bind_Account", "Binding Akun");

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


        zh_CN.setProperty("nutsplay_viewstring_bindcellphone", "绑定手机");
        vi.setProperty("nutsplay_viewstring_bindcellphone", "Cố định SĐT");
        th.setProperty("nutsplay_viewstring_bindcellphone", "ผูกมัดโทรศัพท์");
        ar.setProperty("nutsplay_viewstring_bindcellphone", "توثيق برقم الهاتف");
        en.setProperty("nutsplay_viewstring_bindcellphone", "Bind your phone");
        ko.setProperty("nutsplay_viewstring_bindcellphone", "휴대폰을 연동하기");
        zh_HK.setProperty("nutsplay_viewstring_bindcellphone", "綁定手機號碼");
        fr.setProperty("Diamond", "Lier par téléphone");
        pt.setProperty("Diamond", "Vincular pelo telefone");
        de.setProperty("Diamond", "Handy verb.");
        es.setProperty("Diamond", "Vincular teléfono");
        it.setProperty("Diamond", "Collega il telefono");
        ja.setProperty("Diamond", "携帯に連携する");
        id.setProperty("Diamond", "Binding No HP");


        zh_CN.setProperty("nutsplay_viewstring_giftcard", "领取礼包");
        vi.setProperty("nutsplay_viewstring_giftcard", "Nhận quà");
        th.setProperty("nutsplay_viewstring_giftcard", "รับรางวัล");
        ar.setProperty("nutsplay_viewstring_giftcard", "الحصول على هدية");
        en.setProperty("nutsplay_viewstring_giftcard", "Gift Package");
        ko.setProperty("nutsplay_viewstring_giftcard", "선물을 수령하기");
        zh_HK.setProperty("nutsplay_viewstring_giftcard", "領取禮包");
        fr.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        pt.setProperty("nutsplay_viewstring_giftcard", "Monatskarte");
        de.setProperty("nutsplay_viewstring_giftcard", "Diamant");
        es.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        it.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        ja.setProperty("nutsplay_viewstring_giftcard", "ダイヤモンド");
        id.setProperty("nutsplay_viewstring_giftcard", "Ambil Kado");

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

        zh_CN.setProperty("nutsplay_viewstring_account_tips", "请输入账号");
        vi.setProperty("nutsplay_viewstring_account_tips", "Nhập tài khoản");
        th.setProperty("nutsplay_viewstring_account_tips", "กรุณาระบุบัญชี");
        ar.setProperty("nutsplay_viewstring_account_tips", "الرجاء إدخال اسم المستخدم");
        en.setProperty("nutsplay_viewstring_account_tips", "Please enter an username");
        ko.setProperty("nutsplay_viewstring_account_tips", "아이디를 입력해주세요");
        zh_HK.setProperty("nutsplay_viewstring_account_tips", "請輸入帳號");
        fr.setProperty("nutsplay_viewstring_account_tips", "Veuillez entrer votre compte");
        pt.setProperty("nutsplay_viewstring_account_tips", "Insira a sua conta");
        de.setProperty("nutsplay_viewstring_account_tips", "Bitte Konto eingeben");
        es.setProperty("nutsplay_viewstring_account_tips", "Introduce tu cuenta");
        it.setProperty("nutsplay_viewstring_account_tips", "Inserisci il tuo ic_account");
        ja.setProperty("nutsplay_viewstring_account_tips", "アカウントを入力してください");
        id.setProperty("nutsplay_viewstring_account_tips", "Masukkan akun anda");

        zh_CN.setProperty("nutsplay_viewstring_password_tips", "请输入用户密码");
        vi.setProperty("nutsplay_viewstring_password_tips", "Nhập mật khẩu");
        th.setProperty("nutsplay_viewstring_password_tips", "กรุณาระบุรหัสผ่าน");
        ar.setProperty("nutsplay_viewstring_password_tips", "الرجاء إدخال كلمة المرور");
        en.setProperty("nutsplay_viewstring_password_tips", "Please enter password");
        ko.setProperty("nutsplay_viewstring_password_tips", "사용자 비밀번호를 입력해주세요");
        zh_HK.setProperty("nutsplay_viewstring_password_tips", "請輸入會員密碼");
        fr.setProperty("nutsplay_viewstring_password_tips", "Veuillez entrer votre mdp");
        pt.setProperty("nutsplay_viewstring_password_tips", "Insira a sua senha");
        de.setProperty("nutsplay_viewstring_password_tips", "Bitte Nutzerpasswort eing.");
        es.setProperty("nutsplay_viewstring_password_tips", "Introduce tu contraseña");
        it.setProperty("nutsplay_viewstring_password_tips", "Inserisci la password");
        ja.setProperty("nutsplay_viewstring_password_tips", "パスワードを入力してください");
        id.setProperty("nutsplay_viewstring_password_tips", "Masukkan password user");

        zh_CN.setProperty("nuts_email", "请输入您的邮箱");
        vi.setProperty("nuts_email", "Email của bạn");
        th.setProperty("nuts_email", "กรุณาใส่ E-Mail");
        ar.setProperty("nuts_email", "الرجاء إدخال عنوان بريدك الإلكتروني");
        en.setProperty("nuts_email", "Please enter your email address");
        ko.setProperty("nuts_email", "이메일 주소를 입력해주세요");
        zh_HK.setProperty("nuts_email", "請輸入您的電子信箱");
        fr.setProperty("nuts_email", "Veuillez entrer votre adresse email");
        pt.setProperty("nuts_email", "Insira o endereço de Email");
        de.setProperty("nuts_email", "Bitte E-Mail-Adresse eingeben");
        es.setProperty("nuts_email", "Introduce tu correo");
        it.setProperty("nuts_email", "Inserisci l'email");
        ja.setProperty("nuts_email", "メールアドレスを入力してください");
        id.setProperty("nuts_email", "Masukkan alamat email anda");

        zh_CN.setProperty("nuts_username_null", "用户名不能为空");
        vi.setProperty("nuts_username_null", "Tên người dùng không thể trống");
        th.setProperty("nuts_username_null", "Username ไม่สามารถปล่อยว่างได้");
        ar.setProperty("nuts_username_null", "اسم المستخدم لا يمكن أن يكون فارغا");
        en.setProperty("nuts_username_null", "Enter the correct ic_account and password");
        ko.setProperty("nuts_username_null", "사용자 아이다를 입력해주세요");
        zh_HK.setProperty("nuts_username_null", "會員名稱不能為空白");
        fr.setProperty("nuts_username_null", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        pt.setProperty("nuts_username_null", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("nuts_username_null", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        es.setProperty("nuts_username_null", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("nuts_username_null", "L'ic_account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("nuts_username_null", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("nuts_username_null", "Akun harus 6-14 karakter(huruf / angka).");

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

        zh_CN.setProperty("welcom_guest", "欢迎使用游客账户");
        vi.setProperty("welcom_guest", "Dùng tài khoản chơi thử");
        th.setProperty("welcom_guest", "ขอต้อนรับบัญชีผู้เยี่ยมชม");
        ar.setProperty("welcom_guest", "مرحبا بالحساب الزائر");
        en.setProperty("welcom_guest", "Welcome guest ic_account!");
        ko.setProperty("welcom_guest", "비회원 계정을 사용하시는 분들을 환영합니다");
        zh_HK.setProperty("welcom_guest", "歡迎使用遊客帳號");
        fr.setProperty("welcom_guest", "Vous pouvez utiliser le compte invité !");
        pt.setProperty("welcom_guest", "Benvindo a usar a conta de convidado");
        de.setProperty("welcom_guest", "Willkommen beim Gastkonto!");
        es.setProperty("welcom_guest", "Bienvenido a usar la cuenta de invitado");
        it.setProperty("welcom_guest", "Sei libero di utilizzare il nostro ic_account guest");
        ja.setProperty("welcom_guest", "ゲストアカウントへようこそ！");
        id.setProperty("welcom_guest", "Selamat menggunakan akun guest!");

        zh_CN.setProperty("paycenter", "充值中心");
        vi.setProperty("paycenter", "Nạp thẻ");
        th.setProperty("paycenter", "ศูนย์เติมเงิน");
        ar.setProperty("paycenter", "مركز الشحن");
        en.setProperty("paycenter", "Recharge Centre");
        ko.setProperty("paycenter", "충전 센터");
        zh_HK.setProperty("paycenter", "儲值中心");
        fr.setProperty("paycenter", "Centre de Recharge");
        pt.setProperty("paycenter", "Centro de Recarga");
        de.setProperty("paycenter", "Aufladezentrum");
        es.setProperty("paycenter", "Centro de regarga");
        it.setProperty("paycenter", "Centro di ricarica");
        ja.setProperty("paycenter", "チャージセンター");
        id.setProperty("paycenter", "Pusat TopUp");

        zh_CN.setProperty("guestlogin", "游客账户登录中");
        vi.setProperty("guestlogin", "Đang đăng nhập");
        th.setProperty("guestlogin", "ศูนย์ไอดีผู้เยี่ยมชม");
        ar.setProperty("guestlogin", "تسجيل دخول الحساب الزائر");
        en.setProperty("guestlogin", "Guest ic_account login");
        ko.setProperty("guestlogin", "비회원 계정으로 등록중");
        zh_HK.setProperty("guestlogin", "遊客帳號登入中");
        fr.setProperty("guestlogin", "Connexion au Compte Invité...");
        pt.setProperty("guestlogin", "Acessar com convidado...");
        de.setProperty("guestlogin", "Gastkonto-Anmeldung …");
        es.setProperty("guestlogin", "Acceso como invitado…");
        it.setProperty("guestlogin", "Accesso del guest ic_account");
        ja.setProperty("guestlogin", "ゲストアカウントログイン…");
        id.setProperty("guestlogin", "Guest akun login...");

        zh_CN.setProperty("welcom", "欢迎 ");
        vi.setProperty("welcom", "Chào mừng ");
        th.setProperty("welcom", "ยินดีต้อนรับ ");
        ar.setProperty("welcom", "مرحبا ");
        en.setProperty("welcom", "Welcome ");
        ko.setProperty("welcom", "환영합니다 ");
        zh_HK.setProperty("welcom", "歡迎 ");
        fr.setProperty("welcom", "Bienvenue");
        pt.setProperty("welcom", "Benvindo");
        de.setProperty("welcom", "Willkommen");
        es.setProperty("welcom", "Bienvenido");
        it.setProperty("welcom", "Benvenuto");
        ja.setProperty("welcom", "ようこそ");
        id.setProperty("welcom", "Welcome");


        zh_CN.setProperty("welcomguset", "欢迎游客");
        vi.setProperty("welcomguset", "Tài khoản chơi thử");
        th.setProperty("welcomguset", "ยินดีต้อนรับผู้เยี่ยมชม");
        ar.setProperty("welcomguset", "مرحبا بالزوار");
        en.setProperty("welcomguset", "Welcome visitors");
        ko.setProperty("welcomguset", "게스트를 환영합니다!");
        zh_HK.setProperty("welcomguset", "歡迎遊客");
        fr.setProperty("welcomguset", "Bienvenue aux invités !");
        pt.setProperty("welcomguset", "Benvindos, convidados!");
        de.setProperty("welcomguset", "Willkommen, Gäste!");
        es.setProperty("welcomguset", "¡Bienvenidos, invitados!");
        it.setProperty("welcomguset", "Benvenuto ospite!");
        ja.setProperty("welcomguset", "ゲストさんようこそ！");
        id.setProperty("welcomguset", "Welcome guest!");


        zh_CN.setProperty("welcomfacebook", "欢迎Facebook用户");
        vi.setProperty("welcomfacebook", "Chào mừng người dùng Facebook");
        th.setProperty("welcomfacebook", "ยินดีต้อนรับผู้ใช้ไอดี Facebook");
        ar.setProperty("welcomfacebook", "مرحبا بمستخدمي الفايسبوك");
        en.setProperty("welcomfacebook", "Welcome Facebook users!");
        ko.setProperty("welcomfacebook", "Facebook사용하시는 분들을 환영합니다");
        zh_HK.setProperty("welcomfacebook", "歡迎Facebook會員");
        fr.setProperty("welcomfacebook", "Bienvenue aux utilisateurs de Facebook");
        pt.setProperty("welcomfacebook", "Benvindos, usuários de Facebook!");
        de.setProperty("welcomfacebook", "Willkommen, Facebook-Nutzer");
        es.setProperty("welcomfacebook", "¡Bienvenidos, usuarios de FB");
        it.setProperty("welcomfacebook", "Benvenuto utente Facebook");
        ja.setProperty("welcomfacebook", "Facebookユーザーさんようこそ");
        id.setProperty("welcomfacebook", "Welcome User Facebook");


        zh_CN.setProperty("comenuts", " 回到Game");
        vi.setProperty("comenuts", " đến với Game");
        th.setProperty("comenuts", " กลับไปยังGame");
        ar.setProperty("comenuts", "العودة إلى Game ");
        en.setProperty("comenuts", " Back to Game");
        ko.setProperty("comenuts", " Game으로 돌아가기");
        zh_HK.setProperty("comenuts", " 回到Game");
        fr.setProperty("comenuts", "Retour à Game");
        pt.setProperty("comenuts", "Regressar a Game");
        de.setProperty("comenuts", "Zurück zu Game");
        es.setProperty("comenuts", "Regresar a Game");
        it.setProperty("comenuts", "Ritorna a Game");
        ja.setProperty("comenuts", "Gameに戻る");
        id.setProperty("comenuts", "Kembali ke Game");


        zh_CN.setProperty("loginok", "登录成功，我们将会给您带来很棒的游戏体验");
        vi.setProperty("loginok", "Đăng nhập thành công, Game thế giới trò chơi vui nhộn nhất");
        th.setProperty("loginok", "ล็อกอินสำเร็จ Game จะให้ท่านได้พบกับการเล่นเกมที่น่าตื่นตาตื่นใจ");
        ar.setProperty("loginok", "تم تسجيل الدخول بنجاح، سوف تتيح لك Game خوض تجربة فريدة\u200f");
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


        zh_CN.setProperty("registerok", "注册成功");
        en.setProperty("registerok", "Account registration successful.");


        zh_CN.setProperty("playto", "一起玩游戏！！");
        vi.setProperty("playto", "Cùng chơi game!!");
        th.setProperty("playto", " มาเล่นเกมกันเถอะ!");
        ar.setProperty("playto", "لنلعب سويا!");
        en.setProperty("playto", "Let's play together!");
        ko.setProperty("playto", "같이 즐거운 게임을 시작해보세요");
        zh_HK.setProperty("playto", "一起玩遊戲！！");
        fr.setProperty("playto", "Diamante");
        pt.setProperty("playto", "Monatskarte");
        de.setProperty("playto", "Diamant");
        es.setProperty("playto", "Diamante");
        it.setProperty("playto", "Diamante");
        ja.setProperty("playto", "ダイヤモンド");
        id.setProperty("playto", "Sukses keluar");

        zh_CN.setProperty("logoOut", "注销成功");
        vi.setProperty("logoOut", "Đăng xuất thành công");
        th.setProperty("logoOut", " ลงทะเบียนสำเร็จ");
        ar.setProperty("logoOut", "تم تسجيل الخروج بنجاح");
        en.setProperty("logoOut", "successfully logged out");
        ko.setProperty("logoOut", "로그아웃 성공");
        zh_HK.setProperty("logoOut", "取消成功");
        fr.setProperty("logoOut", "Déconnecté avec succès.");
        pt.setProperty("logoOut", "Saída bem sucedida");
        de.setProperty("logoOut", "Abgemeldet");
        es.setProperty("logoOut", "Salido con éxito");
        it.setProperty("logoOut", "Logout avvenuto con successo");
        ja.setProperty("logoOut", "登録成功");
        id.setProperty("logoOut", "Berlian");

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


        zh_CN.setProperty("sending", "正在发送验证");
        vi.setProperty("sending", "Đang gửi mã xác nhận");
        th.setProperty("sending", "กำลังส่งรหัสยืนยัน");
        ar.setProperty("sending", "التحقق من الإرسال");
        en.setProperty("sending", "Sending verification");
        ko.setProperty("sending", "인증번호를 보내는 중..");
        zh_HK.setProperty("sending", "正在發送驗證碼");
        fr.setProperty("sending", "Envoi de la vérification");
        pt.setProperty("sending", "Enviando verificação");
        de.setProperty("sending", "Sendet Bestätigung");
        es.setProperty("sending", "Enviando verificación");
        it.setProperty("sending", "Invio della conferma");
        ja.setProperty("sending", "認証を送信中");
        id.setProperty("sending", "Mengirim verifikasi");

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

        zh_CN.setProperty("please_register", "请先注册账户");
        vi.setProperty("please_register", "Hãy đăng ký tài khoản");
        th.setProperty("please_register", "กรุณาลงทะเบียนไอดีก่อน");
        ar.setProperty("please_register", "يرجى تسجيل حسابك أولا");
        en.setProperty("please_register", "Please register your ic_account first");
        ko.setProperty("please_register", "회원가입하십시오");
        zh_HK.setProperty("please_register", "請先註冊帳號");
        fr.setProperty("please_register", "Veuillez créer un compte");
        pt.setProperty("please_register", "Registre uma conta");
        de.setProperty("please_register", "Bitte Konto registrieren");
        es.setProperty("please_register", "Registra una cuenta");
        it.setProperty("please_register", "Si prega di registrare un ic_account");
        ja.setProperty("please_register", "アカウントを登録してください");
        id.setProperty("please_register", "Silahkan register akun");

        zh_CN.setProperty("please_pay", "请选择支付方式");
        vi.setProperty("please_pay", "Hãy đăng ký tài khoản");
        th.setProperty("please_pay", "กรุณาลงทะเบียนไอดีก่อน");
        ar.setProperty("please_pay", "الرجاء اختيار طريقة الدفع");
        en.setProperty("please_pay", "please select a payment method");
        ko.setProperty("please_pay", "지불 수단을 선택해주십시오");
        zh_HK.setProperty("please_pay", "請選擇付款方式");
        fr.setProperty("please_pay", "Sélectionnez le Mode de Paiement :");
        pt.setProperty("please_pay", "Selecione um Método de Pagamento:");
        de.setProperty("please_pay", "Zahlungsmethode ausw.:");
        es.setProperty("please_pay", "Elige el medio de pago:");
        it.setProperty("please_pay", "Selezionare il metodo di pagamento:");
        ja.setProperty("please_pay", "支払方法を選んでください：");
        id.setProperty("please_pay", "Pilih Metode TopUp:");

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
        it.setProperty("1", "L'ic_account è già stato registrato");
        ja.setProperty("1", "このアカウントは既に登録されています");
        id.setProperty("1", "Akun telah terdaftar");


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
        it.setProperty("2", "L'ic_account non esiste");
        ja.setProperty("2", "このアカウントは存在しません");
        id.setProperty("2", "Akun tidak tersedia");

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
        it.setProperty("3", "Si prega di inserire correttamente l'ic_account o la password");
        ja.setProperty("3", "正しいアカウントとパスワードを入力してください");
        id.setProperty("3", "Masukkan akun atau password dengan benar");

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
        it.setProperty("4", "Nessun ic_account trovato associato a questa email");
        ja.setProperty("4", "メールアドレスは存在しません");
        id.setProperty("4", "Email tidak tersedia");

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
        it.setProperty("7", "L'ic_account è stato associato");
        ja.setProperty("7", "アカウントは連携されました");
        id.setProperty("7", "Akun telah dibinding");

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

        zh_CN.setProperty("9", "账号被冻结");
        vi.setProperty("9", "Tài khoản bị đóng băng");
        th.setProperty("9", "ไอดีถูกล็อก");
        ar.setProperty("9", "الحساب مجمد");
        en.setProperty("9", "The ic_account is frozen");
        ko.setProperty("9", "동결되어있는 아이다입니다");
        zh_HK.setProperty("9", "帳號被鎖定");
        fr.setProperty("9", "Le compte est gelé");
        pt.setProperty("9", "A conta está congelada");
        de.setProperty("9", "Konto eingefroren");
        es.setProperty("9", "La cuenta está congelada");
        it.setProperty("9", "L'ic_account è congelato");
        ja.setProperty("9", "アカウントは凍結されました");
        id.setProperty("9", "Akun telah dibekukan");

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
        it.setProperty("10", "Errore nell'ic_account");
        ja.setProperty("10", "アカウントエラー");
        id.setProperty("10", "Akun error");

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
        it.setProperty("13", "L'ic_account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("13", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("13", "Akun harus 6-14 karakter(huruf / angka).");

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

        zh_CN.setProperty("16", "卡已经被使用");
        vi.setProperty("16", "Thẻ đã bị sử dụng qua");
        th.setProperty("16", "บัตรถูกใช้ไปแล้ว");
        ar.setProperty("16", "البطاقة مستخدمة");
        en.setProperty("16", "The card has already been used");
        ko.setProperty("16", "이미 사용되어있는 카드입니다");
        zh_HK.setProperty("16", "卡號已被使用過");
        fr.setProperty("16", "La carte a déjà été utilisé");
        pt.setProperty("16", "Este cartão já foi usado");
        de.setProperty("16", "Karte wurde schon benutzt");
        es.setProperty("16", "La tarjeta ya ha sido usada");
        it.setProperty("16", "La carta è già stata utilizzata");
        ja.setProperty("16", "このカードは使用されました");
        id.setProperty("16", "Kartu telah digunakan");

        zh_CN.setProperty("17", "卡号或者密码无效");
        vi.setProperty("17", "Số thẻ hoặc mã thẻ vô hiệu");
        th.setProperty("17", "เลขบัตรหรือรหัสไม่ถูกต้อง");
        ar.setProperty("17", "رقم البطاقة أو كلمة المرور خاطئة");
        en.setProperty("17", "Invalid card number or password");
        ko.setProperty("17", "카드 번호나 비밀 번호는 올바르지 않습니다");
        zh_HK.setProperty("17", "卡號或密碼錯誤");
        fr.setProperty("17", "Mot de passe ou numéro de carte invalide");
        pt.setProperty("17", "Número ou senha de cartão inválido");
        de.setProperty("17", "Ungültige Karten-Nr. oder Passwort");
        es.setProperty("17", "Tarjeta o contraseña no válidas");
        it.setProperty("17", "Numero della carta o password non validi");
        ja.setProperty("17", "カードナンバー或いはパスワードが無効です");
        id.setProperty("17", "Nomor kartu atau password tidak valid");

        zh_CN.setProperty("18", "Ncoin余额不足");
        vi.setProperty("18", "Ncoin còn không đủ");
        th.setProperty("18", "Ncoin ไม่พอ");
        ar.setProperty("18", "رصيد Ncoin غير كاف");
        en.setProperty("18", "\u200FNcoin insufficient balance");
        ko.setProperty("18", "잔액이 부족합니다");
        zh_HK.setProperty("18", "Ncoin餘額不足：");
        fr.setProperty("18", "Solde de Ncoin insuffisant");
        pt.setProperty("18", "Saldo de Ncoin insuficiente");
        de.setProperty("18", "Zu wenig Ncoin-Guthaben");
        es.setProperty("18", "Balance de Ncoin insuficiente");
        it.setProperty("18", "Saldo Ncoin non sufficiente");
        ja.setProperty("18", "Ncoin残高が足りません");
        id.setProperty("18", "Nominal Ncoin tidak mencukupi");


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

        zh_CN.setProperty("20", "游客账户");
        vi.setProperty("20", "Tài khoản chơi thử");
        th.setProperty("20", "ไอดีเยี่ยมชม");
        ar.setProperty("20", "حساب زائر");
        en.setProperty("20", "Tourist ic_account");
        ko.setProperty("20", "비회원 계정");
        zh_HK.setProperty("20", "遊客帳號");
        fr.setProperty("20", "Compte Invité");
        pt.setProperty("20", "Conta de convidado");
        de.setProperty("20", "Gastkonto");
        es.setProperty("20", "Cuenta de invitado");
        it.setProperty("20", "Account guest");
        ja.setProperty("20", "ゲストアカウント");
        id.setProperty("20", "Akun guest");

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

        zh_CN.setProperty("26", "发送验证码");
        vi.setProperty("26", "Gửi mã xác nhận");
        th.setProperty("26", "ส่งรหัสยืนยัน");
        ar.setProperty("26", "إرسال رمز التحقق");
        en.setProperty("26", "Send the code");
        ko.setProperty("26", "인증번호 보내기");
        zh_HK.setProperty("26", "發送驗證碼");
        fr.setProperty("26", "Envoyer le code");
        pt.setProperty("26", "Enviar o código");
        de.setProperty("26", "Code senden");
        es.setProperty("26", "Enviar código");
        it.setProperty("26", "Inviare il codice");
        ja.setProperty("26", "認証コードを発送します");
        id.setProperty("26", "Kirim Kode");

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

        zh_CN.setProperty("30", "游客用户需要绑定平台账户才能使用此功能");
        vi.setProperty("30", "Người dùng chơi thử cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("30", "ไอดีเยี่ยมชมต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("30", "تحتاج الحسابات الزائرة إلى تقييد حساباتها  للتمكن من استخدام هذه الميزة");
        en.setProperty("30", "Guest users will need to bind their platform ic_account to use this feature");
        ko.setProperty("30", "이 기능은 연동된 플랫품 계정하는 사용자만 사용하실 수 있습니다.");
        zh_HK.setProperty("30", "遊客帳號需要綁定平台帳號才能使用此功能");
        fr.setProperty("30", "Les comptes invité doivent d’abord faire une liaison");
        pt.setProperty("30", "Contas de convidado necessitam de vincular primeiro");
        de.setProperty("30", "Gastkonto zuerst mit Plattformkonto verb.");
        es.setProperty("30", "Las cuentas de invitado deben ser vinculadas primero.");
        it.setProperty("30", "Il guest ic_account deve prima essere associato al platform ic_account");
        ja.setProperty("30", "ゲストアカウントは、まずプラットフォームアカウントに連携する必要があります");
        id.setProperty("30", "Akun Guest harus segera bind akun");

        zh_CN.setProperty("31", "Facebook用户需要绑定平台账户才能使用此功能");
        vi.setProperty("31", "Người dùng Facebook cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("31", "ไอดี Facebook ต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("31", "يحتاج مستخدمو الفايسبوك إلى تقييد حساباتهم للتمكن من استخدام هذه الميزة");
        en.setProperty("31", "Facebook users need to bind the platform ic_account to use this feature");
        ko.setProperty("31", "Facebook 사용자가 플랫폼 계정을 연동하여 이 기능을 사용하실 수 있습니다.");
        zh_HK.setProperty("31", "Facebook會員需要綁定平台帳號才能使用此功能");
        fr.setProperty("31", "Les utilisateurs Facebook doivent d’abord faire une liaison");
        pt.setProperty("31", "Usuários de Facebook necessitam de vincular a conta");
        de.setProperty("31", "Facebook-Nutzer müssen zuerst das Plattformkonto verb.");
        es.setProperty("31", "Los usuarios de Facebook deben vincular sus cuentas.");
        it.setProperty("31", "l'utente Facebook deve prima essere associato al platform ic_account");
        ja.setProperty("31", "Facebookアカウントは、まずプラットフォームアカウントに連携する必要があります");
        id.setProperty("31", "User Facebook harus segera bind akun");

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
        it.setProperty("38Reg", "L'ic_account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("38Reg", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("38Reg", "Akun harus 6-14 karakter(huruf / angka).");


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
        it.setProperty("38", "L'ic_account può contenere 6-14 caratteri (lettere o numeri) ");
        ja.setProperty("38", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        id.setProperty("38", "Akun harus 6-14 karakter(huruf / angka).");


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


        zh_CN.setProperty("44", "请输入验证码");
        vi.setProperty("44", "Nhập mã xác nhận");
        th.setProperty("44", "กรุณาใส่รหัสยืนยัน");
        ar.setProperty("44", "الرجاء إدخال رمز التحقق");
        en.setProperty("44", "Please enter the verification code");
        ko.setProperty("44", "인증번호를 입력해 주십시오.");
        zh_HK.setProperty("44", "請輸入驗證碼");
        fr.setProperty("44", "Veuillez entrer le code de vérification");
        pt.setProperty("44", "Insira o código de verificação");
        de.setProperty("44", "Bestätigungscode eingeben");
        es.setProperty("44", "Introduce el código de verificación");
        it.setProperty("44", "Si prega di inserire il codice di conferma");
        ja.setProperty("44", "認証コードを入力してください");
        id.setProperty("44", "Masukkan kode verifikasi");


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

        zh_CN.setProperty("autoLogin", "默认此账户为下次登录账户");
        th.setProperty("autoLogin", "Mặc định sử dụng tài khoản này cho lần đăng nhập kế tiếp");
        vi.setProperty("autoLogin", "ตั้งไอดีนี้เป็นค่าเริ่มต้น");
        ar.setProperty("autoLogin", "اعتمد هذا الحساب ليكون الحساب الافتراضي");
        en.setProperty("autoLogin", "Set this ic_account as a default user ic_account");
        ko.setProperty("autoLogin", "오토 로그인");
        zh_HK.setProperty("autoLogin", "設定此帳號為下次登入帳號");
        fr.setProperty("autoLogin", "Connexion-auto");
        pt.setProperty("autoLogin", "Acesso Automático");
        de.setProperty("autoLogin", "Auto-Anm.");
        es.setProperty("autoLogin", "Acceder automáticamente");
        it.setProperty("autoLogin", "Auto-login");
        ja.setProperty("autoLogin", "自動ログイン");
        id.setProperty("autoLogin", "Auto-login");


        zh_CN.setProperty("cardnull", "卡号不能为空");
        th.setProperty("cardnull", "ไม่สามารถเว้นได้");
        vi.setProperty("cardnull", "Mã số thẻ không thể trống");
        ar.setProperty("cardnull", "卡号不能为空");
        en.setProperty("cardnull", "卡号不能为空");
        ko.setProperty("cardnull", "卡号不能为空");
        zh_HK.setProperty("cardnull", "สลับไอดี");
        fr.setProperty("cardnull", "Le numéro de carte ne peut pas être vide");
        pt.setProperty("cardnull", "Número de cartão não pode ficar em branco");
        de.setProperty("cardnull", "Karten-Nr. darf nicht leer sein");
        es.setProperty("cardnull", "El número de tarjeta no puede quedar en blanco");
        it.setProperty("cardnull", "Il numero della carta non può essere vuoto");
        ja.setProperty("cardnull", "カードナンバーを入力しなければいけません");
        id.setProperty("cardnull", "Nomor kartu harus diisi");


        zh_CN.setProperty("nutspayinfo", "Ncoin说明:\n" +
                "1.使用Ncoin充值，比同额度卡充值多10%钻石\n" +
                "2.Ncoin通过专属通道获得，具体可通过Fanpage咨询客服");
        th.setProperty("nutspayinfo", "อธิบาย Ncoin\n" +
                "1.เติมเงินผ่าน Ncoin จะได้รับเพชรมากกว่าบัตรเติมเงินอื่น ๆ 10%\n" +
                "2.สามารถขอรับวิธีเติม Ncoin ได้ที่ Facebook Fanpage ");
        vi.setProperty("nutspayinfo", "Giới thiệu Ncoin:\n" +
                "1. Nạp qua Ncoin, sẽ được thêm 10% kim cương\n" +
                "2. Ncoin sử dụng kênh chuyên biệt, chi tiết xin liên hệ GM thông qua Fanpage");
        ar.setProperty("nutspayinfo", "卡号不能为空");
        en.setProperty("nutspayinfo", "Ncoin说明:\n" +
                "1.使用Ncoin充值，比同额度卡充值多10%钻石\n" +
                "2.Ncoin通过专属通道获得，具体可通过Fanpage咨询客服");
        ko.setProperty("nutspayinfo", "卡号不能为空");
        zh_HK.setProperty("nutspayinfo", "Ncoin說明：\n" +
                "1。使用Ncoin儲值，比同額度卡儲值多10％鑽石\n" +
                "2.Ncoin通過專屬通道獲得，詳細辦法可通過粉絲專頁洽詢客服");
        fr.setProperty("nutspayinfo", "mua");
        pt.setProperty("nutspayinfo", "mua");
        de.setProperty("nutspayinfo", "mua");
        es.setProperty("nutspayinfo", "mua");
        it.setProperty("nutspayinfo", "mua");
        ja.setProperty("nutspayinfo", "mua");
        id.setProperty("nutspayinfo", "mua");

        //新增的提示说明
        zh_CN.setProperty("login_first", "请先登录");
        th.setProperty("login_first", "login first");
        vi.setProperty("login_first", "login first");
        ar.setProperty("login_first", "login first");
        en.setProperty("login_first", "login first");
        ko.setProperty("login_first", "login first");
        zh_HK.setProperty("login_first", "login first");
        fr.setProperty("login_first", "login first");
        pt.setProperty("login_first", "login first");
        de.setProperty("login_first", "login first");
        es.setProperty("login_first", "login first");
        it.setProperty("login_first", "login first");
        ja.setProperty("login_first", "login first");
        id.setProperty("login_first", "login first");

        zh_CN.setProperty("str_bind_account_tip", "游戏账号仅供使用，为了您的账户安全，请及时绑定注册账号");
        zh_CN.setProperty("str_bind_account", "绑定账户");
        zh_CN.setProperty("str_enter_game", "进入游戏");
        zh_CN.setProperty("check_your_network", "请求失败，请检查您的网络连接");


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

        zh_CN.setProperty("str_bind_facebook","绑定Facebook");
        zh_HK.setProperty("str_bind_facebook","綁定Facebook");
        en.setProperty("str_bind_facebook","Bind Facebook");

        zh_CN.setProperty("str_reset_pwd","重置密码");
        zh_HK.setProperty("str_reset_pwd","重置密碼");
        en.setProperty("str_reset_pwd","Reset Password");


        en.setProperty("str_reset","Reset");

        zh_CN.setProperty("reset_pwd","重置密码");
        en.setProperty("reset_pwd","Reset Password");

        //发送邮箱验证码成功
        en.setProperty("sendVerifySuccess","Send successfully, please go to your mailbox to check the verification code.");

        //绑定邮箱成功
        en.setProperty("bindEmailSuccess","Bind mailbox successfully.");

        //重置密码成功
        en.setProperty("resetPwdSuccess","Reset password successfully.");


        //提醒用户绑定邮箱
        en.setProperty("bind_email_tips","Bind your account and mailbox as soon as possible in the user center so that you can retrieve your password in the future.");
        zh_CN.setProperty("bind_email_tips","尽快在用户中心绑定将您的账号与邮箱绑定，以便在将来可以找回您的密码");
        zh_HK.setProperty("bind_email_tips","盡快在用戶中心綁定將您的賬號與郵箱綁定，以便在將來可以找回您的密碼");
        ja.setProperty("bind_email_tips","ユーザーセンターでアカウントとメールボックスをできるだけ早くバインドして、将来パスワードを取得できるようにします");
        ru.setProperty("bind_email_tips","Свяжите свою учетную запись и почтовый ящик как можно скорее в центре пользователя, чтобы вы могли восстановить свой пароль в будущем");
        ko.setProperty("bind_email_tips","나중에 비밀번호를 검색 할 수 있도록 사용자 센터에서 가능한 빨리 계정과 메일 함을 바인드하십시오.");
        it.setProperty("bind_email_tips","Associa il tuo account e la casella di posta il prima possibile nel centro utenti in modo da poter recuperare la password in futuro");
        de.setProperty("bind_email_tips","Binden Sie Ihr Konto und Ihre Mailbox so schnell wie möglich im User Center, damit Sie Ihr Passwort in Zukunft abrufen können");



        //密码输入的密码不一致
        en.setProperty("pwd_different","Passwords are inconsistent.");
        zh_HK.setProperty("pwd_different","密碼不一致.");
        fr.setProperty("pwd_different","Les mots de passe sont incohérents.");
        de.setProperty("pwd_different","Passwörter sind inkonsistent.");
        zh_CN.setProperty("pwd_different","密码不一致.");
        ko.setProperty("pwd_different","암호가 일치하지 않습니다.");
        ru.setProperty("pwd_different","Пароли несовместимы.");
        it.setProperty("pwd_different","Le password sono incoerenti.");
        ja.setProperty("pwd_different","パスワードに矛盾があります.");

    }
}

