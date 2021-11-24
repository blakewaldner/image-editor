package hw4.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import hw4.model.ImageModelInterface;
import hw4.view.ImageTextView;
import hw4.controller.functions.ImageFunction;
import hw4.model.ImageModel;
import hw4.view.ImageViewInterface;

/**
 * ImageControllerImpl is responsible for taking in input and deciding what to execute
 * in ImageModel.
 */
public class ImageControllerImpl implements ImageController {
  private Readable in;
  private Appendable out;
  private final ImageModelInterface model;

  /**
   * Constructor for ImageControllerImpl. It initializes its own ImageModel.
   *
   * @param in  is the input.
   * @param out represents the output used in ImageTextView.
   */
  public ImageControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters in controller");
    }
    this.in = in;
    this.out = out;
    this.model = new ImageModel();
  }

  /**
   * Constructor for ImageControllerImpl. It takes in an ImageModel that it then modifies.
   *
   * @param in    is the input.
   * @param out   is the output used in ImageTextView.
   * @param model is an ImageModel that it uses to execute methods.
   */
  public ImageControllerImpl(Readable in, Appendable out, ImageModel model) {
    if (in == null || out == null || model == null) {
      throw new IllegalArgumentException("Invalid parameters in controller");
    }
    this.in = in;
    this.out = out;
    this.model = model;
  }

  /**
   * This function takes in commands to modify the list of Images in ImageModel and reads
   * from in. It displays an error message when the user types in an incorrect command but
   * does not quit when it happens. The controller also initializes ImageTextView to display
   * the error messages. If no command line arguments are passed, runs the program in GUI
   * mode.
   *
   * @throws IOException when the String can not be formatted in ImageTextView.
   */
  public void startProcess() throws IOException {
    Scanner scanner = new Scanner(this.in);
    ImageViewInterface view = new ImageTextView(out);
    while (scanner.hasNext()) {
      //gets the first argument of command line, ("save", "load", etc)
      String firstArg = scanner.next();

      //sets a list of allowed commands that perform functions
      ArrayList<ImageFunction> functions = ImageUtil.createFunctions();
      boolean didCommand = false;
      //checks if user's command is valid command
      for (int i = 0; i < functions.size(); i++) {
        if (functions.get(i).getCommand().equals(firstArg)) {
          try {
            //does command if valid
            didCommand = true;
            functions.get(i).doFunction(model, scanner);
            view.renderMessage("Successfully recognized " +
                    functions.get(i).getCommand() + " command\n");
            //valid command, but invalid arguments for command
          } catch (IllegalArgumentException e1) {
            view.renderMessage("Invalid Arguments: " + e1.getMessage() + "\n");
          }
          //valid command, but invalid filepath as argument
          catch (FileNotFoundException e2) {
            view.renderMessage(e2.getMessage() + "\n");
          }
        }
        //invalid command, couldn't find
        if (!didCommand && i == functions.size() - 1) {
          view.renderMessage("Invalid Command\n");
        }
      }
    }
  }
}

