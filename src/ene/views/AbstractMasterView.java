package ene.views;

import ene.AbstractObject;
import ene.interfaces.MasterView;
import ene.interfaces.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract master view class.
 * @since 0.3.0
 */
abstract class AbstractMasterView <CoreComponentType> extends AbstractObject implements MasterView <CoreComponentType> {
    /**
     * Core component instance.
     */
    protected CoreComponentType coreComponent;

    /**
     * Contained view instances.
     */
    protected List<View> views = new ArrayList<>();

    @Override
    public void setCoreComponent(CoreComponentType coreComponent) {
        this.coreComponent = coreComponent;
    }

    @Override
    public CoreComponentType getCoreComponent() {
        return this.coreComponent;
    }

    @Override
    public void addView(View view) {
        this.views.add(view);
    }

    @Override
    public void setAllViews(List<View> views) {
        this.views = views;
    }

    @Override
    public List<View> getAllViews() {
        return this.views;
    }

    @Override
    public void compose() {}

    @Override
    public void render() {}
}
