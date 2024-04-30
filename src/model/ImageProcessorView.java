package model;

import java.util.List;
import java.util.Set;

import state.Picture;

/**
 * This interface represents the operations that a view for an Image Processor application
 * should be able to access. These will not modify the view but rather take information out of them.
 */
public interface ImageProcessorView {

  /**
   * Return the image (Picture object) with the given string name.
   *
   * @param imageName represents the name of the image.
   * @return the Picture object with the given name.
   * @throws IllegalArgumentException when imageName does not exist.
   */
  Picture getImage(String imageName) throws IllegalArgumentException;

  /**
   * Return all the images stored in this ImageProcessorView as a list.
   *
   * @return all the images stored in this ImageProcessorView.
   */
  List<Picture> getAllImages();

  /**
   * Return all the image names stored in this ImageProcessorView as a set.
   *
   * @return all the image names stored in this ImageProcessorView.
   */
  Set<String> getAllImageNames();

}
