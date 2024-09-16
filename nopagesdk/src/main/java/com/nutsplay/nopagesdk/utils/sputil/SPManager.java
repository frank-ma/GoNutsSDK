/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nutsplay.nopagesdk.utils.sputil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.nutsplay.nopagesdk.utils.toast.SDKToast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * SharePreferences 管理
 */
public class SPManager {

    private static final String SharedPreferenceFileName = "sp_nutsplay_client";

    private SharedPreferences mPreferences;

    private SharedPreferences.Editor mEditor;

    private static SPManager mSPHelper;
    private static Context context;

    public static SPManager getInstance(Context app) {
        context = app;
        if (mSPHelper == null)
            mSPHelper = new SPManager(app);

        return mSPHelper;
    }

    private SPManager(Context context) {

        if (context == null )return;
        mPreferences = context.getSharedPreferences(SharedPreferenceFileName, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public boolean putString(String key, String value) {
        if (mEditor == null){
            mPreferences = context.getSharedPreferences(SharedPreferenceFileName, Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        }
        mEditor.putString(key, value);
        return mEditor.commit();
    }

    public String getString(String key) {
        if (mPreferences == null)return "";
        return mPreferences.getString(key, "");
    }

    public String getString(String key, String defValue) {
        if (mPreferences == null)return "";
        return mPreferences.getString(key, defValue);
    }

    public boolean removeString(String key) {
        mEditor.remove(key);
        return mEditor.commit();
    }

    public boolean putInt(String key, int value) {
        mEditor.putInt(key, value);
        return mEditor.commit();
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }
    public boolean putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        return mEditor.commit();
    }

    public float getFloat(String key) {
        return mPreferences.getFloat(key, 0f);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public boolean putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        return mEditor.commit();
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    /**
     * 存放实体类以及任意类型
     * @param key key
     * @param obj object
     */
    public  void putBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                mEditor.putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            SDKToast.getInstance().ToastShow("the obj must implement Serializble",4);
        }

    }

    public Object getBean(String key) {
        Object obj = null;
        try {
            String base64 = getString(key,"");
            if ("".equals(base64)) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
