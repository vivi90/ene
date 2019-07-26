package ene.models;

import ene.models.AbstractModel;
import java.util.UUID;

/**
 * Track model.
 */
public class TrackModel extends AbstractModel {
    /**
     * Unique identifier.
     */
    protected UUID uuid;

    /**
     * Filename.
     */
    protected String filename;

    /**
     * Artist.
     */
    protected String artist;

    /**
     * Title.
     */
    protected String title;

    /**
     * Genre.
     */
    protected String genre;

    /**
     * Get the unique identifier.
     * @return Unique identifier.
     */
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Set the unique identifier.
     * @param uuid Unique identifier.
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

	/**
	 * Get the filename.
	 * @return Filename.
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Set the filename.
	 * @param filename Filename.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
        this.changed();
	}

	/**
	 * Get the artist.
	 * @return Artist.
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * Set the artist.
	 * @param artist Artist.
	 */
	public void setArtist(String artist) {
		this.artist = artist;
        this.changed();
	}

	/**
	 * Get the title.
	 * @return Title.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the title.
	 * @param title Title.
	 */
	public void setTitle(String title) {
		this.title = title;
        this.changed();
	}

	/**
	 * Get the genre.
	 * @return Genre.
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Set the genre
	 * @param genre Genre.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
        this.changed();
	}

    /**
     * Constructor.
     * @param uuid Unique identifier.
     * @param filename Filename.
     * @param artist Artist.
     * @param title Title.
     * @param genre Genre.
     */
    public TrackModel(UUID uuid, String filename, String artist, String title, String genre) {
        this.setUUID(uuid);
        this.setFilename(filename);
        this.setArtist(artist);
        this.setTitle(title);
        this.setGenre(genre);
    }
}
