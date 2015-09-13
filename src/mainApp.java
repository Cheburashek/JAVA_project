
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.io.File;


//TODO: loggers names in logs
//TODO: udawac przegladarke + timeouts


// problem ze stat -> null

public class mainApp 
{
	static String locationWeb = "D:/JAVA/JAVA_project/storedWeb.html";
	static String locationSQL = "D:/JAVA/JAVA_project/sqlData.db";
	static String address = new String ( "http://pl.kingofsat.net/pos-13E_pol.php" );
	
	public static void main( String args[] )
	{

		kingOfDownloading downloader = new kingOfDownloading();
		kingOfParsing parser = new kingOfParsing();
		
		
		File file = new File ( locationWeb );	// To be saved on disk
		
	    Logger mainLogger = Logger.getLogger( mainApp.class );
		BasicConfigurator.configure();
		mainLogger.debug("Application started");

		
		// Main part of program:
		downloader.getFile ( file, address, locationWeb );	// Start of downloading and storing page in .html
		parser.parseFile ( file, address, locationSQL );		// Start of parsing
		
	}

}
