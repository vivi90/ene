package ene.models;

import ene.models.AbstractModel;

/**
 * Track model.
 * @version 2.0.0
 */
public class TrackModel extends AbstractModel {
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
     * @param filename Filename.
     * @param artist Artist.
     * @param title Title.
     * @param genre Genre.
     * @since 0.10.0
     */
    public TrackModel(String filename, String artist, String title, String genre) {
        this.setFilename(filename);
        this.setArtist(artist);
        this.setTitle(title);
        this.setGenre(genre);
    }

    /**
     * Returns a string representation of the object.
     * @return String representation of the object.
     */
    public String toString() {
        return getClass().getName() + "{"
        + this.getFilename() + ", "
        + this.getArtist() + ", "
        + this.getTitle() + ", "
        + this.getGenre()
        + "}" + '@' + Integer.toHexString(hashCode());
    }
}
