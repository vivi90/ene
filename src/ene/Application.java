package ene;

import ene.AbstractObject;
import ene.controllers.LibraryController;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.interfaces.View;
import ene.interfaces.Model;
import ene.models.LibraryModel;
import ene.models.PlayerModel;
import ene.views.gui.partial.LibraryView;
import ene.views.gui.partial.PlayerView;
import ene.views.gui.partial.ContentView;
import ene.views.gui.WindowView;

/**
 * Bootstrap class.
 * @since 0.8.0
 * @version 1.2.0
 */
public class Application extends AbstractObject {
    /**
     * Library database file name.
     */
    protected static final String LIBRARY_DATABASE_FILE = "library.db";

    /**
     * Entry point.
     *
     * @param args Arguments.
     * @version 1.0.0
     */
    public static void main(String args[]) {
        processArguments(args);
        run();
    }

    /**
     * Processes arguments.
     * @param arguments Arguments.
     * @version 1.0.0
     */
    protected static void processArguments(String arguments[]) {
        for (String argument: arguments) {
            // Help text.
            if (argument.equals("-h") || argument.equals("--help") || argument.equals("-?")) {
                consoleOutput("Usage: java -jar ene.jar [-d]");
                consoleOutput("");
                consoleOutput("Options:");
                consoleOutput("    -d                    Shows debug infos.");
                consoleOutput("    -h, --help, -?        Shows this info text and terminates the application.");
                System.exit(0);
            // Debug mode
            } else if (argument.equals("-d")) {
                enableDebugMode();
            }
        }
    }

    /**
     * Runs application.
     * @version 1.1.0
     */
    protected static void run() {
        Model playerModel = new PlayerModel();
        Model libraryModel = new LibraryModel();
        Controller playerController = new PlayerController(playerModel);
        Controller libraryController = new LibraryController(libraryModel, LIBRARY_DATABASE_FILE);
        View libraryView = new LibraryView(libraryModel, libraryController, playerController);
        View playerView = new PlayerView(playerModel, playerController);
        View windowView = new WindowView(new ContentView(libraryView), playerView);
        windowView.render();
    }
}
