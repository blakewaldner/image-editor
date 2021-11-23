package hw4.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import hw4.controller.functions.BlueComponentFunction;
import hw4.controller.functions.BlurFunction;
import hw4.controller.functions.BrightenFunction;
import hw4.controller.functions.DarkenFunction;
import hw4.controller.functions.GreenComponentFunction;
import hw4.controller.functions.GreyScaleFunction;
import hw4.controller.functions.HorizontalFlipFunction;
import hw4.controller.functions.ImageFunction;
import hw4.controller.functions.IntensityComponentFunction;
import hw4.controller.functions.LoadFunction;
import hw4.controller.functions.LumaComponentFunction;
import hw4.controller.functions.RedComponentFunction;
import hw4.controller.functions.SaveFunction;
import hw4.controller.functions.SepiaFunction;
import hw4.controller.functions.SharpenFunction;
import hw4.controller.functions.ValueComponentFunction;
import hw4.controller.functions.VerticalFlipFunction;
import hw4.model.Pixel;
import hw4.model.Image;


/**
 * This class contains utility methods to read/write a PPM image from file.
 */
public class ImageUtil {

  /**
   * Reads a file of supported extension (bmp, png, jpg, ppm) and converts it
   * to an image class.
   *
   * @param fileName  path of file
   * @param imageName name referred to by commands
   * @return image object from file
   */
  public static Image readFile(String fileName, String imageName) {

    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
      throw new IllegalArgumentException("Invalid file ext for file path");
    }
    String ext = fileName.substring(dotIndex + 1);

    if (ext.equalsIgnoreCase("ppm")) {
      return readPPM(fileName, imageName);
    } else {
      BufferedImage img;
      try {
        img = ImageIO.read(new File(fileName));
      } catch (IOException e) {
        throw new IllegalArgumentException("Invalid file name, no file found name: " + fileName);
      }
      int height = img.getHeight();
      int width = img.getWidth();
      Pixel[][] imgList = new Pixel[height][width];
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          imgList[j][i] = new Pixel(img.getRGB(i, j));
        }
      }
      return new Image(imgList, imageName);
    }
  }

  /**
   * Read an image file in the PPM format and converts it to an Image class.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename, String imageName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file name, no file found with given name");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file, no P3 token");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();


    Pixel[][] imgList = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imgList[i][j] = new Pixel(r, g, b);
        //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
    }

    return new Image(imgList, imageName, maxValue);
  }

  /**
   * Writes an image class to a File. Supported extensions are png/jpg/bmp/ppm.
   * Relevant for "save" command.
   *
   * @param fileName file path to be saved to
   * @param image    image class being saved
   * @throws IOException if file path or image is invalid
   */
  public static void writeImage(String fileName, Image image) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
      throw new IllegalArgumentException("Invalid file ext for file path");
    }
    String ext = fileName.substring(dotIndex + 1);
    if (ext.equalsIgnoreCase("ppm")) {
      writePPM(fileName, image, width, height);
    } else {
      File file = new File(fileName);
      ImageIO.write(convertToBufferedImage(image), ext, file);
    }
  }

  /**
   * Writes an image class to a PPM file. Relevant for "save" command.
   *
   * @param fileName file path to be saved to
   * @param image    image class being saved
   * @param width    width of image
   * @param height   height of image
   * @throws IOException if filepath or image is invalid
   */
  public static void writePPM(String fileName, Image image, int width, int height)
          throws IOException {
    int max = image.getMax();
    Pixel[][] img = image.getImg();
    FileOutputStream file = new FileOutputStream(fileName);
    file.write("P3\n".getBytes());
    file.write(("" + width + " " + height + "\n").getBytes());
    file.write(("" + max + "\n").getBytes());
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        file.write(img[i][j].toString().getBytes());
      }
    }
    try {
      file.close();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid filepath when saving");
    }
  }

  /**
   * Converts an image object to a BufferedImage object.
   * @param image image to be converted
   * @return image in bufferedimage format
   */
  public static BufferedImage convertToBufferedImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        img.setRGB(i, j, image.getPixel(j, i).getRGB());
      }
    }
    return img;
  }

  /**
   * Creates a list of current supported image functions.
   * @return arraylist containing all image functions available to user
   */
  public static ArrayList<ImageFunction> createFunctions() {
    ArrayList<ImageFunction> functions = new ArrayList();
    functions.add(new SaveFunction());
    functions.add(new LoadFunction());
    functions.add(new BrightenFunction());
    functions.add(new DarkenFunction());
    functions.add(new HorizontalFlipFunction());
    functions.add(new VerticalFlipFunction());
    functions.add(new RedComponentFunction());
    functions.add(new GreenComponentFunction());
    functions.add(new BlueComponentFunction());
    functions.add(new LumaComponentFunction());
    functions.add(new ValueComponentFunction());
    functions.add(new IntensityComponentFunction());
    functions.add(new BlurFunction());
    functions.add(new SharpenFunction());
    functions.add(new GreyScaleFunction());
    functions.add(new SepiaFunction());
    return functions;
  }
}