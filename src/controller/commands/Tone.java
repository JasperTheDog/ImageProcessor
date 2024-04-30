package controller.commands;


import model.ImageProcessor;
import model.defaults.Transformations;

/**
 * A class to represent the command for changing tone of an object using a color transformation.
 * It takes in a tone type, and applies that transformation to the image with given imageName.
 * Saving a new image with given assignName.
 */
public class Tone implements ProcessorCommand {

  private final String imageName;
  private final String assignName;
  private final String toneType;

  private String maskName;

  /**
   * Constructs a tone command object using toneType, the name of the image and the new image's
   * name after applying color transformation.
   *
   * @param toneType   represents the type of tone.
   * @param imageName  represents the name of the image being transformed.
   * @param assignName represents the name of the image being assigned.
   */
  public Tone(String toneType, String imageName, String assignName) {
    this.toneType = toneType;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = "";
  }

  /**
   * Constructs a tone command object using toneType, the name of the image and the new image's
   * name after applying color transformation.
   *
   * @param toneType   represents the type of tone.
   * @param imageName  represents the name of the image being transformed.
   * @param assignName represents the name of the image being assigned.
   * @param maskName   represents the name of the mask image.
   */
  public Tone(String toneType, String imageName, String assignName, String maskName) {
    this.toneType = toneType;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = maskName;
  }


  @Override
  public void process(ImageProcessor processor) {
    double[][] transformer;
    if (this.toneType.equals("sepia")) {
      transformer = Transformations.SEPIA;
    } else if (this.toneType.equals("greyscale")) {
      transformer = Transformations.GREYSCALE;
    } else {
      throw new IllegalArgumentException("bad colorType give");
    }

    if (this.maskName.equals("")) {
      processor.colorTransform(this.imageName, this.assignName, transformer);
    } else {
      processor.colorTransform(this.imageName, this.assignName, this.maskName, transformer);
    }
  }
}
