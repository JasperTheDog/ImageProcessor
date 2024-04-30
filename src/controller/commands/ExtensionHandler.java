package controller.commands;

import java.util.HashMap;
import java.util.Map;

import converter.ImageConverter;
import converter.ImageConverterBMP;
import converter.ImageConverterJPEG;
import converter.ImageConverterJPG;
import converter.ImageConverterPNG;
import converter.ImageConverterPPM;

/**
 * A class made to handle extensions. It keeps track of all supported extensions in a map
 * and is able to return an ImageConverter when given an extension (if supported).
 */
class ExtensionHandler {
  private final Map<String, ImageConverter> converterMap = new HashMap<>();


  /**
   * The constructor adds all extensions the handler supports.
   */
  ExtensionHandler() {
    this.converterMap.put("ppm", new ImageConverterPPM());
    this.converterMap.put("jpeg", new ImageConverterJPEG());
    this.converterMap.put("jpg", new ImageConverterJPG());
    this.converterMap.put("png", new ImageConverterPNG());
    this.converterMap.put("bmp", new ImageConverterBMP());
  }

  /**
   * A method to return the appropriate ImageConverter based on the file path provided.
   *
   * @param filePath the file path provided
   * @return the appropriate ImageConverter according to the given file path
   * @throws IllegalArgumentException if the extension is not supported
   */
  ImageConverter getImageConverter(String filePath) throws IllegalArgumentException {
    String extension = this.getExtension(filePath);
    if (converterMap.containsKey(extension)) {
      return this.converterMap.get(extension);
    }
    throw new IllegalArgumentException("Extension not supported");
  }

  /**
   * A method to return the appropriate extension based on the filePath provided.
   *
   * @param filePath the file path provided
   * @return the appropriate extension according to the given file path
   * @throws IllegalArgumentException if the filePath is invalid
   */
  private String getExtension(String filePath) throws IllegalArgumentException {
    try {
      String[] spl = filePath.split("\\.");
      return spl[spl.length - 1].toLowerCase();
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("File has no extension");
    }
  }

}
