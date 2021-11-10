package hw4.model;

/**
 * Represents a pixel in an image. It contains the integer values for red, green, and blue.
 */
public class Pixel {

  private int r;
  private int g;
  private int b;

  /**
   * Constructor for a pixel.
   *
   * @param r is the red value.
   * @param g is the green value.
   * @param b is the blue value.
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public Pixel(int rgb) {
    this.r = (rgb & 0xff0000) >> 16;
    this.g = (rgb & 0xff00) >> 8;
    this.b = rgb & 0xff;
  }

  /**
   * Returns the values of r,b and b in a String.
   *
   * @returns a String.
   */
  @Override
  public String toString() {
    return "" + this.r + "\n"
            + this.g + "\n"
            + this.b + "\n";
  }

  /**
   * Returns a greyscale version of a pixel.
   *
   * @param c is the value to set the r,g,b values to.
   * @returns a new Pixel.
   */
  public Pixel greyScale(int c) {
    return new Pixel(c, c, c);
  }

  /**
   * Sets the greyscale to either a red, green or blue value by changing all the other values
   * to the input value.
   *
   * @param color is the color to set the greyscale to. It can be red,green or blue.
   * @returns a new Pixel.
   */
  public Pixel scaleByColor(String color) {
    int value = 0;
    switch (color) {
      case "red":
        value = this.r;
        break;
      case "green":
        value = this.g;
        break;
      case "blue":
        value = this.b;
        break;
      default:
        throw new IllegalArgumentException("Invalid Input");
    }
    return greyScale(value);
  }

  /**
   * This function calculates the weighted sum of the three color values.
   *
   * @returns an integer.
   */
  public int getLuma() {
    return (int) Math.round(0.2126 * this.r + 0.7152 * this.g + 0.0722 * this.b);
  }

  /**
   * This function calculates the minimum value of the three color values.
   *
   * @returns an integer.
   */
  public int getMin() {
    return Math.min(Math.min(this.r, this.g), this.b);
  }

  /**
   * This function calculates the average of the three color values.
   *
   * @returns an integer.
   */
  public int getIntensity() {
    return (int) Math.round((this.r + this.g + this.b) / 3);
  }

  /**
   * This function increases/decreases the three color values by amt until the max value or 0.
   *
   * @param amt is the amount added to the three color values. A positive values brightens an
   *            image, while a negative value makes an image darker.
   * @param max is the maximum value the color values can be.
   * @returns a new Pixel.
   */
  public Pixel brighten(int amt, int max) {
    //clamps min/max values if above or below
    return clampPixel(this.r + amt, this.g + amt, this.b + amt, max);
  }

  public Pixel clampPixel(int r, int g, int b, int max) {
    //clamps to max value if above
    if (r > max) {
      r = max;
    }
    if (g > max) {
      g = max;
    }
    if (b > max) {
      b = max;
    }
    //clamps to 0 (min value) if below
    if (r < 0) {
      r = 0;
    }
    if (g < 0) {
      g = 0;
    }
    if (b < 0) {
      b = 0;
    }
    return new Pixel(r, g, b);
  }

  /**
   * This function overrides Java's default equality function. Two pixels are equal if they
   * have the same red, green, and blue values.
   *
   * @param obj is the object to be compared.
   * @returns a boolean for whether or not the two objects are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Pixel)) {
      return false;
    }
    Pixel that = (Pixel) obj;

    return this.r == that.getRed() && this.b == that.getBlue() && this.g == that.getGreen();
  }

  public int getRGB() {
    int rgb = this.r;
    rgb = (rgb << 8) + this.g;
    rgb = (rgb << 8) + this.b;
    return rgb;
  }

  public int getRed() {
    return r;
  }

  public int getGreen() {
    return g;
  }

  public int getBlue() {
    return b;
  }

  /**
   * This function overrides the default Java hashCode.
   *
   * @returns a hashcode based on a pixel's rgb values.
   */
  @Override
  public int hashCode() {
    return this.r * 100 + this.g * 10 + this.b + 100000;
  }


}
