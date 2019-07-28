package ene.views;

import ene.AbstractObject;
import ene.interfaces.Model;
import ene.interfaces.View;

/**
 * Abstract view class.
 */
abstract class AbstractView <CoreComponentType, ModelType> extends AbstractObject implements View <CoreComponentType, ModelType> {
    /**
     * Layout position.
     */
    protected String layoutPosition;

    /**
     * Core component instance.
     */
    protected CoreComponentType coreComponent;

    /**
     * Model instance.
     */
    protected ModelType model;

    @Override
    public void setLayoutPosition(String layoutPosition) {
        this.layoutPosition = layoutPosition;
    }

    @Override
    public String getLayoutPosition() {
        return this.layoutPosition;
    }

    @Override
    public void setCoreComponent(CoreComponentType coreComponent) {
        this.coreComponent = coreComponent;
    }

    @Override
    public CoreComponentType getCoreComponent() {
        return this.coreComponent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setModel(Model model) {
        this.model = (ModelType)model;
    }

    @Override
    public ModelType getModel() {
        return this.model;
    }

    @Override
    public void update() {}
}
