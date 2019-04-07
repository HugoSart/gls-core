package com.hugovs.gls.core;

import java.io.Serializable;

/**
 * Class that describes the audio properties of the audio to be processed.
 *
 * @author Hugo Sartori
 */
public class AudioFormat implements Serializable {
    private int sampleRate = 44100;
    private int sampleSize = 16;
    private int channels = 1;
    private boolean signed = true;
    private boolean bigEndian = true;

    public AudioFormat() {
        // empty
    }

    public AudioFormat(int sampleRate, int sampleSize, int channels, boolean signed, boolean bigEndian) {
        this.sampleRate = sampleRate;
        this.sampleSize = sampleSize;
        this.channels = channels;
        this.signed = signed;
        this.bigEndian = bigEndian;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }
}
