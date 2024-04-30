package model;

import java.util.List;
import java.util.Set;

import state.Picture;

/**
 * Represents an implementation of ImageProcessorView that uses composition.
 */
public class ProcessorViewImpl implements ImageProcessorView {

  private final ImageProcessor delegate;

  /**
   * Constructs a ProcessorViewImpl object that takes in a delegate ImageProcessor to run all the
   * methods for this object.
   *
   * @param delegate represents the delegating object.
   */
  public ProcessorViewImpl(ImageProcessor delegate) {
    if (delegate == null) {
      throw new IllegalArgumentException("the delegate cannot be null!");
    }
    this.delegate = delegate;
  }

  @Override
  public Picture getImage(String imageName) throws IllegalArgumentException {
    return this.delegate.getImage(imageName);
  }

  @Override
  public List<Picture> getAllImages() {
    return this.delegate.getAllImages();
  }

  @Override
  public Set<String> getAllImageNames() {
    return this.delegate.getAllImageNames();
  }

}
