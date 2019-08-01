package ene.interfaces;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization interface.
 */
public interface Localization {
    /**
     * Default locale.
     */
    public static final String DEFAULT_LOCALE = "en_US";

    /**
     * Get the localized string.
     * @param viewInstance View instance.
     * @param stringName String name.
     * @return Localized string.
     */
    default String getString(String stringName) {
        String viewName = this.getClass().getSimpleName();
        try {
            String locale = Locale.getDefault().toString();
            return ResourceBundle.getBundle("ene.resources.locales." + locale + "." + viewName).getString(stringName);
        } catch (Exception exception) {
            return ResourceBundle.getBundle("ene.resources.locales." + DEFAULT_LOCALE + "." + viewName).getString(stringName);
        }
    }
}
