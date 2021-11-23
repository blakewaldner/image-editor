package hw4.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.functions.ImageFunction;
import hw4.model.ImageModelInterface;
import hw4.view.Features;
import hw4.view.GuiView;
import hw4.view.ImageGUIView;

public class ImageControllerGUI implements Features, ImageController {
  private ImageModelInterface model;
  private GuiView view;
  private ArrayList<ImageFunction> functions;

  public void setView(GuiView a){
    view = a;
    view.addFeatures(this);
  }

  public ImageControllerGUI(ImageModelInterface a){
      model = a;
      functions = ImageUtil.createFunctions();
  }

  public void startProcess() throws IOException {
    new ImageGUIView();
  }

  @Override
  public void open(JPanel panel) {
      JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Images", "jpg", "png", "bmp", "ppm","jpeg");
      fchooser.setFileFilter(filter);
      int retvalue = fchooser.showOpenDialog(panel);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        model.save(ImageUtil.readFile(f.getAbsolutePath(), "image"));
        view.setImageIcon(new ImageIcon(
                ImageUtil.convertToBufferedImage(model.getImageByName("image"))));
        view.setImage(model.getImageByName("image"));
      }
      view.repaintHistogram();
  }

  @Override
  public void save(JPanel panel) throws IOException {
    JFileChooser fchooser = new JFileChooser(".");
    fchooser.setSelectedFile(new File("image.png"));
    int retvalue = fchooser.showSaveDialog(panel);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      ImageUtil.writeImage(fchooser.getSelectedFile().getName(), model.getImageByName("image"));
    }
  }

  @Override
  public void transform(ActionEvent arg0) throws IOException {
    if (model.getImgList().size() != 0) {
      for (int i = 0; i < functions.size(); i++) {
        if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
          try {
            if (arg0.getActionCommand().equals("brighten")
                    || arg0.getActionCommand().equals("darken")) {
              functions.get(i).doFunction(model, new Scanner(new StringReader(
                      JOptionPane.showInputDialog("Change image by how much? " +
                              "(Answer must be number)") + "\nimage\nimage")));
            } else {
              functions.get(i).doFunction(model, new Scanner(new StringReader("image\nimage")));
            }
          } catch (IOException | IllegalArgumentException e) {
            view.renderMessage("Error when clicking button");
          }
          view.setImageIcon(new ImageIcon(
                  ImageUtil.convertToBufferedImage(model.getImageByName("image"))));
          view.setImage(model.getImageByName("image"));
          view.repaintHistogram();
          i = functions.size();
        }
      }
    } else {
      view.renderMessage("No image currently loaded");
    }
  }


}
