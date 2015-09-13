import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.io.File;


public class mainApp 
{	
	// Location on the disk to store downloaded page in .html:
	static String locationWeb = "D:/JAVA/JAVA_project/storedWeb.html";
	// Location on the disk to store created SQL database:
	static String locationSQL = "D:/JAVA/JAVA_project/sqlData.db";
	// Address of the website to parse:
	static String address = new String ( "http://pl.kingofsat.net/pos-13E_pol.php" );
	
	
	//***********************************************************************
	// main:
	public static void main( String args[] )
	{
	    Logger mainLogger = Logger.getLogger( mainApp.class );
		BasicConfigurator.configure();
		mainLogger.debug("Application started!");
		
		kingOfDownloading downloader = new kingOfDownloading();
		kingOfParsing parser = new kingOfParsing();
				
		File file = new File ( locationWeb );	// To be saved on disk
				
		// Main part of the program:
		downloader.getFile ( file, address, locationWeb );	// Start of downloading and storing page in .html
		parser.parseFile ( file, address, locationSQL );	// Start of parsing		
	}
}
