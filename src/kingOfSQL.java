import java.sql.*;  
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

// Class for storing data into SQL database

public class kingOfSQL
{	
	
	Logger sqlLogger = Logger.getLogger( kingOfSQL.class );
	
	// String used to create a SQL database:
	String satSQL = 
			"CREATE TABLE IF NOT EXISTS Satellites (" + 
            " Position       CHAR(50), " +
            " Name      	 CHAR(50), " + 
            " Frequency      CHAR(50), " + 
            " Polarization   CHAR(50), " +
            " Transponder    CHAR(50), " + 
            " Bundle         CHAR(50), " +
            " Standard       CHAR(50), " +
            " Modulation     CHAR(50), " +
            " SymbolRate     CHAR(50), " +
            " FEC            CHAR(50), " + 
            " Provider       CHAR(50), " +
            " BitRate        CHAR(50), " +
            " NID            CHAR(50), " +
            " TID            CHAR(50))"; 
	
		
    static Statement  stat = null;	
	static Connection con = null;
	
		
	//***********************************************************************
	// Constructor:
	kingOfSQL( String locSQL )
	{	    		
		BasicConfigurator.configure();	// Logger	
		
		// Trying to connect to database or create it:
		try
		{		
		
			Class.forName ( "org.sqlite.JDBC" );	
	    	sqlLogger.info ( "Class found" );
			con = DriverManager.getConnection("jdbc:sqlite:" + locSQL );
		    sqlLogger.info ( "Connection with SQL database ok!" );
		    stat = con.createStatement();
		    
		    // Creating satellites database:		    
		    stat.executeUpdate(satSQL);
		    ResultSet rsSat = stat.executeQuery( "SELECT count(*) FROM Satellites;");
		    rsSat.next();
		    int cnt = rsSat.getInt(1);
		    rsSat.close();
		    
		    if( 0 == cnt )
		    {
		    	sqlLogger.debug("Table created successfully");		   
			}
		    		    
		}
		catch ( SQLException e )
		{			
			sqlLogger.error ( "SQL exception!  " + e );
			System.exit(0);
		}
		catch ( ClassNotFoundException e )
		{
			sqlLogger.error ( "Class not found!  " + e );
			
		}
	}
		
	
	//***********************************************************************
	// Function for wrapping String into apostrophes:
	private static String intoApos ( String temp )
	{ 		
		return ("'" + temp + "'");		
	}
			
	
	// **********************************************************************
	// storeSatSQL:
	public void storeSatSQL ( satDataStorage satData )
	{
		String data =
				
		"INSERT INTO Satellites ( Position, Name, Frequency, Polarization, Transponder," +
	    " Bundle, Standard, Modulation, SymbolRate, FEC, Provider, BitRate, NID, TID ) " +
	    "VALUES (" 
	    + intoApos( satData.position 	) + ", "
	    + intoApos( satData.name 		) + ", "
	    + intoApos( satData.freq 		) + ", " 
	    + intoApos( satData.polar 		) + ", " 
	    + intoApos( satData.transponder ) + ", " 
	    + intoApos( satData.bundle 		) + ", " 
	    + intoApos( satData.standard 	) + ", " 
	    + intoApos( satData.modulation 	) + ", " 
	    + intoApos( satData.symbolRate 	) + ", " 
	    + intoApos( satData.FEC 		) + ", " 
	    + intoApos( satData.provider 	) + ", " 
	    + intoApos( satData.bitRate		) + ", " 
	    + intoApos( satData.NID 		) + ", " 
	    + intoApos( satData.TID 		) + ")" ; 
		
		// Store into SQL database:		
		try
		{
			stat.executeUpdate( data );
			sqlLogger.debug ( "Satellite parameters added!" );
		}
		catch ( SQLException e )
		{
			sqlLogger.error ( "Could not store data  " + e );			
		}		
	}		
}
