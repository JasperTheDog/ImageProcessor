package state.color;


/**
 * Represents an implementation of PixelColor that holds the colors characteristics, including
 * its red, green, blue, and alpha values and has various methods of measuring these channels.
 */
public class JavaPixel implements PixelColor {

  //INVARIANT - red is always between 0 and 255 inclusive
  private final int red;

  //INVARIANT - green is always between 0 and 255 inclusive
  private final int green;

  //INVARIANT - blue is always between 0 and 255 inclusive
  private final int blue;

  //INVARIANT - alpha is always between 0 and 255 inclusive
  private final int alpha;


  /**
   * Constructs a JavaPixel object using given red, green, blue components and transparency.
   *
   * @param red   represents the red component of the color [0,255]
   * @param green represents the green component of the color [0,255]
   * @param blue  represents the blue component of the color [0,255]
   * @param alpha represents the transparency of the pixel.
   * @throws IllegalArgumentException when red,green, or blue are not within the range [0,255].
   */
  public JavaPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {

    if (red < 0 || green < 0 || blue < 0 || alpha < 0) {
      throw new IllegalArgumentException("Given component is less than 0.");
    }
    if (red > 255 || green > 255 || blue > 255 || alpha > 255) {
      throw new IllegalArgumentException("Given component is greater than 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Constructs a JavaPixel object using given value.
   *
   * @param value represents the value to be set to all three components, r,g,b,.
   * @param alpha represents the transparency value of this pixel.
   * @throws IllegalArgumentException when given a value not in range [0,255]
   */
  public JavaPixel(int value, int alpha) throws IllegalArgumentException {
    this(value, value, value, alpha);
  }

  /**
   * Constructs a JavaPixel object using a Color input.
   *
   * @param color represents the color this JavaPixel will represent.
   * @throws IllegalArgumentException if given a null value for color.
   */
  public JavaPixel(PixelColor color) {
    if (color == null) {
      throw new IllegalArgumentException("Given color is null");
    }
    this.red = color.getRed();
    this.green = color.getGreen();
    this.blue = color.getBlue();
    this.alpha = color.getAlpha();
  }

  /**
   * Return the intensity of this pixel.
   * Intensity is the average of the 3 components - red, green, and blue (on a 0-255 scale).
   * The result truncates to an integer.
   *
   * @return the intensity of this pixel as an integer.
   */
  @Override
  public int intensity() {
    int sum = this.red + this.blue + this.green;
    return sum / 3;
  }

  @Override
  public int value() {
    return Math.max(this.red, Math.max(this.blue, this.green));
  }

  /**
   * Return the luma of this pixel.
   * Luma is the weight sum of 0.2126red + 0.7152green + 0.0722blue (on a 0-255 scale).
   * The result truncates to an integer.
   *
   * @return the luma of this pixel as an integer as an integer.
   */
  @Override
  public int luma() {
    double sum = 0.2126 * this.red + 0.0722 * this.blue + 0.7152 * this.green;
    return (int) sum;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public int getAlpha() {
    return this.alpha;
  }


  /**
   * Precision of 250+ for determining if pixel is black.
   *
   * @return whether this pixel is white enough (250+, 250+, 250+)
   */
  @Override
  public boolean isBlack() {
    return this.red < 10 && this.green < 10 && this.blue < 10;
  }


}
