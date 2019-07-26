package ene.interfaces;

import ene.interfaces.Model;

/**
 * Controller interface.
 */
public interface Controller <ModelType> {
    /**
     * Sets the model instance.
     * @param model Model instance.
     */
    public abstract void setModel(Model model);

    /**
     * Returns the model instance.
     * @return Model instance.
     */
    public abstract ModelType getModel();
}
