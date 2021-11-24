package hw4.view;

import javax.swing.ImageIcon;

import hw4.controller.Features;
import hw4.model.Image;

public interface GuiView extends ImageViewInterface{
  void addFeatures(Features features);
  void setImageIcon(ImageIcon icon);
  void repaintHistogram();
  void setImage(Image image);
}
