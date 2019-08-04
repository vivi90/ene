package ene.interfaces;

import ene.interfaces.Model;

/**
 * Controller.
 * @version 1.0.0
 */
public interface Controller <ModelType> {
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
}
