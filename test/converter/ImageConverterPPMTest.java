package converter;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.Scanner;

import state.Picture;
import state.PixelColorPicture;
import state.color.JavaPixel;
import state.color.PixelColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents a simple juint testing class for ImageConverterPPM.
 */
public class ImageConverterPPMTest extends AbstractConverterTest {
  private final static String type = "ppm";

  public ImageConverterPPMTest() {
    super(type);
  }

  @Test
  public void testExport() {
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
    new ImageConverterPPM().convertExport(new PixelColorPicture(array),
            "images/testExport.ppm");
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream("images/testExport.ppm"));
      assertEquals("P3", sc.next());
      assertEquals("3", sc.next());
      assertEquals("3", sc.next());
      assertEquals("255", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("100", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("110", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
      assertEquals("120", sc.next());
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testImport() {
    Picture pic = this.getConverter().convertImport("images/testExport." + this.type);
    assertEquals(100, pic.getColorAt(0, 0).getRed());
    assertEquals(100, pic.getColorAt(0, 0).getGreen());
    assertEquals(100, pic.getColorAt(0, 0).getBlue());
    assertEquals(110, pic.getColorAt(1, 1).getRed());
    assertEquals(110, pic.getColorAt(1, 1).getGreen());
    assertEquals(110, pic.getColorAt(1, 1).getBlue());
    assertEquals(120, pic.getColorAt(2, 2).getRed());
    assertEquals(120, pic.getColorAt(2, 2).getGreen());
    assertEquals(120, pic.getColorAt(2, 2).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadImportNonP3() {
    Picture pic = new ImageConverterPPM().convertImport("images/nonp3.ppm");
  }
}