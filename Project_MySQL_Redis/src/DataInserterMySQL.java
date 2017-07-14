import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataInserterMySQL {
	static Connection connect;
	static Statement stmt;

	public static void createConnection() {
		try {
			String host= "jdbc:mysql://localhost/test";
			String name= "root";
			String pass="";
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connected MySQL JDBC driver");
			
			connect= DriverManager.getConnection(host,name,pass);
		    System.out.println("Connected MySql");
		} catch (Exception e) {
			
			System.out.println("Problem with connection database");
		}
	}
	public static void createTable(){
		//createConnection();
		
		try {
			
		   
		    
		    stmt = connect.createStatement();
		      
		      String sql = "CREATE TABLE COMMENTS_9 " +
		                   "(Id BIGINT NOT NULL, "+
		    		       "PostId BIGINT, "+
		                   " Score INT, " + 
		                   " Text VARCHAR(9000), " + 
		                   "CreationDate VARCHAR(100), " +
		                   "UserId BIGINT,"+
		                   " PRIMARY KEY ( Id ))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
		      
		       
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// create connection
		createConnection();
		
		createTable();

		
		try {
		    Comment temp;
		    stmt = connect.createStatement();
		    
	    	for(int i=1;i<=9;i++){
	    		String filename="/home/afrina/XMLFiles/Comments_"+i+".xml";
	    		try{
	    			String statement= "load xml infile '"+filename +"' into table test.COMMENTS_9 rows identified by '<row>';";
	    			System.out.println(statement);
	  		      	stmt.executeUpdate(statement);
	    		} catch (Exception e){
	    			System.out.println("File " + i + " not parsed.");
	    		}
	    	}
	
		} 
	     catch ( Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			System.out.println("Problem With closing Database");
		}
		System.out.println("program is finished");
	}

}
