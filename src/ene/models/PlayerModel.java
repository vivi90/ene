package ene.models;

import ene.AbstractObject;
import ene.models.AbstractModel;
import ene.models.TrackModel;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Player class.
 */
public class PlayerModel extends AbstractModel implements LineListener {
    /**
     * Clip instance.
     */
    Clip clip;

    /**
     * Line event instance.
     */
    LineEvent lastEvent;

    /**
     * Track length.
     */
    int trackLength;

    /**
     * Sets the clip.
     * @param clip Clip instance.
     */
    protected void setClip(Clip clip) {
        this.clip = clip;
    }

    /**
     * Returns the clip.
     * @return Clip instance.
     */
    public Clip getClip() {
        return this.clip;
    }

    /**
     * Sets the last event.
     * @param event Line event instance.
     */
    protected void setLastEvent(LineEvent event) {
        this.lastEvent = event;
    }

    /**
     * Returns the last event.
     * @return Line event instance.
     */
    public LineEvent getLastEvent() {
        return this.lastEvent;
    }

    /**
     * Returns the track length.
     * @return Track length in seconds.
     */
    public int getTrackLength() {
        this.delay(10);
        return (int) (this.getClip().getMicrosecondLength() / 1000000);
    }

    /**
     * Sets the current track position.
     * @param position Track position in seconds.
     */
    public void setTrackPosition(int position) {
        if (this.getTrackPosition() != position) this.getClip().setMicrosecondPosition(position * 1000000);
    }

    /**
     * Returns the current track position.
     * @return Current track position in seconds.
     */
    public int getTrackPosition() {
        this.delay(10);
        return (int) (this.getClip().getMicrosecondPosition() / 1000000);
    }

    /**
     * Load track.
     * Implementation inpired by: https://www.tutorials.de/threads/kontinuierlich-laufende-hintergrundmusik-in-java.359029
     * @param track Track model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean load(TrackModel track) {
        debugInfoAbout(track);
        try {
            Clip clip = this.getClip();
            if (clip != null) if (clip.isOpen()) clip.close();
            File soundFile = new File(track.getFilename());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            int size = (int) (format.getFrameSize() * audioInputStream.getFrameLength());
            DataLine.Info info = new DataLine.Info(Clip.class, format, size);
            byte[] buffer = new byte[size];
            audioInputStream.read(buffer, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(format, buffer, 0, size);
            audioInputStream.close();
            this.setClip(clip);
        } catch (Exception exception) {
            debugInfoAbout(exception);
            return false;
        }
        return true;
    }

    /**
     * Start playing.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean start() {
        try {
            this.getClip().start();
        } catch (Exception exception) {
            debugInfoAbout(exception);
            return false;
        }
        return true;
    }

    /**
     * Pause playing.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean pause() {
        try {
            this.getClip().stop();
        } catch (Exception exception) {
            debugInfoAbout(exception);
            return false;
        }
        return true;
    }

    @Override
    public void update(LineEvent event) {
        debugInfoAbout(event);
        this.setLastEvent(event);
        this.changed();
    }
}
