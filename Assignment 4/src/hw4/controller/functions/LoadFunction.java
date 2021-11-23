package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModel;
import hw4.controller.ImageUtil;

/**
 * This class represents the command for "load". It loads
 * a given image from a file path to a new given image name.
 * If same image name, overrides image.
 */
public class LoadFunction extends ImageFunction {

  /**
   * Constructor for creating load function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public LoadFunction() {
    super("load");
  }

  /**
   * Performs the load function on a given image according to inputs
   * from given scanner.
   *
   * @param model   list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public void doFunction(ImageModelInterface model, Scanner scanner) {
    String imagePath = scanner.next();//args[1];
    String imageName = scanner.next();//args[2];
    model.save(ImageUtil.readFile(imagePath, imageName));
  }
}
