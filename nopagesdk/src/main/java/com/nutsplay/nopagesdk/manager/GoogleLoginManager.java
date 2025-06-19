package com.nutsplay.nopagesdk.manager;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * Created by frankma on 2025/6/9 11:13
 * Email: frankma9103@gmail.com
 * Desc: Google 登录管理类
 */
public class GoogleLoginManager {
    private static GoogleLoginManager INSTANCE;
    public static GoogleLoginManager getInstance(){
        if (INSTANCE == null){
            synchronized (GoogleLoginManager.class){
                if (INSTANCE==null){
                    INSTANCE=new GoogleLoginManager();
                }
            }
        }
        return INSTANCE;
    }

    public void login(Activity context,String webClientID,String nonceString){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);





//        GoogleLoginManagerKt googleLoginManagerKt = new GoogleLoginManagerKt();
//       GoogleLoginManagerKt.handleSignIn();
//        GoogleLoginManagerKt.INSTANCE.login(context, webClientID, nonceString, new Continuation<Unit>() {
//            @NonNull
//            @Override
//            public CoroutineContext getContext() {
//                return null;
//            }
//
//            @Override
//            public void resumeWith(@NonNull Object o) {
//
//            }
//        });
//        googleLoginManagerKt.login(context, webClientID, nonceString, new Continuation<Unit>() {
//            @NonNull
//            @Override
//            public CoroutineContext getContext() {
//                return null;
//            }
//
//            @Override
//            public void resumeWith(@NonNull Object o) {
//
//            }
//        });
    }

}
