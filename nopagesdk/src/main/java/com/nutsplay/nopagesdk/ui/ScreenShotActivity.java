package com.nutsplay.nopagesdk.ui;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.nutsplay.nopagesdk.utils.SDKResUtils;

public class ScreenShotActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(SDKResUtils.getResId(this,"activity_screen_shot","layout"));


        requestScreenShot();
    }

    /**
     *
     * 发起屏幕截图
     *
     */
    private void requestScreenShot() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final MediaProjectionManager projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            if (projectionManager != null) {

                Intent intent = projectionManager.createScreenCaptureIntent();
                startActivityForResult(intent, 0x20);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        handleScreenShotIntent(resultCode,data);

    }

}
