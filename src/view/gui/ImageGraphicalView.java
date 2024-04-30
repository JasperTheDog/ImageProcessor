package view.gui;

import java.awt.event.ActionListener;

/**
 * This interface represents the operations that a Graphical view should offer for the
 * imageProcessor application.
 */
public interface ImageGraphicalView {


  /**
   * Provide the view with the actionListener so that proper operations occur when buttons
   * are pressed.
   *
   * @param actionListener represents the actionListener.
   */
  void acceptActionListener(ActionListener actionListener);

  /**
   * Returns the user entered assignName for the current image operation.
   * Also clears the user entered field, if applicable.
   *
   * @return the user entered assignName for the current image operation.
   */
  String getAssignName();

  /**
   * Returns the user entered flipType. Also clears the user entered field if applicable.
   *
   * @return the user entered flipType.
   */
  String getFlipType();

  /**
   * Returns the user entered filterType. Also clears the user entered field if applicable.
   *
   * @return the user entered filterType.
   */
  String getFilterType();

  /**
   * Returns the user entered toneType. Also clears the user entered field if applicable.
   *
   * @return the user entered toneType.
   */
  String getToneType();

  /**
   * Returns the user entered changeBrightnessValue.
   * Also clears the user entered field if applicable.
   *
   * @return the user entered changeBrightnessValue.
   */
  int getChangeBrightnessValue();

  /**
   * Returns the user entered greyScaleType. Also clears the user entered field if applicable.
   *
   * @return the user entered greyScaleType.
   */
  String getGreyscaleType();

  /**
   * Returns the user entered image name in list of images stored being processed.
   * Also clears the user entered field if applicable.
   *
   * @return the user entered image name out of list of images being processed.
   */
  String getSelectedImage();

  /**
   * Returns the name of current image being displayed by image processor.
   *
   * @return the name of current image being processed.
   */
  String getImageName();

  /**
   * Sets the current image being processed to be the image with given name in our image Processor.
   *
   * @param imageName represents the name of image to be currently processed.
   */
  void setImage(String imageName);

  /**
   * Adds image with given name to our list of images being processed.
   *
   * @param name the name of image.
   */
  void addToImageList(String name);

  /**
   * Renders an error message based on implementation.
   *
   * @param message represents the message to be displayed.
   */
  void renderErrorMessage(String message);

  /**
   * Makes this gui view visible to user.
   */
  void makeVisible();

  /**
   * Returns the user entered ratio for width for downsizing.
   * @return the user entered ratio for new width for downsizing.
   */
  double getDownsizeW();

  /**
   * Returns the user entered ratio for height for downsizing.
   * @return the user entered ratio for new height for downsizing.
   */
  double getDownsizeH();
}
