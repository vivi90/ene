package ene.views.gui;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Font;
import ene.interfaces.View;
import ene.views.AbstractMasterView;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window view.
 * @version 1.3.0
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
    public WindowView(View<?> ... views) {
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
        // Set font size.
        Font font = new Font("Default", Font.PLAIN, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/960)*10);
        UIManager.getLookAndFeelDefaults().put("TabbedPane.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        UIManager.getLookAndFeelDefaults().put("Panel.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        UIManager.getLookAndFeelDefaults().put("Table.font", new FontUIResource(font));
        UIManager.getLookAndFeelDefaults().put("TableHeader.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        UIManager.getLookAndFeelDefaults().put("Label.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        UIManager.getLookAndFeelDefaults().put("Button.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        UIManager.getLookAndFeelDefaults().put("TextField.font", new FontUIResource(font));
        UIManager.getLookAndFeelDefaults().put("Slider.font", new FontUIResource(font.deriveFont(Font.BOLD)));
        SwingUtilities.updateComponentTreeUI(frame);
        // Merge partial content components.
        JPanel contentPane = new JPanel(new BorderLayout());
        for (View<?> partialView : this.getAllViews()) {
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
