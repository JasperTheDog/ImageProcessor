package state;

import org.junit.Before;
import org.junit.Test;

import state.color.JavaPixel;
import state.color.PixelColor;

import static org.junit.Assert.assertEquals;


/**
 * Represents a simple junit testing class for PixelColorPicture.
 */
public class PixelColorPictureTest {

  private Picture picture1;
  private Picture picture2;

  @Before
  public void setup() {

    PixelColor[][] pixelColors1 = new PixelColor[2][3];
    PixelColor[][] pixelColors2 = new PixelColor[4][1];

    pixelColors1[0][0] = new JavaPixel(24, 43, 23,255);
    pixelColors1[0][1] = new JavaPixel(34,255);
    pixelColors1[0][2] = new JavaPixel(234, 132, 7,255);
    pixelColors1[1][0] = new JavaPixel(134,255);
    pixelColors1[1][1] = new JavaPixel(255, 0, 0,255);
    pixelColors1[1][2] = new JavaPixel(4, 10, 101,255);

    pixelColors2[0][0] = new JavaPixel(56, 78, 205,255);
    pixelColors2[1][0] = new JavaPixel(33,255);
    pixelColors2[2][0] = new JavaPixel(48, 90, 134,255);
    pixelColors2[3][0] = new JavaPixel(219,255);

    this.picture1 = new PixelColorPicture(pixelColors1);
    this.picture2 = new PixelColorPicture(pixelColors2);
  }

  @Test
  public void getColorAt() {
    PixelColor color1 = this.picture1.getColorAt(0, 0);
    assertEquals(24, color1.getRed());
    assertEquals(43, color1.getGreen());
    assertEquals(23, color1.getBlue());

    PixelColor color2 = this.picture1.getColorAt(1, 2);
    assertEquals(4, color2.getRed());
    assertEquals(10, color2.getGreen());
    assertEquals(101, color2.getBlue());

    PixelColor color3 = this.picture1.getColorAt(0, 1);
    assertEquals(34, color3.getRed());
    assertEquals(34, color3.getGreen());
    assertEquals(34, color3.getBlue());

    PixelColor color4 = this.picture2.getColorAt(2, 0);
    assertEquals(48, color4.getRed());
    assertEquals(90, color4.getGreen());
    assertEquals(134, color4.getBlue());

    PixelColor color5 = this.picture2.getColorAt(3, 0);
    assertEquals(219, color5.getRed());
    assertEquals(219, color5.getGreen());
    assertEquals(219, color5.getBlue());
  }

  @Test
  public void getHeight() {
    assertEquals(2, this.picture1.getHeight());
    assertEquals(4, this.picture2.getHeight());
  }

  @Test
  public void getWidth() {
    assertEquals(3, this.picture1.getWidth());
    assertEquals(1, this.picture2.getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    Picture picture = new PixelColorPicture(null);
  }
}