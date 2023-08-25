package com.nutsplay.nonutssdk;

import android.os.Bundle;

/**
 * 客服系统 Aihelp
 *
 *
 */
public class AihelpActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        pushMsg();
    }

    private void pushMsg() {
        //必传参数。用户的推送标识，需要在游戏集成的推送平台获取。
//        AIHelpSupport.setPushTokenAndPlatform("PUSH_TOKEN", PushPlatform.FIREBASE);
    }
}