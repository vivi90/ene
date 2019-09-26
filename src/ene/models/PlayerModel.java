package ene.models;

import ene.models.AbstractModel;
import ene.models.TrackModel;
import ene.models.PlaylistModel;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Player model.
 * @version 1.1.1
 */
public class PlayerModel extends AbstractModel implements LineListener {
    /**
     * Current track instance.
     */
    TrackModel currentTrack;

    /**
     * Playlist model instance.
     */
    PlaylistModel playlist;

    /**
     * Clip instance.
     */
    Clip clip;

    /**
     * Line event instance.
     */
    LineEvent lastEvent;

    /**
     * Sets the current track model instance.
     * @param track Track model instance.
     */
    protected void setCurrentTrack(TrackModel track) {
        this.currentTrack = track;
    }

    /**
     * Returns the current track model instance.
     * @return Track model instance.
     * @since 1.0.0
     */
    public TrackModel getCurrentTrack() {
        return this.currentTrack;
    }

    /**
     * Sets the playlist model instance.
     * @param playlist Playlist model instance.
     */
    protected void setPlaylist(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    /**
     * Returns the playlist model instance.
     * @return Playlist model instance.
     * @since 1.0.0
     */
    public PlaylistModel getPlaylist() {
        return this.playlist;
    }

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
     * @version 1.0.1
     */
    public int getTrackLength() {
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
     * @version 1.0.1
     */
    public int getTrackPosition() {
        return (int) (this.getClip().getMicrosecondPosition() / 1000000);
    }

    /**
     * Loads playlist.
     * @param playlist Playlist model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean loadPlaylist(PlaylistModel playlist) {
        this.setPlaylist(playlist);
        return this.load(playlist.getCurrentTrack());
    }

    /**
     * Load track.
     * Implementation inpired by: https://www.tutorials.de/threads/kontinuierlich-laufende-hintergrundmusik-in-java.359029
     * @param track Track model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @version 1.0.1
     */
    public boolean load(TrackModel track) {
        debugInfoAbout(track);
        this.setCurrentTrack(track);
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
            this.delay(size/1000000);
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
     * Jumps to the previous track.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean previous() {
        PlaylistModel playlist = this.getPlaylist();
        if (playlist != null) {
            playlist.previousTrack();
            return this.load(playlist.getCurrentTrack());
        } else {
            return false;
        }
    }

    /**
     * Jumps to the next track.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean next() {
        PlaylistModel playlist = this.getPlaylist();
        if (playlist != null) {
            playlist.nextTrack();
            return this.load(playlist.getCurrentTrack());
        } else {
            return false;
        }
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
