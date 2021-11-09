package hw4;

/**
 * Represents an image. It contains a nested array of Pixel objects, the max color value for those
 * Pixels, and a String name. It contains methods for brightening an image, greyscaling an image,
 * flipping an image, or getting various details about a specified image.
 */
public class Image {
  private final Pixel[][] img;
  private final String name;
  private final int max;

  /**
   * Constructor for the Image. It sets the max value to 255.
   *
   * @param img  is a nested array of Pixels that is meant to represent an image.
   * @param name is the name of the Image.
   * @throws IllegalArgumentException when any of the arguments are null.
   */
  public Image(Pixel[][] img, String name) throws IllegalArgumentException {
    this(img, name, 255);
  }

  /**
   * Constructor for an Image.
   *
   * @param img  is a nested array of Pixels that is meant to represent an image.
   * @param name is the name of the Image.
   * @param max  is the max color value for the Pixels.
   * @throws IllegalArgumentException when any of the arguments are null.
   */
  public Image(Pixel[][] img, String name, int max) throws IllegalArgumentException {
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        if (img[i][j] == null) {
          throw new IllegalArgumentException("Invalid img");
        }
      }
    }
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.img = img;
    this.name = name;
    this.max = max;
  }


  /**
   * This function brightens/darkens each Pixel in img by amt and returns a new Pixel with the name
   * passed in the parameter.
   *
   * @param amt  is the amount to be added to each Pixel's rgb values.
   * @param name is the name of the returned Image.
   * @throws IllegalArgumentException when the String is null.
   * @returns a new Image.
   */
  public Image brighten(int amt, String name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    //brightens each pixel in image
    Pixel[][] img2 = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        img2[i][j] = img[i][j].brighten(amt, max);
      }
    }
    return new Image(img2, name, this.max);
  }

  /**
   * This function is responsible for the greyscale functionality. It does
   * red-greyscale, green-greyscale, blue-greyscale, luma-greyscale,
   * value-greyscale, and intensity-greyscale.
   *
   * @param method is the method to do the greyscale.
   * @param name   is the name of the new Image that is returned.
   * @returns an Image.
   */

  public Image component(String method, String name) {
    if (name == null || method == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    Pixel[][] img2 = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        switch (method) {
          case "red":
          case "green":
          case "blue":
            img2[i][j] = img[i][j].scaleByColor(method);
            break;
          case "luma":
            img2[i][j] = img[i][j].greyScale(img[i][j].getLuma());
            break;
          case "value":
            img2[i][j] = img[i][j].greyScale(img[i][j].getMin());
            break;
          case "intensity":
            img2[i][j] = img[i][j].greyScale(img[i][j].getIntensity());
            break;
          default:
            throw new IllegalArgumentException("Invalid method");
        }
      }
    }
    return new Image(img2, name, this.max);
  }

  /**
   * This function returns a new Image that is a vertically flipped version of this one. Its name
   * is passed in through the parameter.
   *
   * @param name is the name of the new Image.
   * @throws IllegalArgumentException when name is null.
   * @returns an Image.
   */

  public Image flipVertical(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    Pixel[][] img2 = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < getHeight(); i++) {
      img2[getHeight() - i - 1] = img[i];
    }
    return new Image(img2, name, this.max);
  }

  /**
   * This function returns a new Image that is flipped horizontally with its name being the
   * name passed in.
   *
   * @param name is the name of the new Image.
   * @throws IllegalArgumentException when the String is null.
   * @returns a nw Image.
   */
  public Image flipHorizontal(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    Pixel[][] img2 = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        img2[i][getWidth() - j - 1] = img[i][j];
      }
    }
    return new Image(img2, name, this.max);
  }

  /**
   * This function returns a copy of the Image.
   *
   * @returns an Image.
   */
  public Pixel[][] getImg() {
    Pixel[][] img2 = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        img2[i][j] = img[i][j];
      }
    }
    return img2;
  }

  /**
   * This function overrides the default Java equality function. Two Images are equal
   * if they have the same Pixels and height,width, and max values.
   *
   * @param obj is the Object to be compared.
   * @returns a boolean.
   */

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Image)) {
      return false;
    }
    Image that = (Image) obj;
    boolean state = true;
    for (int i = 0; i < that.getHeight(); i++) {
      for (int j = 0; j < that.getWidth(); j++) {
        if (!that.getImg()[i][j].equals(img[i][j])) {
          state = false;
        }
      }
    }

    return this.getWidth() == that.getWidth() && that.getHeight() == this.getHeight() && state
           && this.getMax() == that.getMax() && this.getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return img.hashCode() * 100 + max;
  }

  /**
   * This function returns the max value of the Pixel's rgb values.
   *
   * @returns an int.
   */
  public int getMax() {
    return this.max;
  }

  /**
   * This function returns the width of the Image.
   *
   * @returns an int.
   */
  public int getWidth() {
    return img[0].length;
  }

  /**
   * This function returns the height of the Image.
   *
   * @returns an int.
   */
  public int getHeight() {
    return img.length;
  }

  /**
   * This function returns the name of the Image.
   *
   * @returns a String.
   */
  public String getName() {
    return this.name;
  }

  /**
   * This functions returns a copy of a specified pixel at a given row/col.
   * @param row row of pixel
   * @param col col of pixel
   * @return copy of pixel at row/col
   */
  public Pixel getPixel(int row, int col){
    return new Pixel(img[row][col].getRed(),img[row][col].getGreen(),img[row][col].getBlue());
  }

}
