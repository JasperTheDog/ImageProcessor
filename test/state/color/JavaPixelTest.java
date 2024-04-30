package state.color;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Represents a simple junit testing class for JavaPixel.
 */
public class JavaPixelTest {

  private PixelColor pixel1;
  private PixelColor pixel2;
  private PixelColor pixel3;
  private PixelColor pixel4;
  private PixelColor pixel5;


  @Before
  public void setup() {
    pixel1 = new JavaPixel(34, 55, 173,255);
    pixel2 = new JavaPixel(246,255);
    pixel3 = new JavaPixel(162, 66, 90,255);
    pixel4 = new JavaPixel(27, 0, 255,255);
    pixel5 = new JavaPixel(88,255);
  }

  @Test
  public void getRed() {
    assertEquals(34, this.pixel1.getRed());
    assertEquals(246, this.pixel2.getRed());
    assertEquals(162, this.pixel3.getRed());
    assertEquals(27, this.pixel4.getRed());
    assertEquals(88, this.pixel5.getRed());
  }

  @Test
  public void getGreen() {
    assertEquals(55, this.pixel1.getGreen());
    assertEquals(246, this.pixel2.getGreen());
    assertEquals(66, this.pixel3.getGreen());
    assertEquals(0, this.pixel4.getGreen());
    assertEquals(88, this.pixel5.getGreen());
  }

  @Test
  public void getBlue() {
    assertEquals(173, this.pixel1.getBlue());
    assertEquals(246, this.pixel2.getBlue());
    assertEquals(90, this.pixel3.getBlue());
    assertEquals(255, this.pixel4.getBlue());
    assertEquals(88, this.pixel5.getBlue());
  }

  @Test
  public void intensity() {
    assertEquals(87, this.pixel1.intensity());
    assertEquals(246, this.pixel2.intensity());
    assertEquals(106, this.pixel3.intensity());
    assertEquals(94, this.pixel4.intensity());
    assertEquals(88, this.pixel5.intensity());
  }

  @Test
  public void luma() {
    assertEquals(59, this.pixel1.luma());
    assertEquals(246, this.pixel2.luma());
    assertEquals(88, this.pixel3.luma());
    assertEquals(24, this.pixel4.luma());
    assertEquals(88, this.pixel5.luma());
  }

  @Test
  public void value() {
    assertEquals(173, this.pixel1.value());
    assertEquals(246, this.pixel2.value());
    assertEquals(162, this.pixel3.value());
    assertEquals(255, this.pixel4.value());
    assertEquals(88, this.pixel5.value());
  }

  @Test
  public void getTransparent() {
    assertEquals(255, this.pixel1.getAlpha());
    assertEquals(255, this.pixel2.getAlpha());
    assertEquals(255, this.pixel3.getAlpha());
    assertEquals(255, this.pixel4.getAlpha());
    assertEquals(255, this.pixel5.getAlpha());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNegRed() {
    PixelColor pixel = new JavaPixel(-1, 4, 5,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegGreen() {
    PixelColor pixel = new JavaPixel(100, -3, 5,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegBlue() {
    PixelColor pixel = new JavaPixel(200, 4, -5,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAboveBoundRed() {
    PixelColor pixel = new JavaPixel(256, 4, 5,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAboveBoundGreen() {
    PixelColor pixel = new JavaPixel(9, 344, 5,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAboveBoundBlue() {
    PixelColor pixel = new JavaPixel(10, 4, 1015,255);
  }
}
