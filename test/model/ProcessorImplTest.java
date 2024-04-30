package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import converter.ImageConverter;
import converter.ImageConverterPPM;
import model.defaults.Filters;
import model.defaults.GreyScaleMaps;
import model.defaults.Transformations;
import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Represents a simple Junit testing class for ProcessorImpl.
 */
public class ProcessorImplTest {

  private ImageProcessor processor;

  @Before
  public void setup() {
    this.processor = new ProcessorImpl();
    ImageConverter c = new ImageConverterPPM();
    Picture p = c.convertImport("images/RandomColors.ppm");
    this.processor.load("colorTestPic", p);

  }


  @Test
  public void load() {
    // test an already loaded image in the setup and make sure it loaded correctly
    Picture picture = this.processor.getImage("colorTestPic");
    assertEquals(34, picture.getColorAt(1, 2).getRed());
    assertEquals(231, picture.getColorAt(1, 2).getGreen());
    assertEquals(89, picture.getColorAt(1, 2).getBlue());
    assertEquals(255, picture.getColorAt(1, 1).getAlpha());

    assertEquals(45, picture.getColorAt(2, 1).getRed());
    assertEquals(35, picture.getColorAt(0, 1).getGreen());
    assertEquals(0, picture.getColorAt(0, 0).getBlue());
  }

  @Test
  public void flipHoriz() {
    this.processor.flipHoriz("colorTestPic", "horizFlip");

    Picture picture = this.processor.getImage("colorTestPic");
    Picture hFlipPicture = this.processor.getImage("horizFlip");

    assertEquals(picture.getColorAt(0, 0).getRed(),
            hFlipPicture.getColorAt(0, 2).getRed());
    assertEquals(picture.getColorAt(0, 0).getGreen(),
            hFlipPicture.getColorAt(0, 2).getGreen());
    assertEquals(picture.getColorAt(0, 0).getBlue(),
            hFlipPicture.getColorAt(0, 2).getBlue());
    assertEquals(picture.getColorAt(0, 0).getAlpha(),
            hFlipPicture.getColorAt(0, 2).getAlpha());


    assertEquals(picture.getColorAt(1, 0).getRed(),
            hFlipPicture.getColorAt(1, 2).getRed());
    assertEquals(picture.getColorAt(1, 0).getGreen(),
            hFlipPicture.getColorAt(1, 2).getGreen());
    assertEquals(picture.getColorAt(1, 0).getBlue(),
            hFlipPicture.getColorAt(1, 2).getBlue());

    assertEquals(picture.getColorAt(0, 1).getRed(),
            hFlipPicture.getColorAt(0, 1).getRed());
    assertEquals(picture.getColorAt(0, 1).getGreen(),
            hFlipPicture.getColorAt(0, 1).getGreen());
    assertEquals(picture.getColorAt(0, 1).getBlue(),
            hFlipPicture.getColorAt(0, 1).getBlue());
  }

  @Test
  public void flipVert() {
    this.processor.flipVert("colorTestPic", "vertFlip");

    Picture picture = this.processor.getImage("colorTestPic");
    Picture vFlipPicture = this.processor.getImage("vertFlip");

    assertEquals(picture.getColorAt(0, 0).getRed(),
            vFlipPicture.getColorAt(2, 0).getRed());
    assertEquals(picture.getColorAt(0, 0).getGreen(),
            vFlipPicture.getColorAt(2, 0).getGreen());
    assertEquals(picture.getColorAt(0, 0).getBlue(),
            vFlipPicture.getColorAt(2, 0).getBlue());
    assertEquals(picture.getColorAt(0, 0).getAlpha(),
            vFlipPicture.getColorAt(2, 0).getAlpha());


    assertEquals(picture.getColorAt(1, 0).getRed(),
            vFlipPicture.getColorAt(1, 0).getRed());
    assertEquals(picture.getColorAt(1, 0).getGreen(),
            vFlipPicture.getColorAt(1, 0).getGreen());
    assertEquals(picture.getColorAt(1, 0).getBlue(),
            vFlipPicture.getColorAt(1, 0).getBlue());

    assertEquals(picture.getColorAt(0, 1).getRed(),
            vFlipPicture.getColorAt(2, 1).getRed());
    assertEquals(picture.getColorAt(0, 1).getGreen(),
            vFlipPicture.getColorAt(2, 1).getGreen());
    assertEquals(picture.getColorAt(0, 1).getBlue(),
            vFlipPicture.getColorAt(2, 1).getBlue());
  }

