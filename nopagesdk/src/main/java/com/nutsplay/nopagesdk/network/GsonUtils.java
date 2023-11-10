package com.nutsplay.nopagesdk.network;

import com.google.gson.Gson;
import com.nutsplay.nopagesdk.utils.toast.SDKToast;
import com.nutspower.commonlibrary.utils.StringUtils;

import java.lang.reflect.Type;

/**
 * Created by frank-ma on 2018/12/28 下午7:40
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class GsonUtils {

    public static Object json2Bean(String json, Type type) {

        if (json == null || StringUtils.isEmpty(json) || StringUtils.isBlank(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            SDKToast.getInstance().ToastShow("json格式错误，解析失败: "+e.getMessage(),3);
            e.printStackTrace();
            return null;
        }
    }

    public static String tojsonString(Object type){
        Gson gson = new Gson();
        return gson.toJson(type);
    }
}
