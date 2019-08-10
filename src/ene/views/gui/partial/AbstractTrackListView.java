package ene.views.gui.partial;

import ene.models.TrackListModel;
import ene.views.AbstractPartialView;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;

/**
 * Track list view.
 * @since 0.14.0
 * @version 2.0.0
 */
abstract class AbstractTrackListView extends AbstractPartialView <JPanel, TrackListModel> implements ListSelectionListener {
    /**
     * Table instance.
     */
    protected JTable table;

    /**
     * Table content instance.
     */
    protected DefaultTableModel tableContent = new DefaultTableModel() {
        /**
         * Object serialization version number.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Player controller instance.
     */
    protected PlayerController playerController;

    /**
     * Sets the table instance.
     * @param table Table instance.
     */
    protected void setTable(JTable table) {
        this.table = table;
    }

    /**
     * Returns the table instance.
     * @return Table instance.
     */
    protected JTable getTable() {
        return this.table;
    }

    /**
     * Sets the table content instance.
     * @param tableContent Table content instance.
     */
    protected void setTableContent(DefaultTableModel tableContent) {
        this.tableContent = tableContent;
    }

    /**
     * Returns the table content instance.
     * @return Table content instance.
     */
    protected DefaultTableModel getTableContent() {
        return this.tableContent;
    }

    /**
     * Sets the player controller instance.
     * @param playerController Player controller instance.
     */
    protected void setPlayerController(Controller playerController) {
        this.playerController = (PlayerController) playerController;
    }

    /**
     * Returns the player controller instance.
     * @return Player controller instance.
     */
    protected PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Hide table column.
     * @param table Table instance.
     * @param columnIndex Column index.
     */
    protected void hideColumn(JTable table, int columnIndex) {
        table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(0);
        table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
    }

    /**
     * Returns the selected filename.
     * @return Selected filename or empty string, if nothing is selected.
     * @since 0.12.0
     */
    protected String getSelectedFilename() {
        int selectedRow = this.getTable().getSelectedRow();
        if (selectedRow > -1) {
            return (String) tableContent.getValueAt(selectedRow, 3);
        } else {
            return "";
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            debugInfoAbout(event);
            if (((DefaultListSelectionModel) event.getSource()).getMinSelectionIndex() > -1) { // Needed to keep playing.
                this.getPlayerController().load(this.getModel().get(this.getSelectedFilename()));
            }
        }
    }
}
