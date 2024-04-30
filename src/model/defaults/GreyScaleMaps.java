package model.defaults;

import java.util.function.Function;

import state.color.JavaPixel;
import state.color.PixelColor;

/**
 * A class to hold all the standard greyscaling maps that might be used when calling on greyscale
 * in our Processor model.
 */
public class GreyScaleMaps {
  public static final Function<PixelColor, PixelColor> RED = (P -> new JavaPixel(P.getRed(),
          P.getAlpha()));
  public static final Function<PixelColor, PixelColor> GREEN = (P -> new JavaPixel(P.getGreen(),
          P.getAlpha()));
  public static final Function<PixelColor, PixelColor> BLUE = (P -> new JavaPixel(P.getBlue(),
          P.getAlpha()));
  public static final Function<PixelColor, PixelColor> INTENSITY = (P -> new JavaPixel(P.intensity()
          , P.getAlpha()));
  public static final Function<PixelColor, PixelColor> LUMA = (P -> new JavaPixel(P.luma(),
          P.getAlpha()));
  public static final Function<PixelColor, PixelColor> VALUE = (P -> new JavaPixel(P.value(),
          P.getAlpha()));

}
