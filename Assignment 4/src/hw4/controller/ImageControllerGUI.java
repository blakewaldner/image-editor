package hw4.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.functions.ImageFunction;
import hw4.model.ImageModelInterface;
import hw4.view.GuiView;

public class ImageControllerGUI implements Features, ImageController {
  private ImageModelInterface model;
  private GuiView view;
  private ArrayList<ImageFunction> functions;

  public ImageControllerGUI(ImageModelInterface model, GuiView view) {
    if (model != null && view != null) {
      this.model = model;
      this.view = view;
    } else {
      try {
        view.renderMessage("Error loading model/view");
      }
      catch (IOException e) {}
    }
  }

  public void startProcess() {
    this.functions = ImageUtil.createFunctions();
    this.view.addFeatures(this);
  }

  @Override
  public void open(JPanel panel) {
    JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm", "jpeg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(panel);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      try {
        model.save(ImageUtil.readFile(f.getAbsolutePath(), "image"));
      }
      catch (NullPointerException e) {
        try {
          view.renderMessage("Error loading file");
        }
        catch (IOException e2) {}
      }
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
