package hw4.view;

import javax.swing.*;

public class RunGUI {

  public static void main(String [] args) {
    ImageGUIView.setDefaultLookAndFeelDecorated(false);
    ImageGUIView frame = new ImageGUIView();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

}
