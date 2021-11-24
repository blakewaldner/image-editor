package hw4.view;

import javax.swing.ImageIcon;

import hw4.controller.Features;
import hw4.model.Image;

/**
 * This interface represents the functions that a view displaying GUI should have.
 * addFeatures adds action listeners, setImageIcon changes the displayed image,
 * repaintHistogram updates the histogram values displayed, and setImage changes
 * the image the histogram reads from.
 */
public interface GuiView extends ImageViewInterface {
  /**
   * Adds features to the buttons in the GUI's panels that adds action listeners
   * to buttons for them to perform image/file functions on current image.
   *
   * @param features
   */
  void addFeatures(Features features);

  /**
   * Changes the displayed image of GUI.
   *
   * @param icon new icon displaying image
   */
  void setImageIcon(ImageIcon icon);

  /**
   * Updates the histogram to display histogram of currently displayed image.
   */
  void repaintHistogram();

  /**
   * Updates the image used by histogram to new image.
   *
   * @param image new image to get histogram values from
   */
  void setImage(Image image);
}
