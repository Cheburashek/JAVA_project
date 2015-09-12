import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.nodes.Document;

import java.io.File;


//TODO: loggers names in logs
//TODO: udawac przegladarke + timeouts
public class mainApp 
{

	public static void main( String args[] )
	{
		
		kingOfDownloading downloader = new kingOfDownloading();
		kingOfParsing parser = new kingOfParsing();
		
		String address = new String ( "http://pl.kingofsat.net/pos-13E_pol.php" );	
		String location = "D:/JAVA/JAVA_project/storedWeb.html";
		File file = new File ( location );	// To be saved on disk
		
	    Logger mainLogger = Logger.getLogger( mainApp.class );
		BasicConfigurator.configure();
		mainLogger.debug("Application started");

		
		// Main part of program:
		
		downloader.getFile ( address, file, location );	// Start of downloading and storing page in .html
		parser.parseFile ( file, address );		// Start of parsing
	
	}

}
