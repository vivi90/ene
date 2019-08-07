package ene.views.gui.partial;

import ene.views.AbstractMasterView;
import ene.interfaces.View;
import javax.swing.JTabbedPane;
import java.awt.Component;

/**
 * Content view.
 * @version 4.0.0
 * @since 0.13.0
 */
public class ContentView extends AbstractMasterView <JTabbedPane> {
    /**
     * Constructor.
     * @param views View instances.
     */
    public ContentView(View ... views) {
        for (int i = 0; i < views.length; i++) {
            this.addView(views[i]);
        }
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        this.setCoreComponent(new JTabbedPane());
    }

    @Override
    public void render() {
        JTabbedPane contentPane = this.getCoreComponent();
        for (View partialView : this.getAllViews()) {
            partialView.render();
            contentPane.add(
                partialView.getTitle(),
                (Component) partialView.getCoreComponent()
            );
        }
    }
}
