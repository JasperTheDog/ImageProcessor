package converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;

/**
 * Represents an implementation of ImageConverter that works for p3 ppm files for importing images
 * and converting and for exporting stored images into p3 ppm files.
 */
public class ImageConverterPPM implements ImageConverter {

  @Override
  public void convertExport(Picture picture, String filePath) throws IllegalArgumentException {

    if (picture == null) {
      throw new IllegalArgumentException("Null picture");
    }

    StringBuilder contents = new StringBuilder();
    int height = picture.getHeight();
    int width = picture.getWidth();

    contents.append("P3\n");
    contents.append(width).append(" ").append(height).append("\n");
    contents.append("255\n");
    for (int row = 0; row < height; row += 1) {
      for (int col = 0; col < width; col += 1) {
        PixelColor color = picture.getColorAt(row, col);
        contents.append(color.getRed()).append("\n");
        contents.append(color.getGreen()).append("\n");
        contents.append(color.getBlue()).append("\n");
      }
    }

    try {
      FileWriter fileWriter = new FileWriter(filePath);
      fileWriter.write(contents.toString());
      fileWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("System cannot find specified filepath");
    }
  }

  @Override
  public Picture convertImport(String filePath) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File to be imported not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Not a P3, PPM file");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    // ap.append("Maximum value of a color in this file (usually 255): " +maxValue+"\n");

    PixelColor[][] image = new JavaPixel[height][width];

    for (int row = 0; row < height; row += 1) {
      for (int col = 0; col < width; col += 1) {

        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();

        image[row][col] = new JavaPixel(red, green, blue, 255);
      }
    }
    return new PixelColorPicture(image);
  }
}
