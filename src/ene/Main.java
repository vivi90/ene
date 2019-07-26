package ene;

import ene.interfaces.Model;
import ene.interfaces.Controller;
import ene.models.PlayerModel;
import ene.interfaces.View;
import ene.interfaces.BaseView;
import ene.models.LibraryModel;
import ene.views.MenuView;
import ene.views.ContentView;
import ene.views.PlayerView;
import ene.views.WindowView;
import ene.controllers.PlayerController;
import ene.controllers.LibraryController;

/**
 * Bootstrap.
 */
public class Main {
    /**
     * Entry point.
     *
     * @param args Arguments.
     */
    public static void main(String args[]) {
        Model playerModel = new PlayerModel();
        Model libraryModel = new LibraryModel();
        Controller playerController = new PlayerController(playerModel);
        Controller libraryController = new LibraryController(libraryModel);
        View menuView = new MenuView(libraryModel, libraryController);
        View contentView = new ContentView(libraryModel, libraryController);
        View playerView = new PlayerView(playerModel, playerController);
        BaseView windowView = new WindowView(menuView, contentView, playerView);
        windowView.render();
    }
}
