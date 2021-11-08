package hw4;

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

    Readable read;
    //checks if any command line arguments given, inserts file with commands into code if there is
    if (args.length > 0) {
      try {
        //attempts to create file with argument
        File file = new File(args[0]);
        read = new FileReader(file);
      } catch (FileNotFoundException e) {
        //invalid file or no file, defaults to system.in for input
        System.out.println("Error loading file from configuration, defaulting to System.in");
        read = new InputStreamReader(System.in);
      }
    } else {
      read = new InputStreamReader(System.in);
    }

    //creates controller, defaults appendable to system.out currently
    ImageController controller = new ImageControllerImpl(read, System.out);
    controller.startProcess();

  }

}
