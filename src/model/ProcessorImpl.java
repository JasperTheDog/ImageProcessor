package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import model.functions.AddComponent;
import model.functions.ColorTransformation;
import model.functions.Kernel;
import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;


/**
 * Represents an implementation of ImageProcessor that uses a map to store the saved images
 * from all the processing. Has the ability to export and load image files, and has many operations
 * that can be done to process saved images.
 */
public class ProcessorImpl implements ImageProcessor {

  private final Map<String, Picture> savedImages;

  /**
   * Constructs a ProcessorImpl object by creating a new HashMap to represent the stored images.
   */
  public ProcessorImpl() {
    this.savedImages = new HashMap<String, Picture>();
  }

  @Override
  public void load(String imageName, Picture picture) throws IllegalArgumentException {
    if (picture == null) {
      throw new IllegalArgumentException("Given null picture");
    }
    this.savedImages.put(imageName, picture);
  }

  @Override
  public void flipHoriz(String imageName, String assignName) throws IllegalArgumentException {
    this.flip(imageName, assignName, true);
  }

  @Override
  public void flipVert(String imageName, String assignName) throws IllegalArgumentException {
    this.flip(imageName, assignName, false);
  }

  @Override
  public void changeBrightness(int value, String imageName, String assignName) throws
          IllegalArgumentException {
    this.addComponentHelper(imageName, assignName, value, new AddComponent());
  }

  @Override
  public void changeBrightness(int value, String imageName, String assignName,
                               String maskName) throws IllegalArgumentException {
    this.addComponentHelper(imageName, assignName, value, maskName, new AddComponent());
  }

  @Override
  public void greyScale(String imageName, String assignName, Function<PixelColor, PixelColor> map)
          throws IllegalArgumentException {
    this.map(imageName, assignName, map);
  }

  @Override
  public void greyScale(String imageName, String assignName, String maskName,
                        Function<PixelColor, PixelColor> map) throws IllegalArgumentException {
    this.map(imageName, assignName, maskName, map);
  }

  @Override
  public Picture getImage(String imageName) throws IllegalArgumentException {
    Picture picture = this.savedImages.getOrDefault(imageName, null);
    if (picture == null) {
      throw new IllegalArgumentException("Image with the given name does not exist!");
    }
    return picture;
  }

  @Override
  public List<Picture> getAllImages() {
    return new ArrayList<>(this.savedImages.values());
  }

  @Override
  public Set<String> getAllImageNames() {
    return this.savedImages.keySet();
  }

  @Override
  public void colorTransform(String imageName, String assignName, double[][] matrix)
          throws IllegalArgumentException {

    if (this.notSquareMatrixOfOddSize(matrix) || this.notSizeThree(matrix)) {
      throw new IllegalArgumentException("provided matrix is not a square matrix of size 3");
    }

    this.colorTransformationPicture(imageName, assignName, matrix);
  }

  @Override
  public void colorTransform(String imageName, String assignName,
                             String maskName, double[][] matrix) throws IllegalArgumentException {
    this.colorTransformationPicture(imageName, assignName, maskName, matrix);
  }


