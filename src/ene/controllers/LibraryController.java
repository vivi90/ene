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
     * Load library content.
     */
    public void load() {
        File path = new File("Music");
        if (path.exists()) {
            this.scan(path);
        } else {
            this.scan(new File("."));
        }
    }

    /**
     * Recursive track file scan.
     * @param path File instance of the path.
     * @param genre Given track genre (Optional, internal usage).
     */
    protected void scan(File path, String ... genre) {
        for (File subpath : path.listFiles()) {
            if (subpath.isDirectory()) {
                this.scan(subpath, ((genre.length > 0) ? genre[0] : subpath.getName()));
            } else {
                String filename = subpath.getName();
                if (filename.substring(filename.lastIndexOf(".") + 1).equals("wav")) {
                    this.getModel().add(new TrackModel(
                        UUID.randomUUID(),
                        subpath.getAbsolutePath(),
                        filename.split(" - ")[0],
                        filename.split(" - ")[1],
                        genre[0]
                    ));
                }
            }
        }
    }
}
