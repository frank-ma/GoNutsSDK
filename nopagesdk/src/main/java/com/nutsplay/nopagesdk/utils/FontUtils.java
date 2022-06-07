package com.nutsplay.nopagesdk.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 自定义字体管理类
 *
 */
public class FontUtils {
    // fontUrl是自定义字体分类的名称
    private static String fontUrl = "Helvetica Bold.ttf";
    private static String fontUrl2 = "Helvetica.ttf";
    //Typeface是字体，这里我们创建一个对象
    private static Typeface tf;

    /**
     * 设置字体
     */
    public static Typeface setFont(Context context) {

        //给它设置你传入的自定义字体文件，再返回回来
        tf = Typeface.createFromAsset(context.getAssets(), fontUrl);

        return tf;
    }

    /**
     * 设置字体
     */
    public static Typeface setFontBold(Context context) {

        //给它设置你传入的自定义字体文件，再返回回来
        tf = Typeface.createFromAsset(context.getAssets(), fontUrl2);

        return tf;
    }
}
