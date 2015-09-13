import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

// Class to parse a website

public class kingOfParsing 
{

	//File inputTxt = new File ( "downPage.txt");
	
	Document docToParse;
    Logger parserLogger = Logger.getLogger( kingOfParsing.class );
    
    //***********************************************************************
    // Constructor:
	kingOfParsing()
	{
		BasicConfigurator.configure();	
	}	
	
	
	//***********************************************************************
	// ParseFile:
	public void parseFile( File stored, String address, String locationSQL )
	{
		
		Document website;
		Elements satellites;
		kingOfSQL dataBase = new kingOfSQL( locationSQL );
		satDataStorage satData = new satDataStorage ();

		
		try
		{
			website = Jsoup.parse( stored, "UTF-8", address );
			
			satellites = website.getElementsByClass ( "frq" ); // From the website html, satelites configuration
		
						
			// Main loop, all satellites:
			for ( Element actSat : satellites )			
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
				satData.symbolRate = 	actSat.childNode(1).childNode(1).childNode(17).childNode(0).childNode(0).toString();						
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
				
				// Store into a database:
				dataBase.storeSatSQL ( satData );
							
				
			}
			parserLogger.info( "Parsing and storing into SQL database done!" );
		}
		catch ( IOException e )
		{			
			parserLogger.error ("File is not accessible!");
			System.exit(0);
		}		
	}
}



