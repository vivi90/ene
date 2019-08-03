package ene.models;

import ene.models.AbstractModel;
import ene.models.TrackModel;
import java.util.Map;
import java.util.HashMap;

/**
 * Library class.
 * @version 2.0.0
 */
public class LibraryModel extends AbstractModel {
    /**
     * All tracks.
     * @since 0.10.0
     */
    protected Map<String, TrackModel> tracks = new HashMap<>();

    /**
     * Adds a track to the library.
     * @param track Track model instance.
     */
    public void add(TrackModel track) {
        this.tracks.put(track.getFilename(), track);
        this.changed();
    }

    /**
     * Removes a track from the library.
     * @param filename File path.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 0.10.0
     */
    public boolean remove(String filename) {
        if (this.tracks.remove(filename) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns track.
     * @param filename File path.
     * @return Track.
     * @since 0.10.0
     */
    public TrackModel get(String filename) {
        return this.tracks.get(filename);
    }

    /**
     * Returns all tracks.
     * @return Tracks.
     * @since 0.10.0
     */
    public Map<String, TrackModel> getAll() {
        return this.tracks;
    }

    /**
     * Returns all tracks by an particular artist.
     * @param artist Artist.
     * @return Tracks.
     * @since 0.10.0
     */
    public Map<String, TrackModel> getByArtist(String artist) {
        HashMap<String, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<String, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getArtist().equals(artist)) {
                tracks.put(track.getFilename(), track);
            }
        }
        return tracks;
    }

    /**
     * Returns all tracks with a particular title.
     * @param title Title.
     * @return Tracks.
     * @since 0.10.0
     */
    public Map<String, TrackModel> getByTitle(String title) {
        HashMap<String, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<String, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getTitle().equals(title)) {
                tracks.put(track.getFilename(), track);
            }
        }
        return tracks;
    }

    /**
     * Returns all tracks from a particular genre.
     * @param genre Genre.
     * @return Tracks.
     * @since 0.10.0
     */
    public Map<String, TrackModel> getByGenre(String genre) {
        HashMap<String, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<String, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getGenre().equals(genre)) {
                tracks.put(track.getFilename(), track);
            }
        }
        return tracks;
    }
}
