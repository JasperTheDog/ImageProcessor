package converter;

import org.junit.Test;

import java.io.FileReader;

import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;

import static org.junit.Assert.fail;


/**
 * A junit test abstract class for converters.
 */
public class AbstractConverterTest {
  protected String type;

  /**
   * Constructs an AbstractConverterTest object.
   *
   * @param type represents the type of converter.
   */
  public AbstractConverterTest(String type) {
    this.type = type;
  }


  /**
   * Returns the correct converter based off type.
   *
   * @return the correct converter based off type.
   */
  public ImageConverter getConverter() {
    if (this.type.equals("jpeg") || this.type.equals("jpg")) {
      return new ImageConverterJPEG();
    } else if (this.type.equals("ppm")) {
      return new ImageConverterPPM();
    } else if (this.type.equals("png")) {
      return new ImageConverterPNG();
    } else if (this.type.equals("bmp")) {
      return new ImageConverterBMP();
    }
    return null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadExport() {
    PixelColor[][] array = new PixelColor[3][3];
    array[0][0] = new JavaPixel(100, 255);
    array[0][1] = new JavaPixel(110, 255);
    array[0][2] = new JavaPixel(120, 255);
    array[1][0] = new JavaPixel(100, 255);
    array[1][1] = new JavaPixel(110, 255);
    array[1][2] = new JavaPixel(120, 255);
    array[2][0] = new JavaPixel(100, 255);
    array[2][1] = new JavaPixel(110, 255);
    array[2][2] = new JavaPixel(120, 255);
    this.getConverter().convertExport(
            new PixelColorPicture(array), "images/patron/tell." + this.type);
  }

  @Test
  public void testExportGood() {
    PixelColor[][] array = new PixelColor[3][3];
    array[0][0] = new JavaPixel(100, 255);
    array[0][1] = new JavaPixel(110, 255);
    array[0][2] = new JavaPixel(120, 255);
    array[1][0] = new JavaPixel(100, 255);
    array[1][1] = new JavaPixel(110, 255);
    array[1][2] = new JavaPixel(120, 255);
    array[2][0] = new JavaPixel(100, 255);
    array[2][1] = new JavaPixel(110, 255);
    array[2][2] = new JavaPixel(120, 255);
    this.getConverter().convertExport(
            new PixelColorPicture(array), "images/testExportGood." + this.type);
    try {
      Readable r = new FileReader("images/testExportGood." + this.type);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadExportNullPic() {
    this.getConverter().convertExport(null,
            "images/testExport." + this.type);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadImportNotFound() {
    Picture pic = this.getConverter().convertImport("images/testdsda." + this.type);
  }
}
