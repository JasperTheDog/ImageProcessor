package controller.commands;

import model.ImageProcessor;
import model.defaults.Filters;

/**
 * A class to represent the command for filtering an object using a kernel.  It takes
 * in a filter type, and applies that filter to the image with given imageName. Saving a new image
 * with given assignName.
 */
public class Filter implements ProcessorCommand {

  private final String filterType;
  private final String imageName;
  private final String assignName;

  private final String maskName;

  /**
   * Constructs a Filter object given a filter type, imageName, and assignName.
   *
   * @param filterType represents the type of filter.
   * @param imageName  represents the name of the image being filtered.
   * @param assignName represents the name of the image.
   */
  public Filter(String filterType, String imageName, String assignName) {
    this.filterType = filterType;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = "";
  }

  /**
   * Constructs a Filter object given a filter type, imageName, and assignName.
   *
   * @param filterType represents the type of filter.
   * @param imageName  represents the name of the image being filtered.
   * @param assignName represents the name of the image.
   * @param maskName   represents the name of the mask.
   */
  public Filter(String filterType, String imageName, String assignName, String maskName) {
    this.filterType = filterType;
    this.imageName = imageName;
    this.assignName = assignName;
    this.maskName = maskName;
  }

  @Override
  public void process(ImageProcessor processor) {
    double[][] filter;
    if (this.filterType.equals("blur")) {
      filter = Filters.BLUR;
    } else if (this.filterType.equals("sharpen")) {
      filter = Filters.SHARPEN;
    } else {
      throw new IllegalArgumentException("filter type not supported");
    }
    if (this.maskName.equals("")) {
      processor.filter(this.imageName, assignName, filter);
    } else {
      processor.filter(this.imageName, assignName, this.maskName, filter);
    }
  }

}
