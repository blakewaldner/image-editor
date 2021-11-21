package hw4.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import hw4.controller.functions.ImageFunction;

public class ImageFunctionPanel extends JPanel implements ActionListener {

  private ArrayList<ImageFunction> functions;

  public ImageFunctionPanel(List<List<String>> buttonMap, ArrayList<ImageFunction> functions) {
    setBorder(BorderFactory.createTitledBorder("Image functions"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.functions = functions;
    for(List<String> button : buttonMap) {
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

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    switch (arg0.getActionCommand()) {
      break;
      case "brighten":
      case "darken": {
        for (int i = 0; i < functions.size(); i++) {
          if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
            try {
              functions.get(i).doFunction(model, new Scanner(new StringReader(
                      JOptionPane.showInputDialog("Change image by how much? " +
                              "(Answer must be number)") + "\nimage\nimage")));
            } catch (IOException e) {
              e.printStackTrace();
            }
            updateImageIcon();
            i = functions.size();
          }
        }
      }
      break;
      default: {
        for (int i = 0; i < functions.size(); i++) {
          if (functions.get(i).getCommand().equals(arg0.getActionCommand())) {
            try {
              functions.get(i).doFunction(model, new Scanner(new StringReader("image\nimage")));
            } catch (IOException e) {
              e.printStackTrace();
            }
            updateImageIcon();
            i = functions.size();
          }
        }
      }
      break;
    }
  }
}
