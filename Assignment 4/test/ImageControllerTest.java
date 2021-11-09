import org.junit.Test;

import java.io.InputStreamReader;

import hw4.controller.ImageController;
import hw4.controller.ImageControllerImpl;
import hw4.model.ImageModel;

import static org.junit.Assert.assertFalse;

/**
 * Test class for the ImageController class.
 */
public class ImageControllerTest {

  @Test
  public void validController() {
    Readable read = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(read, new StringBuilder());
    assertFalse(controller == null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidReadable() {
    Readable read = null;
    ImageController controller = new ImageControllerImpl(read, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAppendable() {
    Readable read = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(read, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBoth() {
    ImageController controller = new ImageControllerImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidModel() {
    Readable read = new InputStreamReader(System.in);
    ImageController controller = new ImageControllerImpl(read, new StringBuilder(), null);
  }

  @Test
  public void validAll() {
    Readable read = new InputStreamReader(System.in);
    ImageModel model = new ImageModel();
    ImageController controller = new ImageControllerImpl(read, new StringBuilder(), model);
    assertFalse(controller == null);
  }


}
