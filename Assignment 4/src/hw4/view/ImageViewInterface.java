package hw4.view;

import java.io.IOException;

/**
 * This interface represents the functionality for ImageView.
 */
public interface ImageViewInterface {
  /**
   * This function appends a message to out.
   *
   * @param message is the message to be rendered.
   * @throws IOException when it can not be formatted.
   */
  void renderMessage(String message) throws IOException;
}
