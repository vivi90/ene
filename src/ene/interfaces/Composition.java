package ene.interfaces;

/**
 * General composition interface.
 */
public interface Composition <CoreComponentType> {
	/**
	 * Sets the core component instance.
	 * @param coreComponent Core component instance.
	 */
	public void setCoreComponent(CoreComponentType coreComponent);

	/**
	 * Returns the core component instance.
	 * @return Core component instance.
	 */
	public CoreComponentType getCoreComponent();
}
