package ene.controllers;

import ene.AbstractObject;
import ene.interfaces.Controller;
import ene.interfaces.Model;

/**
 * Abstract controller.
 * @version 1.0.0
 */
abstract class AbstractController <ModelType> extends AbstractObject implements Controller <ModelType> {
    /**
     * Model instance.
     */
    protected ModelType model;

    @SuppressWarnings("unchecked")
    @Override
    public void setModel(Model model) {
        this.model = (ModelType)model;
    }

    @Override
    public ModelType getModel() {
        return this.model;
    }
}
