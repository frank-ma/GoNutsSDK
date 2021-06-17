package com.nutsplay.nopagesdk.ui;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.widget.GameRequestDialog;
import com.nutsplay.nopagesdk.kernel.SDKManager;

import org.xutils.common.util.LogUtil;

/**
 * FB游戏好友请求
 * 官方文档：https://developers.facebook.com/docs/games/services/gamerequests/?translation#invites
 *
 */
public class FBAppRequestActivity extends BaseActivity {


    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAppRequest();
    }

    private void initAppRequest() {
        callbackManager = CallbackManager.Factory.create();
        final GameRequestDialog requestDialog = new GameRequestDialog(this);
        requestDialog.registerCallback(callbackManager, new FacebookCallback<GameRequestDialog.Result>() {
            @Override
            public void onSuccess(GameRequestDialog.Result result) {
                String id = result.getRequestId();
                LogUtil.e(id);
                SDKManager.getInstance().getAppRequestCallback().onSuccess();
                finish();
            }

            @Override
            public void onCancel() {
                LogUtil.e("邀请取消");
                finish();
            }

            @Override
            public void onError(FacebookException error) {
                SDKManager.getInstance().getAppRequestCallback().onFailure(error.toString());
                finish();
            }
        });

        //发起请求
        String message = getIntent().getStringExtra("message");
        GameRequestContent content = new GameRequestContent.Builder()
//                .setTitle("Dragon Home")
                .setMessage(message).build();
        requestDialog.show(content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}