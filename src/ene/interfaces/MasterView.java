package ene.interfaces;

import ene.interfaces.View;
import java.util.List;

/**
 * Master view.
 * @since 0.3.0
 * @version 2.0.0
 */
public interface MasterView <CoreComponentType> extends View <CoreComponentType> {
    /**
     * Adds view instance.
     * @param view View instance.
     */
    void addView(View view);

    /**
     * Sets all contained view instances.
     * @param views List of view instaces.
     */
    void setAllViews(List<View> views);

    /**
     * Returns all contained view instances.
     * @return List of view instaces.
     */
    List<View> getAllViews();
}
