package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;
import hw4.ImageUtil;

/**
 * This class represents the command for "save". It saves
 * a ppm file to a given file path from a given image name.
 */
public class SaveFunction extends ImageFunction {

  /**
   * Constructor for creating save function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public SaveFunction() {
    super("save");
  }

  /**
   * Performs the save function on a given image according to inputs
   * from given scanner.
   *
   * @param model   list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public void doFunction(ImageModel model, Scanner scanner) throws IOException {
    String imagePath = scanner.next();//args[1];
    String imageName = scanner.next();//args[2];
    ImageUtil.writeImage(imagePath, model.getImageByName(imageName));
  }
}
