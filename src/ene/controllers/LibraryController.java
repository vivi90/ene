package ene.controllers;

import ene.controllers.AbstractController;
import ene.interfaces.Model;
import ene.models.LibraryModel;
import ene.models.TrackModel;
import java.util.UUID;
import java.io.File;

/**
 * Library controller.
 */
public class LibraryController extends AbstractController <LibraryModel> {
    /**
     * Contructor.
     * @param model Library model instance.
     */
    public LibraryController(Model model) {
        this.setModel(model);
    }

    /**
     * Adds a file to the library.
     * @param file File instance.
     * @param genre Genre.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean addFile(File file, String genre) {
        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        if (extension.equals("wav") && file.length() > 0) {
            String[] tags = filename.split(" - ");
            this.getModel().add(new TrackModel(
                UUID.randomUUID(), // UUID
                file.getAbsolutePath(), // File path
                tags[0], // Artist
                tags[1].substring(0, tags[1].lastIndexOf(".")), // Title
                genre // Genre
            ));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds the files of a whole directory to the library.
     * @param directory File instance of the directory.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean addDirectoryContent(File directory) {
        if (directory.exists()) {
            if (this.scan(directory) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Scans and adds files recursively.
     * @param path File instance of the path.
     * @param genre Given track genre (Optional, internal usage).
     * @return Returns the amount of added files.
     */
    protected int scan(File path, String ... genre) {
        int addedFiles = 0;
        for (File subpath : path.listFiles()) {
            if (subpath.isDirectory()) {
                addedFiles += this.scan(subpath, ((genre.length > 0) ? genre[0] : subpath.getName()));
            } else {
                if (this.addFile(subpath, ((genre.length > 0) ? genre[0] : ""))) addedFiles++;
            }
        }
        return addedFiles;
    }

    /**
     * Removes a file from the library by it's unique identifier.
     * @param uuid Unique identifier.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean remove(UUID uuid) {
        return this.getModel().remove(uuid);
    }
}
