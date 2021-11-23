package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModelInterface;

/**
 * This class represents the command for "horizontal-flip". It horizontally
 * flips a given to a new given image name. If same image name,
 * overrides image.
 */
public class HorizontalFlipFunction extends ImageFunction {


  /**
   * Constructor for creating horizontal-flip function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public HorizontalFlipFunction() {
    super("horizontal-flip");
  }

  /**
   * Performs the horizontal-flip function on a given image according to inputs
   * from given scanner.
   *
   * @param model list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public void doFunction(ImageModelInterface model, Scanner scanner) {
    String imageName = scanner.next();//args[1];
    String destImageName = scanner.next();//args[2];
    model.save(model.getImageByName(imageName).flipHorizontal(destImageName));
  }
}
