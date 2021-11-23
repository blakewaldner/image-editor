package hw4.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Image View class that initializes and creates the GUI window for image processing.
 */
public class ImageGUIView implements ImageViewInterface {

  private JFrame frame;

  /**
   * Constructor for ImageGUIView that creates the JFrame holding the GUI.
   */
  public ImageGUIView() {
    frame = new ImageGUIFrame(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * This function displays a message box.
   * Typically, used for displaying error messages.
   *
   * @param message is the message to be rendered.
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }
}
