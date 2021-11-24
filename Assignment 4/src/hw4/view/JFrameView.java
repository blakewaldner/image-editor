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

public class JFrameView extends JFrame implements GuiView {
  private JPanel mainPanel;
  private JPanel imagePanel;
  private HistogramPanel histogramPanel;
  private ImageFunctionPanel imageFunctionPanel;
  private FileFunctionPanel fileFunctionPanel;
  private JScrollPane mainScrollPane;
  private JScrollPane imageScrollPane;
  private JLabel imageLabel;

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

    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);
    imagePanel.add(histogramPanel);

    mainPanel.updateUI();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  @Override
  public void addFeatures(Features features) {
    imageFunctionPanel.addFeatures(features);
    fileFunctionPanel.addFeatures(features);
  }

  @Override
  public void setImageIcon(ImageIcon icon) {
    imageLabel.setIcon(icon);
  }

  @Override
  public void repaintHistogram() {
    histogramPanel.repaint();
  }

  public void setImage(Image image){
    histogramPanel.setImage(image);
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
