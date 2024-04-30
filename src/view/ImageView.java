package view;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by a view for the ImageProcessor
 * application.
 */
public interface ImageView {

  /**
   * Render the image to the provided data destination. The method in which the image is rendered is
   * up to the implementation.
   *
   * @param imageName represents the name of the image to render.
   * @throws IOException if transmission of image to provided data destination fails.
   */
  void renderImage(String imageName) throws IOException;

  /**
   * Render the names of all the images loaded onto the image processor. The method in which the
   * image is rendered is up to the implementation.
   *
   * @throws IOException              if transmission of image to provided data destination fails.
   * @throws IllegalArgumentException if given an image name that is not contained in model this
   *                                  view represents
   */
  void renderImageNames() throws IOException;

  /**
   * Render the message to the provided data destination. The method in which the
   * message is rendered is up to the implementation.
   *
   * @param message represents the message to be rendered.
   * @throws IOException if transmission of image to provided data destination fails.
   */
  void renderMessage(String message) throws IOException;

}
