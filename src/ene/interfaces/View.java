package ene.interfaces;

import ene.interfaces.Composition;
import ene.interfaces.Model;
import ene.interfaces.Controller;

/**
 * View interface.
 */
public interface View <CoreComponentType, ModelType, ControllerType> extends Composition <CoreComponentType> {
    /**
     * Sets the layout position.
     * @param layoutPosition Layout position.
     */
    public abstract void setLayoutPosition(String layoutPosition);

    /**
     * Returns the layout position.
     * @return Layout position.
     */
    public abstract String getLayoutPosition();

    /**
     * Sets the model instance.
     * @param model Model instance.
     */
    public abstract void setModel(Model model);

    /**
     * Returns the model instance.
     * @return Model instance.
     */
    public abstract ModelType getModel();

    /**
     * Sets the controller instance.
     * @param controller Controller instance.
     */
    public abstract void setController(Controller controller);

    /**
     * Returns the controller instance.
     * @return Controller instance.
     */
    public abstract ControllerType getController();

    /**
     * Request update.
     */
    public abstract void update();
}
