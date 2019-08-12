package ene.interfaces;

/**
 * Export interface.
 * @since 1.0.0
 * @version 1.0.0
 */
public interface Export {
    /**
     * Saves data to a file.
     * @param filename File path.
     * @return Returns TRUE if successful. Otherwise FALSE.
     */
    boolean saveToFile(String filename);
}
