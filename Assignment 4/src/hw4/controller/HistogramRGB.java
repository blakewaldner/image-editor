package hw4.controller;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import hw4.model.Image;

public class HistogramRGB extends JPanel {
  Image image;
  int[] red = new int[256];
  int[] green = new int[256];
  int[] blue = new int[256];
  int[] intensity = new int[256];

  public HistogramRGB(Image image){
    if(image == null){
      throw new IllegalArgumentException("Image is null");
    }
    this.setPreferredSize(new Dimension(500,500));
    this.image = image;
    for(int i = 0; i < red.length; i ++){
      red[i] = 0;
      green[i] = 0;
      blue[i] = 0;
      intensity[i] = 0;
    }
    red = redValues();
    blue = blueValues();
    green = greenValues();
    intensity = intensityValues();

  }

  public int[] redValues(){
    for(int i = 0; i <  image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        red[image.getPixel(i,j).getRed()]++;
      }
    }
    return red;
  }

  public int[] blueValues(){
    for(int i = 0; i <  image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        blue[image.getPixel(i,j).getBlue()]++;
      }
    }
    return blue;
  }

  public int[] greenValues(){
    for(int i = 0; i <  image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        green[image.getPixel(i,j).getGreen()]++;
      }
    }
    return green;
  }

  public int[] intensityValues(){
    for(int i = 0; i <  image.getHeight(); i ++){
      for(int j = 0; j < image.getWidth(); j ++){
        intensity[(image.getPixel(i,j).getGreen() + image.getPixel(i,j).getRed()
                + image.getPixel(i,j).getBlue())/3]++;
      }
    }
    return intensity;
  }

  public void paint(Graphics g){
    for(int i = 0; i < red.length - 1; i ++ ){
      g.setColor(Color.RED);
      g.drawLine(i,red[i]/5,i+1,red[i+1]/5);

      g.setColor(Color.GREEN);
      g.drawLine(i,green[i]/5,i+1,green[i+1]/5);

      g.setColor(Color.BLUE);
      g.drawLine(i,blue[i]/5,i+1,blue[i+1]/5);

      g.setColor(Color.BLACK);
      g.drawLine(i,intensity[i]/5,i+1,intensity[i+1]/5);
    }
  }


}
