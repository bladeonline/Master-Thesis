package Chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class main {

	static ArrayList<Message> message;
	static Connection conn;
	static ArrayList<Expression> expression;
	static ArrayList<String> category= new ArrayList<String>();
	static HashMap <Integer, Price> price = new HashMap <Integer, Price>();
	static Statement statement;
	boolean gui_needed=true;
	
	
	/*
	 * 
	 * Constructor, in case, no GUI is needed
	 * 
	 */
	public main(boolean gui_needed){
		this.gui_needed=gui_needed;
		
	}
	
	
	
	/*
	 * 
	 * Empty Constructor for all other cases
	 * 
	 */
	public main(){
		
		
	}
	
	
	
	
	void load_category(){
		
		category.add("general");
		category.add("politics");
		category.add("ethnicity");
		category.add("religion");
		category.add("philosophical");
		category.add("genetic data");
		category.add("biometrics");
		category.add("sexual orientation");
		category.add("health");
		
	}
	
	 public static void connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:privacy.db");
			statement = conn.createStatement();
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			
		
			
			
		}
			
	}
	 
	 
	 
	 
	public static void write_price_initial() throws SQLException{
		
		
		
		String result;
		
		for (int i=1;i<=price.size();i++){
			result ="INSERT INTO price('level', 'price', 'Changing_Factor', 'treshold', 'direction') VALUES";
			result+="(" + (i) + ", " + price.get(i).price+", "+price.get(i).changing+", "+ price.get(i).treshold+", "+ price.get(i).direction + ");";
			
			statement.execute(result);
			
			
			
		}
		
		
	} 
	
	public static void update_price(int level, Price tprice) throws SQLException{

		String queue = "UPDATE price SET price ="+tprice.price+" AND Changing_Factor="+tprice.changing;
		queue+=" AND treshold="+tprice.treshold+" AND direction="+tprice.direction+" WHERE level="+level+";";
			statement.execute(queue);
		
		
	}
	 
	 
	public static boolean expression_exists(String found_expression){
		for(int i=0;i<expression.size();i++){
			if(expression.get(i).expression.equals(found_expression))
				return true;
			
		}
		return false;
		
	}
	
	
	
	 
	 
	 public static int find_message(String message_user, String time){
	
		 
		 for(int i=0;i<message.size();i++){
			 if(message.get(i).message.equals(message_user) && message.get(i).time.equals(time))
				 return i;
			 
			 
		 }
		 return -1;
		 
		 
		 
		 
	 }
	 
	 

	 
	 public void load_price(){
		 
		 String execute = "SELECT * From price";
		 
			 try {
				ResultSet rs = statement.executeQuery(execute);
				
				while(rs.next()){
					
					
					price.put(rs.getInt(1), new Price( rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 
		 
		 
	 }
	 
	 
	 
	 public void fill_expression(){
		 
		 String execute = "SELECT * FROM privacy_tag";
		 ResultSet rs;
		 String tag;
		 
		 try {
			rs = statement.executeQuery(execute);
			 while(rs.next()){
				 
				 
				 
				 tag = rs.getString("tag");
				 tag=tag.replaceAll("#", "'"); //SQL does not support apostrophs, this why the expressions were changed to #, this changes it back to '
				 
				 
					expression.add(new Expression(tag, rs.getString("category"), rs.getInt(2)     ));
					 
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	
		 
		 
		 
		 
	 }
	 

	 
	 
	
	 
	 static Message getMessage(String message, String time){
		 Message mess_return =null;
		 Message temp=null;
		for(int i=0;i<main.message.size();i++){
			temp=main.message.get(i);
			if(temp.message.equals(message) && temp.time.equals(message))
				break;
			
		}
		 mess_return = temp;
		 
		 
		 return mess_return;
	 }
	 
	 
	 

void load(){


	message = new ArrayList<Message>();
	expression = new ArrayList<Expression>();
	
	
	connect();

	load_category();

	fill_expression();
	
	load_price();


	
}
	 


	void initialize() {
		load();
		if(gui_needed)
		new GUI();

	}

	
	
	public static void main(String[] args) {

		new main().initialize();

	}

}
