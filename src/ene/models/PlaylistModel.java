package ene.models;

import ene.models.TrackList;

/**
 * Playlist model.
 * @since 0.11.0
 * @version 1.0.0
 */
public class PlaylistModel extends TrackList {
    /**
     * Playlist name.
     */
    String name;

    /**
     * Sets the playlist name.
     * @param name Playlist name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the playlist name.
     * @return Playlist name.
     */
    public String getName() {
        return this.name;
    }
}
