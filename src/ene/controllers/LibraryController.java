package ene.controllers;

import ene.models.TrackModel;
import ene.models.LibraryModel;
import ene.databases.SQLDatabase;
import java.util.Properties;
import ene.controllers.AbstractController;
import ene.interfaces.DatabaseConnection;
import ene.interfaces.Model;
import java.io.File;
import java.util.Map;

/**
 * Library controller.
 * @version 3.2.0
 */
public class LibraryController extends AbstractController <LibraryModel> {
    /**
     * Database connection timeout in seconds.
     */
    protected static final int DATABASE_TIMEOUT = 30;

    /**
     * Database table name.
     */
    protected static final String DATABASE_TABLE = "tracks";

    /**
     * Database file path.
     */
    protected String databaseFilename;

    /**
     * Sets the database file path.
     * @param filename Database file path.
     * @since 0.16.0
     */
    protected void setDatabaseFilename(String filename) {
        this.databaseFilename = filename;
    }

    /**
     * Returns the database file path.
     * @return Database file path.
     * @since 0.16.0
     */
    protected String getDatabaseFilename() {
        return this.databaseFilename;
    }

    /**
     * Contructor.
     * @param model Library model instance.
     * @version 1.0.0
     */
    public LibraryController(Model model) {
        this.setModel(model);
    }

    /**
     * Contructor.
     * @param model Library model instance.
     * @param databaseFilename Database file path.
     * @since 0.16.0
     * @version 1.0.0
     */
    public LibraryController(Model model, String databaseFilename) {
        this.setModel(model);
        this.setDatabaseFilename(databaseFilename);
        this.loadFromFile(databaseFilename);
    }

    /**
     * Loads the model from a file.
     * @param filename File path.
     * @return Returns TRUE if successful. Otherwise FALSE.
     * @since 0.16.0
     * @version 1.0.0
     */
    public boolean loadFromFile(String filename) {
        if (!filename.isEmpty()) {
            if (new File(filename).exists()) {
                try {
                    DatabaseConnection databaseConnection = new SQLDatabase(
                        "jdbc:sqlite:" + filename,
                        DATABASE_TIMEOUT,
                        new Properties() {
                            private static final long serialVersionUID = 1L;
                            {
                                // SQLITE_OPEN_READONLY mode (see: https://www.sqlite.org/c3ref/c_open_autoproxy.html)
                                setProperty("open_mode", "1");
                            }
                        }
                    );
                    databaseConnection.query("select * from " + DATABASE_TABLE).forEach(row -> {
                        this.getModel().add(new TrackModel(
                            row.get("filename"),
                            row.get("artist"),
                            row.get("title"),
                            row.get("genre")
                        ));
                    });
                    return true;
                } catch (Exception exception) {
                    debugInfoAbout(exception);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Save the model to a file.
     * @param filename File path.
     * @return Returns TRUE if successful. Otherwise FALSE.
     * @since 0.16.0
     * @version 1.0.0
     */
    public boolean saveToFile(String filename) {
        if (!filename.isEmpty()) {
            try {
                DatabaseConnection databaseConnection = new SQLDatabase(
                    "jdbc:sqlite:" + filename,
                    DATABASE_TIMEOUT
                );
                databaseConnection.execute("DROP TABLE IF EXISTS " + DATABASE_TABLE);
                databaseConnection.execute("CREATE TABLE " + DATABASE_TABLE + " (filename text primary key not null, artist text, title text not null, genre text)");
                for (Map.Entry<String, TrackModel> entry : this.getModel().getAll().entrySet()) {
                    TrackModel track = entry.getValue();
                    databaseConnection.execute("INSERT INTO tracks VALUES ('" + track.getFilename() + "', '" + track.getArtist() + "', '" + track.getTitle() + "', '" + track.getGenre() + "')");
                }
                return true;
            } catch (Exception exception) {
                debugInfoAbout(exception);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Adds a file to the library.
     * @param filename File path.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 0.12.0
     * @version 1.1.0
     */
    public boolean add(String filename) {
        File file = new File(filename);
        String databaseFilename = this.getDatabaseFilename();
        boolean successful = false;
        if (file.exists()) {
            if (file.isDirectory()) {
                successful = this.addDirectoryContent(file);
            } else {
                successful = this.addFile(file);
            }
        }
        if (successful && !databaseFilename.isEmpty()) successful = this.saveToFile(databaseFilename);
        return successful;
    }

    /**
     * Removes a file from the library.
     * @param filename File path.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 0.10.0
     * @version 1.1.0
     */
    public boolean remove(String filename) {
        String databaseFilename = this.getDatabaseFilename();
        boolean successful = false;
        successful = this.getModel().remove(filename);
        if (successful && !databaseFilename.isEmpty()) successful = this.saveToFile(databaseFilename);
        return successful;
    }

    /**
     * Edits a track in the library.
     * @param track Updated track model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 0.20.0
     * @version 1.0.0
     */
    public boolean edit(TrackModel track) {
        this.getModel().add(track);
        return this.saveToFile(databaseFilename);
    }

    /**
     * Adds a file to the library.
     * @param file File instance.
     * @param genre Given track genre (Optional, for internal usage only).
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    protected boolean addFile(File file, String ... genre) {
        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        if (extension.equals("wav") && file.length() > 0) {
            this.getModel().add(new TrackModel(
                file.getAbsolutePath(),
                TrackModel.detectArtist(filename),
                TrackModel.detectTitle(filename),
                ((genre.length > 0) ? genre[0] : "")
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
    protected boolean addDirectoryContent(File directory) {
        if (this.scan(directory) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Scans and adds files recursively.
     * @param path File instance of the path.
     * @param genre Given track genre (Optional, for internal usage only).
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
}
