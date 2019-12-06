package com.nutsplay.nopagesdk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nutsplay.nopagesdk.manager.LoginManager;
import com.nutspower.commonlibrary.utils.LogUtils;

/**
 * Created by frankma on 2019-11-19 17:10
 * Email: frankma9103@gmail.com
 * Desc: Google登录页
 */
public class GoogleLoginActivity extends BaseActivity {


    private static final int RC_SIGN_IN = 0x22;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleGoogleLogin();
    }

    private void handleGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            String googleID = account.getId();
            LogUtils.e("GoogleId",googleID);
            LoginManager.getInstance().getGoogleLoginListener().onSuccess(googleID);
            finish();
        }else {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                // Google登录成功
                String googleID = account.getId();
                LogUtils.e("GoogleId",googleID);
                LoginManager.getInstance().getGoogleLoginListener().onSuccess(googleID);
                finish();
            }
        } catch (ApiException e) {
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("GoogleLoginActivity", "signInResult:failed code=" + e.getStatusCode());
            LoginManager.getInstance().getGoogleLoginListener().onFailure("signInResult:failed code=" + e.getStatusCode()+"---"+e.getMessage());
            finish();
        }
    }
}
