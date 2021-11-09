package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;

/**
 * This class represents a kernel that blurs an image.
 */
public class BlurFunction extends AKernel {

  /**
   * Constructor for the class. The kernel's values are pre-defined.
   */
  public BlurFunction() {
    super("blur", new double[][]{
            new double[]{0.0625, 0.125, 0.0625},
            new double[]{0.125, 0.25, 0.125},
            new double[]{0.0625, 0.125, 0.0625}
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
