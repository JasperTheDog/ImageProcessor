package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.ChangeBrightness;
import controller.commands.Downsize;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.GreyScale;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Tone;
import model.ImageProcessor;
import view.gui.ImageGraphicalView;

/**
 * An implementation of the ImageProcessorController that is more aligned with a GUI and thus
 * extends ActionListener acting as an actionListener for a GUI.
 */
public class GUIController implements ImageProcessorController, ActionListener {
  private final ImageProcessor model;

  private final ImageGraphicalView view;

  /**
   * Constructs an GUIController object that takes in the model it controls and the view it takes
   * output from via acting as an action listener for the view.
   *
   * @param m             represents the ImageProcessor model this controller operates on.
   * @param graphicalView represnts the GUI view this controller takes input from.
   */
  public GUIController(ImageProcessor m, ImageGraphicalView graphicalView)
          throws IllegalArgumentException {
    if (m == null || graphicalView == null) {
      throw new IllegalArgumentException("null arguments given");
    }
    this.model = m;
    this.view = graphicalView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "flip": {
          String assignName = this.view.getAssignName();
          new Flip(this.view.getFlipType(), this.view.getImageName(), assignName)
                  .process(this.model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        }
        case "load": {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "Images", "jpeg", "png", "bmp", "ppm", "jpg");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog(null);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            String path = f.getAbsolutePath();
            String name = f.getName().split("\\.")[0];
            new Load(path, name).process(model);
            this.view.addToImageList(name);
            this.view.setImage(name);
          }
          break;
        }
        case "save": {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showSaveDialog(null);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            new Save(f.getAbsolutePath(), this.view.getImageName()).process(this.model);
          }
          break;
        }
        case "filter": {
          String assignName = this.view.getAssignName();
          new Filter(this.view.getFilterType(), this.view.getImageName(), assignName)
                  .process(this.model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        }
        case "change brightness": {
          String assignName = this.view.getAssignName();
          new ChangeBrightness(this.view.getChangeBrightnessValue(),
                  this.view.getImageName(), assignName).process(model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        }
        case "tone": {
          String assignName = this.view.getAssignName();
          new Tone(this.view.getToneType(), this.view.getImageName(), assignName).process(model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        }
        case "greyscale": {
          String assignName = this.view.getAssignName();
          new GreyScale(this.view.getGreyscaleType(), this.view.getImageName(), assignName)
                  .process(model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        }
        case "switch":
          String imageName = this.view.getSelectedImage();
          this.view.setImage(imageName);
          break;
        case "downsize":
          String assignName = this.view.getAssignName();
          new Downsize(this.view.getImageName(), assignName,
                  this.view.getDownsizeW(), this.view.getDownsizeH()).process(model);
          this.view.addToImageList(assignName);
          this.view.setImage(assignName);
          break;
        default:
          break;
      }
    } catch (Exception exception) {
      this.view.renderErrorMessage(exception.getMessage());
    }

  }

  @Override
  public void runProcessor() throws IllegalStateException {
    view.acceptActionListener(this);
    view.makeVisible();
  }
}
