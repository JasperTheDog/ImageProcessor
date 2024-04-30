package model.functions;

import java.util.function.BiFunction;

import state.color.PixelColor;
import state.color.JavaPixel;

/**
 * A function object to apply the given 3x3 color transformation matrix to a pixel color,
 * producing a new transformed pixel in the process.
 */
public class ColorTransformation implements BiFunction<PixelColor, double[][], PixelColor> {
  /**
   * Method to apply the given color transformation to a pixel and return a new pixel.
   * @param pixelColor is the given pixel
   * @param matrix is the given color transformation
   * @return the new pixel after the transformation has been applied
   */
  @Override
  public PixelColor apply(PixelColor pixelColor, double[][] matrix) {
    // assumed array is 3 x 3
    int red = Math.max(0, Math.min(255, (int) (matrix[0][0] * pixelColor.getRed()
            + matrix[0][1] * pixelColor.getGreen() + matrix[0][2] * pixelColor.getBlue())));
    int green = Math.max(0, Math.min(255, (int) (matrix[1][0] * pixelColor.getRed()
            + matrix[1][1] * pixelColor.getGreen() + matrix[1][2] * pixelColor.getBlue())));
    int blue = Math.max(0, Math.min(255, (int) (matrix[2][0] * pixelColor.getRed()
            + matrix[2][1] * pixelColor.getGreen() + matrix[2][2] * pixelColor.getBlue())));
    return new JavaPixel(red, green, blue, pixelColor.getAlpha());

  }
}
