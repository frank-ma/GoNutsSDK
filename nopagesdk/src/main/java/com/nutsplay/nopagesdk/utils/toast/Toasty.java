package com.nutsplay.nopagesdk.utils.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.nutsplay.nopagesdk.kernel.SDKManager;
import com.nutsplay.nopagesdk.utils.SDKGameUtils;
import com.nutsplay.nopagesdk.utils.SDKResUtils;

import java.lang.reflect.Field;


@SuppressLint("InflateParams")
public class Toasty {
    private static final @ColorInt
    int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    private static final @ColorInt
    int ERROR_COLOR = Color.parseColor("#E64343");
    private static final @ColorInt
    int INFO_COLOR = Color.parseColor("#3F51B5");
    private static final @ColorInt
    int SUCCESS_COLOR = Color.parseColor("#36C24D");
    private static final @ColorInt
    int WARNING_COLOR = Color.parseColor("#FFA900");

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";


    public static @CheckResult
    Toast normal(@NonNull Context context, @NonNull String message) {
        return normal(context, message, Toast.LENGTH_SHORT, null, false);
    }

    public static @CheckResult
    Toast normal(@NonNull Context context, @NonNull String message, Drawable icon) {
        return normal(context, message, Toast.LENGTH_SHORT, icon, true);
    }

    public static @CheckResult
    Toast normal(@NonNull Context context, @NonNull String message, int duration) {
        return normal(context, message, duration, null, false);
    }

    public static @CheckResult
    Toast normal(@NonNull Context context, @NonNull String message, int duration,
                 Drawable icon) {
        return normal(context, message, duration, icon, true);
    }

    public static @CheckResult
    Toast normal(@NonNull Context context, @NonNull String message, int duration,
                 Drawable icon, boolean withIcon) {
        return custom(context, message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }

    public static @CheckResult
    Toast warning(@NonNull Context context, @NonNull String message) {
        return warning(context, message, Toast.LENGTH_SHORT, true);
    }

    public static @CheckResult
    Toast warning(@NonNull Context context, @NonNull String message, int duration) {
        return warning(context, message, duration, true);
    }

    public static @CheckResult
    Toast warning(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, SDKResUtils.getResId(context,"icon_info","drawable")),
                DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }

    public static @CheckResult
    Toast info(@NonNull Context context, @NonNull String message) {
        return info(context, message, Toast.LENGTH_SHORT, true);
    }

    public static @CheckResult
    Toast info(@NonNull Context context, @NonNull String message, int duration) {
        return info(context, message, duration, true);
    }

    public static @CheckResult
    Toast info(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, SDKResUtils.getResId(context,"icon_info","drawable")),
                DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }

    public static @CheckResult
    Toast success(@NonNull Context context, @NonNull String message) {
        return success(context, message, Toast.LENGTH_SHORT, true);
    }

    public static @CheckResult
    Toast success(@NonNull Context context, @NonNull String message, int duration) {
        return success(context, message, duration, true);
    }

    public static @CheckResult
    Toast success(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(true,context,message, ToastyUtils.getDrawable(context,SDKResUtils.getResId(context,"icon_info","drawable")),
                DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, false);
    }

    public static @CheckResult
    Toast error(@NonNull Context context, @NonNull String message) {
        return error(context, message, Toast.LENGTH_SHORT, true);
    }

    public static @CheckResult
    Toast error(@NonNull Context context, @NonNull String message, int duration) {
        return error(context, message, duration, true);
    }

    public static @CheckResult
    Toast error(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(false,context, message, ToastyUtils.getDrawable(context,SDKResUtils.getResId(context,"icon_info","drawable")),
                DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, false);
    }

    public static @CheckResult
    Toast custom(@NonNull Context context, @NonNull String message, Drawable icon,
                 @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }

    public static @CheckResult
    Toast custom(@NonNull Context context, @NonNull String message, @DrawableRes int iconRes,
                 @ColorInt int textColor, @ColorInt int tintColor, int duration,
                 boolean withIcon, boolean shouldTint) {
        return custom(context, message, ToastyUtils.getDrawable(context, iconRes), textColor,
                tintColor, duration, withIcon, shouldTint);
    }
    static Toast currentToast;
    public static @CheckResult
    Toast custom(@NonNull Context context, @NonNull String message, Drawable icon,
                 @ColorInt int textColor, @ColorInt int tintColor, int duration,
                 boolean withIcon, boolean shouldTint) {
//        if (currentToast==null){
            currentToast = new Toast(context);
//        }
        final View toastLayout;
        switch (SDKManager.getInstance().getUIVersion()){
            case 0:
            case 1:
                toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(SDKResUtils.getResId(context,"sdk_layout_toast_normal","layout"), null);
                break;
            default:
                toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(SDKResUtils.getResId(context,"sdk_layout_toast","layout"), null);
                break;
        }

        currentToast.setGravity(Gravity.TOP,0,0);
        final ImageView toastIcon = toastLayout.findViewById(SDKResUtils.getResId(context,"toast_icon","id"));
        final TextView toastTextView = toastLayout.findViewById(SDKResUtils.getResId(context,"toast_text","id"));
        Drawable drawableFrame;

        if (shouldTint)
            drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        else
            drawableFrame = ToastyUtils.getDrawable(context,SDKResUtils.getResId(context,"toast_frame","drawable"));
        ToastyUtils.setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon != null){
                ToastyUtils.setBackground(toastIcon, icon);
            }else {
                toastIcon.setVisibility(View.GONE);
            }
        } else {
            toastIcon.setVisibility(View.GONE);
        }
        SDKGameUtils.setTypeFaceBold(context,toastTextView);//自定义字体
        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));
        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);

//        try {
//            Object mTN;
//            mTN = getField(currentToast, "mTN");
//            if (mTN != null) {
//                Object mParams = getField(mTN, "mParams");
//                if (mParams != null
//                        && mParams instanceof WindowManager.LayoutParams) {
//                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
//                    params.windowAnimations =SDKResUtils.getResId(context,"Nuts_Style","style");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return currentToast;
    }

    /**
     * 自定义Toast
     * @param isSuccess
     * @param context
     * @param message
     * @param icon
     * @param textColor
     * @param tintColor
     * @param duration
     * @param withIcon
     * @param shouldTint
     * @return
     */
    public static @CheckResult
    Toast custom(boolean isSuccess, @NonNull Context context, @NonNull String message, Drawable icon,
                 @ColorInt int textColor, @ColorInt int tintColor, int duration,
                 boolean withIcon, boolean shouldTint) {
        currentToast = new Toast(context);
        final View toastLayout;
        int resource = 0;
        if (isSuccess) {
            resource = SDKResUtils.getResId(context, "nuts2_toast_success", "layout");
        } else {
            resource = SDKResUtils.getResId(context, "nuts2_toast_fail", "layout");
        }
        toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resource, null);

        currentToast.setGravity(Gravity.TOP, 0, 0);
        TextView toastTextView = toastLayout.findViewById(SDKResUtils.getResId(context, "toast_text", "id"));
        SDKGameUtils.setTypeFaceBold(context, toastTextView);//自定义字体
        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        return currentToast;
    }

    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

    public static void cancel(){
        if (currentToast!=null){
            currentToast.cancel();
        }
    }

}
