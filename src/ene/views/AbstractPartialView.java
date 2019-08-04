package ene.views;

import ene.views.AbstractView;
import ene.interfaces.Model;
import ene.interfaces.PartialView;

/**
 * Abstract partial view.
 * @since 0.11.0
 * @version 1.0.0
 */
abstract public class AbstractPartialView <CoreComponentType, ModelType> extends AbstractView <CoreComponentType> implements PartialView <CoreComponentType, ModelType> {
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
