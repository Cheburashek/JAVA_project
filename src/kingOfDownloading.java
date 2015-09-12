import java.io.IOException;  

import java.io.File;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;  
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class kingOfDownloading
{

    
    Logger downloaderLogger = Logger.getLogger( mainApp.class );

	
	// Constructor:
    kingOfDownloading()
	{		
		BasicConfigurator.configure();		
	}
	
    
	//Trying to connect and download data from the website to a file on disk:
    public void getFile ( String address, File file, String location )
    {
    	Document doc;
        StringBuilder content = new StringBuilder();
        FileWriter fileWriter;
        
        try
        {        	
        	fileWriter = new FileWriter ( location );
            BufferedWriter bufferedWriter = new BufferedWriter ( fileWriter );
      
	        try	        
	        {
	        	doc = Jsoup.connect ( address ).get(); 
	            content.append( doc.body().html() );
	            bufferedWriter.write ( content.toString() );
	            bufferedWriter.close ();    	   	
	
	            String title = doc.title();  
	            downloaderLogger.info ( "Website title: \"" + title + " \"" ); 
				downloaderLogger.debug ( "Page succesfully readed" );
	
	        }
	        catch ( IOException e )       
	        {        	
	            downloaderLogger.error ( "Cannot connect to website!" + e ); 
	        }
	        
			downloaderLogger.debug ( "Page succesfully stored in file: " + location );
	        
        }     
        catch ( IOException e )
        {
        	downloaderLogger.error ( "Problems with file" + e ); 
        }

    }
    

}
