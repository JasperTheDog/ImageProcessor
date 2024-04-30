package view.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import model.ImageProcessorView;
import state.Picture;
import state.color.PixelColor;

/**
 * An implementation of the ImageView interface. Uses java's java swing
 * to make a graphical user interface to interact with our image processor.
 */
public class ImageGUI extends JFrame implements ImageGraphicalView {

  private JComboBox<String> flipCombo;

  private JTextField assignField;

  private JComboBox<String> imageCombo;

  private JComboBox<String> filterCombo;

  private JComboBox<String> gsCombo;

  private JComboBox<String> toneCombo;

  private JTextField cbField;

  private JTextField downWField;

  private JTextField downHField;

  private String imageName;

  private final JLabel imageLabel = new JLabel();

  private JPanel histogram;

  private JButton filterButton;

  private JButton flipButton;

  private JButton gsButton;

  private JButton toneButton;

  private JButton cbButton;

  private JButton downButton;

  private JButton load;

  private JButton save;

  private JButton imageButton;

  private final ImageProcessorView modelView;

  /**
   * A constructor to create an object of this class with the given modelView, sets up all the
   * panels of the GUI in the process. Also sets the program to close on exit.
   *
   * @param modelView the modelView used to create this class. It helps in getting images
   *                  from the model
   */
  public ImageGUI(ImageProcessorView modelView) {
    super();
    this.modelView = modelView;
    this.setTitle("Image processor");
    this.setSize(1000, 1000);

    // creates the main panel with a horizontal layout
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    // adds all components of the left of the gui to the main panel
    this.addLeft(mainPanel);
    // adds all components in the middle of the gui to the main panel
    this.addMiddle(mainPanel);

    // adds the main panel to this view
    this.add(mainPanel);

    // exits when the close button is clicked
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // helper method to create the middle of the main panel and add to it
  private void addMiddle(JPanel mainPanel) {

    // create the middle panel with a vertical layout
    JPanel middle = new JPanel();
    middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
    middle.setPreferredSize(new Dimension(450, 1000));

    // create the panel which would store the image
    // add scroll bars to it
    JPanel image = new JPanel();
    image.setBorder(BorderFactory.createTitledBorder("image"));
    image.add(this.imageLabel);
    JScrollPane scrollImage = new JScrollPane(image);
    scrollImage.setPreferredSize(new Dimension(450, 700));
    // add the panel to the middle panel
    middle.add(scrollImage);

    // create the panel which would store the histogram
    // add scroll bars to it
    histogram = new JPanel();
    histogram.setBorder(BorderFactory.createTitledBorder("histogram"));
    JScrollPane histScroll = new JScrollPane(histogram);
    histScroll.setPreferredSize(new Dimension(450, 300));
    // add the panel to the middle panel
    middle.add(histScroll);

    // add the whole middle panel to the main panel
    mainPanel.add(middle);
  }

  // helper method to create the left of the main panel and add to it
  private void addLeft(JPanel mainPanel) {
    // create the left panel with a vertical layout
    // give it a border with title commands
    JPanel left = new JPanel();
    left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
    left.setBorder(BorderFactory.createTitledBorder("commands"));
    left.setPreferredSize(new Dimension(300, 1000));
    left.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));

    // create the panel which holds the assign name text field
    // the panel is horizontal
    JPanel assignPanel = new JPanel();
    assignPanel.setMaximumSize(new Dimension(250, 100));
    assignPanel.setLayout(new BoxLayout(assignPanel, BoxLayout.X_AXIS));
    assignPanel.add(new JLabel("assign name:"));
    // create the text field to enter the assigned name
    assignField = new JTextField();
    assignField.setMaximumSize(new Dimension(200, 20));
    // add the text field to the assign panel
    assignPanel.add(assignField);
    // add the assign panel to the left panel
    left.add(assignPanel);

    // create the panel which holds the flip types and button
    // the panel is horizontal
    JPanel flipPanel = new JPanel();
    flipPanel.setMaximumSize(new Dimension(250, 100));
    flipPanel.setLayout(new BoxLayout(flipPanel, BoxLayout.LINE_AXIS));
    flipPanel.add(new JLabel("flip:"));
    // create the combo box to hold flip types
    flipCombo = new JComboBox<>();
    flipCombo.setMaximumSize(new Dimension(100, 20));
    flipCombo.addItem("horizontal");
    flipCombo.addItem("vertical");
    // add the combo box to the panel
    flipPanel.add(flipCombo);
    // create the button to flip
    flipButton = new JButton("convert");
    flipButton.setActionCommand("flip");
    // add the button to the panel
    flipPanel.add(flipButton);
    // add the flip panel to the left panel
    left.add(flipPanel);

