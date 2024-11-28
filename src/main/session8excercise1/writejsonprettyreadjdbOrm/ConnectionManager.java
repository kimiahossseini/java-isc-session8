package writejsonprettyreadjdbOrm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class ConnectionManager {
private static final Logger log= Logger.getLogger(ConnectionManager.class.getName());
	
	private static Connection connection=null;

	private ConnectionManager() {
		super();	
	}
	
	//-----method getconnection check connection---------singlton-----------------------
	private static final String URL = "jdbc:h2:tcp://localhost:9092/~/testdb";
	private static final String USER = "sa"; 
	private static final String PASSWORD = "";
	
	public static Connection getConnection(){

		if (connection==null) {
			
					try {
						connection=DriverManager.getConnection(URL, USER, PASSWORD);
					} catch (SQLException e) {
						log.severe("Database connection Error");
						e.printStackTrace();
					}
				
	
		}return connection;
		
	}
	 
	
}
