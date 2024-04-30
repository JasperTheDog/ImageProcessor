package controller.commands;

import model.ImageProcessor;

/**
 * Used to represent different processor commands a ImageProcessorController might utilize.
 * Has one method process, which uses the ImageProcessor model in some capacity based on
 * implementation.
 */
public interface ProcessorCommand {

  /**
   * Uses the ImageProcessor model in some capacity based on
   * implementation.
   *
   * @param processor represents the ImageProcessor model that will perform the action of the
   *                  command.
   */
  void process(ImageProcessor processor);
}
