package com.stringee.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by sunil on 20/1/16.
 */
public class PermissionsUtils {

    public static final int REQUEST_VIDEO_CALL = 1;
    public static final int REQUEST_VOICE_CALL = 2;

    public static String[] PERMISSION_VIDEO_CALL = {Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA};
    public static String[] PERMISSION_VIDEO_CALL_ANDROID_12 = {android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.BLUETOOTH_CONNECT};
    public static String[] PERMISSION_VOICE_CALL = {Manifest.permission.RECORD_AUDIO};
    public static String[] PERMISSION_VOICE_CALL_ANDROID_12 = {android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.BLUETOOTH_CONNECT};

    public static boolean isVideoCallPermissionGranted(Context context) {
        int resultMic = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        int resultCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            int resultBluetooth = ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT);
            return (resultMic == PackageManager.PERMISSION_GRANTED &&
                    resultCamera == PackageManager.PERMISSION_GRANTED &&
                    resultBluetooth == PackageManager.PERMISSION_GRANTED);
        } else {
            return (resultMic == PackageManager.PERMISSION_GRANTED &&
                    resultCamera == PackageManager.PERMISSION_GRANTED);
        }
    }

    public static boolean isVoiceCallPermissionGranted(Context context) {
        int resultMic = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            int resultBluetooth = ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT);
            return (resultMic == PackageManager.PERMISSION_GRANTED &&
                    resultBluetooth == PackageManager.PERMISSION_GRANTED);
        } else {
            return (resultMic == PackageManager.PERMISSION_GRANTED);
        }
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }
}
