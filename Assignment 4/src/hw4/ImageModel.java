package hw4;

import java.util.ArrayList;

/**
 * This class represents a model for the image project. The model is responsible for the methods
 * used in ImageControllerImpl.
 */
public class ImageModel implements ImageModelInterface {
  //Arraylist of images stored in the model. It intially has zero stored Images.
  private final ArrayList<Image> imgList;

  /**
   * Constructor for ImageModel. It initially has zero stored Images.
   */
  public ImageModel() {
    imgList = new ArrayList<>();
  }

  /**
   * This function adds an Image to the ArrayList.
   *
   * @param image is the image to be saved.
   */
  public void save(Image image) {
    imgList.add(image);
    for (int i = 0; i < imgList.size() - 1; i++) {
      if (imgList.get(i).getName().equals(image.getName())) {
        imgList.remove(i);
      }
    }
  }

  /**
   * This function iterates through imgList to find the Image with the given name.
   *
   * @param name is the name of the Image.
   * @throws IllegalArgumentException when the Image can not be found.
   * @returns a String.
   */
  public Image getImageByName(String name) throws IllegalArgumentException {
    for (Image img : imgList) {
      if (img.getName().equals(name)) {
        return img;
      }
    }
    throw new IllegalArgumentException("No image found with given name");
  }

  /**
   * This returns a copy of imgList.
   *
   * @returns an Arraylist.
   */

  public ArrayList<Image> getImgList() {
    ArrayList<Image> imgList2 = new ArrayList<>();

    for (int i = 0; i < imgList.size(); i++) {
      imgList2.add(new Image(imgList.get(i).getImg(), imgList.get(i).getName(),
              imgList.get(i).getMax()));
    }
    return imgList2;
  }
}
