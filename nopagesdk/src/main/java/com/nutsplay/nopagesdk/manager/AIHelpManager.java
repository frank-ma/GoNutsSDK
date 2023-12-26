package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.nutsplay.nopagesdk.beans.InitParameter;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;

import net.aihelp.config.UserConfig;
import net.aihelp.init.AIHelpSupport;
import net.aihelp.ui.listener.OnAIHelpInitializedCallback;
import net.aihelp.ui.listener.OnMessageCountArrivedCallback;

/**
 * Created by frankma on 2023/10/24 9:36 PM
 * Email: frankma9103@gmail.com
 * Desc:  AiHelp客服系统
 * 文档：https://aihelp.net/FAQ/#/AIhelp-Support/app/zh-CN/EB5AE263D8AB85A4/0B9CFFA02F4F21FD/14D0F99762615284
 */
public class AIHelpManager {

    private static final String TAG = "AIHelpManager";
    private volatile static AIHelpManager INSTANCE;

    public static AIHelpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AIHelpManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new AIHelpManager();
                }
            }
        }
        return INSTANCE;
    }

    public static void initAiHelp(Activity activity, InitParameter initParameter) {
        AIHelpSupport.init(activity,
                initParameter.getAihelpAppkey(),
                initParameter.getAihelpDomain(),
                initParameter.getAihelpAppID(),
                SDKGameUtils.getAIHelpLanguage(initParameter.getLanguage()));
        AIHelpSupport.setOnAIHelpInitializedCallback(new OnAIHelpInitializedCallback() {
            @Override
            public void onAIHelpInitialized(boolean isSuccess, String message) {
                System.out.println("AIHelp初始化结果:" + isSuccess + message);
            }
        });
    }

    /**
     * E001默认的入口模板ID
     */
    public void show() {
        AIHelpSupport.show("E001");
    }

    /**
     * 你可以通过在 AIHelp 后台自定义「帮助中心」入口，用相应的入口 ID 拉起 AIHelp 帮助中心页面：
     *
     * @param entranceId
     */
    public void show(String entranceId) {
        AIHelpSupport.show(entranceId);
    }

    /**
     * 传用户信息
     * UserConfig userConfig
     *
     * @param userConfig UserConfig userConfig = new UserConfig.Builder()
     *                   .setUserId("123456789")
     *                   .setUserName("AIHelper")
     *                   .setUserTags("recharge,suggestion")
     *                   .build();
     */
    public void updateUserInfo(UserConfig userConfig) {
        AIHelpSupport.updateUserInfo(userConfig);
    }

    /**
     * 用户退出登录时调用此方法告知 AIHelp 来清除登录用户的信息，以保证游客/用户信息的准确性：
     */
    public void resetUserInfo() {
        AIHelpSupport.resetUserInfo();
    }

    public void updateSDKLanguage(String lan) {
        AIHelpSupport.updateSDKLanguage(lan);
    }

    /**
     * 未读消息
     * 方法内部每 5 分钟主动拉取一次当前用户的未读消息数量，并在以下两种情况时将结果返回给调用者：
     * <p>
     * 1、有进行中客诉的用户收到新消息时，返回该用户累计未读的消息数量；
     * <p>
     * 2、用户打开客服会话窗口时，返回数字 0 以标记用户已读当前消息。
     *
     * @param callback
     */
    public void startUnreadMessageCountPolling(OnMessageCountArrivedCallback callback) {
        AIHelpSupport.startUnreadMessageCountPolling(callback);
    }

    /**
     * 日志文件的绝对路径
     * AIHelp 目前只支持上传 .log / .bytes / .txt / .zip 格式的文件。
     *
     * @param filePath
     */
    public void uploadLog(String filePath) {
        AIHelpSupport.setUploadLogPath(filePath);
    }

    /**
     * 关闭所以AIHelp页面
     */
    public void close() {
        AIHelpSupport.close();
    }


}
