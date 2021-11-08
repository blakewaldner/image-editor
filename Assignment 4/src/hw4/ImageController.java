package hw4;

import java.io.IOException;

/**
 * This represents the functionalityfor the ImageController.
 */
public interface ImageController {

  /**
   * This function takes in commands and modifies the ImageModel based on those commands.
   *
   * @throws IOException when the String in ImageView can not be formatted.
   */
  void startProcess() throws IOException;

}
