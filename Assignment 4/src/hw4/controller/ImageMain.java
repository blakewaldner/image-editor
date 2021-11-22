package hw4.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents the main class. It is used to run the controller with the appropriate
 * parameters.
 */
public class ImageMain {

  /**
   * The main function takes in input from the terminal and runs startProcess.
   *
   * @param args is input from the terminal.
   * @throws IOException when ImageController throws an IOException.
   */

  public static void main(String[] args) throws IOException {
    Readable read = null;
    //checks if any command line arguments given
    if (args.length > 0) {
      if (args[0].equalsIgnoreCase("-file")) {
        if (args.length > 1) {
          try {
            //attempts to load script file with argument
            File file = new File(args[1]);
            read = new FileReader(file);
          } catch (FileNotFoundException e) {
            System.out.println("Error loading script file from arguments");
          }
        } else {
          System.out.println("Error loading script file from arguments");
        }
      } else if (args[0].equalsIgnoreCase("-text")) {
        //if -text arg, then interactive text mode
        read = new InputStreamReader(System.in);
      }
      if (read != null) {
        //creates controller
        ImageController controller = new ImageControllerImpl(read, System.out);
        controller.startProcess();
      }
    } else {
      //default case, no args = run gui
      ImageController controller = new ImageControllerImpl();
      controller.startProcess();
    }
  }
}
