package hw4.view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

public interface Features {
  void save(JPanel panel) throws IOException;
  void open(JPanel panel);
  void transform(ActionEvent arg0) throws IOException;
}
