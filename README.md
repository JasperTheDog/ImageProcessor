# README

***All parts of the program are complete (including extra credit). Enjoy using our Image Processor :)***

***Use the available .jar file to run APP***

#
## Extra Credit Changes
### Masking
- **Model/ModelInterface** - Added overloaded masking methods for filter, colorTransformation, changeBrightness, greyscale,
- **Commands** - added private final field to applicable commands representing name of masked image, also created second constructor to assign this name
- **Controller(Text)** - added a few options for user to input in our command map like flipMask etc.
## Downsizing
- **Model/ModelInterface** - Created a downsize method
- **Commands** - created a downsize command
- **GUIcontroller** - added case for for actionperformed for downsize
- **GuiView** - added button and text fields for down size inputs


#
## Assignment 6
## Additions and Changes to Design
### src/controller/commands
- **Downsize (class)** - added a command object to support downsizing an image
- **ChangeBrightness (class)** - added a new constructor to take in another argument - the masked image - if requires. Also determines in its process method on how many arguments to pass to the model's method.
- **Filter (class)** - added a new constructor to take in another argument - the masked image - if requires. Also determines in its process method on how many arguments to pass to the model's method.
- **GreyScale (class)** - added a new constructor to take in another argument - the masked image - if requires. Also determines in its process method on how many arguments to pass to the model's method.
- **Tone (class)** - added a new constructor to take in another argument - the masked image - if requires. Also determines in its process method on how many arguments to pass to the model's method.
### src/controller
- **GUIController (class)** - added a new class to this package which controls the GUI based view of this program. Still extends the old controller interface and also acts like the action listener for the GUI.
- **TextImageProcessorControllerImpl (class)** - added new code to support working with masked images (added a new command to the map)

### src/converter
- **IOConverter (class)** - the class now uses the picture's convertToImage method to create the buffered image for a given picture. This reduces code duplication.

### src/model
- **ImageProcessor (interface)** - the interface has a bunch of new overloaded methods to support working with masked images. There is an additional new method to support downsizing of an image.
- **ProcessorImpl (class)** - the class now has to implement the above methods which work with masked images and downsizing.

### src/state
- **Picture (interface)** - now has a new method called convertToImage(). It takes in whether it supports a transparent pixel (as an integer) and returns a BufferedImage with that information. This helps in displaying the picture on the GUI and also in saving an image

- **Picture (interface)** - implements the new method added to the interface

### src/view/GUI
- **Histogram (class)** - a class which represents the histogram as a line chart for a picture in our model. It takes in 4 arrays containing frequency of each component's value and then draws all 4 on the histogram.
- **ImageGraphicalView (interface)** - an interface to represent the image view of the image processor. Has methods to set action listeners, get various command types, set the image, add the image to the image list, render error messages, and many more.
- **ImageGUI (class)** - a class which implements the ImageGraphicalView interface using java swing. Is able to neatly display a GUI for a user to work with an image processor. 

### src/
- **Application (class)** - edited the main method a bit to support more types of command line arguments.
<br>
<br>
#
## Assignment 5

## Additions and Changes to Design

### src/controller/commands
- **ExtensionHandler (class)** - added new extensions to the map which the program can support.
- **Filter (class)** - added a command object to support filters for an image.
- **Tone (class)** - added a command object to support different tone for an image.


### src/controller
- **TextImageProcessorControllerImpl (class)** 

     - the controller now handles all file IO. We realized the model did not need to have any knowledge about file names and paths and should only be dealing with Pictures.
     - the menu and extensible controller map have been updated to support new features.

### src/converter
- **IOConverter (abstract class)** - an abstract class which extends the ImageConverter interface. It's an abstract class which acts as a super class for all child classes which use the ImageIO class java provides.
- **ImageConverterBMP (class)** - a class that represents an image converter for the BMP file format. It can convert a picture to a bmp, and a bmp to a picture.
- **ImageConverterPNG (class)** - a class that represents an image converter for the PNG file format. It can convert a picture to a png, and a png to a picture.
- **ImageConverterJPEG (class)** - a class that represents an image converter for the JPEG file format. It can convert a picture to a jpeg, and a jpeg to a picture.
- **ImageConverterJPG (class)** - a class that represents an image converter for the JPG file format. It can convert a picture to a jpg, and a jpg to a picture.

### model/defaults
- **Filters (class)** - class containing *public, static, and final* constants to represent some default filters developers might want to use. These are made accessible for convenience's sake.
- **GreyScaleMaps (class)** - class containing *public, static, and final* constants to represent some default grey scale maps developers might want to use. These are made accessible for convenience's sake.
- **Transformations (class)** - class containing *public, static, and final* constants to represent some default color transformations developers might want to use. These are made accessible for convenience's sake.

### model/functions
- **ColorTransformation (class)** - a function object which is used as a helper to create a new pixel after applying a given color transformation to it.
- **Kernal (class)** - a function object which is used as a helper to return a new integer after doing operations on two matrices.

