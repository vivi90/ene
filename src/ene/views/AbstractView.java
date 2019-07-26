package ene.views;

import ene.interfaces.View;
import ene.interfaces.Model;
import ene.interfaces.Controller;

/**
 * Abstract view class.
 */
abstract class AbstractView <CoreComponentType, ModelType, ControllerType> implements View <CoreComponentType, ModelType, ControllerType> {
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

    /**
     * Controller instance.
     */
    protected ControllerType controller;

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

    @SuppressWarnings("unchecked")
    @Override
    public void setController(Controller controller) {
        this.controller = (ControllerType)controller;
    }

    @Override
    public ControllerType getController() {
        return this.controller;
    }

    @Override
    public void update() {}
}
