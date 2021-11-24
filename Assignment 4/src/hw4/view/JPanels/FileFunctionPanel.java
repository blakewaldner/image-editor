package hw4.view.JPanels;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import hw4.controller.Features;

public class FileFunctionPanel extends JPanel{

  private JButton fileOpenButton;
  private JButton fileSaveButton;

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