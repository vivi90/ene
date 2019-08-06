package ene.models;

import ene.models.AbstractModel;
import java.util.Map;
import java.util.HashMap;

/**
 * Track list model.
 * @since 0.11.0
 * @version 1.0.0
 */
public class TrackList extends AbstractModel {
    /**
     * All tracks.
     */
    protected Map<String, TrackModel> tracks = new HashMap<>();

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
     * Returns all tracks.
     * @return Tracks.
     */
    public Map<String, TrackModel> getAll() {
        return this.tracks;
    }
}
