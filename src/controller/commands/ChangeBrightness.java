package controller.commands;

import model.ImageProcessor;

/**
 * A class to represent a command which changes the brightness. It takes in a value
 * and an image name and changes the image's brightness by that value. It saves the new image
 * with the given assigned name.
 */
public class ChangeBrightness implements ProcessorCommand {
  private final int value;
  private final String imageName;
  private final String assignName;

  private final String maskName;

  /**
   * A constructor to build an object of this class. It takes in values from the controller which
   * are the value by which the image's brightness needs to increase (or decrease), the image name,
   * and the new name for the new image.
   *
   * @param value      the value by which the brightness changes
   * @param imageName  the name of the image to be changed
   * @param assignName the name to save the new image under
   */
  public ChangeBrightness(int value, String imageName, String assignName) {
    this.value = value;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = "";
  }

  /**
   * A constructor to build an object of this class. It takes in values from the controller which
   * are the value by which the image's brightness needs to increase (or decrease), the image name,
   * and the new name for the new image.
   *
   * @param value      the value by which the brightness changes
   * @param imageName  the name of the image to be changed
   * @param assignName the name to save the new image under
   * @param maskName   represents the name of the mask image
   */
  public ChangeBrightness(int value, String imageName, String assignName, String maskName) {
    this.value = value;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = maskName;
  }

  @Override
  public void process(ImageProcessor processor) {
    if (maskName.equals("")) {
      processor.changeBrightness(this.value, this.imageName, this.assignName);
    } else {
      processor.changeBrightness(this.value, this.imageName, this.assignName, this.maskName);
    }
  }

}
