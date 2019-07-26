package ene.views;

import ene.interfaces.Localization;
import ene.interfaces.View;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window class.
 */
public class WindowView extends AbstractBaseView <JFrame> implements Localization {
    /**
     * Constructor.
     * @param views View instances.
     */
    public WindowView(View ... views) {
        for (int i = 0; i < views.length; i++) {
            this.addView(views[i]);
        }
        this.initialize();
        this.compose();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(getString("WINDOW_TITLE"));
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/images/icon.png")).getImage());
        this.setCoreComponent(frame);
    }

    @Override
    public void compose() {
        JPanel contentPane = new JPanel(new BorderLayout());
        for (View component : this.getAllViews()) {
            contentPane.add(
                (Component)component.getCoreComponent(), component.getLayoutPosition()
                //contentPane.add(new ContentView(model, controller), BorderLayout.CENTER);
            );
        }
        this.getCoreComponent().setContentPane(contentPane);
    }

    @Override
    public void render() {
        this.getCoreComponent().pack();
        this.getCoreComponent().setVisible(true);
    }
}
