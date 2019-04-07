/**
 * --- GLS CORE DESCRIPTION --------------------------------------------------------------------------------------------
 *
 * This package contains all the classes from the core of the Gunfire Locator System (GLS).
 *
 * The main class of this module is the {@link com.hugovs.gls.core.AudioServer}.
 * The {@link com.hugovs.gls.core.AudioServer} is the one that creates the server and hold its extensions.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * --- AUDIO INPUT -----------------------------------------------------------------------------------------------------
 *
 * The {@link com.hugovs.gls.core.AudioInput} is responsible to provide the samples to the
 * {@link com.hugovs.gls.core.AudioReader} to be processed to the server and it's extensions.
 *
 * Without a {@link com.hugovs.gls.core.AudioInput} defined, the server does nothing.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * ---- AUDIO SERVER EXTENSION -----------------------------------------------------------------------------------------
 *
 * The server are designed to be complemented with {@link com.hugovs.gls.core.AudioServerExtension}.
 * Any extra functionality is created by creating a new extension.
 *
 * All the extensions runs in sequence.
 * If your extension is heavy on resources, it's recommended that you create a new thread to process the data.
 *
 * On your extension you can implement {@link com.hugovs.gls.core.AudioListener} to perform an action every time a
 * new set of samples arrives. You can also implement {@link com.hugovs.gls.core.AudioFilter} to filter the sound,
 * i. e. remove noise.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 */
package com.hugovs.gls.core;