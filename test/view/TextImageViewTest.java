package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.CorruptAppendable;
import converter.ImageConverter;
import converter.ImageConverterPPM;
import model.ImageProcessor;
import model.ImageProcessorView;
import model.ProcessorImpl;
import model.ProcessorViewImpl;
import state.Picture;

import static org.junit.Assert.assertEquals;

/**
 * Represents a simple junit testing class for TextImageView objects.
 */
public class TextImageViewTest {

  private Appendable a1;
  private Appendable a2;
  private ImageProcessor m1;
  private ImageProcessorView mv1;
  private ImageView v1;

  @Before
  public void init() {
    a1 = new StringBuilder();
    a2 = new CorruptAppendable();
    m1 = new ProcessorImpl();
    mv1 = new ProcessorViewImpl(m1);
    v1 = new TextImageView(a1, mv1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadNullAppendable() {
    v1 = new TextImageView(null, mv1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadNullModel() {
    v1 = new TextImageView(a1, null);
  }

  @Test(expected = IOException.class)
  public void testCorruptAppendableRenderImage() throws IOException {
    v1 = new TextImageView(a2, mv1);
    m1.load( "k", new ImageConverterPPM().convertImport("images/ourppm.ppm"));
    v1.renderImage("k");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRenderImageArg() throws IOException {
    v1 = new TextImageView(a2, mv1);
    v1.renderImage("berry");
  }

  @Test(expected = IOException.class)
  public void testCorruptAppendableRenderImageName() throws IOException {
    m1.load( "k", new ImageConverterPPM().convertImport("images/ourppm.ppm"));
    v1 = new TextImageView(a2, mv1);
    v1.renderImageNames();
  }

  @Test(expected = IOException.class)
  public void testCorruptAppendableMessage() throws IOException {
    v1 = new TextImageView(a2, mv1);
    v1.renderMessage("ada");
  }

  @Test
  public void testRenderImage() throws IOException {
    m1.load( "k", new ImageConverterPPM().convertImport("images/ourppm.ppm"));
    v1.renderImage("k");
    assertEquals("k", a1.toString());
  }

  @Test
  public void testRenderImageNames() throws IOException {
    ImageConverter c = new ImageConverterPPM();
    Picture p = c.convertImport("images/ourppm.ppm");
    m1.load( "k", p);
    m1.load("k2", p);
    m1.load( "BillyBobJunior", p);
    v1.renderImageNames();
    assertEquals("k2, BillyBobJunior, k", a1.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    v1.renderMessage("tyranny\nblocks");
    assertEquals("tyranny\nblocks", a1.toString());
  }
}