### model/
- **ImageProcessor (interface)** 
    - interface has been changed to have only 7 methods - load, greyscale, change brightness, flip horizontally, flip vertically, color transformations and filter.
    - the greyscale now also takes in a function object (sort of a map) which takes in a pixel and returns a pixel. We have provided some default maps for developers to use. This design increases extensibility while keeping convenience for the developer to not write too much of their own code.
    - the filter takes in a kernel (a matrix) to work on. We have provided some default kernels for developers to use. This design increases extensibility while keeping convenience for the developer to not write too much of their own code.
    - the color transformation takes in 3x3 matrix to work with. We have provided some default matrices for developers to use. This design increases extensibility while keeping convenience for the developer to not write too much of their own code. 

    <br>
- **ImageProcessorImpl (class)** - filter, greyscale and colortransform now have new implementations based on their changed signature in the interface. They call on the appropriate private helpers (if necessary) which call on the appropriate function objects. 

### src/
- **Application (class)** - Updated the main method to be able to support command line arguments

#

__Script:__ Write the following after the program starts running (via running Application class’s main method) by typing into console.


load Mikey.ppm mikey 

flip vertical mikey flippedMikey

save Mikey2.ppm flippedMikey

brighten 50 mikey brightMikey

save Mikey3.ppm brightMikey

greyscale green mikey greyscaleMikey

save Mikey4.ppm greyscaleMikey

darken 30 greyscaleMikey darkMikey

save Mikey5.ppm darkMikey

tone sepia mikey sepiaMikey

save Mikey6.ppm sepiaMikey

filter blur mikey blurMikey

save Mikey7.ppm blurMikey

q

__Location where image taken__ https://free-images.com/display/bird_wildlife_sky_clouds.html

__Fair use from site:__  This image was acquired from Pixabay and Pickupimage. It was marked as Public Domain or CC0 and is free to use. To verify, go to the source and check the information there.
#

## Design information

### src/controller
- __ImageProcessorController (interface)__ - An interface to represent a controller for an image processor. Has single method called runProcessor() which runs the processor.

- __TextImageProcessorControllerImpl (class)__ - A class which implements an ImageProcessorController. It is a text based controller and deals with text inputs/outputs (text based view). Best suited for a text based view, but is made to work with any class implementing the model and view interface.

### src/controller/Commands
- **ProcessorCommand (interface)** - an interface to represent all processor commands that are used as a part of the command design pattern in the controller. It has one method called process which takes in a processor and does the appropriate process depending on which class calls it.

- **ChangeBrightness (class)** - a command class which applies the changeBrightness method to the processor.

- **Flip (class)** - a command class which applies the flip method to the processor (handles both horizontal and vertical flips).

- **GreyScale (class)** - a command class which applies the greyscale method to the processor (handles all types of greyscaling - red, green, blue, intensity, luma, value).

- **Load (class)** - a command class which applies the load method to the processor.

- **Save (class)** - a command class which applies the save method to the processor.

- **ExtensionHandler (class)** - a class which contains all supported extensions and returns the appropriate extension when a file path is given (if supported).

### src/converter
- **ImageConverter (interface)** - an interface representing image converters. It has two methods - convertExport and convertImport. convertExport converts a picture to a file (depending on the class), and convertImport converts a file into a picture (also depending on the class)

- **ImageConverterPPM (class)** - a class that represents an image converter for the PPM file format. It can convert a picture to a ppm, and a ppm to a picture.

### src/model
- **ImageProcessor (interface)** - an interface to represent an image processor which supports multiple ways to process/edit an image.
- **ProcessorImpl (class)** - an implementation of an ImageProcessor which stores Pictures in a map of Strings to Pictures. It supports editing on all Pictures that use PixelColors (an interface to represent a pixel).
- **ImageProcessorView (interface)** - an interface to represent all the operations that a view might need access of from the model (none of these operations can modify a Picture. Methods include getting an image, getting all images, and getting all the images’ names.
- **ProcessorViewImpl (class)** - an implementation of an ImageProcessorView. Uses a delegate of an ImageProcessor and calls methods using that delegate.

### src/model/functions
- AddComponent (class) - a function object which is used as a helper for creating a new pixel after changing the brightness of it.

### src/state
- **Picture (interface)** - an interface to represent an image. Has methods which can only query its state but can’t modify it (getColorAt a specific (row, col), height, width)
- **PixelColorPicture (class)** - a class which is an implementation of the Picture interface. It uses PixelColors to represent the pixels and represents an image as a 2D array of PixelColor.

### src/state/color
- **PixelColor (interface)** - an interface to represent a pixel. It has methods to query a pixels property but no method can alter the pixel.
- **JavaPixel (class)** - a class which uses java’s Color class to represent an implementation of a PixelColor.

### src/view
- **ImageView (interface)** - an interface to represent the view for an image processor. Has methods that can render a message, render an image, and render image names. 
- **TextImageView (class)** - a class representing a text-based implementation of the ImageView interface. The user can see all outputs of this view in text. 

### src/
- **Application (class)** - The class which has the main method to run the program. 

### images/ 
has multiple ppms used for code testing purposes.

### res/
has multiple ppms that can be viewed visually for intuitive testing purposes.
