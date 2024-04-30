package converter;

import state.Picture;

/**
 * Represents an interface for exporting pictures into files on current system, and for importing
 * files from current system and turning them into Picture objects. Various implementations will
 * work for various file extensions.
 */
public interface ImageConverter {

  /**
   * Converts given picture into a file at filePath location on this system with certain extension
   * based on implementation.
   *
   * @param picture  represents the Picture to be exported.
   * @param filePath represents the location of the file to export to on this system.
   * @throws IllegalArgumentException if filePath doesn't exist on this system or if Picture is null
   */
  void convertExport(Picture picture, String filePath) throws IllegalArgumentException;

  /**
   * Converts given file from given filePath into a Picture and returns it. File extension type will
   * be based on implementation.
   *
   * @param filePath represents the filePath location on this system to import from.
   * @return the picture created from conversion.
   * @throws IllegalArgumentException when file path doesn't exist.
   */
  Picture convertImport(String filePath) throws IllegalArgumentException;
}
