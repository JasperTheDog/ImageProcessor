package view;

import java.io.IOException;
import java.util.ArrayList;

import model.ImageProcessorView;

/**
 * Represents an implementation ImageView to display the Image Processor in text format.
 */
public class TextImageView implements ImageView {
  Appendable appendable;
  ImageProcessorView model;

  /**
   * Construct a TextImageView object that takes in an appendable to output to, and a model to take
   * state info from.
   *
   * @param appendable represents where to output to.
   * @param model      represents the current model state of the image processor.
   */
  public TextImageView(Appendable appendable, ImageProcessorView model)
          throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Given model/appendable is null");
    }
    this.model = model;
    this.appendable = appendable;
  }


  /**
   * Render the image to the output source by displaying the name of the image.
   *
   * @param imageName represents the name of the image to render.
   * @throws IOException              if transmission of image to provided data destination fails.
   * @throws IllegalArgumentException when given an image name that doesn't exist in our model
   */
  @Override
  public void renderImage(String imageName) throws IOException, IllegalArgumentException {
    if (this.model.getAllImageNames().contains(imageName)) {
      this.renderMessage(imageName);
    } else {
      throw new IllegalArgumentException("No image with given name");
    }

  }

  /**
   * Render the names of all the images loaded onto the image processor. Outputs these names to the
   * output source textually.
   *
   * @throws IOException if transmission to provided data destination fails.
   */
  @Override
  public void renderImageNames() throws IOException {
    ArrayList<String> imageNames = new ArrayList<>(this.model.getAllImageNames());
    for (int i = 0; i < model.getAllImageNames().size() - 1; i += 1) {
      appendable.append(imageNames.get(i) + ", ");
    }
    if (imageNames.size() > 0) {
      appendable.append(imageNames.get(imageNames.size() - 1));
    }

  }

  /**
   * Render the message to the provided data destination. Output this message textually to the
   * output source.
   *
   * @param message represents the message to be rendered.
   * @throws IOException if transmission of image to provided data destination fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    appendable.append(message);
  }

}
