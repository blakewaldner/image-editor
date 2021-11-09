package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModel;

/**
 * This class represents the command for greyscaling an image. It performs luma greyscaling
 * using a kernal on a given image to a new given image name. If same image name,
 * the image is overridden with the new image.
 */
public class GreyScaleFunction extends AColorTransform {

  /**
   * Constructor for creating greyscale function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public GreyScaleFunction() {
    super("greyscale", new double[][]{
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722},
            new double[]{0.2126, 0.7152, 0.0722}
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
