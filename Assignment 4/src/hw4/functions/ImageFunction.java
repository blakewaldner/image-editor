package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;

/**
 * Abstract class for a command that can be run by the program
 * on images.
 */
public abstract class ImageFunction {

  public String command;

  /**
   * Constructor for creating a valid command that the controller
   * will recognize.
   *
   * @param command command prompt string for controller to recognize command
   */
  public ImageFunction(String command) {
    this.command = command;
  }

  /**
   * Performs the command's function on a given image according to inputs
   * from given scanner.
   *
   * @param model list of images currently operating on
   * @param s     inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  public abstract void doFunction(ImageModel model, Scanner s) throws IOException;

}
