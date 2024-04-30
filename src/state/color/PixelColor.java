package state.color;

/**
 * This interface offers a way to represent a color of a pixel and has methods that give information
 * about properties of the pixel without altering it.
 */
public interface PixelColor {

  /**
   * Return the intensity of this pixel.
   * Intensity is the average of the 3 components - red, green, and blue (on a 0-255 scale).
   * The conversion of the result to an integer (if necessary) is implementation specific.
   *
   * @return the intensity of this pixel as an integer.
   */
  int intensity();

  /**
   * Return the value of this pixel.
   * Value is the maximum value of the 3 components - red, green, and blue (on a 0-255 scale).
   *
   * @return the value of this pixel.
   */
  int value();

  /**
   * Return the luma of this pixel.
   * Luma is the weight sum of 0.2126red + 0.7152green + 0.0722blue (on a 0-255 scale).
   * The conversion of the result to an integer (if necessary) is implementation specific.
   *
   * @return the luma of this pixel as an integer as an integer.
   */
  int luma();

  /**
   * Return the red component of this pixel.
   * [0,255] scale.
   *
   * @return the red component of this pixel.
   */
  int getRed();

  /**
   * Return the green component of this pixel.
   * [0,255] scale.
   *
   * @return the green component of this pixel.
   */
  int getGreen();

  /**
   * Return the blue component of this pixel.
   * [0,255] scale.
   *
   * @return the blue component of this pixel.
   */
  int getBlue();


  /**
   * Return the alpha (transparent) component of this pixel.
   * [0,255] scale.
   *
   * @return the blue component of this pixel.
   */
  int getAlpha();

  /**
   * Returns whether the pixel is Black enough for masking. Precision based off of implementaiton.
   *
   * @return whether the pixel is Black for masking.
   */
  boolean isBlack();
}
