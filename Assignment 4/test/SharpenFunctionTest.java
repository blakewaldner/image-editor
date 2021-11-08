import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.Image;
import hw4.ImageModel;
import hw4.Pixel;
import hw4.functions.SharpenFunction;
import static org.junit.Assert.assertEquals;


public class SharpenFunctionTest {
  ImageModel imageModel;
  Readable read;
  Image image;
  Image image1;
  Scanner scan;

  private void initializer() {
     read = new StringReader("test testafter");
     imageModel = new ImageModel();
     scan = new Scanner(read);

    Pixel[][] img = new Pixel[5][5];

    for(int i = 0; i < img.length; i ++){
      for(int j = 0; j < img[0].length; j ++){
        img[i][j] = new Pixel(i*8, i+j*8, j*8);
      }
    }



     image = new Image(img, "test");

    Pixel e = new Pixel(4, 5, 6);
    Pixel f = new Pixel(3, 3, 3);
    Pixel g = new Pixel(6, 6, 7);
    Pixel h = new Pixel(8, 8, 8);

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = e;
    img2[0][1] = f;
    img2[1][1] = g;
    img2[1][0] = h;

     image1 = new Image(img2, "testtwo");

    imageModel.save(image);
    imageModel.save(image1);
  }

  @Test
  public void testDoFunction() throws IOException {
    initializer();
  SharpenFunction sharpen = new SharpenFunction();
  sharpen.doFunction(imageModel, new Scanner(read));
  for(int i =0; i < 5; i ++){
    for(int j = 0; j < 5; j ++){
      imageModel.getImageByName("testafter").getPixel(i,j).getRed() + " "
      imageModel.getImageByName("testafter").getPixel(i,j).getGreen() + " "
      imageModel.getImageByName("testafter").getPixel(i,j).getBlue());

    }
  }

  }
}
