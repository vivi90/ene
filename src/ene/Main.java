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
 * Bootstrap.
 */
public class Main extends AbstractObject {
    /**
     * Entry point.
     *
     * @param args Arguments.
     */
    public static void main(String args[]) {
        processArguments(args);
        Model playerModel = new PlayerModel();
        Model libraryModel = new LibraryModel();
        Controller playerController = new PlayerController(playerModel);
        Controller libraryController = new LibraryController(libraryModel);
        View contentView = new ContentView(libraryModel, libraryController, playerController);
        View playerView = new PlayerView(playerModel, playerController);
        MasterView windowView = new WindowView(contentView, playerView);
        windowView.render();
    }

    /**
     * Processes arguments.
     * @param arguments Arguments.
     */
    public static void processArguments(String arguments[]) {
        for (String argument: arguments) {
            // Help text.
            if (argument.equals("-h") || argument.equals("--help") || argument.equals("-?")) {
                Main.consoleOutput("Usage: java -jar ene.jar [-d]");
                Main.consoleOutput("");
                Main.consoleOutput("Options:");
                Main.consoleOutput("    -d                    Shows debug infos.");
                Main.consoleOutput("    -h, --help, -?        Shows this info text and terminates the application.");
                System.exit(0);
            // Debug mode
            } else if (argument.equals("-d")) {
                Main.enableDebugMode();
            }
        }
    }
}
