package ene.models;

import ene.AbstractObject;
import ene.interfaces.Model;
import ene.interfaces.PartialView;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract model.
 * @version 2.0.0
 */
abstract class AbstractModel extends AbstractObject implements Model {
    /**
     * Related views.
     */
    protected List<PartialView> views = new ArrayList<>();

    @Override
    public void addView(PartialView view) {
        this.views.add(view);
    }

    /**
     * Update event.
     */
    protected void changed() {
        this.views.forEach(PartialView::update);
    }
}
