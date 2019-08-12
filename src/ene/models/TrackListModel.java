package ene.models;

import ene.models.AbstractModel;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Track list model.
 * @since 0.11.0
 * @version 2.0.0
 */
public class TrackListModel extends AbstractModel {
    /**
     * All tracks.
     */
    protected NavigableMap<String, TrackModel> tracks = new TreeMap<>();

    /**
     * Adds a track.
     * @param track Track model instance.
     */
    public void add(TrackModel track) {
        this.tracks.put(track.getFilename(), track);
        this.changed();
    }

    /**
     * Removes a track.
     * @param filename File path.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean remove(String filename) {
        if (this.tracks.remove(filename) == null) {
            return false;
        } else {
            this.changed();
            return true;
        }
    }

    /**
     * Returns track.
     * @param filename File path.
     * @return Track.
     */
    public TrackModel get(String filename) {
        return this.tracks.get(filename);
    }

    /**
     * Checks if a track exists.
     * @param filename File path.
     * @return Returns TRUE, if exists. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean has(String filename) {
        return this.getAll().containsKey(filename);
    }

    /**
     * Returns all tracks.
     * @return Tracks.
     */
    public NavigableMap<String, TrackModel> getAll() {
        return this.tracks;
    }
}
