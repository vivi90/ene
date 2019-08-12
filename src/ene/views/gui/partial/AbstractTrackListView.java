package ene.views.gui.partial;

import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.models.TrackListModel;
import ene.views.AbstractPartialView;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Track list view.
 * @since 0.14.0
 * @version 2.2.0
 */
abstract class AbstractTrackListView extends AbstractPartialView <JPanel, TrackListModel> implements ListSelectionListener, MouseListener {
    /**
     * File path table column.
     */
    protected static final int TABLE_COLUMN_FILENAME = 0;

    /**
     * Table instance.
     */
    protected JTable table;

    /**
     * Table row sorter instance.
     */
    protected TableRowSorter<DefaultTableModel> tableRowSorter;

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
     * Sets the table row sorter instance.
     * @param tableRowSorter Table row sorter instance.
     * @since 0.18.0
     */
    protected void setTableRowSorter(TableRowSorter<DefaultTableModel> tableRowSorter) {
        this.tableRowSorter = tableRowSorter;
    }

    /**
     * Returns the table row sorter instance.
     * @return Table row sorter instance.
     * @since 0.18.0
     */
    protected TableRowSorter<DefaultTableModel> getTableRowSorter() {
        return this.tableRowSorter;
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
     * @version 1.1.0
     */
    protected void hideColumn(JTable table, int columnIndex) {
        this.setColumnWidth(table, columnIndex, 0);
    }

    /**
     * Sets table column width.
     * @param table Table instance.
     * @param columnIndex Column index.
     * @param width Column width.
     * @since 1.0.0
     * @version 1.0.0
     */
    protected void setColumnWidth(JTable table, int columnIndex, int width) {
        table.getColumnModel().getColumn(columnIndex).setMinWidth(width);
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(width);
        table.getColumnModel().getColumn(columnIndex).setMaxWidth(width);
    }

    /**
     * Returns the selected filename.
     * @return Selected filename or empty string, if nothing is selected.
     * @since 0.12.0
     * @version 1.1.1
     */
    protected String getSelectedFilename() {
        JTable table = this.getTable();
        DefaultTableModel tableContent = this.getTableContent();
        int selectedRow;
        if (this.getTableRowSorter() == null) {
            selectedRow = table.getSelectedRow();
        } else {
            try {
                selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
            } catch (Exception exception) {
                selectedRow = -1;
            }
        }
        if (selectedRow > -1) {
            return (String) tableContent.getValueAt(selectedRow, TABLE_COLUMN_FILENAME);
        } else {
            return "";
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        debugInfoAbout((Object) event);
        if (event.getClickCount() == 2 && ((JTable) event.getSource()).getSelectedRow() != -1) {
            PlayerController playerController = this.getPlayerController();
            playerController.load(this.getModel().get(this.getSelectedFilename()));
            playerController.togglePlayback();
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            debugInfoAbout(event);
        }
    }
}
