package hw4.controller.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.model.ImageModel;
import hw4.model.ImageModelInterface;

/**
 * This class represents the command for sharpening an image using a kernal object.
 * It sharpens a given image to a new given image name.
 * If same image name, the image is overridden with the new image.
 */
public class SharpenFunction extends AKernel {

  /**
   * Constructor for creating sharpen function object.
   * Takes no arguments, command for activating function is hard coded in
   * and set to abstract parent function.
   */
  public SharpenFunction() {
    super("sharpen", new double[][]{
        new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
        new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new double[]{-0.125, 0.25, 1, 0.25, -0.125},
        new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
    });
  }

  /**
   * Performs the command's function on a given image according to inputs
   * from given scanner.
   *
   * @param model   list of images currently operating on
   * @param scanner inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  @Override
  public void doFunction(ImageModelInterface model, Scanner scanner) throws IOException {
    String imageName = scanner.next();//args[1];
    String destImageName = scanner.next();//args[2];
    model.save(applyKernel(model.getImageByName(imageName), destImageName));
  }
}
