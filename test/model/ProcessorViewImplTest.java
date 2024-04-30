package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import converter.ImageConverter;
import converter.ImageConverterPPM;
import state.Picture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Represents a simple Junit testing class for ProcessorViewImpl.
 */
public class ProcessorViewImplTest {

  private ImageProcessorView view;

  @Before
  public void setup() {
    ImageProcessor delegate = new ProcessorImpl();
    ImageConverter a = new ImageConverterPPM();
    Picture pic = a.convertImport("images/testPic1.ppm");
    delegate.load("pic1", pic);
    pic = a.convertImport("images/testPic2.ppm");
    delegate.load("pic2", pic);
    pic = a.convertImport("images/testPic3.ppm");
    delegate.load( "pic3",pic );
    this.view = new ProcessorViewImpl(delegate);
  }

  @Test
  public void getAllImageNames() {
    Set<String> setOfNames = this.view.getAllImageNames();
    String[] imageNames = new String[]{"pic1", "pic2", "pic3"};
    assertEquals(imageNames.length, setOfNames.size());
    for (String name : imageNames) {
      assertTrue(setOfNames.contains(name));
    }
  }

  @Test
  public void getImage() {
    Picture pic1 = this.view.getImage("pic1");
    assertEquals(100, pic1.getColorAt(0, 0).getRed());
    assertEquals(100, pic1.getColorAt(1, 0).getGreen());
    assertEquals(100, pic1.getColorAt(1, 1).getBlue());


    Picture pic2 = this.view.getImage("pic2");
    assertEquals(207, pic2.getColorAt(0, 0).getRed());
    assertEquals(142, pic2.getColorAt(1, 0).getGreen());
    assertEquals(166, pic2.getColorAt(1, 1).getBlue());
  }

  @Test
  public void getAllImages() {
    List<Picture> pictureList = this.view.getAllImages();
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

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    ProcessorViewImpl processorView = new ProcessorViewImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistentImage() {
    this.view.getImage("doesNotExist");
  }
}