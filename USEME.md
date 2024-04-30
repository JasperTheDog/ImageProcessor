
# USING THE IMAGE PROCESSOR

## USING GUI
**Command line argument**- ***java -jar jar-file-name.jar***

Using the GUI is fairly easy and intuitive, with a very simple yet calming UX. The UI is split into three sections - left, right top, and right bottom. 
### Left
The left side of the UI has all the command buttons. 
- **Load** - to load an iamge, just click on the load button and load an image using the file explorer (only supported files - png, bmp, jpeg, jpg, and ppm). All other file types will trigger an error pop up.
- **Flipping** - first, assign a name to the new image. Then choose the type of flip you want - horizontal or vertical. Finally, click the convert to the right of the dropdown and see the result.
- **Filter** - first, assign a name to the new image. Then choose the type of filter you want - blur or sharpen. Finally, click the convert to the right of the dropdown and see the result.
- **Greyscale** - first, assign a name to the new image. Then choose the type of greyscale you want - red, green, blue, value, intensity, or luma. Finally, click the convert to the right of the dropdown and see the result.
- **Tone** - first, assign a name to the new image. Then choose the type of tone you want - sepia or greyscale. Finally, click the convert to the right of the dropdown and see the result.
- **Flipping** - first, assign a name to the new image. Then choose the type of flip you want - horizontal or vertical. Finally, click the convert to the right of the dropdown and see the result.
- **Change brightness** - first, assign a name to the new image. Then, type into the text box by how much do you want to change brightness (positve number means brighten and negative means darken. Pixel values are kept between 0 and 255 at all times regardless of the value of the integer inputed). Finally, click the convert button to the right of the text field to see the result.
- **Downsize** - first, assign a name to the new image. Then, type in the ratio of width you want with respect to the current width (and same for height - value should be a number between 0 and 1 exclusive). Finally, click the convert button to the right of the text fields to see the result.
- **Switch image** - to check out a previously saved image (in the processor and not necessarily on the local computer), just select an image name from the dropdown and click the switch button.
- **Save** - to save the current image, click the save button and navigate to where you want to save the image using the file explorer. Please give an appropriate extension (png, jped, jpg, bmp, and ppm) as other extensions will trigger an error message pop up.
- **Quit** - just click the close button on the top left and the program will quit.
### Right top
This section contains the image. If the image is bigger than the allocated panel size, scroll bars are created to be able to view sections of the image at a time.
### Right bottom
This section contains the histogram of the current image. If the histogram is biffer than the allocated panel size, scroll bars are created to view sections of the histogram at a time.
### Miscellaneous 
- If you accidentally do anything wrong, self explanatory error messages will pop up to help guide you how to correct your commands and the program won't break.
#

## USING CONSOLE 
**Command line argument**- ***java -jar jar-file-name.jar -text***
### Menu of commands:
- **load file-path assign-name**
- **brighten value image-name assign-name**
- **darken value image-name assign-name**
- **flip flip-type image-name assign-name**
    - flip-types supported - vertical, horizontal
- **greyscale greyscale-type image-name assign-name**
    -  greyscale-types supported - red, green, blue, luma, value, intensity
- **tone tone-type image-name assign-name**
    - tone-types supported - sepia, greyscale(intensity)
- **filter filter-type image-name assign-name**
    - filter-types supported - sharpen, blur
- **save file-path image-name**
- **brightenMask value image-name assign-name mask-name**
- **darkenMask value image-name assign-name mask-name**
- **greyscaleMask greyscale-type image-name assign-name mask-name**
    -  greyscale-types supported - red, green, blue, luma, value, intensity
- **toneMask tone-type image-name assign-name mask-name**
    - tone-types supported - sepia, greyscale(intensity)
- **filterMask filter-type image-name assign-name mask-name**
    - filter-types supported - sharpen, blur
- **q/quit (quit the program)**
 -**m/menu (reprint the menu)**

#

## USING A SCRIPT FILE 
**Command line argument**- ***java -jar jar-file-name.jar -file file-path***

- Arguments should look like - ***-file file-path*** (any bad arguments won't be supported and the program might throw an exception)
- All scripts should end in ***q*** or ***quit*** otherwise the program will throw an exception
- Scripiting supports only (and all) commands that the text based UI supports.
### Notes about using the JAR
 - can copy the jar from outside of the artifacts foler into another folder to use it (or can choose to access it from within the artifacts folder)
 - the relative path for loading and saving images will work for the folder to which the jar was copied to.
 