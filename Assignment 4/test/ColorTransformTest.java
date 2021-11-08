import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.Image;
import hw4.ImageModel;
import hw4.Pixel;
import hw4.functions.AColorTransform;
import hw4.functions.SepiaFunction;

public class ColorTransformTest {

  @Test
  public void testValidTransform() throws IOException {
    SepiaFunction sepia = new SepiaFunction();
    Readable read = new StringReader("sepia testwo-s testtwo");
    ImageModel model = new ImageModel();
    Scanner scan = new Scanner(read);

    Pixel[][] img2 = new Pixel[2][2];
    Pixel e = new Pixel(4, 5, 6);
    Pixel f = new Pixel(3, 3, 3);
    Pixel g = new Pixel(6, 6, 7);
    Pixel h = new Pixel(8, 8, 8);

    img2[0][0] = e;
    img2[0][1] = f;
    img2[1][1] = g;
    img2[1][0] = h;

    Image i2 = new Image(img2, "testtwo");
    model.save(i2);
    sepia.doFunction(model, scan);
    Image sepiaImage = model.getImageByName("testtwo-s");
  }

}
