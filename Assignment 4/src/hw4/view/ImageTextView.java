package hw4.view;

import java.io.IOException;

/**
 * This class represents the view model. It is responsible for outputting text.
 */
public class ImageTextView implements ImageViewInterface {
  private final Appendable out;

  /**
   * This constructs a new ImageTextView.
   *
   * @param out is the chosen output type.
   * @throws IllegalArgumentException when Appendable out is null.
   */
  public ImageTextView(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Arguments can not be null");
    }
    this.out = out;
  }

  /**
   * This function appends a message to out.
   *
   * @param message is the message to be rendered.
   * @throws IOException when it can not be formatted.
   */
  public void renderMessage(String message) throws IOException {
    out.append(String.format(message));
  }


}
