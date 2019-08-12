package ene.views.gui.partial;

import ene.controllers.PlaylistController;
import ene.controllers.PlayerController;
import ene.interfaces.Controller;
import ene.interfaces.Model;
import ene.models.PlaylistModel;
import ene.models.TrackModel;
import ene.views.gui.partial.AbstractTrackListView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseEvent;

/**
 * Playlist view.
 * @version 1.0.0
 * @since 1.0.0
 */
public class PlaylistView extends AbstractTrackListView {
    /**
     * Playlist controller instance.
     */
    protected PlaylistController playlistController;

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
     * @param model Playlist model instance.
     * @param playlistController Playlist controller instance.
     * @param playerController Player controller instance.
     * @version 1.1.0
     */
    public PlaylistView(Model model, Controller playlistController, Controller playerController) {
        model.addView(this);
        this.setModel(model);
        this.setTitle(getString("PANE_TITLE"));
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
            String currentTrackFilename = ((PlaylistModel) this.getModel()).getCurrentTrack().getFilename();
            tableContent.addRow(new String[]{track.getFilename(), track.getArtist(), track.getTitle(), ((track.getFilename().equals(currentTrackFilename)) ? getString("TABLE_ROW_CURRENT") : "")});
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        debugInfoAbout((Object) event);
        if (event.getClickCount() == 2 && ((JTable) event.getSource()).getSelectedRow() != -1) {
            PlayerController playerController = this.getPlayerController();
            PlaylistModel playlist = (PlaylistModel) this.getModel();
            playlist.setCurrentTrack(this.getSelectedFilename());
            playerController.loadPlaylist(playlist);
            playerController.togglePlayback();
        }
    }

    @Override
    public void update() {
        this.loadContent(this.getModel().getAll());
    }

    @Override
    public void render() {
        // Prepare base panel.
        JPanel playlistPanel = new JPanel(new BorderLayout());
        this.setCoreComponent(playlistPanel);
        // Prepare table controls.
        JPanel controlPanel = new JPanel(new FlowLayout());
        playlistPanel.add(controlPanel, BorderLayout.WEST);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton addButton = new JButton(getString("TABLE_BUTTON_ADD"));
        controlPanel.add(addButton);
        addButton.setFocusPainted(false);
        addButton.addActionListener(event -> {
            ((JTabbedPane) playlistPanel.getParent()).setSelectedIndex(0);
        });
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton removeButton = new JButton(getString("TABLE_BUTTON_REMOVE"));
        controlPanel.add(removeButton);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(event -> this.getPlaylistController().remove(this.getSelectedFilename()));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        // Prepare table content.
        DefaultTableModel tableContent = this.getTableContent();
        tableContent.setColumnIdentifiers(new String[]{getString("TABLE_COLUMN_FILENAME"), getString("TABLE_COLUMN_ARTIST"), getString("TABLE_COLUMN_TITLE"), ""});
        // Prepare table row sorter.
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(tableContent);
        this.setTableRowSorter(tableRowSorter);
        // Prepare table.
        JTable table = new JTable(tableContent);
        this.setTable(table);
        int fontSize = table.getFont().getSize();
        table.setRowHeight(fontSize);
        table.setRowSorter(tableRowSorter);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);
        table.addMouseListener(this);
        this.setColumnWidth(table, 3, fontSize);
        this.hideColumn(table, TABLE_COLUMN_FILENAME);
        playlistPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        // Ready to get data.
        this.update();
    }
}