  @Test
  public void changeBrightness() {
    this.processor.changeBrightness(82, "colorTestPic",
            "brightColorTestPic");

    Picture picture = this.processor.getImage("colorTestPic");
    Picture brightPic = this.processor.getImage("brightColorTestPic");

    assertEquals(picture.getColorAt(0, 0).getRed() + 82,
            brightPic.getColorAt(0, 0).getRed());
    assertEquals(picture.getColorAt(0, 0).getGreen() + 82,
            brightPic.getColorAt(0, 0).getGreen());
    assertEquals(picture.getColorAt(0, 0).getBlue() + 82,
            brightPic.getColorAt(0, 0).getBlue());
    assertEquals(picture.getColorAt(0, 0).getAlpha(),
            brightPic.getColorAt(0, 0).getAlpha());

    assertEquals(picture.getColorAt(0, 1).getRed() + 82,
            brightPic.getColorAt(0, 1).getRed());
    assertEquals(picture.getColorAt(0, 1).getGreen() + 82,
            brightPic.getColorAt(0, 1).getGreen());
    // check that the maximum value of a channel is capped at 255
    assertEquals(255,
            brightPic.getColorAt(0, 1).getBlue());

    this.processor.changeBrightness(-51, "colorTestPic",
            "darkColorTestPic");
    Picture darkPic = this.processor.getImage("darkColorTestPic");
    assertEquals(picture.getColorAt(2, 0).getRed() - 51,
            darkPic.getColorAt(2, 0).getRed());
    assertEquals(picture.getColorAt(2, 0).getGreen() - 51,
            darkPic.getColorAt(2, 0).getGreen());
    assertEquals(picture.getColorAt(2, 0).getBlue() - 51,
            darkPic.getColorAt(2, 0).getBlue());
    assertEquals(picture.getColorAt(2, 0).getAlpha(),
            darkPic.getColorAt(2, 0).getAlpha());

    assertEquals(0, darkPic.getColorAt(2, 1).getRed());
    assertEquals(picture.getColorAt(2, 1).getGreen() - 51,
            darkPic.getColorAt(2, 1).getGreen());
    // check that the maximum value of a channel is capped at 255
    assertEquals(picture.getColorAt(2, 1).getBlue() - 51,
            darkPic.getColorAt(2, 1).getBlue());

  }

