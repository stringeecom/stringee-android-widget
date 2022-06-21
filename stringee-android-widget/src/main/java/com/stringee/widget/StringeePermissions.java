package com.stringee.widget;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

/**
 * Created by sunil on 22/1/16.
 */
public class StringeePermissions {
    private Activity activity;

    public StringeePermissions(Activity activity) {
        this.activity = activity;
    }

    public void requestVideoCallPermission() {
        if (VERSION.SDK_INT >= VERSION_CODES.S) {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VIDEO_CALL_ANDROID_12, PermissionsUtils.REQUEST_VIDEO_CALL);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VIDEO_CALL, PermissionsUtils.REQUEST_VIDEO_CALL);
        }
    }

    public void requestVoiceCallPermission() {
        if (VERSION.SDK_INT >= VERSION_CODES.S) {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VOICE_CALL_ANDROID_12, PermissionsUtils.REQUEST_VOICE_CALL);
        } else {
            PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VOICE_CALL, PermissionsUtils.REQUEST_VOICE_CALL);
        }
    }
}
