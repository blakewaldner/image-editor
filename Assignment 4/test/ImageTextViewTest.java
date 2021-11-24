import org.junit.Test;

import java.io.IOException;

import hw4.view.ImageTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests representing tests for class ImageTextView.
 */

public class ImageTextViewTest {
  StringBuilder out;
  ImageTextView a;

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInput() {
    a = new ImageTextView(out);
  }

  @Test
  public void renderMessage() throws IOException {
    out = new StringBuilder();
    a = new ImageTextView(out);
    a.renderMessage("Test message");
    assertEquals(out.toString(), "Test message");
  }
}