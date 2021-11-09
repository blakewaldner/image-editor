package hw4.model;

import java.util.ArrayList;

import hw4.model.Image;

/**
 * This class represents the functionality for ImageModels.
 */
public interface ImageModelInterface {
  /**
   * This function saves an Image object to the model.
   *
   * @param image is the image to be saved.
   */
  void save(Image image);

  /**
   * This function gets an image by its name.
   *
   * @param name is the name of the Image.
   * @throws IllegalArgumentException when the Image can not be found.
   * @returns an Image.
   */
  Image getImageByName(String name) throws IllegalArgumentException;

  /**
   * This function retuns the list of object Images stored in ImageModel.
   *
   * @returns an Arraylist of Images.
   */
  ArrayList<Image> getImgList();

}
