package ene.views;

import ene.interfaces.MasterView;
import ene.interfaces.View;
import ene.views.AbstractView;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract master view.
 * @since 0.3.0
 * @version 1.0.0
 */
abstract public class AbstractMasterView <CoreComponentType> extends AbstractView <CoreComponentType> implements MasterView <CoreComponentType> {
    /**
     * Contained view instances.
     */
    protected List<View<?>> views = new ArrayList<>();

    @Override
    public void addView(View<?> view) {
        this.views.add(view);
    }

    @Override
    public void setAllViews(List<View<?>> views) {
        this.views = views;
    }

    @Override
    public List<View<?>> getAllViews() {
        return this.views;
    }
}
