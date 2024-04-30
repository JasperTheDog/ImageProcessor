package controller.commands;

import model.ImageProcessor;

/**
 * A class to represent a loading command. Where we load in a file from given file path
 * and using the correct imageConverter based on its extension type we convert it and store the
 * image as a Picture object.
 */
public class Load implements ProcessorCommand {

  private final String filePath;
  private final String assignName;

  private final ExtensionHandler extensionHandler;

  /**
   * Constructs a Load object with a given filePath to load form and an assignName to call the
   * imported image.
   *
   * @param filePath   represents the filePath of the image being imported.
   * @param assignName represents the name of the image after importing.
   */
  public Load(String filePath, String assignName) {
    this.filePath = filePath;
    this.assignName = assignName;
    this.extensionHandler = new ExtensionHandler();
  }

  @Override
  public void process(ImageProcessor processor) {
    processor.load(assignName,
            this.extensionHandler.getImageConverter(this.filePath).convertImport(this.filePath));
  }

}
