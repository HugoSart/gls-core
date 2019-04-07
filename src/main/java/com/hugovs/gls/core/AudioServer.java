package com.hugovs.gls.core;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Facade of the audio stream.
 *
 * @author Hugo Sartori
 */
public class AudioServer implements Closeable {

    private final Logger log = Logger.getLogger(AudioServer.class);

    // Extensions
    private final Collection<AudioServerExtension> audioServerExtensions = new ArrayList<>();

    // References
    private AudioInput input;
    private AudioReader receiver;
    private AudioFormat audioFormat;

    // Extra
    private boolean running = false;

    public AudioServer(int sampleRate, int sampleSize) {
        this.audioFormat = new AudioFormat(sampleRate, sampleSize, 1, true, false);
    }

    /**
     * Starts a {@link AudioReader}.
     */
    public void start() {

        log.info("Starting server ...");

        // Creates the AudioReader and also the listeners
        log.info("Audio input: " + input.getClass().getSimpleName());
        receiver = new AudioReader(input);

        // Load audioServerExtensions
        audioServerExtensions.forEach(audioServerExtension -> {
            if (AudioListener.class.isAssignableFrom(audioServerExtension.getClass()))
                receiver.addListener((AudioListener) audioServerExtension);
            if (AudioFilter.class.isAssignableFrom(audioServerExtension.getClass()))
                receiver.addFilter((AudioFilter) audioServerExtension);
        });
        audioServerExtensions.forEach(audioServerExtension -> {
            try {
                audioServerExtension.setAudioServer(this);
                audioServerExtension.onServerStart();
            } catch (Exception e) {
                log.error("Failed to start audioServerExtension " + audioServerExtension.getClass().getSimpleName(), e);
            }
        });

        running = true;
        receiver.startReceiving();
        while (!receiver.isReceiving()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        running = false;

        // Unload audioServerExtensions
        audioServerExtensions.forEach(audioServerExtension -> {
            try {
                audioServerExtension.onServerClose();
            } catch (Exception e) {
                log.error("Failed to close audioServerExtension " + audioServerExtension.getClass().getSimpleName(), e);
            }
        });

    }

    /**
     * Closes the {@link AudioReader}.
     */
    @Override
    public void close() {
        receiver.stopReceiving();
        log.info("AudioServer stopped.");
    }

    /**
     * Checks if the server is running.
     *
     * @return {@code true}  : if the server is running;
     *         {@code false} : if teh server is not running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Set the default {@link AudioInput} to the given one.
     *
     * @param input: the {@link AudioInput} to be used as input reader.
     */
    public void setInput(AudioInput input) {
        this.input = input;
    }

    /**
     * Add one or more server {@link AudioServerExtension}s.
     *
     * @param audioServerExtensions: one or more {@link AudioServerExtension} to be added.
     */
    public void addExtension(AudioServerExtension... audioServerExtensions) {
        this.audioServerExtensions.addAll(Arrays.asList(audioServerExtensions));
    }

    /**
     * Add a {@link Collection} of {@link AudioServerExtension}s.
     *
     * @param audioServerExtensions: a {@link Collection} of {@link AudioServerExtension} to be added.
     */
    public void addExtension(Collection<AudioServerExtension> audioServerExtensions) {
        this.audioServerExtensions.addAll(audioServerExtensions);
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }


}
