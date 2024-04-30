package converter;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;

/**
 * Class to abstract the process converting standard file formats based
 * off extension into our Picture Objects, and
 * also for converting our Picture objects into standard file formats.
 */
public abstract class IOConverter implements ImageConverter {

  static void convertExportHelp(Picture picture, String filePath, String extension) {

    Map<String, Integer> extToColorType = new HashMap<>();
    extToColorType.put("jpeg", BufferedImage.TYPE_INT_RGB);
    extToColorType.put("jpg", BufferedImage.TYPE_INT_RGB);
    extToColorType.put("bmp", BufferedImage.TYPE_INT_RGB);
    extToColorType.put("png", BufferedImage.TYPE_INT_ARGB);

    if (picture == null) {
      throw new IllegalArgumentException("Null picture");
    }

    BufferedImage bufferedImage = picture.convertToImage(extToColorType.get(extension));

    try {
      File output = new File(filePath);
      ImageIO.write(bufferedImage, extension, output);
    } catch (IOException e) {
      throw new IllegalArgumentException("System cannot find specified file path");
    }
  }

  static Picture convertImportHelp(String filePath) {
    BufferedImage pic;

    try {
      pic = ImageIO.read(new File(filePath));
    } catch (Exception e) {
      throw new IllegalArgumentException("File to be imported not found");
    }

    int width = pic.getWidth();
    int height = pic.getHeight();

    PixelColor[][] image = new PixelColor[height][width];

    for (int x = 0; x < width; x += 1) {
      for (int y = 0; y < height; y += 1) {
        Color color = new Color(pic.getRGB(x, y), true);
        image[y][x] = new JavaPixel(color.getRed(), color.getGreen(),
                color.getBlue(), color.getAlpha());
      }
    }
    return new PixelColorPicture(image);
  }

  @Override
  public abstract void convertExport(Picture picture, String filePath)
          throws IllegalArgumentException;

  @Override
  public Picture convertImport(String filePath) throws IllegalArgumentException {
    return IOConverter.convertImportHelp(filePath);
  }
}