package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.ImageModel;

public class SepiaFunction extends AColorTransform {

  public SepiaFunction() {
    super("sepia", new double[][]{
            new double[]{0.393, 0.769, 0.189},
            new double[]{0.349, 0.686, 0.168},
            new double[]{0.272, 0.534, 0.131}
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
