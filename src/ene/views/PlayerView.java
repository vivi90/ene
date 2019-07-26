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
public class PlayerView extends AbstractView <JPanel, PlayerModel, PlayerController> implements Localization {
    /**
     * Play/Stop button.
     */
    protected JButton playButton;

    /**
     * Constructor.
     * @param model Player model instance.
     * @param controller Player controller instance.
     */
    public PlayerView(Model model, Controller controller) {
        model.addView(this);
        this.setModel(model);
        this.setController(controller);
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        this.setCoreComponent(new JPanel(new FlowLayout()));
        this.setLayoutPosition(BorderLayout.SOUTH);
        this.playButton = new JButton(getString("PLAY_BUTTON_START"));
        this.playButton.addActionListener(e -> this.getController().togglePlayback());
        this.getCoreComponent().add(this.playButton);
        this.setLayoutPosition(BorderLayout.SOUTH);
    }
    @Override
    public void update() {
        LineEvent lastEvent = this.getModel().getLastEvent();
        if (lastEvent.getType() == Type.START) {
            this.playButton.setText(getString("PLAY_BUTTON_STOP"));
        } else if (lastEvent.getType() == Type.STOP) {
            this.playButton.setText(getString("PLAY_BUTTON_START"));
        }
    }
}
