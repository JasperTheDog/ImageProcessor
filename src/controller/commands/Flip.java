package controller.commands;

import model.ImageProcessor;

/**
 * A class to represent a flip command. It supports both horizontal and vertical flipping. Flips
 * given image with given imageName, and assigns it the name assignName.
 */
public class Flip implements ProcessorCommand {

  private final String imageName;
  private final String assignName;
  private final String flipType;

  /**
   * A constructor to make an object of the Flip class. It takes in the imageName, assignName, and
   * flipType from the controller.
   *
   * @param imageName  the name of the image to be flipped
   * @param assignName the name under which the new image needs to be saved
   * @param flipType   the type of flip to be made (vertical or horizontal)
   */
  public Flip(String flipType, String imageName, String assignName) {
    this.flipType = flipType;
    this.imageName = imageName;
    this.assignName = assignName;
  }

  @Override
  public void process(ImageProcessor processor) {
    if (this.flipType.equalsIgnoreCase("vertical")) {
      processor.flipVert(this.imageName, this.assignName);
    } else if (this.flipType.equalsIgnoreCase("horizontal")) {
      processor.flipHoriz(this.imageName, this.assignName);
    } else {
      throw new IllegalArgumentException("flip type not supported!");
    }
  }
}
