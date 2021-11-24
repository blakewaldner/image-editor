package hw4.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hw4.controller.Features;
import hw4.model.Image;
import hw4.view.JPanels.FileFunctionPanel;
import hw4.view.JPanels.HistogramPanel;
import hw4.view.JPanels.ImageFunctionPanel;

/**
 * This class represents the main JFrame that holds all panels for displaying the image
 * processor's file functions, image functions, current image, and histogram.
 */
public class JFrameView extends JFrame implements GuiView {
  private JPanel mainPanel;
  private JPanel imagePanel;
  private HistogramPanel histogramPanel;
  private ImageFunctionPanel imageFunctionPanel;
  private FileFunctionPanel fileFunctionPanel;
  private JScrollPane mainScrollPane;
  private JScrollPane imageScrollPane;
  private JLabel imageLabel;

  /**
   * Constructs the window that holds the GUI/all panels.
   */
  public JFrameView() {
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

    histogramPanel = new HistogramPanel();
    imageFunctionPanel = new ImageFunctionPanel();
    fileFunctionPanel = new FileFunctionPanel();

    imagePanel.add(histogramPanel);
    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel.updateUI();
    setVisible(true);
  }

  /**
   * Adds features to the buttons in the GUI's panels that adds action listeners
   * to buttons for them to perform image/file functions on current image.
   *
   * @param features features object holding image operations
   */
  @Override
  public void addFeatures(Features features) {
    imageFunctionPanel.addFeatures(features);
    fileFunctionPanel.addFeatures(features);
  }

  /**
   * Changes the displayed image of GUI.
   *
   * @param icon new icon displaying image
   */
  @Override
  public void setImageIcon(ImageIcon icon) {
    imageLabel.setIcon(icon);
  }

  /**
   * Updates the histogram to display histogram of currently displayed image.
   */
  @Override
  public void repaintHistogram() {
    histogramPanel.repaint();
  }

  /**
   * Updates the image used by histogram to new image.
   *
   * @param image new image to get histogram values from
   */
  public void setImage(Image image) {
    histogramPanel.setImage(image);
  }

  /**
   * Renders a message in a GUI message box.
   *
   * @param message is the message to be rendered.
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
