package model.functions;

import java.util.function.BiFunction;

import state.color.JavaPixel;
import state.color.PixelColor;

/**
 * A Function Object to add a given integer to a pixel and return a new pixel
 * with the additions.
 */
public class AddComponent implements BiFunction<PixelColor, Integer, PixelColor> {

  /**
   * The method to add to each component of a pixel and return a new pixel. The maximum value
   * of a pixel is 255 and the minimum value is 0 and the method makes sure a channel's value
   * stays within those bounds.
   *
   * @param pixelColor the pixel to which the value needs to be added
   * @param value      the value to be added
   * @return a new pixel with the new channel values
   */
  @Override
  public PixelColor apply(PixelColor pixelColor, Integer value) {
    int newRed = Math.max(0, Math.min(255, pixelColor.getRed() + value));
    int newBlue = Math.max(0, Math.min(255, pixelColor.getBlue() + value));
    int newGreen = Math.max(0, Math.min(255, pixelColor.getGreen() + value));
    return new JavaPixel(newRed, newGreen, newBlue, pixelColor.getAlpha());
  }
}
