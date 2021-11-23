package hw4.view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import hw4.controller.ImageUtil;
import hw4.controller.functions.ImageFunction;
import hw4.model.Image;
import hw4.model.ImageModel;

/**
 * This class represents the GUI JPanel that holds the buttons for
 * modifying an image.
 */
public class ImageFunctionPanel extends JPanel implements ActionListener {

  private ArrayList<ImageFunction> functions;
  private JPanel histogramPanel;
  private JLabel imageLabel;
  private ImageModel model;
  private ImageGUIView view;

  /**
   * This constructs a new ImageFunctionPanel for the main ImageGUIFrame to hold.
   * @param model model that holds current image state
   * @param imageLabel JLabel that is displaying image
   * @param histogramPanel JPanel that is displaying histogram
   * @param view view for rendering error messages
   */
  public ImageFunctionPanel(ImageModel model, JLabel imageLabel, JPanel histogramPanel,
                            ImageGUIView view) {
    setBorder(BorderFactory.createTitledBorder("Image functions"));
    setLayout(new GridLayout(4, 3, 10, 10));
    setPreferredSize(new Dimension(50, 200));

    this.functions = ImageUtil.createFunctions();
    this.histogramPanel = histogramPanel;
    this.model = model;
    this.imageLabel = imageLabel;
    this.view = view;

    for (List<String> button : createButtonMap()) {
      String label = button.get(0);
      String command = button.get(1);
      add(createButton(label, command));
    }
  }

  private JButton createButton(String label, String command) {
    JButton button = new JButton(label);
    button.setActionCommand(command);
    button.addActionListener(this);
    return button;
  }

  private void updateImageIcon() {
    Image image = model.getImageByName("image");
    imageLabel.setIcon(new ImageIcon(ImageUtil.convertToBufferedImage(image)));
    histogramPanel.repaint();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
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
          } catch (IOException e) {
            view.renderMessage("Error when clicking button");
          }
          updateImageIcon();
          i = functions.size();
        }
      }
    } else {
      view.renderMessage("No image currently loaded");
    }
  }

  private List<List<String>> createButtonMap() {
    List<List<String>> buttonMap = new ArrayList<>();
    buttonMap.add(Arrays.asList("Blur", "blur"));
    buttonMap.add(Arrays.asList("Flip Horizontal", "horizontal-flip"));
    buttonMap.add(Arrays.asList("Flip Vertical", "vertical-flip"));
    buttonMap.add(Arrays.asList("Sepia", "sepia"));
    buttonMap.add(Arrays.asList("Greyscale", "greyscale"));
    buttonMap.add(Arrays.asList("Sharpen", "sharpen"));
    buttonMap.add(Arrays.asList("Red Component", "red-component"));
    buttonMap.add(Arrays.asList("Green Component", "green-component"));
    buttonMap.add(Arrays.asList("Blue Component", "blue-component"));
    buttonMap.add(Arrays.asList("Luma Component", "luma-component"));
    buttonMap.add(Arrays.asList("Intensity Component", "intensity-component"));
    buttonMap.add(Arrays.asList("Value Component", "value-component"));
    buttonMap.add(Arrays.asList("Brighten", "brighten"));
    buttonMap.add(Arrays.asList("Darken", "darken"));
    return buttonMap;
  }

}
