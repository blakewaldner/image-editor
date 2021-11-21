package hw4.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import hw4.controller.ImageUtil;
import hw4.controller.functions.ImageFunction;
import hw4.model.Image;
import hw4.model.ImageModel;

public class ImageFunctionPanel extends JPanel implements ActionListener {

  private ArrayList<ImageFunction> functions;
  private JPanel histogramPanel;
  private JLabel imageLabel;
  private ImageModel model;

  public ImageFunctionPanel(List<List<String>> buttonMap, ArrayList<ImageFunction> functions,
                            ImageModel model, JLabel imageLabel, JPanel histogramPanel) {
    setBorder(BorderFactory.createTitledBorder("Image functions"));
    //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    //setLayout(new SpringLayout());
    setPreferredSize(new Dimension(50,200));
    this.functions = functions;
    this.histogramPanel = histogramPanel;
    this.model = model;
    this.imageLabel = imageLabel;
    for (List<String> button : buttonMap) {
      String label = button.get(0);
      String command = button.get(1);
      add(createButton(label, command));
    }
  }

  private JButton createButton(String label, String command) {
    JButton button = new JButton(label);
    button.setActionCommand(command);
    button.addActionListener(this);
    return button;
  }

  private void updateImageIcon() {
    Image image = model.getImageByName("image");
    imageLabel.setIcon(new ImageIcon(ImageUtil.convertToBufferedImage(image)));
    histogramPanel.repaint();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (modelHasImgs()) {
      for (int i = 0; i < functions.size(); i++) {
        if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
          try {
            if (arg0.getActionCommand().equals("brighten")
                    || arg0.getActionCommand().equals("darken")) {
              functions.get(i).doFunction(model, new Scanner(new StringReader(
                      JOptionPane.showInputDialog("Change image by how much? " +
                              "(Answer must be number)") + "\nimage\nimage")));
            } else {
              functions.get(i).doFunction(model, new Scanner(new StringReader("image\nimage")));
            }
          } catch (IOException e) {
            throw new IllegalArgumentException("Error when clicking button");
          }
          updateImageIcon();
          i = functions.size();
        }
      }
    }
  }

  private boolean modelHasImgs() {
    return model.getImgList().size() != 0;
  }
}
