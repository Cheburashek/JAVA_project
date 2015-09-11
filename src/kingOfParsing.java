
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.Iterator;
import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;





public class kingOfParsing 
{

	//File inputTxt = new File ( "downPage.txt");
	
	Document docToParse;
    Logger parserLogger = Logger.getLogger( kingOfParsing.class );

    
    // Constructor:
	kingOfParsing()
	{
		BasicConfigurator.configure();	
	}
	
	
	// ParseFile:
	public void parseFile( File stored, String address )
	{
		
		Document website;
		
		try
		{
			website = Jsoup.parse( stored, "UTF-8", address );
			
			Elements satelites = website.getElementsByClass ( "frq" ); // From the website html, satelites configuration
			satDataStorage satData = new satDataStorage ();
			
			
			// Main loop:
			for ( Element actSat : satelites )
			{
				
				//satData.
				
				
			}
			
		}
		catch ( IOException e )
		{			
			parserLogger.error ("File is not accessible!");			
		}

	}
	
	
	
	
	
	
}


// Class for parsed data storage:
final class satDataStorage
{
	
	String name;
	String freq;
	String polar;
	String bundle; 
	String standard;
	String modulation;
	String symbolRate;
	String FEC;
	String provider;
	String bitRate;
	String NID;		// Network ID
	String TID;		// Transponder ID
	
	// Constructor:
	satDataStorage(){}

}
