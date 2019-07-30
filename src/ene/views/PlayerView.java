package ene.views;

import ene.interfaces.Model;
import ene.interfaces.Controller;
import ene.controllers.PlayerController;
import ene.interfaces.Localization;
import ene.models.PlayerModel;
import ene.views.AbstractView;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.*;

import javax.sound.sampled.LineEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.sound.sampled.LineEvent.Type;

/**
 * Player view class.
 */
public class PlayerView extends AbstractView <JPanel, PlayerModel> implements Localization {
    /**
     * Player controller instance.
     */
    protected PlayerController playerController;

    /**
     * Play/Stop button.
     */
    protected JButton playButton;
    protected JButton addButton;
    protected JButton deleteButton;

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

    
    protected void AddTitle() /*{
    	JFrame parentFrame = new JFrame();
    	 
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Choose a Title");   
    	 
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    File fileToSave = fileChooser.getSelectedFile();
    	    
    	    
    	    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    	}
    }
*/    
    {
    
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select an image");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Wav Sound file", "wav");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println(jfc.getSelectedFile().getPath());
		}
    }  
    
    
    
    //File will be delete it
    
  protected void DeleteTitle() {
	  JFrame parentFrame = new JFrame();
 	 
  	JFileChooser fileChooser = new JFileChooser();
  	fileChooser.setDialogTitle("Choose a Title");   
  	 
  	int userSelection = fileChooser.showSaveDialog(parentFrame);
  	 
  	if (userSelection == JFileChooser.APPROVE_OPTION) {
  	    File fileToDelete = fileChooser.getSelectedFile();
  	    
  	    
  	    System.out.println("Save as file: " + fileToDelete.getAbsolutePath());
  	
	  
	
	        
      if(fileToDelete.delete()) 
      { 
          System.out.println("File deleted successfully"); 
      } 
      else
      { 
          System.out.println("Failed to delete the file"); 
      } 
  } }
	 
    /**
     * Constructor.
     * @param model Player model instance.
     * @param controller Player controller instance.
     */
    public PlayerView(Model model, Controller playerController) {
        model.addView(this);
        this.setModel(model);
        this.setPlayerController(playerController);
        this.initialize();
    }

    /**
     * Initializing.
     */
    protected void initialize() {
        this.setCoreComponent(new JPanel(new FlowLayout()));
        this.setLayoutPosition(BorderLayout.SOUTH);
        this.playButton = new JButton(getString("PLAY_BUTTON_START"));
        this.playButton.setFocusPainted(false);
        this.playButton.addActionListener(event -> this.getPlayerController().togglePlayback());
        this.getCoreComponent().add(this.playButton);
        
        this.addButton = new JButton(getString("ADD_BUTTON"));
        this.addButton.setFocusPainted(false);
        this.addButton.addActionListener(event -> this.AddTitle());
        this.getCoreComponent().add(this.addButton);
        
        this.deleteButton = new JButton(getString("DELETE_BUTTON"));
        this.deleteButton.setFocusPainted(false);
        this.deleteButton.addActionListener(event -> this.DeleteTitle());
        this.getCoreComponent().add(this.deleteButton);
        
        
        
        
        
        this.setLayoutPosition(BorderLayout.SOUTH);
        
        
        
        
        
    }
    @Override
    public void update() {
        LineEvent lastEvent = this.getModel().getLastEvent();
        if (lastEvent.getType() == Type.START) {
            this.playButton.setText(getString("PLAY_BUTTON_PAUSE"));
        } else if (lastEvent.getType() == Type.STOP) {
            this.playButton.setText(getString("PLAY_BUTTON_START"));
        }
    }
}
