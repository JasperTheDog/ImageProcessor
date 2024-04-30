package controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import model.ImageProcessor;
import state.Picture;
import state.color.PixelColor;

/**
 * Represents a mock model class for imageProcessor interface. This is used to test whether
 * the controller gives the correct inputs into the model.
 */
public class MockModel implements ImageProcessor {

  private final Appendable logs;

  /**
   * Constructs a MockModel object with given log appendable.
   *
   * @param ap represents the logs.
   */
  public MockModel(Appendable ap) {
    this.logs = ap;
  }

  @Override
  public void load(String imageName, Picture picture)
          throws IllegalArgumentException {
    try {
      logs.append("Load: imageName: " + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void flipHoriz(String imageName, String assignName) throws IllegalArgumentException {
    try {
      logs.append("FlipHoriz: assignName: " + assignName + " imagename: "
              + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void flipVert(String imageName, String assignName) throws IllegalArgumentException {
    try {
      logs.append("FlipVert: assignName: " + assignName + " imagename: "
              + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void changeBrightness(int value, String imageName, String assignName)
          throws IllegalArgumentException {
    try {
      logs.append("ChangeBrightness: value: " + value + " assignName: "
              + assignName + " imagename: " + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void changeBrightness(int value, String imageName, String assignName, String maskName)
          throws IllegalArgumentException {
    try {
      logs.append("ChangeBrightness: value: " + value + " assignName: "
              + assignName + " imagename: " + imageName + " maskName: " + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void greyScale(String imageName, String assignName, Function<PixelColor, PixelColor> map)
          throws IllegalArgumentException {
    try {
      logs.append("GreyScale: assignName: " + assignName + " imagename: "
              + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void greyScale(String imageName, String assignName,
                        String maskName,
                        Function<PixelColor, PixelColor> map) throws IllegalArgumentException {
    try {
      logs.append("GreyScale: assignName: " + assignName + " imagename: "
              + imageName + " maskName: " + maskName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void colorTransform(String imageName, String assignName, double[][] matrix) {
    try {
      logs.append("ColorTransform: assignName: " + assignName + " imagename: "
              + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void colorTransform(String imageName,
                             String assignName,
                             String maskName, double[][] matrix) throws IllegalArgumentException {

    try {
      logs.append("ColorTransform: assignName: " + assignName + " imagename: "
              + imageName + " maskName: " + maskName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void filter(String imageName, String assignName, double[][] matrix) {
    try {
      logs.append("Filter: assignName: " + assignName + " imagename: "
              + imageName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }

  @Override
  public void filter(String imageName,
                     String assignName,
                     String maskName, double[][] matrix) throws IllegalArgumentException {
    try {
      logs.append("Filter: assignName: " + assignName + " imagename: "
              + imageName + " maskName: " + maskName + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }


  @Override
  public void downsize(double w, double h, String imageName, String assignName)
          throws IllegalArgumentException {
    try {
      logs.append("Downsize: assignName: " + assignName + " imagename: "
              + imageName + " double w: " + w + " double h " + h + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Bad logs appendable");
    }
  }


  @Override
  public Picture getImage(String imageName) throws IllegalArgumentException {
    return null;
  }

  @Override
  public List<Picture> getAllImages() {
    return null;
  }

  @Override
  public Set<String> getAllImageNames() {
    return null;
  }
}
