package ene.interfaces;

/**
 * Import interface.
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Import {
    /**
     * Loads data from a file.
     * @param filename File path.
     * @return Returns TRUE if successful. Otherwise FALSE.
     */
    boolean loadFromFile(String filename);
}
