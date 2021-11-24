package GUIControllerTest;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import hw4.controller.ImageControllerGUI;
import hw4.model.Image;
import hw4.model.ImageModel;
import hw4.model.ImageModelInterface;
import hw4.model.Pixel;
import hw4.view.JPanels.ImageFunctionPanel;

import static org.junit.Assert.assertEquals;

/**
 * Tests if the controller is calling the view correctly.
 */
public class ImageControllerGUITest {
  ImageModelInterface model;
  MockJFrameView a;
  ImageControllerGUI b;
  Image image;
  Pixel[][] img;
  public void initializer() {
    model = new ImageModel();
    Pixel one = new Pixel(4, 5, 6);
    Pixel two = new Pixel(3, 3, 3);
    Pixel three = new Pixel(6, 6, 7);
    Pixel four = new Pixel(8, 8, 8);

    img = new Pixel[2][2];

    img[0][0] = one;
    img[0][1] = two;
    img[1][0] = three;
    img[1][1] = four;

    image = new Image(img, "image");

     a = new MockJFrameView(new StringBuilder());
     b = new ImageControllerGUI(model, a);
  }
  @Test
  public void startProcess() {
    initializer();
    model.save(image);
    b.startProcess();
    assertEquals(a.out.toString(), "addFeatures\n");
  }

  @Test
  public void open() {
    initializer();
    model.save(image);
    b.open(new JPanel());
    assertEquals(a.out.toString(), "repaintHistogram\n");
  }

  @Test
  public void InvalidCommandTransform() {
    initializer();
    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"",0));
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(a.out.toString(), "renderMessage\n");
  }

  @Test
  public void transform() {
    initializer();
    model.save(image);
    b.startProcess();
    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"horizontal-flip",0));
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(a.out.toString(), "addFeatures\n" +
            "setImageIcon\n" +
            "setImage\n" +
            "repaintHistogram\n");
  }

  @Test
  public void testBlur(){
    model = new ImageModel();
    Pixel[][] img = new Pixel[3][3];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        img[i][j] = new Pixel(i * 8, i + j * 8, j * 8);
      }
    }

    image = new Image(img, "image");

    model.save(image);

    a = new MockJFrameView(new StringBuilder());
    b = new ImageControllerGUI(model, a);
    b.startProcess();

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"blur",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[3][3];

    img2[0][0] = new Pixel(1, 1, 1);
    img2[0][1] = new Pixel(1, 6, 6);
    img2[0][2] = new Pixel(1, 7, 7);
    img2[1][0] = new Pixel(6, 1, 1);
    img2[1][1] = new Pixel(8, 8, 8);
    img2[1][2] = new Pixel(6, 9, 9);
    img2[2][0] = new Pixel(7, 1, 1);
    img2[2][1] = new Pixel(9, 6, 6);
    img2[2][2] = new Pixel(7, 7, 7);

    Image image1 = new Image(img2, "image");


    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testGreyScale(){
    initializer();
    model.save(image);
    b.startProcess();

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"greyscale",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = new Pixel(4, 4, 4);
    img2[0][1] = new Pixel(3, 3, 3);
    img2[1][0] = new Pixel(6, 6, 6);
    img2[1][1] = new Pixel(8, 8, 8);
    Image image1 = new Image(img2, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testSepia(){
    initializer();
    model.save(image);
    b.startProcess();

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"sepia",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = new Pixel(6, 5, 4);
    img2[0][1] = new Pixel(4, 3, 2);
    img2[1][0] = new Pixel(8, 7, 5);
    img2[1][1] = new Pixel(10, 9, 7);
    Image image1 = new Image(img2, "image");

    assertEquals(model.getImageByName("image"),image1);
  }


  @Test
  public void testSharpen(){
    model = new ImageModel();
    Pixel[][] img = new Pixel[5][5];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        img[i][j] = new Pixel(i * 8, i + j * 8, j * 8);
      }
    }

    image = new Image(img, "image");

    model.save(image);

    a = new MockJFrameView(new StringBuilder());
    b = new ImageControllerGUI(model, a);
    b.startProcess();

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"sharpen",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[5][5];

    img2[0][0] = new Pixel(0, 0, 0);
    img2[0][1] = new Pixel(0, 1, 6);
    img2[0][2] = new Pixel(0, 11, 18);
    img2[0][3] = new Pixel(0, 37, 42);
    img2[0][4] = new Pixel(0, 35, 39);
    img2[1][0] = new Pixel(6, 0, 0);
    img2[1][1] = new Pixel(9, 4, 9);
    img2[1][2] = new Pixel(3, 18, 26);
    img2[1][3] = new Pixel(9, 54, 59);
    img2[1][4] = new Pixel(6, 47, 51);
    img2[2][0] = new Pixel(18, 0, 0);
    img2[2][1] = new Pixel(26, 2, 3);
    img2[2][2] = new Pixel(16, 12, 16);
    img2[2][3] = new Pixel(26, 46, 49);
    img2[2][4] = new Pixel(18, 40, 42);
    img2[3][0] = new Pixel(42, 1, 0);
    img2[3][1] = new Pixel(59, 16, 9);
    img2[3][2] = new Pixel(49, 31, 26);
    img2[3][3] = new Pixel(59, 64, 59);
    img2[3][4] = new Pixel(42, 55, 51);
    img2[4][0] = new Pixel(39, 2, 0);
    img2[4][1] = new Pixel(51, 13, 6);
    img2[4][2] = new Pixel(42, 25, 18);
    img2[4][3] = new Pixel(51, 48, 42);
    img2[4][4] = new Pixel(39, 44, 39);

    Image image1 = new Image(img2, "image");


    assertEquals(model.getImageByName("image"),image1);
  }

  @Test

  public void testFlipV(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"vertical-flip",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = l;
    img2[0][1] = k;
    img2[1][0] = i;
    img2[1][1] = j;
    Image image1 = new Image(img2, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testFlipH(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"horizontal-flip",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = j;
    img2[0][1] = i;
    img2[1][0] = k;
    img2[1][1] = l;
    Image image1 = new Image(img2, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testRedComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"red-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img2 = new Pixel[2][2];

    img2[0][0] = new Pixel(1, 1, 1);
    img2[0][1] = new Pixel(0, 0, 0);
    img2[1][1] = new Pixel(3, 3, 3);
    img2[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img2, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testGreenComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"green-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img1, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testBlueComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"blue-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(3, 3, 3);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(4, 4, 4);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img1, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testLumaComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"luma-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img1, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testIntensityComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"intensity-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(2, 2, 2);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img1, "image");

    assertEquals(model.getImageByName("image"),image1);
  }

  @Test
  public void testValueComponent(){
    initializer();
    model.save(image);
    b.startProcess();
    Pixel i = new Pixel(1, 2, 3);
    Pixel j = new Pixel(0, 0, 0);
    Pixel k = new Pixel(3, 3, 4);
    Pixel l = new Pixel(5, 5, 5);
    img[0][0] = i;
    img[0][1] = j;
    img[1][1] = k;
    img[1][0] = l;

    try {
      b.transform(new ActionEvent(new ImageFunctionPanel(),0,"value-component",0));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Pixel[][] img1 = new Pixel[2][2];

    img1[0][0] = new Pixel(1, 1, 1);
    img1[0][1] = new Pixel(0, 0, 0);
    img1[1][1] = new Pixel(3, 3, 3);
    img1[1][0] = new Pixel(5, 5, 5);
    Image image1 = new Image(img1, "image");

    assertEquals(model.getImageByName("image"),image1);
  }



}