import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import hw4.controller.functions.GreyScaleFunction;
import hw4.controller.functions.SepiaFunction;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.Pixel;

import static org.junit.Assert.assertEquals;

public class GreyScaleFunctionTest extends AColorTransformTest {

  public GreyScaleFunctionTest() {
    super(new Pixel(4, 5, 6),
            new Pixel(3, 3, 3),
            new Pixel(6, 6, 7),
            new Pixel(8, 8, 8),
            new Pixel(4, 4, 4),
            new Pixel(3, 3, 3),
            new Pixel(6, 6, 6),
            new Pixel(8, 8, 8),
            new GreyScaleFunction());
  }

}
