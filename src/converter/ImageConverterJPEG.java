package converter;

import state.Picture;

/**
 * Represents an implementation of ImageConverter that is able to take in and export JPEG files.
 * Imports these files as Picture objects and exports given Picture objects as JPEG files.
 */
public class ImageConverterJPEG extends IOConverter {

  @Override
  public void convertExport(Picture picture, String filePath) throws IllegalArgumentException {
    IOConverter.convertExportHelp(picture, filePath, "jpeg");
  }

}
