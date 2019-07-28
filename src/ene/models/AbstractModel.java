package ene.models;

import ene.AbstractObject;
import ene.interfaces.Model;
import ene.interfaces.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract model class.
 */
abstract class AbstractModel extends AbstractObject implements Model {
    /**
     * Related views.
     */
    protected List<View> views = new ArrayList<>();

    @Override
    public void addView(View view) {
        this.views.add(view);
    }

    /**
     * Update event.
     */
    protected void changed() {
        this.views.forEach(View::update);
    }
}
