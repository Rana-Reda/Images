
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
	//I started with the image I wanted (inImage)
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for(Pixel pixel: outImage.pixels()){
			//look at the corresponding pixel in inImage
                        Pixel inPixel= inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
                        int average=(inPixel.getRed()+ inPixel.getGreen()+ inPixel.getBlue())/3;
			//set pixel's red to average
                        pixel.setRed(average);
			//set pixel's green to average
                        pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
                }   	

                return outImage;
		//outImage is your answer
	}
        //this method converts multiple images into gray together by calling makeGray method 
	public void selectAndConvert(){
	        //create a new directoryResource to open multiple files
                DirectoryResource dr= new DirectoryResource();
	        //for every file you select
                for(File f: dr.selectedFiles()){
	             //create a new imageResource to open an image
                     ImageResource inImage= new ImageResource(f);
	             //pass the image to the methid makeGray to convert it
                     ImageResource gray= makeGray(inImage);
	             //draw the gray converted image 
                     gray.draw();
	   
                }
        }
        //this method saves a copy of the converted image
	public void doSave(){
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			ImageResource gray=makeGray(image);
			//get the original file name of the image
			String fname = image.getFileName();
			//add the word "gray" before the original name
			String newName = "gray-" + fname;
			//set this name to the new gray image
			gray.setFileName(newName);
			gray.draw();
			//save the new image with the new name
			gray.save();
		}
	
        }
        //this method testes the makeGray method
	public void testGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}
}
