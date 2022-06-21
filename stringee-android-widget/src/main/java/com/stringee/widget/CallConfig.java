package com.stringee.widget;

import java.io.Serializable;

public class CallConfig implements Serializable {

    public static enum Resolution {
        NORMAL(0),
        HD(1),
        FULLHD(2);

        private final short value;

        private Resolution(final int value) {
            this.value = (short) value;
        }

        public short getValue() {
            return (short) value;
        }
    }

    private String from;
    private String to;
    private String fromAlias;
    private String toAlias;
    private String customData;
    private boolean isVideoCall;
    private Resolution resolution;
    private boolean isCallout;

    public CallConfig(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromAlias() {
        return fromAlias;
    }

    public void setFromAlias(String fromAlias) {
        this.fromAlias = fromAlias;
    }

    public String getToAlias() {
        return toAlias;
    }

    public void setToAlias(String toAlias) {
        this.toAlias = toAlias;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public boolean isVideoCall() {
        return isVideoCall;
    }

    public void setVideoCall(boolean videoCall) {
        isVideoCall = videoCall;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public boolean isCallout() {
        return isCallout;
    }

    public void setCallout(boolean callout) {
        isCallout = callout;
    }
}
