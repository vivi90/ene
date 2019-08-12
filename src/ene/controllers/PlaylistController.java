package ene.controllers;

import ene.models.TrackModel;
import ene.controllers.AbstractController;
import ene.interfaces.Export;
import ene.interfaces.Import;
import ene.interfaces.Model;
import ene.models.PlaylistModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Playlist controller.
 * @since 1.0.0
 * @version 1.0.0
 */
public class PlaylistController extends AbstractController <PlaylistModel> implements Import, Export {
    /**
     * Playlist file path.
     */
    protected String playlistFilename;

    /**
     * Sets the playlist file path.
     * @param filename Playlist file path.
     */
    protected void setPlaylistFilename(String filename) {
        this.playlistFilename = filename;
    }

    /**
     * Returns the playlist file path.
     * @return Playlist file path.
     */
    protected String getPlaylistFilename() {
        return this.playlistFilename;
    }

    /**
     * Contructor.
     * @param model Playlist model instance.
     * @version 1.0.0
     */
    public PlaylistController(Model model) {
        this.setModel(model);
    }

    /**
     * Contructor.
     * @param model Playlist model instance.
     * @param playlistFilename Playlist file path.
     * @version 1.0.0
     */
    public PlaylistController(Model model, String playlistFilename) {
        this.setModel(model);
        this.setPlaylistFilename(playlistFilename);
        this.loadFromFile(playlistFilename);
    }

    @Override
    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            try {
                String name = "";
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.indexOf("#EXTM3U") == -1) {
                        if (line.indexOf("#EXTINF") > -1) {
                            name = line.substring(line.indexOf(",") + 1);
                        } else {
                            if (name.isEmpty()) name = line;
                            this.add(new TrackModel(
                                line,
                                TrackModel.detectArtist(name),
                                TrackModel.detectTitle(name),
                                ""
                            ));
                            name = "";
                        }
                    }
                }
                scanner.close();
                return true;
            } catch (Exception exception) {
                debugInfoAbout(exception);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean saveToFile(String filename) {
        File file = new File(filename);
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.println("#EXTM3U");
            for (Map.Entry<String, TrackModel> entry : this.getModel().getAll().entrySet()) {
                TrackModel track = entry.getValue();
                writer.println("#EXTINF:-1," + track.getArtist() + " - " + track.getTitle());
                writer.println(track.getFilename());
            }
            writer.close();
            return true;
        } catch (Exception exception) {
            debugInfoAbout(exception);
            return false;
        }
    }

    /**
     * Adds a track to the playlist.
     * @param track Track model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean add(TrackModel track) {
        this.getModel().add(track);
        return this.saveToFile(this.getPlaylistFilename());
    }

    /**
     * Removes a track from the playlist.
     * @param filename File path.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean remove(String filename) {
        boolean successful = this.getModel().remove(filename);
        if (successful) successful = this.saveToFile(this.getPlaylistFilename());
        return successful;
    }
}