  @Test
  public void greyScaleRed() {
    this.processor.greyScale("colorTestPic", "gsRedPic", GreyScaleMaps.RED);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsRedPic = this.processor.getImage("gsRedPic");


    assertEquals(0, testPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsRedPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsRedPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsRedPic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsRedPic.getColorAt(0, 0).getAlpha());

    assertEquals(100, testPic.getColorAt(0, 1).getRed());
    assertEquals(100, gsRedPic.getColorAt(0, 1).getRed());
    assertEquals(100, gsRedPic.getColorAt(0, 1).getGreen());
    assertEquals(100, gsRedPic.getColorAt(0, 1).getBlue());

    assertEquals(34, testPic.getColorAt(1, 2).getRed());
    assertEquals(34, gsRedPic.getColorAt(1, 2).getRed());
    assertEquals(34, gsRedPic.getColorAt(1, 2).getGreen());
    assertEquals(34, gsRedPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void greyScaleGreen() {
    this.processor.greyScale("colorTestPic", "gsGreenPic", GreyScaleMaps.GREEN);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsGreenPic = this.processor.getImage("gsGreenPic");


    assertEquals(0, testPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsGreenPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsGreenPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsGreenPic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsGreenPic.getColorAt(0, 0).getAlpha());

    assertEquals(35, testPic.getColorAt(0, 1).getGreen());
    assertEquals(35, gsGreenPic.getColorAt(0, 1).getRed());
    assertEquals(35, gsGreenPic.getColorAt(0, 1).getGreen());
    assertEquals(35, gsGreenPic.getColorAt(0, 1).getBlue());

    assertEquals(231, testPic.getColorAt(1, 2).getGreen());
    assertEquals(231, gsGreenPic.getColorAt(1, 2).getRed());
    assertEquals(231, gsGreenPic.getColorAt(1, 2).getGreen());
    assertEquals(231, gsGreenPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void greyScaleBlue() {
    this.processor.greyScale("colorTestPic", "gsBluePic", GreyScaleMaps.BLUE);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsBluePic = this.processor.getImage("gsBluePic");


    assertEquals(0, testPic.getColorAt(0, 0).getBlue());
    assertEquals(0, gsBluePic.getColorAt(0, 0).getRed());
    assertEquals(0, gsBluePic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsBluePic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsBluePic.getColorAt(0, 0).getAlpha());

    assertEquals(239, testPic.getColorAt(0, 1).getBlue());
    assertEquals(239, gsBluePic.getColorAt(0, 1).getRed());
    assertEquals(239, gsBluePic.getColorAt(0, 1).getGreen());
    assertEquals(239, gsBluePic.getColorAt(0, 1).getBlue());

    assertEquals(89, testPic.getColorAt(1, 2).getBlue());
    assertEquals(89, gsBluePic.getColorAt(1, 2).getRed());
    assertEquals(89, gsBluePic.getColorAt(1, 2).getGreen());
    assertEquals(89, gsBluePic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void greyScaleIntensity() {
    this.processor.greyScale("colorTestPic", "gsIntensityPic", GreyScaleMaps.INTENSITY);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsIntensityPic = this.processor.getImage("gsIntensityPic");


    assertEquals(0, testPic.getColorAt(0, 0).intensity());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsIntensityPic.getColorAt(0, 0).getAlpha());

    assertEquals(124, testPic.getColorAt(0, 1).intensity());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getRed());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getGreen());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getBlue());

    assertEquals(118, testPic.getColorAt(1, 2).intensity());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getRed());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getGreen());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void greyScaleLuma() {
    this.processor.greyScale("colorTestPic", "gsLumaPic", GreyScaleMaps.LUMA);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsLumaPic = this.processor.getImage("gsLumaPic");


    assertEquals(0, testPic.getColorAt(0, 0).luma());
    assertEquals(0, gsLumaPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsLumaPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsLumaPic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsLumaPic.getColorAt(0, 0).getAlpha());

    assertEquals(63, testPic.getColorAt(0, 1).luma());
    assertEquals(63, gsLumaPic.getColorAt(0, 1).getRed());
    assertEquals(63, gsLumaPic.getColorAt(0, 1).getGreen());
    assertEquals(63, gsLumaPic.getColorAt(0, 1).getBlue());

    assertEquals(178, testPic.getColorAt(1, 2).luma());
    assertEquals(178, gsLumaPic.getColorAt(1, 2).getRed());
    assertEquals(178, gsLumaPic.getColorAt(1, 2).getGreen());
    assertEquals(178, gsLumaPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void greyScaleValue() {
    this.processor.greyScale("colorTestPic", "gsValuePic", GreyScaleMaps.VALUE);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsValuePic = this.processor.getImage("gsValuePic");


    assertEquals(0, testPic.getColorAt(0, 0).value());
    assertEquals(0, gsValuePic.getColorAt(0, 0).getRed());
    assertEquals(0, gsValuePic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsValuePic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsValuePic.getColorAt(0, 0).getAlpha());

    assertEquals(239, testPic.getColorAt(0, 1).value());
    assertEquals(239, gsValuePic.getColorAt(0, 1).getRed());
    assertEquals(239, gsValuePic.getColorAt(0, 1).getGreen());
    assertEquals(239, gsValuePic.getColorAt(0, 1).getBlue());

    assertEquals(231, testPic.getColorAt(1, 2).value());
    assertEquals(231, gsValuePic.getColorAt(1, 2).getRed());
    assertEquals(231, gsValuePic.getColorAt(1, 2).getGreen());
    assertEquals(231, gsValuePic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void filterBlur() {
    this.processor.colorTransform("colorTestPic", "blurPic", Filters.BLUR);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture blurPic = this.processor.getImage("blurPic");

    assertEquals(0, testPic.getColorAt(0, 0).getRed());
    assertEquals(0, testPic.getColorAt(0, 0).getGreen());
    assertEquals(0, testPic.getColorAt(0, 0).getBlue());
    assertEquals(0, blurPic.getColorAt(0, 0).getRed());
    assertEquals(0, blurPic.getColorAt(0, 0).getGreen());
    assertEquals(0, blurPic.getColorAt(0, 0).getBlue());

    assertEquals(100, testPic.getColorAt(0, 1).getRed());
    assertEquals(35, testPic.getColorAt(0, 1).getGreen());
    assertEquals(239, testPic.getColorAt(0, 1).getBlue());
    assertEquals(255, testPic.getColorAt(0, 1).getAlpha());
    assertEquals(25, blurPic.getColorAt(0, 1).getRed());
    assertEquals(51, blurPic.getColorAt(0, 1).getGreen());
    assertEquals(25, blurPic.getColorAt(0, 1).getBlue());
    assertEquals(255, blurPic.getColorAt(0, 1).getAlpha());

    assertEquals(34, testPic.getColorAt(1, 2).getRed());
    assertEquals(231, testPic.getColorAt(1, 2).getGreen());
    assertEquals(89, testPic.getColorAt(1, 2).getBlue());
    assertEquals(36, blurPic.getColorAt(1, 2).getRed());
    assertEquals(73, blurPic.getColorAt(1, 2).getGreen());
    assertEquals(36, blurPic.getColorAt(1, 2).getBlue());

  }

  @Test
  public void filterSharp() {
    this.processor.filter("colorTestPic", "sharpPic", Filters.SHARPEN);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture sharpPic = this.processor.getImage("sharpPic");

    assertEquals(0, testPic.getColorAt(0, 0).getRed());
    assertEquals(0, testPic.getColorAt(0, 0).getGreen());
    assertEquals(0, testPic.getColorAt(0, 0).getBlue());
    assertEquals(81, sharpPic.getColorAt(0, 0).getRed());
    assertEquals(30, sharpPic.getColorAt(0, 0).getGreen());
    assertEquals(44, sharpPic.getColorAt(0, 0).getBlue());

    assertEquals(100, testPic.getColorAt(0, 1).getRed());
    assertEquals(35, testPic.getColorAt(0, 1).getGreen());
    assertEquals(239, testPic.getColorAt(0, 1).getBlue());
    assertEquals(255, testPic.getColorAt(0, 1).getAlpha());
    assertEquals(189, sharpPic.getColorAt(0, 1).getRed());
    assertEquals(204, sharpPic.getColorAt(0, 1).getGreen());
    assertEquals(255, sharpPic.getColorAt(0, 1).getBlue());
    assertEquals(255, sharpPic.getColorAt(0, 1).getAlpha());

    assertEquals(34, testPic.getColorAt(1, 2).getRed());
    assertEquals(231, testPic.getColorAt(1, 2).getGreen());
    assertEquals(89, testPic.getColorAt(1, 2).getBlue());
    assertEquals(63, sharpPic.getColorAt(1, 2).getRed());
    assertEquals(255, sharpPic.getColorAt(1, 2).getGreen());
    assertEquals(244, sharpPic.getColorAt(1, 2).getBlue());

  }

  @Test
  public void colorTransformSepia() {
    this.processor.colorTransform("colorTestPic", "sepiaPic", Transformations.SEPIA);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture sepiaPic = this.processor.getImage("sepiaPic");

    assertEquals(0, testPic.getColorAt(0, 0).getRed());
    assertEquals(0, testPic.getColorAt(0, 0).getGreen());
    assertEquals(0, testPic.getColorAt(0, 0).getBlue());
    assertEquals(0, sepiaPic.getColorAt(0, 0).getRed());
    assertEquals(0, sepiaPic.getColorAt(0, 0).getGreen());
    assertEquals(0, sepiaPic.getColorAt(0, 0).getBlue());

    assertEquals(100, testPic.getColorAt(0, 1).getRed());
    assertEquals(35, testPic.getColorAt(0, 1).getGreen());
    assertEquals(239, testPic.getColorAt(0, 1).getBlue());
    assertEquals(255, testPic.getColorAt(0, 1).getAlpha());
    assertEquals(111, sepiaPic.getColorAt(0, 1).getRed());
    assertEquals(99, sepiaPic.getColorAt(0, 1).getGreen());
    assertEquals(77, sepiaPic.getColorAt(0, 1).getBlue());
    assertEquals(255, sepiaPic.getColorAt(0, 1).getAlpha());

    assertEquals(34, testPic.getColorAt(1, 2).getRed());
    assertEquals(231, testPic.getColorAt(1, 2).getGreen());
    assertEquals(89, testPic.getColorAt(1, 2).getBlue());
    assertEquals(207, sepiaPic.getColorAt(1, 2).getRed());
    assertEquals(185, sepiaPic.getColorAt(1, 2).getGreen());
    assertEquals(144, sepiaPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void colorTransformGreyScaleIntensity() {
    this.processor.colorTransform("colorTestPic", "gsIntensityPic", Transformations.GREYSCALE);

    Picture testPic = this.processor.getImage("colorTestPic");
    Picture gsIntensityPic = this.processor.getImage("gsIntensityPic");


    assertEquals(0, testPic.getColorAt(0, 0).intensity());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getRed());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getGreen());
    assertEquals(0, gsIntensityPic.getColorAt(0, 0).getBlue());
    assertEquals(255, gsIntensityPic.getColorAt(0, 0).getAlpha());

    assertEquals(124, testPic.getColorAt(0, 1).intensity());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getRed());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getGreen());
    assertEquals(124, gsIntensityPic.getColorAt(0, 1).getBlue());

    assertEquals(118, testPic.getColorAt(1, 2).intensity());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getRed());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getGreen());
    assertEquals(118, gsIntensityPic.getColorAt(1, 2).getBlue());
  }

  @Test
  public void getImage() {
    ImageProcessor model = new ProcessorImpl();
    ImageConverter c = new ImageConverterPPM();
    Picture p = c.convertImport("images/testPic1.ppm");
    model.load("pic1", p);
    p = c.convertImport("images/testPic2.ppm");
    model.load("pic2", p);
    p = c.convertImport("images/testPic3.ppm");
    model.load("pic3", p);

    Picture pic1 = model.getImage("pic1");
    assertEquals(100, pic1.getColorAt(0, 0).getRed());
    assertEquals(100, pic1.getColorAt(1, 0).getGreen());
    assertEquals(100, pic1.getColorAt(1, 1).getBlue());


    Picture pic2 = model.getImage("pic2");
    assertEquals(207, pic2.getColorAt(0, 0).getRed());
    assertEquals(142, pic2.getColorAt(1, 0).getGreen());
    assertEquals(166, pic2.getColorAt(1, 1).getBlue());
  }

  @Test
  public void getAllImages() {
    ImageProcessor model = new ProcessorImpl();
    ImageConverter c = new ImageConverterPPM();
    Picture p = c.convertImport("images/testPic1.ppm");
    model.load("pic1", p);
    p = c.convertImport("images/testPic2.ppm");
    model.load("pic2", p);
    p = c.convertImport("images/testPic3.ppm");
    model.load("pic3", p);

    List<Picture> pictureList = model.getAllImages();
    assertEquals(3, pictureList.size());

    Picture pic1 = pictureList.get(0);
    assertEquals(100, pic1.getColorAt(0, 0).getRed());
    assertEquals(100, pic1.getColorAt(1, 0).getGreen());
    assertEquals(100, pic1.getColorAt(1, 1).getBlue());

    Picture pic2 = pictureList.get(1);
    assertEquals(207, pic2.getColorAt(0, 0).getRed());
    assertEquals(142, pic2.getColorAt(1, 0).getGreen());
    assertEquals(166, pic2.getColorAt(1, 1).getBlue());

    Picture pic3 = pictureList.get(2);
    assertEquals(0, pic3.getColorAt(0, 0).getRed());
    assertEquals(99, pic3.getColorAt(1, 0).getGreen());
    assertEquals(143, pic3.getColorAt(1, 1).getBlue());
  }

  @Test
  public void getAllImageNames() {
    ImageProcessor model = new ProcessorImpl();
    ImageConverter c = new ImageConverterPPM();
    Picture p = c.convertImport("images/testPic1.ppm");
    model.load("pic1", p);
    p = c.convertImport("images/testPic2.ppm");
    model.load("pic2", p);
    p = c.convertImport("images/testPic3.ppm");
    model.load("pic3", p);

    Set<String> setOfNames = model.getAllImageNames();
    String[] imageNames = new String[]{"pic1", "pic2", "pic3"};
    assertEquals(imageNames.length, setOfNames.size());
    for (String name : imageNames) {
      assertTrue(setOfNames.contains(name));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMaskName() {
    this.processor.filter("colorTestPic", "new", "mask",
            Filters.BLUR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMaskSize() {
    PixelColor[][] image = new PixelColor[1][1];
    image[0][0] = new JavaPixel(255,255);
    Picture p = new PixelColorPicture(image);
    this.processor.load("mask", p);
    this.processor.filter("colorTestPic", "new", "mask",
            Filters.BLUR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalFilePathLoad() {
    this.processor.load("test",
            new ImageConverterPPM().convertImport("images/doesNotExist.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoExtensionLoad() {
    this.processor.load("test",
            new ImageConverterPPM().convertImport("images/noExtension"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testP6PPMIllegal() {
    this.processor.load("test",
            new ImageConverterPPM().convertImport("images/nonP3.ppm"));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizNonExistentImageName() {
    this.processor.flipHoriz("nonExistentPic", "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVertNonExistentImageName() {
    this.processor.flipVert("nonExistentPic", "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeBrightnessNonExistentImageName() {
    this.processor.changeBrightness(10, "nonExistentPic", "test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleNonExistentImageName() {
    this.processor.greyScale("nonExistentPic", "test", GreyScaleMaps.VALUE);
  }

}