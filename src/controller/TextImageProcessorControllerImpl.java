package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ChangeBrightness;
import controller.commands.Filter;
import controller.commands.GreyScale;
import controller.commands.Load;
import controller.commands.ProcessorCommand;
import controller.commands.Save;
import controller.commands.Flip;
import controller.commands.Tone;
import model.ImageProcessor;
import view.ImageView;

/**
 * Represents an implementation of the ImageProcessController using readable to take inputs, a model
 * to keep track of the state of our ImageProcessor, a view to output to, and a scanner to read
 * input from.
 */
public class TextImageProcessorControllerImpl implements ImageProcessorController {

  private final ImageProcessor model;
  private final ImageView view;
  private final Scanner sc;
  private boolean quit;
  private final Map<String, Function<Scanner, ProcessorCommand>> commands =
          new HashMap<String, Function<Scanner, ProcessorCommand>>();

  /**
   * Constructs a TextImageProcessorControllerImpl object with given model, view, and readable.
   * Constructs the scanner instance variable using the given readable.
   *
   * @param model    represents the imageProcessor used to process images.
   * @param view     represents the view object we output feedback and other messages to.
   * @param readable represents the object we take input from.
   * @throws IllegalArgumentException when given a null argument for any of the parameters.
   */
  public TextImageProcessorControllerImpl(ImageProcessor model, ImageView view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new
              IllegalArgumentException("Cannot give null arguments for constructing a controller");
    }
    this.model = model;
    this.view = view;
    this.sc = new Scanner(readable);
    quit = false;
    this.addCommands();
  }

  @Override
  public void runProcessor() throws IllegalStateException {
    this.printMenu();
    while (!quit) {

      String command = processNextString();
      ProcessorCommand p;

      switch (command) {
        case "q":
        case "quit":
          quit = true;
          this.writeMessage("program quit!");
          break;
        case "m":
        case "menu":
          this.printMenu();
          break;
        default:
          Function<Scanner, ProcessorCommand> cmd = this.commands.getOrDefault(command,
                  null);
          if (cmd == null) {
            this.writeMessage("command not supported");
          } else {
            try {
              p = cmd.apply(sc);
              try {
                p.process(this.model);
                this.writeMessage(command + " successful");
              } catch (IllegalArgumentException e) {
                writeMessage(e.getMessage());
              }
            } catch (NumberFormatException error) {
              writeMessage(error.getMessage());
            }
          }
          break;
      }
    }
  }

  // For printing out menu
  private void printMenu() {
    this.writeMessage("Menu of commands:");
    this.writeMessage("load file-path assign-name");
    this.writeMessage("brighten value image-name assign-name");
    this.writeMessage("darken value image-name assign-name");
    this.writeMessage("flip flip-type image-name assign-name");
    this.writeMessage("flip-types supported - vertical, horizontal");
    this.writeMessage("greyscale greyscale-type image-name assign-name");
    this.writeMessage("greyscale-types supported - red, green, blue, luma, value, intensity");
    this.writeMessage("tone tone-type image-name assign-name");
    this.writeMessage("tone-types supported - sepia, greyscale(intensity)");
    this.writeMessage("filter filter-type image-name assign-name");
    this.writeMessage("filter-types supported - sharpen, blur");
    this.writeMessage("save file-path image-name");
    this.writeMessage("q/quit (quit the program)");
    this.writeMessage("m/menu (reprint the menu)");

  }

  /**
   * Calls on view object to render message.
   *
   * @param message represents message to be output (appended).
   * @throws IllegalStateException if an IOException occurs during transmission to appendable in
   *                               view object.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Calls on the scanner to take in the next input, throws an IllegalStateException if
   * no input left.
   *
   * @return the next string in this scanner.
   * @throws IllegalStateException when scanner has no next.
   */
  private String processNextString() throws IllegalStateException {
    if (!sc.hasNext()) {
      throw new IllegalStateException("Readable ran out of inputs, make sure to have q/quit at " +
              "end of readable");
    }
    return sc.next();
  }

  /**
   * Calls on the scanner to take in the next input, throws an IllegalStateException if
   * no input left.
   *
   * @return the next int in this scanner.
   * @throws IllegalStateException when scanner has no next.
   */
  private int processNextInt() {
    if (!sc.hasNext()) {
      throw new IllegalStateException("Readable ran out of inputs, make sure to have q/quit at " +
              "end of readable");
    }
    try {
      return Integer.parseInt(sc.next());
    } catch (NumberFormatException e) {
      throw new NumberFormatException("the expect input was an integer!");
    }
  }

  /**
   * Adds all known commands in string version into commands map instance variable as keys,
   * creates their corresponding command objects as their value.
   */
  private void addCommands() {
    this.commands.put("load", s -> new Load(this.processNextString(), this.processNextString()));
    this.commands.put("flip", s ->
            new Flip(this.processNextString(), this.processNextString(), this.processNextString()));
    this.commands.put("darken", s -> new ChangeBrightness(
            -1 * this.processNextInt(), this.processNextString(), this.processNextString()));
    this.commands.put("brighten", s -> new ChangeBrightness(
            this.processNextInt(), this.processNextString(), this.processNextString()));
    this.commands.put("darkenMask", s -> new ChangeBrightness(
            -1 * this.processNextInt(), this.processNextString(), this.processNextString(),
            this.processNextString()));
    this.commands.put("brightenMask", s -> new ChangeBrightness(
            this.processNextInt(), this.processNextString(), this.processNextString(),
            this.processNextString()));
    this.commands.put("greyscale", s ->
            new GreyScale(this.processNextString(), this.processNextString(),
                    this.processNextString()));
    this.commands.put("greyscaleMask", s ->
            new GreyScale(this.processNextString(), this.processNextString(),
                    this.processNextString(), this.processNextString()));
    this.commands.put("tone", s -> new Tone(this.processNextString(), this.processNextString(),
            this.processNextString()));
    this.commands.put("toneMask", s -> new Tone(this.processNextString(), this.processNextString(),
            this.processNextString(), this.processNextString()));
    this.commands.put("filter", s -> new Filter(this.processNextString(), this.processNextString(),
            this.processNextString()));
    this.commands.put("filterMask", s -> new Filter(this.processNextString(),
            this.processNextString(), this.processNextString(), this.processNextString()));
    this.commands.put("save", s -> new Save(this.processNextString(), this.processNextString()));
  }
}
