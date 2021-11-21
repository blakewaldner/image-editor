package hw4.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents the main class. It is used to run the program startProcess.
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
    //checks if any command line arguments given, inserts file with commands into code if there is
    if (args.length > 0) {
      if(args[0].equalsIgnoreCase("-file")) {
        if (args.length > 1) {
          try {
            //attempts to create file with argument
            File file = new File(args[1]);
            read = new FileReader(file);
          } catch (FileNotFoundException e) {
            //invalid file or no file, defaults to system.in for input
            System.out.println("Error loading file from configuration, defaulting to System.in");
            read = new InputStreamReader(System.in);
          }
        }
      } else if (args[0].equalsIgnoreCase("-text")) {
        read = new InputStreamReader(System.in);
      }
      if(read != null) {
        //creates controller, defaults appendable to system.out currently
        ImageController controller = new ImageControllerImpl(read, System.out);
        controller.startProcess();
      }
    } else {
      //TODO: default case, run gui
      ImageController controller = new ImageControllerImplGUI();
      controller.startProcess();
    }

   //TODO:
   /*
   Any other command-line arguments are invalid:
   in these cases the program should display an error message suitably and quit.
    */

  }
}
