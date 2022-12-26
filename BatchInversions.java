
import edu.duke.*;
import java.io.*;

public class BatchInversions {
       //I started with the image I wanted (inImage)
        public ImageResource makeInversion(ImageResource inImage) {
	     //I made a blank image of the same size
             ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
	      //for each pixel in outImage
		for(Pixel pixel: outImage.pixels()){
			//look at the corresponding pixel in inImage
                        Pixel inPixel= inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
                        
			//set pixel's red to average
                        pixel.setRed(255-inPixel.getRed());
			//set pixel's green to average
                        pixel.setGreen(255-inPixel.getGreen());
			//set pixel's blue to average
			pixel.setBlue(255-inPixel.getBlue());
                }   	

                return outImage;
		//outImage is your answer
        }
        //this method converts multiple invert images together by calling makeInversion method 
	public void selectAndConvert(){
	        //create a new directoryResource to open multiple files
                DirectoryResource dr= new DirectoryResource();
                //for every file you select
	        for(File f: dr.selectedFiles()){
	             //create a new imageResource to open an image
	             ImageResource inImage= new ImageResource(f);
	             //pass the image to the methid makeInversion to convert it
	             ImageResource invert= makeInversion(inImage);
	             invert.draw();
	   
                }
        }
        //this method saves a copy of the converted image
	public void doSave(){
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			ImageResource invert=makeInversion(image);
			//get the original file name of the image
			String fname = image.getFileName();
			//add the word "invert" before the original name
			String newName = "invert-" + fname;
			//set this name to the new inverted image
			invert.setFileName(newName);
			invert.draw();
			//save the new image 
			invert.save();
		}
	
        }


           
	//method to test makeInversion method
	public void testInversion() {
		ImageResource ir = new ImageResource();
		ImageResource invert = makeInversion(ir);
		invert.draw();
	}
}
