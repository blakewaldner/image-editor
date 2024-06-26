import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.controller.functions.ImageFunction;
import hw4.controller.functions.SharpenFunction;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for SharpenFunction "sharpen" script command.
 */
public class SharpenFunctionTest {
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

    Pixel[][] img = new Pixel[5][5];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        img[i][j] = new Pixel(i * 8, i + j * 8, j * 8);
      }
    }

    image = new Image(img, "test");

    img2 = new Pixel[5][5];

    imageModel.save(image);
  }

  @Test
  public void testDoFunction() throws IOException {
    initializer();
    ImageFunction sharpen = new SharpenFunction();
    sharpen.doFunction(imageModel, new Scanner(read));

    img2[0][0] = new Pixel(0, 0, 0);
    img2[0][1] = new Pixel(0, 1, 6);
    img2[0][2] = new Pixel(0, 11, 18);
    img2[0][3] = new Pixel(0, 37, 42);
    img2[0][4] = new Pixel(0, 35, 39);
    img2[1][0] = new Pixel(6, 0, 0);
    img2[1][1] = new Pixel(9, 4, 9);
    img2[1][2] = new Pixel(3, 18, 26);
    img2[1][3] = new Pixel(9, 54, 59);
    img2[1][4] = new Pixel(6, 47, 51);
    img2[2][0] = new Pixel(18, 0, 0);
    img2[2][1] = new Pixel(26, 2, 3);
    img2[2][2] = new Pixel(16, 12, 16);
    img2[2][3] = new Pixel(26, 46, 49);
    img2[2][4] = new Pixel(18, 40, 42);
    img2[3][0] = new Pixel(42, 1, 0);
    img2[3][1] = new Pixel(59, 16, 9);
    img2[3][2] = new Pixel(49, 31, 26);
    img2[3][3] = new Pixel(59, 64, 59);
    img2[3][4] = new Pixel(42, 55, 51);
    img2[4][0] = new Pixel(39, 2, 0);
    img2[4][1] = new Pixel(51, 13, 6);
    img2[4][2] = new Pixel(42, 25, 18);
    img2[4][3] = new Pixel(51, 48, 42);
    img2[4][4] = new Pixel(39, 44, 39);

    image1 = new Image(img2, "test");

    assertEquals(image1, imageModel.getImageByName("test"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName() throws IOException {
    initializer();
    ImageFunction sharpen = new SharpenFunction();
    read = new StringReader("testInvalid test");
    sharpen.doFunction(imageModel, new Scanner(read));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() throws IOException {
    initializer();
    ImageFunction sharpen = new SharpenFunction();
    read = new StringReader("thisname thisname");
    Image image = new Image(null, "thisname");
    imageModel.save(image);
    sharpen.doFunction(imageModel, new Scanner(read));
  }
}
