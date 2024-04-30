package model.functions;

import java.util.function.BiFunction;

/**
 * A function object to filter a given odd x odd matrix kernel onto a pixel channel. Creates the new
 * value of the pixel after filerting.
 */
public class Kernel implements BiFunction<int[][], double[][], Integer> {

  /**
   * Method to operate on two matrices and return the appropriate integer (which is bounded between
   * 0 and 255 inclusive).
   * @param pixelMatrix the first matrix in the operation
   * @param kernel the second matrix in the operation
   * @return the resulting integer value after operating on the two matrices
   */
  @Override
  public Integer apply(int[][] pixelMatrix, double[][] kernel) {
    int sum = 0;
    for (int i = 0; i < pixelMatrix.length; i += 1) {
      for (int j = 0; j < pixelMatrix[0].length; j += 1) {
        sum += pixelMatrix[i][j] * kernel[i][j];
      }
    }
    return Math.max(0, Math.min(255, sum));
  }
}
