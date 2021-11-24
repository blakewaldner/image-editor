package hw4.view.JPanels;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import hw4.controller.Features;

/**
 * This class represents the GUI JPanel that holds the buttons for
 * opening/saving an image.
 */
public class FileFunctionPanel extends JPanel {

  private JButton fileOpenButton;
  private JButton fileSaveButton;

  /**
   * This constructs a new FileFunctionPanel for the main ImageGUIFrame to hold.
   * Holds buttons for opening/saving a file.
   */
  public FileFunctionPanel() {

    setBorder(BorderFactory.createTitledBorder("File functions"));
    setLayout(new GridLayout(1, 2, 10, 10));
    setPreferredSize(new Dimension(50, 75));

    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");

    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");

    add(fileOpenButton);
    add(fileSaveButton);
    setVisible(true);
  }

  /**
   * Adds features to the buttons in the FileFunctionPanel that adds action listeners
   * to buttons for them to perform file functions on current image.
   *
   * @param features features object holding file operation functions
   */
  public void addFeatures(Features features) {
    fileOpenButton.addActionListener(evt -> features.open(this));
    fileSaveButton.addActionListener(evt -> {
      try {
        features.save(this);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error when saving");
      }
    });
  }
}