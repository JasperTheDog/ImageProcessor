package controller.commands;

import java.util.Locale;
import java.util.function.Function;

import model.ImageProcessor;
import model.defaults.GreyScaleMaps;
import state.color.PixelColor;

/**
 * A Class to represent doing common grey-Scaleing commands. Supports grey-scaling by:
 * red, green, blue components and
 * intensity, value, and luma components.
 */
public class GreyScale implements ProcessorCommand {

  private final String imageName;
  private final String assignName;
  private final String gsType;

  private final String maskName;

  /**
   * Constructs a GreyScale object that takes in the image name to be grey-scaled, the new name
   * of the new image, and the string version of the type of grey-scaling.
   *
   * @param imageName  represents the name of the image to be grey-scaled.
   * @param assignName represents the new name of the grey-scaled image.
   * @param gsType     represents the component in which to greyScale by.
   */
  public GreyScale(String gsType, String imageName, String assignName) {
    this.gsType = gsType.toLowerCase(Locale.ROOT);
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = "";
  }

  /**
   * Constructs a GreyScale object that takes in the image name to be grey-scaled, the new name
   * of the new image, and the string version of the type of grey-scaling.
   *
   * @param imageName  represents the name of the image to be grey-scaled.
   * @param assignName represents the new name of the grey-scaled image.
   * @param gsType     represents the component in which to greyScale by.
   * @param maskName   represents the name of the mask
   */
  public GreyScale(String gsType, String imageName, String assignName, String maskName) {
    this.gsType = gsType.toLowerCase(Locale.ROOT);
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = maskName;
  }

  @Override
  public void process(ImageProcessor processor) {
    Function<PixelColor, PixelColor> func;
    switch (this.gsType) {
      case "red":
        func = GreyScaleMaps.RED;
        break;
      case "green":
        func = GreyScaleMaps.GREEN;
        break;
      case "blue":
        func = GreyScaleMaps.BLUE;
        break;
      case "intensity":
        func = GreyScaleMaps.INTENSITY;
        break;
      case "value":
        func = GreyScaleMaps.VALUE;
        break;
      case "luma":
        func = GreyScaleMaps.LUMA;
        break;
      default:
        throw new IllegalArgumentException("Given greyScale not valid");
    }
    if (this.maskName.equals("")) {
      processor.greyScale(this.imageName, this.assignName, func);
    } else {
      processor.greyScale(this.imageName, this.assignName, maskName, func);
    }
  }

}
