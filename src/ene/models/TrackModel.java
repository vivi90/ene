package ene.models;

import ene.models.AbstractModel;

/**
 * Track model.
 * @version 2.1.1
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

    /**
     * Detects the artist by the filenme.
     * @param filename The filename.
     * @return Returns the artist or a empty string.
     * @since 0.10.1
     * @version 2.0.0
     */
    public static String detectArtist(String filename) {
        filename = cutFilenameFromPath(filename);
        String[] tags = filename.split(" - ");
        if (tags.length > 1) {
            return tags[0];
        } else {
            return "";
        }
    }

    /**
     * Detects the title by the filenme.
     * @param filename The filename.
     * @return Returns the title or a empty string.
     * @since 0.10.1
     * @version 2.0.0
     */
    public static String detectTitle(String filename) {
        filename = cutFilenameFromPath(filename);
        String[] tags = filename.split(" - ");
        if (filename.indexOf(".") > -1) {
            if (tags.length > 1) {
                return tags[1].substring(0, tags[1].lastIndexOf("."));
            } else {
                return filename.substring(0, filename.lastIndexOf("."));
            }
        } else {
            if (tags.length > 1) {
                return tags[1];
            } else {
                return filename;
            }
        }
    }

    /**
     * Cuts the filenme from a given path.
     * @param path File path.
     * @return Returns the filename without it's path.
     * @since 1.0.0
     * @version 1.0.0
     */
    protected static String cutFilenameFromPath(String path) {
        String filename = path;
        if (path.indexOf('\\') > -1) {
            filename =  path.substring(path.lastIndexOf('\\') + 1);
        }
        if (filename.indexOf('/') > -1) {
            filename = path.substring(path.lastIndexOf('/') + 1);
        }
        return filename;
    }
}
