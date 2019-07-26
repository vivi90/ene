package ene.views;

import java.awt.BorderLayout;
import ene.interfaces.Model;
import ene.interfaces.Controller;
import ene.controllers.LibraryController;
import ene.models.LibraryModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import ene.interfaces.Localization;
import ene.views.AbstractView;

/**
 * Menu view class.
 */
public class MenuView extends AbstractView <JMenuBar, LibraryModel, LibraryController> implements Localization {
    /**
     * Constructor.
     * @param model Library model instance.
     * @param controller Library controller instance.
     */
    public MenuView(Model model, Controller controller) {
        model.addView(this);
        this.setModel(model);
        this.setController(controller);
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        JMenuBar menuBar = new JMenuBar();
        JMenu libraryMenu = new JMenu(getString("MENU_LIBRARY"));
        JMenuItem reloadMenuItem = new JMenuItem(getString("MENU_LIBRARY_RELOAD"));
        libraryMenu.add(reloadMenuItem);
        JMenuItem searchMenuItem = new JMenuItem(getString("MENU_LIBRARY_SEARCH"));
        libraryMenu.add(searchMenuItem);
        menuBar.add(libraryMenu);
        JMenu editMenu = new JMenu(getString("MENU_EDIT"));
        JMenuItem editTrackMenuItem = new JMenuItem(getString("MENU_EDIT_TRACK"));
        editMenu.add(editTrackMenuItem);
        menuBar.add(editMenu);
        this.setCoreComponent(menuBar);
        this.setLayoutPosition(BorderLayout.NORTH);
    }
}
