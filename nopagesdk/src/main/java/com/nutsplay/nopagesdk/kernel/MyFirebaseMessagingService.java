package com.nutsplay.nopagesdk.kernel;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by frankma on 2020/10/15 5:46 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public abstract class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String TAG = "MyFirebaseMessagingService";

    /**
     * 注册令牌可能会在发生下列情况时更改：
     *
     * 应用在新设备上恢复
     * 用户卸载/重新安装应用
     * 用户清除应用数据。
     * @param s
     */
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        //卸载重装，清除应用数据和缓存会导致令牌重置，重置之后重进游戏会在这个方法里回调新的令牌
        Log.e(TAG,"新的令牌："+s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
//            NotificationUtils.showNotify(this,title,body,"0");
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
