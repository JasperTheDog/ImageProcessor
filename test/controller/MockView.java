package controller;

import java.io.IOException;

import view.ImageView;

/**
 * A mock for checking if a view takes it the correct input by appending it to a logs.
 */
public class MockView implements ImageView {
  Appendable logs;

  /**
   * Constructs a new MockView object that takes in an appendable representing logs.
   *
   * @param ap represents the logs.
   */
  public MockView(Appendable ap) {
    this.logs = ap;
  }

  @Override
  public void renderImage(String imageName) throws IOException {
    logs.append("ImageName: ");
  }

  @Override
  public void renderImageNames() throws IOException {
    //Nothing
  }

  @Override
  public void renderMessage(String message) throws IOException {
    logs.append("Message: " + message);
  }
}
