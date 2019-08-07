package ene.models;

import ene.models.TrackListModel;
import ene.models.TrackModel;
import java.util.Map;
import java.util.HashMap;

/**
 * Library model.
 * @version 4.0.0
 */
public class LibraryModel extends TrackListModel {
    /**
     * Returns all tracks by an particular artist.
     * @param artist Artist.
     * @return Tracks.
     * @since 0.10.0
     */
    public Map<String, TrackModel> getByArtist(String artist) {
        HashMap<String, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<String, TrackModel> entry : this.getAll().entrySet()) {
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
        for (Map.Entry<String, TrackModel> entry : this.getAll().entrySet()) {
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
        for (Map.Entry<String, TrackModel> entry : this.getAll().entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getGenre().equals(genre)) {
                tracks.put(track.getFilename(), track);
            }
        }
        return tracks;
    }
}
