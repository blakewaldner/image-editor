import org.junit.Test;

import hw4.view.JPanels.HistogramPanel;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test class for testing the functionality of Histogram class.
 */
public class HistogramRGBTest {
  Image image;
  ImageModel model;
  HistogramPanel a;
  int[] x;

  private void initializer(){
    Pixel z = new Pixel(1, 2, 3);
    Pixel b = new Pixel(1, 0, 0);
    Pixel c = new Pixel(3, 3, 4);
    Pixel d = new Pixel(5, 5, 5);

    Pixel[][] img = new Pixel[2][2];

    img[0][0] = z;
    img[0][1] = b;
    img[1][0] = c;
    img[1][1] = d;

    image = new Image(img, "image");
    model = new ImageModel();
    model.save(image);

    a = new HistogramPanel();

     x = new int[256];
    for(int a : x){
      a = 0;
    }
  }



  @Test
  public void testRedValues() {
    initializer();
    assertArrayEquals(a.getRed(), x);
  }

  @Test
  public void blueValues() {
    initializer();
    assertArrayEquals(a.getBlue(), x);
  }

  @Test
  public void greenValues() {
    initializer();
    assertArrayEquals(a.getGreen(), x);
  }

  @Test
  public void intensityValues() {
    initializer();
    assertArrayEquals(a.getIntensity(), x);
  }

  @Test
  public void testSetUpValues(){
    initializer();
    assertArrayEquals(a.getRed(), x);
    assertArrayEquals(a.getBlue(), x);
    assertArrayEquals(a.getGreen(), x);
    assertArrayEquals(a.getIntensity(), x);
    a.setImage(image);
    a.setValues();

    x[1] = 2;
    x[3] = 1;
    x[5] = 1;
    assertArrayEquals(a.getRed(),x);

    x[0] = 1;
    x[1] = 0;
    x[2] = 1;
    x[3] = 1;
    x[5] = 1;
    assertArrayEquals(a.getGreen(),x);

    x[0] = 1;
    x[1] = 0;
    x[2] = 0;
    x[3] = 1;
    x[4] = 1;
    x[5] = 1;
    assertArrayEquals(a.getBlue(),x);

    x[0] = 1;
    x[1] = 0;
    x[2] = 1;
    x[3] = 1;
    x[4] = 0;
    x[5] = 1;
    assertArrayEquals(a.getIntensity(),x);
  }
}