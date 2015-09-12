
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.Iterator;
import java.sql.*;
import java.util.*;

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
			
						
			// Main loop, all satelites:
			for ( Element actSat : satelites )
			{
				//TODO: change bitrate storing manner
				
				satData.position = 	 	actSat.childNode(1).childNode(1).childNode(1).childNode(0).childNode(0).childNode(0).toString();
				satData.name =		 	actSat.childNode(1).childNode(1).childNode(3).childNode(2).childNode(0).toString();
				satData.freq = 			actSat.childNode(1).childNode(1).childNode(5).childNode(0).toString();
				satData.polar =			actSat.childNode(1).childNode(1).childNode(7).childNode(0).toString();
				satData.transponder = 	actSat.childNode(1).childNode(1).childNode(9).childNode(0).childNode(0).toString();
				satData.bundle = 		actSat.childNode(1).childNode(1).childNode(11).childNode(0).childNode(0).toString();	
				satData.standard = 		actSat.childNode(1).childNode(1).childNode(13).childNode(0).toString();
				satData.modulation = 	actSat.childNode(1).childNode(1).childNode(15).childNode(0).toString();	
				satData.symbolRate = 	actSat.childNode(1).childNode(1).childNode(17).childNode(0).toString();						
				satData.FEC =			actSat.childNode(1).childNode(1).childNode(17).childNode(2).childNode(0).toString();
				
				// Sometimes provider is unknown:
				try
				{
					// If provider field doesn't exist, IndexOutOfBoundsException is thrown:
					satData.provider = 	actSat.childNode(1).childNode(1).childNode(19).childNode(0).childNode(0).toString();	
					satData.bitRate = 	actSat.childNode(1).childNode(1).childNode(19).childNode(1).toString();
				}
				catch ( IndexOutOfBoundsException e )
				{
					satData.provider = 	">Unknown<";
					satData.bitRate = 	actSat.childNode(1).childNode(1).childNode(19).childNode(0).toString();		
				}
				
				satData.NID = 			actSat.childNode(1).childNode(1).childNode(21).childNode(0).toString();		
				satData.TID = 			actSat.childNode(1).childNode(1).childNode(23).childNode(0).toString();

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
	//TODO: add websites addresses
	String position;
	String name;
	String freq;
	String polar;
	String transponder;
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
