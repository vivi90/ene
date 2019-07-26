package ene.views;

import ene.interfaces.Composition;

/**
 * View composition class.
 */
public class ViewComposition <CoreComponentType> implements Composition <CoreComponentType> {
    /**
     * Core component instance.
     */
    protected CoreComponentType coreComponent;

	/**
	 * Sets the core component instance.
	 * @param coreComponent Core component instance.
	 */
	public void setCoreComponent(CoreComponentType coreComponent) {
		this.coreComponent = coreComponent;
	}

	/**
	 * Returns the core component instance.
	 * @return Core component instance.
	 */
	public CoreComponentType getCoreComponent() {
		return this.coreComponent;
	}
}
