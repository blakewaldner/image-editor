import org.junit.Test;

import hw4.model.Image;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Class representing tests for class Image.
 */
public class ImageTest {
  Pixel a = new Pixel(1, 2, 3);
  Pixel b = new Pixel(0, 0, 0);
  Pixel c = new Pixel(3, 3, 4);
  Pixel d = new Pixel(5, 5, 5);

  Pixel e = new Pixel(4, 5, 6);
  Pixel f = new Pixel(3, 3, 3);
  Pixel g = new Pixel(6, 6, 7);
  Pixel h = new Pixel(8, 8, 8);

  String nullname;

  Pixel[][] img = new Pixel[2][2];

  Image image;

  private void initialize() {
    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;
    img[1][0] = d;

    image = new Image(img, "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImgConstructorTwoParameters() {
    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;

    Image d = new Image(img, "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructorTwoParameters() {
    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;
    img[1][0] = d;

    Image test = new Image(img, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImgConstructorThreeParameters() {
    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;

    Image d = new Image(img, "test", 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStringConstructorThreeParameters() {
    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;
    img[1][0] = d;
    String e;

    Image d = new Image(img, nullname, 255);
  }


  @Test
  public void testBrighten() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(252, 253, 254);
    img1[0][1] = new Pixel(251, 251, 251);
    img1[1][1] = new Pixel(254, 254, 255);
    img1[1][0] = new Pixel(255, 255, 255);
    Image image2 = new Image(img1, "testnew");

    assertEquals(image.brighten(251, "testnew"), image2);
  }

  @Test
  public void testDarken() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(0, 0, 0);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(0, 0, 0);
    img1[1][0] = new Pixel(0, 0, 0);
    Image image2 = new Image(img1, "testnew");

    assertEquals(image.brighten(-10, "testnew"), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBrighten() {
    initialize();
    image.brighten(3, nullname);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullComponent() {
    initialize();
    image.component("red", nullname);
    image.component(nullname, "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidComponentMethod() {
    initialize();
    image.component("grey", "test");
  }

  @Test
  public void testComponentRed() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(1, 1, 1);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("red", "testnew"), image2);
  }

  @Test
  public void testComponentGreen() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("green", "testnew"), image2);
  }

  @Test
  public void testComponentBlue() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(3, 3, 3);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(4, 4, 4);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("blue", "testnew"), image2);
  }

  @Test
  public void testComponentLuma() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("luma", "testnew"), image2);
  }

  @Test
  public void testComponentValue() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(1, 1, 1);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("value", "testnew"), image2);
  }

  @Test
  public void testComponentIntensity() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.component("intensity", "testnew"), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidVertical() {
    initialize();
    image.flipVertical(nullname);
  }

  @Test
  public void flipVertical() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = d;
    img1[0][1] = c;
    img1[1][1] = b;
    img1[1][0] = a;
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.flipVertical("testnew"), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHorizontal() {
    initialize();
    image.flipHorizontal(nullname);
  }

  @Test
  public void flipHorizontal() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = b;
    img1[0][1] = a;
    img1[1][1] = d;
    img1[1][0] = c;
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.flipHorizontal("testnew"), image2);
  }

  @Test
  public void getImg() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);

    img1[0][0] = i;
    img1[0][1] = j;
    img1[1][1] = k;
    img1[1][0] = l;
    assertEquals(image.getImg(), img1);
  }

  @Test
  public void getMax() {
    initialize();
    assertEquals(image.getMax(), 255);
  }

  @Test
  public void getWidth() {
    initialize();
    assertEquals(image.getWidth(), 2);
  }

  @Test
  public void getHeight() {
    initialize();
    assertEquals(image.getHeight(), 2);
  }

  @Test
  public void getName() {
    initialize();
    assertEquals(image.getName(), "test");
  }

  @Test
  public void tesEquals() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];
    img1[0][0] = d;
    img1[0][1] = c;
    img1[1][1] = b;
    img1[1][0] = a;
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.equals(image), true);
    assertEquals(image.equals(image2), false);

  }

  @Test
  public void testMultipleCommands() {
    initialize();
    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = b;
    img1[0][1] = a;
    img1[1][1] = d;
    img1[1][0] = c;
    Image image2 = new Image(img1, "testnew");
    assertEquals(image.flipHorizontal("testnew"), image2);

    img1[0][0] = c;
    img1[0][1] = d;
    img1[1][1] = a;
    img1[1][0] = b;
    image2 = new Image(img1, "testnew");
    assertEquals(image.flipHorizontal("testnew").flipVertical("testnew"), image2);


    img1[0][0] = new Pixel(3, 3, 3);
    img1[0][1] = new Pixel(5, 5, 5);
    img1[1][1] = new Pixel(1, 1, 1);
    img1[1][0] = new Pixel(0, 0, 0);
    image2 = new Image(img1, "testnew");
    assertEquals(image.flipHorizontal("testnew").flipVertical("testnew").component("red",
            "testnew"), image2);

  }
}