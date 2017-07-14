import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.redisson.Redisson;

import com.mysql.jdbc.ResultSet;

//SELECT * FROM `COMMENTS` c1 where (select COUNT(*) from COMMENTS c2 where c1.Score > c2.Score) < 3 

public class MySQLQueryRunner {
	static Connection connect;
	static Statement stmt;

	public static void connectMySQL(){
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
	
	
	public static void showTop3LikedComment (){
		System.out.println("Running query in mySQL...");
		Statement mystat;
		try {
			mystat = connect.createStatement();
			System.out.println("statement created");
			ResultSet res= (ResultSet) mystat.executeQuery("Select * from test.COMMENTS_9 order by Score desc limit 3;");
			
			System.out.println("Top 3 most liked comments are :");
			int i=0;
			while(res.next()){
					    	
		    	System.out.println(res.getLong("Id")+ ", "+ res.getString("Text"));
		    	i++;
		    	if(i==3) break;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("error hoise");
		}
	}
	
	
	
	public static void showMostRecentComment (){
		System.out.println("Running query in mySQL...");
		Statement mystat;
		try {
			mystat = connect.createStatement();
			System.out.println("statement created");
			ResultSet res= (ResultSet) mystat.executeQuery("Select * from test.COMMENTS_9 order by CreationDate desc limit 1;");
			
			System.out.println("The most recent comment is :");
			int i=0;
			while(res.next()){
					    	
		    	System.out.println(res.getLong("Id")+ ", "+ res.getString("Text"));
		    	i++;
		    	if(i==1) break;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("error hoise");
		}
	}
	
	public static void showMostCommentedPost (){
		System.out.println("Running query in mySQL...");
		Statement mystat;
		try {
			mystat = connect.createStatement();
			System.out.println("statement created");
			ResultSet res= (ResultSet) mystat.executeQuery("Select PostId,count(*) as numOfCom from test.COMMENTS_9" +
					" Group by PostId order by PostId desc limit 1;");
			
			System.out.println("The most commented Post is :");
			int i=0;
			while(res.next()){
					    	
		    	System.out.println("POST ID - "  + res.getLong("PostId")+ ", Total Comments"+ res.getInt("numOfCom"));
		    	i++;
		    	if(i==1) break;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("error hoise");
		}
	}
	
	
}
