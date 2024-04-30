package state;

import java.awt.image.BufferedImage;

import state.color.PixelColor;

/**
 * This interface represents the Image and the operations that can be used to monitor the state of
 * the image without altering the Image.
 */
public interface Picture {

  /**
   * Returns the Color of this Image's pixel at the given coordinates.
   *
   * @param row represents the row of the pixel
   * @param col represents the column of the pixel
   * @throws IllegalArgumentException when given coordinate is outside the bounds of the image.
   */
  PixelColor getColorAt(int row, int col) throws IllegalArgumentException;

  /**
   * Returns the height of this Image.
   *
   * @return the height of this Image.
   */
  int getHeight();

  /**
   * Returns the width of this Image.
   *
   * @return the width of this Image.
   */
  int getWidth();

  /**
   * Converts this Picture into a BufferedImage.
   *
   * @param i represents what type of BufferedImage, for example whether it has alpha values or not.
   * @return the BufferedImage of this Picture.
   */
  BufferedImage convertToImage(int i);

}
