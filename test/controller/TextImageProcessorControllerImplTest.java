package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import model.ImageProcessor;
import model.ProcessorImpl;
import model.ProcessorViewImpl;
import view.ImageView;
import view.TextImageView;

import static org.junit.Assert.assertEquals;

/**
 * Represents a simple juint testing class for TextImageProcessorControllerImpl.
 */
public class TextImageProcessorControllerImplTest {

  private ImageProcessorController c1;
  private Appendable a1;
  private ImageView v1;
  private ProcessorViewImpl mv1;
  private ImageProcessor m1;
  private Readable r1;

  @Before
  public void init() {
    a1 = new StringBuilder();
    r1 = new StringReader("q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
  }


  @Test
  public void testConstructor() {
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("Menu of commands:", output[0]);
    assertEquals("load file-path assign-name", output[1]);
    assertEquals("brighten value image-name assign-name", output[2]);
    assertEquals("darken value image-name assign-name", output[3]);
    assertEquals("flip flip-type image-name assign-name", output[4]);
    assertEquals("flip-types supported - vertical, horizontal", output[5]);
    assertEquals("greyscale greyscale-type image-name assign-name", output[6]);
    assertEquals("greyscale-types supported - red, green, blue, luma, value, intensity",
            output[7]);
    assertEquals("tone tone-type image-name assign-name", output[8]);
    assertEquals("tone-types supported - sepia, greyscale(intensity)", output[9]);
    assertEquals("filter filter-type image-name assign-name", output[10]);
    assertEquals("filter-types supported - sharpen, blur", output[11]);
    assertEquals("save file-path image-name", output[12]);
    assertEquals("q/quit (quit the program)", output[13]);
    assertEquals("m/menu (reprint the menu)", output[14]);
  }

  @Test
  public void testModelInputs() {
    Appendable logs = new StringBuilder();
    a1 = new StringBuilder();
    r1 = new StringReader("load images/Koala2.ppm k save images/Koala2.ppm k darken 10 k k1 " +
            "brighten 10 k k1 " +
            "flip vertical k k1 flip horizontal k k1 greyscale red k k1 greyscale blue k k1 " +
            "greyscale green k k1 greyscale luma k k1 " +
            "greyscale intensity k k1 greyscale value k k1 " +
            "tone sepia k k1 tone greyscale k k1 filter blur k k1 filter sharpen k k1" +
            " save Koala.ppm k q");
    m1 = new MockModel(logs);
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = logs.toString().split(System.lineSeparator());
    assertEquals("Load: imageName: k", output[0]);
    assertEquals("ChangeBrightness: value: -10 assignName: k1 imagename: k", output[1]);
    assertEquals("ChangeBrightness: value: 10 assignName: k1 imagename: k", output[2]);
    assertEquals("FlipVert: assignName: k1 imagename: k", output[3]);
    assertEquals("FlipHoriz: assignName: k1 imagename: k", output[4]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[5]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[6]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[7]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[8]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[9]);
    assertEquals("GreyScale: assignName: k1 imagename: k", output[10]);
    assertEquals("ColorTransform: assignName: k1 imagename: k", output[11]);
    assertEquals("ColorTransform: assignName: k1 imagename: k", output[12]);
    assertEquals("Filter: assignName: k1 imagename: k", output[13]);
    assertEquals("Filter: assignName: k1 imagename: k", output[14]);
  }

  @Test
  public void testFileParsing() throws FileNotFoundException {
    Appendable logs = new StringBuilder();
    r1 = new FileReader("images/testScript.txt");
    m1 = new MockModel(logs);
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = logs.toString().split(System.lineSeparator());

    assertEquals("FlipVert: assignName: mikeyV imagename: mikey", output[0]);
    assertEquals("ChangeBrightness: value: -50 assignName: mikeyVD imagename: mikeyV",
            output[1]);
    assertEquals("Filter: assignName: mikeyVDS imagename: mikeyVD", output[2]);
    assertEquals("ColorTransform: assignName: mikeyVDSS imagename: mikeyVDS", output[3]);

  }

  @Test
  public void testViewOutputs() {
    Appendable logs = new StringBuilder();
    a1 = new StringBuilder();
    r1 = new StringReader("load images/Koala2.ppm k save images/Koala2.ppm k darken 10 k k1 " +
            "brighten 10 k k1 " +
            "flip vertical k k1 flip horizontal k k1 greyscale red k k1 greyscale blue k k1 " +
            "greyscale green k k1 greyscale luma k k1 " +
            "greyscale intensity k k1 greyscale value k k1 " +
            "tone sepia k k1 tone greyscale k k1 filter blur k k1 filter sharpen k k1" +
            " save images/viewTest.ppm k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new MockView(logs);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = logs.toString().split(System.lineSeparator());
    assertEquals("Message: Menu of commands:", output[0]);
    assertEquals("Message: load file-path assign-name", output[1]);
    assertEquals("Message: brighten value image-name assign-name", output[2]);
    assertEquals("Message: darken value image-name assign-name", output[3]);
    assertEquals("Message: flip flip-type image-name assign-name", output[4]);
    assertEquals("Message: flip-types supported - vertical, horizontal", output[5]);
    assertEquals("Message: greyscale greyscale-type image-name assign-name", output[6]);
    assertEquals("Message: greyscale-types supported - red, green, blue, luma, value, " +
            "intensity", output[7]);
    assertEquals("Message: tone tone-type image-name assign-name", output[8]);
    assertEquals("Message: tone-types supported - sepia, greyscale(intensity)", output[9]);
    assertEquals("Message: filter filter-type image-name assign-name", output[10]);
    assertEquals("Message: filter-types supported - sharpen, blur", output[11]);
    assertEquals("Message: save file-path image-name", output[12]);
    assertEquals("Message: q/quit (quit the program)", output[13]);
    assertEquals("Message: m/menu (reprint the menu)", output[14]);
    assertEquals("Message: load successful", output[15]);
    assertEquals("Message: save successful", output[16]);
    assertEquals("Message: darken successful", output[17]);
    assertEquals("Message: brighten successful", output[18]);
    assertEquals("Message: flip successful", output[19]);
    assertEquals("Message: flip successful", output[20]);
    assertEquals("Message: greyscale successful", output[21]);
    assertEquals("Message: greyscale successful", output[22]);
    assertEquals("Message: greyscale successful", output[23]);
    assertEquals("Message: greyscale successful", output[24]);
    assertEquals("Message: greyscale successful", output[25]);
    assertEquals("Message: greyscale successful", output[26]);
    assertEquals("Message: tone successful", output[27]);
    assertEquals("Message: tone successful", output[28]);
    assertEquals("Message: filter successful", output[29]);
    assertEquals("Message: filter successful", output[30]);
    assertEquals("Message: save successful", output[31]);
    assertEquals("Message: program quit!", output[32]);
  }

  @Test
  public void menuWelcome() {
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("Menu of commands:", output[0]);
    assertEquals("load file-path assign-name", output[1]);
    assertEquals("brighten value image-name assign-name", output[2]);
    assertEquals("darken value image-name assign-name", output[3]);
    assertEquals("flip flip-type image-name assign-name", output[4]);
    assertEquals("flip-types supported - vertical, horizontal", output[5]);
    assertEquals("greyscale greyscale-type image-name assign-name", output[6]);
    assertEquals("greyscale-types supported - red, green, blue, luma, value, intensity",
            output[7]);
    assertEquals("tone tone-type image-name assign-name", output[8]);
    assertEquals("tone-types supported - sepia, greyscale(intensity)", output[9]);
    assertEquals("filter filter-type image-name assign-name", output[10]);
    assertEquals("filter-types supported - sharpen, blur", output[11]);
    assertEquals("save file-path image-name", output[12]);
    assertEquals("q/quit (quit the program)", output[13]);
    assertEquals("m/menu (reprint the menu)", output[14]);
  }

  @Test
  public void menuCommand() {
    a1 = new StringBuilder();
    r1 = new StringReader("m q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("Menu of commands:", output[15]);
    assertEquals("load file-path assign-name", output[16]);
    assertEquals("brighten value image-name assign-name", output[17]);
    assertEquals("darken value image-name assign-name", output[18]);
    assertEquals("flip flip-type image-name assign-name", output[19]);
    assertEquals("flip-types supported - vertical, horizontal", output[20]);
    assertEquals("greyscale greyscale-type image-name assign-name", output[21]);
    assertEquals("greyscale-types supported - red, green, blue, luma, value, intensity",
            output[22]);
    assertEquals("tone tone-type image-name assign-name", output[23]);
    assertEquals("tone-types supported - sepia, greyscale(intensity)", output[24]);
    assertEquals("filter filter-type image-name assign-name", output[25]);
    assertEquals("filter-types supported - sharpen, blur", output[26]);
    assertEquals("save file-path image-name", output[27]);
    assertEquals("q/quit (quit the program)", output[28]);
    assertEquals("m/menu (reprint the menu)", output[29]);
  }

  @Test
  public void quitMessage() {
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("Menu of commands:", output[0]);
    assertEquals("load file-path assign-name", output[1]);
    assertEquals("brighten value image-name assign-name", output[2]);
    assertEquals("darken value image-name assign-name", output[3]);
    assertEquals("flip flip-type image-name assign-name", output[4]);
    assertEquals("flip-types supported - vertical, horizontal", output[5]);
    assertEquals("greyscale greyscale-type image-name assign-name", output[6]);
    assertEquals("greyscale-types supported - red, green, blue, luma, value, intensity",
            output[7]);
    assertEquals("tone tone-type image-name assign-name", output[8]);
    assertEquals("tone-types supported - sepia, greyscale(intensity)", output[9]);
    assertEquals("filter filter-type image-name assign-name", output[10]);
    assertEquals("filter-types supported - sharpen, blur", output[11]);
    assertEquals("save file-path image-name", output[12]);
    assertEquals("q/quit (quit the program)", output[13]);
    assertEquals("m/menu (reprint the menu)", output[14]);
    assertEquals("program quit!", output[15]);
  }

  @Test
  public void testWrongCommands() {
    a1 = new StringBuilder();
    r1 = new StringReader("asdsd asdasd q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);

    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("command not supported", output[15]);
    assertEquals("command not supported", output[16]);
  }

  @Test
  public void testWrongCommandsWithRightOnes() {
    a1 = new StringBuilder();
    r1 = new StringReader("asdsd load images/ourppm.ppm k asdasd q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);

    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());

    assertEquals("command not supported", output[15]);
    assertEquals("load successful", output[16]);
    assertEquals("command not supported", output[17]);
  }

  // ppm conversion bad
  @Test
  public void testBadLoadNotFound() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/koala68.ppm k68 q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("File to be imported not found", output[15]);
  }

  @Test
  public void testBadLoadNonP3PPM() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/nonp3.ppm k68 q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Not a P3, PPM file", output[15]);
  }

  @Test
  public void testBadLoadNoExtension() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/nonp3 k68 q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Extension not supported", output[15]);
  }

  @Test
  public void testBadExportNoFileLocation() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 save billybob/a.ppm k68 q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("System cannot find specified filepath", output[16]);
  }

