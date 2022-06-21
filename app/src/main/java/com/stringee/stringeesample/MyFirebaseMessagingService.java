package com.stringee.stringeesample;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.stringee.call.StringeeCall;
import com.stringee.call.StringeeCall2;
import com.stringee.exception.StringeeError;
import com.stringee.listener.StatusListener;
import com.stringee.widget.StringeeListener;
import com.stringee.widget.StringeeWidget;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luannguyen on 9/5/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        StringeeWidget stringeeWidget = StringeeWidget.getInstance(this);
        if (stringeeWidget.isConnected()) {
            stringeeWidget.registerPushNotification(token, new StatusListener() {
                @Override
                public void onSuccess() {
                    Log.d("Stringee", "Refresh firebase token successfully");
                }
            });
        }
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            String pushFromStringee = remoteMessage.getData().get("stringeePushNotification");
            if (pushFromStringee != null) {
                String data = remoteMessage.getData().get("data");
                Log.d("Stringee", data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String callId = jsonObject.optString("callId", null);
                    if (callId != null) {
                        String callStatus = jsonObject.getString("callStatus");
                        if (callStatus != null) {
                            if (callStatus.equals("started")) {
                                StringeeWidget stringeeWidget = StringeeWidget.getInstance(this);
                                stringeeWidget.setListener(new StringeeListener() {
                                    @Override
                                    public void onConnectionConnected() {

                                    }

                                    @Override
                                    public void onConnectionDisconnected() {

                                    }

                                    @Override
                                    public void onConnectionError(StringeeError error) {

                                    }

                                    @Override
                                    public void onRequestNewToken() {

                                    }

                                    @Override
                                    public void onCallStateChange(StringeeCall stringeeCall, StringeeCall.SignalingState signalingState) {

                                    }

                                    @Override
                                    public void onCallStateChange2(StringeeCall2 stringeeCall, StringeeCall2.SignalingState signalingState) {

                                    }
                                });
//                                stringeeWidget.connect(MainActivity.token);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
