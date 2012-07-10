/*
 * Main class for Hw1. 
 * 
 */
public class CS440P0Main {

	/**
	 * Entry point for the program
	 * @param args
	 */
	public static void main(String[] args) {

		try
		{
			
			//Initialize VideoSink
			CS440P0 dvs = new CS440P0();

			//Initialize VideoSource
			ExtVideoSource evs = new ExtVideoSource();
			evs.setup(dvs,500);			

			//start grab
			evs.run();
			// System.out.println("end of main");
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	static {
        System.loadLibrary("ExtVideoSource");
    }
	
	
}


