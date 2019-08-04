package ene.views;

import ene.interfaces.View;
import ene.AbstractObject;

/**
 * Abstract view.
 * @version 1.0.0
 */
abstract public class AbstractView <CoreComponentType> extends AbstractObject implements View <CoreComponentType> {
    /**
     * Core component instance.
     */
    protected CoreComponentType coreComponent;

    /**
     * Layout position.
     */
    protected String layoutPosition;

    @Override
    public void setCoreComponent(CoreComponentType coreComponent) {
        this.coreComponent = coreComponent;
    }

    @Override
    public CoreComponentType getCoreComponent() {
        return this.coreComponent;
    }

    @Override
    public void setLayoutPosition(String layoutPosition) {
        this.layoutPosition = layoutPosition;
    }

    @Override
    public String getLayoutPosition() {
        return this.layoutPosition;
    }
}
