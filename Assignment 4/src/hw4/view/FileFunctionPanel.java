package hw4.view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.ImageUtil;
import hw4.model.ImageModel;

public class FileFunctionPanel extends JPanel{

  private ImageModel model;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private ImageGUIView view;

  public FileFunctionPanel(JLabel imageLabel, JPanel histogramPanel) {
    //this.model = model;
    this.imageLabel = imageLabel;
    this.histogramPanel = histogramPanel;
    //this.view = view;

    setBorder(BorderFactory.createTitledBorder("File functions"));
    setLayout(new GridLayout(1, 2, 10, 10));
    setPreferredSize(new Dimension(50, 75));

    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");

    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");

    add(fileOpenButton);
    add(fileSaveButton);
  }

  public void addFeatures(Features a) {
    fileOpenButton.addActionListener(evt -> a.open(this));
    fileSaveButton.addActionListener(evt -> {
      try {
        a.save(this);
      } catch (IOException e) {
        view.renderMessage("Error when saving file");
      }
    });
  }
}

//  private void saveFile() throws IOException {
//    JFileChooser fchooser = new JFileChooser(".");
//    fchooser.setSelectedFile(new File("image.png"));
//    int retvalue = fchooser.showSaveDialog(this);
//    if (retvalue == JFileChooser.APPROVE_OPTION) {
//      ImageUtil.writeImage(fchooser.getSelectedFile().getName(), model.getImageByName("image"));
//    }
//  }


//  @Override
//  public void actionPerformed(ActionEvent arg0) {
//    if (arg0.getActionCommand().equals("Open file")) {
//      openFile();
//    } else if (arg0.getActionCommand().equals("Save file")) {
//      if (model.getImgList().size() != 0) {
//        try {
//          saveFile();
//        } catch (IOException | IllegalArgumentException e) {
//          view.renderMessage("Error saving file");
//        }
//      } else {
//        view.renderMessage("No image currently loaded");
//      }
//    }
//  }


