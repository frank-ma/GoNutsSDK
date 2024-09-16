package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.nutsplay.nopagesdk.beans.InitParameter;

import org.json.JSONObject;

/**
 * Created by frank-ma on 2018/12/28 下午9:23
 * Email: frankma9103@gmail.com
 * Desc: AIHELP 客服系统
 */
public class AIHelpManager {


    /**
     * 初始化AiHelp客服系统
     */
    public static void initAiHelp(Activity activity, InitParameter parameters) {

//        try {
//            if (parameters.getAihelpAppkey().isEmpty() ||
//                    parameters.getAihelpAppID().isEmpty() ||
//                    parameters.getAihelpDomain().isEmpty()) {
//                System.out.println("AiHelp初始化失败：参数信息不全");
//                return;
//            }
//            AIHelpSupport.init(activity,
//                    parameters.getAihelpAppkey(),
//                    parameters.getAihelpDomain(),
//                    parameters.getAihelpAppID(),
//                    SDKGameUtils.getAIHelpLanguageAlia(parameters.getLanguage()));
//            AIHelpSupport.setOnAIHelpInitializedCallback(new OnAIHelpInitializedCallback() {
//                @Override
//                public void onAIHelpInitialized() {
//                    Log.e("AIHelp", "AiHelp初始化成功");
//                }
//            });
//            Log.e("Aihelp","AIhelp-v"+AIHelpSupport.getSDKVersion());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     *  获取未读消息数量
     * @param callback
     */
//    public static void fetchUnreadMessage(OnMessageCountArrivedCallback callback) {
//        if (callback == null) return;
//        AIHelpSupport.startUnreadMessageCountPolling(callback);
//    }

    /**
     * 直接进入客服聊天界面
     *
     * @param playerName
     * @param serverId
     * @param userTags
     * @param customData
     */
    public static void customerSupport(String playerName, String serverId, String userTags,JSONObject customData,boolean showRobot) {

//        try {
//            String nutsId = "";
////            if (SDKManager.getInstance().isLogin()){
//                if (SDKManager.getInstance().getUser() != null) {
//                    nutsId = SDKManager.getInstance().getUser().getUserId();
//                }
////            }
//
//            ConversationConfig.Builder conversationBuilder = new ConversationConfig.Builder();
//            conversationBuilder.setAlwaysShowHumanSupportButtonInBotPage(true);
//            if (showRobot){
//                conversationBuilder.setConversationIntent(ConversationIntent.BOT_SUPPORT);
//            }else {
//                conversationBuilder.setConversationIntent(ConversationIntent.HUMAN_SUPPORT);
//            }
//            AIHelpSupport.showConversation(conversationBuilder.build());
//            //设置用户信息
//            net.aihelp.config.UserConfig userConfig = new net.aihelp.config.UserConfig.Builder()
//                    .setServerId(serverId)
//                    .setUserName(playerName)
//                    .setUserId(nutsId)
//                    .setUserTags(userTags)
//                    .setCustomData(customData.toString())
//                    .build();
//            AIHelpSupport.updateUserInfo(userConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * FAQ
     * @param userName
     * @param serverId
     * @param userTags
     * @param customData
     * @param showRobot
     */
    public static void showFAQs(String userName, String serverId, String userTags, JSONObject customData, boolean showRobot) {
//        try {
//            String nutsId = "";
////            if (SDKManager.getInstance().isLogin()){
//                if (SDKManager.getInstance().getUser() != null) {
//                    nutsId = SDKManager.getInstance().getUser().getUserId();
//                }
////            }
//
//            FaqConfig.Builder faqBuilder = new FaqConfig.Builder();
//            ConversationConfig.Builder conversationBuilder = new ConversationConfig.Builder();
//            conversationBuilder.setAlwaysShowHumanSupportButtonInBotPage(showRobot);
//            if (showRobot){
//                conversationBuilder.setConversationIntent(ConversationIntent.BOT_SUPPORT);
//            }else {
//                conversationBuilder.setConversationIntent(ConversationIntent.HUMAN_SUPPORT);
//            }
//            faqBuilder.setShowConversationMoment(ShowConversationMoment.ALWAYS);
//            faqBuilder.setConversationConfig(conversationBuilder.build());
//            AIHelpSupport.showAllFAQSections(faqBuilder.build());
//            //设置用户信息
//            net.aihelp.config.UserConfig userConfig = new net.aihelp.config.UserConfig.Builder()
//                    .setServerId(serverId)
//                    .setUserName(userName)
//                    .setUserId(nutsId)
//                    .setUserTags(userTags)
//                    .setCustomData(customData.toString())
//                    .build();
//            AIHelpSupport.updateUserInfo(userConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
