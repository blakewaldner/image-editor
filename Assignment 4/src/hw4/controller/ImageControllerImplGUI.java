package hw4.controller;

import java.io.IOException;

import javax.swing.*;

import hw4.view.ImageGUIView;

public class ImageControllerImplGUI implements ImageController {

  /**
   * This function takes in commands and modifies the ImageModel based on those commands.
   *
   * @throws IOException when the String in ImageView can not be formatted.
   */
  @Override
  public void startProcess() throws IOException {
    ImageGUIView.setDefaultLookAndFeelDecorated(false);
    ImageGUIView frame = new ImageGUIView();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

