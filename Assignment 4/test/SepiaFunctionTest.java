import hw4.model.Pixel;
import hw4.controller.functions.SepiaFunction;

/**
 * Test class for SepiaFunction "sepia" script command.
 */
public class SepiaFunctionTest extends AColorTransformTest {
  /**
   * Creates tests for valid/invalid usages of sepia function in abstract parent class.
   */
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
