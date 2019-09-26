package ene.views.gui.partial;

import javax.swing.plaf.metal.MetalSliderUI;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.interfaces.Model;
import ene.models.PlayerModel;
import ene.views.AbstractPartialView;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.sound.sampled.LineEvent.Type;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * Player view.
 * @version 2.2.2
 */
public class PlayerView extends AbstractPartialView <JPanel, PlayerModel> {
    /**
     * Player controller instance.
     */
    protected PlayerController playerController;

    /**
     * Previous button.
     */
    protected JButton previousButton;

    /**
     * Play/Pause button.
     */
    protected JButton playButton;

    /**
     * Next button.
     */
    protected JButton nextButton;

    /**
     * Progress slider.
     */
    protected JSlider progressSlider;

    /**
     * Progress label.
     */
    protected JLabel progressLabel;

    /**
     * Progress refresh timer.
     */
    Timer progressTimer;

    /**
     * Track time format instance.
     */
    SimpleDateFormat trackTimeFormat;

    /**
     * Sets the player controller instance.
     * @param playerController Player controller instance.
     */
    protected void setPlayerController(Controller<?> playerController) {
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
     * @param playerController Player controller instance.
     * @version 1.1.0
     */
    public PlayerView(Model model, Controller<?> playerController) {
        model.addView(this);
        this.setModel(model);
        this.setPlayerController(playerController);
        this.setLayoutPosition(BorderLayout.SOUTH);
    }

    /**
     * Enables player controls.
     */
    protected void enablePlayerControls() {
        if (isCurrentTrackPartOfCurrentPlaylist(this.getModel())) {
            this.previousButton.setEnabled(true);
            this.nextButton.setEnabled(true);
        }
        this.playButton.setEnabled(true);
        this.progressSlider.setEnabled(true);
        this.progressLabel.setEnabled(true);
    }

    /**
     * Disables player controls.
     */
    protected void disablePlayerControls() {
        this.previousButton.setEnabled(false);
        this.playButton.setEnabled(false);
        this.nextButton.setEnabled(false);
        this.progressSlider.setEnabled(false);
        this.progressLabel.setEnabled(false);
    }

    /**
     * Updates the progress slider and label.
     */
    protected void updateProgress() {
        int trackLength = this.getModel().getTrackLength();
        int trackPosition = this.getModel().getTrackPosition();
        this.progressSlider.setMaximum(trackLength);
        this.progressSlider.setValue(trackPosition);
        this.progressLabel.setText(
            this.formatTimeToString(trackPosition) +
            " / " +
            this.formatTimeToString(trackLength)
        );
    }

    /**
     * Resets the track progress.
     */
    protected void resetProgress() {
        this.getPlayerController().changeTrackPosition(0);
        this.updateProgress();
    }

    /**
     * Returns a string representation of the time.
     * @param timeInSeconds Time in seconds.
     * @return String representation of the time.
     */
    protected String formatTimeToString(int timeInSeconds) {
        return this.trackTimeFormat.format(new Date(timeInSeconds*1000));
    }

    @Override
    public void update() {
        Type lastEventType = this.getModel().getLastEvent().getType();
        // Track opened.
        if (lastEventType == Type.OPEN) {
            this.resetProgress();
            this.enablePlayerControls();
        // Track closed.
        } else if (lastEventType == Type.CLOSE) {
            this.resetProgress();
            this.progressLabel.setText("--:-- / --:--");
            this.disablePlayerControls();
        // Track started.
        } else if (lastEventType == Type.START) {
            this.enablePlayerControls();
            this.progressTimer.start();
            this.playButton.setText(getString("PLAY_BUTTON_PAUSE"));
        // Track paused.
        } else if (lastEventType == Type.STOP) {
            PlayerModel playerModel = this.getModel();
            if (playerModel.getTrackPosition() == playerModel.getTrackLength()) {
                if (isCurrentTrackPartOfCurrentPlaylist(playerModel)) {
                    this.getPlayerController().next();
                    this.getPlayerController().togglePlayback();
                } else {
                    this.resetProgress();
                }
            }
            this.progressTimer.stop();
            this.playButton.setText(getString("PLAY_BUTTON_START"));
        }
    }

    /**
     * Checks, if the current track a part of the current playlist is.
     * @return Returns TRUE, if it is a part of. Otherwise FALSE.
     * @since 1.0.0
     * @version 1.0.0
     */
    protected boolean isCurrentTrackPartOfCurrentPlaylist(PlayerModel player) {
        if (player.getPlaylist() != null) {
            if (player.getPlaylist().has(player.getCurrentTrack().getFilename())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void render() {
        // Panel
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setCoreComponent(panel);
        // Control buttons.
        JPanel controlButtonPanel = new JPanel();
        panel.add(controlButtonPanel, BorderLayout.WEST);
        this.previousButton = new JButton(getString("PREVIOUS_BUTTON"));
        controlButtonPanel.add(this.previousButton);
        this.previousButton.setFocusPainted(false);
        this.previousButton.addActionListener(event -> {
            debugInfoAbout(event);
            this.getPlayerController().previous();
            this.getPlayerController().togglePlayback();
        });
        this.playButton = new JButton(getString("PLAY_BUTTON_START"));
        controlButtonPanel.add(playButton);
        this.playButton.setFocusPainted(false);
        this.playButton.addActionListener(event -> {
            debugInfoAbout(event);
            this.getPlayerController().togglePlayback();
        });
        this.nextButton = new JButton(getString("NEXT_BUTTON"));
        controlButtonPanel.add(this.nextButton);
        this.nextButton.setFocusPainted(false);
        this.nextButton.addActionListener(event -> {
            debugInfoAbout(event);
            this.getPlayerController().next();
            this.getPlayerController().togglePlayback();
        });
        // Progress slider.
        JSlider slider = new JSlider(0, 100, 0);
        this.progressSlider = slider;
        slider.setUI(new MetalSliderUI() {
            @Override
            protected void scrollDueToClickInTrack(int direction) {
                slider.setValue(this.valueForXPosition(slider.getMousePosition().x));
            }
        });
        slider.addChangeListener(event -> {
            this.getPlayerController().changeTrackPosition(slider.getValue());
        });
        panel.add(slider, BorderLayout.CENTER);
        // Progress label.
        this.progressLabel = new JLabel("--:-- / --:--");
        this.progressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.trackTimeFormat = new SimpleDateFormat("mm:ss");
        this.trackTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        panel.add(this.progressLabel, BorderLayout.EAST);
        // Progress refresh timer.
        this.progressTimer = new Timer(10, event -> {
            this.updateProgress();
        });
        // Prepare player controls.
        this.disablePlayerControls();
    }
}
