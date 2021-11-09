

import org.junit.Test;

import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Class representing tests for Pixels.
 */
public class PixelTest {

  @Test
  public void testGreyScale() {
    Pixel a = new Pixel(2, 3, 4);
    Pixel b = new Pixel(5, 5, 5);

    assertEquals(a.greyScale(5), b);
  }

  @Test
  public void testScaleByColor() {
    Pixel a = new Pixel(2, 3, 4);
    Pixel b = new Pixel(2, 2, 2);
    Pixel c = new Pixel(3, 3, 3);
    Pixel d = new Pixel(4, 4, 4);

    assertEquals(a.scaleByColor("red"), b);
    assertEquals(a.scaleByColor("green"), c);
    assertEquals(a.scaleByColor("blue"), d);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidScale() {
    Pixel a = new Pixel(2, 2, 2);
    a.scaleByColor("grey");
  }

  @Test
  public void testGetLuma() {
    Pixel a = new Pixel(2, 3, 4);
    Pixel b = new Pixel(0, 0, 0);
    assertEquals(a.getLuma(), 3);
    assertEquals(b.getLuma(), 0);

  }

  @Test
  public void testGetMin() {
    Pixel a = new Pixel(2, 3, 4);
    Pixel b = new Pixel(0, 0, 0);
    Pixel c = new Pixel(3, 3, 4);
    Pixel d = new Pixel(5, 3, 4);

    assertEquals(a.getMin(), 2);
    assertEquals(b.getLuma(), 0);
    assertEquals(c.getLuma(), 3);
    assertEquals(d.getLuma(), 3);

  }

  @Test
  public void testGetIntensity() {
    Pixel a = new Pixel(2, 3, 4);
    Pixel b = new Pixel(0, 0, 0);
    assertEquals(a.getIntensity(), 3);
    assertEquals(b.getIntensity(), 0);
  }

  @Test
  public void testBrighten() {
    Pixel a = new Pixel(200, 300, 400);
    Pixel b = new Pixel(400, 400, 400);
    Pixel c = new Pixel(230, 330, 430);

    assertEquals(a.brighten(500, 400).toString(), b.toString());
    a = new Pixel(200, 300, 400);
    assertEquals(a.brighten(30, 500).toString(), c.toString());
  }

  @Test
  public void testtoString() {
    Pixel a = new Pixel(200, 300, 400);
    assertEquals(a.toString(), "200\n" +
            "300\n" +
            "400\n");
  }

  @Test
  public void testEquals() {
    Pixel a = new Pixel(200, 200, 200);
    Pixel b = new Pixel(200, 100, 200);
    Pixel c = new Pixel(200, 200, 200);
    assertEquals(a.equals(c), true);
    assertEquals(b.equals(c), false);

  }

  @Test
  public void testGetValues() {
    Pixel a = new Pixel(1, 3, 5);
    assertEquals(a.getRed(), 1);
    assertEquals(a.getGreen(), 3);
    assertEquals(a.getBlue(), 5);

  }
}