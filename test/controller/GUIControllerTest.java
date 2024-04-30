package controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import model.ImageProcessor;
import model.ImageProcessorView;
import model.ProcessorImpl;
import model.ProcessorViewImpl;
import view.gui.ImageGUI;
import view.gui.ImageGraphicalView;

import static org.junit.Assert.assertEquals;

/**
 * Simple junit testing class for a GUIController object.
 */
public class GUIControllerTest {
  private GUIController gui;

  private ImageProcessor model;

  private ImageGraphicalView guiView;

  @Before
  public void setup() {
    model = new ProcessorImpl();
    ImageProcessorView viewModel = new ProcessorViewImpl(model);
    guiView = new ImageGUI(viewModel);
    gui = new GUIController(model, guiView);
  }

  @Test
  public void testActionPerformed() {
    Appendable logs = new StringBuilder();
    model = new MockModel(logs);
    Appendable logsView = new StringBuilder();
    guiView = new MockGUIView(logsView);
    gui = new GUIController(model, guiView);
    gui.actionPerformed(new ActionEvent(guiView, 1, "flip"));
    // commented because they make popups but they work.
    //gui.actionPerformed(new ActionEvent(guiView, 2, "load"));
    //gui.actionPerformed(new ActionEvent(guiView, 3, "save"));
    gui.actionPerformed(new ActionEvent(guiView, 4, "filter"));
    gui.actionPerformed(new ActionEvent(guiView, 5, "change brightness"));
    gui.actionPerformed(new ActionEvent(guiView, 6, "tone"));
    gui.actionPerformed(new ActionEvent(guiView, 7, "greyscale"));
    gui.actionPerformed(new ActionEvent(guiView, 8, "switch"));
    gui.actionPerformed(new ActionEvent(guiView, 9, "downsize"));

    // Model logs
    String[] output = logs.toString().split(System.lineSeparator());
    assertEquals("FlipVert: assignName: aa imagename: null", output[0]);
    assertEquals("Filter: assignName: aa imagename: null", output[1]);
    assertEquals("ChangeBrightness: value: 10 assignName: aa imagename: null", output[2]);
    assertEquals("ColorTransform: assignName: aa imagename: null", output[3]);
    assertEquals("GreyScale: assignName: aa imagename: null", output[4]);
    assertEquals("Downsize: assignName: aa imagename: null double w: 0.0 double h 0.0", output[5]);

    // View logs
    output = logsView.toString().split(System.lineSeparator());
    assertEquals("getAssignName called", output[0]);
    assertEquals("getFlipType called", output[1]);
    assertEquals("getImageName called", output[2]);
    assertEquals("addToImageList called", output[3]);
    assertEquals("setImage called", output[4]);

    assertEquals("getAssignName called", output[5]);
    assertEquals("getFilterType called", output[6]);
    assertEquals("getImageName called", output[7]);
    assertEquals("addToImageList called", output[8]);
    assertEquals("setImage called", output[9]);

    assertEquals("getAssignName called", output[10]);
    assertEquals("getChangeBrightnessValue called", output[11]);
    assertEquals("getImageName called", output[12]);
    assertEquals("addToImageList called", output[13]);
    assertEquals("setImage called", output[14]);

    assertEquals("getAssignName called", output[15]);
    assertEquals("getToneType called", output[16]);
    assertEquals("getImageName called", output[17]);
    assertEquals("addToImageList called", output[18]);
    assertEquals("setImage called", output[19]);

    assertEquals("getAssignName called", output[20]);
    assertEquals("getGreyscaleType called", output[21]);
    assertEquals("getImageName called", output[22]);
    assertEquals("addToImageList called", output[23]);
    assertEquals("setImage called", output[24]);

    assertEquals("getSelectedImage called", output[25]);
    assertEquals("setImage called", output[26]);
    assertEquals("getAssignName called", output[27]);
    assertEquals("getImageName called", output[28]);
    assertEquals("getDownsizeW called", output[29]);
    assertEquals("getDownsizeH called", output[30]);
    assertEquals("addToImageList called", output[31]);
    assertEquals("setImage called", output[32]);
  }

}