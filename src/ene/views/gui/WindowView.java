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
 * @version 1.1.0
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
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/ene/resources/images/icon.png")).getImage());
        this.setCoreComponent(frame);
        this.setTitle(getString("WINDOW_TITLE"));
    }

    @Override
    public void render() {
        JFrame masterComponent = this.getCoreComponent();
        JPanel contentPane = new JPanel(new BorderLayout());
        for (View partialView : this.getAllViews()) {
            partialView.render();
            contentPane.add(
                (Component) partialView.getCoreComponent(), partialView.getLayoutPosition()
            );
        }
        masterComponent.setContentPane(contentPane);
        masterComponent.pack();
        masterComponent.setVisible(true);
    }
}
