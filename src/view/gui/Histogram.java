package view.gui;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Represents an implementation of JPanel that creates Histograms of images where the line graphs
 * on this Histogram represents the number of red/green/blue/intensity data points of each pixel
 * are plotted. Has the ability to paint itself and creating the JPanel.
 */
public class Histogram extends JPanel {

  private final int[] redCoords;
  private final int[] greenCoords;
  private final int[] blueCoords;
  private final int[] intensityCoords;
  private final int max;
  private final int offsetX;

  /**
   * Constructs a Histogram object by giving it the array of coords it will graph.
   *
   * @param redCoords       represents the array of red coordinates to graph.
   * @param greenCoords     represents the array of green coordinates to graph.
   * @param blueCoords      represents the array of blue coordinates to graph.
   * @param intensityCoords represents the array of intensity coordinates to graph.
   * @param max             represents the max of coords.
   */
  public Histogram(int[] redCoords, int[] greenCoords, int[] blueCoords,
                   int[] intensityCoords, int max) {
    this.redCoords = redCoords;
    this.greenCoords = greenCoords;
    this.blueCoords = blueCoords;
    this.intensityCoords = intensityCoords;
    this.max = max;
    this.offsetX = 30;
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.setColor(Color.BLACK);
    graphics.drawLine(offsetX, this.max, 256 + offsetX, this.max);
    graphics.drawLine(offsetX, this.max, offsetX, 0);

    for (int i = 0; i < 255; i += 1) {
      if (i % 50 == 0 && i != 250) {
        graphics.drawString(i + "", i + 25, this.max + 20);
        graphics.drawLine(i + offsetX, this.max, i + offsetX, this.max + 5);
      }
      this.drawRed(i, graphics);
      this.drawGreen(i, graphics);
      this.drawBlue(i, graphics);
      this.drawIntesity(i, graphics);
    }
    graphics.drawString("256", 256 + (offsetX / 2), this.max + 20);
    graphics.drawLine(256 + offsetX, this.max, 256 + offsetX, this.max + 5);

    for (int i = 1; i < 5; i += 1) {
      int val = i * this.max / 5;
      graphics.drawLine(offsetX, this.max - val, offsetX - 5, this.max - val);
      graphics.drawString(val + "", offsetX - 30, this.max - val + 5);
    }
    graphics.drawLine(offsetX, 0, offsetX - 5, 0);
    graphics.drawString(this.max + "", offsetX - 30, 10);
  }


  // draw the intensity data points onto the chart.
  private void drawIntesity(int i, Graphics graphics) {
    int thisY = this.intensityCoords[i];
    int nextY = this.intensityCoords[i + 1];
    graphics.setColor(Color.black);
    graphics.drawLine(i + offsetX, (this.max - thisY), (i + 1) + offsetX, (this.max - nextY));
  }

  // draw the blue data points onto the chart.
  private void drawBlue(int i, Graphics graphics) {
    int thisY = this.blueCoords[i];
    int nextY = this.blueCoords[i + 1];
    graphics.setColor(Color.blue);
    graphics.drawLine(i + offsetX, (this.max - thisY), (i + 1) + offsetX, (this.max - nextY));
  }

  // draw the green data points onto the chart.
  private void drawGreen(int i, Graphics graphics) {
    int thisY = this.greenCoords[i];
    int nextY = this.greenCoords[i + 1];
    graphics.setColor(Color.green);
    graphics.drawLine(i + offsetX, (this.max - thisY), (i + 1) + offsetX, (this.max - nextY));
  }

  // draw the red data points onto the graph.
  private void drawRed(int i, Graphics graphics) {
    int thisY = this.redCoords[i];
    int nextY = this.redCoords[i + 1];
    graphics.setColor(Color.red);
    graphics.drawLine(i + offsetX, (this.max - thisY), (i + 1) + offsetX, (this.max - nextY));
  }

  /**
   * Returns the prefferedSize of this Histogram.
   *
   * @return the preffereed size of this Histogram panel.
   */
  public Dimension getPreferredSize() {
    return new Dimension(300, max + 100);
  }

  /**
   * Returns the maximum size of this Histogram.
   *
   * @return the maximum size of this Histogram panel.
   */
  public Dimension getMaximumSize() {
    return new Dimension(Integer.MAX_VALUE, max + 100);
  }
}

