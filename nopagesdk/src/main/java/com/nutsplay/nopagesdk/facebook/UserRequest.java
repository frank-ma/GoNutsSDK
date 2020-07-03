package com.nutsplay.nopagesdk.facebook;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;

/**
 * Created by frankma on 2020/7/1 8:03 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class UserRequest {

    private static final String ME_ENDPOINT = "/me";

    public static void makeUserRequest(GraphRequest.Callback callback) {
        Bundle params = new Bundle();
        params.putString("fields", "picture,name,id,email,permissions");

        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), ME_ENDPOINT, params, HttpMethod.GET, callback);
        request.executeAsync();
    }
}
