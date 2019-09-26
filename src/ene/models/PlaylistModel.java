package ene.models;

import ene.models.TrackListModel;
import java.util.Date;

/**
 * Playlist model.
 * @since 0.14.0
 * @version 2.1.0
 */
public class PlaylistModel extends TrackListModel {
    /**
     * The current track model instance.
     */
    protected TrackModel currentTrack = null;

    /**
     * Date and time of the last access.
     */
    protected Date lastAccess;

    /**
     * Update date and time of the last access.
     * @version 1.0.0
     * @since 1.0.4
     */
    protected void updateLastAccess() {
        this.lastAccess = new Date();
    }

    /**
     * Returns the date and time of the last access.
     * @return Date and time of the last access.
     * @version 1.0.0
     * @since 1.0.4
     */
    public Date getLastAccess() {
        return this.lastAccess;
    }

    /**
     * Sets the current track.
     * @param filename File path.
     * @version 1.1.0
     */
    public void setCurrentTrack(String filename) {
        this.currentTrack = this.get(filename);
        this.updateLastAccess();
        this.changed();
    }

    /**
     * Returns the current track.
     * @return The current track model instance.
     * @version 1.1.0
     */
    public TrackModel getCurrentTrack() {
        this.updateLastAccess();
        if (this.currentTrack == null) {
            return this.getAll().firstEntry().getValue();
        } else {
            return this.currentTrack;
        }
    }

    /**
     * Jumps to the previous track.
     * @version 1.0.1
     */
    public void previousTrack() {
        String currentFilename = this.getCurrentTrack().getFilename();
        String previousFilename;
        if (this.getAll().lowerKey(currentFilename) == null) {
            previousFilename = this.getAll().lastEntry().getKey();
        } else {
            previousFilename = this.getAll().lowerKey(currentFilename);
        }
        this.setCurrentTrack(previousFilename);
    }

    /**
     * Jumps to the next track.
     * @version 1.0.1
     */
    public void nextTrack() {
        String currentFilename = this.getCurrentTrack().getFilename();
        String nextFilename;
        if (this.getAll().higherKey(currentFilename) == null) {
            nextFilename = this.getAll().firstEntry().getKey();
        } else {
            nextFilename = this.getAll().higherKey(currentFilename);
        }
        this.setCurrentTrack(nextFilename);
    }
}
