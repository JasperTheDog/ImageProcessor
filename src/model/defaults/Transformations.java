package model.defaults;

/**
 * A class to hold all the current color transformation matrixes that might be used when calling
 * on our model's colorTransform method.
 */
public class Transformations {
  public static final double[][] SEPIA =
          new double[][]{
                  {0.393, 0.769, 0.189},
                  {0.349, 0.686, 0.168},
                  {0.272, 0.534, 0.131}};
  public static final double[][] GREYSCALE =
          new double[][]{
                  {1.0 / 3, 1.0 / 3, 1.0 / 3},
                  {1.0 / 3, 1.0 / 3, 1.0 / 3},
                  {1.0 / 3, 1.0 / 3, 1.0 / 3}};
}
