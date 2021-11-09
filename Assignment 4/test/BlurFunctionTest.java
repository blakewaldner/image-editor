import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.controller.functions.SharpenFunction;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;
import hw4.controller.functions.BlurFunction;

import static org.junit.Assert.*;

public class BlurFunctionTest {
  ImageModel imageModel;
  Readable read;
  Image image;
  Image image1;
  Scanner scan;
  Pixel[][] img2;

  private void initializer() {
    read = new StringReader("test test");
    imageModel = new ImageModel();
    scan = new Scanner(read);

    Pixel[][] img = new Pixel[3][3];

    for(int i = 0; i < img.length; i ++){
      for(int j = 0; j < img[0].length; j ++){
        img[i][j] = new Pixel(i*8, i+j*8, j*8);
      }
    }

    image = new Image(img, "test");

    img2 = new Pixel[3][3];

    imageModel.save(image);
  }
  @Test
  public void doFunction() throws IOException {
    initializer();
    BlurFunction blur = new BlurFunction();
    blur.doFunction(imageModel, new Scanner(read));

    img2[0][0] = new Pixel(1,1,1);
    img2[0][1] = new Pixel(1,6,6);
    img2[0][2] = new Pixel(1,7,7);
    img2[1][0] = new Pixel(6,1,1);
    img2[1][1] = new Pixel(8,8,8);
    img2[1][2] = new Pixel(6,9,9);
    img2[2][0] = new Pixel(7,1,1);
    img2[2][1] = new Pixel(9,6,6);
    img2[2][2] = new Pixel(7,7,7);

    image1 = new Image(img2, "test");

    assertEquals(image1, imageModel.getImageByName("test"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidName() throws IOException {
    initializer();
    BlurFunction blur = new BlurFunction();
    read = new StringReader("testInvalid test");
    blur.doFunction(imageModel, new Scanner(read));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidImage() throws IOException {
    initializer();
    BlurFunction blur = new BlurFunction();
    read = new StringReader("thisname thisname");
    scan = new Scanner(read);
    Image image = new Image(null, "thisname");
    imageModel.save(image);
    blur.doFunction(imageModel, scan);
  }
}