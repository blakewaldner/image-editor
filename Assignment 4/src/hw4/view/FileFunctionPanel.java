package hw4.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.ImageUtil;
import hw4.model.ImageModel;

public class FileFunctionPanel extends JPanel implements ActionListener {

  private ImageModel model;
  private JLabel imageLabel;
  private JPanel histogramPanel;

  public FileFunctionPanel(ImageModel model, JLabel imageLabel, JPanel histogramPanel) {
    this.model = model;
    this.imageLabel = imageLabel;
    this.histogramPanel = histogramPanel;
    setBorder(BorderFactory.createTitledBorder("File functions"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    add(filesavePanel);
    add(fileopenPanel);
  }

  private void saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      //fileSaveDisplay.setText(f.getAbsolutePath());
    }
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if(arg0.getActionCommand().equals("Open file")) {
      openFile();
    }
    else if(arg0.getActionCommand().equals("Save file")) {
      saveFile();
    }
  }

  private void openFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      model.save(ImageUtil.readFile(f.getAbsolutePath(), "image"));
      //TODO: change way image is displayed not through icon but image somehow
      imageLabel.setIcon(new ImageIcon(
              ImageUtil.convertToBufferedImage(model.getImageByName("image"))));
    }
    histogramPanel.repaint();
  }
}
