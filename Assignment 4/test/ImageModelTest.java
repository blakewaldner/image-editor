import org.junit.Test;

import java.util.ArrayList;

import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.ImageModelInterface;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Class representing tests for class ImageModel.
 */
public class ImageModelTest {
  ImageModelInterface a = new ImageModel();
  Image image;
  Image image1;
  ArrayList<Image> listofImg = new ArrayList<>();

  private void initializer() {
    Pixel a = new Pixel(1, 2, 3);
    Pixel b = new Pixel(0, 0, 0);
    Pixel c = new Pixel(3, 3, 4);
    Pixel d = new Pixel(5, 5, 5);

    Pixel[][] img = new Pixel[2][2];

    img[0][0] = a;
    img[0][1] = b;
    img[1][1] = c;
    img[1][0] = d;

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


  }

  @Test
  public void saveTwo() {
    assertEquals(a.getImgList(), listofImg);
    initializer();
    a.save(image);
    a.save(image1);
    listofImg.add(image);
    listofImg.add(image1);
    assertEquals(a.getImgList(), listofImg);
  }

  @Test
  public void saveDuplicate() {
    assertEquals(a.getImgList(), listofImg);
    initializer();
    image1 = new Image(image1.getImg(), "test");
    a.save(image);
    a.save(image1);
    listofImg.add(image1);
    assertEquals(a.getImgList(), listofImg);
  }

  @Test
  public void getImageByName() {
    initializer();
    a.save(image);
    a.save(image1);
    assertEquals(a.getImageByName("test"), image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getImageByInvalidName() {
    initializer();
    a.save(image);
    a.save(image1);
    a.getImageByName("a");
  }

}