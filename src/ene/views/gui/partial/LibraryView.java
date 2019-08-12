package ene.views.gui.partial;

import ene.controllers.LibraryController;
import ene.controllers.PlaylistController;
import ene.interfaces.Controller;
import ene.interfaces.Model;
import ene.models.LibraryModel;
import ene.models.TrackModel;
import ene.views.gui.partial.AbstractTrackListView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Library view.
 * @version 2.6.0
 * @since 0.13.0
 */
public class LibraryView extends AbstractTrackListView {
    /**
     * Library controller instance.
     */
    protected LibraryController libraryController;

    /**
     * Playlist controller instance.
     */
    protected PlaylistController playlistController;

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
     * Sets the playlist controller instance.
     * @param playlistController Playlist controller instance.
     */
    protected void setPlaylistController(Controller playlistController) {
        this.playlistController = (PlaylistController) playlistController;
    }

    /**
     * Returns the playlist controller instance.
     * @return Playlist controller instance.
     */
    protected PlaylistController getPlaylistController() {
        return this.playlistController;
    }

    /**
     * Constructor.
     * @param model Library model instance.
     * @param libraryController Library controller instance.
     * @param playlistController Playlist controller instance.
     * @param playerController Player controller instance.
     * @version 2.0.0
     */
    public LibraryView(Model model, Controller libraryController, Controller playlistController, Controller playerController) {
        model.addView(this);
        this.setModel(model);
        this.setTitle(getString("PANE_TITLE"));
        this.setLibraryController(libraryController);
        this.setPlaylistController(playlistController);
        this.setPlayerController(playerController);
        this.setLayoutPosition(BorderLayout.CENTER);
    }

    /**
     * Loads the table content from the model.
     * @param tracks Map of Tracks.
     */
    protected void loadContent(Map<String, TrackModel> tracks) {
        DefaultTableModel tableContent = this.getTableContent();
        tableContent.setRowCount(0);
        for (Map.Entry<String, TrackModel> entry : tracks.entrySet()) {
            TrackModel track = entry.getValue();
            tableContent.addRow(new String[]{track.getFilename(), track.getArtist(), track.getTitle(), track.getGenre()});
        }
    }

    @Override
    public void update() {
        this.loadContent(this.getModel().getAll());
    }

    @Override
    public void render() {
        // Prepare base panel.
        JPanel libraryPanel = new JPanel(new BorderLayout());
        this.setCoreComponent(libraryPanel);
        // Prepare table controls.
        JPanel controlPanel = new JPanel(new FlowLayout());
        libraryPanel.add(controlPanel, BorderLayout.WEST);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton addButton = new JButton(getString("TABLE_BUTTON_ADD"));
        controlPanel.add(addButton);
        addButton.setFocusPainted(false);
        addButton.addActionListener(event -> {
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
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton editButton = new JButton(getString("TABLE_BUTTON_EDIT"));
        controlPanel.add(editButton);
        editButton.setFocusPainted(false);
        editButton.addActionListener(event -> {
            String selectedFilename = this.getSelectedFilename();
            if (!selectedFilename.isEmpty()) {
                TrackModel selectedTrack = this.getModel().get(selectedFilename);
                JTextField title = new JTextField(selectedTrack.getTitle());
                JTextField artist = new JTextField(selectedTrack.getArtist());
                JTextField genre = new JTextField(selectedTrack.getGenre());
                JOptionPane editDialog = new JOptionPane(
                    new Object[]{
                        getString("TABLE_COLUMN_TITLE"), title,
                        getString("TABLE_COLUMN_ARTIST"), artist,
                        getString("TABLE_COLUMN_GENRE"), genre
                    },
                    JOptionPane.WARNING_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION
                );
                editDialog.createDialog(this.getCoreComponent(), getString("TABLE_DIALOG_EDIT_TITLE")).setVisible(true);
                Object result = editDialog.getValue();
                if (result != null) {
                    if ((int) result == JOptionPane.OK_OPTION) {
                        this.getLibraryController().edit(new TrackModel(
                            selectedFilename,
                            artist.getText(),
                            title.getText(),
                            genre.getText()
                        ));
                    }
                }
            }
        });
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton removeButton = new JButton(getString("TABLE_BUTTON_REMOVE"));
        controlPanel.add(removeButton);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(event -> this.getLibraryController().remove(this.getSelectedFilename()));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton searchButton = new JButton(getString("TABLE_BUTTON_SEARCH"));
        controlPanel.add(searchButton);
        searchButton.addActionListener(event -> {
            JTextField title = new JTextField();
            JTextField artist = new JTextField();
            JTextField genre = new JTextField();
            JOptionPane searchDialog = new JOptionPane(
                new Object[]{
                    getString("TABLE_COLUMN_TITLE"), title,
                    getString("TABLE_COLUMN_ARTIST"), artist,
                    getString("TABLE_COLUMN_GENRE"), genre
                },
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION
            );
            searchDialog.createDialog(this.getCoreComponent(), getString("TABLE_DIALOG_SEARCH_TITLE")).setVisible(true);
            Object result = searchDialog.getValue();
            if (result != null) {
                if ((int) result == JOptionPane.OK_OPTION) {
                    this.loadContent(((LibraryModel) this.getModel()).search(
                        title.getText(),
                        artist.getText(),
                        genre.getText()
                    ));
                }
            }
        });
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton addToPlaylistButton = new JButton(getString("TABLE_BUTTON_ADD_TO_PLAYLIST"));
        controlPanel.add(addToPlaylistButton);
        addToPlaylistButton.setFocusPainted(false);
        addToPlaylistButton.addActionListener(event -> {
            String selectedFilename = this.getSelectedFilename();
            if (!selectedFilename.isEmpty()) {
                this.getPlaylistController().add(
                    this.getModel().get(selectedFilename)
                );
            }
        });
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        // Prepare table content.
        DefaultTableModel tableContent = this.getTableContent();
        tableContent.setColumnIdentifiers(new String[]{getString("TABLE_COLUMN_FILENAME"), getString("TABLE_COLUMN_ARTIST"), getString("TABLE_COLUMN_TITLE"), getString("TABLE_COLUMN_GENRE")});
        // Prepare table row sorter.
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(tableContent);
        this.setTableRowSorter(tableRowSorter);
        // Prepare table.
        JTable table = new JTable(tableContent);
        this.setTable(table);
        table.setRowHeight(table.getFont().getSize());
        table.setRowSorter(tableRowSorter);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);
        table.addMouseListener(this);
        this.hideColumn(table, TABLE_COLUMN_FILENAME);
        libraryPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        // Ready to get data.
        this.update();
    }
}
