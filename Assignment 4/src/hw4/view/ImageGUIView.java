package hw4.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

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
import hw4.model.Image;
import hw4.model.ImageModel;

public class ImageGUIView extends JFrame implements ActionListener {

  private ImageModel model;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  private Graphics g;

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
    //TODO: DEFAULT IMAGE HARD CODED IN
    model.save(ImageUtil.readFile("res/dog.png", "image"));

    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.Y_AXIS));
    mainPanel.add(dialogBoxesPanel);


    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);

    //button panel
    JPanel messageDialogPanel = new JPanel();
    messageDialogPanel.setBorder(BorderFactory.createTitledBorder("Image functions"));
    messageDialogPanel.setLayout(new BoxLayout(messageDialogPanel, BoxLayout.Y_AXIS));
    mainPanel.add(messageDialogPanel);


    //button blur
    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    blurButton.addActionListener(this);
    messageDialogPanel.add(blurButton);

    //button flip h
    JButton flipHButton = new JButton("Flip Horizontal");
    flipHButton.setActionCommand("horizontal-flip");
    flipHButton.addActionListener(this);
    messageDialogPanel.add(flipHButton);

    //button flip v
    JButton flipVButton = new JButton("Flip Vertical");
    flipVButton.setActionCommand("vertical-flip");
    flipVButton.addActionListener(this);
    messageDialogPanel.add(flipVButton);

    //button sepia
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(this);
    messageDialogPanel.add(sepiaButton);

    //button greyscale
    JButton greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(this);
    messageDialogPanel.add(greyscaleButton);

    //button sharpen
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener(this);
    messageDialogPanel.add(sharpenButton);

    //button red comp
    JButton redComponentButton = new JButton("Red Component");
    redComponentButton.setActionCommand("red-component");
    redComponentButton.addActionListener(this);
    messageDialogPanel.add(redComponentButton);

    //button green comp
    JButton greenComponentButton = new JButton("Green Component");
    greenComponentButton.setActionCommand("green-component");
    greenComponentButton.addActionListener(this);
    messageDialogPanel.add(greenComponentButton);

    //button blue comp
    JButton blueComponentButton = new JButton("Blue Component");
    blueComponentButton.setActionCommand("blue-component");
    blueComponentButton.addActionListener(this);
    messageDialogPanel.add(blueComponentButton);

    //button luma comp
    JButton lumaComponentButton = new JButton("Luma Component");
    lumaComponentButton.setActionCommand("blue-component");
    lumaComponentButton.addActionListener(this);
    messageDialogPanel.add(lumaComponentButton);

    //button intensity comp
    JButton intensityComponentButton = new JButton("Intensity Component");
    intensityComponentButton.setActionCommand("intensity-component");
    intensityComponentButton.addActionListener(this);
    messageDialogPanel.add(intensityComponentButton);

    //button value comp
    JButton valueComponentButton = new JButton("Value Component");
    valueComponentButton.setActionCommand("value-component");
    valueComponentButton.addActionListener(this);
    messageDialogPanel.add(valueComponentButton);

    //button brighten
    JButton brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener(this);
    messageDialogPanel.add(brightenButton);

    //button brighten
    JButton darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("darken");
    darkenButton.addActionListener(this);
    messageDialogPanel.add(darkenButton);

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    //JLabel imageLabel;
    JScrollPane imageScrollPane;


    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);


    updateImageIcon();
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);

    //histogram
    histogramPanel = new HistogramRGB(model.getImageByName("image"));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.X_AXIS));
    imagePanel.add(histogramPanel);

    //this.setLocationRelativeTo(null);


  }

  private void openFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(ImageGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      model.save(ImageUtil.readFile(f.getAbsolutePath(), "image"));
      //TODO: change way image is displayed not through icon but image somehow
      imageLabel.setIcon(new ImageIcon(f.getAbsolutePath()));
    }
  }

  private void saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(ImageGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      //fileSaveDisplay.setText(f.getAbsolutePath());
    }
  }

  private void updateImageIcon() {
    Image image = model.getImageByName("image");
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        img.setRGB(i, j, image.getPixel(j, i).getRGB());
      }
    }
    imageLabel.setIcon(new ImageIcon(img));
  }

  /**
   * Invoked when an action occurs.
   *
   * @param arg0 the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {

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

    switch (arg0.getActionCommand()) {
      case "Open file": {
        openFile();
      }
      break;
      case "Save file": {
        saveFile();
      }
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
