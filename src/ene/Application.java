package ene;

import ene.AbstractObject;
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
 * Bootstrap class.
 * @since 0.8.0
 */
public class Application extends AbstractObject {
    /**
     * Entry point.
     *
     * @param args Arguments.
     */
    public static void main(String args[]) {
        processArguments(args);
        run();
    }

    /**
     * Processes arguments.
     * @param arguments Arguments.
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
     */
    protected static void run() {
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
