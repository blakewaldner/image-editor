package hw4.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ImageGUIView implements ImageViewInterface {

  JFrame frame;

  public ImageGUIView() {
    frame = new ImageGUIFrame(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * This function displays a message box.
   *
   * @param message is the message to be rendered.
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }
}
