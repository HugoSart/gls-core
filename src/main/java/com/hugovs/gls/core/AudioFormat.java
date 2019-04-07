package com.hugovs.gls.core;

public class AudioFormat {
    private int sampleRate = 44100;
    private int sampleSize = 16;
    private int channels = 1;
    private boolean signed = true;
    private boolean bigEndian = true;

    public AudioFormat(int sampleRate, int sampleSize, int channels, boolean signed, boolean bigEndian) {
        this.sampleRate = sampleRate;
        this.sampleSize = sampleSize;
        this.channels = channels;
        this.signed = signed;
        this.bigEndian = bigEndian;
    }

}
