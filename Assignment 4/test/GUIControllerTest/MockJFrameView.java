package GUIControllerTest;

import java.io.IOException;

import javax.swing.*;

import hw4.controller.Features;
import hw4.model.Image;
import hw4.view.GuiView;

/**
 * Mock class for the view.
 */
public class MockJFrameView implements GuiView {
  public Appendable out;
  public MockJFrameView(Appendable out){
    this.out = out;
  }

  @Override
  public void addFeatures(Features features) {
    try {
      out.append(String.format("addFeatures\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setImageIcon(ImageIcon icon) {
    try {
      out.append(String.format("setImageIcon\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void repaintHistogram() {
    try {
      out.append(String.format("repaintHistogram\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setImage(Image image) {
    try {
      out.append(String.format("setImage\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      out.append(String.format("renderMessage\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
