package com.stringee.widget;

import android.app.Activity;

/**
 * Created by sunil on 22/1/16.
 */
public class StringeePermissions {
    private Activity activity;

    public StringeePermissions(Activity activity) {
        this.activity = activity;
    }

    public void requestVideoCallPermission() {
        PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VIDEO_CALL, PermissionsUtils.REQUEST_VIDEO_CALL);
    }

    public void requestVoiceCallPermission() {
        PermissionsUtils.requestPermissions(activity, PermissionsUtils.PERMISSION_VOICE_CALL, PermissionsUtils.REQUEST_VOICE_CALL);
    }
}
