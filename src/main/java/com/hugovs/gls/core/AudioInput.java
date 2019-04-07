package com.hugovs.gls.core;

import java.io.Closeable;

/**
 * Interface for the audio input.
 * The {@link AudioReader} uses this interface to read data.
 *
 * @author Hugo Sartori
 */
public interface AudioInput extends Closeable {

    /**
     * Read a single {@link AudioData} synchronously.
     * This method should block the thread until an {@link AudioData} arrives.
     *
     * @return the {@link AudioData}.
     */
    default AudioData read() { return null; }

    default void open() {}
    default void close() {}

}
