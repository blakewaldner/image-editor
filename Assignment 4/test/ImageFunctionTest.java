import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import hw4.controller.ImageController;
import hw4.controller.ImageControllerImpl;
import hw4.model.ImageModel;
import hw4.controller.functions.BlueComponentFunction;
import hw4.controller.functions.BrightenFunction;
import hw4.controller.functions.DarkenFunction;
import hw4.controller.functions.GreenComponentFunction;
import hw4.controller.functions.HorizontalFlipFunction;
import hw4.controller.functions.ImageFunction;
import hw4.controller.functions.IntensityComponentFunction;
import hw4.controller.functions.LoadFunction;
import hw4.controller.functions.LumaComponentFunction;
import hw4.controller.functions.RedComponentFunction;
import hw4.controller.functions.SaveFunction;
import hw4.controller.functions.ValueComponentFunction;
import hw4.controller.functions.VerticalFlipFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ImageFunction class and the classes that extend from it.
 */
public class ImageFunctionTest {

  @Test
  public void testValidImageCommands() throws IOException {

    ArrayList<String> validCommands = new ArrayList<>();
    validCommands.add("load res/testing.ppm test");
    validCommands.add("save res/test-saved.ppm test");
    validCommands.add("brighten 10 test test-brighter");
    validCommands.add("darken 10 test test-darker");
    validCommands.add("vertical-flip test test-vertical");
    validCommands.add("horizontal-flip test test-horizontal");
    validCommands.add("value-component test test-value");
    validCommands.add("luma-component test test-luma");
    validCommands.add("intensity-component test test-intensity");
    validCommands.add("red-component test test-red");
    validCommands.add("green-component test test-green");
    validCommands.add("blue-component test test-blue");


    ArrayList<ImageFunction> functions = new ArrayList();
    functions.add(new LoadFunction());
    functions.add(new SaveFunction());
    functions.add(new BrightenFunction());
    functions.add(new DarkenFunction());
    functions.add(new VerticalFlipFunction());
    functions.add(new HorizontalFlipFunction());
    functions.add(new ValueComponentFunction());
    functions.add(new LumaComponentFunction());
    functions.add(new IntensityComponentFunction());
    functions.add(new RedComponentFunction());
    functions.add(new GreenComponentFunction());
    functions.add(new BlueComponentFunction());

    for (int x = 0; x < functions.size(); x++) {
      Readable read = new StringReader("load res/testing.ppm test\n" + validCommands.get(x));
      Appendable append = new StringBuilder();
      ImageController controller = new ImageControllerImpl(read, append);
      controller.startProcess();
      assertTrue(append.toString().endsWith("Successfully recognized "
              + functions.get(x).getCommand() + " command\n"));
    }

  }

  @Test
  public void testInvalidLoadFilePath() throws IOException {
    Readable read = new StringReader("load doesntexist.ppm test");
    Appendable append = new StringBuilder();
    ImageController controller = new ImageControllerImpl(read, append);
    controller.startProcess();
    assertEquals(append.toString(), "Invalid Arguments\n");
  }

  @Test
  public void testValidLoad() throws IOException {
    Readable read = new StringReader("load res/testing.ppm test");
    Appendable append = new StringBuilder();
    ImageModel model = new ImageModel();
    ImageController controller = new ImageControllerImpl(read, append, model);
    controller.startProcess();
    assertTrue(model.getImgList().size() > 0);
  }

  @Test
  public void testInvalidPathSave() throws IOException {
    Readable read = new StringReader("load res/testing.ppm test\n" +
            "save folderdoesntexist/test-saved.ppm test");
    Appendable append = new StringBuilder();
    ImageController controller = new ImageControllerImpl(read, append);
    controller.startProcess();
    assertEquals(append.toString(), "Successfully recognized load command\n" +
            "File not found at location\n");
  }

  @Test
  public void testInvalidCommand() throws IOException {
    Readable read = new StringReader("commanddoesntexist");
    Appendable append = new StringBuilder();
    ImageController controller = new ImageControllerImpl(read, append);
    controller.startProcess();
    assertEquals(append.toString(), "Invalid Command\n");
  }

  @Test
  public void testInvalidImageInCommand() throws IOException {

    ArrayList<String> validCommands = new ArrayList<>();
    validCommands.add("brighten 10 imagedoesntexist dog");
    validCommands.add("darken 10 imagedoesntexist dog");
    validCommands.add("vertical-flip imagedoesntexist dog");
    validCommands.add("horizontal-flip imagedoesntexist dog");
    validCommands.add("value-component imagedoesntexist dog");
    validCommands.add("luma-component imagedoesntexist dog");
    validCommands.add("intensity-component imagedoesntexist dog");
    validCommands.add("red-component imagedoesntexist dog");
    validCommands.add("green-component imagedoesntexist dog");
    validCommands.add("blue-component imagedoesntexist dog");


    ArrayList<ImageFunction> functions = new ArrayList();
    functions.add(new BrightenFunction());
    functions.add(new DarkenFunction());
    functions.add(new VerticalFlipFunction());
    functions.add(new HorizontalFlipFunction());
    functions.add(new ValueComponentFunction());
    functions.add(new LumaComponentFunction());
    functions.add(new IntensityComponentFunction());
    functions.add(new RedComponentFunction());
    functions.add(new GreenComponentFunction());
    functions.add(new BlueComponentFunction());

    for (int x = 0; x < functions.size(); x++) {
      Readable read = new StringReader(validCommands.get(x));
      Appendable append = new StringBuilder();
      ImageController controller = new ImageControllerImpl(read, append);
      controller.startProcess();
      assertEquals(append.toString(), "Invalid Arguments\n");
    }

  }

  @Test
  public void testMultipleCommandSameImage() throws IOException {
    Readable read = new StringReader("load res/testing.ppm test\n" +
            "horizontal-flip test test\n" +
            "brighten 10 test test\n" +
            "vertical-flip test test\n" +
            "value-component test test");
    Appendable append = new StringBuilder();
    ImageModel model = new ImageModel();
    ImageController controller = new ImageControllerImpl(read, append, model);
    controller.startProcess();
    assertEquals(append.toString(), "Successfully recognized load command\n" +
            "Successfully recognized horizontal-flip command\n" +
            "Successfully recognized brighten command\n" +
            "Successfully recognized vertical-flip command\n" +
            "Successfully recognized value-component command\n");//change to what equals
    assertTrue(model.getImgList().size() == 1);
  }

}