  @Test
  public void testBadExportBadName() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 save images/ourppm.ppm k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testExportNoExtension() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 save images/ourppm k68 q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Extension not supported", output[16]);
  }

  @Test
  public void testBadHorizFlip() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 flip horizontal k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testBadVertFlip() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 flip vertical k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testBadBrighten() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 brighten 10 k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testBadDarken() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 darken 10 k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testGreyScaleRed() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale red k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testGreyScaleGreen() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale green k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testGreyScaleBlue() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale blue k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }


  @Test
  public void testGreyScaleValue() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale value k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testGreyScaleLuma() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale luma k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testGreyScaleIntensity() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 greyscale intensity k67 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testToneSepia() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 tone sepia k87 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testToneGrey() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 tone greyscale k87 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testFilterBlur() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 filter blur k87 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  @Test
  public void testFilterSharpen() {
    a1 = new StringBuilder();
    r1 = new StringReader("load images/ourppm.ppm k68 filter sharpen k87 k q");
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
    String[] output = a1.toString().split(System.lineSeparator());
    assertEquals("Image with the given name does not exist!", output[16]);
  }

  // null exceptions

  @Test(expected = IllegalArgumentException.class)
  public void initNullModel() {
    m1 = null;
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initNullView() {
    v1 = null;
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initNullReadable() {
    r1 = null;
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void initNullMultiple() {
    r1 = null;
    v1 = null;
    m1 = null;
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
  }

  @Test(expected = IllegalStateException.class)
  public void readerOutOfInputEmpty() {
    r1 = new StringReader("");
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
  }

  @Test(expected = IllegalStateException.class)
  public void readerOutOfInputNonEmpty() {
    r1 = new StringReader("m m load images/ourPPM.ppm k");
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
  }

  @Test(expected = IllegalStateException.class)
  public void badAppendable() {
    v1 = new TextImageView(new CorruptAppendable(), m1);
    c1 = new TextImageProcessorControllerImpl(m1, v1, r1);
    c1.runProcessor();
  }

}