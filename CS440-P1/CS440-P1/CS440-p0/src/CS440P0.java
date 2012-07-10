import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;

/***********
 * The VideoSink class represents the entry point for high level
 * analysis of videos. Images are fed to the CS440Hw1 class via 
 * the receiveFrame. The videoSink has the ability to display 
 * images with the ImageViewer class.
 * 
 * @author Sam Epstein
 **********/
public class CS440P0 {

	//The window to display images
	ImageViewer imageViewer;
	
	//Simple counter for video cutoff
	long counter;
	
	//The constructor initializes the window for display
	CS440P0()
	{
		imageViewer=new ImageViewer("Image Viewer");
		counter = 0;
	}
	
	
	 
	/**
	 * The central function of VideoSink and the place where students
	 * can edit the code. receiveFrame function is given an image. The 
	 * body of the code will perform high level manipulations of the 
	 * image, then display the image in the imageViewer. The return values
	 * indicates to the the video source whether or not to keep sending 
	 * images.
	 * 
	 * @param frame The current frame of the video source/
	 * @param firstFrame Whether or not the frame is the first frame of the video
	 * @return true if the video source should continue, or false if the video source should stop.
	 */
	public boolean receiveFrame(CS440Image frame) {
	
		/**********
		 * Replace function with your code
		 **********/

/*		//this code block puts a small red rectangle in the upper left corner of the image
		int x=0;
		int y=0;
		Color c;
		if(imageViewer.getKey()=='r')
		{
			c = new Color(255,0,0);
		}
		else if(imageViewer.getKey()=='g')
		{
			c = new Color(0,255,0);
		}
		else if(imageViewer.getKey()=='b')
		{
			c = new Color(0,0,255);
		}
		else
		{
			c = new Color(0,0,0);
		}
		for(x=0; x<20; x++)
		{
			for(y=0; y<20; y++)
			{
				frame.set(x,y,c);
			}
		}
		
*/		
	/* Goal of Adam's modification - Color shifting.
	 * Red becomes green, green becomes blue, blue becomes red.
	 */
		Color c;
		int x = 0;
		int y = 0;
		int[] cRGBshift = new int[3];
		BufferedImage f1 = frame.getRawImage();
		BufferedImage combine = new BufferedImage(frame.width(), (2*frame.height()), BufferedImage.TYPE_INT_RGB);
		Graphics g = combine.getGraphics();
		g.drawImage(f1, 0, 0, null);
		for (x=0; x < frame.width(); x++){
			for (y=0; y < frame.height(); y++){
				c = frame.get(x, y);
				cRGBshift[0] = c.getGreen();
                cRGBshift[1] = c.getBlue();
                cRGBshift[2] = c.getRed();
				c = new Color(cRGBshift[0], cRGBshift[1], cRGBshift[2]);
				frame.set(x, y, c);
			}
		}
		g.drawImage(frame.getRawImage(), 0, f1.getHeight(), null);
//		imageViewer.showImage(f1);
		g.dispose();
		boolean shouldStop = displayImage(new CS440Image(combine)); 
		return 	shouldStop;
	}

	/**
	 * This function displays the passed image in a frame.
	 * @param image The image to be displayed
	 */
	public boolean displayImage(CS440Image image)
	{
		// Window is closed.
		if (!imageViewer.isShowing())
		{
			return false;
		}
		
		if(imageViewer == null) {
			// System.out.println("now we return false");
			return false;			
		}
			
		imageViewer.showImage(image);
		return true;
	}
	
/*	public boolean displayImage(CS440Image image, CS440Image image2)
	{
		// Window is closed.
		if (!imageViewer.isShowing())
		{
			return false;
		}
		
		if(imageViewer == null) {
			// System.out.println("now we return false");
			return false;			
		}
			
		imageViewer.showImage(image, image2);
		return true;
	}
*/
	/***
	 * Closes the window
	 */
	public void close()
	{
		if(imageViewer!=null)
		{
			this.imageViewer.dispose();
			imageViewer=null;
		}
	}

}
