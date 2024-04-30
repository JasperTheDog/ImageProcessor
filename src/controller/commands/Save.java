package controller.commands;

import model.ImageProcessor;

/**
 * A class to represent exporting a file from our stored images in our ImageProcessor model onto
 * the current system.
 */
public class Save implements ProcessorCommand {

  private final String filePath;
  private final String imageName;

  private final ExtensionHandler extensionHandler;

  /**
   * Constructs a Save object that takes in a filePath location to export to, and an imageName of
   * the image to be exported from this application.
   *
   * @param filePath  represents the filePath location to export to.
   * @param imageName represents the name of the image being exported.
   */
  public Save(String filePath, String imageName) {
    this.filePath = filePath;
    this.imageName = imageName;
    this.extensionHandler = new ExtensionHandler();
  }

  @Override
  public void process(ImageProcessor processor) {
    this.extensionHandler.getImageConverter(this.filePath)
            .convertExport(processor.getImage(this.imageName), this.filePath);
  }
}
