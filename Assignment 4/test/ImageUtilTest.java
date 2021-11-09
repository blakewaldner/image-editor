import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import hw4.controller.ImageController;
import hw4.controller.ImageControllerImpl;
import hw4.controller.ImageUtil;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test class for the ImageController class.
 */
public class ImageUtilTest {

  @Test
  public void validPPMExport() throws IOException {
    Pixel [] [] img = new Pixel[2][2];
    img[0][0] = new Pixel(0, 255, 0);
    img[0][1] = new Pixel(255, 0, 0);
    img[1][1] = new Pixel(0, 0, 255);
    img[1][0] = new Pixel(0, 0, 0);
    Image image = new Image(img, "testppm", 255);
    ImageUtil.writePPM("res/testppm.ppm", image, 2, 2);
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream("res/testppm.ppm"));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file name, no file found with given name");
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    String expectedPPM = "P3\n" + //ppm version
            "2 2\n" + //width/height
            "255\n" + //max value
            "0\n" + //pixels
            "255\n" +
            "0\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n";
    assertEquals(builder.toString(), expectedPPM);
  }

  @Test
  public void validPNGExport() throws IOException {
    Pixel [] [] img = new Pixel[2][2];
    img[0][0] = new Pixel(0, 255, 0);
    img[0][1] = new Pixel(255, 0, 0);
    img[1][1] = new Pixel(0, 0, 255);
    img[1][0] = new Pixel(0, 0, 0);
    Image image = new Image(img, "testpng", 255);
    ImageUtil.writeImage("res/testpng.png", image);
    Image newImage = ImageUtil.readFile("res/testpng.png", "newpng");
    assertEquals(image.getImg(), newImage.getImg());
  }

  @Test
  public void validJPGExport() throws IOException {
    Pixel [] [] img = new Pixel[2][2];
    img[0][0] = new Pixel(0, 255, 0);
    img[0][1] = new Pixel(255, 0, 0);
    img[1][1] = new Pixel(0, 0, 255);
    img[1][0] = new Pixel(0, 0, 0);
    Image image = new Image(img, "testjpg", 255);
    ImageUtil.writeImage("res/testjpg.jpg", image);
    Image newImage = ImageUtil.readFile("res/testjpg.jpg", "newjpg");
    //tests that image can be recognized from jpg file, jpg has lossy conversion so
    //wasn't possible to test pixels individually, (https://piazza.com/class/kstd2rfiays6tw?cid=807)
    assertFalse(newImage == null);
  }

  @Test
  public void validPPMToPngConversion() throws IOException {
    Image image = ImageUtil.readFile("res/testppm.ppm", "testppm");
    ImageUtil.writeImage("res/testpng.png", image);
    Image newImage = ImageUtil.readFile("res/testpng.png", "testpng");
    assertEquals(image.getImg(), newImage.getImg());
  }

  @Test
  public void validPngToPPM() throws IOException {
    Image image = ImageUtil.readFile("res/testpng.png", "testpng");
    ImageUtil.writeImage("res/testppm.ppm", image);
    Image newImage = ImageUtil.readFile("res/testppm.ppm", "testppm");
    assertEquals(image.getImg(), newImage.getImg());
  }

  @Test
  public void validPPMtoJpg() throws IOException {
    Image image = ImageUtil.readFile("res/testppm.ppm", "testppm");
    ImageUtil.writeImage("res/testjpg.jpg", image);
    Image newImage = ImageUtil.readFile("res/testjpg.jpg", "testjpg");
    //jpg = lossy so impossible to test pixels being same, checks if image size is retained
    assertEquals(image.getHeight()+image.getWidth(), newImage.getHeight()+ newImage.getWidth());
  }

  @Test
  public void validJpgToPPM() throws IOException {
    Image image = ImageUtil.readFile("res/testjpg.jpg", "testjpg");
    ImageUtil.writeImage("res/testppm.ppm", image);
    Image newImage = ImageUtil.readFile("res/testppm.ppm", "testppm");
    assertEquals(image.getImg(), newImage.getImg());
  }

}
