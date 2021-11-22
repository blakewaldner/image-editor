import org.junit.Test;

import hw4.view.HistogramPanel;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.*;

public class HistogramRGBTest {
  Image image;
  ImageModel model;
  HistogramPanel a;

  private void initializer(){
    Pixel z = new Pixel(1, 2, 3);
    Pixel b = new Pixel(0, 0, 0);
    Pixel c = new Pixel(3, 3, 4);
    Pixel d = new Pixel(5, 5, 5);

    Pixel[][] img = new Pixel[2][2];

    img[0][0] = z;
    img[0][1] = b;
    img[1][1] = c;
    img[1][0] = d;

    image = new Image(img, "image");
    model = new ImageModel();
    model.save(image);

    a = new HistogramPanel(model);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvailidInput(){
    a = new HistogramPanel(model);
  }

  @Test
  public void testRedValues() {
    initializer();
    int[] x = {1,0,3,5};
    assertEquals(a.redValues(), x);

  }

  @Test
  public void blueValues() {
    initializer();
    int[] x = {2,0,3,5};
    assertEquals(a.blueValues(), x);
  }

  @Test
  public void greenValues() {
    initializer();
    int[] x = {3,0,4,5};
    assertEquals(a.greenValues(), x);
  }

  @Test
  public void intensityValues() {
    initializer();
    int[] x = {2,0,3,5};
    assertEquals(a.intensityValues(), x);
  }

}