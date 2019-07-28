package ene.views;

import ene.interfaces.Model;
import ene.interfaces.Controller;
import ene.controllers.PlayerController;
import ene.interfaces.Localization;
import ene.models.PlayerModel;
import ene.views.AbstractView;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.sound.sampled.LineEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.sound.sampled.LineEvent.Type;

/**
 * Player view class.
 */
public class PlayerView extends AbstractView <JPanel, PlayerModel> implements Localization {
    /**
     * Player controller instance.
     */
    protected PlayerController playerController;

    /**
     * Play/Stop button.
     */
    protected JButton playButton;

    /**
     * Sets the player controller instance.
     * @param playerController Player controller instance.
     */
    protected void setPlayerController(Controller playerController) {
        this.playerController = (PlayerController)playerController;
    }

    /**
     * Returns the player controller instance.
     * @return Player controller instance.
     */
    protected PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Constructor.
     * @param model Player model instance.
     * @param controller Player controller instance.
     */
    public PlayerView(Model model, Controller playerController) {
        model.addView(this);
        this.setModel(model);
        this.setPlayerController(playerController);
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        this.setCoreComponent(new JPanel(new FlowLayout()));
        this.setLayoutPosition(BorderLayout.SOUTH);
        this.playButton = new JButton(getString("PLAY_BUTTON_START"));
        this.playButton.setFocusPainted(false);
        this.playButton.addActionListener(event -> this.getPlayerController().togglePlayback());
        this.getCoreComponent().add(this.playButton);
        this.setLayoutPosition(BorderLayout.SOUTH);
    }
    @Override
    public void update() {
        LineEvent lastEvent = this.getModel().getLastEvent();
        if (lastEvent.getType() == Type.START) {
            this.playButton.setText(getString("PLAY_BUTTON_PAUSE"));
        } else if (lastEvent.getType() == Type.STOP) {
            this.playButton.setText(getString("PLAY_BUTTON_START"));
        }
    }
}
