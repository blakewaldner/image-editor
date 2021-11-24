package hw4.view.JPanels;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import hw4.controller.Features;

/**
 * This class represents the GUI JPanel that holds the buttons for
 * modifying an image.
 */
public class ImageFunctionPanel extends JPanel {
  /**
   * This constructs a new ImageFunctionPanel for the main ImageGUIFrame to hold.
   * The panel holds buttons for all the image functions available.
   */
  public ImageFunctionPanel() {
    setBorder(BorderFactory.createTitledBorder("Image functions"));
    setLayout(new GridLayout(4, 3, 10, 10));
    setPreferredSize(new Dimension(50, 200));
  }

  /**
   * Adds features to the buttons in the ImageFunctionPanel that adds action listeners
   * to buttons for them to perform image functions on current image.
   *
   * @param features features object holding image operations
   */
  public void addFeatures(Features features) {
    for (List<String> button : createButtonMap()) {
      String label = button.get(0);
      String command = button.get(1);
      add(createButton(label, command, features));
    }
    this.updateUI();
    this.setVisible(true);
  }

  private JButton createButton(String label, String command, Features features) {
    JButton button = new JButton(label);
    button.setActionCommand(command);
    button.addActionListener(evt -> {
      try {
        features.transform(evt);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error when transforming");
      }
    });
    return button;
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