package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

import view.gui.ImageGraphicalView;

/**
 * A Simple mock class for a GUIView with a logs appendable to log method usage.
 */
public class MockGUIView implements ImageGraphicalView {
  private final Appendable logs;

  public MockGUIView(Appendable logs) {
    this.logs = logs;
  }

  @Override
  public void acceptActionListener(ActionListener actionListener) {
    try {
      logs.append("actionListening accepted" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }

  }

  @Override
  public String getAssignName() {
    try {
      logs.append("getAssignName called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return "aa";
  }

  @Override
  public String getFlipType() {
    try {
      logs.append("getFlipType called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return "vertical";
  }

  @Override
  public String getFilterType() {
    try {
      logs.append("getFilterType called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return "blur";
  }

  @Override
  public String getToneType() {
    try {
      logs.append("getToneType called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return "sepia";
  }

  @Override
  public int getChangeBrightnessValue() {
    try {
      logs.append("getChangeBrightnessValue called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return 10;
  }

  @Override
  public String getGreyscaleType() {
    try {
      logs.append("getGreyscaleType called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return "red";
  }

  @Override
  public String getSelectedImage() {
    try {
      logs.append("getSelectedImage called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return null;
  }

  @Override
  public String getImageName() {
    try {
      logs.append("getImageName called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return null;
  }

  @Override
  public void setImage(String imageName) {
    try {
      logs.append("setImage called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
  }

  @Override
  public void addToImageList(String name) {
    try {
      logs.append("addToImageList called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }

  }

  @Override
  public void renderErrorMessage(String message) {
    try {
      logs.append("renderErrorMessage called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
  }

  @Override
  public void makeVisible() {
    try {
      logs.append("makeVisible called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
  }

  @Override
  public double getDownsizeW() {
    try {
      logs.append("getDownsizeW called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return 0;
  }

  @Override
  public double getDownsizeH() {
    try {
      logs.append("getDownsizeH called" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("failed accept");
    }
    return 0;
  }
}
