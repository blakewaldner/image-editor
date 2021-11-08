package hw4.functions;

import java.io.IOException;
import java.util.Scanner;

import hw4.Image;
import hw4.ImageModel;
import hw4.Pixel;

public abstract class AColorTransform extends ImageFunction{
  double [][] colorChange;

  /**
   * Constructor for creating a valid command that the controller
   * will recognize.
   *
   * @param command command prompt string for controller to recognize command
   */
  public AColorTransform(String command, double [] [] colorChange) {
    super(command);
    this.colorChange = colorChange;
  }

  protected Image applyColorTransform(Image image, String imageName){ // need to talk about how compenent is implemented
    Pixel [][] a = new Pixel[image.getHeight()][image.getWidth()];
    for(int i = 0; i < image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        a[i][j] = changePixelColor(image.getPixel(i,j));
      }
    }
    return new Image(a, imageName);
  }

  protected Pixel changePixelColor(Pixel a){
    int newRed;
    int newGreen;
    int newBlue;
    newRed = rowSum(colorChange[0][0], colorChange[0][1], colorChange[0][2], a);
    newGreen = rowSum(colorChange[1][0], colorChange[1][1], colorChange[1][2], a);
    newBlue = rowSum(colorChange[2][0], colorChange[2][1], colorChange[2][2], a);

    return new Pixel(newRed, newGreen, newBlue).clampPixel(newRed, newGreen, newBlue, 255);

  }

  private int rowSum(double a, double b, double c, Pixel d){
    int z = 0;
    z += a*d.getRed() + b*d.getGreen() + c*d.getBlue();
    return z;
  }
}
