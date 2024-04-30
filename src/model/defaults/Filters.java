package model.defaults;

/**
 * A class to hold all the static filter matrix for applying as kernels to each channel of a pixel.
 * These will be used when using our Model's filter method.
 */
public class Filters {
  public static final double[][] BLUR =
          new double[][]{
                  {1.0 / 16, 1.0 / 8, 1.0 / 16},
                  {1.0 / 8, 1.0 / 4, 1.0 / 8},
                  {1.0 / 16, 1.0 / 8, 1.0 / 16}};

  public static final double[][] SHARPEN =
          new double[][]{
                  {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                  {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
}
