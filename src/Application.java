import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.GUIController;
import controller.ImageProcessorController;
import controller.TextImageProcessorControllerImpl;
import model.ImageProcessor;
import model.ImageProcessorView;
import model.ProcessorImpl;
import model.ProcessorViewImpl;
import view.gui.ImageGUI;
import view.TextImageView;

/**
 * Program to run an Image Processor.
 */
public class Application {

  /**
   * Main method to run Image Processor.
   *
   * @param args represents the command line arguments. Valid arguments are -file (filePath)
   */
  public static void main(String[] args) {
    ImageProcessorController controller;
    ImageProcessor processor = new ProcessorImpl();
    ImageProcessorView modelView = new ProcessorViewImpl(processor);

    int argLength = args.length;

    if (argLength == 0) {
      controller = new GUIController(processor, new ImageGUI(modelView));
    } else if (argLength == 1 && args[0].equals("-text")) {
      controller = new TextImageProcessorControllerImpl(processor,
              new TextImageView(System.out, modelView),
              new InputStreamReader(System.in));
    } else if (argLength == 2 && args[0].equals("-file")) {
      Readable r;
      try {
        r = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("file not found!");
      }
      controller = new TextImageProcessorControllerImpl(processor,
              new TextImageView(System.out, modelView), r);
    } else {
      throw new IllegalArgumentException("unsupported command line arguments!");
    }
    controller.runProcessor();
  }
}
