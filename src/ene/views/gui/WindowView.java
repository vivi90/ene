package ene.views.gui;

import ene.interfaces.View;
import ene.views.AbstractMasterView;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window view.
 * @version 1.2.0
 */
public class WindowView extends AbstractMasterView <JFrame> {
    @Override
    public void setTitle(String title) {
        this.getCoreComponent().setTitle(title);
    }

	@Override
	public String getTitle() {
        return this.getCoreComponent().getTitle();
    }

    /**
     * Constructor.
     * @param views View instances.
     */
    public WindowView(View ... views) {
        for (int i = 0; i < views.length; i++) {
            this.addView(views[i]);
        }
    }

    @Override
    public void render() {
        // Prepare core component.
        JFrame frame = new JFrame();
        this.setCoreComponent(frame);
        this.setTitle(getString("WINDOW_TITLE"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/ene/resources/images/icon.png")).getImage());
        // Merge partial content components.
        JPanel contentPane = new JPanel(new BorderLayout());
        for (View partialView : this.getAllViews()) {
            partialView.render();
            contentPane.add(
                (Component) partialView.getCoreComponent(), partialView.getLayoutPosition()
            );
        }
        frame.setContentPane(contentPane);
        frame.pack();
        // Ready to show.
        frame.setVisible(true);
    }
}
