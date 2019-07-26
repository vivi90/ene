package ene.controllers;

import ene.interfaces.Controller;
import ene.interfaces.Model;

/**
 * Abstract controller class.
 */
abstract class AbstractController <ModelType> implements Controller <ModelType> {
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
