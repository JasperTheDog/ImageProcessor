package state;

import java.awt.Color;
import java.awt.image.BufferedImage;

import state.color.PixelColor;

/**
 * Represents an implementation of Picture that uses a 2D board of PixelColors to represent
 * the image of pixels.
 */
public class PixelColorPicture implements Picture {

  private final PixelColor[][] image;

  /**
   * Constructs a PixelColorPicture object using a given image, a 2D array of PixelColor.
   *
   * @param image represents the image being given and represented by this PixelColorPicture object.
   * @throws IllegalArgumentException when given image is null
   */
  public PixelColorPicture(PixelColor[][] image) {
    if (image == null) {
      throw new IllegalArgumentException("Given a null value for image");
    }
    this.image = image;
  }


  @Override
  public PixelColor getColorAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.getHeight()) {
      throw new IllegalArgumentException("The row is out of image bounds");
    }
    if (col < 0 || col >= this.getWidth()) {
      throw new IllegalArgumentException("The column is out of image bounds");
    }
    return this.image[row][col];
  }

  @Override
  public int getHeight() {
    return image.length;
  }

  @Override
  public int getWidth() {
    return image[0].length;
  }

  @Override
  public BufferedImage convertToImage(int i) {
    int width = this.getWidth();
    int height = this.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, i);
    for (int x = 0; x < width; x += 1) {
      for (int y = 0; y < height; y += 1) {
        PixelColor pixelColor = this.getColorAt(y, x);
        Color color = new Color(pixelColor.getRed(), pixelColor.getGreen(),
                pixelColor.getBlue(), pixelColor.getAlpha());
        bufferedImage.setRGB(x, y, color.getRGB());
      }
    }
    return bufferedImage;
  }
}
