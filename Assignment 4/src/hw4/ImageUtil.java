package hw4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read/write a PPM image from file.
 */
public class ImageUtil {

  public static Image readFile(String fileName, String imageName) {

    int dotIndex = fileName.lastIndexOf(".");
    if(dotIndex == -1 || dotIndex == fileName.length()-1) {
      throw new IllegalArgumentException("Invalid file ext for file path");
    }
    String ext = fileName.substring(dotIndex+1);

    if(ext.equalsIgnoreCase("ppm")) {
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
      Pixel [] [] imgList = new Pixel[height][width];
      for(int i = 0; i < width; i++) {
        for(int j = 0; j < height; j++) {
          imgList[j][i] = new Pixel(img.getRGB(i,j));
        }
      }
      //TODO: need max value for pngs/jpgs?
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
   * Writes an image class to a PPM file. Relevant for "save" command.
   *
   * @param fileName file path to be saved to
   * @param image    image class being saved
   * @throws IOException if filepath or image is invalid
   */
  public static void writeImage(String fileName, Image image) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    int dotIndex = fileName.lastIndexOf(".");
    if(dotIndex == -1 || dotIndex == fileName.length()-1) {
      throw new IllegalArgumentException("Invalid file ext for file path");
    }
    String ext = fileName.substring(dotIndex+1);
    if (ext.equalsIgnoreCase("ppm")) {
      writePPM(fileName, image, width, height);
    }
    else {
      File file = new File(fileName);
      BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i < width-1; i++) {
        for (int j = 0; j < height-1; j++) {
          img.setRGB(i,j,image.getPixel(j,i).getRGB());
        }
      }
      ImageIO.write(img, ext, file);
    }
  }

  public static void writePPM(String fileName, Image image, int width, int height) throws IOException {
    int max = image.getMax();
    Pixel[][] img = image.getImg();
    FileOutputStream file = new FileOutputStream(fileName);
    file.write(new String("P3\n").getBytes());
    file.write(new String("" + width + " " + height + "\n").getBytes());
    file.write(new String("" + max + "\n").getBytes());
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
}