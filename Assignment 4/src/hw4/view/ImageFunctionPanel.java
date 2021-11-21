package hw4.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import hw4.controller.HistogramRGB;
import hw4.controller.functions.ImageFunction;
import hw4.model.Image;
import hw4.model.ImageModel;

public class ImageFunctionPanel extends JPanel implements ActionListener {

  private ArrayList<ImageFunction> functions;
  private JPanel histogramPanel;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private ImageModel model;

  public ImageFunctionPanel(List<List<String>> buttonMap, ArrayList<ImageFunction> functions,
                            ImageModel model, JLabel imageLabel, JPanel imagePanel, JPanel histogramPanel) {
    setBorder(BorderFactory.createTitledBorder("Image functions"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.functions = functions;
    this.imagePanel = imagePanel;
    this.histogramPanel = histogramPanel;
    this.model = model;
    this.imageLabel = imageLabel;
    for(List<String> button : buttonMap) {
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
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        img.setRGB(i, j, image.getPixel(j, i).getRGB());
      }
    }
    imageLabel.setIcon(new ImageIcon(img));
    //TODO: possible fix? inefficient
    histogramPanel.repaint();
    imagePanel.repaint();
    histogramPanel.revalidate();
    imagePanel.revalidate();
    imagePanel.validate();
    histogramPanel.validate();
//    imagePanel.remove(histogramPanel);
//    histogramPanel = new HistogramRGB(model.getImageByName("image"));
//    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
//    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.X_AXIS));
//    imagePanel.add(histogramPanel);
//    imagePanel.revalidate();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    switch (arg0.getActionCommand()) {
      case "brighten":
      case "darken": {
        for (int i = 0; i < functions.size(); i++) {
          if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
            try {
              functions.get(i).doFunction(model, new Scanner(new StringReader(
                      JOptionPane.showInputDialog("Change image by how much? " +
                              "(Answer must be number)") + "\nimage\nimage")));
            } catch (IOException e) {
              e.printStackTrace();
            }
            updateImageIcon();
            i = functions.size();
          }
        }
      }
      break;
      default: {
        for (int i = 0; i < functions.size(); i++) {
          if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
            try {
              functions.get(i).doFunction(model, new Scanner(new StringReader("image\nimage")));
            } catch (IOException e) {
              e.printStackTrace();
            }
            updateImageIcon();
            i = functions.size();
          }
        }
      }
      break;
    }
  }
}
