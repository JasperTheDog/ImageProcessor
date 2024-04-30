package controller;

/**
 * The interface that represents a controller of an ImageProcessor. It takes in inputs and outputs
 * based on the implementation. It is able to start up an ImageProcessor via runProcessor() method.
 * Throws IllegalStateExceptions when given bad input source and output sources.
 */
public interface ImageProcessorController {

  /**
   * Run a new ImageProcessor Application, with input and output sources.
   * The nature of the input/output will be an implementation detail.
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit output.
   */
  void runProcessor() throws IllegalStateException;
}
