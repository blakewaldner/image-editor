import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.controller.functions.AColorTransform;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for AColorTransform class.
 */
public abstract class AColorTransformTest {

  ImageModel model;
  Readable read;
  Scanner scan;
  Pixel[][] img2;
  Pixel[][] newPixels;
  AColorTransform func;

  /**
   * Creates tests for an AColorTransform image function.
   *
   * @param a    1st pixel of base 2x2 image
   * @param b    2nd pixel of base 2x2 image
   * @param c    3rd pixel of base 2x2 image
   * @param d    4th pixel of base 2x2 image
   * @param e    1st expected pixel of new 2x2 image
   * @param f    2nd expected pixel of new 2x2 image
   * @param g    3rd expected pixel of new 2x2 image
   * @param h    4th expected pixel of new 2x2 image
   * @param func acolortransform image function being tested
   */
  public AColorTransformTest(Pixel a, Pixel b, Pixel c, Pixel d,
                             Pixel e, Pixel f, Pixel g, Pixel h, AColorTransform func) {
    img2 = new Pixel[2][2];
    img2[0][0] = a;
    img2[0][1] = b;
    img2[1][1] = c;
    img2[1][0] = d;
    read = new StringReader("test test-new");
    model = new ImageModel();
    scan = new Scanner(read);
    this.func = func;

    newPixels = new Pixel[2][2];
    newPixels[0][0] = e;
    newPixels[0][1] = f;
    newPixels[1][1] = g;
    newPixels[1][0] = h;
  }

  @Test
  public void testValidFunction() throws IOException {
    Image i2 = new Image(img2, "test");
    model.save(i2);
    func.doFunction(model, scan);
    Image newImage = model.getImageByName("test-new");
    assertEquals(newImage.getImg(), newPixels);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName() throws IOException {
    read = new StringReader("invalidname invalidname");
    scan = new Scanner(read);
    Image image = new Image(img2, "thisname");
    model.save(image);
    func.doFunction(model, scan);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() throws IOException {
    read = new StringReader("thisname thisname");
    scan = new Scanner(read);
    Image image = new Image(null, "thisname");
    model.save(image);
    func.doFunction(model, scan);
  }

}
