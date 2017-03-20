package Chat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Import {

	FileInputStream fis;
	BufferedReader br;
	
	
	public void open(String source) throws FileNotFoundException{
		
		fis = new FileInputStream(source);
		
		br= new BufferedReader(new InputStreamReader(fis));
		
		
	}
	
	
	
	public void connect(){
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			main.conn = DriverManager.getConnection("jdbc:sqlite:privacy.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void insert(String category) throws IOException, SQLException{
		
		String line;
		
		String insert;
		
		
		main.statement=main.conn.createStatement();
		
		while((line= br.readLine())!=null){
			
			line= line.replaceAll("'", "#"); // Change apostroph to hashtag
		
		
			

			
			
		insert = "INSERT INTO privacy_tag (\"tag\", \"level\", \"category\") VALUES ('"+line+"', '-1', '"+category+"')";
		
		
		main.statement.execute(insert);
			
			
			
		}
		
		
		
		
		
	}
	
	
	
//	public static void main(String [] args){
//		
//		Database_Import db = new Database_Import();
//		
//		
//		try {
//			db.open("C:/test.csv");
//			db.connect();
//			try {
//				db.insert("health");
//			} catch (IOException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//	}
}
