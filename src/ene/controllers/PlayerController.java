package ene.controllers;

import ene.controllers.AbstractController;
import ene.interfaces.Model;
import ene.models.PlayerModel;
import ene.models.TrackModel;
import javax.sound.sampled.LineEvent.Type;

/**
 * Player controller.
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
     * Load track.
     * @param track Track instance.
     * @return Returns TRUE, if successful. Otherwise FALSE.
     */
    public boolean load(TrackModel track) {
        return this.getModel().load(track);
    }

    /**
     * Toggle playback.
     */
    public void togglePlayback() {
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
