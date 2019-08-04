package ene.interfaces;

import ene.interfaces.View;
import ene.interfaces.Model;

/**
 * Partial view.
 * @since 0.11.0
 * @version 1.0.0
 */
public interface PartialView <CoreComponentType, ModelType> extends View <CoreComponentType> {
    /**
     * Sets the model instance.
     * @param model Model instance.
     */
    void setModel(Model model);

    /**
     * Returns the model instance.
     * @return Model instance.
     */
    ModelType getModel();

    /**
     * Request update.
     */
    void update();
}
