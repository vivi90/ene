package ene;

import ene.controllers.LibraryController;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.interfaces.MasterView;
import ene.interfaces.Model;
import ene.interfaces.View;
import ene.models.LibraryModel;
import ene.models.PlayerModel;
import ene.views.ContentView;
import ene.views.PlayerView;
import ene.views.WindowView;

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
        View contentView = new ContentView(libraryModel, libraryController, playerController);
        View playerView = new PlayerView(playerModel, playerController);
        MasterView windowView = new WindowView(contentView, playerView);
        windowView.render();
    }
}
