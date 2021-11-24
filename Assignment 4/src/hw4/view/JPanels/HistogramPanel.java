package hw4.view.JPanels;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import hw4.model.Image;

/**
 * This class is a JPanel that displays a histogram based on the RGB and intensity
 * values of a specified image. setValues(String name) is used to specify the image.
 * This class takes in an ImageModel.
 */
public class HistogramPanel extends JPanel {
  private Image image;
  private int[] red = new int[256];
  private int[] green = new int[256];
  private int[] blue = new int[256];
  private int[] intensity = new int[256];

  /**
   * This is the constructor for HistogramPanel. It takes in an ImageModel.
   * All the values in the arrays are initialized to 0.
   */
  public HistogramPanel() {
    this.setPreferredSize(new Dimension(500, 500));
    for (int i = 0; i < red.length; i++) {
      red[i] = 0;
      green[i] = 0;
      blue[i] = 0;
      intensity[i] = 0;
    }
    setVisible(true);
  }

  private int[] redValues() {
    int[] red = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        red[image.getPixel(i, j).getRed()]++;
      }
    }
    return red;
  }

  private int[] blueValues() {
    int[] blue = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        blue[image.getPixel(i, j).getBlue()]++;
      }
    }
    return blue;
  }

  private int[] greenValues() {
    int[] green = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        green[image.getPixel(i, j).getGreen()]++;
      }
    }
    return green;
  }

  private int[] intensityValues() {
    int[] intensity = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        intensity[(image.getPixel(i, j).getGreen() + image.getPixel(i, j).getRed()
                + image.getPixel(i, j).getBlue()) / 3]++;
      }
    }
    return intensity;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  /**
   * This sets the values for the red,green,blue, and intensity arrays based on the name.
   */
  public void setValues() {
    if (image != null) {
      red = redValues();
      blue = blueValues();
      green = greenValues();
      intensity = intensityValues();
    }
  }

  /**
   * This returns the red array.
   *
   * @returns an array.
   */
  public int[] getRed() {
    return red.clone();
  }

  public int[] getBlue() {
    return blue.clone();
  }

  public int[] getGreen() {
    return green.clone();
  }

  public int[] getIntensity() {
    return intensity.clone();
  }

  public void paint(Graphics g) {
    setValues();
    for (int i = 0; i < red.length - 1; i++) {
      int a = 20;
      int b = 2;
      g.setColor(Color.RED);
      g.drawLine(i*b, red[i] / a, i*b + b, red[i + 1] / a);

      g.setColor(Color.GREEN);
      g.drawLine(i*b, green[i] / a, i*b + b, green[i + 1] / a);

      g.setColor(Color.BLUE);
      g.drawLine(i*b, blue[i] / a, i*b + b, blue[i + 1] / a);

      g.setColor(Color.BLACK);
      g.drawLine(i*b, intensity[i] / a, i*b + b, intensity[i + 1] / a);
    }
  }

}
