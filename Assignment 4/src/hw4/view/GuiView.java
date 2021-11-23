package hw4.view;

import javax.swing.*;

import hw4.model.Image;

public interface GuiView extends ImageViewInterface{
  void addFeatures(Features a);
  void setImageIcon(ImageIcon b);
  void repaintHistogram();
  void setImage(Image image);
}
