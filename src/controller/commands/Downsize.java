package controller.commands;

import model.ImageProcessor;

/**
 * A class to represent a command that causes an image to Downsize based off its imageName,
 * its new assignName and its widthRatio and heightRatio.
 */
public class Downsize implements ProcessorCommand {

  private final String imageName;

  private final String assignName;

  private final double widthRatio;

  private final double heightRatio;

  /**
   * Constructs an Downsize object with given imageName, assignName, widthRatio, heightRatio.
   *
   * @param imageName   represents the name of the image being downsized.
   * @param assignName  represents the name of the assignName of new image.
   * @param widthRatio  represents the widthRatio being applied.
   * @param heightRatio represents the heightRatio being applied.
   */
  public Downsize(String imageName, String assignName, double widthRatio, double heightRatio) {
    this.imageName = imageName;
    this.assignName = assignName;
    this.widthRatio = widthRatio;
    this.heightRatio = heightRatio;
  }


  @Override
  public void process(ImageProcessor processor) {
    processor.downsize(widthRatio, heightRatio, imageName, assignName);
  }
}
