/*
 * 
 * Database Class, in SQlite; Additioal resource added to the project 
 * 
 */

package Temp;


import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Database {

	 public Connection conn=null;
	 public Statement message;
	 String answer;
	 
	 public static Connection connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:privacy.db");
		
			
			return conn;
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			
			return null;
			
			
		}
			
	}
	
	 
	 public int getNumberRows(Statement statement, ResultSet results, String table){
		  try{
		       int counter=0;
		     
		       results = statement.executeQuery("SELECT COUNT (*) FROM "+table);
		      
		       
		       
		    return results.getInt(1);
		    } catch (Exception e){
		       System.out.println("Error getting row count");
		       e.printStackTrace();
		    }
		    return 0; 
		 
		 
		 
	 }
	 
	public void CloseConnection(){
		try {
			
			message.close();
			
			if(!conn.isClosed())
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		}
	
	public void gettables(){
		try{
		conn=connect();
		message=conn.createStatement();
		conn.setAutoCommit(false);
//		message=conn.createStatement();
//		String name="'test1'";
//		String IP= "'localhost'";
//		
//		
//		answer = "INSERT INTO connection(name, IP) VALUES("+name+", "+IP+")";
//		System.out.println(answer);
//		System.out.println("na1");
//		
//		message.executeUpdate(answer);
//		System.out.println("na2");
//		
//		name="'test2'"; IP="'127.0.0.1'";
//		answer = "INSERT INTO connection(name, IP) VALUES("+name+", "+IP+")";
//		System.out.println(answer);
//		message.executeUpdate(answer);
//		
//		System.out.println("funst3");
		System.out.println("geht noch1");
		answer = "SELECT * FROM connection";
		System.out.println("geht noch2");
		ResultSet r=message.executeQuery(answer);
		System.out.println("geht noch3");
		while(r.next()){
			System.out.println("geht noch4");
			System.out.println("Name="+r.getString("name")+", IP="+r.getString("IP"));
			
		}
		System.out.println("geht noch5");
		r.close();
		
		CloseConnection();
		}catch(SQLException e){
			e.getErrorCode();
		
							}	
	}
	
	public void setcontact(String name, String IP){

	
			
			
			
		

		
		try{
			
			conn=connect();
			message=conn.createStatement();
			

			answer= "INSERT INTO connection(\"name\", \"IP\") VALUES ('"+name+"', '"+IP+"')";
			message.execute(answer);
			CloseConnection();
			
		}catch(SQLException e){
			e.printStackTrace();;
			
		}
		
		
	}
 	public String[][] getContacts(){
		ArrayList<ArrayList<String>> contacts= new ArrayList<ArrayList<String>>();
		conn =connect();
		try {
			message=conn.createStatement();
			
			answer = "SELECT * FROM Connection";
			
			
			ResultSet result1= null;
			
			int rows=getNumberRows(message, result1, "connection");
			ResultSet result = conn.createStatement().executeQuery(answer);
			
			
			

			
			String [][] columns = new String[rows][2];
		int counter=0;	
		while(result.next()){
				

			columns[counter][0]=result.getString("name");
			columns[counter][1]=result.getString("IP");
				counter++;
			}
			
			CloseConnection();
			return columns;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
	}

 	public void deletecontact(String IP){
 		
		try{
			
			conn=connect();
			message=conn.createStatement();
			

			answer= "DELETE FROM connection WHERE IP="+IP;
			message.execute(answer);
			CloseConnection();
			
		}catch(SQLException e){
			e.printStackTrace();;
			
		}
 		
 		
 		
 		
 	}
 	
 	
	public void CreateTables(){
		
		
		try {
			conn=connect();
			System.out.println("ja");
			System.out.println("a");
			message=conn.createStatement();
			System.out.println("b");
			answer = "CREATE TABLE connection (name VARCHAR PRIMARY KEY  NOT NULL , IP VARCHAR NOT NULL  UNIQUE )" ;
			System.out.println("c");
			message.execute(answer);
			
			message=conn.createStatement();
			answer = "CREATE TABLE price (level NUMERIC PRIMARY KEY  NOT NULL  UNIQUE , price NUMERIC NOT NULL )" ;
			message.execute(answer);
			
			//message=conn.createStatement();
			answer = "CREATE TABLE privacy_tag (tag TEXT PRIMARY KEY  NOT NULL  UNIQUE , level NUMERIC NOT NULL )" ;
			message.execute(answer);
			
			System.out.println("geschafft final");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}	

	
	
	public static void main(String[]args) throws SQLException{
		Database db= new Database();
		
		Connection conn=db.connect();
		Statement statement=conn.createStatement();
		ResultSet bla=null;
		
		System.out.print(db.getNumberRows(statement, bla, "connection"));
	
		
		//db.getContacts();
		
	}

	
}
