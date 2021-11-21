package hw4.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import hw4.controller.HistogramRGB;
import hw4.controller.ImageUtil;
import hw4.controller.functions.BlueComponentFunction;
import hw4.controller.functions.BlurFunction;
import hw4.controller.functions.BrightenFunction;
import hw4.controller.functions.DarkenFunction;
import hw4.controller.functions.GreenComponentFunction;
import hw4.controller.functions.GreyScaleFunction;
import hw4.controller.functions.HorizontalFlipFunction;
import hw4.controller.functions.ImageFunction;
import hw4.controller.functions.IntensityComponentFunction;
import hw4.controller.functions.LoadFunction;
import hw4.controller.functions.LumaComponentFunction;
import hw4.controller.functions.RedComponentFunction;
import hw4.controller.functions.SaveFunction;
import hw4.controller.functions.SepiaFunction;
import hw4.controller.functions.SharpenFunction;
import hw4.controller.functions.ValueComponentFunction;
import hw4.controller.functions.VerticalFlipFunction;
import hw4.model.ImageModel;

public class ImageGUIView extends JFrame{

  private ImageModel model;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  private JPanel imagePanel;

  public ImageGUIView() {
    super();
    setTitle("Image Processor");
    setSize(400, 400);
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    model = new ImageModel();
    JScrollPane imageScrollPane;

    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);

    //histogram
    histogramPanel = new HistogramRGB(model);
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.X_AXIS));

    ImageFunctionPanel imageFunctionPanel = new ImageFunctionPanel(
            createButtonMap(), createFunctions(),
            model, imageLabel, histogramPanel);
    FileFunctionPanel fileFunctionPanel = new FileFunctionPanel(model, imageLabel, histogramPanel);
    mainPanel.add(fileFunctionPanel);
    mainPanel.add(imageFunctionPanel);
    mainPanel.add(imagePanel);
    imagePanel.add(histogramPanel);
  }

  private ArrayList<ImageFunction> createFunctions() {
    ArrayList<ImageFunction> functions = new ArrayList();
    functions.add(new SaveFunction());
    functions.add(new LoadFunction());
    functions.add(new BrightenFunction());
    functions.add(new DarkenFunction());
    functions.add(new HorizontalFlipFunction());
    functions.add(new VerticalFlipFunction());
    functions.add(new RedComponentFunction());
    functions.add(new GreenComponentFunction());
    functions.add(new BlueComponentFunction());
    functions.add(new LumaComponentFunction());
    functions.add(new ValueComponentFunction());
    functions.add(new IntensityComponentFunction());
    functions.add(new BlurFunction());
    functions.add(new SharpenFunction());
    functions.add(new GreyScaleFunction());
    functions.add(new SepiaFunction());
    return functions;
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
