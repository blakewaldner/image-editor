package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;

/**
 * This class represents the command for applying the "sepia" effect
 * using a color transformation object an an image.
 * It gives an image a reddish-brownish tone to a new given image name.
 * If same image name, the image is overridden with the new image.
 */
public class SepiaFunction extends AColorTransform {

  /**
   * Constructor for creating sepia function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public SepiaFunction() {
    super("sepia", new double[][]{
            new double[]{0.393, 0.769, 0.189},
            new double[]{0.349, 0.686, 0.168},
            new double[]{0.272, 0.534, 0.131}
    });
  }

  /**
   * Performs the command's function on a given image according to inputs
   * from given scanner.
   *
   * @param model list of images currently operating on
   * @param scanner     inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  @Override
  public void doFunction(ImageModel model, Scanner scanner) throws IOException {
    String imageName = scanner.next();//args[1];
    String destImageName = scanner.next();//args[2];
    model.save(applyColorTransform(model.getImageByName(imageName), destImageName));
  }
}
