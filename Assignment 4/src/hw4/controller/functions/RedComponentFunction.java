package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModelInterface;

/**
 * This class represents the command for "red-component". It greyscales a
 * given image by each pixels red value to a new given image name. If same image name,
 * overrides image.
 */
public class RedComponentFunction extends ImageFunction {

  /**
   * Constructor for creating red-component function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public RedComponentFunction() {
    super("red-component");
  }

  /**
   * Performs the red-component function on a given image according to inputs
   * from given scanner.
   *
   * @param model   list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public void doFunction(ImageModelInterface model, Scanner scanner) {
    String imageName = scanner.next();//args[1];
    String destImageName = scanner.next();//args[2];
    model.save(model.getImageByName(imageName).component("red", destImageName));
  }
}
