package ene.interfaces;

/**
 * View.
 * @version 2.1.0
 */
public interface View <CoreComponentType> {
	/**
	 * Sets the title.
	 * @param title Title.
	 * @since 0.13.0
	 */
	void setTitle(String title);

	/**
	 * Returns the title.
	 * @return Title.
	 * @since 0.13.0
	 */
	String getTitle();

	/**
	 * Sets the core component instance.
	 * @param coreComponent Core component instance.
	 */
	void setCoreComponent(CoreComponentType coreComponent);

	/**
	 * Returns the core component instance.
	 * @return Core component instance.
	 */
	CoreComponentType getCoreComponent();

	/**
     * Sets the layout position.
     * @param layoutPosition Layout position.
     */
    void setLayoutPosition(String layoutPosition);

    /**
     * Returns the layout position.
     * @return Layout position.
     */
    String getLayoutPosition();

	/**
     * Renders the view.
     */
    void render();
}
