import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.nodes.Document;

import java.io.File;


public class mainApp 
{

	public static void main( String args[] )
	{
		
		kingOfDownloading download = new kingOfDownloading();
		kingOfParsing parser = new kingOfParsing();
		
		String address = new String("http://example.com/");	
		String location = System.getProperty("java.io.tmpdir")+"storedWeb.html";
		File stored = new File ( location );	// To be saved on disk
		
	    Logger mainLogger = Logger.getLogger( mainApp.class );
		BasicConfigurator.configure();
		mainLogger.debug("Application started");



		// Start of parsing:			
		parser.parseFile ( stored, address );	// TODO: check if file is ok
	
	}

}