    // create the panel which holds the filter types and button
    // the panel is horizontal
    JPanel filterPanel = new JPanel();
    filterPanel.setMaximumSize(new Dimension(250, 100));
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.LINE_AXIS));
    filterPanel.add(new JLabel("filter:"));
    // create the combo box to hold filter types
    filterCombo = new JComboBox<>();
    filterCombo.setMaximumSize(new Dimension(100, 20));
    filterCombo.addItem("blur");
    filterCombo.addItem("sharpen");
    // add the combo box to the panel
    filterPanel.add(filterCombo);
    // create the button to filter
    filterButton = new JButton("convert");
    filterButton.setActionCommand("filter");
    // add the button to the panel
    filterPanel.add(filterButton);
    // add the filter panel to the left panel
    left.add(filterPanel);

    // create the panel which holds the greyscale types and button
    // the panel is horizontal
    JPanel gsPanel = new JPanel();
    gsPanel.setMaximumSize(new Dimension(270, 100));
    gsPanel.setLayout(new BoxLayout(gsPanel, BoxLayout.LINE_AXIS));
    gsPanel.add(new JLabel("greyscale:"));
    // create the combo box to hold filter types
    gsCombo = new JComboBox<>();
    gsCombo.setMaximumSize(new Dimension(100, 20));
    gsCombo.addItem("blue");
    gsCombo.addItem("green");
    gsCombo.addItem("intensity");
    gsCombo.addItem("luma");
    gsCombo.addItem("red");
    gsCombo.addItem("value");
    // add the combo box to the panel
    gsPanel.add(gsCombo);
    // create the greyscale button
    gsButton = new JButton("convert");
    gsButton.setActionCommand("greyscale");
    // add the button to the panel
    gsPanel.add(gsButton);
    // add the greyscale panel to the left panel
    left.add(gsPanel);

    // create the panel which holds the tone types and button
    // the panel is horizontal
    JPanel tonePanel = new JPanel();
    tonePanel.setMaximumSize(new Dimension(250, 100));
    tonePanel.setLayout(new BoxLayout(tonePanel, BoxLayout.LINE_AXIS));
    tonePanel.add(new JLabel("tone:"));
    // create the combo box to hold tone types
    toneCombo = new JComboBox<>();
    toneCombo.setMaximumSize(new Dimension(100, 20));
    toneCombo.addItem("sepia");
    toneCombo.addItem("greyscale");
    // add the combo box to the panel
    tonePanel.add(toneCombo);
    // create the tone button
    toneButton = new JButton("convert");
    toneButton.setActionCommand("tone");
    // add the button to the panel
    tonePanel.add(toneButton);
    // add the tone panel to the left panel
    left.add(tonePanel);

    // create the panel which holds the brightness text field and button
    // the panel is horizontal
    JPanel cbPanel = new JPanel();
    cbPanel.setMaximumSize(new Dimension(250, 100));
    cbPanel.setLayout(new BoxLayout(cbPanel, BoxLayout.LINE_AXIS));
    cbPanel.add(new JLabel("change brightness:"));
    // create the text field to enter the change in brightness
    cbField = new JTextField(10);
    cbField.setMaximumSize(new Dimension(200, 20));
    // add the text field to the panel
    cbPanel.add(cbField);
    // create the change brightness button
    cbButton = new JButton("convert");
    cbButton.setActionCommand("change brightness");
    cbPanel.add(cbButton);
    // add the change brightness panel to the left panel
    left.add(cbPanel);

    // create the panel which holds the Downsize text fields and button
    // the panel is horizontal
    JPanel downPanel = new JPanel();
    downPanel.setMaximumSize(new Dimension(250, 100));
    downPanel.setPreferredSize(new Dimension(250, 100));
    downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
    downPanel.setBorder(BorderFactory.createTitledBorder("Downsize"));
    // create the text field to enter the new width ratio
    downPanel.add(new JLabel("width:"));
    downWField = new JTextField(5);
    downWField.setMaximumSize(new Dimension(10, 20));
    downPanel.add(downWField);
    // create the text field to enter the new height ratio
    downPanel.add(new JLabel("height:"));
    downHField = new JTextField(5);
    downHField.setMaximumSize(new Dimension(10, 20));
    downPanel.add(downHField);
    // create the downsize button
    downButton = new JButton("convert");
    downButton.setActionCommand("downsize");
    downPanel.add(downButton);
    // add the change brightness panel to the left panel
    left.add(downPanel);

    // create the panel which holds the load button
    JPanel loadPanel = new JPanel();
    loadPanel.setPreferredSize(new Dimension(250, 50));
    loadPanel.setMaximumSize(new Dimension(250, 50));
    // create the load button
    load = new JButton("load");
    load.setActionCommand("load");
    // add the button to the panel
    loadPanel.add(load);
    // add the load panel to the left panel
    left.add(loadPanel);

    // create the panel which holds the save button
    JPanel savePanel = new JPanel();
    loadPanel.setPreferredSize(new Dimension(250, 50));
    savePanel.setMaximumSize(new Dimension(250, 50));
    // create the save button
    save = new JButton("save");
    save.setActionCommand("save");
    // add the button to the panel
    savePanel.add(save);
    // add the save panel to the left panel
    left.add(savePanel);

    // create the panel which holds the combo box for the images saved
    // in the model and the button to switch
    JPanel images = new JPanel();
    images.setPreferredSize(new Dimension(250, 100));
    images.setMaximumSize(new Dimension(250, 100));
    images.setLayout(new BoxLayout(images, BoxLayout.X_AXIS));
    images.setBorder(BorderFactory.createTitledBorder("current image"));
    // create the combo box
    imageCombo = new JComboBox<>();
    imageCombo.setMaximumSize(new Dimension(100, 20));
    // add the combo box to the panel
    images.add(imageCombo);
    // create the switch button
    imageButton = new JButton("switch");
    imageButton.setActionCommand("switch");
    // add the button to the panel
    images.add(imageButton);
    // add the images panel to the left panel
    left.add(images);

    // add the left panel to the main panel
    mainPanel.add(left);
  }


  @Override
  public void acceptActionListener(ActionListener actionListener) {
    flipButton.addActionListener(actionListener);
    filterButton.addActionListener(actionListener);
    gsButton.addActionListener(actionListener);
    toneButton.addActionListener(actionListener);
    cbButton.addActionListener(actionListener);
    load.addActionListener(actionListener);
    save.addActionListener(actionListener);
    imageButton.addActionListener(actionListener);
    downButton.addActionListener(actionListener);
  }

  @Override
  public String getAssignName() {
    String str = this.assignField.getText();
    if (str.length() == 0) {
      throw new IllegalArgumentException("Please assign a name to the new image!");
    }
    this.assignField.setText("");
    return str;
  }

  @Override
  public String getFlipType() {
    return (String) this.flipCombo.getSelectedItem();
  }

  @Override
  public String getFilterType() {
    return (String) this.filterCombo.getSelectedItem();
  }

  @Override
  public String getToneType() {
    return (String) this.toneCombo.getSelectedItem();
  }

  @Override
  public int getChangeBrightnessValue() {
    try {
      return Integer.parseInt(this.cbField.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("please enter an integer");
    }

  }

  @Override
  public String getGreyscaleType() {
    return (String) this.gsCombo.getSelectedItem();
  }

  @Override
  public String getSelectedImage() {
    return (String) this.imageCombo.getSelectedItem();
  }

  @Override
  public void addToImageList(String name) {
    for (int i = 0; i < this.imageCombo.getItemCount(); i += 1) {
      if (this.imageCombo.getItemAt(i).equals(name)) {
        return;
      }
    }
    this.imageCombo.addItem(name);
  }

  @Override
  public void renderErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "try again!",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public double getDownsizeW() {
    try {
      return Double.parseDouble(this.downWField.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("please enter a decimal value between 0 and 1");
    }

  }

  @Override
  public double getDownsizeH() {
    try {
      return Double.parseDouble(this.downHField.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("please enter a decimal value between 0 and 1");
    }
  }

  @Override
  public String getImageName() {
    return this.imageName;
  }

  @Override
  public void setImage(String imageName) {
    Picture pic = this.modelView.getImage(imageName);
    this.imageName = imageName;
    ImageIcon icon = new ImageIcon(pic.convertToImage(BufferedImage.TYPE_INT_ARGB));
    this.imageLabel.setIcon(icon);
    this.imageCombo.setSelectedItem(imageName);
    this.createHistogram(pic);
  }

  // a private helper to create a histogram for the given picture
  private void createHistogram(Picture pic) {
    histogram.removeAll();
    int[] red = this.getRed(pic);
    int[] green = this.getGreen(pic);
    int[] blue = this.getBlue(pic);
    int[] intensity = this.getIntensity(pic);
    int max = this.getMax(red, green, blue, intensity);
    Histogram h = new Histogram(red, green, blue, intensity, max);
    histogram.add(h);
  }

  // a private helper to get the pixel value which has the highest frequency
  // out of the four given arrays to help create the histogram
  private int getMax(int[] red, int[] green, int[] blue, int[] intensity) {
    int max = 0;
    for (int i = 0; i < 256; i += 1) {
      max = Math.max(red[i], Math.max(blue[i], Math.max(green[i], Math.max(intensity[i], max))));
    }
    return max;
  }

  // a private helper to get an array which represents which pixel value has how much frequency
  // for the given picture's intensity property
  private int[] getIntensity(Picture pic) {
    return this.getValues(pic, (PixelColor::intensity));
  }

  // a private helper to get an array which represents which pixel value has how much frequency
  // for the given picture's blue property
  private int[] getBlue(Picture pic) {
    return this.getValues(pic, (PixelColor::getBlue));
  }

  // a private helper to get an array which represents which pixel value has how much frequency
  // for the given picture's green property
  private int[] getGreen(Picture pic) {
    return this.getValues(pic, (PixelColor::getGreen));
  }

  // a private helper to get an array which represents which pixel value has how much frequency
  // for the given picture's red property
  private int[] getRed(Picture pic) {
    return this.getValues(pic, (PixelColor::getRed));
  }

  // a private helper to get an array which represents which pixel value has how much frequency
  // for the given picture depending on which property is wanted (represented by the map function)
  private int[] getValues(Picture pic, Function<PixelColor, Integer> map) {
    int[] values = new int[256];
    for (int row = 0; row < pic.getHeight(); row += 1) {
      for (int col = 0; col < pic.getWidth(); col += 1) {
        int val = map.apply(pic.getColorAt(row, col));
        values[val] = values[val] + 1;
      }
    }
    return values;
  }

}