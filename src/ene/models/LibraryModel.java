package ene.models;

import ene.models.AbstractModel;
import ene.models.TrackModel;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

/**
 * Library class.
 */
public class LibraryModel extends AbstractModel {
    /**
     * All tracks.
     */
    protected Map<UUID, TrackModel> tracks = new HashMap<>();

    /**
     * Add a track to the library.
     * @param track Track model instance.
     */
    public void add(TrackModel track) {
        this.tracks.put(track.getUUID(), track);
        this.changed();
    }

    /**
     * Get track by UUID.
     * @param uuid Unique identifier.
     * @return Track.
     */
    public TrackModel getByUUID(UUID uuid) {
        return this.tracks.get(uuid);
    }

    /**
     * Get all tracks.
     * @return Tracks.
     */
    public Map<UUID, TrackModel> getAll() {
        return this.tracks;
    }

    /**
     * Get tracks by artist.
     * @param artist Artist.
     * @return Tracks.
     */
    public Map<UUID, TrackModel> getByArtist(String artist) {
        HashMap<UUID, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<UUID, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getArtist().equals(artist)) {
                tracks.put(track.getUUID(), track);
            }
        }
        return tracks;
    }

    /**
     * Get tracks by title.
     * @param title Title.
     * @return Tracks.
     */
    public Map<UUID, TrackModel> getByTitle(String title) {
        HashMap<UUID, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<UUID, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getTitle().equals(title)) {
                tracks.put(track.getUUID(), track);
            }
        }
        return tracks;
    }

    /**
     * Get tracks by genre.
     * @param genre Genre.
     * @return Tracks.
     */
    public Map<UUID, TrackModel> getByGenre(String genre) {
        HashMap<UUID, TrackModel> tracks = new HashMap<>();
        for (Map.Entry<UUID, TrackModel> entry : this.tracks.entrySet()) {
            TrackModel track = entry.getValue();
            if (track.getGenre().equals(genre)) {
                tracks.put(track.getUUID(), track);
            }
        }
        return tracks;
    }
}
