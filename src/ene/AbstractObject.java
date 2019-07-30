package ene;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Abstract object class.
 * @since 0.4.0
 */
abstract public class AbstractObject {
    /**
     * Debug mode state.
     */
    protected static boolean debugModeEnabled = false;

    /**
     * Enables debug mode.
     */
    public static void enableDebugMode() {
        AbstractObject.debugModeEnabled = true;
    }

    /**
     * Disables debug mode.
     */
    public static void disableDebugMode() {
        AbstractObject.debugModeEnabled = false;
    }

    /**
     * Returns debug mode state.
     * @return Returns TRUE, if the debug mode is enabled. Otherwise FALSE.
     */
    public static boolean isDebugModeEnabled() {
        return AbstractObject.debugModeEnabled;
    }

    /**
     * Debug info.
     * @param exception Exception instance.
     */
    public static void debugInfoAbout(Object object) {
        if (AbstractObject.isDebugModeEnabled()) {
            AbstractObject.consoleOutput("[" + new Timestamp(new Date().getTime()).toString() + "] " + object.toString() + "\n");
        }
    }

    /**
     * Console output.
     * @param text Text.
     */
    public static void consoleOutput(String text) {
        System.out.println(text);
    }

    /**
     * Let's wait for a while in the current thread.
     * @param milliseconds Wait time in milliseconds.
     */
    protected void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
