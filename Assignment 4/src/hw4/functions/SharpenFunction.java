package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;

public class SharpenFunction extends AKernel {

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
   * @param model list of images currently operating on
   * @param scanner     inputted arguments
   * @throws IOException if invalid input from scanner is read
   */
  @Override
  public void doFunction(ImageModel model, Scanner scanner) throws IOException {
    String imageName = scanner.next();//args[1];
    String destImageName = scanner.next();//args[2];
    model.save(applyKernel(model.getImageByName(imageName), destImageName));
  }
}
