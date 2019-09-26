package com.nutsplay.nopagesdk.kernel;


import com.nutsplay.nopagesdk.utils.SDKGameUtils;

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
        int value = SDKGameUtils.getLanguage(SDKKernel.getInstance().getLanguage()) - 1;
        if (value < 0) {
            value = 0;
        }
        return array[value].getProperty(key, key);
    }

    /**
     * zh_cn:中文
     * th：泰语
     * vi：越语
     * ar:阿拉伯语
     */
    public SDKLangConfig() {

        Properties zh_cn = new Properties();
        Properties th = new Properties();
        Properties vi = new Properties();
        Properties ar = new Properties();
        Properties en = new Properties();
        Properties zh_hk = new Properties();
        Properties kr = new Properties();

        Properties fr = new Properties();//法语
        Properties br = new Properties();//葡萄牙语
        Properties de = new Properties();//德
        Properties sp = new Properties();//西班牙
        Properties it = new Properties();//意大利语
        Properties jp = new Properties();//日语
        Properties idn = new Properties();//印度尼西亚语


        array = new Properties[]{zh_cn, en, th, vi, ar, kr, zh_hk, fr, br, de, sp, it, jp, idn};


        zh_cn.setProperty("yuekainfo", "Ncoin Recharge");
        vi.setProperty("yuekainfo", "Đã chọn mua");
        th.setProperty("yuekainfo", "แจ้ง： เติมเงินซื้อบัตรเดือนสามารถใช้บัตรที่มีมูลมค่ามากกว่าเติมเงินได้ ส่วนที่เกินจะกลายเป็น Ncoin และสามารถนำไปใช้ซื้อสินค้าอื่นต่อไปได้");
        ar.setProperty("yuekainfo", "Chose already");
        en.setProperty("yuekainfo", "Chose already");
        kr.setProperty("yuekainfo", "Chose already");
        zh_hk.setProperty("yuekainfo", "Chose already");
        fr.setProperty("yuekainfo", "Carte Mensuelle");
        br.setProperty("yuekainfo", "Cartão Mensal");
        de.setProperty("yuekainfo", "Monatskarte");
        sp.setProperty("yuekainfo", "Tarjeta mensual");
        it.setProperty("yuekainfo", "Carta mensile");
        jp.setProperty("yuekainfo", "マンスリーカード");
        idn.setProperty("yuekainfo", "Monthly Card");


        zh_cn.setProperty("Chose already", "Ncoin Recharge");
        vi.setProperty("Chose already", "Đã chọn mua");
        th.setProperty("Chose already", "เลือกซื้อแล้ว");
        ar.setProperty("Chose already", "Chose already");
        en.setProperty("Chose already", "Chose already");
        kr.setProperty("Chose already", "Chose already");
        zh_hk.setProperty("Chose already", "Chose already");
        fr.setProperty("Chose already", "Chose already");
        br.setProperty("Chose already", "Chose already");
        de.setProperty("Chose already", "Chose already");
        sp.setProperty("Chose already", "Chose already");
        it.setProperty("Chose already", "Chose already");
        jp.setProperty("Chose already", "Chose already");
        idn.setProperty("Chose already", "Chose already");


        zh_cn.setProperty("Ncoin Recharge", "Ncoin Recharge");
        vi.setProperty("Ncoin Recharge", "Nạp qua Ncoin");
        th.setProperty("Ncoin Recharge", "เติมเงินผ่าน Ncoin");
        ar.setProperty("Ncoin Recharge", "Ncoin Recharge");
        en.setProperty("Ncoin Recharge", "Ncoin Recharge");
        kr.setProperty("Ncoin Recharge", "Ncoin Recharge");
        zh_hk.setProperty("Ncoin Recharge", "Ncoin Recharge");
        fr.setProperty("Ncoin Recharge", "CNcoin Recharge");
        br.setProperty("Ncoin Recharge", "Ncoin Recharge");
        de.setProperty("Ncoin Recharge", "Ncoin Recharge");
        sp.setProperty("Ncoin Recharge", "Ncoin Recharge");
        it.setProperty("Ncoin Recharge", "Ncoin Recharge");
        jp.setProperty("Ncoin Recharge", "Ncoin Recharge");
        idn.setProperty("Ncoin Recharge", "Ncoin Recharge");


        zh_cn.setProperty("mua", "Mua");
        vi.setProperty("mua", "Mua");
        th.setProperty("mua", "ซื้อ");
        ar.setProperty("mua", "mua");
        en.setProperty("mua", "mua");
        kr.setProperty("mua", "mua");
        zh_hk.setProperty("mua", "mua");
        fr.setProperty("mua", "mua");
        br.setProperty("mua", "mua");
        de.setProperty("mua", "mua");
        sp.setProperty("mua", "mua");
        it.setProperty("mua", "mua");
        jp.setProperty("mua", "mua");
        idn.setProperty("mua", "mua");

        zh_cn.setProperty("getDomin", "注: 首次充值可获得双倍钻石, 之后每次充值可额外获得10%钻石");
        vi.setProperty("getDomin", "Chú ý: Lần đầu nạp nhận Kim Cương X2, kế tiếp mỗi lần nạp sẽ nhận thêm 10% kim cương");
        th.setProperty("getDomin", "แจ้ง : เติมเงินครั้งแรกจะได้รับเพชร2เท่า ครั้งที่2เป็นต้นไปจะได้รับเพชรเพิ่ม10%");
        ar.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        en.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        kr.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        zh_hk.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        fr.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        br.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        de.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        sp.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        it.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        jp.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");
        idn.setProperty("getDomin", "1st time payment will get double diamonds, and the after will get 10% more of diamonds.");


        zh_cn.setProperty("Pack", "Diamond");
        vi.setProperty("Pack", "Quà");
        th.setProperty("Pack", "กล่อง");
        ar.setProperty("Pack", "Pack");
        en.setProperty("Pack", "Pack");
        kr.setProperty("Pack", "Pack");
        zh_hk.setProperty("Pack", "Pack");
        fr.setProperty("Pack", "Diamante");
        br.setProperty("Pack", "Monatskarte");
        de.setProperty("Pack", "Diamant");
        sp.setProperty("Pack", "Diamante");
        it.setProperty("Pack", "Diamante");
        jp.setProperty("Pack", "ダイヤモンド");
        idn.setProperty("Pack", "Berlian");


        zh_cn.setProperty(" Monthly Card", "Monthly Card");
        vi.setProperty(" Monthly Card", "Thẻ Tháng");
        th.setProperty(" Monthly Card", "บัตรเดือน");
        ar.setProperty(" Monthly Card", "Monthly Card");
        en.setProperty(" Monthly Card", "Monthly Card");
        kr.setProperty(" Monthly Card", "Monthly Card");
        zh_hk.setProperty(" Monthly Card", "Monthly Card");
        fr.setProperty(" Monthly Card", "Carte Mensuelle");
        br.setProperty(" Monthly Card", "Cartão Mensal");
        de.setProperty(" Monthly Card", "Monatskarte");
        sp.setProperty(" Monthly Card", "Tarjeta mensual");
        it.setProperty(" Monthly Card", "Carta mensile");
        jp.setProperty(" Monthly Card", "マンスリーカード");
        idn.setProperty(" Monthly Card", "Monthly Card");

        zh_cn.setProperty("Diamond", "Diamond");
        vi.setProperty("Diamond", "Kim Cương");
        th.setProperty("Diamond", "เพชร");
        ar.setProperty("Diamond", "Diamond");
        en.setProperty("Diamond", "Diamond");
        kr.setProperty("Diamond", "Diamond");
        zh_hk.setProperty("Diamond", "Diamond");
        fr.setProperty("Diamond", "Diamante");
        br.setProperty("Diamond", "Monatskarte");
        de.setProperty("Diamond", "Diamant");
        sp.setProperty("Diamond", "Diamante");
        it.setProperty("Diamond", "Diamante");
        jp.setProperty("Diamond", "ダイヤモンド");
        idn.setProperty("Diamond", "Berlian");

        zh_cn.setProperty("switchAccount", "切换账户");
        vi.setProperty("switchAccount", "Đổi tài khoản");
        th.setProperty("switchAccount", "สลับไอดี");
        ar.setProperty("switchAccount", "تبديل  الحساب");
        en.setProperty("switchAccount", "Switch account");
        kr.setProperty("switchAccount", "계정 전환");
        zh_hk.setProperty("switchAccount", "切換帳號");
        fr.setProperty("switchAccount", "Changer de compte");
        br.setProperty("switchAccount", "Mudar de conta");
        de.setProperty("switchAccount", "Konto wechseln");
        sp.setProperty("switchAccount", "Cambiar cuenta");
        it.setProperty("switchAccount", "Cambia account");
        jp.setProperty("switchAccount", "アカウント変更");
        idn.setProperty("switchAccount", "Ganti Akun");

        zh_cn.setProperty("googles", "Google+");
        th.setProperty("googles", "Google+");
        vi.setProperty("googles", "Google+");
        ar.setProperty("googles", "Google+");
        en.setProperty("googles", "Google+");
        kr.setProperty("googles", "Google+");
        zh_hk.setProperty("googles", "Google+");
        fr.setProperty("googles", "Google+");
        br.setProperty("googles", "Google+");
        de.setProperty("googles", "Google+");
        sp.setProperty("googles", "Google+");
        it.setProperty("googles", "Google+");
        jp.setProperty("googles", "Google+");
        idn.setProperty("googles", "Google+");


        zh_cn.setProperty("userinfos", "用户");
        vi.setProperty("userinfos", "User");
        th.setProperty("userinfos", "บัญชี");
        ar.setProperty("userinfos", "المستخدم");
        en.setProperty("userinfos", "User");
        kr.setProperty("userinfos", "사용자");
        zh_hk.setProperty("userinfos", "會員");
        fr.setProperty("userinfos", "Utilisateur");
        br.setProperty("userinfos", "Usuário");
        de.setProperty("userinfos", "Nutzer");
        sp.setProperty("userinfos", "Usuario");
        it.setProperty("userinfos", "Utente");
        jp.setProperty("userinfos", "ユーザー");
        idn.setProperty("userinfos", "User");


        zh_cn.setProperty("nutsHide", "隐藏");
        vi.setProperty("nutsHide", "Ẩn");
        th.setProperty("nutsHide", " อน");
        ar.setProperty("nutsHide", "ซ่อน");
        en.setProperty("nutsHide", "Hide");
        kr.setProperty("nutsHide", "숨김");
        zh_hk.setProperty("nutsHide", "隱藏");
        fr.setProperty("nutsHide", "Masquer");
        br.setProperty("nutsHide", "Esconder");
        de.setProperty("nutsHide", "Verberg.");
        sp.setProperty("nutsHide", "Esconder");
        it.setProperty("nutsHide", "Nascondi");
        jp.setProperty("nutsHide", "隠す");
        idn.setProperty("nutsHide", "Hide");

        zh_cn.setProperty("bindGoogle", "绑定Google+");
        vi.setProperty("bindGoogle", "Cố định Google+");
        th.setProperty("bindGoogle", "ผูกมัดGoogle+");
        ar.setProperty("bindGoogle", "ربط جوجل");
        en.setProperty("bindGoogle", "Bind Google+");
        kr.setProperty("bindGoogle", "g+와 연동하기");
        zh_hk.setProperty("bindGoogle", "绑定Google+");
        fr.setProperty("bindGoogle", "Lier Google+");
        br.setProperty("bindGoogle", "Vincular Google+");
        de.setProperty("bindGoogle", "Google+ verb.");
        sp.setProperty("bindGoogle", "Vincular Google+");
        it.setProperty("bindGoogle", "Collega Google+");
        jp.setProperty("bindGoogle", "Googe+連携");
        idn.setProperty("bindGoogle", "Bind Google+");


        zh_cn.setProperty("bindSucess", "绑定成功");
        th.setProperty("bindSucess", "ผูกมัดสำเร็จ");
        vi.setProperty("bindSucess", "Cố định thành công");
        ar.setProperty("bindSucess", " ربط  النجاح ");
        en.setProperty("bindSucess", "Binding success");
        kr.setProperty("bindSucess", "연동하기 성공");
        zh_hk.setProperty("bindSucess", "绑定成功");
        fr.setProperty("bindSucess", "Liaison réussie");
        br.setProperty("bindSucess", "Vínculo com sucesso");
        de.setProperty("bindSucess", "Verbunden");
        sp.setProperty("bindSucess", "Vinculado con éxito");
        it.setProperty("bindSucess", "Collegamento riuscito");
        jp.setProperty("bindSucess", "連携成功");
        idn.setProperty("bindSucess", "Binding sukses");

        zh_cn.setProperty("pwsome", "请输入相同的密码");
        th.setProperty("pwsome", "กรุณาใส่รหัสอีกครั้ง");
        vi.setProperty("pwsome", "Nhập mật khẩu tương đồng");
        ar.setProperty("pwsome", "يرجى إدخال نفس كلمة المرور");
        en.setProperty("pwsome", "Enter the same password");
        kr.setProperty("pwsome", "같은 비밀번호를 입력했습니다.");
        zh_hk.setProperty("pwsome", "請輸入相同密碼");
        fr.setProperty("pwsome", "Entrez le même mdp");
        br.setProperty("pwsome", "Insira a mesma senha");
        de.setProperty("pwsome", "Gleiches Passwort eing.");
        sp.setProperty("pwsome", "Introduce la misma contraseña");
        it.setProperty("pwsome", "Inserisci la stessa password");
        jp.setProperty("pwsome", "同じパスワードを入力してください");
        idn.setProperty("pwsome", "Masukkan password yang sama");


        zh_cn.setProperty("exit", "退出");
        th.setProperty("exit", "ออก");
        vi.setProperty("exit", "Thoát");
        ar.setProperty("exit", "تسجيل الخروج");
        en.setProperty("exit", "Exit");
        kr.setProperty("exit", "나가기");
        zh_hk.setProperty("exit", "退出");
        fr.setProperty("exit", "Sortir");
        br.setProperty("exit", "Sair");
        de.setProperty("exit", "Verlassen");
        sp.setProperty("exit", "Verlassen");
        it.setProperty("exit", "Esci");
        jp.setProperty("exit", "中止");
        idn.setProperty("exit", "Keluar");


        zh_cn.setProperty("exitmessage", "您想要退出游吗?");
        th.setProperty("exitmessage", "ต้องการออกเกมหรือไม่?");
        vi.setProperty("exitmessage", "Muốn thoát game??");
        ar.setProperty("exitmessage", "هل تود الخروج من اللعبة؟");
        en.setProperty("exitmessage", "Do you want to quit the game??");
        kr.setProperty("exitmessage", "게임을 종료하시겠습니까?");
        zh_hk.setProperty("exitmessage", "您想要退出遊戲嗎？");
        fr.setProperty("exitmessage", "Diamante");
        br.setProperty("exitmessage", "Monatskarte");
        de.setProperty("exitmessage", "Diamant");
        sp.setProperty("exitmessage", "Diamante");
        it.setProperty("exitmessage", "Diamante");
        jp.setProperty("exitmessage", "ダイヤモンド");
        idn.setProperty("exitmessage", "Berlian");


        zh_cn.setProperty("cancel", "取消");
        th.setProperty("cancel", "ยกเลิก");
        vi.setProperty("cancel", "Hủy");
        ar.setProperty("cancel", "تراجع");
        en.setProperty("cancel", "Cancel");
        kr.setProperty("cancel", "취소");
        zh_hk.setProperty("cancel", "取消");
        fr.setProperty("cancel", "Annuler");
        br.setProperty("cancel", "Cancelar");
        de.setProperty("cancel", "Abbrechen");
        sp.setProperty("cancel", "Cancelar");
        it.setProperty("cancel", "Cancella");
        jp.setProperty("cancel", "キャンセル");
        idn.setProperty("cancel", "Batal");

        zh_cn.setProperty("account_message", "检测到登录多账户，请选择以下一个账户进行游戏");
        th.setProperty("account_message", "ตรวจพบล็อกอินหลายไอดีด กรุณาเลือกไอดีที่ต้องการใช้เพียง 1 ไอดี");
        vi.setProperty("account_message", "Phát hiện đăng nhập nhiều tài khoản, hãy chọn ra 1 tài khoản để vào game");
        ar.setProperty("account_message", "تم التفطن إلى أكثر من تسجيل دخول متزامن الرجاء اختيار واحد من الحسابات التالية لدخول اللعبة");
        en.setProperty("account_message", "Multiple sign-in is detected, Please select one of the following accounts to play the game");
        kr.setProperty("account_message", "여러 계정은 로그인 되어있으므로 한 계정만 선택해서 등록해주십시오");
        zh_hk.setProperty("account_message", "偵測到登入重複帳號，請選擇以下任一帳號進入遊戲");
        fr.setProperty("account_message", "Enregistrement multiple détecté, veuillez sélectionner l’un des comptes suivants pour jouer au jeu");
        br.setProperty("account_message", "Múltiplos registos foram detectados, selecione uma das seguintes contas para jogar.");
        de.setProperty("account_message", "Mehrfach angemeldet, bitte wähle eines dieser Konten zum Spielen.");
        sp.setProperty("account_message", "Accesos múltiples detectados. Por favor, elige una de las siguientes cuentas para jugar.");
        it.setProperty("account_message", "Rilevati accessi multipli. Seleziona uno dei seguenti account per giocare");
        jp.setProperty("account_message", "複数のアカウントがサインイン状態であることが検出されました。以下のアカウントから一つ選んでプレイしてください。");
        idn.setProperty("account_message", "Login multiple akun, silahkan pilih salah satu akun untuk memulai permainan");

        zh_cn.setProperty("pwInfomessage", "修改密码成功，请输入账户和新的密码进行登录验证");
        th.setProperty("pwInfomessage", "แก้รหัสสำเร็จ กรุณาใส่ไอดีและรหัสใหม่ยืนยันการ");
        vi.setProperty("pwInfomessage", "Đổi mật khẩu thành công, hãy nhập tài khoản và mật khẩu mới để xác nhận đăng nhập");
        ar.setProperty("pwInfomessage", "تم تغيير كلمة المرور,الرجاء إدخال اسم مستخدم وكلمة مرور جديدة للمصادقة على تسجيل الدخول");
        en.setProperty("pwInfomessage", "password has been updated, Please enter an account and a new password for login verification");
        kr.setProperty("pwInfomessage", "비밀번호가 변경되었습니다.변경된 계정과 비밀번호로 다시 등록해주십시오이");
        zh_hk.setProperty("pwInfomessage", "修改密碼成功，請輸入帳號和新的密碼進行登入驗證");
        fr.setProperty("pwInfomessage", "Mot de passe changé. Veuillez entrer votre compte et le mot de passe pour une vérification de connexion.");
        br.setProperty("pwInfomessage", "A senha foi atualizada. Por favor insira a sua conta e a nova senha para verificação de acesso.");
        de.setProperty("pwInfomessage", "Passwort aktualisiert. Bitte Konto und das neue Passwort zum Bestätigen angeben.");
        sp.setProperty("pwInfomessage", "Contraseña actualizada. Introduce tu cuenta y tu nueva contraseña.");
        it.setProperty("pwInfomessage", "Password aggiornata. Inserisci l'account e la nuova password per verificare l'accesso");
        jp.setProperty("pwInfomessage", "パスワードを変更しました。アカウントと新しいパスワードを入力してログインしてください");
        idn.setProperty("pwInfomessage", "Password telah diubah.Silahkan masukkan akun dan password baru untuk verifikasi login.");


        zh_cn.setProperty("pwInfo", "此功能需要已经绑定邮箱的账户才能使用，如果未绑定邮箱，请及时联系客服");
        th.setProperty("pwInfo", "ฟังก์ชั่นดังกล่าวต้องผูกมัด E-mail ก่อนจึงจะใช้ได้ หากยังไม่ได้ผูกมัด กรุณาติดต่อฝ่ายบริการ");
        vi.setProperty("pwInfo", "Cần cố định Email mới có thể sử dụng chức năng này, nếu chưa cố định, hãy liên hệ CSKH");
        ar.setProperty("pwInfo", "هذه الخاصية تحتاج إلى حساب موثق ببريد إلكتروني إذا كان الحساب غير موثق ببريد إلكتروني الرجاء الاتصال بخدمة العملاء");
        en.setProperty("pwInfo", "This function requires an account that is already bound to the mailbox,If the mailbox is not bound,Please contact customer service");
        kr.setProperty("pwInfo", "이 기능은 이메일이 연동되어있는 계정만 사용할 수 있으므로 연동되지 않은 경우에는 고객 센터에 연락해 주시기 바랍니다");
        zh_hk.setProperty("pwInfo", "此功能需要已經綁定電子信箱的帳號才能使用，如果未綁定電子信箱，請聯繫客服");
        fr.setProperty("pwInfo", "Cette fonction nécessite un compte qui est déjà lié par email. Si l’email n’est pas lié, veuillez contacter le service clientèle.");
        br.setProperty("pwInfo", "Esta função requer uma conta já vinculada a uma conta de e-mail. Se o e-mail não estivere vinculado,por favor contacte o apoio ao cliente.");
        de.setProperty("pwInfo", "Dieses Funktion erfordert ein bereits mit der Mailbox verbundenes Konto. Ist die Mailbox nicht verbunden, wende dich an den Kundendienst.");
        sp.setProperty("pwInfo", "Esta función requiere una cuenta ya vinculada. Si la cuenta no está vinculada, por favor, contacta con atención al cliente.");
        it.setProperty("pwInfo", "Questa funzione necessita di un account collegato alla casella email. Se la casella email non è collegata, contatta il servizio clienti");
        jp.setProperty("pwInfo", "メールアドレスに連携したプレーヤーしかこの機能を体験できません。まだメールアドレスに連携されていなければ、サポートにご連絡ください");
        idn.setProperty("pwInfo", "Berlaku untuk akun yang sudah binding email.Bila belum melakukan binding email, silahkan hubungi customer service.");

        zh_cn.setProperty("bindFacebookSucess", "当前账户绑定Facebook成功,下次登录游戏点击游客登录将会生成一个新的游戏账户");
        th.setProperty("bindFacebookSucess", "ขณะนี้ผูกมัด Facebookสำเร็จ ในการล็อกอินครั้งหน้ากดเยี่ยมชม จะเป็นการสร้างไอดีใหม่");
        vi.setProperty("bindFacebookSucess", "Tài khoản cố định Facebook thành công, lần đăng nhập sau nếu nhấn đăng nhập chơi thử sẽ xuất hiện 1 tài khoản mới");
        ar.setProperty("bindFacebookSucess", "تم ربط الحساب بالفايسبوك بنجاح,يؤدي النقر على حساب زائر  إلى إنشاء حساب جديد عند تسجيل الدخول القادم!");
        en.setProperty("bindFacebookSucess", "The current account bind Facebook successfully,Clicking on Guest Login will generate a new game account at next login");
        kr.setProperty("bindFacebookSucess", "현재 Facebook 계정은 연동되어있으며 다음에 게임을 로그인할때는 자동적으로 새로운 계정이 생성될겁니다");
        zh_hk.setProperty("bindFacebookSucess", "該帳號成功綁定Facebook，下次登入遊戲時, 點擊遊客登入將會產生一個新的遊戲帳號");
        fr.setProperty("bindFacebookSucess", "Lias on du compte avec Facebook réussie. Appuyez sur Connexion Invité générera un nouveau compte à la prochaine connexion.");
        br.setProperty("bindFacebookSucess", "A conta atual foi vinculada ao Facebook com sucesso. Clicar em Acesso de Convidado irá gerar uma nova conta na próxima vez.");
        de.setProperty("bindFacebookSucess", "Aktuelles Konto mit Facebook verbunden. Über Gastanmeldung wird beim nächsten Mal ein neues Konto erzeugt.");
        sp.setProperty("bindFacebookSucess", "La cuenta actual ha sido vinculada con Facebook. Toca Acceso de invitado para generar una nueva cuenta al entrar.");
        it.setProperty("bindFacebookSucess", "L'account selezionato è stato collegato a Facebook. Toccare Login Ospite genererà un nuovo account al prossimo accesso");
        jp.setProperty("bindFacebookSucess", "このアカウントをFacebookに連携しました、次回ゲームにログインする時に、ゲストアカウントをクリックすると、新しいアカウントが現れます");
        idn.setProperty("bindFacebookSucess", "Binding Facebook sukses.Berikutnya tap Guest Login akan membuat akun login baru.");


        th.setProperty("sdk_initial_error", "เริ่มต้นล้มเหลว");
        vi.setProperty("sdk_initial_error", "Khởi động thất bại");
        ar.setProperty("sdk_initial_error", "فشل التهيئة");
        en.setProperty("sdk_initial_error", "Initialization failed");
        kr.setProperty("sdk_initial_error", "초기화 실패");
        zh_hk.setProperty("sdk_initial_error", "初始化失敗");
        fr.setProperty("sdk_initial_error", "Echec d’initialisation");
        br.setProperty("sdk_initial_error", "Falha na inicialização");
        de.setProperty("sdk_initial_error", "Initialisierung fehlgeschl.");
        sp.setProperty("sdk_initial_error", "Inicialización fallida");
        it.setProperty("sdk_initial_error", "Inizializzazione Fallita");
        jp.setProperty("sdk_initial_error", "初期化失敗");
        idn.setProperty("sdk_initial_error", "Inisialisasi gagal");

        zh_cn.setProperty("50", "正在绑定...");
        th.setProperty("50", "กำลังผูกมัด");
        vi.setProperty("50", "Đang cố định…");
        ar.setProperty("50", "بصدد التوثيق");
        en.setProperty("50", "Binding...");
        kr.setProperty("50", "연동하기 중...");
        zh_hk.setProperty("50", "正在綁定…");
        fr.setProperty("50", "Liaison...");
        br.setProperty("50", "A vincular...");
        de.setProperty("50", "Verbinden …");
        sp.setProperty("50", "Vinculando…");
        it.setProperty("50", "Collegamento in corso...");
        jp.setProperty("50", "連携中…");
        idn.setProperty("50", "Binding...");

        zh_cn.setProperty("51", "账户已经被绑定");
        th.setProperty("51", "บัญชีผูกติดแล้ว");
        vi.setProperty("51", "Tài khoản đã được cố định");
        ar.setProperty("51", "الحساب موثق");
        en.setProperty("51", "The account has been bound");
        kr.setProperty("51", "계정이 연동되었습니다");
        zh_hk.setProperty("51", "帳號已被使用過");
        fr.setProperty("51", "Le compte a été lié");
        br.setProperty("51", "A conta foi vinculada.");
        de.setProperty("51", "Konto ist verbunden");
        sp.setProperty("51", "La cuenta ha sido vinculada");
        it.setProperty("51", "L'account è stato collegato");
        jp.setProperty("51", "このアカウントは既に連携されています");
        idn.setProperty("51", "Akun sudah dibinding");

        zh_cn.setProperty("nutsplay_viewstring_signin", "登录");
        th.setProperty("nutsplay_viewstring_signin", "เข้าสู่ระบบ");
        vi.setProperty("nutsplay_viewstring_signin", "Đăng nhập");
        ar.setProperty("nutsplay_viewstring_signin", "تسجيل الدخول");
        en.setProperty("nutsplay_viewstring_signin", "log in");
        kr.setProperty("nutsplay_viewstring_signin", "로그인");
        zh_hk.setProperty("nutsplay_viewstring_signin", "登入");
        fr.setProperty("nutsplay_viewstring_signin", "Connecter");
        br.setProperty("nutsplay_viewstring_signin", "Acesso");
        de.setProperty("nutsplay_viewstring_signin", "Anm.");
        sp.setProperty("nutsplay_viewstring_signin", "Acceder");
        it.setProperty("nutsplay_viewstring_signin", "Accedi");
        jp.setProperty("nutsplay_viewstring_signin", "ログイン");
        idn.setProperty("nutsplay_viewstring_signin", "Login");

        zh_cn.setProperty("nutsplay_viewstring_touristsignin", "游客登录");
        th.setProperty("nutsplay_viewstring_touristsignin", "ผู้เยี่ยมชม");
        vi.setProperty("nutsplay_viewstring_touristsignin", "Chơi ngay");
        ar.setProperty("nutsplay_viewstring_touristsignin", "تسجيل دخول حساب زائر");
        en.setProperty("nutsplay_viewstring_touristsignin", "Quick Play");
        kr.setProperty("nutsplay_viewstring_touristsignin", "쾌속 플레이");
        zh_hk.setProperty("nutsplay_viewstring_touristsignin", "遊客登入");
        fr.setProperty("nutsplay_viewstring_touristsignin", "Partie Rapide");
        br.setProperty("nutsplay_viewstring_touristsignin", "Jogo rápido");
        de.setProperty("nutsplay_viewstring_touristsignin", "Schnellspiel");
        sp.setProperty("nutsplay_viewstring_touristsignin", "Partida rápida");
        it.setProperty("nutsplay_viewstring_touristsignin", "Partita Rapida");
        jp.setProperty("nutsplay_viewstring_touristsignin", "ゲスト");
        idn.setProperty("nutsplay_viewstring_touristsignin", "Guest Login");

        zh_cn.setProperty("nutsplay_viewstring_signup", "注册账户");
        th.setProperty("nutsplay_viewstring_signup", "สมัคร");
        vi.setProperty("nutsplay_viewstring_signup", "Đăng ký");
        ar.setProperty("nutsplay_viewstring_signup", "تسجيل حساب");
        en.setProperty("nutsplay_viewstring_signup", "Register");
        kr.setProperty("nutsplay_viewstring_signup", "등록");
        zh_hk.setProperty("nutsplay_viewstring_signup", "註冊帳號");
        fr.setProperty("nutsplay_viewstring_signup", "S’inscrire");
        br.setProperty("nutsplay_viewstring_signup", "Registo");
        de.setProperty("nutsplay_viewstring_signup", "Registrieren");
        sp.setProperty("nutsplay_viewstring_signup", "Registrar");
        it.setProperty("nutsplay_viewstring_signup", "Registrati");
        jp.setProperty("nutsplay_viewstring_signup", "登録");
        idn.setProperty("nutsplay_viewstring_signup", "Daftar");

        zh_cn.setProperty("nutsplay_viewstring_ResetPassword", "找回密码");
        th.setProperty("nutsplay_viewstring_ResetPassword", "ลืมรหัสผ่าน");
        vi.setProperty("nutsplay_viewstring_ResetPassword", "Tìm lại mật khẩu>");
        ar.setProperty("nutsplay_viewstring_ResetPassword", "نسيت كلمة المرور");
        en.setProperty("nutsplay_viewstring_ResetPassword", "Forgot password");
        kr.setProperty("nutsplay_viewstring_ResetPassword", "비밀 번호 찾기");
        zh_hk.setProperty("nutsplay_viewstring_ResetPassword", "忘記密碼");
        fr.setProperty("nutsplay_viewstring_ResetPassword", "Mot de passe oublié");
        br.setProperty("nutsplay_viewstring_ResetPassword", "Esqueci a senha");
        de.setProperty("nutsplay_viewstring_ResetPassword", "Passwort vergessen");
        sp.setProperty("nutsplay_viewstring_ResetPassword", "Olvidé mi contraseña");
        it.setProperty("nutsplay_viewstring_ResetPassword", "Password dimenticata");
        jp.setProperty("nutsplay_viewstring_ResetPassword", "パスワードを忘れた");
        idn.setProperty("nutsplay_viewstring_ResetPassword", "Lupa Password");

        zh_cn.setProperty("nutsplay_viewstring_confirm", "确定");
        th.setProperty("nutsplay_viewstring_confirm", "ยืนยัน");
        vi.setProperty("nutsplay_viewstring_confirm", "Xác nhận");
        ar.setProperty("nutsplay_viewstring_confirm", "تحديد");
        en.setProperty("nutsplay_viewstring_confirm", "Determine");
        kr.setProperty("nutsplay_viewstring_confirm", "확정");
        zh_hk.setProperty("nutsplay_viewstring_confirm", "確定");
        fr.setProperty("nutsplay_viewstring_confirm", "Confirmer");
        br.setProperty("nutsplay_viewstring_confirm", "Confirmar");
        de.setProperty("nutsplay_viewstring_confirm", "Bestätigen");
        sp.setProperty("nutsplay_viewstring_confirm", "Confirmar");
        it.setProperty("nutsplay_viewstring_confirm", "Conferma");
        jp.setProperty("nutsplay_viewstring_confirm", "確認");
        idn.setProperty("nutsplay_viewstring_confirm", "Yakin");


        zh_cn.setProperty("nutsplay_viewstring_usercenter", "用户中心");
        th.setProperty("nutsplay_viewstring_usercenter", "ศูนย์ผู้เล่น");
        vi.setProperty("nutsplay_viewstring_usercenter", "Use");
        ar.setProperty("nutsplay_viewstring_usercenter", "مركز المستخدم");
        en.setProperty("nutsplay_viewstring_usercenter", "User Center");
        kr.setProperty("nutsplay_viewstring_usercenter", "사용자 센터");
        zh_hk.setProperty("nutsplay_viewstring_usercenter", "會員中心");
        fr.setProperty("nutsplay_viewstring_usercenter", "Centre d’Utilisateur");
        br.setProperty("nutsplay_viewstring_usercenter", "Centro de Usuário");
        de.setProperty("nutsplay_viewstring_usercenter", "Nutzerzentrum");
        sp.setProperty("nutsplay_viewstring_usercenter", "Centro de usuario");
        it.setProperty("nutsplay_viewstring_usercenter", "Centro utente");
        jp.setProperty("nutsplay_viewstring_usercenter", "ユーザーセンター");
        idn.setProperty("nutsplay_viewstring_usercenter", "Layanan");

        zh_cn.setProperty("nutsplay_viewstring_cardno", "请输入卡号");
        th.setProperty("nutsplay_viewstring_cardno", "กรุณาระบุหมายเลขบัตร");
        vi.setProperty("nutsplay_viewstring_cardno", "Nhập số seri thẻ");
        ar.setProperty("nutsplay_viewstring_cardno", "الرجاء إدخال كلمة مرور البطاقة");
        en.setProperty("nutsplay_viewstring_cardno", "Please enter the card password");
        kr.setProperty("nutsplay_viewstring_cardno", "카드 번호를 입력해 주세요");
        zh_hk.setProperty("nutsplay_viewstring_cardno", "請輸入卡號");
        fr.setProperty("nutsplay_viewstring_cardno", "Entrez le numéro de carte");
        br.setProperty("nutsplay_viewstring_cardno", "Inserir o número do cartão");
        de.setProperty("nutsplay_viewstring_cardno", "Kartennummer eingeben");
        sp.setProperty("nutsplay_viewstring_cardno", "Introduce el número de tarjeta");
        it.setProperty("nutsplay_viewstring_cardno", "Inserisci il numero carta");
        jp.setProperty("nutsplay_viewstring_cardno", "カードナンバーを入力してください");
        idn.setProperty("nutsplay_viewstring_cardno", "Masukkan nomor kartu");

        zh_cn.setProperty("nutsplay_viewstring_cardpass", "请输入卡密码");
        th.setProperty("nutsplay_viewstring_cardpass", "กรุณาระบุรหัสบัตร");
        vi.setProperty("nutsplay_viewstring_cardpass", "Nhập mã số thẻ");
        ar.setProperty("nutsplay_viewstring_cardpass", "الرجاء إدخال رقم البطاقة");
        en.setProperty("nutsplay_viewstring_cardpass", "Please enter the card number");
        kr.setProperty("nutsplay_viewstring_cardpass", "비밀번호를 입력해 주세요");
        zh_hk.setProperty("nutsplay_viewstring_cardpass", "請輸入密碼");
        fr.setProperty("nutsplay_viewstring_cardpass", "Entrez le mdp de carte");
        br.setProperty("nutsplay_viewstring_cardpass", "Inserir a senha do cartão");
        de.setProperty("nutsplay_viewstring_cardpass", "Kartenpasswort eingeben");
        sp.setProperty("nutsplay_viewstring_cardpass", "Introduce la contraseña de la tarjeta");
        it.setProperty("nutsplay_viewstring_cardpass", "Inserisci la password carta");
        jp.setProperty("nutsplay_viewstring_cardpass", "カードパスワードを入力してください");
        idn.setProperty("nutsplay_viewstring_cardpass", "Masukkan password kartu");


        zh_cn.setProperty("nutsplay_viewstring_recharge", "充值");
        th.setProperty("nutsplay_viewstring_recharge", "เติมเงิน");
        vi.setProperty("nutsplay_viewstring_recharge", "Nạp");
        ar.setProperty("nutsplay_viewstring_recharge", "إعادة شحن");
        en.setProperty("nutsplay_viewstring_recharge", "Recharge");
        kr.setProperty("nutsplay_viewstring_recharge", "충전");
        zh_hk.setProperty("nutsplay_viewstring_recharge", "儲值");
        fr.setProperty("nutsplay_viewstring_recharge", "Recharger");
        br.setProperty("nutsplay_viewstring_recharge", "Recarregar");
        de.setProperty("nutsplay_viewstring_recharge", "Aufladen");
        sp.setProperty("nutsplay_viewstring_recharge", "Recargar");
        it.setProperty("nutsplay_viewstring_recharge", "Ricarica");
        jp.setProperty("nutsplay_viewstring_recharge", "チャージ");
        idn.setProperty("nutsplay_viewstring_recharge", "TopUp");


        zh_cn.setProperty("nutsplay_viewstring_cardpay", "充值卡");
        th.setProperty("nutsplay_viewstring_cardpay", "เติมเงิน");
        vi.setProperty("nutsplay_viewstring_cardpay", "Thẻ nạp");
        ar.setProperty("nutsplay_viewstring_cardpay", "إعادة شحن البطاقة");
        en.setProperty("nutsplay_viewstring_cardpay", "Recharge card");
        kr.setProperty("nutsplay_viewstring_cardpay", "충전 카드");
        zh_hk.setProperty("nutsplay_viewstring_cardpay", "儲值卡");
        fr.setProperty("nutsplay_viewstring_cardpay", "Carte de Recharge");
        br.setProperty("nutsplay_viewstring_cardpay", "Cartão de recarga");
        de.setProperty("nutsplay_viewstring_cardpay", "Karte aufladen");
        sp.setProperty("nutsplay_viewstring_cardpay", "Recargar tarjeta");
        it.setProperty("nutsplay_viewstring_cardpay", "Ricarica carta");
        jp.setProperty("nutsplay_viewstring_cardpay", "チャージカード");
        idn.setProperty("nutsplay_viewstring_cardpay", "Kartu TopUp");

        zh_cn.setProperty("nutsplay_viewstring_choose_payment_style", "选择充值方式:");
        th.setProperty("nutsplay_viewstring_choose_payment_style", "เลือกวิธีการเติมเงิน");
        vi.setProperty("nutsplay_viewstring_choose_payment_style", "Chọn phương thức nạp:");
        ar.setProperty("nutsplay_viewstring_choose_payment_style", "تحديد وسيلة الشحن:");
        en.setProperty("nutsplay_viewstring_choose_payment_style", "Choose the top-up method:");
        kr.setProperty("nutsplay_viewstring_choose_payment_style", "충전하는 수단을 선택해주십시오");
        zh_hk.setProperty("nutsplay_viewstring_choose_payment_style", "選擇儲值方式：");
        fr.setProperty("nutsplay_viewstring_choose_payment_style", "Sélectionnez le Mode de Paiement :");
        br.setProperty("nutsplay_viewstring_choose_payment_style", "Selecionar Método de Pagamento:");
        de.setProperty("nutsplay_viewstring_choose_payment_style", "Zahlungsmethode ausw.:");
        sp.setProperty("nutsplay_viewstring_choose_payment_style", "Elegir medio de pago:");
        it.setProperty("nutsplay_viewstring_choose_payment_style", "Scegli metodo di pagamento");
        jp.setProperty("nutsplay_viewstring_choose_payment_style", "チャージ方法を選んでください");
        idn.setProperty("nutsplay_viewstring_choose_payment_style", "Pilih Metode TopUp:");

        zh_cn.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin余额:");
        th.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin คงเหลือ:");
        vi.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin còn:");
        ar.setProperty("nutsplay_viewstring_pay_ncoin_balance", "رصيد الـ Ncoin");
        en.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin Balance:");
        kr.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin잔액");
        zh_hk.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin餘額：");
        fr.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Solde de Ncoin :");
        br.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Saldo de Ncoin:");
        de.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin-Guthaben:");
        sp.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Balance Ncoin:");
        it.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Saldo Ncoin");
        jp.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Ncoin残高：");
        idn.setProperty("nutsplay_viewstring_pay_ncoin_balance", "Nominal Ncoin:");


        zh_cn.setProperty("nutsplay_viewstring_pay_consume_coin", "本次消费:");
        th.setProperty("nutsplay_viewstring_pay_consume_coin", "ใช้จ่ายครั้งนี:");
        vi.setProperty("nutsplay_viewstring_pay_consume_coin", "Tiêu phí:");
        ar.setProperty("nutsplay_viewstring_pay_consume_coin", "الاستهلاك :");
        en.setProperty("nutsplay_viewstring_pay_consume_coin", "The consumption:");
        kr.setProperty("nutsplay_viewstring_pay_consume_coin", "이번 비용:");
        zh_hk.setProperty("nutsplay_viewstring_pay_consume_coin", "本次消費：");
        fr.setProperty("nutsplay_viewstring_pay_consume_coin", "La consommation :");
        br.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        de.setProperty("nutsplay_viewstring_pay_consume_coin", "Verbrauch:");
        sp.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        it.setProperty("nutsplay_viewstring_pay_consume_coin", "Consumo:");
        jp.setProperty("nutsplay_viewstring_pay_consume_coin", "今回の消費：");
        idn.setProperty("nutsplay_viewstring_pay_consume_coin", "Pemakaian:");

        zh_cn.setProperty("nutsplay_viewstring_signining", "登录中...");
        vi.setProperty("nutsplay_viewstring_signining", "Đang đăng nhập...:");
        th.setProperty("nutsplay_viewstring_signining", "กำลังเข้าสู่ระบบ...");
        ar.setProperty("nutsplay_viewstring_signining", "تسجيل الدخول ...");
        en.setProperty("nutsplay_viewstring_signining", "Login...");
        kr.setProperty("nutsplay_viewstring_signining", "로그인 중...");
        zh_hk.setProperty("nutsplay_viewstring_signining", "登入中…");
        fr.setProperty("nutsplay_viewstring_signining", "Connexion...");
        br.setProperty("nutsplay_viewstring_signining", "Acedendo...");
        de.setProperty("nutsplay_viewstring_signining", "Anm. …");
        sp.setProperty("nutsplay_viewstring_signining", "Accediendo…");
        it.setProperty("nutsplay_viewstring_signining", "Accesso in corso...");
        jp.setProperty("nutsplay_viewstring_signining", "ログイン中…");
        idn.setProperty("nutsplay_viewstring_signining", "Login....");

        zh_cn.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登录中...");
        vi.setProperty("nutsplay_viewstring_signining_fb", "Facebook Đang đăng nhập...:");
        th.setProperty("nutsplay_viewstring_signining_fb", "Facebook กำลังเข้าสู่ระบบ...");
        ar.setProperty("nutsplay_viewstring_signining_fb", "تسجيل الدخول بالفايسبوك ...");
        en.setProperty("nutsplay_viewstring_signining_fb", "Facebook Login ...");
        kr.setProperty("nutsplay_viewstring_signining_fb", "Facebook 로그인 중...");
        zh_hk.setProperty("nutsplay_viewstring_signining_fb", "Facebook 登入中...");
        fr.setProperty("nutsplay_viewstring_signining_fb", "Connexion Facebook...");
        br.setProperty("nutsplay_viewstring_signining_fb", "Acessar com Facebook...");
        de.setProperty("nutsplay_viewstring_signining_fb", "Facebook-Anm.");
        sp.setProperty("nutsplay_viewstring_signining_fb", "Accediendo con Facebook...");
        it.setProperty("nutsplay_viewstring_signining_fb", "Accedendo con Facebook...");
        jp.setProperty("nutsplay_viewstring_signining_fb", "Facebookログイン中…");
        idn.setProperty("nutsplay_viewstring_signining_fb", "Facebook Login...");

        zh_cn.setProperty("tourist_signin_alert", "游戏账号仅供使用，为了您的账户安全，请及时绑定注册账号");
        vi.setProperty("tourist_signin_alert", "Vì an toàn, hãy cố định tài khoản");
        th.setProperty("tourist_signin_alert", "บัญชีนี้เป็นบัญชีที่ใช้ร่วมกัน เพื่อความปลอดภัยของบัญชีท่าน กรุณาทำการผูกมัดในทันที");
        ar.setProperty("tourist_signin_alert", "حسابك هنا معد فقط للاستخدام في اللعبة، ينصح بتوثيق حسابك لتأمينه وحمايته");
        en.setProperty("tourist_signin_alert", "The game account is only for use, for your account security, please bind the registered account");
        kr.setProperty("tourist_signin_alert", "이 계정을 안전하게 사용하기를 위하여 등록한 계정번호를 연동해주시기 바랍니다");
        zh_hk.setProperty("tourist_signin_alert", "遊客帳號僅供使用，為了您的帳號安全，請及時綁定註冊帳號");
        fr.setProperty("tourist_signin_alert", "Le compte de jeu est destiné uniquement à l’utilisation, pour la sécurité de votre compte, veuillez lier ce compte");
        br.setProperty("tourist_signin_alert", "A conta do jogo se destina apenas para utilização. Por motivo de segurança deve vincular a conta");
        de.setProperty("tourist_signin_alert", "Das Spielkonto dient nur deiner Sicherheit, bitte registriertes Konto verbinden.");
        sp.setProperty("tourist_signin_alert", "La cuenta del juego es solo para uso. Por tu seguridad, vincula tu cuenta.");
        it.setProperty("tourist_signin_alert", "L'account del gioco è solo per uso, per maggiore sicurezza collega l'account registrato");
        jp.setProperty("tourist_signin_alert", "ゲームアカウントは使用することしかできません。アカウントの安全のため、早めに登録アカウントに連携してください");
        idn.setProperty("tourist_signin_alert", "Untuk keamanan karakter, segera binding akun registrasi.");


        zh_cn.setProperty("nutsplay_viewstring_enter_game", "进入游戏");
        vi.setProperty("nutsplay_viewstring_enter_game", "Đăng nhập");
        th.setProperty("nutsplay_viewstring_enter_game", "เข้าเกม");
        ar.setProperty("nutsplay_viewstring_enter_game", "دخول اللعبة");
        en.setProperty("nutsplay_viewstring_enter_game", "Enter the game");
        kr.setProperty("nutsplay_viewstring_enter_game", "게임을 시작하기");
        zh_hk.setProperty("nutsplay_viewstring_enter_game", "進入遊戲");
        fr.setProperty("nutsplay_viewstring_enter_game", "Entrer dans le jeu");
        br.setProperty("nutsplay_viewstring_enter_game", "Entrar no jogo");
        de.setProperty("nutsplay_viewstring_enter_game", "Spiel betreten");
        sp.setProperty("nutsplay_viewstring_enter_game", "Entrar al juego");
        it.setProperty("nutsplay_viewstring_enter_game", "Entra");
        jp.setProperty("nutsplay_viewstring_enter_game", "ゲームに入る");
        idn.setProperty("nutsplay_viewstring_enter_game", "Masuk game");

        zh_cn.setProperty("nutsplay_viewstring_Bind_Account", "绑定账号");
        vi.setProperty("nutsplay_viewstring_Bind_Account", "Cố định TK");
        th.setProperty("nutsplay_viewstring_Bind_Account", "ผูกมัดบัญชี");
        ar.setProperty("nutsplay_viewstring_Bind_Account", "حسابات موثقة");
        en.setProperty("nutsplay_viewstring_Bind_Account", "Bind accounts");
        kr.setProperty("nutsplay_viewstring_Bind_Account", "계정번호를 연동하기");
        zh_hk.setProperty("nutsplay_viewstring_Bind_Account", "綁定帳號");
        fr.setProperty("nutsplay_viewstring_Bind_Account", "Lier le compte");
        br.setProperty("nutsplay_viewstring_Bind_Account", "Vincular conta");
        de.setProperty("nutsplay_viewstring_Bind_Account", "Konto verbinden");
        sp.setProperty("nutsplay_viewstring_Bind_Account", "Vincular cuenta");
        it.setProperty("nutsplay_viewstring_Bind_Account", "Collega account");
        jp.setProperty("nutsplay_viewstring_Bind_Account", "アカウントに連携する");
        idn.setProperty("nutsplay_viewstring_Bind_Account", "Binding Akun");

        zh_cn.setProperty("nutsplay_viewstring_account_id", "账号ID:");
        vi.setProperty("nutsplay_viewstring_account_id", "TK ID:");
        th.setProperty("nutsplay_viewstring_account_id", "บัญชีID:");
        ar.setProperty("nutsplay_viewstring_account_id", "اسم المستخدم");
        en.setProperty("nutsplay_viewstring_account_id", "Account ID:");
        kr.setProperty("nutsplay_viewstring_account_id", "아이디:");
        zh_hk.setProperty("nutsplay_viewstring_account_id", "帳號ID：");
        fr.setProperty("nutsplay_viewstring_account_id", "ID de Compte :");
        br.setProperty("nutsplay_viewstring_account_id", "ID de Conta:");
        de.setProperty("nutsplay_viewstring_account_id", "Konto-ID:");
        sp.setProperty("nutsplay_viewstring_account_id", "ID Cuenta:");
        it.setProperty("nutsplay_viewstring_account_id", "ID Account:");
        jp.setProperty("nutsplay_viewstring_account_id", "アカウントID：");
        idn.setProperty("nutsplay_viewstring_account_id", "Akun ID:");


        zh_cn.setProperty("nutsplay_viewstring_bindemail", "绑定邮箱");
        vi.setProperty("nutsplay_viewstring_bindemail", "Cố định Email");
        th.setProperty("nutsplay_viewstring_bindemail", "ผูกมัดอีเมล");
        ar.setProperty("nutsplay_viewstring_bindemail", "توثيق بالبريد");
        en.setProperty("nutsplay_viewstring_bindemail", "Bind Email");
        kr.setProperty("nutsplay_viewstring_bindemail", "이메일 주소를 연동하기");
        zh_hk.setProperty("nutsplay_viewstring_bindemail", "綁定電子信箱");
        fr.setProperty("nutsplay_viewstring_bindemail", "Lier par Email");
        br.setProperty("nutsplay_viewstring_bindemail", "Vincular Email");
        de.setProperty("nutsplay_viewstring_bindemail", "E-Mail verb.");
        sp.setProperty("nutsplay_viewstring_bindemail", "Vincular correo");
        it.setProperty("nutsplay_viewstring_bindemail", "Collega email");
        jp.setProperty("nutsplay_viewstring_bindemail", "メールアドレスに連携する");
        idn.setProperty("nutsplay_viewstring_bindemail", "Binding Email");


        zh_cn.setProperty("nutsplay_viewstring_bindcellphone", "绑定手机");
        vi.setProperty("nutsplay_viewstring_bindcellphone", "Cố định SĐT");
        th.setProperty("nutsplay_viewstring_bindcellphone", "ผูกมัดโทรศัพท์");
        ar.setProperty("nutsplay_viewstring_bindcellphone", "توثيق برقم الهاتف");
        en.setProperty("nutsplay_viewstring_bindcellphone", "Bind your phone");
        kr.setProperty("nutsplay_viewstring_bindcellphone", "휴대폰을 연동하기");
        zh_hk.setProperty("nutsplay_viewstring_bindcellphone", "綁定手機號碼");
        fr.setProperty("Diamond", "Lier par téléphone");
        br.setProperty("Diamond", "Vincular pelo telefone");
        de.setProperty("Diamond", "Handy verb.");
        sp.setProperty("Diamond", "Vincular teléfono");
        it.setProperty("Diamond", "Collega il telefono");
        jp.setProperty("Diamond", "携帯に連携する");
        idn.setProperty("Diamond", "Binding No HP");


        zh_cn.setProperty("nutsplay_viewstring_giftcard", "领取礼包");
        vi.setProperty("nutsplay_viewstring_giftcard", "Nhận quà");
        th.setProperty("nutsplay_viewstring_giftcard", "รับรางวัล");
        ar.setProperty("nutsplay_viewstring_giftcard", "الحصول على هدية");
        en.setProperty("nutsplay_viewstring_giftcard", "Gift Package");
        kr.setProperty("nutsplay_viewstring_giftcard", "선물을 수령하기");
        zh_hk.setProperty("nutsplay_viewstring_giftcard", "領取禮包");
        fr.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        br.setProperty("nutsplay_viewstring_giftcard", "Monatskarte");
        de.setProperty("nutsplay_viewstring_giftcard", "Diamant");
        sp.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        it.setProperty("nutsplay_viewstring_giftcard", "Diamante");
        jp.setProperty("nutsplay_viewstring_giftcard", "ダイヤモンド");
        idn.setProperty("nutsplay_viewstring_giftcard", "Ambil Kado");

        zh_cn.setProperty("nutsplay_viewstring_cscenter", "客服中心");
        vi.setProperty("nutsplay_viewstring_cscenter", "CSKH");
        th.setProperty("nutsplay_viewstring_cscenter", "ศูนย์บริการ");
        ar.setProperty("nutsplay_viewstring_cscenter", "خدمة العملاءا");
        en.setProperty("nutsplay_viewstring_cscenter", "Customer Service");
        kr.setProperty("nutsplay_viewstring_cscenter", "고객 센터");
        zh_hk.setProperty("nutsplay_viewstring_cscenter", "客服中心");
        fr.setProperty("nutsplay_viewstring_cscenter", "Service Clientèle");
        br.setProperty("nutsplay_viewstring_cscenter", "Apoio ao Cliente");
        de.setProperty("nutsplay_viewstring_cscenter", "Kundendienst");
        sp.setProperty("nutsplay_viewstring_cscenter", "Atención al cliente");
        it.setProperty("nutsplay_viewstring_cscenter", "Servizio Clienti");
        jp.setProperty("nutsplay_viewstring_cscenter", "問い合わせ");
        idn.setProperty("nutsplay_viewstring_cscenter", "Customer Service");

        zh_cn.setProperty("nutsplay_viewstring_account_tips", "请输入账号");
        vi.setProperty("nutsplay_viewstring_account_tips", "Nhập tài khoản");
        th.setProperty("nutsplay_viewstring_account_tips", "กรุณาระบุบัญชี");
        ar.setProperty("nutsplay_viewstring_account_tips", "الرجاء إدخال اسم المستخدم");
        en.setProperty("nutsplay_viewstring_account_tips", "Please enter an username");
        kr.setProperty("nutsplay_viewstring_account_tips", "아이디를 입력해주세요");
        zh_hk.setProperty("nutsplay_viewstring_account_tips", "請輸入帳號");
        fr.setProperty("nutsplay_viewstring_account_tips", "Veuillez entrer votre compte");
        br.setProperty("nutsplay_viewstring_account_tips", "Insira a sua conta");
        de.setProperty("nutsplay_viewstring_account_tips", "Bitte Konto eingeben");
        sp.setProperty("nutsplay_viewstring_account_tips", "Introduce tu cuenta");
        it.setProperty("nutsplay_viewstring_account_tips", "Inserisci il tuo account");
        jp.setProperty("nutsplay_viewstring_account_tips", "アカウントを入力してください");
        idn.setProperty("nutsplay_viewstring_account_tips", "Masukkan akun anda");

        zh_cn.setProperty("nutsplay_viewstring_password_tips", "请输入用户密码");
        vi.setProperty("nutsplay_viewstring_password_tips", "Nhập mật khẩu");
        th.setProperty("nutsplay_viewstring_password_tips", "กรุณาระบุรหัสผ่าน");
        ar.setProperty("nutsplay_viewstring_password_tips", "الرجاء إدخال كلمة المرور");
        en.setProperty("nutsplay_viewstring_password_tips", "Please enter the user password");
        kr.setProperty("nutsplay_viewstring_password_tips", "사용자 비밀번호를 입력해주세요");
        zh_hk.setProperty("nutsplay_viewstring_password_tips", "請輸入會員密碼");
        fr.setProperty("nutsplay_viewstring_password_tips", "Veuillez entrer votre mdp");
        br.setProperty("nutsplay_viewstring_password_tips", "Insira a sua senha");
        de.setProperty("nutsplay_viewstring_password_tips", "Bitte Nutzerpasswort eing.");
        sp.setProperty("nutsplay_viewstring_password_tips", "Introduce tu contraseña");
        it.setProperty("nutsplay_viewstring_password_tips", "Inserisci la password");
        jp.setProperty("nutsplay_viewstring_password_tips", "パスワードを入力してください");
        idn.setProperty("nutsplay_viewstring_password_tips", "Masukkan password user");

        zh_cn.setProperty("nuts_email", "请输入您的邮箱");
        vi.setProperty("nuts_email", "Email của bạn");
        th.setProperty("nuts_email", "กรุณาใส่ E-Mail");
        ar.setProperty("nuts_email", "الرجاء إدخال عنوان بريدك الإلكتروني");
        en.setProperty("nuts_email", "Please enter your email address");
        kr.setProperty("nuts_email", "이메일 주소를 입력해주세요");
        zh_hk.setProperty("nuts_email", "請輸入您的電子信箱");
        fr.setProperty("nuts_email", "Veuillez entrer votre adresse email");
        br.setProperty("nuts_email", "Insira o endereço de Email");
        de.setProperty("nuts_email", "Bitte E-Mail-Adresse eingeben");
        sp.setProperty("nuts_email", "Introduce tu correo");
        it.setProperty("nuts_email", "Inserisci l'email");
        jp.setProperty("nuts_email", "メールアドレスを入力してください");
        idn.setProperty("nuts_email", "Masukkan alamat email anda");

        zh_cn.setProperty("nuts_username_null", "用户名不能为空");
        vi.setProperty("nuts_username_null", "Tên người dùng không thể trống");
        th.setProperty("nuts_username_null", "Username ไม่สามารถปล่อยว่างได้");
        ar.setProperty("nuts_username_null", "اسم المستخدم لا يمكن أن يكون فارغا");
        en.setProperty("nuts_username_null", "Enter the correct account and password");
        kr.setProperty("nuts_username_null", "사용자 아이다를 입력해주세요");
        zh_hk.setProperty("nuts_username_null", "會員名稱不能為空白");
        fr.setProperty("nuts_username_null", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("nuts_username_null", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("nuts_username_null", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        sp.setProperty("nuts_username_null", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("nuts_username_null", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("nuts_username_null", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("nuts_username_null", "Akun harus 6-14 karakter(huruf / angka).");

        zh_cn.setProperty("nuts_service_err", "服务器数据解析错误");
        vi.setProperty("nuts_service_err", "Dữ liệu lỗi, hãy thử lại");
        th.setProperty("nuts_service_err", "ข้อมูลเซิร์ฟเวอร์ผิดพลาด กรุณาลองใหม่้");
        ar.setProperty("nuts_service_err", "خطأ في الاتصال ببيانات السيرفر");
        en.setProperty("nuts_service_err", "Server data error");
        kr.setProperty("nuts_service_err", "서버 데이터 에러");
        zh_hk.setProperty("nuts_service_err", "伺服器數據解析錯誤");
        fr.setProperty("nuts_service_err", "Erreur de données serveur");
        br.setProperty("nuts_service_err", "Erro no servidor");
        de.setProperty("nuts_service_err", "Serverdatenfehler");
        sp.setProperty("nuts_service_err", "Error en el servidor");
        it.setProperty("nuts_service_err", "Errore server");
        jp.setProperty("nuts_service_err", "サーバーデーターエラー");
        idn.setProperty("nuts_service_err", "Data server error");

        zh_cn.setProperty("welcom_guest", "欢迎使用游客账户");
        vi.setProperty("welcom_guest", "Dùng tài khoản chơi thử");
        th.setProperty("welcom_guest", "ขอต้อนรับบัญชีผู้เยี่ยมชม");
        ar.setProperty("welcom_guest", "مرحبا بالحساب الزائر");
        en.setProperty("welcom_guest", "Welcome guest account!");
        kr.setProperty("welcom_guest", "비회원 계정을 사용하시는 분들을 환영합니다");
        zh_hk.setProperty("welcom_guest", "歡迎使用遊客帳號");
        fr.setProperty("welcom_guest", "Vous pouvez utiliser le compte invité !");
        br.setProperty("welcom_guest", "Benvindo a usar a conta de convidado");
        de.setProperty("welcom_guest", "Willkommen beim Gastkonto!");
        sp.setProperty("welcom_guest", "Bienvenido a usar la cuenta de invitado");
        it.setProperty("welcom_guest", "Sei libero di utilizzare il nostro account guest");
        jp.setProperty("welcom_guest", "ゲストアカウントへようこそ！");
        idn.setProperty("welcom_guest", "Selamat menggunakan akun guest!");

        zh_cn.setProperty("paycenter", "充值中心");
        vi.setProperty("paycenter", "Nạp thẻ");
        th.setProperty("paycenter", "ศูนย์เติมเงิน");
        ar.setProperty("paycenter", "مركز الشحن");
        en.setProperty("paycenter", "Recharge Centre");
        kr.setProperty("paycenter", "충전 센터");
        zh_hk.setProperty("paycenter", "儲值中心");
        fr.setProperty("paycenter", "Centre de Recharge");
        br.setProperty("paycenter", "Centro de Recarga");
        de.setProperty("paycenter", "Aufladezentrum");
        sp.setProperty("paycenter", "Centro de regarga");
        it.setProperty("paycenter", "Centro di ricarica");
        jp.setProperty("paycenter", "チャージセンター");
        idn.setProperty("paycenter", "Pusat TopUp");

        zh_cn.setProperty("guestlogin", "游客账户登录中");
        vi.setProperty("guestlogin", "Đang đăng nhập");
        th.setProperty("guestlogin", "ศูนย์ไอดีผู้เยี่ยมชม");
        ar.setProperty("guestlogin", "تسجيل دخول الحساب الزائر");
        en.setProperty("guestlogin", "Guest account login");
        kr.setProperty("guestlogin", "비회원 계정으로 등록중");
        zh_hk.setProperty("guestlogin", "遊客帳號登入中");
        fr.setProperty("guestlogin", "Connexion au Compte Invité...");
        br.setProperty("guestlogin", "Acessar com convidado...");
        de.setProperty("guestlogin", "Gastkonto-Anmeldung …");
        sp.setProperty("guestlogin", "Acceso como invitado…");
        it.setProperty("guestlogin", "Accesso del guest account");
        jp.setProperty("guestlogin", "ゲストアカウントログイン…");
        idn.setProperty("guestlogin", "Guest akun login...");

        zh_cn.setProperty("welcom", "欢迎 ");
        vi.setProperty("welcom", "Chào mừng ");
        th.setProperty("welcom", "ยินดีต้อนรับ ");
        ar.setProperty("welcom", "مرحبا ");
        en.setProperty("welcom", "Welcome ");
        kr.setProperty("welcom", "환영합니다 ");
        zh_hk.setProperty("welcom", "歡迎 ");
        fr.setProperty("welcom", "Bienvenue");
        br.setProperty("welcom", "Benvindo");
        de.setProperty("welcom", "Willkommen");
        sp.setProperty("welcom", "Bienvenido");
        it.setProperty("welcom", "Benvenuto");
        jp.setProperty("welcom", "ようこそ");
        idn.setProperty("welcom", "Welcome");


        zh_cn.setProperty("welcomguset", "欢迎游客");
        vi.setProperty("welcomguset", "Tài khoản chơi thử");
        th.setProperty("welcomguset", "ยินดีต้อนรับผู้เยี่ยมชม");
        ar.setProperty("welcomguset", "مرحبا بالزوار");
        en.setProperty("welcomguset", "Welcome visitors");
        kr.setProperty("welcomguset", "게스트를 환영합니다!");
        zh_hk.setProperty("welcomguset", "歡迎遊客");
        fr.setProperty("welcomguset", "Bienvenue aux invités !");
        br.setProperty("welcomguset", "Benvindos, convidados!");
        de.setProperty("welcomguset", "Willkommen, Gäste!");
        sp.setProperty("welcomguset", "¡Bienvenidos, invitados!");
        it.setProperty("welcomguset", "Benvenuto ospite!");
        jp.setProperty("welcomguset", "ゲストさんようこそ！");
        idn.setProperty("welcomguset", "Welcome guest!");


        zh_cn.setProperty("welcomfacebook", "欢迎Facebook用户");
        vi.setProperty("welcomfacebook", "Chào mừng người dùng Facebook");
        th.setProperty("welcomfacebook", "ยินดีต้อนรับผู้ใช้ไอดี Facebook");
        ar.setProperty("welcomfacebook", "مرحبا بمستخدمي الفايسبوك");
        en.setProperty("welcomfacebook", "Welcome Facebook users!");
        kr.setProperty("welcomfacebook", "Facebook사용하시는 분들을 환영합니다");
        zh_hk.setProperty("welcomfacebook", "歡迎Facebook會員");
        fr.setProperty("welcomfacebook", "Bienvenue aux utilisateurs de Facebook");
        br.setProperty("welcomfacebook", "Benvindos, usuários de Facebook!");
        de.setProperty("welcomfacebook", "Willkommen, Facebook-Nutzer");
        sp.setProperty("welcomfacebook", "¡Bienvenidos, usuarios de FB");
        it.setProperty("welcomfacebook", "Benvenuto utente Facebook");
        jp.setProperty("welcomfacebook", "Facebookユーザーさんようこそ");
        idn.setProperty("welcomfacebook", "Welcome User Facebook");


        zh_cn.setProperty("comenuts", " 回到NutsPlayGame");
        vi.setProperty("comenuts", " đến với NutsPlayGame");
        th.setProperty("comenuts", " กลับไปยังNutsPlayGame");
        ar.setProperty("comenuts", "العودة إلى NutsPlayGame ");
        en.setProperty("comenuts", " Back to NutsPlayGame");
        kr.setProperty("comenuts", " NutsPlayGame으로 돌아가기");
        zh_hk.setProperty("comenuts", " 回到NutsPlayGame");
        fr.setProperty("comenuts", "Retour à NutsPlayGame");
        br.setProperty("comenuts", "Regressar a NutsPlayGame");
        de.setProperty("comenuts", "Zurück zu NutsPlayGame");
        sp.setProperty("comenuts", "Regresar a NutsPlayGame");
        it.setProperty("comenuts", "Ritorna a NutsPlayGame");
        jp.setProperty("comenuts", "NutsPlayGameに戻る");
        idn.setProperty("comenuts", "Kembali ke NutsPlayGame");


        zh_cn.setProperty("nutsgood", "登录成功，NutsPlayGame将会给您带来很棒的游戏体验");
        vi.setProperty("nutsgood", "Đăng nhập thành công, Nuts Game thế giới trò chơi vui nhộn nhất");
        th.setProperty("nutsgood", "ล็อกอินสำเร็จ NutsPlayGame จะให้ท่านได้พบกับการเล่นเกมที่น่าตื่นตาตื่นใจ");
        ar.setProperty("nutsgood", "تم تسجيل الدخول بنجاح، سوف تتيح لك NutsPlayGame خوض تجربة فريدة\u200f");
        en.setProperty("nutsgood", "successfully logged in, NutsPlayGame will bring you a great gaming experience");
        kr.setProperty("nutsgood", "NutsPlayGame으로 돌아가기");
        zh_hk.setProperty("nutsgood", "登入成功，NutsPlayGame將會給您帶來很棒的遊戲體驗");
        fr.setProperty("nutsgood", "Connecté avec succès. NutsPlayGame vous apportera une expérience de jeu grandiose !");
        br.setProperty("nutsgood", "Acessou com sucesso. NutsPlayGame irá proporcionar uma excelente experiência de jogo!");
        de.setProperty("nutsgood", "Angemeldet. NutsPlayGame bringt dir ein großartiges Spielerlebnis!");
        sp.setProperty("nutsgood", "Accedido con éxito. NutsPlayGame te hará pasar un tiempo extraordinario.");
        it.setProperty("nutsgood", "Accesso avvenuto con successo. Con NutsPlayGame avrai una fantastica esperienza di gioco");
        jp.setProperty("nutsgood", "ログインしました。NutsPlayGameは素晴らしいゲーム体験を提供します！");
        idn.setProperty("nutsgood", "Sukses login.Nikmati pengalaman bermain di NutsPlayGame!");

        zh_cn.setProperty("playto", "一起玩游戏！！");
        vi.setProperty("playto", "Cùng chơi game!!");
        th.setProperty("playto", " มาเล่นเกมกันเถอะ!");
        ar.setProperty("playto", "لنلعب سويا!");
        en.setProperty("playto", "Let's play together!");
        kr.setProperty("playto", "같이 즐거운 게임을 시작해보세요");
        zh_hk.setProperty("playto", "一起玩遊戲！！");
        fr.setProperty("playto", "Diamante");
        br.setProperty("playto", "Monatskarte");
        de.setProperty("playto", "Diamant");
        sp.setProperty("playto", "Diamante");
        it.setProperty("playto", "Diamante");
        jp.setProperty("playto", "ダイヤモンド");
        idn.setProperty("playto", "Sukses keluar");

        zh_cn.setProperty("logoOut", "注销成功");
        vi.setProperty("logoOut", "Đăng xuất thành công");
        th.setProperty("logoOut", " ลงทะเบียนสำเร็จ");
        ar.setProperty("logoOut", "تم تسجيل الخروج بنجاح");
        en.setProperty("logoOut", "successfully logged out");
        kr.setProperty("logoOut", "로그아웃 성공");
        zh_hk.setProperty("logoOut", "取消成功");
        fr.setProperty("logoOut", "Déconnecté avec succès.");
        br.setProperty("logoOut", "Saída bem sucedida");
        de.setProperty("logoOut", "Abgemeldet");
        sp.setProperty("logoOut", "Salido con éxito");
        it.setProperty("logoOut", "Logout avvenuto con successo");
        jp.setProperty("logoOut", "登録成功");
        idn.setProperty("logoOut", "Berlian");

        zh_cn.setProperty("email_null", "邮箱不能为空");
        vi.setProperty("email_null", "Email không thể trống");
        th.setProperty("email_null", "E-Mail ไม่สามารถปล่อยว่างได้จ");
        ar.setProperty("email_null", "عنوان البريد الإلكتروني لا يمكن أن يكون فارغا");
        en.setProperty("email_null", "The Email can not be blank");
        kr.setProperty("email_null", "이메일 주소를 입력해주십시오");
        zh_hk.setProperty("email_null", "電子信箱不能為空白");
        fr.setProperty("email_null", "L’adresse email ne peut pas être vide");
        br.setProperty("email_null", "O endereço de email não pode ficar em branco");
        de.setProperty("email_null", "E-Mail-Adresse darf nicht leer sein");
        sp.setProperty("email_null", "El correo no puede quedar en blanco");
        it.setProperty("email_null", "L'email non può essere vuota");
        jp.setProperty("email_null", "メールアドレスを入力しないといけません");
        idn.setProperty("email_null", "Alamat email harus diisi");


        zh_cn.setProperty("sending", "正在发送验证");
        vi.setProperty("sending", "Đang gửi mã xác nhận");
        th.setProperty("sending", "กำลังส่งรหัสยืนยัน");
        ar.setProperty("sending", "التحقق من الإرسال");
        en.setProperty("sending", "Sending verification");
        kr.setProperty("sending", "인증번호를 보내는 중..");
        zh_hk.setProperty("sending", "正在發送驗證碼");
        fr.setProperty("sending", "Envoi de la vérification");
        br.setProperty("sending", "Enviando verificação");
        de.setProperty("sending", "Sendet Bestätigung");
        sp.setProperty("sending", "Enviando verificación");
        it.setProperty("sending", "Invio della conferma");
        jp.setProperty("sending", "認証を送信中");
        idn.setProperty("sending", "Mengirim verifikasi");

        zh_cn.setProperty("please_input_email", "请输入邮箱");
        vi.setProperty("please_input_email", "Mời nhập Email");
        th.setProperty("please_input_email", "กรุณาใส่ E-Mail");
        ar.setProperty("please_input_email", "يرجى إدخال عنوان البريد الإلكتروني");
        en.setProperty("please_input_email", "please enter your email");
        kr.setProperty("please_input_email", "이메일 주소를 입력해 주세요");
        zh_hk.setProperty("please_input_email", "請輸入電子信箱");
        fr.setProperty("please_input_email", "Veuillez entrer votre adresse email");
        br.setProperty("please_input_email", "Insira o seu endereço de email");
        de.setProperty("please_input_email", "Bitte E-Mail-Adresse eingeben");
        sp.setProperty("please_input_email", "Introduce tu correo");
        it.setProperty("please_input_email", "Si prega di inserire l'indirizzo email");
        jp.setProperty("please_input_email", "メールアドレスを入力してください");
        idn.setProperty("please_input_email", "Masukkan alamat email anda");

        zh_cn.setProperty("please_register", "请先注册账户");
        vi.setProperty("please_register", "Hãy đăng ký tài khoản");
        th.setProperty("please_register", "กรุณาลงทะเบียนไอดีก่อน");
        ar.setProperty("please_register", "يرجى تسجيل حسابك أولا");
        en.setProperty("please_register", "Please register your account first");
        kr.setProperty("please_register", "회원가입하십시오");
        zh_hk.setProperty("please_register", "請先註冊帳號");
        fr.setProperty("please_register", "Veuillez créer un compte");
        br.setProperty("please_register", "Registre uma conta");
        de.setProperty("please_register", "Bitte Konto registrieren");
        sp.setProperty("please_register", "Registra una cuenta");
        it.setProperty("please_register", "Si prega di registrare un account");
        jp.setProperty("please_register", "アカウントを登録してください");
        idn.setProperty("please_register", "Silahkan register akun");

        zh_cn.setProperty("please_pay", "请选择支付方式");
        vi.setProperty("please_pay", "Hãy đăng ký tài khoản");
        th.setProperty("please_pay", "กรุณาลงทะเบียนไอดีก่อน");
        ar.setProperty("please_pay", "الرجاء اختيار طريقة الدفع");
        en.setProperty("please_pay", "please select a payment method");
        kr.setProperty("please_pay", "지불 수단을 선택해주십시오");
        zh_hk.setProperty("please_pay", "請選擇付款方式");
        fr.setProperty("please_pay", "Sélectionnez le Mode de Paiement :");
        br.setProperty("please_pay", "Selecione um Método de Pagamento:");
        de.setProperty("please_pay", "Zahlungsmethode ausw.:");
        sp.setProperty("please_pay", "Elige el medio de pago:");
        it.setProperty("please_pay", "Selezionare il metodo di pagamento:");
        jp.setProperty("please_pay", "支払方法を選んでください：");
        idn.setProperty("please_pay", "Pilih Metode TopUp:");

        zh_cn.setProperty("please_bind_email", "请先绑定您的邮箱");
        vi.setProperty("please_bind_email", "Chọn kiểu thanh toán");
        th.setProperty("please_bind_email", "กรุณาเลือกช่องทางการเติมเงิน");
        ar.setProperty("please_bind_email", "الرجاء ربط البريد الإلكتروني الخاص بك");
        en.setProperty("please_bind_email", "Please bind your email first");
        kr.setProperty("please_bind_email", "이메일 주소를 연동하기를 하세요");
        zh_hk.setProperty("please_bind_email", "請先綁定您的電子信箱");
        fr.setProperty("please_bind_email", "Veuillez d’abord lier votre email");
        br.setProperty("please_bind_email", "Vincule o seu email primeiro");
        de.setProperty("please_bind_email", "Bitte zuerst E-Mail verbinden");
        sp.setProperty("please_bind_email", "Vincula tu correo primero");
        it.setProperty("please_bind_email", "Prima si prega di associare la tua mail");
        jp.setProperty("please_bind_email", "メールアドレスに連携してください");
        idn.setProperty("please_bind_email", "Binding email dulu");

        zh_cn.setProperty("nutsplay_viewstring_repeatpassword", "重复密码");
        vi.setProperty("nutsplay_viewstring_repeatpassword", "Xác nhận mật khẩu");
        th.setProperty("nutsplay_viewstring_repeatpassword", "ระบุรหัสผ่านอีกครั้ง");
        ar.setProperty("nutsplay_viewstring_repeatpassword", "الرجاء إعادة كتابة  كلمة المرور");
        en.setProperty("nutsplay_viewstring_repeatpassword", "Repeat password");
        kr.setProperty("nutsplay_viewstring_repeatpassword", "비밀번호가 중복되었습니다");
        zh_hk.setProperty("nutsplay_viewstring_repeatpassword", "密碼重複");
        fr.setProperty("nutsplay_viewstring_repeatpassword", "Répétez le mdp");
        br.setProperty("nutsplay_viewstring_repeatpassword", "Repita a senha");
        de.setProperty("nutsplay_viewstring_repeatpassword", "Passwort wiederh.");
        sp.setProperty("nutsplay_viewstring_repeatpassword", "Repite tu contraseña");
        it.setProperty("nutsplay_viewstring_repeatpassword", "Ridigitare la password");
        jp.setProperty("nutsplay_viewstring_repeatpassword", "パスワードを繰り返してください");
        idn.setProperty("nutsplay_viewstring_repeatpassword", "Ulangi password");


        zh_cn.setProperty("nutsplay_viewstring_signout", "注销");
        vi.setProperty("nutsplay_viewstring_signout", "Thoát");
        th.setProperty("nutsplay_viewstring_signout", "ยกเลิก");
        ar.setProperty("nutsplay_viewstring_signout", "تسجيل الخروج");
        en.setProperty("nutsplay_viewstring_signout", "Log out");
        kr.setProperty("nutsplay_viewstring_signout", "로그아웃");
        zh_hk.setProperty("nutsplay_viewstring_signout", "取消");
        fr.setProperty("nutsplay_viewstring_signout", "Déconnecter");
        br.setProperty("nutsplay_viewstring_signout", "Sair");
        de.setProperty("nutsplay_viewstring_signout", "Abmeld.");
        sp.setProperty("nutsplay_viewstring_signout", "Salir");
        it.setProperty("nutsplay_viewstring_signout", "Esci");
        jp.setProperty("nutsplay_viewstring_signout", "ログアウト");
        idn.setProperty("nutsplay_viewstring_signout", "Keluar");

        zh_cn.setProperty("gameview.pay.user.cannel", "用户取消交易");
        th.setProperty("gameview.pay.user.cannel", " ผู้เล่นยกเลิกการดำเนินการ ");
        vi.setProperty("gameview.pay.user.cannel", "Người dùng hủy giao dịch");
        ar.setProperty("gameview.pay.user.cannel", "ألغى المستخدم عملية الشحن");
        en.setProperty("gameview.pay.user.cannel", "The user cancels the transaction");
        kr.setProperty("gameview.pay.user.cannel", "사용자가 거래를 취소하셨습니다");
        zh_hk.setProperty("gameview.pay.user.cannel", "會員取消交易");
        fr.setProperty("gameview.pay.user.cannel", "L’utilisateur a annulé l’opération");
        br.setProperty("gameview.pay.user.cannel", "O usuário cancelou a transação");
        de.setProperty("gameview.pay.user.cannel", "Nutzer bricht Transaktion ab");
        sp.setProperty("gameview.pay.user.cannel", "El usuario ha cancelado la transacción");
        it.setProperty("gameview.pay.user.cannel", "L'utente ha annullato l'operazione");
        jp.setProperty("gameview.pay.user.cannel", "このユーザーは取引をキャンセルしました");
        idn.setProperty("gameview.pay.user.cannel", "User membatalkan transaksi");


        zh_cn.setProperty("1", "账户已存在");
        vi.setProperty("1", "Tài khoản Đã có người dùng");
        th.setProperty("1", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("1", "اسم المستخدم موجود من قبل");
        en.setProperty("1", "Account has already been registered");
        kr.setProperty("1", "동일한 아이디가 존재하였습니다");
        zh_hk.setProperty("1", "帳號已存在");
        fr.setProperty("1", "Le compte a déjà été enregistré");
        br.setProperty("1", "A conta já foi registada");
        de.setProperty("1", "Konto bereits registriert");
        sp.setProperty("1", "La cuenta ya ha sido registrada");
        it.setProperty("1", "L'account è già stato registrato");
        jp.setProperty("1", "このアカウントは既に登録されています");
        idn.setProperty("1", "Akun telah terdaftar");


        zh_cn.setProperty("2", "账户不存在");
        vi.setProperty("2", "Tài khoản không tồn tại");
        th.setProperty("2", "ไม่มีไอดีนี้อยู่");
        ar.setProperty("2", "اسم المستخدم غير موجود");
        en.setProperty("2", "Username does not exist");
        kr.setProperty("2", "존재하지 않은 아이다입니다");
        zh_hk.setProperty("2", "帳號不存在");
        fr.setProperty("2", "Le compte n’existe pas !");
        br.setProperty("2", "A conta não existe");
        de.setProperty("2", "Konto existiert nicht");
        sp.setProperty("2", "La cuenta no existe");
        it.setProperty("2", "L'account non esiste");
        jp.setProperty("2", "このアカウントは存在しません");
        idn.setProperty("2", "Akun tidak tersedia");

        zh_cn.setProperty("3", "请输入正确的账户或者密码");
        vi.setProperty("3", "Sai mật khẩu");
        th.setProperty("3", "รหัสผิดพลาด");
        ar.setProperty("3", "كلمة مرور خاطئة");
        en.setProperty("3", "Enter the correct account and password");
        kr.setProperty("3", "비밀전호 오류");
        zh_hk.setProperty("3", "密碼錯誤");
        fr.setProperty("3", "Entrez le compte et mdp corrects");
        br.setProperty("3", "Insira a conta ou a senha correta");
        de.setProperty("3", "Bitte korrektes Konto und Passwort eing.");
        sp.setProperty("3", "Introduce tu cuenta o contraseña");
        it.setProperty("3", "Si prega di inserire correttamente l'account o la password");
        jp.setProperty("3", "正しいアカウントとパスワードを入力してください");
        idn.setProperty("3", "Masukkan akun atau password dengan benar");

        zh_cn.setProperty("4", "邮箱不存在");
        vi.setProperty("4", "Email không tồn tại");
        th.setProperty("4", "ไม่มี E-mail นี้อยู่");
        ar.setProperty("4", "البريد الإلكتروني غير موجود");
        en.setProperty("4", "The email does not exist");
        kr.setProperty("4", "이 이메일과 바인딩된 계정이 존재하지 않습니다.");
        zh_hk.setProperty("4", "電子信箱不存在");
        fr.setProperty("4", "Aucun compte trouvé avec cette adresse email");
        br.setProperty("4", "Não foi encontrada nenhuma conta com esse endereço de correio");
        de.setProperty("4", "Kein Konto mit dieser E-Mail gefunden");
        sp.setProperty("4", "No se han encontrado cuentas con este correo");
        it.setProperty("4", "Nessun account trovato associato a questa email");
        jp.setProperty("4", "メールアドレスは存在しません");
        idn.setProperty("4", "Email tidak tersedia");

        zh_cn.setProperty("5", "邮箱已存在");
        vi.setProperty("5", "Email đã tồn tại");
        th.setProperty("5", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("5", "البريد الإلكتروني موجود من قبل");
        en.setProperty("5", "Email already exists");
        kr.setProperty("5", "이 이메일과 바인딩된 유저가 이미 존재합니다.");
        zh_hk.setProperty("5", "電子信箱已存在");
        fr.setProperty("5", "Adresse email déjà utilisé par un autre utilisateur");
        br.setProperty("5", "Já existe um usuário com esse email");
        de.setProperty("5", "Ein Nutzer mit dieser E-Mail von dir existiert bereits");
        sp.setProperty("5", "El usuario del correo especificado ya existe");
        it.setProperty("5", "Esiste già un utente associato a questa email");
        jp.setProperty("5", "このメールアドレスは既に登録されています");
        idn.setProperty("5", "Email telah terdaftar");

        zh_cn.setProperty("6", "身份验证无效");
        vi.setProperty("6", "Xác nhận vô hiệu");
        th.setProperty("6", "มีไอดีนี้อยู่แล้ว");
        ar.setProperty("6", "البيانات غير صالحة");
        en.setProperty("6", "Invalid authentication");
        kr.setProperty("6", "인증하기 실페");
        zh_hk.setProperty("6", "身份驗證無效");
        fr.setProperty("6", "Authentification non valide");
        br.setProperty("6", "Autenticação inválida");
        de.setProperty("6", "Ungültige Authentifizierung");
        sp.setProperty("6", "Autentificación inválida");
        it.setProperty("6", "Autenticazione invalida");
        jp.setProperty("6", "認証失敗");
        idn.setProperty("6", "Verifikasi Gagal");

        zh_cn.setProperty("7", "账户已绑定");
        vi.setProperty("7", "Tài khoản đã cố định");
        th.setProperty("7", "ไอดีผูกมัดแล้ว");
        ar.setProperty("7", "الحساب موثق");
        en.setProperty("7", "Account is bound");
        kr.setProperty("7", "이미 연동되어있는 계정입니다");
        zh_hk.setProperty("7", "帳號已綁定");
        fr.setProperty("7", "Le compte a été lié");
        br.setProperty("7", "A conta foi criada");
        de.setProperty("7", "Konto ist verbunden");
        sp.setProperty("7", "Cuenta vinculada");
        it.setProperty("7", "L'account è stato associato");
        jp.setProperty("7", "アカウントは連携されました");
        idn.setProperty("7", "Akun telah dibinding");

        zh_cn.setProperty("8", "游戏状态无效");
        vi.setProperty("8", "Trạng thái vô hiệu");
        th.setProperty("8", "สถานะเกมล้มเหลว");
        ar.setProperty("8", "وضع لعبة غير صالح");
        en.setProperty("8", "Game status is invalid");
        kr.setProperty("8", "게임의 상태가 올바르지 않음");
        zh_hk.setProperty("8", "遊戲狀態無效");
        fr.setProperty("8", "État du jeu non valide");
        br.setProperty("8", "Estado de jogo inválido");
        de.setProperty("8", "Ungültiger Spielstatus");
        sp.setProperty("8", "Estado de juego no válido");
        it.setProperty("8", "Stato di gioco non valido");
        jp.setProperty("8", "ゲームの状態が無効です");
        idn.setProperty("8", "Status game tidak valid");

        zh_cn.setProperty("9", "账号被冻结");
        vi.setProperty("9", "Tài khoản bị đóng băng");
        th.setProperty("9", "ไอดีถูกล็อก");
        ar.setProperty("9", "الحساب مجمد");
        en.setProperty("9", "The account is frozen");
        kr.setProperty("9", "동결되어있는 아이다입니다");
        zh_hk.setProperty("9", "帳號被鎖定");
        fr.setProperty("9", "Le compte est gelé");
        br.setProperty("9", "A conta está congelada");
        de.setProperty("9", "Konto eingefroren");
        sp.setProperty("9", "La cuenta está congelada");
        it.setProperty("9", "L'account è congelato");
        jp.setProperty("9", "アカウントは凍結されました");
        idn.setProperty("9", "Akun telah dibekukan");

        zh_cn.setProperty("10", "账户错误");
        vi.setProperty("10", "Tài khoản lỗi");
        th.setProperty("10", "ไอดีผิดพลาด");
        ar.setProperty("10", "خطأ في الحساب");
        en.setProperty("10", "Account error");
        kr.setProperty("10", "계정번호 오류");
        zh_hk.setProperty("10", "帳號錯誤");
        fr.setProperty("10", "Erreur de compte");
        br.setProperty("10", "Erro na conta");
        de.setProperty("10", "Kontofehler");
        sp.setProperty("10", "Error de cuenta");
        it.setProperty("10", "Errore nell'account");
        jp.setProperty("10", "アカウントエラー");
        idn.setProperty("10", "Akun error");

        zh_cn.setProperty("11", "不是固定邮箱");
        vi.setProperty("11", "Email cố định không đúng");
        th.setProperty("11", "ไม่ใช่ E-mail ที่แน่นอน");
        ar.setProperty("11", "يجب تحديد البريد الكتروني الخاص بك");
        en.setProperty("11", "Not a fixed mailbox");
        kr.setProperty("11", "고정된 메일 주소가 아닙니다");
        zh_hk.setProperty("11", "非原本電子信箱");
        fr.setProperty("11", "Format d’adresse e-mail incorrecte");
        br.setProperty("11", "Formato de endereço de email inválido");
        de.setProperty("11", "Ungültiges E-Mail-Format");
        sp.setProperty("11", "Error en el formato del correo");
        it.setProperty("11", "Formato dell'indirizzo email invalido");
        jp.setProperty("11", "メールアドレスの形式が無効です");
        idn.setProperty("11", "Alamat email tidak valid");

        zh_cn.setProperty("12", "邮箱格式无效");
        vi.setProperty("12", "Định dạng Email không hợp lệ");
        th.setProperty("12", "รูปแบบ E-mail ใช้ไม่ได้");
        ar.setProperty("12", "عنوان البريد الإلكتروني غير صالح");
        en.setProperty("12", "Invalid Email format");
        kr.setProperty("12", "올바르지 않은 이메일 주소 형식");
        zh_hk.setProperty("12", "電子信箱格式錯誤");
        fr.setProperty("12", "Format d’Email non Valide");
        br.setProperty("12", "Formato de email inválido");
        de.setProperty("12", "Ungültiges E-Mail-Format");
        sp.setProperty("12", "Error en el formato del corroe");
        it.setProperty("12", "Formato Email invalido");
        jp.setProperty("12", "メールの形式が無効です");
        idn.setProperty("12", "Format email tidak valid");

        zh_cn.setProperty("13", "账户格式无效");
        vi.setProperty("13", "Định dạng tài khoản không hợp lệ");
        th.setProperty("13", "รูปแบบไอดีใช้ไม่ได้");
        ar.setProperty("13", "شكل حساب غير صالح");
        en.setProperty("13", "Account must be 6-24 characters Letters or Numbers");
        kr.setProperty("13", "올바르지 않은 계정 형식");
        zh_hk.setProperty("13", "帳號格式錯誤");
        fr.setProperty("13", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("13", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("13", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        sp.setProperty("13", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("13", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("13", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("13", "Akun harus 6-14 karakter(huruf / angka).");

        zh_cn.setProperty("14", "支付失败");
        vi.setProperty("14", "Thanh toán thất bại");
        th.setProperty("14", "ชำระเงินล้มเหลว");
        ar.setProperty("14", "تعذر الدفع");
        en.setProperty("14", "Payment failed");
        kr.setProperty("14", "지불 실패");
        zh_hk.setProperty("14", "付款失敗");
        fr.setProperty("14", "Paiement échoué");
        br.setProperty("14", "Falha no pagamento");
        de.setProperty("14", "Zahlung fehlgeschl.");
        sp.setProperty("14", "Pago fallido");
        it.setProperty("14", "Pagamento non riuscito");
        jp.setProperty("14", "支払失敗");
        idn.setProperty("14", "TopUp gagal");

        zh_cn.setProperty("15", "密码格式无效");
        vi.setProperty("15", "Mã thẻ không hợp lệ");
        th.setProperty("15", "รูปแบบรหัสใช้ไม่ได้");
        ar.setProperty("15", "شكل كلمة المرور غير صالح");
        en.setProperty("15", "Password must be 6-24 characters Letters or Numbers");
        kr.setProperty("15", "올바르지 않은 비밀번호 형식");
        zh_hk.setProperty("15", "密碼格式錯誤");
        fr.setProperty("15", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("15", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("15", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        sp.setProperty("15", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("15", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("15", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("15", "Password harus 6-14 karakter(huruf / angka).");

        zh_cn.setProperty("16", "卡已经被使用");
        vi.setProperty("16", "Thẻ đã bị sử dụng qua");
        th.setProperty("16", "บัตรถูกใช้ไปแล้ว");
        ar.setProperty("16", "البطاقة مستخدمة");
        en.setProperty("16", "The card has already been used");
        kr.setProperty("16", "이미 사용되어있는 카드입니다");
        zh_hk.setProperty("16", "卡號已被使用過");
        fr.setProperty("16", "La carte a déjà été utilisé");
        br.setProperty("16", "Este cartão já foi usado");
        de.setProperty("16", "Karte wurde schon benutzt");
        sp.setProperty("16", "La tarjeta ya ha sido usada");
        it.setProperty("16", "La carta è già stata utilizzata");
        jp.setProperty("16", "このカードは使用されました");
        idn.setProperty("16", "Kartu telah digunakan");

        zh_cn.setProperty("17", "卡号或者密码无效");
        vi.setProperty("17", "Số thẻ hoặc mã thẻ vô hiệu");
        th.setProperty("17", "เลขบัตรหรือรหัสไม่ถูกต้อง");
        ar.setProperty("17", "رقم البطاقة أو كلمة المرور خاطئة");
        en.setProperty("17", "Invalid card number or password");
        kr.setProperty("17", "카드 번호나 비밀 번호는 올바르지 않습니다");
        zh_hk.setProperty("17", "卡號或密碼錯誤");
        fr.setProperty("17", "Mot de passe ou numéro de carte invalide");
        br.setProperty("17", "Número ou senha de cartão inválido");
        de.setProperty("17", "Ungültige Karten-Nr. oder Passwort");
        sp.setProperty("17", "Tarjeta o contraseña no válidas");
        it.setProperty("17", "Numero della carta o password non validi");
        jp.setProperty("17", "カードナンバー或いはパスワードが無効です");
        idn.setProperty("17", "Nomor kartu atau password tidak valid");

        zh_cn.setProperty("18", "Ncoin余额不足");
        vi.setProperty("18", "Ncoin còn không đủ");
        th.setProperty("18", "Ncoin ไม่พอ");
        ar.setProperty("18", "رصيد Ncoin غير كاف");
        en.setProperty("18", "\u200FNcoin insufficient balance");
        kr.setProperty("18", "잔액이 부족합니다");
        zh_hk.setProperty("18", "Ncoin餘額不足：");
        fr.setProperty("18", "Solde de Ncoin insuffisant");
        br.setProperty("18", "Saldo de Ncoin insuficiente");
        de.setProperty("18", "Zu wenig Ncoin-Guthaben");
        sp.setProperty("18", "Balance de Ncoin insuficiente");
        it.setProperty("18", "Saldo Ncoin non sufficiente");
        jp.setProperty("18", "Ncoin残高が足りません");
        idn.setProperty("18", "Nominal Ncoin tidak mencukupi");


        zh_cn.setProperty("19", "支付成功,如果长时间未到账,请重启游戏或者联系客服");
        vi.setProperty("19", "Thanh toán thành công, nếu chưa nhận được, hãy khởi động lại game hoặc liên hệ CSKH");
        th.setProperty("19", "เติมเงินสำเร็จ หากเงินยังไม่เข้าเกมเป็นเวลานาน กรุณาออกเข้าใหม่หรือติดต่อฝ่ายบริการ");
        ar.setProperty("19", "دفع ناجح، إن لم يتم التفعيل لوقت طويل، يرجى إعادة تشغيل اللعبة أو الاتصال بخدمة العملاء");
        en.setProperty("19", "Payment success, if not credited for a long time, please restart the game or contact customer service");
        kr.setProperty("19", "지불이 완료되었습니다. 완료되지 않는 경우에는 게임을 다시 시작하거나 고객 센터에 연락해주시기 바랍니다");
        zh_hk.setProperty("19", "交易成功，如果仍未收到點數，請重啟遊戲或聯絡客服");
        fr.setProperty("19", "Paiement réussi. Si non reçu pendant une longue période, veuillez redémarrer le jeu ou contacter le service clientèle");
        br.setProperty("19", "Pagamento bem sucedido. Se não for creditado na conta, reinicie o jogo ou contate o apoio ao cliente");
        de.setProperty("19", "Zahlung erfolgt. Wenn der Erhalt zu lange dauert, Spiel neu starten oder Kundendienst kontaktieren.");
        sp.setProperty("19", "Pago completado. Si no recibes el paquete reinicia el juego o contacta a atención al cliente.");
        it.setProperty("19", "Pagamento avvenuto con successo. Se non accreditato dopo molto tempo, si prega di riavviare il gioco o contattare il servizio clienti");
        jp.setProperty("19", "支払成功、長期間届かない場合は、ゲームを再開するか、或いはサポートにご連絡ください");
        idn.setProperty("19", "BePembayaran sukses. Jika masih belum menerima, silahkan coba restart game atau hubungi customer servicerlian");

        zh_cn.setProperty("20", "游客账户");
        vi.setProperty("20", "Tài khoản chơi thử");
        th.setProperty("20", "ไอดีเยี่ยมชม");
        ar.setProperty("20", "حساب زائر");
        en.setProperty("20", "Tourist account");
        kr.setProperty("20", "비회원 계정");
        zh_hk.setProperty("20", "遊客帳號");
        fr.setProperty("20", "Compte Invité");
        br.setProperty("20", "Conta de convidado");
        de.setProperty("20", "Gastkonto");
        sp.setProperty("20", "Cuenta de invitado");
        it.setProperty("20", "Account guest");
        jp.setProperty("20", "ゲストアカウント");
        idn.setProperty("20", "Akun guest");

        zh_cn.setProperty("21", "正在发送验证码...");
        vi.setProperty("21", "Đang gửi mã xác nhận…");
        th.setProperty("21", "กำลังส่งรหัสยืนยัน");
        ar.setProperty("21", "إرسال رمز التحقق ...");
        en.setProperty("21", "Sending verification code ...");
        kr.setProperty("21", "인증번호를 보내는 중...");
        zh_hk.setProperty("21", "正在發送驗證碼…");
        fr.setProperty("21", "Envoi du code de vérification...");
        br.setProperty("21", "Enviando código de verificação");
        de.setProperty("21", "Sendet Bestätigungscode ...");
        sp.setProperty("21", "Enviando código de verificación…");
        it.setProperty("21", "Invio del codice di conferma...");
        jp.setProperty("21", "認証コードを発信中…");
        idn.setProperty("21", "Sedang kirim kode verifikasi...");

        zh_cn.setProperty("22", "验证码发送成功!");
        vi.setProperty("22", "Gửi mã xác nhận thành công!");
        th.setProperty("22", "ส่งรหัสยืนยันเสร็จสิ้น");
        ar.setProperty("22", "أُرسلَ رمز التحقق بنجاح!!");
        en.setProperty("22", "Verification code sent successfully!");
        kr.setProperty("22", "인증번호를 보내는 중...");
        zh_hk.setProperty("22", "驗證碼發送成功！");
        fr.setProperty("22", "Code de vérification envoyé !");
        br.setProperty("22", "Código de verificação enviado com sucesso!");
        de.setProperty("22", "Bestätigungscode gesendet!");
        sp.setProperty("22", "Código de verificación enviado");
        it.setProperty("22", "Codice di conferma inviato correttamente!");
        jp.setProperty("22", "認証コードを発送しました！");
        idn.setProperty("22", "Sukses kirim kode verifikasi!");

        zh_cn.setProperty("23", "关闭");
        vi.setProperty("23", "Đóng");
        th.setProperty("23", "ปิด");
        ar.setProperty("23", "إغلاق");
        en.setProperty("23", "nuts_icon_close");
        kr.setProperty("23", "닫기");
        zh_hk.setProperty("23", "關閉");
        fr.setProperty("23", "Fermer");
        br.setProperty("23", "Fechar");
        de.setProperty("23", "Schließen");
        sp.setProperty("23", "Cerrar");
        it.setProperty("23", "Chiudi");
        jp.setProperty("23", "閉じる");
        idn.setProperty("23", "Tutup");


        zh_cn.setProperty("24", "验证码发送失败!");
        vi.setProperty("24", "Gửi mã xác nhận thất bại!");
        th.setProperty("24", "ส่งรหัสยืนยันล้มเหลว");
        ar.setProperty("24", "فشل في إرسال رمز التحقق!");
        en.setProperty("24", "Verification code failed to send!");
        kr.setProperty("24", "인증번호 보내기는 실패!");
        zh_hk.setProperty("24", "驗證碼發送失敗！");
        fr.setProperty("24", "Impossible d’envoyer le code de vérification");
        br.setProperty("24", "Falha no envio do código de verificação");
        de.setProperty("24", "Bestätigungscode senden fehlgeschl.");
        sp.setProperty("24", "Error al enviar código de verificación");
        it.setProperty("24", "Invio del codice di conferma non riuscito");
        jp.setProperty("24", "確認コードの送信に失敗しました");
        idn.setProperty("24", "Gagal kirim kode verifikasi");

        zh_cn.setProperty("25", "验证中...");
        vi.setProperty("25", "Đang xác nhận…");
        th.setProperty("25", "กำลังยืนยัน");
        ar.setProperty("25", "التأكيد ...");
        en.setProperty("25", "Verification ...");
        kr.setProperty("25", "로딩 중...");
        zh_hk.setProperty("25", "驗證中…");
        fr.setProperty("25", "Vérification...");
        br.setProperty("25", "Verificando...");
        de.setProperty("25", "Bestätigen …");
        sp.setProperty("25", "Verificando…");
        it.setProperty("25", "Verifica...");
        jp.setProperty("25", "認証中…");
        idn.setProperty("25", "Verifikasi... ");

        zh_cn.setProperty("26", "发送验证码");
        vi.setProperty("26", "Gửi mã xác nhận");
        th.setProperty("26", "ส่งรหัสยืนยัน");
        ar.setProperty("26", "إرسال رمز التحقق");
        en.setProperty("26", "Send the code");
        kr.setProperty("26", "인증번호 보내기");
        zh_hk.setProperty("26", "發送驗證碼");
        fr.setProperty("26", "Envoyer le code");
        br.setProperty("26", "Enviar o código");
        de.setProperty("26", "Code senden");
        sp.setProperty("26", "Enviar código");
        it.setProperty("26", "Inviare il codice");
        jp.setProperty("26", "認証コードを発送します");
        idn.setProperty("26", "Kirim Kode");

        zh_cn.setProperty("27", "正在领取...");
        vi.setProperty("27", "Đang nhận…");
        th.setProperty("27", "กำลังรับ...");
        ar.setProperty("27", "قيد الاستلام ...");
        en.setProperty("27", "Receiving ...");
        kr.setProperty("27", "가져 오는 중...");
        zh_hk.setProperty("27", "正在領取…");
        fr.setProperty("27", "Réception...");
        br.setProperty("27", "Recebendo...");
        de.setProperty("27", "Erhalten ...");
        sp.setProperty("27", "Recibiendo…");
        it.setProperty("27", "ricezione...");
        jp.setProperty("27", "受取中…");
        idn.setProperty("27", "Sedang menerima...");

        zh_cn.setProperty("28", "领取成功，请到游戏内查看");
        vi.setProperty("28", "Nhận thành công, hãy vào game kiểm tra");
        th.setProperty("28", "รับสำเร็จ");
        ar.setProperty("28", "تم الاستلام بنجاح، يرجى التحقق في اللعبة");
        en.setProperty("28", "To receive success, please go to the game view");
        kr.setProperty("28", "가져오기 성공! 게임 안에서 확인해보십시오");
        zh_hk.setProperty("28", "領取成功，請至遊戲內確認");
        fr.setProperty("28", "Reçu avec succès. Veuillez vérifier en jeu.");
        br.setProperty("28", "Recebido com sucesso. Verifique no jogo.");
        de.setProperty("28", "Erhalten. Bitte im Spiel anm.");
        sp.setProperty("28", "Recibido con éxito. Compruébalo en el juego.");
        it.setProperty("28", "Ricezione avvenuta con successo, Si prega di verificare nel gioco");
        jp.setProperty("28", "受取成功！ゲーム内でチェックしてください。");
        idn.setProperty("28", "Sukses terima. Silahkan cek di game.");

        zh_cn.setProperty("29", "领取失败，请重试");
        vi.setProperty("29", "Nhận thất bại, hãy thử lại");
        th.setProperty("29", "ล้มเหลว กรุณาลองใหม่");
        en.setProperty("29", "Failed to receive. Please try again");
        ar.setProperty("29", "فشل الاستلام، يرجى المحاولة مرة أخرى");
        kr.setProperty("29", "가져오기 실페! 다시 시도해 주십시오");
        zh_hk.setProperty("29", "領取失敗，請重試");
        fr.setProperty("29", "Échec de réception. Veuillez réessayer ultérieurement");
        br.setProperty("29", "Falha na receção. Tente novamente.");
        de.setProperty("29", "Erhalt fehlgeschl., bitte erneut vers.");
        sp.setProperty("29", "Error al recibir. Vuelve a intentarlo.");
        it.setProperty("29", "Ricezione non riuscita. Riprovare di nuovo");
        jp.setProperty("29", "受取失敗、もう一度試してください");
        idn.setProperty("29", "Gagal ambil. Coba kembali");

        zh_cn.setProperty("30", "游客用户需要绑定平台账户才能使用此功能");
        vi.setProperty("30", "Người dùng chơi thử cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("30", "ไอดีเยี่ยมชมต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("30", "تحتاج الحسابات الزائرة إلى تقييد حساباتها  للتمكن من استخدام هذه الميزة");
        en.setProperty("30", "Guest users will need to bind their platform account to use this feature");
        kr.setProperty("30", "이 기능은 연동된 플랫품 계정하는 사용자만 사용하실 수 있습니다.");
        zh_hk.setProperty("30", "遊客帳號需要綁定平台帳號才能使用此功能");
        fr.setProperty("30", "Les comptes invité doivent d’abord faire une liaison");
        br.setProperty("30", "Contas de convidado necessitam de vincular primeiro");
        de.setProperty("30", "Gastkonto zuerst mit Plattformkonto verb.");
        sp.setProperty("30", "Las cuentas de invitado deben ser vinculadas primero.");
        it.setProperty("30", "Il guest account deve prima essere associato al platform account");
        jp.setProperty("30", "ゲストアカウントは、まずプラットフォームアカウントに連携する必要があります");
        idn.setProperty("30", "Akun Guest harus segera bind akun");

        zh_cn.setProperty("31", "Facebook用户需要绑定平台账户才能使用此功能");
        vi.setProperty("31", "Người dùng Facebook cần cố định tài khoản mới có thể dùng chức năng này");
        th.setProperty("31", "ไอดี Facebook ต้องผูกมัดไอดีก่อนจึงจะใช้ฟังก์ชั่นได้");
        ar.setProperty("31", "يحتاج مستخدمو الفايسبوك إلى تقييد حساباتهم للتمكن من استخدام هذه الميزة");
        en.setProperty("31", "Facebook users need to bind the platform account to use this feature");
        kr.setProperty("31", "Facebook 사용자가 플랫폼 계정을 연동하여 이 기능을 사용하실 수 있습니다.");
        zh_hk.setProperty("31", "Facebook會員需要綁定平台帳號才能使用此功能");
        fr.setProperty("31", "Les utilisateurs Facebook doivent d’abord faire une liaison");
        br.setProperty("31", "Usuários de Facebook necessitam de vincular a conta");
        de.setProperty("31", "Facebook-Nutzer müssen zuerst das Plattformkonto verb.");
        sp.setProperty("31", "Los usuarios de Facebook deben vincular sus cuentas.");
        it.setProperty("31", "l'utente Facebook deve prima essere associato al platform account");
        jp.setProperty("31", "Facebookアカウントは、まずプラットフォームアカウントに連携する必要があります");
        idn.setProperty("31", "User Facebook harus segera bind akun");

        zh_cn.setProperty("32", "当前没有绑定邮箱!需要先绑定邮箱才能使用找回密码");
        vi.setProperty("32", "Chưa cố định Email! Cần cố định Email mới có thể tìm lại mật khẩu");
        th.setProperty("32", "ยังไม่ได้ผูกมัดไอดี ต้องผูกมัดไอดีก่อนจึงจะรีเซ็ตรหัสได้");
        ar.setProperty("32", "لا يوجد حساب مقيد! تحتاج إلى تقييد الحساب بعنوان بريد إلكتروني أولا لاسترداد كلمة المرور");
        en.setProperty("32", "There is no binding mailbox at present! Bind mailbox to use retrieve password");
        kr.setProperty("32", "현재 이 계정을 연동하는 이메일 주소가 없습니다! 비밀번호 찾기는 연동된 이메일 주소로만 할 수 있습니다");
        zh_hk.setProperty("32", "尚未綁定電子信箱！需要先綁定電子信箱才能使用找回密碼");
        fr.setProperty("32", "Aucun email lié ! La liaison par email permet de pouvoir récupérer son mot de passe.");
        br.setProperty("32", "Não existe vinculação ao email. Vincular no email permite recuperar a senha.");
        de.setProperty("32", "Keine Mailbox verb.! Diese verb. ermöglicht Passwortwiederherst.");
        sp.setProperty("32", "No hay correos vinculados. Vincula tu correo para poder recuperar tu contraseña.");
        it.setProperty("32", "Nessuna email associata! L'associazione di un'email permette la ricezione della password.");
        jp.setProperty("32", "まだメールアドレスに連携していません！メールアドレスに連携してからパスワードを取得できます");
        idn.setProperty("32", "Bind email kosong! Bind email untuk mendapatkan password kembali.");

        zh_cn.setProperty("33", "密码不能为空");
        vi.setProperty("33", "Mật khẩu không thể trống");
        th.setProperty("33", "รหัสปล่อยว่างไม่ได้");
        ar.setProperty("33", "خانة كلمة المرور لا يمكن أن تكون فارغة");
        en.setProperty("33", "Enter the correct account and password");
        kr.setProperty("33", "비밀 번호를 입력해주십시오");
        zh_hk.setProperty("33", "密碼不能為空白");
        fr.setProperty("33", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("33", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("33", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        sp.setProperty("33", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("33", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("33", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("33", "Password harus 6-14 karakter(huruf / angka).");

        zh_cn.setProperty("34", "正在注册...");
        vi.setProperty("34", "Đang đăng ký...");
        th.setProperty("34", "กำลังลงทะเบียน...");
        ar.setProperty("34", "تسجيل ...");
        en.setProperty("34", "Signing up ...");
        kr.setProperty("34", "등록 중...");
        zh_hk.setProperty("34", "正在註冊…");
        fr.setProperty("34", "Inscription...");
        br.setProperty("34", "Registrando...");
        de.setProperty("34", "Registriert …");
        sp.setProperty("34", "Registrando");
        it.setProperty("34", "Registrazione...");
        jp.setProperty("34", "登録中…");
        idn.setProperty("34", "Sedang register...");

        zh_cn.setProperty("35", "注册失败");
        vi.setProperty("35", "Định dạng Email không đúng");
        th.setProperty("35", "ลงทะเบียนล้มเหลว");
        ar.setProperty("35", "فشل التسجيل");
        en.setProperty("35", "registration failed");
        kr.setProperty("35", "등록 실패");
        zh_hk.setProperty("35", "註冊失敗");
        fr.setProperty("35", "Inscription échouée");
        br.setProperty("35", "Falha no registro");
        de.setProperty("35", "Registrieren fehlgeschl.");
        sp.setProperty("35", "Registro fallido");
        it.setProperty("35", "Registrazione non riuscita");
        jp.setProperty("35", "登録失敗");
        idn.setProperty("35", "Registrasi gagal");

        zh_cn.setProperty("36", "邮箱格式不正确");
        vi.setProperty("36", "Định dạng Email không đúng");
        th.setProperty("36", "รูปแบบ E-mail ไม่ถูกต้อง");
        ar.setProperty("36", "عنوان البريد الإلكتروني غير صالح");
        en.setProperty("36", "E-mail format is incorrect");
        kr.setProperty("36", "올바르지 않은 이메일 형식입니다");
        zh_hk.setProperty("36", "電子信箱格式不正確");
        fr.setProperty("36", "Format d’adresse e-mail incorrecte");
        br.setProperty("36", "Formato de endereço de email inválido");
        de.setProperty("36", "Ungültiges E-Mail-Format");
        sp.setProperty("36", "Formato de correo no válido");
        it.setProperty("36", "Formato dell'indirizzo email invalido");
        jp.setProperty("36", "メールアドレスの形式が無効です");
        idn.setProperty("36", "Format alamat email salah");

        zh_cn.setProperty("37", "邮箱不能为空");
        vi.setProperty("37", "Email không thể trống");
        th.setProperty("37", "E-mail ปล่อยว่างไม่ได้");
        ar.setProperty("37", "عنوان البريد الإلكتروني لا يمكن أن يكون فارغا");
        en.setProperty("37", "E-mail can not be empty");
        kr.setProperty("37", "이메일 주소를 입력해주십시오");
        zh_hk.setProperty("37", "電子信箱不能為空白");
        fr.setProperty("37", "L’adresse email ne peut pas être vide");
        br.setProperty("37", "O endereço de email não pode ficar em branco");
        de.setProperty("37", "E-Mail-Addresse darf nicht leer sein");
        sp.setProperty("37", "El correo no puede quedar en blanco");
        it.setProperty("37", "L'email non può essere vuota");
        jp.setProperty("37", "メールアドレスを入力しなければなりません");
        idn.setProperty("37", "Alamat email harus diisi");

        zh_cn.setProperty("39", "验证码不能为空");
        vi.setProperty("39", "Mã xác nhận không thể trống");
        th.setProperty("39", "รหัสยืนยันปล่อยว่างไม่ได้");
        ar.setProperty("39", "خانة رمز التحقق لا يمكن أن تكون فارغة");
        en.setProperty("39", "verification code must be filled");
        kr.setProperty("39", "인증 번호를 입력해주십시");
        zh_hk.setProperty("39", "驗證碼不能為空白");
        fr.setProperty("39", "Le code de vérification ne peut pas être vide");
        br.setProperty("39", "Código de verificação não pode ficar em branco");
        de.setProperty("39", "Bestätigungscode darf nicht leer sein");
        sp.setProperty("39", "El código de verificación no puede quedar en blanco");
        it.setProperty("39", "Il codice di conferma non può essere vuoto");
        jp.setProperty("39", "認証コードを入力しなければなりません");
        idn.setProperty("39", "Koder verifikasi harus diisi");

        zh_cn.setProperty("38Reg", "用户名格式不正确");
        vi.setProperty("38Reg", "Định dạng người dùng không hợp lệ");
        th.setProperty("38Reg", "รูปแบบชื่อไอดีไม่ถูกต้อง");
        ar.setProperty("38Reg", "شكل اسم المستخدم غير صالح");
        en.setProperty("38Reg", "Account must be 6-14 characters Letters or Numbers");
        kr.setProperty("38Reg", "올바르지 않은 사용자 아이디 형식입니다");
        zh_hk.setProperty("38Reg", "帳號格式不正確");
        fr.setProperty("38Reg", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("38Reg", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("38Reg", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        sp.setProperty("38Reg", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("38Reg", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("38Reg", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("38Reg", "Akun harus 6-14 karakter(huruf / angka).");


        zh_cn.setProperty("38", "用户名格式不正确");
        vi.setProperty("38", "Định dạng người dùng không hợp lệ");
        th.setProperty("38", "รูปแบบชื่อไอดีไม่ถูกต้อง");
        ar.setProperty("38", "شكل اسم المستخدم غير صالح");
        en.setProperty("38", "Username format is incorrect");
        kr.setProperty("38", "올바르지 않은 사용자 아이디 형식입니다");
        zh_hk.setProperty("38", "帳號格式不正確");
        fr.setProperty("38", "Le Compte doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("38", "A conta deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("38", "Konto muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen)");
        sp.setProperty("38", "La cuenta debe tener entre 6 y 14 caracteres (letras o números)");
        it.setProperty("38", "L'account può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("38", "アカウントは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("38", "Akun harus 6-14 karakter(huruf / angka).");


        zh_cn.setProperty("40", "验证码格式不正确");
        vi.setProperty("40", "Mã xác nhận không đúng");
        th.setProperty("40", "รูปแบบรหัสยืนยันไม่ถูกต้อง");
        ar.setProperty("40", "شكل رمز التحقق غير صالح");
        en.setProperty("40", "The verification code is not in the correct format");
        kr.setProperty("40", "올바르지 않은 인증번호 형식입니다");
        zh_hk.setProperty("40", "驗證碼格式不正確");
        fr.setProperty("40", "Format de code de vérification invalide");
        br.setProperty("40", "Formato de código de verificação inválido");
        de.setProperty("40", "Ungült. Bestätigungscode-Format");
        sp.setProperty("40", "Código de verificación no válido");
        it.setProperty("40", "Formato del codice di conferma invalido");
        jp.setProperty("40", "認証コードの形式が無効です");
        idn.setProperty("40", "Format kode verifikasi salah");


        zh_cn.setProperty("regspw", "密码格式不正确");
        vi.setProperty("regspw", "Lỗi định dạng mật khẩu");
        th.setProperty("regspw", "รูปแบบรหัสไม่ถูกต้อง");
        ar.setProperty("regspw", "شكل كلمة المرور غير صالح");
        en.setProperty("regspw", "Password must be 6-14 characters Letters or Numbers");
        kr.setProperty("regspw", "올바르지 않은 비밀번호 형식입니다");
        zh_hk.setProperty("regspw", "密碼格式不正確");
        fr.setProperty("regspw", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("regspw", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("regspw", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        sp.setProperty("regspw", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("regspw", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("regspw", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("regspw", "Password harus 6-14 karakter(huruf / angka).");


        zh_cn.setProperty("41", "密码格式不正确");
        vi.setProperty("41", "Lỗi định dạng mật khẩu");
        th.setProperty("41", "รูปแบบรหัสไม่ถูกต้อง");
        ar.setProperty("41", "شكل كلمة المرور غير صالح");
        en.setProperty("41", "The password is not formatted correctly");
        kr.setProperty("41", "올바르지 않은 비밀번호 형식입니다");
        zh_hk.setProperty("41", "密碼格式不正確");
        fr.setProperty("41", "Le MDP doit contenir de 6 à 14 caractères ( lettres ou chiffres)");
        br.setProperty("41", "A senha deve ter entre 6 e 14 carateres (letras ou números).");
        de.setProperty("41", "Passwort muss 6-14 Zeichen lang sein (Buchstaben oder Zahlen).");
        sp.setProperty("41", "Debe tener entre 6 y 14 caracteres con al menos una letra y un número");
        it.setProperty("41", "La password può contenere 6-14 caratteri (lettere o numeri) ");
        jp.setProperty("41", "パスワードは6ー14文字内にしてください(アルファベット或いは数字)");
        idn.setProperty("41", "Password harus 6-14 karakter(huruf / angka).");


        zh_cn.setProperty("42", "正在开启支付");
        vi.setProperty("42", "Đang mở thanh toán");
        th.setProperty("42", "กำลังเปิดการชำระเงิน");
        ar.setProperty("42", "الأجور افتتاح");
        en.setProperty("42", "Opening payment");
        kr.setProperty("42", "지불하기를 불러오는중..");
        zh_hk.setProperty("42", "正在開啟付款");
        fr.setProperty("42", "Ouverture du paiement");
        br.setProperty("42", "Abrindo pagamento");
        de.setProperty("42", "Öffnet Zahlung");
        sp.setProperty("42", "Abriendo pago");
        it.setProperty("42", "Apertura pagamento");
        jp.setProperty("42", "支払が起動中");
        idn.setProperty("42", "Membuka pembayaran");

        zh_cn.setProperty("43", "关闭");
        vi.setProperty("43", "Đóng");
        th.setProperty("43", "ปิด");
        ar.setProperty("43", "إغلاق");
        en.setProperty("43", "shut down");
        kr.setProperty("43", "닫기");
        zh_hk.setProperty("43", "關閉");
        fr.setProperty("43", "Fermer");
        br.setProperty("43", "Fechar");
        de.setProperty("43", "Schließen");
        sp.setProperty("43", "Cerrar");
        it.setProperty("43", "Chiudi");
        jp.setProperty("43", "閉じる");
        idn.setProperty("43", "Tutup");


        zh_cn.setProperty("44", "请输入验证码");
        vi.setProperty("44", "Nhập mã xác nhận");
        th.setProperty("44", "กรุณาใส่รหัสยืนยัน");
        ar.setProperty("44", "الرجاء إدخال رمز التحقق");
        en.setProperty("44", "Please enter the verification code");
        kr.setProperty("44", "인증번호를 입력해 주십시오.");
        zh_hk.setProperty("44", "請輸入驗證碼");
        fr.setProperty("44", "Veuillez entrer le code de vérification");
        br.setProperty("44", "Insira o código de verificação");
        de.setProperty("44", "Bestätigungscode eingeben");
        sp.setProperty("44", "Introduce el código de verificación");
        it.setProperty("44", "Si prega di inserire il codice di conferma");
        jp.setProperty("44", "認証コードを入力してください");
        idn.setProperty("44", "Masukkan kode verifikasi");


        zh_cn.setProperty("45", "Facebook");
        vi.setProperty("45", "Facebook");
        th.setProperty("45", "Facebook");
        ar.setProperty("45", "فايسبوك");
        en.setProperty("45", "Facebook");
        kr.setProperty("45", "Facebook");
        zh_hk.setProperty("45", "Facebook");
        fr.setProperty("45", "Facebook");
        br.setProperty("45", "Facebook");
        de.setProperty("45", "Facebook");
        sp.setProperty("45", "Facebook");
        it.setProperty("45", "Facebook");
        jp.setProperty("45", "Facebook");
        idn.setProperty("45", "Facebook");

        zh_cn.setProperty("46", "绑定 Facebook");
        vi.setProperty("46", "Cố định Facebook");
        th.setProperty("46", "ผูกมัด Facebook");
        ar.setProperty("46", "فايسبوك");
        en.setProperty("46", "Bind Facebook");
        kr.setProperty("46", "Facebook계정을 연동하기");
        zh_hk.setProperty("46", "綁定Facebook");
        fr.setProperty("46", "Lier Facebook");
        br.setProperty("46", "Vincular Facebook");
        de.setProperty("46", "Facebook verb.");
        sp.setProperty("46", "Vincular Facebook");
        it.setProperty("46", "Associa Facebook");
        jp.setProperty("46", "Facebookに連携する");
        idn.setProperty("46", "Bind Facebook");

        zh_cn.setProperty("autoLogin", "默认此账户为下次登录账户");
        th.setProperty("autoLogin", "Mặc định sử dụng tài khoản này cho lần đăng nhập kế tiếp");
        vi.setProperty("autoLogin", "ตั้งไอดีนี้เป็นค่าเริ่มต้น");
        ar.setProperty("autoLogin", "اعتمد هذا الحساب ليكون الحساب الافتراضي");
        en.setProperty("autoLogin", "Set this account as a default user account");
        kr.setProperty("autoLogin", "오토 로그인");
        zh_hk.setProperty("autoLogin", "設定此帳號為下次登入帳號");
        fr.setProperty("autoLogin", "Connexion-auto");
        br.setProperty("autoLogin", "Acesso Automático");
        de.setProperty("autoLogin", "Auto-Anm.");
        sp.setProperty("autoLogin", "Acceder automáticamente");
        it.setProperty("autoLogin", "Auto-login");
        jp.setProperty("autoLogin", "自動ログイン");
        idn.setProperty("autoLogin", "Auto-login");


        zh_cn.setProperty("cardnull", "卡号不能为空");
        th.setProperty("cardnull", "ไม่สามารถเว้นได้");
        vi.setProperty("cardnull", "Mã số thẻ không thể trống");
        ar.setProperty("cardnull", "卡号不能为空");
        en.setProperty("cardnull", "卡号不能为空");
        kr.setProperty("cardnull", "卡号不能为空");
        zh_hk.setProperty("cardnull", "สลับไอดี");
        fr.setProperty("cardnull", "Le numéro de carte ne peut pas être vide");
        br.setProperty("cardnull", "Número de cartão não pode ficar em branco");
        de.setProperty("cardnull", "Karten-Nr. darf nicht leer sein");
        sp.setProperty("cardnull", "El número de tarjeta no puede quedar en blanco");
        it.setProperty("cardnull", "Il numero della carta non può essere vuoto");
        jp.setProperty("cardnull", "カードナンバーを入力しなければいけません");
        idn.setProperty("cardnull", "Nomor kartu harus diisi");


        zh_cn.setProperty("nutspayinfo", "Ncoin说明:\n" +
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
        kr.setProperty("nutspayinfo", "卡号不能为空");
        zh_hk.setProperty("nutspayinfo", "Ncoin說明：\n" +
                "1。使用Ncoin儲值，比同額度卡儲值多10％鑽石\n" +
                "2.Ncoin通過專屬通道獲得，詳細辦法可通過粉絲專頁洽詢客服");
        fr.setProperty("nutspayinfo", "mua");
        br.setProperty("nutspayinfo", "mua");
        de.setProperty("nutspayinfo", "mua");
        sp.setProperty("nutspayinfo", "mua");
        it.setProperty("nutspayinfo", "mua");
        jp.setProperty("nutspayinfo", "mua");
        idn.setProperty("nutspayinfo", "mua");

        //新增的提示说明
        zh_cn.setProperty("login_first", "请先登录");
        th.setProperty("login_first", "login first");
        vi.setProperty("login_first", "login first");
        ar.setProperty("login_first", "login first");
        en.setProperty("login_first", "login first");
        kr.setProperty("login_first", "login first");
        zh_hk.setProperty("login_first", "login first");
        fr.setProperty("login_first", "login first");
        br.setProperty("login_first", "login first");
        de.setProperty("login_first", "login first");
        sp.setProperty("login_first", "login first");
        it.setProperty("login_first", "login first");
        jp.setProperty("login_first", "login first");
        idn.setProperty("login_first", "login first");

        zh_cn.setProperty("tip", "提示");
        th.setProperty("tip", "Tip");
        vi.setProperty("tip", "Tip");
        ar.setProperty("tip", "Tip");
        en.setProperty("tip", "Tip");
        kr.setProperty("tip", "Tip");
        zh_hk.setProperty("tip", "Tip");
        fr.setProperty("tip", "Tip");
        br.setProperty("tip", "Tip");
        de.setProperty("tip", "Tip");
        sp.setProperty("tip", "Tip");
        it.setProperty("tip", "Tip");
        jp.setProperty("tip", "Tip");
        idn.setProperty("tip", "Tip");


//    <string name = "str_verify_code_send_success" > 验证码发送成功 </string >
//    <string name = "str_verify_code_send_failed" > 验证码发送失败 </string >
//    <string name = "str_verify_code_tip" > 验证码不能为空 </string >
//    <string name = "str_register_failed" > 注册失败 </string >
//    <string name = "str_bind_failed" > 绑定失败 </string >
//    <string name = "str_verify_code_error" > 验证码输入有误，请重新输入</string >
//    <string name = "reset_pwd" > 重置 </string >
//    <string name = "bind_phone_number" > 绑定 </string >


        zh_cn.setProperty("str_bind_account_tip", "游戏账号仅供使用，为了您的账户安全，请及时绑定注册账号");
        zh_cn.setProperty("str_bind_account", "绑定账户");
        zh_cn.setProperty("str_enter_game", "进入游戏");
        zh_cn.setProperty("check_your_network", "请求失败，请检查您的网络连接");
        zh_cn.setProperty("user_protocol", "\n" +
                "用户注册协议<br/> 一、总则\n" +
                "<br/> 1.1 本《用户注册协议》为您（即“用户”）与北京点悦互动软件科技有限公司（以下简称“点悦互动”）就点悦互动所提供的服务达成的协议。点悦互动在此特别提醒您认真阅读、充分理解本《协议》:用户应认真阅读、充分理解本《协议》中各条款，特别涉及免除或者限制点悦互动责任的免责条款，对用户的权利限制的条款，法律适用、争议解决方式的条款。如果您未满18周岁，请在法定监护人的陪同下阅读本《协议》。<br/> 1.2 请您审慎阅读并选择同意或不同意本《协议》，除非您接受本《协议》所有条款，否则您无权以下载、安装、升级、登陆、显示、运行、截屏等方式使用本软件及其相关服务。您的下载、安装、显示、账号获取和登录、截屏等行为表明您自愿接受本协议的全部内容并受其约束，不得以任何理由包括但不限于以未能认真阅读本协议等作为纠纷抗辩理由。<br/>1.3 点悦互动有权不定期对本《协议》进行必要的更新，更新后的协议条款一旦公布即代替原来的协议条款，您可在游戏平台相关页面查看最新版协议条款。在点悦互动修改《协议》条款后，如果您不接受更新后的条款，请立即停止使用点悦互动提供的软件和服务，您继续使用点悦互动提供的软件和服务将被视为已接受了更新后的协议。<br/> 1.4 本《协议》内容包括但不限于本协议以下内容，针对某些具体服务所约定的管理办法、公告、重要提示、指引、说明等均为本协议的补充内容，为本协议不可分割之组成部分，具有与本协议同等的法律效力，接受本协议即视为您同时接受以上管理办法、公告、重要提示、指引、说明等并受其约束；否则请您立即停止使用点悦互动提供的软件和服务。同时提醒用户，本协议已包含文化部根据《网络游戏管理暂行办法》（文化部令第49号）制定的《网络游戏服务格式化协议必备条款》。<br/>二、服务说明\n" +
                "<br/> 2.1经由本平台而发布、上传的游戏、文字、资讯、资料、音乐、照片、图形、视讯、信息或其它资料（以下简称“内容 ”），均由内容提供者授权提供、上传并承担责任。本平台仅为内容提供者提供信息网络存储空间。<br/> 2.2本平台对用户提供平台服务，包括但不限于授权用户通过其账号进行在线游戏、即时通讯、添加好友、加入小组、发布评论。点悦互动可以对其提供的所有服务予以变更、增加或强化，包括所推出的新功能，均受到本协议之规范。\n" +
                "<br/> 2.3用户了解并同意，点悦互动仅依其当前所呈现状况向您提供服务，对于任何用户信息或个人化设定之时效、删除、传递错误、未予储存或其他任何问题，点悦互动均不承担任何责任。\n" +
                "<br/> 2.4您需要自行负担个人上网或第三方（包括但不限于电信或移动通信提供商）收取的通讯费、信息费等有关费用。\n" +
                "<br/> 2.5鉴于网络服务的特殊性，您了解并同意点悦互动有权不经事先通知，随时变更、中断或终止部分或全部的服务（包括收费网络服务）。点悦互动不担保网络服务不会中断，对网络服务的及时性、安全性、准确性等也都不作担保。\n" +
                "<br/> 2.6点悦互动需要定期或不定期地对提供服务的平台或相关的设备进行检修或者维护，如因此类情况而造成服务（包括收费网络服务）在合理时间内的中断，点悦互动无需为此承担任何责任。点悦互动保留不经事先通知为维修保养、升级或其它目的暂停本服务任何部分的权利。\n" +
                "<br/> 2.7用户明确同意其使用点悦互动服务所存在的风险将完全由其自己承担。用户理解并接受通过点悦互动服务取得的任何内容取决于用户自己，并由其承担系统受损、资料丢失以及其它任何风险。\n" +
                "<br/> 2.8用户充分理解并同意，为高效利用服务器资源，如果您长期未使用游戏账号登录点悦互动游戏，点悦互动有权视需要对该账号及其账号下的游戏数据及相关信息采取删除等处置措施，上述处置可能导致您对该游戏账号下相关权益的丧失，对此点悦互动不承担任何责任。\n" +
                "<br/> 2.9用户充分理解并同意：为营造公平、健康的游戏环境，在您使用点悦互动游戏服务的过程中，点悦互动有权通过技术手段了解您终端设备的随机存储内存以及与点悦互动游戏同时运行的相关程序。一经发现有任何未经授权的、危害点悦互动游戏服务正常运营的相关程序，点悦互动将收集所有与此有关的信息并采取合理措施予以打击。\n" +
                "<br/> 三、使用规则\n" +
                "<br/> 3.1 用户在申请使用点悦互动服务时，必须提供准确的个人资料，如个人资料有任何变动，必须及时更新。因用户提供个人资料不准确、不真实而引发的一切后果由用户承担。<br/> 3.2 用户对其账户、密码有保管义务，不应将其账号、密码转让、出借、销售或以任何脱离用户控制的形式交由他人使用。如用户发现其账号遭他人非法使用，应立即通知点悦互动。因黑客行为或用户的保管疏忽导致账号、密码遭他人非法使用，以及由此产生的任何后果，点悦互动不承担任何责任。<br/> 3.3 点悦互动账号的所有权归点悦互动，用户完成注册申请手续后，获得点悦互动账号的使用权。<br/>3.4 用户应当为自身注册帐户下的一切行为负责，因用户行为而导致的用户自身或其他任何第三方的任何损失或损害，点悦互动不承担责任。<br/> 3.5 用户理解并接受点悦互动提供的服务中可能包括广告，同意在使用服务的过程中显示点悦互动和第三方供应商、合作伙伴提供的广告。<br/> 3.6 用户在使用点悦互动服务过程中，必须遵循以下原则：<br/> 3.6.1 遵守中国有关的法律和法规；<br/> 3.6.2 遵守所有与网络服务有关的网络协议、规定和程序；<br/> 3.6.3 不得为任何非法目的而使用网络服务系统；<br/>3.6.4 不得利用点悦互动服务系统进行任何可能对互联网或移动网正常运转造成不利影响的行为；<br/> 3.6.5 不得利用点悦互动提供的服务上传、展示或传播任何虚假的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、庸俗淫秽的或其他任何非法的信息资料；<br/> 3.6.6 不得侵犯点悦互动和其他任何第三方的专利权、著作权、商标权、名誉权或其他任何合法权益；<br/> 3.6.7 不得利用点悦互动服务系统进行任何损害点悦互动合法权益的行为；<br/> 3.6.8不得利用任何相关系统漏洞或服务漏洞，包括但不限于点悦互动的系统或服务漏洞、点悦互动相关合作方的系统或服务漏洞，作出损害点悦互动合法权益的行为；\n" +
                "<br/> 3.6.9不得对游戏软件进行反向工程、反向汇编、反向编译或者以其他方式尝试发现软件的源代码；\n" +
                "<br/> 3.6.10不得对游戏软件或者软件运行过程中释放到任何终端内存中的数据、软件运行过程中客户端与服务器端的交互数据，以及软件运行所必需的系统数据，进行复制、修改、增加、删除、挂接运行或创作任何衍生作品，形式包括但不限于使用插件、外挂或非经合法授权的第三方工具/服务接入软件和相关系统；\n" +
                "<br/> 3.6.11 如发现任何非法使用用户账号或账号出现安全漏洞的情况，应立即通告点悦互动。<br/> 3.7 如用户在使用点悦互动服务时违反相关法律规定或者本协议的任何约定，点悦互动或其授权人有权采取包括但不限于下列措施：（1）要求用户改正相关行为；（2）变更、中断、终止用户使用点悦互动的服务；（3）对用户的账号采取临时性或永久性的禁止登陆（即封号）措施等。<br/> 四、知识产权\n" +
                "<br/> 4.1 点悦互动提供的服务中包含的任何文本、图片、图形、音频和/或视频资料均受版权、商标和/或其它财产所有权法律的保护，未经相关权利人同意，上述资料均不得用于任何商业目的。<br/> 4.2 点悦互动为提供服务而使用的任何软件（包括但不限于软件中所含的任何图像、照片、动画、录像、录音、音乐、文字和附加程序、随附的帮助材料）的一切权利均属于该软件的著作权人，未经该软件的著作权人许可，用户不得对该软件进行反向工程（reverse engineer）、反向编译（decompile）或反汇编（disassemble），不得进行任何有可能损害著作权人合法权益的行为。<br/>五、隐私保护政策与个人信息利用政策\n" +
                "<br/> 5.1 保护用户隐私是点悦互动的一项基本政策，点悦互动保证不对外公开或向第三方提供单个用户的注册资料及用户在使用本服务时存储在点悦互动的非公开内容，但下列情况除外：<br/> 5.1.1 事先获得用户的明确授权；<br/> 5.1.2 根据有关的法律法规要求；<br/> 5.1.3 按照相关政府主管部门的要求；<br/> 5.1.4 为维护社会公众的利益；<br/> 5.1.5 为维护点悦互动的合法权益。<br/> 5.2 点悦互动可能会与第三方合作向用户提供相关的网络服务，用户同意在此情况下，如该第三方同意承担与点悦互动同等的保护用户隐私的责任，则用户同意点悦互动有权将用户的注册资料等提供给该第三方。<br/>5.3 在不透露单个用户隐私资料的前提下，用户同意点悦互动有权对整个用户数据库进行分析并对用户数据库进行商业使用。<br/> 5.4 点悦互动制定了以下四项隐私权保护原则，指导我们如何来处理产品中涉及到用户隐私权和用户信息等方面的问题：<br/> （1） 利用我们收集的信息为用户提供有价值的产品和服务。<br/> （2） 开发符合隐私权标准和隐私权惯例的产品。<br/> （3） 尽最大的努力保护我们掌握的信息。<br/> 5.5点悦互动非常重视对未成年人个人信息的保护。若您是18周岁以下的未成年人，在使用点悦互动服务前，应事先取得您家长或法定监护人的同意；在使用点悦互动服务过程中同意您家长或法定监护人根据相关规定，对您的游戏账号进行相应的限制性操作。\n" +
                "<br/> 5.6点悦互动有权依据相关规定，对未成年人游戏账号进行相应的限制性操作或授权未成年人家长或法定监护人进行相应的限制性操作。用户充分理解到：点悦互动可能会将用户的实名注册信息运用于防沉迷系统之中，即点悦互动可能会根据用户的实名注册信息判断用户是否年满18周岁，从而决定是否对用户相应的游戏帐号予以防沉迷限制。\n" +
                "<br/> 六、资费说明\n" +
                "<br/> 6.1 点悦互动在提供产品和服务时，可能会对部分产品和服务向用户收取一定的费用。在此情况下，点悦互动会在相关页面上做明确的提示。<br/> 6.2点悦互动有权决定所提供的产品和服务的资费标准和收费方式，点悦互动可能会就不同的产品和服务制定不同的资费标准和收费方式，也可能按照点悦互动所提供的产品和服务的不同阶段确定不同的资费标准和收费方式。另外，点悦互动有权利根据市场实际情况修改资费政策。\n" +
                "<br/> 6.3对于点悦互动的收费产品和服务，用户可自主选择接受或拒绝该收费产品或服务，并保证在使用收费产品或服务时，将按照点悦互动的相关收费规定支付费用。如果用户未按点悦互动确定的资费政策购买产品和服务，点悦互动可以立即停止向用户提供该产品和服务，由此导致的相关后果由用户自行承担。\n" +
                "<br/> 6.4除非法律另有明文规定，否则用户不得要求点悦互动返还用户已经支付予点悦互动游戏的任何资费（以下简称“退款”），无论该等资费是否已被消费。点悦互动有权决定是否、何时、以何种方式向用户退款。点悦互动同意退款的，用户应补偿支付时使用信用卡、手机等支付渠道产生的费用，点悦互动有权在返还用户的资费中直接扣收。存在下列情况之一的，点悦互动有权不予退款：（1）用户已经消费的资费部分，不予退款；（2）用户存在违反本协议约定的行为的，点悦互动有权不予退款；（3）点悦互动在产品和服务提供过程中赠送的充值金额、虚拟商品、虚拟道具等，不予退款或变现。\n" +
                "<br/> 七、免责条款\n" +
                "<br/> 7.1 用户之间因线上游戏行为所发生或可能发生的任何心理、生理上的伤害和经济上的损失，点悦互动不承担任何责任。<br/> 7.2 用户因其个人原因造成账号资料保管不妥而导致个人信息数据被他人泄露或账号中虚拟财产、游戏道具被盗或损失的，点悦互动不承担任何责任。<br/> 7.3 用户因除了按游戏规则进行游戏的行为外的其他行为触犯了中华人民共和国法律法规的，责任自负，点悦互动不承担任何责任。<br/> 7.4 用户账号长期不使用的，点悦互动有权进行回收，因此带来的用户个人信息数据丢失、账户内虚拟财产和游戏道具清零等一切损失由用户个人承担，点悦互动不承担任何责任。<br/>7.5基于网络环境的复杂性，点悦互动不担保服务一定能满足用户的要求，不保证各项服务完全无错误、无缺陷、不会中断、或不会受到任何来自他方因素的干扰及损害等，对服务的及时性、安全性也不作担保。因网络安全、网络故障问题和其他用户的非法行为给用户造成的损失，点悦互动不承担任何责任。\n" +
                "<br/> 7.6 基于网络环境的特殊性，点悦互动不担保对用户限制性行为和禁止性行为的判断的准确性，用户因此产生的任何损失点悦互动不承担任何责任，用户可按点悦互动相关规定进行申诉解决。<br/> 7.7 点悦互动不保证您从第三方获得的点悦互动虚拟商品、游戏道具等游戏物品能正常使用，也不保证该等物品不被索回，因私下购买虚拟商品、游戏道具等游戏物品所产生的一切损失均由用户自行承担，点悦互动不承担任何责任。<br/> 7.8用户在使用点悦互动服务时，必须遵守中华人民共和国相关法律法规的规定，用户同意将不会利用本服务进行任何违法或不正当的活动，包括但不限于下列行为∶\n" +
                "<br/> 上传、展示、张贴、传播或以其它方式传送含有下列内容之一的信息：\n" +
                "<br/> （1）反对宪法所确定的基本原则的；\n" +
                "<br/> （2）危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；\n" +
                "<br/> （3）损害国家荣誉和利益的；\n" +
                "<br/> （4）煽动民族仇恨、民族歧视、破坏民族团结的；\n" +
                "<br/> （5）破坏国家宗教政策，宣扬邪教和封建迷信的；\n" +
                "<br/> （6）散布谣言，扰乱社会秩序，破坏社会稳定的；\n" +
                "<br/> （7）散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；\n" +
                "<br/> （8）侮辱或者诽谤他人，侵害他人合法权利的；\n" +
                "<br/> （9）含有虚假、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容；\n" +
                "<br/> （10）含有中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其它内容的或以任何方式危害他人的合法权益。\n" +
                "<br/> 7.9用户违反本协议的约定或相关的服务条款的规定，导致或产生的任何第三方主张的任何索赔、要求或损失，包括合理的律师费，您同意赔偿点悦互动与合作公司、关联公司，并使之免受损害。对此，点悦互动有权视用户的行为性质，采取包括但不限于下列一项或多项措施：（1）删除用户发布信息内容；（2）暂停或终止使用许可；（3）暂停或终止服务；（4）限制或禁止使用账号（封号）；（5）回收账号；（6）追究法律责任等。因上述措施所导致的用户帐号内相关游戏道具（包括但不限于游戏虚拟商品、游戏装备等）、信息数据等过期或者失效等后果，由用户自行承担。同时，点悦互动会按照司法部门的要求，协助司法调查。\n" +
                "<br/> 八、未成年人使用条款及健康游戏忠告\n" +
                "<br/> 8.1 未成年人用户必须遵守全国青少年网络文明公约：<br/> 要善于网上学习，不浏览不良信息；要诚实友好交流，不侮辱欺诈他人；要增强自护意识，不随意约会网友；要维护网络安全，不破坏网络秩序；要有益身心健康，不沉溺虚拟时空。\n" +
                "<br/> 8.2根据国家新闻出版总署关于健康游戏的忠告，点悦互动提醒您：抵制不良游戏，拒绝盗版游戏；注意自我保护，谨防受骗上当；适度游戏益脑，沉迷游戏伤身。\n" +
                "<br/> 九、法律适用及争议解决\n" +
                "<br/> 9.1 本协议适用中华人民共和国大陆地区法律。<br/> 9.2 因本协议引起的或与本协议有关的任何争议，各方应友好协商解决；如协商不成，任何一方均有权将争议提交北京仲裁委员会，按照提交仲裁时该会现行有效的仲裁规则进行仲裁。<br/> 十、其他条款\n" +
                "<br/> 10.1 如果本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，或违反任何适用的法律，则该条款被视为删除，但本协议的其余条款仍继续有效并且有约束力。<br/> 10.2 点悦互动在法律允许范围内对本协议拥有解释权与修改权。<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/> 附件一：网络游戏服务格式化协议必备条款\n" +
                "<br/> 根据《网络游戏管理暂行办法》（文化部令第49号），文化部制定《网络游戏服务格式化协议必备条款》。甲方为网络游戏运营企业，乙方为网络游戏用户。\n" +
                "<br/> 一、账号注册\n" +
                "<br/> 1.1 乙方承诺以其真实身份注册成为甲方的用户，并保证所提供的个人身份资料信息真实、完整、有效，依据法律规定和必备条款约定对所提供的信息承担相应的法律责任。<br/> 1.2 乙方以其真实身份注册成为甲方用户后，需要修改所提供的个人身份资料信息的，甲方应当及时、有效地为其提供该项服务。<br/> 二、用户账号使用与保管\n" +
                "<br/> 2.1 根据必备条款的约定，甲方有权审查乙方注册所提供的身份信息是否真实、有效，并应积极地采取技术与管理等合理措施保障用户账号的安全、有效；乙方有义务妥善保管其账号及密码，并正确、安全地使用其账号及密码。任何一方未尽上述义务导致账号密码遗失、账号被盗等情形而给乙方和他人的民事权利造成损害的，应当承担由此产生的法律责任。<br/> 2.2乙方对登录后所持账号产生的行为依法享有权利和承担责任。\n" +
                "<br/> 2.3 乙方发现其账号或密码被他人非法使用或有使用异常的情况的，应及时根据甲方公布的处理方式通知甲方，并有权通知甲方采取措施暂停该账号的登录和使用。<br/> 2.4 甲方根据乙方的通知采取措施暂停乙方账号的登录和使用的，甲方应当要求乙方提供并核实与其注册身份信息相一致的个人有效身份信息。<br/> 2.4.1 甲方核实乙方所提供的个人有效身份信息与所注册的身份信息相一致的，应当及时采取措施暂停乙方账号的登录和使用。<br/> 2.4.2 甲方违反2.4.1款项的约定，未及时采取措施暂停乙方账号的登录和使用，因此而给乙方造成损失的，应当承担其相应的法律责任。<br/>2.4.3 乙方不能提供其个人有效身份证件或者乙方提供的个人有效身份证件与所注册的身份信息不一致的，甲方有权拒绝乙方上述请求。<br/> 2.5 乙方为了维护其合法权益，向甲方提供与所注册的身份信息相一致的个人有效身份信息时，甲方应当为乙方提供账号注册人证明、原始注册信息等必要的协助和支持，并根据需要向有关行政机关和司法机关提供相关证据信息资料。<br/> 三、服务的中止与终止\n" +
                "<br/> 3.1乙方有发布违法信息、严重违背社会公德、以及其他违反法律禁止性规定的行为，甲方应当立即终止对乙方提供服务。\n" +
                "<br/> 3.2乙方在接受甲方服务时实施不正当行为的，甲方有权终止对乙方提供服务。该不正当行为的具体情形应当在本协议中有明确约定或属于甲方事先明确告知的应被终止服务的禁止性行为，否则，甲方不得终止对乙方提供服务。\n" +
                "<br/> 3.3乙方提供虚假注册身份信息，或实施违反本协议的行为，甲方有权中止对乙方提供全部或部分服务；甲方采取中止措施应当通知乙方并告知中止期间，中止期间应该是合理的，中止期间届满甲方应当及时恢复对乙方的服务。\n" +
                "<br/> 3.4 甲方根据本条约定中止或终止对乙方提供部分或全部服务的，甲方应负举证责任。<br/> 四、用户信息保护\n" +
                "<br/> 4.1 甲方要求乙方提供与其个人身份有关的信息资料时，应当事先以明确而易见的方式向乙方公开其隐私权保护政策和个人信息利用政策，并采取必要措施保护乙方的个人信息资料的安全。<br/> 4.2未经乙方许可甲方不得向任何第三方提供、公开或共享乙方注册资料中的姓名、个人有效身份证件号码、联系方式、家庭住址等个人身份信息，但下列情况除外：\n" +
                "<br/> 4.2.1 乙方或乙方监护人授权甲方披露的；<br/> 4.2.2 有关法律要求甲方披露的；<br/> 4.2.3 司法机关或行政机关基于法定程序要求甲方提供的；<br/> 4.2.4 甲方为了维护自己合法权益而向乙方提起诉讼或者仲裁时；<br/> 4.2.5 应乙方监护人的合法要求而提供乙方个人身份信息时。<br/>\n" +
                "<br/> 附件二：《点悦互动游戏用户个人信息及隐私保护政策》\n" +
                "<br/> 点悦互动游戏用户个人信息及隐私保护政策\n" +
                "<br/> 《点悦互动游戏用户个人信息及隐私保护政策》重要须知：为了贯彻执行文化部颁布的《网络游戏管理暂行办法》以及《关于贯彻实施\n" +
                "<网络游戏管理暂行办法>的通知》，北京点悦互动软件科技有限公司（以下称“点悦互动”）特此制定本《点悦互动游戏用户个人信息及隐私保护政策》。<br/> 点悦互动在此特别提醒用户仔细阅读本《点悦互动游戏用户个人信息及隐私保护政策》中的各个条款（未成年人应当在其法定监护人陪同下阅读），并选择接受或者不接受本《点悦互动游戏用户个人信息及隐私保护政策》。\n" +
                "    <br/> 点悦互动严格遵守法律法规，遵循以下隐私保护原则，为您提供更加安全、可靠的服务：\n" +
                "    <br/>\n" +
                "    <br/> 1、安全可靠：\n" +
                "    <br/> 点悦互动竭尽全力通过合理有效的信息安全技术及管理流程，防止您的信息泄露、损毁、丢失。\n" +
                "    <br/> 2、自主选择：\n" +
                "    <br/> 点悦互动为您提供便利的信息管理选项，以便您做出合适的选择，管理您的个人信息。\n" +
                "    <br/> 3、保护通信秘密：\n" +
                "    <br/> 点悦互动严格遵照法律法规，保护您的通信秘密，为您提供安全的通信服务。\n" +
                "    <br/> 4、合理必要：\n" +
                "    <br/> 点悦互动为了向您和其他用户提供更好的服务，我们仅收集必要的信息。\n" +
                "    <br/> 5、清晰透明：\n" +
                "    <br/> 点悦互动努力使用简明易懂的表述，向您介绍隐私政策，以便您清晰地了解我们的信息处理方式。\n" +
                "    <br/> 6、将隐私保护融入产品设计：\n" +
                "    <br/> 点悦互动在产品或服务开发的各个环节，综合法律、产品、设计等多方因素，融入隐私保护的理念。\n" +
                "    <br/>\n" +
                "    <br/> 我们根据合法、正当、必要的原则，仅收集实现产品功能所必要的信息。\n" +
                "    <br/> 您在使用我们服务时主动提供的信息\n" +
                "    <br/>\n" +
                "    <br/> ·您在注册帐户时填写的信息\n" +
                "    <br/> 例如，您在注册游戏帐户时所填写的昵称、手机号码。\n" +
                "    <br/>\n" +
                "    <br/> ·您授权访问的信息\n" +
                "    <br/> 例如，您在登陆游戏时，我们将收集您所授予的权限。\n" +
                "    <br/>\n" +
                "    <br/> ·您通过我们的客服或参加我们举办的活动时所提交的信息\n" +
                "    <br/> 例如，您参与我们线上活动时填写的调查问卷中可能包含您的姓名、电话、家庭地址等信息。\n" +
                "    <br/> 我们的部分服务可能需要您提供特定的个人敏感信息来实现特定功能。若您选择不提供该类信息，则可能无法正常使用服务中的特定功能，但不影响您使用服务中的其他功能。若您主动提供您的个人敏感信息，即表示您同意我们按本政策所述目的和方式来处理您的个人敏感信息。\n" +
                "    <br/> 我们在您使用服务时获取的信息\n" +
                "    <br/> ·日志信息。当您使用我们的服务时，我们可能会自动收集相关信息并存储为服务日志信息。\n" +
                "    <br/> 1)设备信息\n" +
                "    <br/> 例如，设备型号、操作系统版本、唯一设备标识符、电池、信号强度等信息。\n" +
                "    <br/> 2)软件信息\n" +
                "    <br/> 例如，软件的版本号、浏览器类型。为确保操作环境的安全或提供服务所需，我们会收集有关您使用的移动应用和其他软件的信息。\n" +
                "    <br/> 3)IP地址\n" +
                "    <br/> 4)服务日志信息\n" +
                "    <br/> 例如，您在使用我们服务时搜索、查看的信息、服务故障信息、引荐网址等信息。\n" +
                "    <br/> 5)通讯日志信息\n" +
                "    <br/> 例如，您在使用我们服务时曾经通讯的账户、通讯时间和时长。\n" +
                "    <br/> ·位置信息\n" +
                "    <br/> 当您使用与位置有关的服务时，我们可能会记录您设备所在的位置信息，以便为您提供相关服务。\n" +
                "    <br/> 在您使用服务时，我们可能会通过IP地址 、GPS、WLAN（如 WiFi)或基站等途径获取您的地理位置信息；<br/> 您或其他用户在使用服务时提供的信息中可能包含您所在地理位置信息，例如您提供的帐号信息中可能包含的您所在地区信息，您或其他人共享的照片包含的地理标记信息。\n" +
                "    <br/> ·其他相关信息\n" +
                "    <br/> 为了帮助您更好地使用我们的产品或服务，我们会收集相关信息。例如，我们收集的好友列表、群列表信息、声纹特征值信息。为确保您使用我们服务时能与您认识的人进行联系，如您选择开启导入通讯录功能，我们可能对您联系人的姓名和电话号码进行加密，并仅收集加密后的信息。\n" +
                "    <br/> 其他用户分享的信息中含有您的信息\n" +
                "    <br/> 例如，其他用户发布的照片或分享的视频中可能包含您的信息。\n" +
                "    <br/>\n" +
                "    <br/> 我们严格遵守法律法规的规定及与用户的约定，将收集的信息用于以下用途。若我们超出以下用途使用您的信息，我们将再次向您进行说明，并征得您的同意。\n" +
                "    <br/> 向您提供服务\n" +
                "    <br/> 满足您的个性化需求\n" +
                "    <br/> 例如，语言设定、位置设定、个性化的帮助服务。\n" +
                "    <br/> 产品开发和服务优化\n" +
                "    <br/> 例如，当我们的系统发生故障时，我们会记录和分析系统故障时产生的信息，优化我们的服务。\n" +
                "    <br/> 安全保障\n" +
                "    <br/> 例如，我们会将您的信息用于身份验证、安全防范、反诈骗监测、存档备份、客户的安全服务等用途。\n" +
                "    <br/>\n" +
                "    <br/> 评估、改善我们的广告投放和其他促销及推广活动的效果\n" +
                "    <br/> 7、点悦互动不向第三方公开透露用户信息，但因下列原因而披露给第三方的除外：\n" +
                "    <br/> （1）基于国家法律法规的规定而对外披露；\n" +
                "    <br/> （2）应国家司法机关及其他有法律赋予权限的政府机关基于法定程序的要求而披露；\n" +
                "    <br/> （3）为保护点悦互动或您的合法权益而披露；\n" +
                "    <br/> （4）在紧急情况下，为保护其他用户及第三方人身安全而披露；\n" +
                "    <br/> （5）用户本人或用户监护人授权披露的；\n" +
                "    <br/> （6）应用户监护人合法要求而提供用户个人身份信息时。\n" +
                "    <br/>");

    }
}

