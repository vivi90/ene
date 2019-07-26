package ene.views;

import ene.interfaces.Model;
import ene.interfaces.Controller;
import ene.controllers.LibraryController;
import ene.interfaces.Localization;
import ene.models.LibraryModel;
import ene.models.TrackModel;
import java.util.Map;
import java.util.UUID;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

/**
 * Content view class.
 */
public class ContentView extends AbstractView <JScrollPane, LibraryModel, LibraryController> implements Localization {
    /**
     * Table content.
     */
    protected DefaultTableModel tableContent = new DefaultTableModel() {
        /**
         * Object serialization version number.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Initializing.
         */
        {
            setColumnIdentifiers(new String[]{getString("TABLE_COLUMN_ARTIST"), getString("TABLE_COLUMN_TITLE"), getString("TABLE_COLUMN_GENRE")});
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Get the table content.
     * @return Table content.
     */
    protected DefaultTableModel getTableContent() {
        return this.tableContent;
    }

    /**
     * Constructor.
     * @param model Library model instance.
     * @param controller Library controller instance.
     */
    public ContentView(Model model, Controller controller) {
        model.addView(this);
        this.setModel(model);
        this.setController(controller);
        this.initialize();
        this.getController().load();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        this.setCoreComponent(new JScrollPane());
        JTable table = new JTable(this.getTableContent());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getCoreComponent().setViewportView(table);
        this.setLayoutPosition(BorderLayout.CENTER);
    }

    @Override
    public void update() {
        DefaultTableModel tableContent = this.getTableContent();
        Map<UUID, TrackModel> tracks = this.getModel().getAll();
        tableContent.setRowCount(0);
        for (Map.Entry<UUID, TrackModel> entry : tracks.entrySet()) {
            TrackModel track = entry.getValue();
            tableContent.addRow(new String[]{track.getArtist(), track.getTitle(), track.getGenre()});
        }
    }
}
