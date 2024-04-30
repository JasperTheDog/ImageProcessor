package model;

import java.util.function.Function;

import state.Picture;
import state.color.PixelColor;

/**
 * This interface represents the operations used to process Images like loading,
 * flipping, viewing it as greyscale, and changing its brightness.
 */
public interface ImageProcessor extends ImageProcessorView {

  /**
   * Saves the given picture to the ImageProcessor with the given image name. If imageName already
   * exist then override that image.
   *
   * @param imageName represents the name of the saved image in this processor
   * @param picture   the picture to be loaded into the image processor
   * @throws IllegalArgumentException when given picture is null.
   */
  void load(String imageName, Picture picture)
          throws IllegalArgumentException;


  /**
   * Saves a new Image with given assignName by flipping Image with given imageName horizontally.
   *
   * @param imageName  represents the name of the image to be flipped
   * @param assignName represents the name of the new image to be saved
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   */
  void flipHoriz(String imageName, String assignName) throws IllegalArgumentException;

  /**
   * Saves a new Image with given assignName by flipping Image with given imageName vertically.
   *
   * @param imageName  represents the name of the image to be flipped
   * @param assignName represents the name of the new image to be saved
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   */
  void flipVert(String imageName, String assignName) throws IllegalArgumentException;

  /**
   * Brighten the Image imageName by the given value and save it as assignName. Give a positive
   * value to increase brightness and a negative value to decrease brightness.
   *
   * @param imageName  represents the name of the image to be flipped
   * @param value      represents the value to add to each pixel
   * @param assignName represents the name of the new image to be saved
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   */
  void changeBrightness(int value, String imageName, String assignName)
          throws IllegalArgumentException;


  /**
   * Brighten the Image imageName by the given value and save it as assignName. Give a positive
   * value to increase brightness and a negative value to decrease brightness. With the added
   * functionality of taking in an maskName.
   *
   * @param imageName  represents the name of the image to be flipped
   * @param value      represents the value to add to each pixel
   * @param assignName represents the name of the new image to be saved
   * @param maskName   represents the name of the mask
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   */
  void changeBrightness(int value, String imageName, String assignName, String maskName)
          throws IllegalArgumentException;

  /**
   * Creates a greyscale Image of the Image imageName using the given map and save
   * it as assignName.
   *
   * @param imageName  represents the name of the image being greyScaled.
   * @param assignName represents the name of the new image to be saved.
   * @param map        represents the map that will map each pixel in the original image
   *                   to the new image
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   */
  void greyScale(String imageName, String assignName, Function<PixelColor, PixelColor> map)
          throws IllegalArgumentException;


  /**
   * Creates a greyscale Image of the Image imageName using the given map and save
   * it as assignName. Has an extra parameter to represent the black and
   * white mask being applied to image.
   *
   * @param imageName  represents the name of the image being greyScaled.
   * @param assignName represents the name of the new image to be saved.
   * @param map        represents the map that will map each pixel in the original image
   *                   to the new image
   * @param maskName   represents the name of the mask being applied to the image.
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   *                                  or when mask not same size as image.
   */
  void greyScale(String imageName, String assignName, String maskName, Function<PixelColor,
          PixelColor> map)
          throws IllegalArgumentException;


  /**
   * Creates a colorTransformed image by applying the matrix to each pixel in given imageName
   * Picture. Saves it as assignName.
   *
   * @param imageName  represents the name of the image being colorTransformed.
   * @param assignName represents the name of the image being saved.
   * @param matrix     represents the matrix being applied to image.
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   *                                  or the given matrix
   *                                  is not a square matrix of size 3
   */
  void colorTransform(String imageName, String assignName, double[][] matrix)
          throws IllegalArgumentException;


  /**
   * Creates a colorTransformed image by applying the matrix to each pixel in given imageName
   * Picture. Saves it as assignName. Has an extra parameter to represent the black and white mask
   * being applied to image.
   *
   * @param imageName  represents the name of the image being colorTransformed.
   * @param assignName represents the name of the image being saved.
   * @param matrix     represents the matrix being applied to image.
   * @param maskName   represents the name of the mask being applied to the image.
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   *                                  or the given matrix
   *                                  is not a square matrix of size 3.
   *                                  Or when mask not same size as image.
   */
  void colorTransform(String imageName, String assignName, String maskName, double[][] matrix)
          throws IllegalArgumentException;

  /**
   * Creates an image which has been filtered by using the given matrix and applying it
   * to each pixel's
   * channel in the given imageName and saves it as assignName.
   *
   * @param imageName  represents the name of the image being filtered
   * @param assignName represents the name of the image being saved
   * @param matrix     represents the filter matrix being applied to the image
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   *                                  or the given matrix
   *                                  is not a square matrix which has an odd-natural number size
   */
  void filter(String imageName, String assignName, double[][] matrix) throws
          IllegalArgumentException;

  /**
   * Creates an image which has been filtered by using the given matrix and applying it
   * to each pixel's
   * channel in the given imageName and saves it as assignName.
   * Has an extra parameter to represent the black and white mask being applied to image.
   *
   * @param imageName  represents the name of the image being filtered
   * @param assignName represents the name of the image being saved
   * @param matrix     represents the filter matrix being applied to the image
   * @param maskName   represents the name of the mask being applied to the image.
   * @throws IllegalArgumentException when given imageName doesn't exist in saved data
   *                                  or the given matrix
   *                                  is not a square matrix which has an odd-natural number size.
   *                                  Or when mask not same size as image.
   */
  void filter(String imageName, String assignName, String maskName, double[][] matrix) throws
          IllegalArgumentException;

  /**
   * Downsizes an image by providing the ratio of the new width and height with respect to the old
   * width and height. Handles mapping of pixels based on implementation.
   *
   * @param w          is the ratio of the new width with respect to the old width
   * @param h          is the ratio of the new height with respect to the old height
   * @param imageName  represents the name of the image being downsized
   * @param assignName represents the name of the image being saved
   * @throws IllegalArgumentException if the provided image name does not exist, or the
   *                                  double is not a number between 0 and 1 (exclusive).
   */
  void downsize(double w, double h, String imageName, String assignName)
          throws IllegalArgumentException;

}
