package hw4.functions;

import hw4.Image;
import hw4.ImageModel;
import hw4.Pixel;

public abstract class AKernel extends ImageFunction {
  protected double[][] kArray;

  /**
   * Constructor for creating a valid command that the controller
   * will recognize.
   *
   * @param command command prompt string for controller to recognize command
   */
  public AKernel(String command, double [][] kArray) {
    super(command);
    this.kArray = kArray;
  }


  protected Image applyKernel(Image image, String imageName){ // need to talk about how compenent is implemented
    Pixel [][] a = new Pixel[image.getHeight()][image.getWidth()];
    for(int i = 0; i < image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        a[i][j] = allAround(i,j,image);
      }
    }
    return new Image(a, imageName);
  }

  protected Pixel allAround(int row, int col, Image image){
    int sumRed = 0;
    int sumGreen = 0;
    int sumBlue = 0;
    for(int i = 0; i < kArray.length; i ++){
      for(int j = 0; j < kArray[0].length; j ++){
        int modelRow = i - kArray.length/2 + row;
        int modelCol = j - kArray.length/2 + col;
        if(modelRow >= 0
                && modelRow < image.getHeight()
                && (modelCol >= 0 && modelCol < image.getWidth())){
          sumRed += (image.getPixel(modelRow, modelCol).getRed()*kArray[i][j]); // how to convert from double to int???
          sumGreen += image.getPixel(modelRow, modelCol).getGreen()*kArray[i][j];
          sumBlue += image.getPixel(modelRow, modelCol).getBlue()*kArray[i][j];

        }
      }
    }
    return new Pixel(sumRed, sumGreen, sumBlue).clampPixel(sumRed, sumGreen, sumBlue, image.getMax()); // good way to clamp
  }
}
