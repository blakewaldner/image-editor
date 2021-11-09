import hw4.model.Pixel;
import hw4.controller.functions.SepiaFunction;


public class SepiaFunctionTest extends AColorTransformTest {

  public SepiaFunctionTest() {
    super(new Pixel(4, 5, 6),
            new Pixel(3, 3, 3),
            new Pixel(6, 6, 7),
            new Pixel(8, 8, 8),
            new Pixel(6, 5, 4),
            new Pixel(4, 3, 2),
            new Pixel(8, 7, 5),
            new Pixel(10, 9, 7),
            new SepiaFunction());
  }

}