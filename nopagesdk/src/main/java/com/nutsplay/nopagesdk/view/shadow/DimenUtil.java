package com.nutsplay.nopagesdk.view.shadow;

import android.content.res.Resources;

/**
 * Created by frankma on 2022/6/7 3:51 下午
 * Email: frankma9103@gmail.com
 * Desc:
 */
public class DimenUtil {

    public static float dp2px(float dpValue){
        return  (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}
