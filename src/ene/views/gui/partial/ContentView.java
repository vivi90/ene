package ene.views.gui.partial;

import ene.controllers.LibraryController;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.interfaces.Model;
import ene.models.LibraryModel;
import ene.models.TrackModel;
import ene.views.AbstractPartialView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Content view.
 * @version 3.0.0
 */
public class ContentView extends AbstractPartialView <JPanel, LibraryModel> implements ListSelectionListener {
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
     * Library controller instance.
     */
    protected LibraryController libraryController;

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
     * Sets the library controller instance.
     * @param libraryController Library controller instance.
     */
    protected void setLibraryController(Controller libraryController) {
        this.libraryController = (LibraryController)libraryController;
    }

    /**
     * Returns the library controller instance.
     * @return Library controller instance.
     */
    protected LibraryController getLibraryController() {
        return this.libraryController;
    }

    /**
     * Sets the player controller instance.
     * @param playerController Player controller instance.
     */
    protected void setPlayerController(Controller playerController) {
        this.playerController = (PlayerController)playerController;
    }

    /**
     * Returns the player controller instance.
     * @return Player controller instance.
     */
    protected PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Constructor.
     * @param model Library model instance.
     * @param libraryController Library controller instance.
     * @param playerController Player controller instance.
     */
    public ContentView(Model model, Controller libraryController, Controller playerController) {
        model.addView(this);
        this.setModel(model);
        this.setLibraryController(libraryController);
        this.setPlayerController(playerController);
        this.initialize();
        this.setLayoutPosition(BorderLayout.CENTER);
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        // Prepare library base panel.
        JPanel libraryPanel = new JPanel(new BorderLayout());
        this.setCoreComponent(libraryPanel);
        // Prepare library table controls.
        JPanel libraryControlPanel = new JPanel();
        libraryControlPanel.setLayout(new BoxLayout(libraryControlPanel, BoxLayout.Y_AXIS));
        libraryControlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton libraryAddButton = new JButton(getString("TABLE_BUTTON_ADD"));
        libraryAddButton.setFocusPainted(false);
        libraryAddButton.addActionListener(event -> {
            JFileChooser addDialog = new JFileChooser();
            addDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            addDialog.setFileFilter(new FileNameExtensionFilter(
                getString("TABLE_DIALOG_ADD_WAV_DESCRIPTION"),
                "wav"
            ));
            if (addDialog.showOpenDialog(libraryPanel) == JFileChooser.APPROVE_OPTION) {
                this.getLibraryController().add(addDialog.getSelectedFile().getAbsolutePath());
            }
        });
        libraryControlPanel.add(libraryAddButton);
        libraryControlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton libraryRemoveButton = new JButton(getString("TABLE_BUTTON_REMOVE"));
        libraryRemoveButton.setFocusPainted(false);
        libraryRemoveButton.addActionListener(event -> this.getLibraryController().remove(this.getSelectedFilename()));
        libraryControlPanel.add(libraryRemoveButton);
        libraryPanel.add(libraryControlPanel, BorderLayout.WEST);
        // Prepare library table content.
        DefaultTableModel tableContent = this.getTableContent();
        tableContent.setColumnIdentifiers(new String[]{getString("TABLE_COLUMN_ARTIST"), getString("TABLE_COLUMN_TITLE"), getString("TABLE_COLUMN_GENRE"), getString("TABLE_COLUMN_FILENAME")});
        // Prepare library table.
        JTable table = new JTable(tableContent);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);
        this.hideColumn(table, 3); // Hides UUID.
        this.setTable(table);
        libraryPanel.add(new JScrollPane(table), BorderLayout.CENTER);
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
    public void update() {
        DefaultTableModel tableContent = this.getTableContent();
        Map<String, TrackModel> tracks = this.getModel().getAll();
        tableContent.setRowCount(0);
        for (Map.Entry<String, TrackModel> entry : tracks.entrySet()) {
            TrackModel track = entry.getValue();
            tableContent.addRow(new String[]{track.getArtist(), track.getTitle(), track.getGenre(), track.getFilename().toString()});
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

    @Override
    public void render() {}
}
