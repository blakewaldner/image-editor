package hw4.view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.ImageUtil;
import hw4.model.ImageModel;

public class FileFunctionPanel extends JPanel implements ActionListener {

  private ImageModel model;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private ImageGUIView view;

  public FileFunctionPanel(ImageModel model, JLabel imageLabel, JPanel histogramPanel,
                           ImageGUIView view) {
    this.model = model;
    this.imageLabel = imageLabel;
    this.histogramPanel = histogramPanel;
    this.view = view;

    setBorder(BorderFactory.createTitledBorder("File functions"));
    setLayout(new GridLayout(1, 2, 10, 10));
    setPreferredSize(new Dimension(50, 75));

    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);

    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);

    add(fileOpenButton);
    add(fileSaveButton);
  }

  private void saveFile() throws IOException {
    JFileChooser fchooser = new JFileChooser(".");
    fchooser.setSelectedFile(new File("image.png"));
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      ImageUtil.writeImage(fchooser.getSelectedFile().getName(), model.getImageByName("image"));
    }
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getActionCommand().equals("Open file")) {
      openFile();
    } else if (arg0.getActionCommand().equals("Save file")) {
      if (model.getImgList().size() != 0) {
        try {
          saveFile();
        } catch (IOException | IllegalArgumentException e) {
          view.renderMessage("Error saving file");
        }
      } else {
        view.renderMessage("No image currently loaded");
      }
    }
  }

  private void openFile() {
    JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm","jpeg");
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
