package ene.interfaces;

import ene.interfaces.Composition;
import ene.interfaces.View;
import java.util.List;

/**
 * Base view interface.
 */
public interface BaseView <CoreComponentType> extends Composition <CoreComponentType> {
    /**
     * Adds view instance.
     * @param view View instance.
     */
    public abstract void addView(View view);

    /**
     * Sets all contained view instances.
     * @param views List of view instaces.
     */
    public abstract void setAllViews(List<View> views);

    /**
     * Returns all contained view instances.
     * @return List of view instaces.
     */
    public abstract List<View> getAllViews();

    /**
     * Compose all core components of all views.
     */
    public void compose();

    /**
     * Render base view.
     */
    public abstract void render();
}
