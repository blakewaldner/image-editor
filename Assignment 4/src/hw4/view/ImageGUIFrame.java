package hw4.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import hw4.model.ImageModel;

/**
 * This class represents the main JFrame that holds all panels for displaying the image
 * processor's file functions, image functions, current image, and histogram.
 */
public class ImageGUIFrame extends JFrame {

  private ImageModel model;

  private JScrollPane mainScrollPane;
  private JScrollPane imageScrollPane;

  private JLabel imageLabel;

  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JPanel imageFunctionPanel;
  private JPanel fileFunctionPanel;

  /**
   * Constructs the window that holds the GUI.
   * @param view view for rendering error messages
   */
  public ImageGUIFrame(ImageGUIView view) {
    super();
    setTitle("Image Processor");
    setSize(1080, 1080);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    imageLabel = new JLabel();

    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imageScrollPane = new JScrollPane(imageLabel);
    imagePanel.add(imageScrollPane);

    model = new ImageModel();

    histogramPanel = new HistogramPanel();
    //imageFunctionPanel = new ImageFunctionPanel(model, imageLabel, histogramPanel, view);
    //fileFunctionPanel = new FileFunctionPanel(model, imageLabel, histogramPanel, view);

    imagePanel.add(histogramPanel);
    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);
  }
}
/*
package hw4.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import hw4.model.ImageModel;

public class ImageGUIFrame extends JFrame {

  private ImageModel model;

  private JScrollPane mainScrollPane;
  private JScrollPane imageScrollPane;

  private JLabel imageLabel;

  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel histogramPanel;
  private JPanel imageFunctionPanel;
  private JPanel fileFunctionPanel;

  public ImageGUIFrame(ImageGUIView view) {
    super();
    setTitle("Image Processor");
    setSize(1080, 1080);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    imageLabel = new JLabel();

    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imageScrollPane = new JScrollPane(imageLabel);
    imagePanel.add(imageScrollPane);

    model = new ImageModel();

    histogramPanel = new HistogramPanel(model);
    imageFunctionPanel = new ImageFunctionPanel(model, imageLabel, histogramPanel, view);
    fileFunctionPanel = new FileFunctionPanel(model, imageLabel, histogramPanel, view);

    imagePanel.add(histogramPanel);
    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);
  }
}
 */
