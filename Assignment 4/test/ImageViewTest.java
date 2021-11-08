import org.junit.Test;

import java.io.IOException;

import hw4.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Tests representing tests for class ImageView.
 */

public class ImageViewTest {
  StringBuilder out;
  ImageView a;

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    a = new ImageView(out);
  }

  @Test
  public void renderMessage() throws IOException {
    out = new StringBuilder();
    a = new ImageView(out);
    a.renderMessage("Test message");
    assertEquals(out.toString(), "Test message");
  }
}