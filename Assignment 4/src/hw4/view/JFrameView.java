package hw4.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import hw4.controller.ImageUtil;
import hw4.model.Image;

public class JFrameView extends JFrame implements GuiView{
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
    imageFunctionPanel = new ImageFunctionPanel( imageLabel, histogramPanel);
    fileFunctionPanel = new FileFunctionPanel( imageLabel, histogramPanel);

    imagePanel.add(histogramPanel);
    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  @Override
  public void addFeatures(Features a) {
    imageFunctionPanel.addFeatures(a);
    fileFunctionPanel.addFeatures(a);
  }

  @Override
  public void setImageIcon(ImageIcon b) {
    imageLabel.setIcon(b);
  }

  @Override
  public void repaintHistogram() {
    histogramPanel.repaint();
    histogramPanel.revalidate();
  }

  public void setImage(Image image){
    histogramPanel.setImage(image);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message);
  }
}