  @Override
  public void filter(String imageName, String assignName, double[][] filter)
          throws IllegalArgumentException {
    if (this.notSquareMatrixOfOddSize(filter)) {
      throw new IllegalArgumentException("provided filter is not a matrix with odd size");
    }

    Picture picture = this.getImage(imageName);
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        newImage[row][col] = this.filterHelper(picture, row, col, pixel, filter);
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  @Override
  public void filter(String imageName, String assignName, String maskName, double[][] filter)
          throws IllegalArgumentException {

    if (this.notSquareMatrixOfOddSize(filter)) {
      throw new IllegalArgumentException("provided filter is not a matrix with odd size");
    }

    Picture picture = this.getImage(imageName);
    Picture mask = this.getImage(maskName);

    if (picture.getHeight() != mask.getHeight() || picture.getWidth() != mask.getWidth()) {
      throw new IllegalArgumentException("provided mask is not same dimension of image");
    }

    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        if (mask.getColorAt(row, col).isBlack()) {
          newImage[row][col] = filterHelper(picture, row, col, pixel, filter);
        } else {
          newImage[row][col] = pixel;
        }

      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  /**
   * Downsizes an image by providing the ratio of the new width and height with respect to the old
   * width and height. Handles mapping of pixels based on the four nearest pixels to the mapped
   * pixel.
   *
   * @param w          is the ratio of the new width with respect to the old width
   * @param h          is the ratio of the new height with respect to the old height
   * @param imageName  represents the name of the image being downsized
   * @param assignName represents the name of the image being saved
   * @throws IllegalArgumentException if the provided image name does not exist, or the
   *                                  double is not a number between 0 and 1 (exclusive).
   */
  @Override
  public void downsize(double w, double h, String imageName, String assignName)
          throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    if (w >= 1.0 || h >= 1.0 || w <= 0.0 || h <= 0.0) {
      throw new IllegalArgumentException("Bad ratio for downsizing");
    }
    int newWidth = (int) (w * picture.getWidth());
    int newHeight = (int) (h * picture.getHeight());
    PixelColor[][] newImage = new PixelColor[newHeight][newWidth];
    for (int row = 0; row < newHeight; row += 1) {
      for (int col = 0; col < newWidth; col += 1) {
        newImage[row][col] = this.getDownsized(picture, row, col, newWidth, newHeight);
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }


  // checks whether the given matrix is a square matrix with odd size
  private boolean notSquareMatrixOfOddSize(double[][] matrix) {
    return !(matrix.length == matrix[0].length && matrix.length % 2 == 1);
  }


  /**
   * Flips image represented by image name vertically or horizontally based on given boolean.
   * Takes in image name and new assign name.
   *
   * @param imageName  Represents the image to be flipped.
   * @param assignName represents the new images name.
   * @param horiz      represents whether it's a horizontal flip or a vertical
   * @throws IllegalArgumentException when given imageName is not one of the stored images.
   */
  private void flip(String imageName, String assignName, boolean horiz)
          throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    PixelColor[][] flipped = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        if (horiz) {
          flipped[row][col] =
                  new JavaPixel(picture.getColorAt(row, picture.getWidth() - col - 1));
        } else {
          flipped[row][col] =
                  new JavaPixel(picture.getColorAt(picture.getHeight() - row - 1, col));
        }
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(flipped));
  }

  /**
   * Represents a private helper method for adding a value to each channel of a given image's
   * pixels.
   *
   * @param imageName  represents the name of the image to be modified.
   * @param assignName represents the name of the new image after being modified.
   * @param value      represents the value to be added to each channel.
   * @param func       represents the bi-function to be applied to each pixel.
   * @throws IllegalArgumentException when given imageName doesn't exist in stored images.
   */
  private void addComponentHelper(String imageName, String assignName,
                                  int value, BiFunction<PixelColor, Integer, PixelColor> func)
          throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = new JavaPixel(picture.getColorAt(row, col));
        newImage[row][col] = func.apply(pixel, value);
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  /**
   * Represents a private helper method for adding a value to each channel of a given image's
   * pixels.
   *
   * @param imageName  represents the name of the image to be modified.
   * @param assignName represents the name of the new image after being modified.
   * @param value      represents the value to be added to each channel.
   * @param func       represents the bi-function to be applied to each pixel.
   * @throws IllegalArgumentException when given imageName doesn't exist in stored images.
   */
  private void addComponentHelper(String imageName, String assignName,
                                  int value, String maskName,
                                  BiFunction<PixelColor, Integer, PixelColor> func)
          throws IllegalArgumentException {
    Picture mask = this.getImage(maskName);
    Picture picture = this.getImage(imageName);

    if (picture.getHeight() != mask.getHeight() || picture.getWidth() != mask.getWidth()) {
      throw new IllegalArgumentException("provided mask is not same dimension of image");
    }

    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        if (mask.getColorAt(row, col).isBlack()) {
          newImage[row][col] = func.apply(pixel, value);
        } else {
          newImage[row][col] = pixel;
        }
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  // Perform a color pixel transformation.
  private void colorTransformationPicture(String imageName, String assignName, double[][] matrix)
          throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    BiFunction<PixelColor, double[][], PixelColor> transformer = new ColorTransformation();
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        newImage[row][col] = transformer.apply(pixel, matrix);
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  // Perform a color pixel transformation with mask.
  private void colorTransformationPicture(String imageName, String assignName, String maskName,
                                          double[][] matrix) throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    Picture mask = this.getImage(maskName);

    if (picture.getHeight() != mask.getHeight() || picture.getWidth() != mask.getWidth()) {
      throw new IllegalArgumentException("provided mask is not same dimension of image");
    }

    BiFunction<PixelColor, double[][], PixelColor> transformer = new ColorTransformation();
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        if (mask.getColorAt(row, col).isBlack()) {
          newImage[row][col] = transformer.apply(pixel, matrix);
        } else {
          newImage[row][col] = pixel;
        }
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  // For maping a pixel to a pixel on all pixels in a picture.
  private void map(String imageName, String assignName,
                   Function<PixelColor, PixelColor> func) throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        newImage[row][col] = func.apply(pixel);
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  private void map(String imageName, String assignName, String maskName,
                   Function<PixelColor, PixelColor> func) throws IllegalArgumentException {
    Picture picture = this.getImage(imageName);
    Picture mask = this.getImage(maskName);

    if (picture.getHeight() != mask.getHeight() || picture.getWidth() != mask.getWidth()) {
      throw new IllegalArgumentException("provided mask is not same dimension of image");
    }
    PixelColor[][] newImage = new PixelColor[picture.getHeight()][picture.getWidth()];
    for (int row = 0; row < picture.getHeight(); row += 1) {
      for (int col = 0; col < picture.getWidth(); col += 1) {
        PixelColor pixel = picture.getColorAt(row, col);
        if (mask.getColorAt(row, col).isBlack()) {
          newImage[row][col] = func.apply(pixel);
        } else {
          newImage[row][col] = pixel;
        }
      }
    }
    this.savedImages.put(assignName, new PixelColorPicture(newImage));
  }

  // For getting the surrounding values of a certain channel of a pixel.
  private int[][] getBox(Picture picture, int row, int col, int length, String type) {
    int[][] components = new int[length][length];
    row = row - (length / 2);
    col = col - (length / 2);
    Map<String, Function<PixelColor, Integer>> pixelMap = new HashMap<>();
    pixelMap.put("red", PixelColor::getRed);
    pixelMap.put("green", PixelColor::getGreen);
    pixelMap.put("blue", PixelColor::getBlue);
    Function<PixelColor, Integer> pixelMapFunc = pixelMap.get(type);
    for (int i = 0; i < components.length; i += 1, row += 1, col -= length) {
      for (int j = 0; j < components.length; j += 1, col += 1) {
        if (col >= 0 && row >= 0 && col < picture.getWidth() && row < picture.getHeight()) {
          int num = pixelMapFunc.apply(picture.getColorAt(row, col));
          components[i][j] = num;
        } else {
          components[i][j] = 0;
        }
      }
    }
    return components;
  }

  // checks whether the given matrix has 3 rows
  private boolean notSizeThree(double[][] matrix) {
    return matrix.length != 3;
  }

  // abstracts job of filtering method
  private PixelColor filterHelper(Picture picture, int row, int col, PixelColor pixel,
                                  double[][] filter) {
    int red = new Kernel().apply(this.getBox(picture, row, col, filter.length, "red"),
            filter);
    int green = new Kernel().apply(this.getBox(picture, row, col, filter.length, "green"),
            filter);
    int blue = new Kernel().apply(this.getBox(picture, row, col, filter.length, "blue"),
            filter);
    return new JavaPixel(red, green, blue, pixel.getAlpha());

  }

  private PixelColor getDownsized(Picture picture, double row, double col,
                                  double newWidth, double newHeight) {
    double ogCol = col * (double) picture.getWidth() / newWidth;
    double ogRow = row * (double) picture.getHeight() / newHeight;


    int red = this.getDownComp(picture, ogRow, ogCol, PixelColor::getRed);
    int green = this.getDownComp(picture, ogRow, ogCol, PixelColor::getGreen);
    int blue = this.getDownComp(picture, ogRow, ogCol, PixelColor::getBlue);
    int alpha = this.getDownComp(picture, ogRow, ogCol, PixelColor::getAlpha);
    return new JavaPixel(red, green, blue, alpha);
  }

  private int getDownComp(Picture picture, double ogRow, double ogCol,
                          Function<PixelColor, Integer> map) {
    PixelColor pixelA = picture.getColorAt(floor(ogRow), floor(ogCol));
    PixelColor pixelB = picture.getColorAt(floor(ogRow), ceiling(ogCol));
    PixelColor pixelC = picture.getColorAt(ceiling(ogRow), floor(ogCol));
    PixelColor pixelD = picture.getColorAt(ceiling(ogRow), ceiling(ogCol));
    double m = map.apply(pixelB) * (ogCol - floor(ogCol)) + map.apply(pixelA) * (ceiling(ogCol)
            - ogCol);
    double n = map.apply(pixelD) * (ogCol - floor(ogCol)) + map.apply(pixelC) * (ceiling(ogCol)
            - ogCol);
    return Math.min(255, (int) (n * (ogRow - floor(ogRow)) + m * (ceiling(ogRow) - ogRow)));
  }


  // floor
  private int floor(double x) {
    return (int) x;
  }

  // ceiling (next int)
  private int ceiling(double x) {
    return this.floor(x) + 1;
  }
}

