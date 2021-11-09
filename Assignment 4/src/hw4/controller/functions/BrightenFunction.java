package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModel;

/**
 * This class represents the command for "brighten". It brighrens a
 * given image by a given amount to a new given image name. If same image name,
 * overrides image.
 */
public class BrightenFunction extends ImageFunction {

  /**
   * Constructor for creating brighten function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public BrightenFunction() {
    super("brighten");
  }

  /**
   * Performs the brighten function on a given image according to inputs
   * from given scanner.
   *
   * @param model   list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public void doFunction(ImageModel model, Scanner scanner) {
    String secondArg = scanner.next();
    int increment = 0;
    if (isNumeric(secondArg)) {
      increment = Integer.parseInt(secondArg);//Integer.parseInt(args[1]);
    } else {
      throw new IllegalArgumentException("Second argument must be a numeric for " +
              "brighten/darken");
    }
    String imageName = scanner.next();//args[2];
    String destImageName = scanner.next();//args[3];
    model.save(model.getImageByName(imageName).brighten(increment, destImageName));
  }

  //determines if a string is a numeric
  private boolean isNumeric(String s) {
    try {
      //if can parse without error, the string is an integer
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
