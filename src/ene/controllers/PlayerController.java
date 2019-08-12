package ene.controllers;

import ene.controllers.AbstractController;
import ene.interfaces.Model;
import ene.models.PlayerModel;
import ene.models.PlaylistModel;
import ene.models.TrackModel;
import javax.sound.sampled.LineEvent.Type;

/**
 * Player controller.
 * @version 1.1.0
 */
public class PlayerController extends AbstractController <PlayerModel>  {
    /**
     * Constructor.
     * @param model Player model instance.
     */
    public PlayerController(Model model) {
        this.setModel(model);
    }

    /**
     * Changes the current track position.
     * @param newPosition New track position in seconds.
     */
    public void changeTrackPosition(int newPosition) {
        this.getModel().setTrackPosition(newPosition);
    }

    /**
     * Load playlist.
     * @param playlist Playlist model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean loadPlaylist(PlaylistModel playlist) {
        return this.getModel().loadPlaylist(playlist);
    }

    /**
     * Load track.
     * @param track Track model instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean load(TrackModel track) {
        return this.getModel().load(track);
    }

    /**
     * Jumps to the previous track in the current playlist.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean previous() {
        return this.getModel().previous();
    }

    /**
     * Jumps to the next track in the current playlist.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    public boolean next() {
        return this.getModel().next();
    }

    /**
     * Toggle playback.
     */
    public void togglePlayback() {
        if (this.getModel().getLastEvent() != null) {
            Type lineEventType = this.getModel().getLastEvent().getType();
            if (lineEventType != Type.CLOSE) {
                if (lineEventType == Type.START) {
                    this.getModel().pause();
                } else {
                    this.getModel().start();
                }
            }
        }
    }
}
