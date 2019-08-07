package ene.models;

import ene.models.TrackListModel;
import ene.models.TrackModel;
import java.util.Map;
import java.util.HashMap;

/**
 * Library model.
 * @version 4.1.0
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

    /**
     * Returns all matching tracks.
     * @param title Title.
     * @param artist Artist.
     * @param genre Genre.
     * @return Map of tracks and it's absolute file path.
     * @since 0.15.0
     * @version 1.0.0
     */
     public Map<String, TrackModel> search(String title, String artist, String genre) {
         HashMap<String, TrackModel> tracks = new HashMap<>();
         for (Map.Entry<String, TrackModel> entry : this.getAll().entrySet()) {
             TrackModel track = entry.getValue();
             if (track.getGenre().equals(genre) || genre.isEmpty()) {
                 if (track.getArtist().equals(artist) || artist.isEmpty()) {
                     if (track.getTitle().equals(title) || title.isEmpty()) {
                         tracks.put(track.getFilename(), track);
                     }
                 }
             }
         }
         return tracks;
     }
}
