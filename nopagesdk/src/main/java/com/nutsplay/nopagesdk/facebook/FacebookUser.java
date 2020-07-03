package com.nutsplay.nopagesdk.facebook;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by frankma on 2020/7/1 8:11 PM
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class FacebookUser implements Serializable {
    private final Uri picture;
    private final String name;
    private final String id;
    private final String email;
    private final String permissions;

    public FacebookUser(Uri picture, String name, String id, String email, String permissions) {
        this.picture = picture;
        this.name = name;
        this.id = id;
        this.email = email;
        this.permissions = permissions;
    }

    public FacebookUser() {
        this.picture = Uri.EMPTY;
        this.name = "";
        this.id = "";
        this.email = "";
        this.permissions = "";
    }

    public Uri getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "FacebookUser{" +
                "picture=" + picture +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
