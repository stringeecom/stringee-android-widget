package com.stringee.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class RingtoneUtils {

    private static RingtoneUtils ringtoneUtils;
    private static Context mContext;

    private static Uri incomingRingtoneUri;
    private static Uri endCallPlayerUri;
    private static Uri waitingPlayerUri;

    private MediaPlayer incomingRingtone;
    private MediaPlayer endCallPlayer;
    private MediaPlayer ringingPlayer;
    private Vibrator incomingVibrator;

    private AudioManager am;
    private boolean previousSpeaker;

    public static RingtoneUtils getInstance(Context context) {
        if (ringtoneUtils == null) {
            ringtoneUtils = new RingtoneUtils();
            mContext = context;
            incomingRingtoneUri = Uri.parse(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString());
            endCallPlayerUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.stringee_call_end);
            waitingPlayerUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.stringee_call_ringing);
        }
        return ringtoneUtils;
    }

    public void playWaitingSound() {
        if (ringingPlayer == null) {
            ringingPlayer = new MediaPlayer();
            ringingPlayer.setOnPreparedListener(mediaPlayer -> ringingPlayer.start());
            ringingPlayer.setLooping(true);
            ringingPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
            try {
                ringingPlayer.setDataSource(mContext, waitingPlayerUri);
                ringingPlayer.prepareAsync();
            } catch (Exception e) {
                if (ringingPlayer != null) {
                    ringingPlayer.release();
                    ringingPlayer = null;
                }
            }
        }
    }

    public void stopWaitingSound() {
        if (ringingPlayer != null) {
            ringingPlayer.release();
            ringingPlayer = null;
        }
    }

    public void playEndCallSound() {
        if (endCallPlayer == null) {
            endCallPlayer = new MediaPlayer();
            endCallPlayer.setOnPreparedListener(mediaPlayer -> endCallPlayer.start());
            endCallPlayer.setLooping(false);
            endCallPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
            try {
                endCallPlayer.setDataSource(mContext, endCallPlayerUri);
                endCallPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (endCallPlayer != null) {
                            endCallPlayer.release();
                            endCallPlayer = null;
                        }
                    }
                });
                endCallPlayer.prepareAsync();
            } catch (Exception e) {
                if (endCallPlayer != null) {
                    endCallPlayer.release();
                    endCallPlayer = null;
                }
            }
        }
    }

    public void ringing() {
        am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        previousSpeaker = am.isSpeakerphoneOn();
        am.setSpeakerphoneOn(true);
        boolean isHeadsetPlugged = am.isWiredHeadsetOn();
        boolean needRing = am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
        boolean needVibrate = am.getRingerMode() != AudioManager.RINGER_MODE_SILENT;

        if (needRing) {
            if (incomingRingtone == null) {
                incomingRingtone = new MediaPlayer();
                incomingRingtone.setOnPreparedListener(mediaPlayer -> incomingRingtone.start());
                incomingRingtone.setLooping(true);
                if (isHeadsetPlugged) {
                    incomingRingtone.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
                } else {
                    incomingRingtone.setAudioStreamType(AudioManager.STREAM_RING);
                }
                try {
                    incomingRingtone.setDataSource(mContext, incomingRingtoneUri);
                    incomingRingtone.prepareAsync();
                } catch (Exception e) {
                    if (incomingRingtone != null) {
                        incomingRingtone.release();
                        incomingRingtone = null;
                    }
                }
            }
        }
        if (needVibrate) {
            incomingVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
            incomingVibrator.vibrate(new long[]{0, 350, 500}, 0);
        }
    }

    public void stopRinging() {
        if (incomingRingtone == null && incomingVibrator == null) {
            return;
        }
        if (am != null) {
            am.setSpeakerphoneOn(previousSpeaker);
            am = null;
        }
        if (incomingRingtone != null) {
            incomingRingtone.stop();
            incomingRingtone.release();
            incomingRingtone = null;
        }
        if (incomingVibrator != null) {
            incomingVibrator.cancel();
            incomingVibrator = null;
        }
    }
}
