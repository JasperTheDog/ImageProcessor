package converter;

import state.Picture;

/**
 * Represents an implementation of ImageConverter that is able to take in and export PNG files.
 * Imports these files as Picture objects and exports given Picture objects as PNG files.
 */
public class ImageConverterPNG extends IOConverter {
  @Override
  public void convertExport(Picture picture, String filePath) throws IllegalArgumentException {
    IOConverter.convertExportHelp(picture, filePath, "png");
  }

}
