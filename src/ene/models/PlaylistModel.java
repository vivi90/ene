package ene.models;

import ene.models.TrackListModel;

/**
 * Playlist model.
 * @since 0.14.0
 * @version 2.0.0
 */
public class PlaylistModel extends TrackListModel {
    /**
     * The current track model instance.
     */
    protected TrackModel currentTrack = null;

    /**
     * Sets the current track.
     * @param filename File path.
     */
    public void setCurrentTrack(String filename) {
        this.currentTrack = this.get(filename);
        this.changed();
    }

    /**
     * Returns the current track.
     * @return The current track model instance.
     */
    public TrackModel getCurrentTrack() {
        if (this.currentTrack == null) {
            return this.getAll().firstEntry().getValue();
        } else {
            return this.currentTrack;
        }
    }

    /**
     * Jumps to the previous track.
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
        this.changed();
    }

    /**
     * Jumps to the next track.
     * @return The next track model instance.
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
        this.changed();
    }
}
