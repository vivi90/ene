package ene.views;

import ene.interfaces.View;
import ene.AbstractObject;
import ene.interfaces.Localization;

/**
 * Abstract view.
 * @version 1.1.0
 */
abstract public class AbstractView <CoreComponentType> extends AbstractObject implements View <CoreComponentType>, Localization {
    /**
     * Title.
     */
    protected String title = "";

    /**
     * Core component instance.
     */
    protected CoreComponentType coreComponent;

    /**
     * Layout position.
     */
    protected String layoutPosition;

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public String getTitle() {
        return this.title;
    }

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
