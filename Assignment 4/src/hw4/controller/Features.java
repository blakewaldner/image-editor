package hw4.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * Interface for creating objects that perform image operations.
 */
public interface Features {
  /**
   * Saves an image file to a given directory.
   *
   * @param panel panel for file chooser box to be attached to
   * @throws IOException if error when saving file
   */
  void save(JPanel panel) throws IOException;

  /**
   * Opens an image file from a given directory.
   *
   * @param panel panel for file chooser box to be attached to
   */
  void open(JPanel panel);

  /**
   * Transforms the current displayed image with a given image operation
   * depending on which button clicked.
   *
   * @param arg0 user's action event from when button clicked
   * @throws IOException if error when transforming image
   */
  void transform(ActionEvent arg0) throws IOException;
}
