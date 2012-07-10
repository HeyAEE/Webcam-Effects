import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

/**
 * The ImageViewer is a simple window which displays an image.
 * 
 * @author Sam Epstein, Rui Li
 *
 */
@SuppressWarnings("serial")
public class ImageViewer extends JFrame implements WindowListener, KeyListener {

	
	/**
	 * Private helper class to display image panel
	 * @author Sam Epsetin, Rui Li
	 *
	 */
	@SuppressWarnings("serial")
	private class IPanel extends JLabel  implements KeyListener{
		private Image img = null;
		  	public int xOff = 0;
		  	public int yOff = 0;
		  	private int width;
		  	private int height;

		  	
		  	/*
		  	@Override
			public int getHeight() {
				// TODO Auto-generated method stub
				return height;
			}

			@Override
			public int getWidth() {
				// TODO Auto-generated method stub
				return width;
			}*/
		  	
		
		public IPanel() {
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setVerticalAlignment(JLabel.TOP);
		}
		
		public void setImage(Image i)  {
			img = i;
		}

		public void paint(Graphics g) {
		   if (img != null) {
	    	  width = img.getWidth(null);
	    	  height = img.getHeight(null);
	    	  Dimension dim = this.getSize();
	    	  if (width > dim.width || height > dim.height) {
		    	  double scale = height / (double) width;
	   			  if (width > height)
	   				  scale = dim.height / (double) height;
	   			  else
	   				  scale = dim.width / (double) width;
	   			  width = (int) (scale * (double) width);
	   			  height = (int) (scale * (double) height);
	    	  }
	    		  xOff = (dim.width - width) / 2;
	    		  yOff = (dim.height - height) / 2;
			  g.drawImage(img, xOff, yOff, width, height, null);
		   }
		}
	  	public void keyTyped(KeyEvent e) {
	  		charTyped = e.getKeyChar();
	  	}
	  	public void keyPressed(KeyEvent e) {}
	  	public void keyReleased(KeyEvent e) {}

	}
	
	
	IPanel imageLabel;
	private char charTyped;
  	  	
	public ImageViewer()
	{
		this("Image viewer");
	}
	public ImageViewer(String title)
	{
		super(title);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		imageLabel=new IPanel();
		contentPane.add(imageLabel,BorderLayout.CENTER);
		
		//imageIcon = new ImageIcon();
		//contentPane.add(imageIcon);
		
		this.addWindowListener(this);
		this.addKeyListener(this);
		setVisible(true);
	}

	
	/**
	 * Displays and resizes the image for display.
	 * @param image
	 */
	public void showImage(CS440Image image)
	{
		Image img = image.getRawImage();
		showImage(img);
	}
	
	public void showImage(Image img)
	{
		imageLabel.setImage(img);
		this.setSize(img.getWidth(null), img.getHeight(null));
		//imageLabel.repaint();
		
		this.repaint();
	}
	
/*	public void showImage(CS440Image im1, CS440Image im2){
		Image i1 = im1.getRawImage();
		imageLabel.setImage(i1);
		Image i2 = im2.getRawImage();
		imageLabel.setImage(i2);
		this.setSize(greater(i1, i2, 'w'), (i1.getHeight(null) + i2.getHeight(null)));
		
		this.repaint();
	}

	*/
	
	//greater is a private function that returns the larger of a chosen dimension between two images.
	// First two inputs are Image objects. Third input is a char indicating which dimension is to
	// be tested. Default is height. Plug in 'w' or 'W' for width.
	
	private int greater(Image a, Image b, char c){
		int a1 = 0;
		int b1 = 0;
		if (c == 'w' || c == 'W'){
			a1 = a.getWidth(null);
			b1 = b.getWidth(null);
		} else {
			a1 = a.getHeight(null);
			b1 = b.getHeight(null);
		}
		if (a1 > b1) {
			return a1;
		} else {
			return b1;
		}
	}

  	public void keyTyped(KeyEvent e) {
  		charTyped = e.getKeyChar();
  		System.out.println("key is " + charTyped);
  	}
  	public void keyPressed(KeyEvent e) {}
  	public void keyReleased(KeyEvent e) {}

  	public char getKey()
  	{
  		return charTyped;
  	}

@Override
	public void windowActivated(WindowEvent arg0) {
		System.out.println("windowActivated");
	}	
	public void windowClosed(WindowEvent arg0){
		System.out.println("windowClosed");
	}
	public void windowDeactivated(WindowEvent arg0){
		System.out.println("windowDeactivated");
	}
	public void windowDeiconified(WindowEvent arg0) {
		System.out.println("windowDeiconified");
	}
	public void windowIconified(WindowEvent arg0) {
		System.out.println("windowIconified");
	}
	public void windowOpened(WindowEvent arg0) {
		System.out.println("windowOpened");
	}
	public void windowClosing(WindowEvent arg0) {
		System.out.println("windowClosing");
		dispose();
	}

}
